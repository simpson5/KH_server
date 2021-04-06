package board.conroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MvcFileRenamePolicy;

/**
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardService();

	/**
	 * 0. form의 속성 enctype="multipart/form-data" 추가
	 * 1. MutipartRequest객체 생성 : 서버컴퓨터 파일 저장
	 * 		- request
	 * 		- 저장경로
	 * 		- encoding
	 * 		- 최대허용크기
	 * 		- 파일명 변경정책 객체
	 * 	2. db에 파일정보를 저장
	 * 		- 사용자가 저장한 파일명 original_filename
	 * 		- 실제 저장된 파일명 renamed_filename
	 * 
	 *  MutilpartRequest객체를 사용하면,
	 *  기존 HttpServletRequest에서는 사용자 입력값에 접근할 수 없다.
	 *  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. MutipartRequest객체 생성
		// /WebContent/upload/board/업로드파일명.jpg
		// 맨 압픠 / 가 /WebContent/이다.
		// web root dir를 절대경로로 반환
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		System.out.println("saveDirectory@servlet = " + saveDirectory);
		
		// 최대 파일 허용 크기 10mb = 10 * 1kb * 1kb
		int maxPostSize = 10 * 1024 * 1024; // byte 단위로 전달
		
		//인코딩
		String encoding = "utf-8";
		
		//파일명 변경정책 객체
		//중복파일인 경우, numbering처리
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		//생성자가 만들어지면 파일 저장이 끝난다!
		MultipartRequest multipartRequest =
				new MultipartRequest(
								request,
								saveDirectory,
								maxPostSize,
								encoding,
								policy
							);
		
		//2. db에 게시글/첨부파일 정보 저장
		
		//2-1. 사용자 입력값처리
		// request 는 null이기에 multipartRequest에서 꺼내와야 한다.
		String title = multipartRequest.getParameter("title");
		String writer = multipartRequest.getParameter("writer");
		String content = multipartRequest.getParameter("content");
		
		//업로드한 파일명
		//filerename : 20210406191919_123.jpg
		String originalFileName = multipartRequest.getOriginalFileName("upFile");
		String renamedFileName = multipartRequest.getFilesystemName("upFile");
		
		//게시판
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//첨부파일이 있는 경우
		//multipartRequest.getFile("upFile")
		if(originalFileName != null) {
			Attachment attach = new Attachment();
			attach.setOriginalFileName(originalFileName);
			attach.setRenamedFileName(renamedFileName);			
			board.setAttach(attach);
		}
		
		//번호 가져오기

		//2-2. 업무로직 : db에 insert
		int result = boardService.boardEnroll(board);
		//seq curr 값 단독 조회 불가능...
//		int boardNo = boardService.selectLastBoardNo();
		String msg = (result > 0) ? "게시글 등록 성공!" : "게시글 등록 실패!";
		//3. DML요청 : 리다이렉트 & 사용자피드백
		HttpSession session = request.getSession(true);
		if(result == 1) {
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/board/boardView?no=" + boardService.no);
		}
	}
}

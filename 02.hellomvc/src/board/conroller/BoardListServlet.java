package board.conroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardCommentCnt;
import common.MvcUtils;
import member.model.vo.Member;

/**
 * 페이징
 * - 한페이지당 게시글수 5개 numPerPage = 5;
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 인코딩 EncodingFilter
		
		//1. 사용자입력값
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
		}
		
		//2. 업무로직
		//a. contents영역 start ~ end
		int end = cPage * numPerPage;
		int start = end - (numPerPage - 1);
		
		//조인으로 하는 방법도 생각해보자
		List<BoardCommentCnt> list = boardService.selectList(start, end);
		
		//b. pagebar 영역 작업
		int totalContents = boardService.countBoardList();
		String url = request.getRequestURI();
		
		String pageBar = MvcUtils.getPageBar(
				cPage, numPerPage, totalContents, url
				);
		
		//4. jsp에 html 응답메세지 작성 위임
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp")
		       .forward(request, response);
	}
}

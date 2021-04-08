package board.conroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값
			int no = Integer.parseInt(request.getParameter("no"));
			
			//2. 업무로직
			int result = boardService.boardDelete(no);
			String msg = result > 0 ? "삭제 성공" : "삭제 실패";
			
			//3.리다이렉트 & 사용자피드백
			HttpSession session = request.getSession(true);
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/board/boardList");			
		} catch(Exception e) {
			//예외 로깅
			e.printStackTrace();
			//관리자 이메일 알림
			//예외 페이지 전환을 위해서 was에 예외던짐.
			throw e;
		}
	}

}

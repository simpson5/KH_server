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
@WebServlet("/binsert")
public class BoardWriteServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String btitle = request.getParameter("btitle");
			String bwriter = request.getParameter("bwriter");
			String bcontent = request.getParameter("bcontent");
			
			//게시판
			Board board = new Board();
			board.setbTitle(btitle);
			board.setbWriter(bwriter);
			board.setbContent(bcontent);
			
			int result = boardService.insertBoard(board);
			response.sendRedirect(request.getContextPath() + "/board/boardView?no=" + board.getNo());
		} catch(Exception e) {
			e.printStackTrace();
			throw e; // was한테 다시 던져서 에러페이지로 전환
		}
	}
}

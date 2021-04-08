package customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CsCommentService;
import customer.model.vo.CsComment;

/**
 * Servlet implementation class CsCommentServlet
 */
@WebServlet("/customer/csComment/*")
public class CsCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CsCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if (url.indexOf("csCommentInsert.do") != -1) {
			
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String cscomment = request.getParameter("cscomment");
			
			CsComment c = new CsComment(0,boardNo,cscomment);
			System.out.println("insert Servlet");
			int result = new CsCommentService().insertCsComment(c);
			System.out.println("scCommentServlet@result="+result);
			String msg = "";
			String loc = "/customer/customerService/customerServiceView.do?boardNo="+boardNo;
			
			if(result>0) msg = "댓글을 등록헀습니다.";
			else msg = "댓글을 등록하지 못했습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
		
		}
		else if(url.indexOf("deleteCsComment.do") != -1) {

			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int csCommentNo = Integer.parseInt(request.getParameter("csCommentNo"));
			
			int result = new CsCommentService().deleteCsComment(csCommentNo);
			
			System.out.println("deleteResult="+result);
			
			String msg = "";
			String loc = "/customer/customerService/customerServiceView.do?boardNo="+boardNo;
			
			if(result>0) msg = "댓글을 삭제헀습니다.";
			else msg = "댓글을 삭제하지 못했습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
			
			
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/memberLogin")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String referer = request.getHeader("Referer");
		
//		if("http://localhost:9090/semi/member/memberLoginCheck".equals(referer)) {
//			referer = request.getHeader("Referer");
//			System.out.println(referer);
//		}
		
		request.getSession().setAttribute("prevPage", referer);
		
		request.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

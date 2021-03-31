package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 영어니깐 인코딩 필요 없음
		
		//1. 값 가져오기
		String memberId = request.getParameter("memberId");
		
		//2. 업무로직 삭제
		int result = new MemberService().deleteMember(memberId);
		
		HttpSession session = request.getSession();
		if(result == 1) {
			session.setAttribute("msg", "탈퇴성공");
			session.removeAttribute("loginMember");
			response.sendRedirect(request.getContextPath());
			//logout페이지 지정
		} else {
			session.setAttribute("msg", "탈퇴실패");
			request.getRequestDispatcher("/WEB-INF/views/member/memberView.jsp")
			       .forward(request, response);
		}
	}

}

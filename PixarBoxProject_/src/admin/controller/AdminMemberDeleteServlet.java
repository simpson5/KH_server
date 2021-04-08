package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/admin/memberDelete")
public class AdminMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
//		System.out.println("memberId@AdminServlet="+memberId);
		
		int result = new AdminService().deleteMemberFromAdmin(memberId);
		
		String msg = "";
		String loc = "/admin/memberList";
		
		if(result>0) {
			msg = "회원탈퇴가 정상적으로 처리되었습니다.";
			
			Member memberLoggedIn = (Member)request.getSession().getAttribute("memberLoggedIn");
			if(!"admin".equals(memberLoggedIn.getMemberId())) {
				loc = "/member/memberLogout";
			}
		}
		else msg = "회원탈퇴에 실패했습니다. 관리자에게 문의하세요.";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

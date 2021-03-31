package admin.controller;

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
 * Servlet implementation class AdminMemberRoleUpdate
 */
@WebServlet("/admin/memberRoleUpdate")
public class AdminMemberRoleUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. request값 가져오기
		String memberId = request.getParameter("memberId");
		String memberRole = request.getParameter("memberRole");
		
		//2. 업무로직 처리 : memberRoleUpdate
		//전해야할 변수의 수가 늘어난다면 객체로 만들어 보내자.
		int result = new MemberService().memberRoleUpdate(memberId, memberRole);
		HttpSession session = request.getSession();
		String msg = null;
		String location = request.getContextPath(); 
		if(result > 0) {
			msg = "수정 성공";
			//현재 로그인한 멤버의 정보가 변할 경우
			Member m = (Member)session.getAttribute("loginMember");
			if(m.getMemberID().equals(memberId)) {
				m.setMemberRole(MemberService.MEMBER_ROLE);
				session.setAttribute("loginMember", m);
			}
			else {
				location += "/admin/memberList";
			}
		}
		else {
			msg = "수정 실패";
			location += "/admin/memberList";
		}
		
		//3. 리다이렉트 및 사용자 피드백
		session.setAttribute("msg", msg);
		response.sendRedirect(location);
	}

}

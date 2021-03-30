package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/*
	@실습문제: POST /mvc/member/updatePassword
	1. 적절한 유효성검사후 폼 제출할 것.
	2. 기존비밀번호가 일치할때만, 신규비밀번호로 업데이트한다.
	   기존비밀번호 일치여부를 어떻게 확인할것인가.
	3. 신규비밀번호 업데이트후에는 
		/mvc/member/memberView로 리다이렉트한다.
	4. 로그인하지 않고 접근시에는 어떻게 할것인가.
	5. POST /mvc/member/memberUpdate 는 비밀번호처리를 제외.
*/
@WebServlet("/member/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 비밀번호 변경페이지 제공
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/updatePassword.jsp")
			   .forward(request, response);
	}

	/**
	 * 비밀번호 변경처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 비밀번호 가져오기
		String password = MvcUtils.getSha512(request.getParameter("password"));
		System.out.println("jspPassword@servlet = "+password);
		String newPassword = MvcUtils.getSha512(request.getParameter("newPassword"));
		
		//2. 비밀번호 일치 여부 확인
		//현재 로그인 정보 가져오기
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		String memberId = member.getMemberID();
		//System.out.println("loginMemberId@servlet = " + memberId);
		//db에서 member 가져오기
		member = new MemberService().selectOne(memberId);
		//3. 위의 비밀번호와 db의 비밀번화 일치 확인하기
		//msg를 alert으로 저장
		String msg = null;
		int result = 0;
		if(password.equals(member.getPassword())) {
			result = new MemberService().updatePassword(memberId, newPassword);
			if(result > 0) {
				msg = "비밀번호를 성공적으로 변경했습니다.";
				session.setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath()+"/member/memberView");
			}
			else {
				//???
			}
		}
		else {
			msg = "비밀번호가 일치하지 않습니다.";
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath()+"/member/updatePassword");
		}
	}
}

package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email")+"@"+request.getParameter("emailAdd");
		String phone = request.getParameter("phone1")+request.getParameter("phone2")+request.getParameter("phone3");
		String interests[] = request.getParameterValues("genre");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
//		int point = Integer.parseInt(request.getParameter("point"));
		
		String interest = "";
		if(interests != null) interest = String.join(",", interests);
		
		Member m = new Member();
		m.setMemberId(memberId);
		m.setLastName(lastName);
		m.setFirstName(firstName);
		m.setEmail(email);
		m.setPhone(phone);
		m.setInterests(interest);
		m.setGender(gender);
		m.setAge(age);
//		m.setPoint(point);
//		System.out.println("m@memberUpdateServlet="+m);
		
		int result = new MemberService().updateMember(m);
		
		String msg = "";
		
		if(result>0) {
			msg = "회원정보수정을 성공했습니다.";
			
			Member memberLoggedIn = (Member)request.getSession().getAttribute("memberLoggedIn");
			if(!"admin".equals(memberLoggedIn.getMemberId())) {
				request.getSession().setAttribute("memberLoggedIn", m);
			}
		}
		else msg = "회원정보수정을 실패했습니다. 관리자에게 문의하세요.";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "/member/my/update.do?memberId="+memberId);
		
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

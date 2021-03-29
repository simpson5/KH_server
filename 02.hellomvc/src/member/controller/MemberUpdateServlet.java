package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.exception.MemberException;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. encoding처리
		request.setCharacterEncoding("utf-8");

		// 2. 값 자겨오기
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String gender = request.getParameter("gender");
		String birthday_string = request.getParameter("birthday");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String[] hobbys = request.getParameterValues("hobby");
		String memberRole = new MemberService().MEMBER_ROLE;
		Date enrolldate = new Date(new java.util.Date().getTime());
		String hobby = ""; // db에는 null로 처리된다.

		for (int i = 0; i < hobbys.length; i++) {
			hobby += hobbys[i];
			if (i != hobbys.length - 1) {
				hobby += ",";
			}
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date birthday = new Date(new java.util.Date().getTime());

		// 예외 처리 왜 하는거지?
		try {
			birthday = new Date(dateFormat.parse(birthday_string).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Member member = new Member(memberId, password, memberName, memberRole, gender, birthday, email, phone, address,
				hobby, enrolldate);

		// 3. DB에 저장
		HttpSession session = request.getSession(true);
		int result = 0;
		result = new MemberService().memberUpdate(member);

		// 4. result 값을 seccion으로 저장한뒤 리다이렉트
		if (result == 1) {
			session.setAttribute("msg2", "회원정보 수정 성공");
			response.sendRedirect(request.getContextPath());
			session.setAttribute("loginMember", member);
			System.out.println("수정전@servlet" + (Member)session.getAttribute("loginMember"));
			System.out.println("수정후@servlet" + member);
		} else {
			session.setAttribute("msg2", "회원정보 수정 실패");
			request.getRequestDispatcher("/WEB-INF/views/member/memberView.jsp").forward(request, response);
		}
	}

}

package com.simp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.member.model.service.MemberService;
import com.simp.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/enroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//회원가입 페이지 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/join.jsp")
		       .forward(request, response);
	}

	//회원 가입 확인
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");
		String member_name = request.getParameter("member_name");
		String member_ssn = request.getParameter("member_ssn");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("postcode") + "["
						+ request.getParameter("address") + "]" + ":"
						+ request.getParameter("dt_adress") + "("
						+ request.getParameter("refer") + ")";
		String[] genres = request.getParameterValues("genre");
		String genre = "";
	
		for(int i = 0; i < genres.length; i++) {
			genre += genres[i];
			if(i != genres.length - 1) {
				genre += ",";
			}
		}
		
		Member member = new Member();
		member.setMember_id(member_id);
		member.setPassword(password);
		member.setMember_name(member_name);
		member.setMember_ssn(member_ssn);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAddress(address);
		member.setPreference_genre(genre);
		
		int result = new MemberService().enrollMember(member);
		
		if(result != 0) {
			System.out.println("성공!");
		}
		
		response.sendRedirect(request.getContextPath());
	}

}

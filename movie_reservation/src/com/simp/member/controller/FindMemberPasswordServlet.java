package com.simp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.member.model.service.MemberService;

/**
 * Servlet implementation class FindMemberPasswordServlet
 */
@WebServlet("/member/findPassword")
public class FindMemberPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memberId =request.getParameter("member_id");
		String memberName = request.getParameter("member_name");
		
		System.out.println( memberName);
		System.out.println( memberId);
		
		String password= new  MemberService().FindPassword(memberName, memberId);
		
		System.out.println("FindMemberID@servlet = " + password);
		
		request.setAttribute("password", password);
		request.getRequestDispatcher("/WEB-INF/views/member/findPassword.jsp")
		   .forward(request, response);
	}

}

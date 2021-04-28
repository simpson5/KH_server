package com.simp.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.member.model.service.MemberService;
import com.simp.member.model.vo.Member;

/**
 * Servlet implementation class FindMemberId
 */
@WebServlet("/member/findMemberId")
public class FindMemberId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		String memberName = request.getParameter("member_name");
		String email =request.getParameter("email");
		
		System.out.println( memberName);
		System.out.println( email);
		
		String member_id= new  MemberService().FindInfo(memberName, email);
		
		System.out.println("FindMemberID@servlet = " + member_id);
		
		request.setAttribute("member_id", member_id);
		request.getRequestDispatcher("/WEB-INF/views/member/findMemberId.jsp")
		   .forward(request, response);
		
	}
	
	

}
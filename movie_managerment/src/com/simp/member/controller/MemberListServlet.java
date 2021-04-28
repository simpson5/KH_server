package com.simp.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.member.model.service.MemberService;
import com.simp.member.model.vo.Member;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/member/memberlist")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//System.out.println("[utf-8] encoding 처리함.");
		
		List<Member> list = new MemberService().selectList();
		request.setAttribute("list", list);
		
		//System.out.println(list);
		
		//2. jsp 위임처리
		request.getRequestDispatcher("/WEB-INF/views/member_list.jsp")
			   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
	}

}

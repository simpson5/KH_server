package com.simp.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.member.model.service.MemberService;

/**
 * Servlet implementation class MemberIDServlet
 */
@WebServlet("/member/checkId")
public class MemberIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		List<String> list = new MemberService().listMemberId();
		
		System.out.println(list);
		
		int result = 0;
		
		if(list.contains(memberId)) {
			result = 0;
		} else {
			result = 1;
		}
		response.setContentType("text/csv; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}
}

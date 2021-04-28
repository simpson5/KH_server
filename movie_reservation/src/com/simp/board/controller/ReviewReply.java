package com.simp.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.board.model.service.BoardService;

/**
 * Servlet implementation class ReviewReply
 */
@WebServlet("/review/reply")
public class ReviewReply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int r_no = Integer.parseInt(request.getParameter("r_no"));
		String member_id = request.getParameter("member_id");
		String r_content = request.getParameter("r_content");
		System.out.println(r_no);
		System.out.println(member_id);
		System.out.println(r_content);
		
		// dao 작업
		int result = new BoardService().insertReply(r_no, member_id,r_content);
		
		System.out.println(result);
		
		response.setContentType("text/csv; charset=utf-8"); // 인코딩
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}

}

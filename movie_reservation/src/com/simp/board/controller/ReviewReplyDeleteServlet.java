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
 * Servlet implementation class ReviewReplyDeleteServlet
 */
@WebServlet("/review/reply/del")
public class ReviewReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("replyNo@servlet = "+no);
		
		int result = boardService.deleteReply(no);
		System.out.println("replyNo@servlet = " + result);
		
		response.setContentType("text/csv; charset=utf-8"); // 인코딩
		PrintWriter out = response.getWriter();
		out.println(result);
	
	
	}
}

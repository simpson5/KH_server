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
 * Servlet implementation class ReviewRecomUpServlet
 */
@WebServlet("/review/recomUp")
public class ReviewRecomUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int r_no = Integer.parseInt(request.getParameter("r_no"));
		String member_id = request.getParameter("member_id");
		System.out.println(r_no);
		
		//다오 작업
		//결과를 스트링으로
		String result = boardService.RecomUp(r_no, member_id);
		System.out.println(result);
				
		
		response.setContentType("text/csv; charset=utf-8"); // 인코딩
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}

}

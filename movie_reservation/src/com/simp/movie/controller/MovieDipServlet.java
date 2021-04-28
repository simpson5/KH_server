package com.simp.movie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.movie.model.service.MovieService;


/**
 * Servlet implementation class MovieDipServlet
 */
@WebServlet("/movie/dip")
public class MovieDipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String movieTitle = request.getParameter("movieTitle");
		
		System.out.println("dip@servlet id : " + memberId + " || title : " + movieTitle);
		
		int result = new MovieService().dipMoive(memberId, movieTitle);
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String movieTitle = request.getParameter("movieTitle");
		
		System.out.println("dip@servlet id : " + memberId + " || title : " + movieTitle);
		
		int result = new MovieService().dipMoiveDelete(memberId, movieTitle);
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}

}

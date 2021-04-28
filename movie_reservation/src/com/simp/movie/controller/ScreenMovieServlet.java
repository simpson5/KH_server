package com.simp.movie.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.member.model.vo.Member;
import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenMovie;



/**
 * Servlet implementation class ScreenMovieServlet
 */
@WebServlet("/movie/screenmovie")
public class ScreenMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService movieservice = new MovieService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1. 업무로직 
		List<ScreenMovie> list = movieservice.selectList();

		//2. jsp에  위임
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/movie_info/screen_movie.jsp")
			   .forward(request, response);
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
package com.simp.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.board.model.service.BoardService;
import com.simp.board.model.vo.Review;
import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenInfo;
import com.simp.movie.model.vo.ScreenMovie;

/**
 * Servlet implementation class MovieInfoServelt
 */
@WebServlet("/movie/movie_info")
public class MovieInfoServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieTitle = request.getParameter("movie_title");
		
		System.out.println(movieTitle);
		
		ScreenMovie sm = new MovieService().movieInfo(movieTitle);
		
		int audience_cnt = new MovieService().audience_cnt(movieTitle);
		
		int female_rate = new MovieService().gender_rate(movieTitle);
		
		System.out.println(female_rate);
		
		if(sm.getMovieTitle() != null) {
			request.setAttribute("screenMovie", sm);
			request.setAttribute("audience_cnt", audience_cnt);
			request.setAttribute("female_rate", female_rate);
		}
		else {
			
		}
		
		List<Review> list = new BoardService().bestReview(movieTitle);
		request.setAttribute("list", list);
		
		System.out.println(sm);
		
		request.getRequestDispatcher("/WEB-INF/views/movie_info/movie_info.jsp")
		       .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

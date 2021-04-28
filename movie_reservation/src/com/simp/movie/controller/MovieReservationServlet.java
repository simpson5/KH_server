package com.simp.movie.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenInfo;
import com.simp.movie.model.vo.ScreenMovie;

/**
 * Servlet implementation class MovieReservationServlet
 */
@WebServlet("/movie/reservation")
public class MovieReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//페이지 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Timestamp sqlDate = new Timestamp(System.currentTimeMillis());
		
		System.out.println("ScreenMovieServlet@현재시간" + sqlDate);
		
		String title = request.getParameter("movie_title");
		
		List<ScreenInfo> list = new MovieService().selectScreenInfo(title, sqlDate);
		
		ScreenMovie sm = new MovieService().movieInfo(title);
		
		request.setAttribute("sm", sm);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/movie_reservation/ticketing.jsp")
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

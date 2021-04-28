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
 * Servlet implementation class ScreenEnrollServlet
 */
@WebServlet("/movie/screenEnroll")
public class ScreenEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieTitle = request.getParameter("movie_title");
		
		ScreenMovie sm = new MovieService().movieInfo(movieTitle);
		
		request.setAttribute("screenMovie", sm);
		request.getRequestDispatcher("/WEB-INF/views/enroll_screen.jsp")
		.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String movieTitle = request.getParameter("movie_title");
		String theater = request.getParameter("theater");
		String screenDate_string = request.getParameter("screen_date");
		String startTime_string = request.getParameter("start_time");
		String endTime_string = request.getParameter("end_time");
		int screenPrice_int = Integer.parseInt(request.getParameter("screen_price"));
		
		System.out.println(movieTitle);
		System.out.println(theater);
		System.out.println(startTime_string);
		System.out.println(endTime_string);
		System.out.println(screenPrice_int);

		
		Timestamp startTime = Timestamp.valueOf(screenDate_string + " " + startTime_string + ":00");
		Timestamp endTime = Timestamp.valueOf(screenDate_string + " " + endTime_string + ":00");
		ScreenInfo si = new ScreenInfo();
		si.setMovieTitle(movieTitle);
		si.setTheaterNo(theater);
		si.setStartTime(startTime);
		si.setEndTime(endTime);
		si.setScreenPrice(screenPrice_int);
		
		System.out.println(startTime.getTime());
		System.out.println(endTime.getTime());
		String msg = "";
		msg = new MovieService().enrollScreen(si);

		HttpSession session = request.getSession(true);
		
		if(!msg.equals("")) {
			session.setAttribute("msg", msg);
		}
		
		response.sendRedirect(request.getContextPath());
	}

}

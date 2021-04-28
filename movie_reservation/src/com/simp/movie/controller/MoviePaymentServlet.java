package com.simp.movie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenInfo;

/**
 * Servlet implementation class MoviePaymentServlet
 */
@WebServlet("/movie/reservation/payment")
public class MoviePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String screen_no = request.getParameter("screen_no");
		String[] seat_no = request.getParameter("seat_no").split(",");
		int amount = Integer.parseInt(request.getParameter("amount"));
		ScreenInfo si = new MovieService().selectOneScreenInfo(screen_no);
		
//		System.out.println(screen_no);
//		System.out.println(amount);
//		System.out.println(seat_no);
		
		request.setAttribute("screenInfo", si);
		request.getRequestDispatcher("/WEB-INF/views/movie_reservation/ticket_order.jsp")
	           .forward(request, response);
	}
}

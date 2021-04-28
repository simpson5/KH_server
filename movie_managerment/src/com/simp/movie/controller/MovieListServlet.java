package com.simp.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenMovie;
import com.simp.product.model.servlcie.ProductService;
import com.simp.product.model.vo.Product;

/**
 * Servlet implementation class MovieListServlet
 */
@WebServlet("/movie/managerment")
public class MovieListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB 조회하기
		List<ScreenMovie> list = new MovieService().selectMovie();
		System.out.println(list);
		
		//forward 위임하기
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/movie_managerment.jsp")
	           .forward(request, response);
	}
}

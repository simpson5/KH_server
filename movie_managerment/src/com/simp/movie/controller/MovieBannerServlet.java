package com.simp.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.Banner;
import com.simp.movie.model.vo.ScreenMovie;

/**
 * Servlet implementation class MovieBannerServlet
 */
@WebServlet("/movie/banner")
public class MovieBannerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DB 조회하기
		List<Banner> list = new MovieService().bannerList();
		System.out.println(list);

		// forward 위임하기
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/banner_managerment.jsp").forward(request, response);
	}

}

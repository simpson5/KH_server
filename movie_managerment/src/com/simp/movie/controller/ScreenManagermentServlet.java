package com.simp.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenInfo;

/**
 * Servlet implementation class ScreenManagermentServlet
 */
@WebServlet("/movie/screen_managerment")
public class ScreenManagermentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ScreenInfo> list = new MovieService().selectScreenInfo();
		
		//forward 위임하기
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/screen_managerment.jsp")
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

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
 * Servlet implementation class ScreenFindServlet
 */
@WebServlet("/movie/screen_find")
public class ScreenFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 값 가져오기
		String title = request.getParameter("search_title");
		
		//2. DB 조회
		List<ScreenInfo> list = new MovieService().serachScreenInfo(title);
		
		//forward 위임하기
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/screen_managerment.jsp")
	           .forward(request, response);
	}

}

package com.simp.movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenMovie;

/**
 * Servlet implementation class MovieUpdateServlet
 */
@WebServlet("/movie/movieUpdate")
public class MovieUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService movieService = new MovieService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.사용자 입력값
		String movieTitle = request.getParameter("movie_title");

		// 2.업무로직
		ScreenMovie sm = new MovieService().movieInfo(movieTitle);

		System.out.println(sm);

		// 3.jsp포워딩
		request.setAttribute("screenMovie", sm);
		request.getRequestDispatcher("/WEB-INF/views/update_screenmovie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		System.out.println("[utf-8] encoding 처리함.");

		// 2. db에 게시글/첨부파일 정보 저장

		// 2-1. 사용자 입력값처리
		String mvTitle = request.getParameter("title");
		String mvTrail = request.getParameter("trailer");
		String mvSynop = request.getParameter("synopsis");
		String mvStatus = request.getParameter("screen_status");

		System.out.println("영화정보 수정 서블릿: " + mvTitle);
		System.out.println("영화정보 수정 서블릿: " + mvTrail);
		System.out.println("영화정보 수정 서블릿: " + mvSynop);
		System.out.println("영화정보 수정 서블릿: " + mvStatus);
		
		ScreenMovie sm = new ScreenMovie();
		sm.setTitle(mvTitle);
		sm.setTrailer(mvTrail);
		sm.setSynopsis(mvSynop);
		sm.setScrennStatus(mvStatus);

		// 3. 업무로직 :
		int result = new MovieService().updateScreenMovie(sm);
		
		
		
//		System.out.println("result@servlet = " + result);

		if (result > 0) {
			System.out.println("변경 성공");
		} else {
			System.out.println("변경 실패");
		}

		
		
		response.sendRedirect(request.getContextPath());
	}

	
		

}


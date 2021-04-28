package com.simp.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.simp.movie.model.vo.Movie;
import com.simp.movie.model.vo.MovieList;

import common.MovieSearch;
import common.MvcUtils;


/**
 * Servlet implementation class MovieSearchServlet
 */
@WebServlet("/movie/search")
public class MovieSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int numPerPage = 10;
		String searchWord = request.getParameter("search_word");
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			//null 이라면 NFF 예외가 발생함
			// 처리 코드 없음. 기본값 1 유지
		}
//		JsonParser jp = new JsonParser();
		
		int start = numPerPage * (cPage - 1) + 1;
		
		if(searchWord != null) {
			String movieInfo = MovieSearch.movieSearch(searchWord, start);
			System.out.println(start);
			System.out.println(movieInfo);
			Gson gson = new Gson();
			MovieList movieList = gson.fromJson(movieInfo, MovieList.class);
			
			String url = request.getRequestURI() + "?search_word=" + searchWord ;
			
			String pageBar = MvcUtils.getPageBar(
					cPage, numPerPage, Integer.parseInt(movieList.getTotal()), url
					);

			
		    List<Movie> list = movieList.getItems();
		    request.setAttribute("pageBar", pageBar);
			request.setAttribute("list", list);
		}
		
		
		request.getRequestDispatcher("/WEB-INF/views/search_movie.jsp")
		       .forward(request, response);
	}

}

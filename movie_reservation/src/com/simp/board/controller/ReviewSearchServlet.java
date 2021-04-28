package com.simp.board.controller;

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
import com.simp.movie.model.vo.ScreenMovie;

import common.MvcUtils;

/**
 * Servlet implementation class ReviewSearchServlet
 */
@WebServlet("/review/search")
public class ReviewSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 : 현재 페이지 cPage
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			// 처리 코드 없음. 기본값 1 유지.
		}

		// 업무로직
		// 페이징
		String type = request.getParameter("type");
		System.out.println("검색type@servlet = " + type);
		String userSearch = request.getParameter("review_search_text");
		System.out.println("검색userSearch@serlvet = "+ userSearch);
		int end = cPage * numPerPage;
		int start = end - (numPerPage - 1);
		List<Review> list = boardService.selectSearchList(start, end, type , userSearch); // 실제 board 객체를 조회해 오는 부분
		System.out.println("검색list@servlet = " + list);
		
		int totalContents = boardService.selectSearchCount(type ,userSearch);
		System.out.println("검색totalContents@servlet = " + totalContents);
		
		// pageBar
		String url = request.getRequestURI()+"?type="+type+"&review_search_text=" + userSearch; /// movie_reservation_my/review/search
		System.out.println("url@serlvet = "+url);
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		if(type.equals("movie_title")) {
			ScreenMovie sm = new MovieService().movieInfo(userSearch);
			request.setAttribute("screenMovie", sm);
		}

		// jsp 위임
		request.setAttribute("type", type);
		request.setAttribute("userSearch", userSearch);
		request.setAttribute("totalContents", totalContents);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/review/review_main.jsp").forward(request, response);

	}

}

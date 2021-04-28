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
import com.simp.board.model.vo.ReviewComment;
import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenMovie;

import common.MvcUtils;

/**
 * Servlet implementation class ReviewBoardServlet
 */
@WebServlet("/review/board")
public class ReviewBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = 0;
		try {			
			no = Integer.parseInt(request.getParameter("no"));
			System.out.println("reviewNo@servlet = "+no);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch (NumberFormatException e) {
			// 처리 코드 없음. 기본값 1 유지.
		}
		
		// 업무로직
		Review review = boardService.selectOne(no); // 실제 board 객체를 조회해 오는 부분
		
		String url = null;
		ScreenMovie sm = null;
		sm = new MovieService().movieInfo(review.getMovieTitle());
	
		
		
		int totalContents = 0;
		//페이징
		int end = cPage * numPerPage;
		int start = end - (numPerPage - 1);
		
		List<ReviewComment> list = boardService.ListComment(start, end, no);
		
		totalContents = boardService.selectCommentCount(no);
		System.out.println("totalContents@servlet = " + totalContents);
		url = request.getRequestURI() +"?no="+ no; //
		//System.out.println(request.getRequestURI()); // /movie_reservation/review/board
		String pageBar = MvcUtils.getPageBar(
				cPage,
				numPerPage,
				totalContents,
				url
			);
		
		// jsp 위임\
		request.setAttribute("screenMovie", sm);
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("review", review);
		request.getRequestDispatcher("/WEB-INF/views/review/review_board.jsp")
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

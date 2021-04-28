package com.simp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.board.model.service.BoardService;
import com.simp.board.model.vo.Review;
import com.simp.member.model.vo.Member;
import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenMovie;

/**
 * Servlet implementation class ReviewWriteServlet
 */
@WebServlet("/review/write")
public class ReviewWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieTitle = null;
		movieTitle = request.getParameter("movie_title");
		
		String url = null;
		ScreenMovie sm = null;
		if(movieTitle != null) {
			sm = new MovieService().movieInfo(movieTitle);
			url = request.getRequestURI() +"?movie_title="+ sm.getMovieTitle(); //
			request.setAttribute("screenMovie", sm);
		} else {
			url = request.getRequestURI();
		}
		
		// jsp 위임
		
		request.getRequestDispatcher("/WEB-INF/views/review/review_write.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String title = request.getParameter("review_title_update");
		String content = request.getParameter("content");
		String movieTitle = request.getParameter("movie_title");
		
		String url = null;
		ScreenMovie sm = null;
		if(movieTitle != null) {
			sm = new MovieService().movieInfo(movieTitle);
			url = request.getRequestURI() +"?movie_title="+ sm.getMovieTitle(); //
			request.setAttribute("screenMovie", sm);
		} else {
			url = request.getRequestURI();
		}
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String writer = loginMember.getMember_id();
		System.out.println("title@sevlet = "+title);
		System.out.println("content@servlet = " + content);
		System.out.println("writer@servlet = " + writer);
		
		
		Review review = new Review();
		review.setTitle(title);
		review.setContent(content);
		review.setWriter(writer);
		review.setMovieTitle(sm.getMovieTitle());
		
		int result = boardService.insertBoard(review);
		
		String msg = (result > 0) ? 
				"게시글 등록 성공!" :
					"게시글 등록 실패!";
		String location = request.getContextPath();
		location += (result > 0 ) ?
				"/review/board?no=" + review.getReviewNo() :
					"/review";
				
		System.out.println("msg@servlet = " + msg);
		System.out.println("location@servlet = " + location);
		// 3. DML요청 : 리다이렉트 & 사용자피드백
		// /mvc/board/boardList
		
		session.setAttribute("msg", msg);
		response.sendRedirect(location);
	}

}

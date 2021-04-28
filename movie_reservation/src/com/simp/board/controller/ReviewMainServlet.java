package com.simp.board.controller;

import java.io.IOException;
import java.util.List;

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
import com.simp.product.model.service.ProductService;
import com.simp.product.model.vo.PaymentHistory;

import common.MvcUtils;


/**
 * Paging Recipe
 * A. Contents Section : 쿼리
 * 		1. start rownum ~ end rownum
 * 		2. cPage 현재 페이지, numPerPage 페이지당 표시할 컨텐츠 수
 * 
 * B. Pagebar Section : html 작성
 *		1. totalContents 총컨텐츠 수
 * 		2. totalPage 전체 페이지 수
 * 		3. pageBarSize 페이지바에 표시할 페이지 개수
 * 		4. pageNo 페이지 넘버를 출력할 증감 변수
 * 		5. pageStart ~ pageEnd pageNo의 범위
 */
@WebServlet("/review")
public class ReviewMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 사용자 입력값 : 현재 페이지 cPage
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch (NumberFormatException e) {
			// 처리 코드 없음. 기본값 1 유지.
		}
		
		String movieTitle = null;
		movieTitle = request.getParameter("movie_title");
		System.out.println("전체movieTitle@servlet = "+ movieTitle);
		// 업무로직
		
		// pageBar
		String url = null;
		ScreenMovie sm = null;
		if(movieTitle != null) {
			sm = new MovieService().movieInfo(movieTitle);
			url = request.getRequestURI() +"?movie_title="+ sm.getMovieTitle(); //
			request.setAttribute("screenMovie", sm);
		} else {
			url = request.getRequestURI();
		}
		
		int totalContents = 0;
		//페이징
		int end = cPage * numPerPage;
		int start = end - (numPerPage - 1);
		List<Review> list = null;
		if(movieTitle != null) {
			list = boardService.selectList(start, end, sm.getMovieTitle()); // 실제 board 객체를 조회해 오는 부분			
			totalContents = boardService.selectReviewCount(sm.getMovieTitle());
		} else {
			list = boardService.selectList(start, end); // 실제 board 객체를 조회해 오는 부분						
			totalContents = boardService.selectReviewCount();
		}
		System.out.println("totalContents@servlet = " + totalContents);
		System.out.println("list@servlet = " + list);
		
		System.out.println(url);
		String pageBar = MvcUtils.getPageBar(
					cPage,
					numPerPage,
					totalContents,
					url
				);
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = "";
		if(loginMember != null) {
			memberId = loginMember.getMember_id();			
			List<PaymentHistory> payList = new ProductService().payment(memberId);
			request.setAttribute("paylist", payList);
		}
		
		//위임처리
		// jsp 위임
		request.setAttribute("totalContents", totalContents);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/review/review_main.jsp")
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

package com.simp.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.member.model.vo.Member;
import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenMovie;
import com.simp.product.model.service.ProductService;

/**
 * Servlet implementation class MemberMyMovieServlet
 */
@WebServlet("/member/mypageMyMovie")
public class MemberMyMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.로그인 정보 가져오기
		HttpSession session = request.getSession();
		
       String memberId = ((Member)session.getAttribute("loginMember")).getMember_id();
		
		System.out.println(memberId);
		
		List<ScreenMovie> dip_list = new MovieService().dipList(memberId);

		
		System.out.println(dip_list);
		
		int amount = new ProductService().amount(memberId);
		request.setAttribute("amount", amount);
		
		//위임
		request.setAttribute("dip_list", dip_list);
		request.getRequestDispatcher("/WEB-INF/views/my_page/myMovies.jsp")
		   .forward(request, response);
	}

}

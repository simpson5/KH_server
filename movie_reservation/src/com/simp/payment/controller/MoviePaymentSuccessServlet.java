package com.simp.payment.controller;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.member.model.service.MemberService;
import com.simp.member.model.vo.Member;
import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenInfo;

import sun.font.CreatedFontTracker;

/**
 * Servlet implementation class MoviePaymentSuccessServlet
 */
@WebServlet("/payment/success/movie")
public class MoviePaymentSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String payment_info = (String)session.getAttribute("payment_info");
		String[] info_arr = payment_info.split("&&");
		
		String member_id = info_arr[0];
		String screen_no = info_arr[1];
		String[] seat_no = info_arr[2].split(",");
		int amount = Integer.parseInt(info_arr[3]);
		int point = Integer.parseInt(info_arr[4]);
		
		ScreenInfo si = new MovieService().selectOneScreenInfo(screen_no);
		
		System.out.println(screen_no);
		System.out.println(seat_no[0]);
		System.out.println(member_id);
		System.out.println(amount);
		System.out.println(point);
		System.out.println(si);
		
		int result = 0;
//		for(String seat : seat_no) {
//			String s_no = si.getTheaterNo().replace("상영관", "T") + "-" + seat;
//			result = new MovieService().soldTicket(screen_no, s_no, member_id, amount, point);
//		}
		List<String> seat_arr = new ArrayList<String>();
		int n = 0;
		for(String seat : seat_no) {
			seat_arr.add(si.getTheaterNo().replace("상영관", "T") + "-" + seat);
		}
		
		result = new MovieService().soldTicket(screen_no, seat_arr, member_id, amount, point);
		
		Member member = new MemberService().selectOne(member_id);
		session.setAttribute("loginMember", member);
		
		request.getRequestDispatcher("/WEB-INF/views/store/payment_complete.jsp")
		       .forward(request, response);
	}
}

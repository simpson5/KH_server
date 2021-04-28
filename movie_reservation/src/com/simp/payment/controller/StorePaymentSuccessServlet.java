package com.simp.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.member.model.service.MemberService;
import com.simp.member.model.vo.Member;
import com.simp.movie.model.service.MovieService;

/**
 * Servlet implementation class PaymentSuccessServlet
 */
@WebServlet("/payment/success/product")
public class StorePaymentSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String payment_info = (String)session.getAttribute("payment_info");
		String[] info_arr = payment_info.split("&&");
		
		String member_id = info_arr[0];
		String pd_name_arr = info_arr[1];
		String cnt_arr = info_arr[2];
		
		String[] pd_name = pd_name_arr.split(",");
		String[] cnt = cnt_arr.split(",");
		
		System.out.println("PaymentSuccessServlet : " + member_id);
		System.out.println("PaymentSuccessServlet : " + pd_name_arr);
		System.out.println("PaymentSuccessServlet : " + cnt_arr);
		
		int result = 0;
		System.out.println(pd_name.length);
		for(int i = 0; i < pd_name.length ; i++) {
			System.out.println(pd_name[i]);
			System.out.println(cnt[i]);
			result = new MovieService().storePayment(member_id, pd_name[i], cnt[i]);
			System.out.println(result);
		}
		//장바구니 초기화 및 포인트 증가
		//왜 리무브가 작동을 안하지?
		//session.setAttribute("payment_info", null);
		Member member = new MemberService().selectOne(member_id);
		session.setAttribute("loginMember", member);
		
		request.getRequestDispatcher("/WEB-INF/views/store/payment_complete.jsp")
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

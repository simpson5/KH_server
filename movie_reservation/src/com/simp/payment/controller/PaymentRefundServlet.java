package com.simp.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.movie.model.service.MovieService;

/**
 * Servlet implementation class PaymentRefundServlet
 */
@WebServlet("/payment/refund")
public class PaymentRefundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pay_no = request.getParameter("input_pay_no");
		
		int result = new MovieService().refund(pay_no);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/member/mypage");
		}
	}

}

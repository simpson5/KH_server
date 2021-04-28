package com.simp.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.simp.product.model.servlcie.ProductService;
import com.simp.product.model.vo.PaymentHistory;

import common.MvcUtils;


/**
 * Servlet implementation class CalculateManagerServlet
 * @param <PaymentHistory>
 */
@WebServlet("/calculate")
public class CalculateManagerServlet<PaymentHistory> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService  productService = new ProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/calculate_board.jsp")
        	   .forward(request, response);
	}


}

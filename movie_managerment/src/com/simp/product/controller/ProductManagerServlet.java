package com.simp.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.product.model.servlcie.ProductService;
import com.simp.product.model.vo.Product;

/**
 * Servlet implementation class ProductManagerServlet
 */
@WebServlet("/product")
public class ProductManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB 조회하기
		List<Product> list = new ProductService().selectProduct();
		System.out.println(list);
		
		
		//forward 위임하기
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/product_managerment.jsp")
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

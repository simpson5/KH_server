package com.simp.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.product.model.service.ProductService;
import com.simp.product.model.vo.Shopping;

/**
 * Servlet implementation class ShoppingSuccessServlet
 */
@WebServlet("/shopping/shoppingsuccess")
public class ShoppingSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productservice = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Shopping> list = productservice.shoppingList();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/movie_reservation/shopping_success.jsp")
		       .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

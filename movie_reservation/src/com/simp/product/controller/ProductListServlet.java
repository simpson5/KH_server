package com.simp.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.product.model.service.ProductService;
import com.simp.product.model.vo.Product;




/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/product/productList")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductService();
	/**
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//업무로직
		List<Product> list = productService.productList();
		
		//System.out.println("list@servlet " +list);
		
		//위임
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/store/store.jsp")
		   .forward(request, response);
		
	}
}

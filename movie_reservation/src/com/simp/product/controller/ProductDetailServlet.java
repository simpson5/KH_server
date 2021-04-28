package com.simp.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.product.model.service.ProductService;
import com.simp.product.model.vo.Product;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/product/productDetail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductService();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //1. request 값 가져오기
		String pd_name = request.getParameter("pd_name");
		
		//2. DB 조회하기
		Product pd = productService.selectProduct(pd_name);
		
		
//		List<Product> list = productService.productList();
//		System.out.println("servlet");
//		request.getRequestDispatcher("/WEB-INF/views/store/store_detail.jsp")
//		   .forward(request, response);
		
		System.out.println(pd);

		request.setAttribute("product", pd);
		request.getRequestDispatcher("/WEB-INF/views/store/store_detail.jsp")
			.forward(request, response);
	}

}

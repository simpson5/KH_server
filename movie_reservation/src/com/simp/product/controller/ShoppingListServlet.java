package com.simp.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.product.model.service.ProductService;
import com.simp.product.model.vo.Product;

/**
 *
 */
@WebServlet("/product/shoppinglist")
public class ShoppingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pd_title = request.getParameter("pd_title");
		int no = Integer.parseInt(request.getParameter("no"));
		HttpSession session = request.getSession(true);
		//List<String> cart = new ArrayList<>();
		
		HashMap<String, Integer> cart = new HashMap<String, Integer>();
		
		//session.setAttribute("msg_cart", pd_title + "가 장바구니에" + no +  "개 담겼습니다.");
		
		if(session.getAttribute("cart") == null) {
			cart.put(pd_title, no);
			session.setAttribute("cart", cart);
		} else {
			cart = (HashMap<String, Integer>)session.getAttribute("cart");
			if(cart.containsKey(pd_title)) {
				//key에 맞는 value 값 가져오기
				int n = cart.get(pd_title) + no;
				cart.put(pd_title, n);
				session.setAttribute("cart", cart);									
			} else {
				cart.put(pd_title, no);
				session.setAttribute("cart", cart);
			}
		}
		
		System.out.println(session.getAttribute("cart"));		
		PrintWriter out = response.getWriter();
		out.println("장바구니에 물건을 담았습니다!");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Iterator<String> iter = null;
		
		String referer =  request.getHeader("Referer");
		
		if(session.getAttribute("cart") != null) {
			HashMap<String, Integer> cart = ((HashMap<String, Integer>)session.getAttribute("cart"));
			iter = cart.keySet().iterator();			
		}
		
		List<Product> pd_list = new ArrayList<Product>();
		
		while(iter != null && iter.hasNext()) {
		    String pd_title = iter.next();
			Product pd = productService.selectProduct(pd_title);
			pd_list.add(pd);
		}
		if(pd_list.isEmpty()) {
			session.setAttribute("msg_cart", "장바구니가 비었습니다!");
			response.sendRedirect(referer);			
		} else {
			request.setAttribute("pd_list", pd_list);
			request.getRequestDispatcher("/WEB-INF/views/store/shoppinglist.jsp")
			   	   .forward(request, response);
		}
	}
}

package com.simp.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.product.model.servlcie.ProductService;



/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/Product/productDelete")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ProductService productService = new ProductService();
	    
	    /**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				//1.사용자 입력값
				String delete_pd_name = request.getParameter("delete_pd_name");
				
				System.out.println("상품 삭제 서블릿 상품명 이름 : " + delete_pd_name);
				
				//2.업무로직
				int result = productService.deleteProduct(delete_pd_name);
				String msg = result > 0 ?
								"상품 삭제 성공!" :
									"상품 삭제 실패!";
				
				//3.리다이렉트 & 사용자피드백
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath() + "/product");
			} catch (Exception e) {
				//예외 로깅
				e.printStackTrace();
				//예외페이지 전환을 위해서 was에 예외던짐.
				throw e;
			}
		}

	}

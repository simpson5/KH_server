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
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/Product/productUpdate")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.한글 깨짐 방지 인코딩처리
		//void javax.servlet.ServletRequest.setCharacterEncoding(String arg0) throws UnsupportedEncodingException
		request.setCharacterEncoding("UTF-8");//대소문자 상관없음. 요청한 view단의 charset값과 동일해야 한다.
		
		int pdNo = Integer.parseInt(request.getParameter("pd_no"));
		int pdPrice =Integer.parseInt(request.getParameter("price"));
		int pdstock =Integer.parseInt(request.getParameter("stock"));
		String pdInfo = request.getParameter("pdinfo");
		
	
		Product pd = new Product();
		pd.setProductNo(pdNo);
		pd.setPdInfo(pdInfo);
		pd.setProductPrice(pdPrice);
		pd.setStock(pdstock);
		
		System.out.println(pd);
		
		//3. DB문 처리
		int result = new ProductService().updateProduct(pd);
//		System.out.println("result@servlet = "+result);
		if(result > 0) {
			System.out.println("변경 성공");
		} else {			
			System.out.println("변경 실패");
		}
		
		//다시 프로젝트 리스트 불러오기!
		List<Product> list = new ProductService().selectProduct();
		
		//4. forword 위임하기
		//셋 리퀘스트 하기
		response.sendRedirect(request.getContextPath() + "/product");
	}


}
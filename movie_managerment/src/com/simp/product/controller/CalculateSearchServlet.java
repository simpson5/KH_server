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
 * Servlet implementation class CalculateSearchServlet
 */
@WebServlet("/calculate/search")
public class CalculateSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductService  productService = new ProductService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		System.out.println(searchType + " : " + searchKeyword);
		
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage =	Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			// 처리 코드 없음. 기본값 1 유지.
		}
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		param.put("start", String.valueOf((cPage -1) * numPerPage + 1));
		param.put("end", String.valueOf(cPage * numPerPage));
		System.out.println("param@servlet = " + param);
		
		//2. 업무 로직
		List<PaymentHistory> list = (List<PaymentHistory>) productService.searchProduct(param);
		System.out.println("list@servlet = " + list);
		
		int totalContents = productService.searchProductCount(param);
		int totalPrice = productService.totalPrice(searchType, searchKeyword);
		System.out.println("totalContents@servlet = " + totalContents);
		System.out.println("totalPrice = " + totalPrice);
		
		//3. pageBar영역 작업
		String url = request.getRequestURI() + "?searchType=" + searchType + "&searchKeyword=" + searchKeyword ; 
		// /mvc/admin/memberFinder?searchType=gender&serarchKeyword=M
		String pageBar = MvcUtils.getPageBar(
					cPage,
					numPerPage,
					totalContents,
					url
				);
		
		//4. jsp에 html응답메세지 작성 위임
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/calculate_board.jsp")
        	   .forward(request, response);
	}

}

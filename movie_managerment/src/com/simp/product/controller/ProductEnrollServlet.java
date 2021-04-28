package com.simp.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.simp.product.model.servlcie.ProductService;
import com.simp.product.model.vo.Product;

/**
 * Servlet implementation class ProductEnrollServlet
 */
@WebServlet("/product/productEnroll")
public class ProductEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1. 파일 등록 처리
		//영화 자료 업로드
		String saveDirectory = getServletContext().getRealPath("/upload/product");
		System.out.println("saveDirectory@servlet = " + saveDirectory);
		
		// 최대 파일 허용 크기 10mb = 10 * 1kb * 1kb
		int maxPostSize = 10 * 1024 * 1024; // byte 단위로 전달
		
		//인코딩
		String encoding = "utf-8";
		
		//파일명 변경정책 객체
		//중복파일인 경우, numbering처리
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		//FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		//생성자가 만들어지면 파일 저장이 끝난다!
		MultipartRequest multipartRequest =
				new MultipartRequest(
								request,
								saveDirectory,
								maxPostSize,
								encoding,
								policy
									);
		
		//2. 값 가져오기
		String pdName = multipartRequest.getParameter("pd_name");
		String pdCategory = multipartRequest.getParameter("pd_category");
		String pdImg = multipartRequest.getFilesystemName("pd_img");
		String pdInfo = multipartRequest.getFilesystemName("pd_info");
		int pdPrice = Integer.parseInt(multipartRequest.getParameter("pd_price"));
		
		Product pd = new Product();
		pd.setProductName(pdName);
		pd.setPdCategory(pdCategory);
		pd.setPdImg(pdImg);
		pd.setPdInfo(pdInfo);
		pd.setProductPrice(pdPrice);
				
		//3. DB문 처리
		int result = new ProductService().enrollProduct(pd);
		
		//4. forword 위임하기
		request.getRequestDispatcher("/index.jsp")
	       	   .forward(request, response);
	}
}

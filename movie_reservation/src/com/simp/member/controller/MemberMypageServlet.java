package com.simp.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.member.model.service.MemberService;
import com.simp.member.model.vo.Member;
import com.simp.product.model.service.ProductService;
import com.simp.product.model.vo.PaymentHistory;

/**
 * Servlet implementation class MemberMypageServlet
 */
@WebServlet("/member/mypage")
public class MemberMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String memberId = loginMember.getMember_id();
		
		
		List<PaymentHistory> list = new ProductService().payment(memberId);
		
		int amount = new ProductService().amount(memberId);
		request.setAttribute("amount", amount);
		
		System.out.println("mypage@servlet = " + list);
		
		
		//위임처리
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/my_page/mypage.jsp")
		   .forward(request, response);
	}




}

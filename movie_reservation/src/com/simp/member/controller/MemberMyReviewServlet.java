package com.simp.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.board.model.service.BoardService;
import com.simp.board.model.vo.Review;
import com.simp.member.model.vo.Member;
import com.simp.product.model.service.ProductService;
import com.simp.product.model.vo.PaymentHistory;

/**
 * Servlet implementation class MemberMyReviewServlet
 */
@WebServlet("/member/my_review")
public class MemberMyReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String memberId = loginMember.getMember_id();
		
		
		List<Review> list = new BoardService().selectListId(memberId);
		
		int amount = new ProductService().amount(memberId);
		request.setAttribute("amount", amount);
		
		System.out.println("mypage@servlet = " + list);
		
		
		//위임처리
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/my_page/my_qna.jsp")
		       .forward(request, response);
	}

}

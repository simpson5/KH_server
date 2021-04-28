package com.simp.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.member.model.service.MemberService;
import com.simp.member.model.vo.Member;
import com.simp.movie.model.service.MovieService;


/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//로그인 페이지 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
			   .forward(request, response);
	}

	//로그인
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("member_id");
		String password = request.getParameter("password");
		String saveId = request.getParameter("saveId");
		
		Member member = new MemberService().selectOne(memberId);
		
		HttpSession session = request.getSession(true);
		if (member != null && password.equals(member.getPassword())) {
			//member.setPassword(null);
			session.setAttribute("loginMember", member);
			Cookie c = new Cookie("saveId", memberId);
			c.setPath(request.getContextPath()); // path 쿠키를 전송할 url
			if (saveId != null) {
				// saveId 체크시
				c.setMaxAge(60 * 60 * 24 * 7); // 7일짜리 영속쿠키
			} else {
				// saveId 체크 해제시
				c.setMaxAge(0); // 0으로 지정해서 즉시 삭제, 음수로 지정하면 session종료시 제거
			}
			response.addCookie(c);
		} else {
			session.setAttribute("msg", "로그인에 실패했습니다.");
		}

		Member loginMember = (Member)session.getAttribute("loginMember");
		
		response.sendRedirect(request.getContextPath());
	}

}

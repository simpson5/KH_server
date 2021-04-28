package com.simp.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simp.member.model.service.MemberService;

/**
 * Servlet implementation class MemberStatusUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberStatusUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		System.out.println("[utf-8] encoding 처리함.");

		// 2. db에 게시글/첨부파일 정보 저장

		// 2-1. 사용자 입력값처리
		String memberId = request.getParameter("memberId");
		String memberStatus = request.getParameter("member_status");

		System.out.println("회원정보 수정 서블릿: " + memberId);
		System.out.println("회원정보 수정 서블릿: " + memberStatus);

		int result = new MemberService().updateMemberStatus(memberId, memberStatus);

		if (result > 0) {
			System.out.println("변경 성공");
		} else {
			System.out.println("변경 실패");
		}

		response.sendRedirect(request.getContextPath()+"/member/memberlist");
	}

}

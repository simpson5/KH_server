package com.simpson.web.servlet;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simpson.controller.MemberController;
import com.simpson.model.vo.Member;

public class SignUp extends HttpServlet {
	private MemberController memberController = new MemberController();
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServerException{
		//1. 사용자입력값 가져오기
		String memberId = request.getParameter("user_id");
		String password = request.getParameter("user_pw");
		String name = request.getParameter("user_name");
		String ssn = request.getParameter("user_ssn");
		String phone = request.getParameter("user_phone");
		String adress = request.getParameter("user_adress");
		String email = request.getParameter("user_email");
		
		Member member = new Member(memberId, password, name, ssn, phone, adress, email);

		memberController.insertMember(member);
	}

}

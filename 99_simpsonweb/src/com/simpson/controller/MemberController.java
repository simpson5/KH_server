package com.simpson.controller;

import com.simpson.model.dao.MemberDao;
import com.simpson.model.exception.MemberException;
import com.simpson.model.service.MemberService;
import com.simpson.model.vo.Member;

public class MemberController {
	private MemberService memberService = new MemberService();
	
	//회원가입
	public int insertMember(Member member) {
		int result = 0;
		try {
			result = memberService.insertMember(member);
		} catch(MemberException e) {
			//에러를 보여준다.
		}
		return result;
	}
}

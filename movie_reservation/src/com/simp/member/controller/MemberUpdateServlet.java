package com.simp.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.member.model.service.MemberService;
import com.simp.member.model.vo.Member;



/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
		MemberService  memberService = new MemberService();
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.한글 깨짐 방지 인코딩처리
				//void javax.servlet.ServletRequest.setCharacterEncoding(String arg0) throws UnsupportedEncodingException
				request.setCharacterEncoding("UTF-8");//대소문자 상관없음. 요청한 view단의 charset값과 동일해야 한다.
				
				//2.사용자 입력값 처리
				//String javax.servlet.ServletRequest.getParameter(String arg0)
				String memberName = request.getParameter("memberName");
				String grade = request.getParameter("grade");
				String memberId = request.getParameter("memberId");
				String password = request.getParameter("password");
				String memberSSN = request.getParameter("member_ssn");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				
				//체크박스같은 경우 선택된 복수의 값이 배열로 전달된다.
				//String[] javax.servlet.ServletRequest.getParameterValues(String arg0)
				String[] genres = request.getParameterValues("genre");
				
				String genre = "";
				//String java.lang.String.join(CharSequence delimiter, CharSequence... elements)
			
				if(genres != null) 
					genre = String.join(",", genres);

				
				
				Member member = new Member();
				member.setMember_name(memberName);
				member.setMember_ssn(memberSSN);
				member.setMember_grade(grade);
				member.setMember_id(memberId);
				member.setPassword(password);
				member.setEmail(email);
				member.setPhone(phone);
				member.setAddress(address);
				member.setPreference_genre(genre);
				
				System.out.println( genre);
				System.out.println("memberUpdate@servlet = " + member);
				
				//3.업무로직
				int result = memberService.updateMember(member);  

				//4. 사용자피드백 및 리다이렉트 처리  DML성격을 가진 애들은 꼭  리다이렉트 처리하기! 
				HttpSession session = request.getSession();
				String msg = "";
				
				System.out.println(result);
				if(result>0){
					msg = "성공적으로 회원정보를 수정했습니다.";
					//세션의 정보도 갱신하도록 한다.
					session.setAttribute("loginMember", memberService.selectOne(memberId));
				}
				else 
					msg = "회원정보수정에 실패했습니다.";	
				
				session.setAttribute("msg", msg);
				response.sendRedirect(request.getContextPath() + "/member/mypage");
	}

}

package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Paging Recipe
 * A. Contents Section : 쿼리
 * 		1. start rownum ~ end rownum
 * 		2. cPage 현재페이지, numPerPage 페이지당 표시할 컨텐츠수
 * 
 * B. Pagebar Section : html 작성
 * 		1. totalContents 총 컨텐츠수 
 * 		2. totalPage 전체페이지수
 * 		3. pageBarSize 페이지바에 표시할 페이지 개수
 * 		4. pageNo 페이지넘버를 출력할 증감변수
 * 		5. pageStart ~ pageEnd pageNo의 범위
 * 
 * 
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 : 현재페이지 cPage
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			//null 이라면 NFF 예외가 발생함
			// 처리 코드 없음. 기본값 1 유지
		}
		
		//2. 업무로직 : 전체회원조회 + 회원가입일 내립차순으로 조회
		//온전히 member관련 이라면 memberService 이용해도 괜찮
		//admin에서 독자적으로 사용하는 기능이라면 adminService 이용
		//int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		int start = end - (numPerPage - 1);
		List<Member> list = memberService.selectList(start, end);
		
		int totalContents = memberService.selectMemberCount();
		String url = request.getRequestURI(); // /mvc/admin/memberList
		//"http://localhost:9090/mvc/admin/memberList?cPage=" + cPage;
		
		System.out.println("totalContents@servlet" + totalContents);
		
		//3. pagebar 영역 작업
		String pageBar = MvcUtils.getPageBar(
				cPage, numPerPage, totalContents, url
				);
		
		//4. jsp에 html 응답메세지 작성 위임
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
			   .forward(request, response);
	}
}

package admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService= new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
		}
		
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		//사용자 값 저장?
		HttpSession session = request.getSession();
		if (param.get("searchType") != null) {
			session.setAttribute("param", param);			
			System.out.println("param@servlet"+param.get("searchType"));
		}
		else {			
			param = (Map<String, String>)session.getAttribute("param");
			System.out.println("param@servlet"+param.get("searchType"));
		}
		//2. 업무로직
		int end = cPage * numPerPage;
		int start = end - (numPerPage - 1);
		
		//List<Member> list = memberService.searchMember(param);
		List<Member> list = memberService.searchMember(param, start, end);
		
		int totalContents = memberService.selectMemberCount(param);
		String url = request.getRequestURI() + "?searchType=" + searchType + "&searchKeyword=" + searchKeyword;
		
		String pageBar = MvcUtils.getPageBar(
				cPage, numPerPage, totalContents, url
				);
		
		//3. jsp에 html응답메세지 작성 위임
		//아직 요청이 끝나지 않았다.
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
		       .forward(request, response);
	}

}

package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginCheckServlet
 */
@WebServlet("/member/memberLoginCheck")
public class MemberLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String loginPwd = request.getParameter("loginPwd");
		
		Member m = new MemberService().selectOne(loginId);
		
		HttpSession session = null;
		
		String msg = "";
		String loc = "/";
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(m == null) {
			msg = "존재하지 않는 아이디입니다.";
			loc = "/member/memberLogin";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			reqDispatcher.forward(request, response);
		}
		//아이디와 비번이 모두 일치한 경우 => 로그인 성공
		else {
			//사용자입력비번과 db회원정보비번 비교
			//비번이 틀린 경우 => 로그인 실패
			if(!m.getPassword().equals(loginPwd)) {
				msg = "비밀번호가 틀렸습니다.";
				loc = "/member/memberLogin";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				reqDispatcher.forward(request, response);
			}
			else {
				//세션이 있으면, 해당세션을 리턴하고, 없으면 새로 생성해서 리턴
				session = request.getSession(true);
//				System.out.println("sessionId = "+session.getId());
				
				//세션에 로그인한 회원객체 속성으로 저장
				session.setAttribute("memberLoggedIn", m);
				
				//session유효시간 설정: 초단위/web.xml에서 설정한것보다 우선순위가 높다.
				session.setMaxInactiveInterval(60*30);
				
				//아이디저장 쿠키관련
				//체크된경우:"on", 체크되지 않은 경우:null
				String saveId = request.getParameter("saveId");
				
				//체크한경우
				if(saveId != null) {
					Cookie c = new Cookie("saveId", loginId);
					c.setMaxAge(7*24*60*60); //7일 후 소멸
					c.setPath("/"); //쿠키사용디렉토리. 도메인 전역에서 사용함.
					response.addCookie(c);
				}
				//체크하지 않은 경우
				else {
					Cookie c = new Cookie("saveId", loginId);
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);
				}
				
				
				//로그인 후 페이지 리다이렉트
				session = ((HttpServletRequest)request).getSession();
				String prevPage = (String)session.getAttribute("prevPage");
				if("http://localhost:9090/pixar/member/memberLoginCheck".equals(prevPage) || "http://localhost:9090/pixar/member/memberLogin".equals(prevPage) || "http://localhost:9090/pixar/member/memberEnroll".equals(prevPage) || "http://localhost:9090/pixar/member/memberEnrollEnd".equals(prevPage)) {
//					System.out.println(prevPage);
					response.sendRedirect("http://localhost:9090/pixar");
				}
				else if(prevPage.contains("http://localhost:9090/pixar/movie/movieView/movieDescription.do?")) {

					response.sendRedirect(prevPage+loginId);
					
				}
				else {					
					//포워딩이 아닌 리다이렉트처리
					//클라이언트에게 해당주소로 재요청하게 함.
					response.sendRedirect(prevPage);
				}
				
				//http://localhost:9090/pixar/member/memberLoginCheck
			}
				
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

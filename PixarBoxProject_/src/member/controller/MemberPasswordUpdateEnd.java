package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberPasswordUpdateEnd
 */
@WebServlet("/member/updatePasswordEnd")
public class MemberPasswordUpdateEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPasswordUpdateEnd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String password_new = request.getParameter("password_new");
		
		Member m = new MemberService().selectOne(memberId);
		
		String msg = "";
		String loc = "";
		if(m!=null && m.getPassword().equals(password)) {
			Member member = new Member();
			member.setMemberId(memberId);
			member.setPassword(password_new);
			
			int result = new MemberService().updatePassword(member);
			
			if(result>0) {
				msg = "비밀번호를 성공적으로 변경하였습니다.";
				String script = "self.close()";
				
				request.setAttribute("script", script);
			}
		}
		else {
			msg = "비밀번호를 잘못 입력하셨습니다.";
			loc = "/member/updatePassword?memberId="+memberId;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

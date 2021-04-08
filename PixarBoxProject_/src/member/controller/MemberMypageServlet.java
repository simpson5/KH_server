package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import movie.model.service.MovieService;
import movie.model.vo.LikeList;

/**
 * Servlet implementation class MemberMypageServlet
 */
@WebServlet("/member/my/*")
public class MemberMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberMypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService memberService = new MemberService();
		
		String url = request.getRequestURL().toString();		
		String memberId = request.getParameter("memberId");
		
		Member m = memberService.selectOne(memberId);
		
		String view = "";
		
		if(url.indexOf("mypage.do")!=-1) { //마이페이지
			
			if(m != null) {
				view = "/WEB-INF/views/member/memberMypage.jsp";
				request.setAttribute("member", m);
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp";
				
				request.setAttribute("msg", "조회된 회원이 없습니다.");
				request.setAttribute("loc", "/");
			}
			
			request.getRequestDispatcher(view).forward(request, response);
		}
		else if(url.indexOf("update.do")!=-1) { //정보수정페이지
			if(m != null) {
				view = "/WEB-INF/views/member/memberUpdate.jsp";
				request.setAttribute("member", m);
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp";
				
				request.setAttribute("msg", "조회된 회원이 없습니다.");
				request.setAttribute("loc", "/");
			}
			
			request.getRequestDispatcher(view).forward(request, response);
			
		}
		else if(url.indexOf("like.do")!=-1) { //찜목록 페이지
			
			if(m != null) {
				view = "/WEB-INF/views/member/memberLikeView.jsp";
				List<LikeList> likeList = new MovieService().selectAllLikeList(memberId);
				// 전체게시물수, 한페이지당 표시할 게시물수 => 총페이지수
				int totalContent = new MovieService().selectMovieLikeCount(memberId);
				final int numPerPage = 8;

				// 총페이지수: 올림처리
				int totalPage = (int) (Math.ceil((double) totalContent / numPerPage));
				
				request.setAttribute("member", m);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("likeList", likeList);
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp";
				
				request.setAttribute("msg", "조회된 회원이 없습니다.");
				request.setAttribute("loc", "/");
			}
			
			request.getRequestDispatcher(view).forward(request, response);
			
		}
		else if(url.indexOf("purchase.do")!=-1) { //예매내역 페이지
			
			if(m != null) {
				view = "/WEB-INF/views/member/memberTicketingList.jsp";
				request.setAttribute("member", m);
				
				
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp";
				
				request.setAttribute("msg", "조회된 회원이 없습니다.");
				request.setAttribute("loc", "/");
			}
			
			request.getRequestDispatcher(view).forward(request, response);
			
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

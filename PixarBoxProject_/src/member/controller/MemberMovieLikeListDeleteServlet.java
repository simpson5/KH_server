package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.service.MovieService;
import movie.model.vo.LikeList;

/**
 * Servlet implementation class MemberMovieLikeListDeleteServlet
 */
@WebServlet("/member/deleteMovieLike")
public class MemberMovieLikeListDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberMovieLikeListDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String movieCode = request.getParameter("movieCode");
		String memberId = request.getParameter("memberId");
		
		LikeList li = new LikeList(0,memberId,movieCode);
		int result = new MovieService().deleteMovieLike(li);
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		//javascript/html에서 사용할 url은 contextPath를 포함한다.
		String loc = "/member/my/like.do?memberId="+memberId;

		if(result>0) {
			msg = "찜목록에서 영화 삭제 성공!";
		}
		else {
			msg = "찜목록에서 영화 삭제 실패";
			
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

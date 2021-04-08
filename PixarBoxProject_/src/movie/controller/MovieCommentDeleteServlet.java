package movie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.service.MovieService;

/**
 * Servlet implementation class MovieCommentDeleteServlet
 */
@WebServlet("/movie/movieCommentDelete")
public class MovieCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String movieCode = request.getParameter("movieCode");
		
//		System.out.println("commentNo@servlet="+commentNo);
		
		int result = new MovieService().deleteMovieComment(commentNo);
		
		String msg = "";
		if(result>0) msg = "댓글이 삭제되었습니다.";
		else msg = "댓글이 삭제되지 않았습니다.";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "/movie/movieView/movieReview.do?movieCode="+movieCode);
		
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

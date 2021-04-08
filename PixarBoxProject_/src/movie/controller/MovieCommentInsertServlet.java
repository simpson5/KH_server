package movie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.service.MovieService;
import movie.model.vo.MovieComment;

/**
 * Servlet implementation class MovieCommentInsertServlet
 */
@WebServlet("/movie/movieCommentInsert")
public class MovieCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieRef = request.getParameter("movieRef");
		String commentWriter = request.getParameter("commentWriter");
		String commentContent = request.getParameter("commentContent");
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
		//댓글인 경우 commentRef = 0, db에는 null로 저장한다.
//		System.out.println(commentRef);
		MovieComment mc = new MovieComment(0, commentLevel, commentWriter, commentContent, movieRef, commentRef, null);
//		System.out.println("MovieComment@servlet="+mc);
		
		int result = new MovieService().insertMovieComment(mc);
		
		String msg = "";
		if(result>0) msg = "댓글이 등록되었습니다.";
		else msg = "댓글이 등록되지 않았습니다.";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "/movie/movieView/movieReview.do?movieCode="+movieRef);
		
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

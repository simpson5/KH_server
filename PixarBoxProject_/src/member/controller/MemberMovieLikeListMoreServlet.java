package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import movie.model.dao.MovieDAO;
import movie.model.service.MovieService;
import movie.model.vo.Movie;

/**
 * Servlet implementation class MemberMovieLikeListMoreServlet
 */
@WebServlet("/member/movieLikeMore")
public class MemberMovieLikeListMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberMovieLikeListMoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cPage = Integer.parseInt(request.getParameter("cPage"));
		final int numPerPage = 8;
		
		String memberId = request.getParameter("memberId");
		
		List<Movie> likeList = new MovieService().selectAllMovieLikeListBymemberId(cPage,numPerPage,memberId);
		
		//3.view단처리: json
		JSONArray jsonArray = new JSONArray();
		for(Movie m : likeList) {
			JSONObject jsonMovie = new JSONObject();
			jsonMovie.put("movieTitle", m.getMovieTitle());
			jsonMovie.put("runningTime", m.getRunningTime()+"");
			jsonMovie.put("movieImg",m.getRenamedFileName());
			jsonMovie.put("releaseDate", m.getReleaseDate()+"");
			jsonMovie.put("movieCode", m.getMovieCode());
			
		
			jsonArray.add(jsonMovie);
			
		}
//		System.out.println(jsonArray);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

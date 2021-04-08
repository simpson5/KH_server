package movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import movie.model.service.MovieService;
import movie.model.vo.LikeList;
import movie.model.vo.Movie;

/**
 * Servlet implementation class MovieMoreServlet
 */
@WebServlet("/movie/movieMore")
public class MovieMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieMoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.parameterHandling
		int order = Integer.parseInt(request.getParameter("orderVal"));
		int cPage = Integer.parseInt(request.getParameter("cPage"));
		final int numPerPage = 8;
		
		String memberId = request.getParameter("memberId");
		
		//2.businessLogic
		List<Movie> list = null;
		
		if(order == 1) {
			list = new MovieService().selectMovieMore(cPage, numPerPage);			
		}
		else {
			list = new MovieService().selectMovieMoreByYear(cPage, numPerPage);
		}
		 
		
		List<LikeList> likeList = null;
		// 사용자 좋아요 리스트 보내기
		likeList = new MovieService().selectAllLikeList(memberId);
		
		int check = 0;
		
		//3.view단처리: json
		JSONArray jsonArray = new JSONArray();
		for(Movie m : list) {
			JSONObject jsonMovie = new JSONObject();
			jsonMovie.put("movieTitle", m.getMovieTitle());
			jsonMovie.put("runningTime", m.getRunningTime()+"");
			jsonMovie.put("movieImg",m.getRenamedFileName());
			jsonMovie.put("releaseDate", m.getReleaseDate()+"");
			jsonMovie.put("movieCode", m.getMovieCode());

			for(int i=0;i<likeList.size();i++) {
				
				if(m.getMovieCode().equals(likeList.get(i).getMovieCode())) {
					jsonMovie.put("like", 1+"");
					check = 1;
				}
				
			}
			
			if(check==0) {
				jsonMovie.put("like", 0+"");
			}
			
			jsonArray.add(jsonMovie);
			check=0;
		}
//		System.out.println(jsonArray);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package movie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import movie.model.service.MovieService;
import movie.model.vo.LikeList;
import movie.model.vo.Movie;



/**
 * Servlet implementation class MovieLikeInsertServlet
 */
@WebServlet("/movie/movieLike")
public class MovieLikeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieLikeInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String movieCode = request.getParameter("movieCode");
		String memberId = request.getParameter("memberId");
		
		//System.out.println("MovieLikeInsertServlet@movieCode=" + movieCode);
		//System.out.println("MovieLikeInsertServlet@movieCode=" + memberId);
		

		LikeList li = new LikeList(0,memberId,movieCode);
		//0이면 insert 0이상이면 delete
		String clickResult = "0";
		//좋아요 눌러져있는지 먼저 확인을 한다.
		int likeClicked = new MovieService().selectLikeCnt(li);
		int result=0;
		//System.out.println(likeClicked+";;");
		if(likeClicked==0) {
			
			result = new MovieService().insertMovieLike(li);
			clickResult="1";
			
		}
		else if(likeClicked==1){
			
			result = new MovieService().deleteMovieLike(li);
			clickResult="0";
			
		}
		JSONArray jsonArray = new JSONArray();
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("clickResult", clickResult);
		jsonResult.put("result", result);
		jsonArray.add(jsonResult);
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

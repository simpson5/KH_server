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
import movie.model.vo.MovieNow;

/**
 * Servlet implementation class MovieNowServlet
 */
@WebServlet("/movie/movieNow/*")
public class MovieNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("movieNow.do")!=-1) {
			request.getRequestDispatcher("/WEB-INF/views/movie/movieNow.jsp").forward(request, response);
		}
		else if(url.indexOf("showMovieNow.do")!=-1) {
			String memberId = request.getParameter("memberId");
			
			List<MovieNow> list = new MovieService().selectMovieNow();
			List<LikeList> likeList = new MovieService().selectAllLikeList(memberId);
			
			int check = 0;
			
			JSONArray jsonArray = new JSONArray();
			for(MovieNow m : list) {
				JSONObject jsonMovieNow = new JSONObject();
				jsonMovieNow.put("movieTitle", m.getMovieTitle());
				jsonMovieNow.put("runningTime", m.getRunningTime()+"");
				jsonMovieNow.put("movieImg",m.getRenamedFileName());
				jsonMovieNow.put("releaseDate", m.getReleaseDate()+"");
				jsonMovieNow.put("movieCode", m.getMovieCode());
				
				for(int i=0; i<likeList.size(); i++) {
					if(m.getMovieCode().equals(likeList.get(i).getMovieCode())) {
						jsonMovieNow.put("like", 1+"");
						check = 1;
					}
				}
				
				if(check==0)
					jsonMovieNow.put("like", 0+"");
				
				jsonArray.add(jsonMovieNow);
				check = 0;
			}
			
//			System.out.println("movieNowList@movieNowServlet="+list);
//			System.out.println("jsonArray@MovieNowServlet="+jsonArray);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonArray);
//			request.getSession().setAttribute("movieNowList", list);
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

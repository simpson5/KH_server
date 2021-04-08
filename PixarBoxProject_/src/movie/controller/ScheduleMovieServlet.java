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
 * Servlet implementation class ScheduleMovieServlet
 */
@WebServlet("/movie/scheduleMovie/*")
public class ScheduleMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("scheduleMovie.do")!=-1) {
			request.getRequestDispatcher("/WEB-INF/views/movie/scheduleMovie.jsp").forward(request, response);
		}
		else if(url.indexOf("showScheduleMovie.do")!=-1) {
			String memberId = request.getParameter("memberId");
			
			List<Movie> list = new MovieService().selectScheduleMovie();
			List<LikeList> likeList = new MovieService().selectAllLikeList(memberId);
			
			int check = 0;
			
			JSONArray jsonArray = new JSONArray();
			for(Movie m : list) {
				JSONObject jsonScheduleMovie = new JSONObject();
				jsonScheduleMovie.put("movieCode", m.getMovieCode());
				jsonScheduleMovie.put("movieTitle", m.getMovieTitle());
				jsonScheduleMovie.put("runningTime", m.getRunningTime()+"");
				
				jsonScheduleMovie.put("director", m.getDirector());
				jsonScheduleMovie.put("actor", m.getActor());
				jsonScheduleMovie.put("description", m.getDescription());
				
				jsonScheduleMovie.put("releaseDate", m.getReleaseDate()+"");
				jsonScheduleMovie.put("ticketSales", m.getTicketSales()+"");
				
				jsonScheduleMovie.put("originalFileName", m.getOriginalFileName());
				jsonScheduleMovie.put("renamedFileName", m.getRenamedFileName());
				
				jsonScheduleMovie.put("movieVideo", m.getMovieVideo());
				jsonScheduleMovie.put("genres",m.getGenres());
				jsonScheduleMovie.put("schedule", m.getSchedule());
				
				for(int i=0; i<likeList.size(); i++) {
					if(m.getMovieCode().equals(likeList.get(i).getMovieCode())) {
						jsonScheduleMovie.put("like", 1+"");
						check = 1;
					}
				}
				
				if(check==0)
					jsonScheduleMovie.put("like", 0+"");
				
				jsonArray.add(jsonScheduleMovie);
				check = 0;
			}
			
//			System.out.println("jsonArray@scheduleMovie.do="+jsonArray);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonArray);
			
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

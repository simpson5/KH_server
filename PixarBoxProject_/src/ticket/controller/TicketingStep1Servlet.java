package ticket.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import movie.model.vo.Movie;
import movie.model.vo.MovieNow;
import ticket.model.service.TicketService;

/**
 * Servlet implementation class Ticketing
 */
@WebServlet("/ticket/ticketingStep1/*")
public class TicketingStep1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("step1.do")!=-1) {
			
			request.getRequestDispatcher("/WEB-INF/views/ticket/ticketingStep1.jsp").forward(request, response);
			
		}
		else if(url.indexOf("showMovieNow.do")!=-1) {
			List<Movie> list = new TicketService().selectMovieNow();
			
			JSONArray jsonArray = new JSONArray();
			
			for(Movie m : list) {
				JSONObject jsonMovie = new JSONObject();
				
				jsonMovie.put("movieCode", m.getMovieCode());
				jsonMovie.put("movieTitle", m.getMovieTitle());
				
				jsonArray.add(jsonMovie);
			}
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(jsonArray.toString());
		}
		else if(url.indexOf("showDateList.do")!=-1) {
			String movieCode = request.getParameter("movieCode");
//			System.out.println("movieCode@ticketServlet="+movieCode);
			
			List<MovieNow> list = new TicketService().selectMovieNowDate(movieCode);
			
			JSONArray jsonArray = new JSONArray();
			
			for(MovieNow m : list) {
				JSONObject jsonMovieNow = new JSONObject();
				
				jsonMovieNow.put("showDate", m.getShowDate()+"");
				
				jsonArray.add(jsonMovieNow);
			}
			
//			System.out.println(jsonArray);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(jsonArray.toString());
		}
		else if(url.indexOf("showTimeList.do")!=-1) {
			String movieCode = request.getParameter("movieCode");
			String showDateStr = request.getParameter("showDate");
			Date showDate = Date.valueOf(showDateStr);
//			System.out.println("showDate@ticketingStep1Servlet="+showDate);
			
			List<MovieNow> list = new TicketService().selectMovieNowTime(movieCode, showDate);
			
			JSONArray jsonArray = new JSONArray();
			
			for(MovieNow m : list) {
				JSONObject jsonMovieNow = new JSONObject();
				
				jsonMovieNow.put("showCode", m.getShowCode());
				jsonMovieNow.put("startTime", m.getStartTime());
				jsonMovieNow.put("showTurn", m.getShowTurn());
				jsonMovieNow.put("screenCode", m.getScreenCode());
				
				jsonArray.add(jsonMovieNow);
			}
			
//			System.out.println(jsonArray);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(jsonArray.toString());
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

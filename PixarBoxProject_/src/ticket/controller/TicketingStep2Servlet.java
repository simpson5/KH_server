package ticket.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import movie.model.vo.MovieNow;
import movie.model.vo.MovieSeat;
import theater.model.vo.Seat;
import ticket.model.service.TicketService;

/**
 * Servlet implementation class TicketingStep2Servlet
 */
@WebServlet("/ticket/ticketingStep2/*")
public class TicketingStep2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketingStep2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("step2.do")!=-1) {
			int showCode = Integer.parseInt(request.getParameter("showCode"));
//			System.out.println("showCode@TicketingStep2Servlet="+showCode);
			
			MovieNow m = new TicketService().selectMovieNowByShowCode(showCode);
			
			List<Seat> seatList = new TicketService().selectSeatInfo(m.getScreenCode(), showCode);
			
//			System.out.println("movieNow@TicketingStep2Servlet="+m);
//			System.out.println("seatList@TicketingStep2Servlet="+seatList);
			
			int rowsNum = 0;
			int colsNum = 0;
			
			if(m.getScreenCode() == 1) {
				rowsNum = 7;
				colsNum = 12;
			} else if(m.getScreenCode() == 2){
				rowsNum = 6;
				colsNum = 11;
			} else if(m.getScreenCode() == 3) {
				rowsNum = 7;
				colsNum = 11;
			} else if(m.getScreenCode() == 4) {
				rowsNum = 8;
				colsNum = 11;
			} else if(m.getScreenCode() == 5) {
				rowsNum = 6;
				colsNum = 12;
			}
			
//			System.out.println("seatList@ticketingStep2Servlet="+seatList);
			
			request.setAttribute("rowsNum", rowsNum);
			request.setAttribute("colsNum", colsNum);
			request.setAttribute("showCode", showCode);
			request.setAttribute("seatList", seatList);
			request.setAttribute("showTurn", m.getShowTurn());
			
			request.getRequestDispatcher("/WEB-INF/views/ticket/ticketingStep2.jsp").forward(request, response);
		}
		else if(url.indexOf("selectMovieInfo.do")!=-1) {
			int showCode = Integer.parseInt(request.getParameter("showCode"));
			
			List<MovieSeat> list = new TicketService().selectMovieInfo(showCode);
			
			JSONArray jsonArray = new JSONArray();
			for(MovieSeat mi : list) {
				JSONObject jsonMovieSeat = new JSONObject();
				jsonMovieSeat.put("renamedFileName", mi.getRenamedFileName());
				jsonMovieSeat.put("movieTitle", mi.getMovieTitle());
				jsonMovieSeat.put("showCode", mi.getShowCode());
				jsonMovieSeat.put("showTurn", mi.getShowTurn());
				jsonMovieSeat.put("screenCode", mi.getScreenCode());
				jsonMovieSeat.put("startTime", mi.getStartTime());
				jsonMovieSeat.put("endTime", mi.getEndTime());
				jsonMovieSeat.put("movieCode", mi.getMovieCode());
				jsonMovieSeat.put("showDate", mi.getShowDate().toString());
				jsonMovieSeat.put("seatCnt", mi.getSeatCnt());
				jsonMovieSeat.put("remainSeats", mi.getRemainSeats());
				jsonMovieSeat.put("movieImg", mi.getRenamedFileName());
				jsonArray.add(jsonMovieSeat);
			}
			
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

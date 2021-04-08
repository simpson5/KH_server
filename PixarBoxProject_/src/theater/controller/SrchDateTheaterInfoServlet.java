package theater.controller;

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

import movie.model.vo.MovieNow;
import movie.model.vo.MovieSeat;
import theater.model.service.TheaterService;

/**
 * Servlet implementation class SrchDateTheaterInfoServlet
 */
@WebServlet("/theater/srchTheaterInfo")
public class SrchDateTheaterInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrchDateTheaterInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//2.파라미터값 가져오기
		String srchDateStr = request.getParameter("srchDate");
		Date srchDate = Date.valueOf(srchDateStr);
//		System.out.println("srchDate@srchDateServlet="+srchDate);
		
		//3.비즈니스 로직처리
		/*List<ShowTimeAddTitle> list = new TheaterService().selectShowTimeList();*/
		//List<ShowTimeAddTitle> list = new TheaterService().selectTodayList();
		/*List<MovieNow> list = new TheaterService().selectShowTimeList(srchDate);
		
		JSONArray jsonArray = new JSONArray();
		
		for(MovieNow m : list) {
			JSONObject jsonMovieNow = new JSONObject();
			jsonMovieNow.put("showCode", m.getShowCode());
			jsonMovieNow.put("movieCode", m.getMovieCode());
			jsonMovieNow.put("screenCode", m.getScreenCode());
			jsonMovieNow.put("showTurn", m.getShowTurn());
			jsonMovieNow.put("startTime", m.getStartTime());
			jsonMovieNow.put("endTime", m.getEndTime());
			jsonMovieNow.put("showDate", m.getShowDate().toString());
			jsonMovieNow.put("movieTitle", m.getMovieTitle());
			
			
			//System.out.println("TheaterInfoServelt@jsonMember"+jsonMember);
			jsonArray.add(jsonMovieNow);
	
			
		}*/
		
		List<MovieSeat> list = new TheaterService().selectShowTimeList(srchDate);
		JSONArray jsonArray = new JSONArray();
		
		for(MovieSeat m : list) {
			JSONObject jsonMovieSeat = new JSONObject();
			jsonMovieSeat.put("showCode", m.getShowCode());
			jsonMovieSeat.put("movieCode", m.getMovieCode());
			jsonMovieSeat.put("screenCode", m.getScreenCode());
			jsonMovieSeat.put("showTurn", m.getShowTurn());
			jsonMovieSeat.put("startTime", m.getStartTime());
			jsonMovieSeat.put("endTime", m.getEndTime());
			jsonMovieSeat.put("showDate", m.getShowDate().toString());
			jsonMovieSeat.put("movieTitle", m.getMovieTitle());
			jsonMovieSeat.put("seatCnt", m.getSeatCnt());
			jsonMovieSeat.put("remainSeats", m.getRemainSeats());

			//System.out.println("TheaterInfoServelt@jsonMember"+jsonMember);
			jsonArray.add(jsonMovieSeat);
		}
		
//		System.out.println("SrchDateServlet@jsonArray"+jsonArray);
		
		//2.view단처리: json문자열을 출력하기
		//인코딩과 mime타입 설정 필수
		response.setContentType("application/json; charset=utf-8"); 
		response.getWriter().append(jsonArray.toString());
		//response.getWriter().print(jsonArray);
	//	PrintWriter out = response.getWriter();
	//	out.print(jsonArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

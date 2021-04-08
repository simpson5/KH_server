package admin.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import movie.model.vo.MovieNow;

/**
 * Servlet implementation class AdminInsertMovieNow
 */
@WebServlet("/admin/insertMovieNow")
public class AdminInsertMovieNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInsertMovieNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieCode = request.getParameter("movieCode");
		String showTurn = request.getParameter("showTurn");
		String screenCodeAndRemainSeat = request.getParameter("screenCodeAndRemainSeat");
		String[] splitArr = screenCodeAndRemainSeat.split("_");

		int screenCode = 0;
		int remainSeats = 0;
	
		screenCode = Integer.parseInt(splitArr[0]);
		remainSeats = Integer.parseInt(splitArr[1]);
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String showDateStr = request.getParameter("showDate");
		Date showDate = Date.valueOf(showDateStr);
		
		MovieNow movieNow = new MovieNow();
		
		movieNow.setMovieCode(movieCode);
		movieNow.setScreenCode(screenCode);
		movieNow.setShowTurn(showTurn);
		movieNow.setStartTime(startTime);
		movieNow.setEndTime(endTime);
		movieNow.setShowDate(showDate);
		movieNow.setRemainSeats(remainSeats);
		
		
		int result = new AdminService().insertMovieNow(movieNow);
		
		String msg = "";
		String loc = "/admin/addDeleteMovieNow";
		if(result>0) {
			msg = "현재상영작이 성공적으로 등록되었습니다.";
		}
		else {
			msg = "현재상영작 등록에 실패했습니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
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

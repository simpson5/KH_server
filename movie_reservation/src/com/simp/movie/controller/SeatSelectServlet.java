package com.simp.movie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.ScreenInfo;
import com.simp.product.model.vo.Ticket;

import common.MvcUtils;

/**
 * Servlet implementation class SeatSelectServlet
 */
@WebServlet("/movie/reservation/seat")
public class SeatSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//페이지 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String screen_no = request.getParameter("screen_no");
		String memberId = request.getParameter("memberId");
		
		//영화 중복 예매 검사하기
		HttpSession session = request.getSession(true);
		if(!new MovieService().checkReservation(memberId, screen_no)) {
			String referer =  request.getHeader("Referer");
			session.setAttribute("msg", "중복예매입니다.");
			response.sendRedirect(referer);
			return;
		}
		
		ScreenInfo si = new MovieService().selectOneScreenInfo(screen_no);
		
		//좌석수 
		int col = 0;
		switch(si.getTheaterNo()) {
		case "상영관1" :
			col = 20;
			break;
		case "상영관2" :
			col = 20;
			break;			
		case "상영관3" :
			col = 20;
			break;			
		case "상영관4" :
			col = 10;
			break;			
		case "상영관5" :
			col = 10;
			break;			
		}
		
		System.out.println(si);
		
		List<Ticket> tkList = new MovieService().selectTicket(screen_no);
		
		List<Integer> seat_cnt = new ArrayList<>();
		
		for(int i = 0; i < si.getTotalTicket(); i++) {
			if(tkList.get(i).getTicketStatus().equals("X")) {
				System.out.println(tkList.get(i).getSetNo().substring(3));
				char alpa = tkList.get(i).getSetNo().substring(3).charAt(0);
				int y = Integer.parseInt(tkList.get(i).getSetNo().substring(4));
				int x = (int)alpa - 65;
				System.out.println((int)alpa);
				seat_cnt.add(x*col + y);	
			}
		}
		
		System.out.println(seat_cnt);
		
		request.setAttribute("seat_cnt", seat_cnt);
		request.setAttribute("tkList", tkList);
		request.setAttribute("screenInfo", si);
		request.getRequestDispatcher("/WEB-INF/views/movie_reservation/seat.jsp")
		.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

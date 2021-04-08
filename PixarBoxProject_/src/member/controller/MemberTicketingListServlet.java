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

import ticket.model.service.TicketService;
import ticket.model.vo.Ticketing;

/**
 * Servlet implementation class MemberTicketingList
 */
@WebServlet("/member/memberTicketingList/*")
public class MemberTicketingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberTicketingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("ticketingList.do")!=-1) {
			String memberId = request.getParameter("memberId");
			
			List<Ticketing> list = new TicketService().selectTicketingListBymemberId(memberId);
			
			//view단 처리: json
			JSONArray jsonArray = new JSONArray();
			for(Ticketing t: list) {
				JSONObject jsonTicketing = new JSONObject();
				jsonTicketing.put("ticketNo", t.getPaymentCode());
				jsonTicketing.put("movieTitle", t.getMovieTitle());
				jsonTicketing.put("screenCode", t.getScreenCode()); //관
				jsonTicketing.put("seatNo", t.getSeatNo()); //좌석
				jsonTicketing.put("showDate", t.getShowDate().toString());
				jsonTicketing.put("startTime", t.getStartTime());
				jsonTicketing.put("endTime", t.getEndTime());
				jsonTicketing.put("ticketingDate", t.getTicketingDate()+"");
/*				jsonTicketing.put("cancelYN", t.getCancelYN());*/
				
				jsonArray.add(jsonTicketing);
			}
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonArray);
		}
		else if(url.indexOf("cancelList.do")!=-1) {
			String memberId = request.getParameter("memberId");
			
			List<Ticketing> list = new TicketService().selectCancelListBymemberId(memberId);
			
			//view단 처리: json
			JSONArray jsonArray = new JSONArray();
			for(Ticketing t: list) {
				JSONObject jsonTicketing = new JSONObject();
				jsonTicketing.put("ticketNo", t.getPaymentCode());
				jsonTicketing.put("movieTitle", t.getMovieTitle());
				jsonTicketing.put("screenCode", t.getScreenCode()); //관
				jsonTicketing.put("seatNo", t.getSeatNo()); //좌석
				jsonTicketing.put("showDate", t.getShowDate().toString());
				jsonTicketing.put("startTime", t.getStartTime());
				jsonTicketing.put("endTime", t.getEndTime());
				jsonTicketing.put("ticketingDate", t.getTicketingDate());
				jsonTicketing.put("cancelDate", t.getCancelDate());
				
				jsonArray.add(jsonTicketing);
			}
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

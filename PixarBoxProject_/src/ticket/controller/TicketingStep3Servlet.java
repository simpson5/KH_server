package ticket.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import ticket.model.service.TicketService;
import ticket.model.vo.Payment;
import ticket.model.vo.Ticketing;

/**
 * Servlet implementation class TicketingStep3Servlet
 */
@WebServlet("/ticket/ticketingStep3/*")
public class TicketingStep3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketingStep3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("step3.do")!=-1) {
			String memberId = request.getParameter("memberId");
			Member member = new MemberService().selectOne(memberId);
			
			int showCode = Integer.parseInt(request.getParameter("showCode"));
			String selectedSeat = request.getParameter("selectedSeat");
			int total = Integer.parseInt(request.getParameter("total"));
			int adult = Integer.parseInt(request.getParameter("adult"));
			int child = Integer.parseInt(request.getParameter("child"));
			int special = Integer.parseInt(request.getParameter("special"));
			
			String[] seatArr = selectedSeat.split(" ");
			String [] resultArr = new String[seatArr.length];
			for(int i=0; i<seatArr.length; i++) {
//				System.out.println("@seatArr="+ seatArr[i]);
				resultArr[i] = new TicketService().selectReservationYN(seatArr[i]);
//				System.out.println("@resultArr="+resultArr[i]);
			}
			
			String view = "";
			String loc = "/";
			String msg = "";
			for(int i=0; i<resultArr.length; i++) {
				if(resultArr[i].equals("Y")) {
					view = "/WEB-INF/views/common/msg.jsp";
					msg = "이미 선택된 좌석입니다.";
					loc = "/ticket/ticketing/step2?showCode="+showCode;
					break;
				}else {
					view = "/WEB-INF/views/ticket/ticketingStep3.jsp";
				}
			}
			
			request.setAttribute("member", member);
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.setAttribute("showCode", showCode);
			request.setAttribute("total", total);
			request.setAttribute("selectedSeat", selectedSeat);
			request.setAttribute("adult", adult);
			request.setAttribute("child", child);
			request.setAttribute("special", special);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
			reqDispatcher.forward(request, response);
		}
		else if(url.indexOf("ticketingEnd.do")!=-1) {
			String memberId = request.getParameter("memberId");
			String paymentMethod = request.getParameter("paymentMethod");
			int paymentPrice = Integer.parseInt(request.getParameter("paymentPrice"));
			String seatNo = request.getParameter("seatNo");
			int screenCode = Integer.parseInt(request.getParameter("screenCode"));
			String movieTitle = request.getParameter("movieTitle");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			int point = Integer.parseInt(request.getParameter("point"));
			int showCode = Integer.parseInt(request.getParameter("showCode"));
			String showDateStr = request.getParameter("showDate");
			Date showDate = Date.valueOf(showDateStr);
			
			int rndNum = (int)(Math.random()*1000); //0~999
			int rndNum2 = (int)(Math.random()*1000); //0~999
			String paymentCode = rndNum+"-"+memberId+"-"+rndNum2;
			Payment p = new Payment(paymentCode, memberId, paymentMethod, null, paymentPrice);
			
			int paymentResult = new TicketService().insertPayment(p);
			Ticketing t = new Ticketing(0, movieTitle, paymentCode, memberId, seatNo, screenCode, startTime, endTime, null, null, null, paymentPrice, showDate);
			int ticketingResult = new TicketService().insertTicketing(t);
			
/*			String msg = "";
			String view = "/WEB-INF/views/common/msg.jsp";
			String loc = "";
			if(paymentResult>0 && ticketingResult>0) {
				msg = "예매가 완료되었습니다.";
				loc = "/member/my/purchase.do?memberId="+memberId;
			}else {
				msg = "예매를 실패하였습니다.";
				loc = "/";
			}*/
			
			//남은좌석 표시
			//결제하기 누를 시 SEAT 테이블의 reservation_yn -> 'Y' & 좌석 수 -1
			String[] seatNoArr = seatNo.split(",");
			int [] seatResultArr = new int[seatNoArr.length];
			int seatResultCnt = 0;
			
			for(int i=0; i<seatNoArr.length; i++) {
//				System.out.println("EndServlet@seatNoArr="+seatNoArr[i]);
				seatResultArr[i] = new TicketService().updateReservationYN_Y(seatNoArr[i], screenCode, showCode);
				if(seatResultArr[i] > 0) {
					seatResultCnt++;
				}
				
			}
//			System.out.println("seatResultCnt="+seatResultCnt+"seatNoArr.length="+seatNoArr.length);
			
			//포인트 차감
			int pointResult = new TicketService().usePoint(memberId, point, movieTitle, paymentCode);
			//포인트 적립
			double savePoint = paymentPrice * 0.05;
			int savePointResult = new TicketService().savePoint(memberId, savePoint, movieTitle, paymentCode);
			
			String msg = "";
			String view = "/WEB-INF/views/common/msg.jsp";
			String loc = "";
			
			//결제, 예매, 좌석트리거, 포인트 사용, 적립의 모든 결과가 > 0이어야 에매 완료
			if(paymentResult>0 && ticketingResult>0 && pointResult>0 && savePointResult>0 && seatResultCnt == seatNoArr.length) {
				msg = "예매가 완료되었습니다.";
				loc = "/member/my/purchase.do?memberId="+memberId;
			}else {
				msg = "예매를 실패하였습니다.";
				loc = "/";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
			reqDispatcher.forward(request, response);
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

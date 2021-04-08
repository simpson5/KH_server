package member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.PointList;
import ticket.model.service.TicketService;

/**
 * Servlet implementation class MemberTicketingCancel
 */
@WebServlet("/member/memberTicketingCancel")
public class MemberTicketingCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberTicketingCancel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int screenCode = Integer.parseInt(request.getParameter("screenCode"));
		String seatNo = request.getParameter("seatNo");
		String ticketNo = request.getParameter("ticketNo");
		String showDate = request.getParameter("showDate");
		String startTime = request.getParameter("startTime");
		String showTime = showDate + " " + startTime + ":" + "00";
//		System.out.println(showTime);
		
		//[사용포인트 => 적립  / 적립포인트 => 차감  / 포인트내역 수정]
		//1. 사용했던 포인트내역 가져오기
		String statusUse = "사용";
		PointList usePoint = new MemberService().selectPointByPaymentCode(ticketNo, statusUse);	
		//2.적립했던 포인트내역 가져오기
		String statusAdd = "적립";
		PointList addPoint = new MemberService().selectPointByPaymentCode(ticketNo, statusAdd);

		//3.사용했던 포인트를 다시 적립해주기 (아이디, 포인트, 영화제목, 결제코드)
		String cancelstr1 = usePoint.getMovieTitle()+" 취소내역";
		int useToAdd = new TicketService().savePoint(memberId, usePoint.getAmount(), cancelstr1, usePoint.getPaymentCode()+"-cancelUse");
		//4. 적립했던 포인트를 다시 차감하기 (아이디, 포인트, 영화제목, 결제코드)
		String cancelstr2 = addPoint.getMovieTitle()+" 취소내역";
		int addToUse = new TicketService().usePoint(memberId, addPoint.getAmount(), cancelstr2, addPoint.getPaymentCode()+"-cancelAdd");

//		System.out.println("사용"+usePoint+"[적립:"+addPoint);
//		System.out.println("사용을 적립"+useToAdd+"[적립을 사용:"+addToUse);
		
		//30분전 취소
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		try {
			Date showTimeDate = sdf.parse(showTime);
			
			long diff = showTimeDate.getTime() - date.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
//			System.out.println("날짜차이="+diffDays);
			
			long minute = diff/60000;
//			System.out.println("분 차이="+minute);
			
			String msg = "";
			String loc = "/member/my/purchase.do?memberId="+memberId;
			if(minute<31) {
				msg = "예매취소는 영화시작시간 30분 전에만 가능합니다.";
			}else {
				int cancelResult = new MemberService().updateCancelYN_Y(ticketNo);
				
				String[] seatNoArr = seatNo.split(",");
				int reservationResult = 0;
				for(int i=0; i<seatNoArr.length; i++) {
					reservationResult = new MemberService().updateReservationYN_N(screenCode, seatNoArr[i]);					
				}
				int paymentDeleteResult = new MemberService().deletePayment(ticketNo);
				if(cancelResult>0 && reservationResult>0 && paymentDeleteResult>0) {
					msg = "예매취소가 성공적으로 완료되었습니다.";
				}else {
					msg = "예매취소를 실패했습니다.";
				}
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

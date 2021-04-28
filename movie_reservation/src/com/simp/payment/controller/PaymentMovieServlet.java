package com.simp.payment.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class PaymentMovieServlet
 */
@WebServlet("/payment/moive")
public class PaymentMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member_id = request.getParameter("member_id");
		String screen_no = request.getParameter("screen_no");
		String seat_no = request.getParameter("seat_no");
		int amount = Integer.parseInt(request.getParameter("amount"));
		int point = Integer.parseInt(request.getParameter("point"));
		
		String approval_url = "http://localhost:9090/movie_reservation/payment/success/movie";
		String cancel_url = "http://localhost:9090/movie_reservation/cancel";
		String fail_url = "http://localhost:9090/movie_reservation/fail";
		
		System.out.println(member_id);
		System.out.println(screen_no);
		System.out.println(seat_no);
		System.out.println(amount);
		
		URL url;
		try {
			url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //서버연결 도와주는 객체
			
			conn.setRequestMethod("POST"); // 연결 방식
			conn.addRequestProperty("Authorization", "KakaoAK 5fffd27826e6caedfb2144a06131c22c"); // 나의 키
			conn.addRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 타입 지정
			conn.setDoInput(true); // 연결 성공시 값 받아올지
			conn.setDoOutput(true); // 값을 넣을 것인지
			
			//URL 맵 지정
			//띄어쓰기 있으면 끝장난다!
			String parameter = "cid=TC0ONETIME&partner_order_id=simpson&partner_user_id="+member_id
					+ "&item_name=" + screen_no
					+ "&quantity=1"
					+ "&total_amount=" + amount
					+ "&vat_amount=0"
					+ "&tax_free_amount=0"
					+ "&approval_url=" + approval_url
					+ "&cancel_url=" + cancel_url
					+ "&fail_url=" + fail_url;
			OutputStream ops = conn.getOutputStream();
			DataOutputStream dops = new DataOutputStream(ops);
			dops.writeBytes(parameter);
			//dops.flush(); // conn 보내서 비워버린다. close하면 자동으로 호출됨
			dops.close();
			
			int result = conn.getResponseCode();
			
			InputStream ips;
			//200만이 성공이다 나머지 다 실패
			if(result == 200) {
				ips = conn.getInputStream();
				System.out.println("성공");
				
				HttpSession session = request.getSession();
				session.setAttribute("payment_info", member_id + "&&" + screen_no + "&&" + seat_no + "&&" + amount + "&&" + point);
			} else {
				ips= conn.getErrorStream();
				System.out.println("실패");
			}
			
			InputStreamReader isr = new InputStreamReader(ips);
			BufferedReader bfr = new BufferedReader(isr);
			
			//json - simple
			org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
			JSONObject obj = (JSONObject) parser.parse(bfr);

			System.out.println(obj);
			
			//3.응답메세지 작성
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(obj);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

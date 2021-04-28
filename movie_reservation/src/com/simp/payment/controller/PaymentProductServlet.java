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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * Servlet implementation class PaymentKakaoServlet
 */
@WebServlet("/payment/product")
public class PaymentProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String member_id = request.getParameter("member_id");
		String pd_name_arr = request.getParameter("pd_name");
		String[] pd_name =  pd_name_arr.split(",");
		String cnt_arr = request.getParameter("pd_cnt");
		String[] cnt = cnt_arr.split(",");
		int pd_cnt = Integer.parseInt(cnt[0]);
		int total_amount = Integer.parseInt(request.getParameter("total_amount"));
		String approval_url = "http://localhost:9090/movie_reservation/payment/success/product?";
		String cancel_url = "http://localhost:9090/movie_reservation/cancel";
		String fail_url = "http://localhost:9090/movie_reservation/fail";
		
		System.out.println(member_id);
		System.out.println(pd_name);
		System.out.println(pd_cnt);
		System.out.println(total_amount);
		
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
			String parameter = "cid=TC0ONETIME&partner_order_id=simpson&partner_user_id="+member_id
					+ "&item_name=" + pd_name[0]
					+ "&quantity=" + cnt[0]
					+ "&total_amount=" + total_amount
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
				session.setAttribute("payment_info", member_id + "&&" + pd_name_arr + "&&" + cnt_arr);
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

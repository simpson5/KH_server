package com.kh.ajax.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxXmlServlet
 */
@WebServlet("/xml")
public class AjaxXmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 업무로직
		List<Member> list = new ArrayList<>();
		list.add(new Member("simpson", "심슨", "김고은.jpg"));
		list.add(new Member("samson", "삼손", "mattDamon.jpg"));
		list.add(new Member("sipson", "십슨", "유재석.jpg"));
		list.add(new Member("samsam", "삼삼", "daftpunk.jpg"));
		
		//2. jsp 위임
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/xml/members.jsp")
			   .forward(request, response);
	}

}

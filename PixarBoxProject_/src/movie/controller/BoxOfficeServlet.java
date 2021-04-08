package movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.service.MovieService;
import movie.model.vo.Movie;

import static common.JDBCTemplate.*;

import java.sql.Connection;

/**
 * Servlet implementation class BoxOfficeServlet
 */
@WebServlet("/movie/boxOffice/*")
public class BoxOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// 전체게시물수, 한페이지당 표시할 게시물수 => 총페이지수
		int totalContent = new MovieService().selectMovieCount();
		final int numPerPage = 8;

		// 총페이지수: 올림처리
		int totalPage = (int) (Math.ceil((double) totalContent / numPerPage));
		int order = 0;
		
		String url = request.getRequestURL().toString();
		
		
//		System.out.println("totalPage="+totalPage);
//		System.out.println("totalContent=" + totalContent);
//		System.out.println("totalPage=" + totalPage);

		request.setAttribute("totalPage", totalPage);
		
		if(url.indexOf("order1.do")!=-1) { //예매율순
			
			order = 1;
			
		}
		
		if(url.indexOf("order2.do")!=-1) { //개봉날짜
			
			order = 2;
		}
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("/WEB-INF/views/movie/boxOffice.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

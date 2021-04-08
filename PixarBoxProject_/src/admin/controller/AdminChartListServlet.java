package admin.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import admin.model.service.AdminService;
import movie.model.service.MovieService;
import movie.model.vo.Movie;

/**
 * Servlet implementation class AdminChartListServlet
 */
@WebServlet("/admin/chartList/*")
public class AdminChartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminChartListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("chartListView.do")!=-1) {
			
			request.getRequestDispatcher("/WEB-INF/views/admin/chartView.jsp").forward(request, response);
			
		}else if(url.indexOf("ticketSales.do")!=-1) {
			//영화 정보 가져오기
			List<Movie> movieList = new MovieService().selectAllMovieInfo();
			
			JSONObject data =new JSONObject();
			
			//컬럼객체
			JSONObject col1 =new JSONObject();
			JSONObject col2 =new JSONObject();
			JSONArray title =new JSONArray();
			col1.put("label", "movieTitle");
			col1.put("type", "string");
			col2.put("label", "판매량");
			col2.put("type" , "number");
			
			title.add(col1);
			title.add(col2);
			
			data.put("cols", title);
	
		
			JSONArray  body =new JSONArray();
			for(Movie  m : movieList){
				JSONObject movieTitle =new JSONObject();
				movieTitle.put("v", m.getMovieTitle()); //상품이름 -> v 객체 
				JSONObject ticketSales =new JSONObject(); 
				ticketSales.put("v", m.getTicketSales()); //가격 ->v 객체
				System.out.println(m.getMovieTitle());

				//  v객체를 row 배열을 만든후 추가한다.
				JSONArray row =new JSONArray();
				row.add(movieTitle);
				row.add(ticketSales);   
	 
				//   c 객체 를 만든후 row 배열을 담는다.
				JSONObject c =new JSONObject();
				c.put("c", row);		
				// c 객체를 배열 형태의 body 에 담는다.
				body.add(c);		
			}
			// 배열 형태의 body 를 rows 키값으로 객체 data 에 담는다.
			data.put("rows", body);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(data);
			
		}else if(url.indexOf("chartType.do")!=-1) {
	
			String searchType = request.getParameter("searchType");
			System.out.println(searchType+"ddd");
			
			if(searchType.equals("ticketSales")) {
				
				request.getRequestDispatcher("/WEB-INF/views/admin/chartView.jsp").forward(request, response);
				
			}else if(searchType.equals("daySales")) {
			
				
				
				request.getRequestDispatcher("/WEB-INF/views/admin/chartDaySales.jsp").forward(request, response);
				
			}else if(searchType.equals("monthSales")) {
				

				request.getRequestDispatcher("/WEB-INF/views/admin/chartMonthSales.jsp").forward(request, response);
				
			}
			
		}else if(url.indexOf("MonthSales.do")!=-1) {
			
			//년도와 월 가져오기
			int year = Integer.parseInt(request.getParameter("year"));
			
			
			//year년 month달 일 매출 가지고 오기.
			Map<Integer,Integer> MonthList = new AdminService().selectMonthSales(year);
			
//			System.out.println("Servlet@MonthList="+MonthList);
			
			JSONObject data =new JSONObject();
			
			//컬럼객체
			JSONObject col1 =new JSONObject();
			JSONObject col2 =new JSONObject();
			JSONArray title =new JSONArray();
			col1.put("label", "원");
			col1.put("type", "number");
			col2.put("label", "월");
			col2.put("type" , "number");
			
			title.add(col1);
			title.add(col2);
			
			data.put("cols", title);
			
			//맵안에 있는 정보들 빼내기.
			Set set = MonthList.entrySet();
			Iterator iterator = set.iterator();
			JSONArray  body =new JSONArray();
			
			while(iterator.hasNext()){

			  Map.Entry entry = (Map.Entry)iterator.next();
			  int key = (int)entry.getKey();
			  int value = (int)entry.getValue();

//			  System.out.println("hashMap Key : " + key);
//			  System.out.println("hashMap Value : " + value);
			 
			  JSONObject month =new JSONObject();
			  month.put("v", key); //상품이름 -> v 객체 
			  JSONObject monthSales =new JSONObject(); 
			  monthSales.put("v", value); //가격 ->v 객체

				//  v객체를 row 배열을 만든후 추가한다.
				JSONArray row =new JSONArray();
				row.add(month);
				row.add(monthSales);   
	 
				//   c 객체 를 만든후 row 배열을 담는다.
				JSONObject c =new JSONObject();
				c.put("c", row);		
				// c 객체를 배열 형태의 body 에 담는다.
				body.add(c);		
			  

			}


	
			// 배열 형태의 body 를 rows 키값으로 객체 data 에 담는다.
			data.put("rows", body);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(data);
			
			
			
		}	
	
		
	
		
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

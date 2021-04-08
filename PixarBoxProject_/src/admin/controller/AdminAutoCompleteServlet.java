package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;

/**
 * Servlet implementation class AdminAutoCompleteServlet
 */
@WebServlet("/admin/autoComplete")
public class AdminAutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAutoCompleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/csv; charset=utf-8");
				
		//2.파라미터핸들링
		String srchMovie = request.getParameter("srchMovie");
//		System.out.println("srchMovie="+srchMovie);
				
		//유효성 검사
		if(srchMovie.trim().isEmpty()) return;
				
		//3.업무로직
		List<String> movieList = new AdminService().selectByMovieName(srchMovie);

		//csv 작업
		StringBuilder csv = new StringBuilder();
		if(movieList!=null && !movieList.isEmpty()){
			for(int i=0; i< movieList.size(); i++){
				if(i!=0) csv.append(",");
					csv.append(movieList.get(i));
			}
		}
				
		//4.view단 처리: csv형태로 전송
		response.getWriter().append(csv);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

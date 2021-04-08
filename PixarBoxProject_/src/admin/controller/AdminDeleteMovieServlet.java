package admin.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;

/**
 * Servlet implementation class AdminDeleteMovieServlet
 */
@WebServlet("/admin/movieDelete")
public class AdminDeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieCode = request.getParameter("movieCode");
		String renamedFileName = request.getParameter("renamedFileName");
		
		int result = new AdminService().deleteMovie(movieCode);
//		System.out.println("AdminDeleteMovieServlet@result="+result);
		//파일삭제
		if(result>0 && !"".equals(renamedFileName)) {
			//파일저장경로
			String saveDirectory
				= getServletContext().getRealPath("/upload/board");
			
			File delFile = new File(saveDirectory, renamedFileName);
//			System.out.println("delFile="+delFile);
			
			//1.삭제처리
			boolean bool = delFile.delete();
			
//			System.out.println("파일삭제 : "+(bool?"성공!":"실패!"));
		}
		
		//3. 받은 결과에 따라 뷰페이지 내보내기
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		//javascript/html에서 사용할 url은 contextPath를 포함한다.
		String loc = "/movie/boxOffice/order1.do";

		if(result>0)
			msg = "영화 삭제 성공!";
			
		else 
			msg = "영화 삭제 실패!";	
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

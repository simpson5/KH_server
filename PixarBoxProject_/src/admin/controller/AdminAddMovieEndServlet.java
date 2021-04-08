package admin.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import admin.model.service.AdminService;
import common.MvcFileRenamePolicy;
import movie.model.vo.Movie;

/**
 * Servlet implementation class AdminAddMovieEndServlet
 */
@WebServlet("/admin/addMovieEnd")
public class AdminAddMovieEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddMovieEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일이 저장될 절대경로 가져오기
		String saveDirectory
			= getServletContext().getRealPath("/images");// / 웹루트 WebContent
//		System.out.println("saveDirectory="+saveDirectory);
		
		//파일최대업로드크기 제한: 10MB까지 제한
		//10MB = 1024 * 1024 * 10
		int maxPostSize = 1024 * 1024 * 10; 
		
		//파일명 재지정 정책 객체
		FileRenamePolicy mvcFileRenamePolicy
			= new MvcFileRenamePolicy();
		MultipartRequest multiReq = new MultipartRequest(request,
														 saveDirectory, 
														 maxPostSize, 
														 "utf-8",
														 mvcFileRenamePolicy);
		
		
		String movieCode = multiReq.getParameter("movieCode");
		String movieTitle = multiReq.getParameter("movieTitle");
		String director = multiReq.getParameter("director");
		String actor = multiReq.getParameter("actor");
		int runningTime = Integer.parseInt(multiReq.getParameter("runningTime"));
		//Date releaseDate = Date.valueOf();
		String stringDate = multiReq.getParameter("releaseDate");
//		System.out.println(stringDate+"stringD");
	
		java.sql.Date date = java.sql.Date.valueOf(stringDate);

		String description = multiReq.getParameter("description");
		String movieVideo = multiReq.getParameter("movieVideo");

		String[] genreArr = multiReq.getParameterValues("genre");
//		System.out.println(genreArr);
		String genre = "";
		
		for(int i=0; i<genreArr.length; i++) {
			if(i != genreArr.length-1) genre += genreArr[i]+",";
			else genre += genreArr[i];
		}
		
		String schedule = multiReq.getParameter("schedule");
		
		
		String originalFileName 
		= multiReq.getOriginalFileName("upFile");//사용자 업로드한 파일명
		String renamedFileName
		= multiReq.getFilesystemName("upFile");//실제 저장된 파일명
		
		Movie m = new Movie(movieCode,movieTitle,runningTime,director,actor,description,date,0,originalFileName,renamedFileName,movieVideo,genre,schedule);

//		System.out.println("addMovieEndServlet@m="+m);
		
		//business logic
		int result = new AdminService().insertMovie(m);
		
		String msg = "";
		String loc = "/movie/boxOffice/order1.do";
		
		if(result>0) msg = "영화등록 성공!";
		else msg = "영화등록 실패!";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package admin.controller;

import java.io.File;
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
 * Servlet implementation class AdminUpdateMovieEndServlet
 */
@WebServlet("/admin/updateMovieEnd")
public class AdminUpdateMovieEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateMovieEndServlet() {
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
		
		//기존첨부파일 정보: 파일이 없는 경우 ""값이 넘어온다.
				String oldOriginalFileName
					= multiReq.getParameter("oldOriginalFileName");
				String oldRenamedFileName
				= multiReq.getParameter("oldRenamedFileName");
		
		/* 
		 * 1.기존첨부파일이 있는 경우:
			- 새로운 첨부파일을 추가한 경우: 기존파일을 삭제, 새로운 파일정보 db등록
			- 기존파일만 삭제하는 경우: 기존파일 삭제, db데이터 null값 대입
			- 기존파일을 유지하는 경우: 기존파일 유지, db데이터 기존값 유지
		*/
		if(!"".equals(oldOriginalFileName)) {
			
			//신규첨부파일 유무 검사: upFile파일첨부가 없는 경우, null을 리턴
			File f = multiReq.getFile("upFile");
			
			//신규첨부파일이 있는 경우, 기존첨부파일 삭제
			if(f!=null) {
				File delFile = new File(saveDirectory, oldRenamedFileName);
				boolean result = delFile.delete();
//				System.out.println("기존첨부파일삭제: "+(result?"성공!":"실패!"));
			}
			//신규첨부파일이 없는 경우: 기존파일 삭제
			else if(multiReq.getParameter("delFileChk")!=null) {
				File delFile = new File(saveDirectory, oldRenamedFileName);
				boolean result = delFile.delete();
//				System.out.println("기존첨부파일삭제: "+(result?"성공!":"실패!"));				
			}
			//신규첨부파일이 없는 경우: 기존파일 유지
			else {
				originalFileName = oldOriginalFileName;
				renamedFileName = oldRenamedFileName;
			}
		}
		

		Movie m = new Movie(movieCode,movieTitle,runningTime,director,actor,description,date,0,originalFileName,renamedFileName,movieVideo,genre,schedule);
		
		//2.business logic
		int result = new AdminService().updateMovie(m);
		
		String msg = "";
		String loc = "/movie/movieView/movieDescription.do?movieCode="+movieCode;
		
		if(result>0) {
			msg = "영화 업데이트 성공!";
		}
		else {
			msg = "영화 업데이트 실패!";
		}
		
		
		//3.view단 처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

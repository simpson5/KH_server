package com.simp.movie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.simp.movie.model.service.MovieService;
import com.simp.movie.model.vo.Movie;
import com.simp.movie.model.vo.ScreenMovie;

/**
 * Servlet implementation class MovieEnrollServlet
 */
@WebServlet("/movie/movieEnroll")
public class MovieEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//상영 영화 등록으로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String img = request.getParameter("img");
		String pubDate = request.getParameter("opening_date");
		String director = request.getParameter("director");
		String actor = request.getParameter("actor");
		
		Movie m = new Movie(title, img, pubDate, director, actor);
		
		int checkMovie = new MovieService().checkMovie(m);
		
		if(checkMovie == 0) {
			request.setAttribute("movie", m);
			request.getRequestDispatcher("/WEB-INF/views/enroll_movie.jsp")
			.forward(request, response);			
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("msg", "이미 등록된 영화입니다.");
			response.sendRedirect(request.getContextPath() + "/movie/search");
		}
		
	}

	//상영 영화 등록 작업
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//영화 자료 업로드
		String saveDirectory = getServletContext().getRealPath("/upload/movie/");
		System.out.println("saveDirectory@servlet = " + saveDirectory);
		
		// 최대 파일 허용 크기 10mb = 10 * 1kb * 1kb
		int maxPostSize = 10 * 1024 * 1024; // byte 단위로 전달
		
		//인코딩
		String encoding = "utf-8";
		
		//파일명 변경정책 객체
		//중복파일인 경우, numbering처리
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		//FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		//생성자가 만들어지면 파일 저장이 끝난다!
		MultipartRequest multipartRequest =
				new MultipartRequest(
								request,
								saveDirectory,
								maxPostSize,
								encoding,
								policy
							);
		
		//영화 정보 가져오기
		String title = multipartRequest.getParameter("title");
		String img = multipartRequest.getParameter("img");
		String pubDate = multipartRequest.getParameter("opening_date");
		String director = multipartRequest.getParameter("director");
		String actor = multipartRequest.getParameter("actor");
		
		Movie m = new Movie(title, img, pubDate, director, actor);
		System.out.println(m);
		
		//기본 영화 정보 등록
		int insert = 0;
		insert = new MovieService().enrollMovie(m);
		
		//추가 영화 정보 등록
		String[] genres = multipartRequest.getParameterValues("genre");
		String genre = "";
		
		for(int i = 0; i < genres.length; i++) {
			genre += genres[i];
			if(i != genres.length - 1) {
				genre += ",";
			}
		}
		
		System.out.println(genre);
		
		String mainPoster = multipartRequest.getFilesystemName("main_poster");
		String poster = multipartRequest.getFilesystemName("poster");
		//저장은 다 되지만 남는것은 마지막 파일명 하나다.
		String steelCuts = multipartRequest.getFilesystemName("steel_cut");
		String steelCut = "";
		for(int i = 1; i < 5; i++) {
			steelCut += steelCuts.replace("4.", i + ".");
			if(i != 4) {
				steelCut += ",";
			}
		}
		
		String trailer = multipartRequest.getParameter("trailer");
		String synopsis = multipartRequest.getParameter("synopsis");
		String screeningRate = multipartRequest.getParameter("screening_rate");
		
		System.out.println(mainPoster);
		
		ScreenMovie sm  = new ScreenMovie();
		sm.setMovieTitle(title);
		sm.setMainPoster(mainPoster);
		sm.setPoster(poster);
		sm.setSteelCut(steelCut);
		sm.setTrailer(trailer);
		sm.setSynopsis(synopsis);
		sm.setScreeningRate(screeningRate);
		sm.setGenre(genre);
		
		System.out.println(sm.toString());
		insert = new MovieService().enrollScreenMovie(sm);
		

		request.setAttribute("screenMovie", sm);
		request.getRequestDispatcher("/WEB-INF/views/enroll_screen.jsp")
	           .forward(request, response);
	}

}

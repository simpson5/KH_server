package movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.service.MovieService;
import movie.model.vo.LikeList;
import movie.model.vo.Movie;
import movie.model.vo.MovieComment;
import movie.model.vo.MovieNow;

/**
 * Servlet implementation class MovieViewServlet
 */
@WebServlet("/movie/movieView/*")
public class MovieViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		String movieCode = request.getParameter("movieCode");
		String memberId = request.getParameter("memberId");
		
		Movie movie = new MovieService().selectMovie(movieCode);
		List<MovieNow> movieNowList = new MovieService().selectMovieNowList(movieCode);
		
//		System.out.println("movieNowList@movieViewServlet="+movieNowList);
		
		int sum = 0;
		double rate = 0;
		
		for(MovieNow m : movieNowList) {
//			System.out.println("ticketSales="+m.getTicketSales());
			sum += m.getTicketSales();
//			System.out.println(m.getMovieCode());
		}
//		System.out.println("sum"+sum);
		
		for(MovieNow m : movieNowList) {
			if(m.getMovieCode().equals(movieCode)) {
				rate = (double)movie.getTicketSales()/sum;
				rate = rate*100;
				rate = (Math.ceil(rate*10)/10.0);
				break;
			}
			else {
				rate = 0;				
			}
			
		}
		
//		System.out.println("rate@movieViewServlet="+rate);
		
		List<MovieComment> commentList = new MovieService().selectCommentList(movieCode);
		LikeList like = null;
		//좋아요 여부 보내기
		if(memberId!=null&&!memberId.equals("")) {
			like = new MovieService().selectMovieLikeOne(memberId,movieCode);
		}
		
		String view = "";
		
		if(movie != null) {
			request.setAttribute("movie", movie);
			request.setAttribute("commentList", commentList);
			request.setAttribute("like", like);
			request.setAttribute("movieNowList", movieNowList);
			request.setAttribute("rate", rate+"");
			
//			System.out.println("commentList@servlet="+commentList);
			
			if(url.indexOf("movieDescription.do")!=-1)
				view = "/WEB-INF/views/movie/movieViewDescription.jsp";
			else if(url.indexOf("movieTrailer.do")!=-1)
				view = "/WEB-INF/views/movie/movieViewTrailer.jsp";
			else if(url.indexOf("movieReview.do")!=-1)
				view= "/WEB-INF/views/movie/movieViewReview.jsp";
			
//			System.out.println("view@servlet="+view);
		}
		else {
			view = "/WEB-INF/views/common/msg.jsp";
			
			request.setAttribute("msg", "조회된 영화정보가 없습니다.");
			request.setAttribute("loc", "/movie/boxOffice");
		}
		
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package movie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import movie.model.service.MovieService;
import movie.model.vo.LikeList;
import movie.model.vo.Movie;

/**
 * Servlet implementation class MovieFinderServlet
 */
@WebServlet("/movie/find/*")
public class MovieFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("movieFinder.do")!=-1) {
			request.getRequestDispatcher("/WEB-INF/views/movie/movieFinder.jsp").forward(request, response);			
		}
		if(url.indexOf("movieFinderView.do")!=-1) {
			String searchCategory = request.getParameter("searchCategory");
			String searchKeyword = request.getParameter("searchKeyword");
			String genreArr[] = request.getParameterValues("genreArr[]");
			String genre = "";
			if(genreArr != null) genre = String.join(",", genreArr);
			String yearStart = request.getParameter("year_start");
			String yearEnd = request.getParameter("year_end");
			
			String memberId = request.getParameter("memberId");
			
			
			List<Movie> list = null;
			
			switch(searchCategory) {
			case "searchAll": list = new MovieService().selectMovieAll(searchCategory, searchKeyword); break;
			case "searchTitle": list = new MovieService().selectMovieByTitle(searchCategory, searchKeyword); break;
			case "searchDirector": list = new MovieService().selectMovieByDirector(searchCategory, searchKeyword); break;
			case "searchActor": list = new MovieService().selectMovieByActor(searchCategory, searchKeyword); break;
			}
//			System.out.println("movieList@movieFinderServlet="+list);
			
			List<Movie> listNew = new ArrayList<Movie>();
			
			if(genreArr!=null) {
				for(Movie m : list) {
					for(int i=0; i<genreArr.length; i++) {
						if(m.getGenres().contains(genreArr[i])) {
//							System.out.println(genreArr[i]+", "+m);
							listNew.add(m);
						}
					}
//					System.out.println(list);
				}				
			}
			else {
				listNew = list;
			}
			
//			System.out.println("listNew@servlet="+listNew);
			HashSet<Movie> listSet = new HashSet<Movie>(listNew);



			List<Movie> processedList = new ArrayList<Movie>(listSet);
			List<LikeList> likeList = new MovieService().selectAllLikeList(memberId);
			
			int check = 0;
			
			JSONArray jsonArray = new JSONArray();
			
			for(Movie m : processedList) {
				JSONObject jsonMovie = new JSONObject();

				jsonMovie.put("movieTitle", m.getMovieTitle());
				jsonMovie.put("runningTime", m.getRunningTime()+"");
				jsonMovie.put("movieImg",m.getRenamedFileName());
				jsonMovie.put("releaseDate", m.getReleaseDate()+"");
				jsonMovie.put("movieCode", m.getMovieCode());
				
				for(int i=0; i<likeList.size(); i++) {
					if(m.getMovieCode().equals(likeList.get(i).getMovieCode())) {
						jsonMovie.put("like", 1+"");
						check = 1;
					}
				}
				
				if(check == 0)
					jsonMovie.put("like", 0+"");
				
				jsonArray.add(jsonMovie);
				check = 0;
			}
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().append(jsonArray.toString());
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

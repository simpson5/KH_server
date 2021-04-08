package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import admin.model.service.AdminService;

/**
 * Servlet implementation class AdminSelectMovieCodeServlet
 */
@WebServlet("/admin/selectMovieCode")
public class AdminSelectMovieCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSelectMovieCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieTitle = request.getParameter("movieTitle");
		System.out.println(movieTitle);
		
		String movieCode = new AdminService().selectMovieCode(movieTitle);
		
		JSONObject jsonMovie = new JSONObject();
		jsonMovie.put("movieCode", movieCode);
		
		response.setContentType("application/json; charset=utf-8"); 
		response.getWriter().append(jsonMovie.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

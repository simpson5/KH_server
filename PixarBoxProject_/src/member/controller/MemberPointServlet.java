package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.PointList;

/**
 * Servlet implementation class MemberPointServlet
 */
@WebServlet("/member/memberPoint/*")
public class MemberPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPointServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String url = request.getRequestURL().toString();
		
		if(url.indexOf("addPoint.do")!=-1) {
			String memberId = request.getParameter("memberId");
			
			List<PointList> list = new MemberService().selectAddPointBymemberId(memberId);
			
			//view단 처리: json
			JSONArray jsonArray = new JSONArray();
			for(PointList p: list) {
				JSONObject jsonAddPoint = new JSONObject();
				jsonAddPoint.put("pointDate", p.getPointDate().toString());
				jsonAddPoint.put("movieTitle", p.getMovieTitle());
				jsonAddPoint.put("amount", p.getAmount());

				jsonArray.add(jsonAddPoint);
			}
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonArray);
		}
		else if(url.indexOf("usePoint.do")!=-1) {
			String memberId = request.getParameter("memberId");
			
			List<PointList> list = new MemberService().selectUsePointBymemberId(memberId);
			
			//view단 처리: json
			JSONArray jsonArray = new JSONArray();
			for(PointList p: list) {
				JSONObject jsonUsePoint = new JSONObject();
				jsonUsePoint.put("pointDate", p.getPointDate().toString());
				jsonUsePoint.put("movieTitle", p.getMovieTitle());
				jsonUsePoint.put("amount", p.getAmount());

				jsonArray.add(jsonUsePoint);
			}
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonArray);
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

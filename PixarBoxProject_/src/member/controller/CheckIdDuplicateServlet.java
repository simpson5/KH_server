package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/member/checkIdDuplicate")
public class CheckIdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdDuplicateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		Member m = new MemberService().selectOne(memberId);
//		boolean isUsable = m==null?true:false;
//		
//		request.setAttribute("isUsable", isUsable);
//		request.getRequestDispatcher("/WEB-INF/views/member/checkIdDuplicate.jsp").forward(request, response);
		int result = 0;
		if(m!=null) result = 1;
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonResult = new JSONObject();
		
		jsonResult.put("result", result);
		
		jsonArray.add(jsonResult);
		
		response.setContentType("applecation/json; charset=utf-8");
		response.getWriter().append(jsonArray.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

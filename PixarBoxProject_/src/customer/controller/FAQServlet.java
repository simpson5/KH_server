package customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.FAQService;
import customer.model.vo.FAQ;

/**
 * Servlet implementation class FAQServlet
 */
@WebServlet("/customer/faq/*")
public class FAQServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("faq.do")!=-1) {
			FAQService faqService = new FAQService();
			
			final int numPerPage = 5;
			int cPage = 1;
			
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {
				
			}
			
			List<FAQ> list = faqService.selectAllFaq(cPage, numPerPage);
			
			int totalContent = faqService.selectFaqCount();
			int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
			
			String pageBar = "";
			final int pageBarSize = 5;
			
			int pageStart = pageBarSize*((cPage-1)/pageBarSize)+1;
			int pageEnd = pageStart+pageBarSize-1;
			
			int pageNo = pageStart;
			
			//이전버튼
			if(pageNo != 1) {
				pageBar += "<li>";
				pageBar += "<a href='"+request.getContextPath()+"/customer/faq/faq.do?cPage="+(pageNo-1)+"'><</a>\n";
				pageBar += "</li>";
			}
			
			//pageNo섹션
			while(pageNo<=pageEnd && pageNo<=totalPage) {
				//현재페이지인 경우
				if(cPage == pageNo) {
					pageBar += "<li>";
					pageBar += "<span class='cPage'>"+pageNo+"</span>";
					pageBar += "</li>";
				}
				else {
					pageBar += "<li>";
					pageBar += "<a href='"+request.getContextPath()+"/customer/faq/faq.do?cPage="+pageNo+"'>"+pageNo+"</a>\n";
					pageBar += "</li>";
				}
				
				pageNo++;
			}
			
			//다음버튼
			if(pageNo <= totalPage) {
				pageBar += "<li>";
				pageBar += "<a href='"+request.getContextPath()+"/customer/faq/faq.do?cPage="+pageNo+"'>></a>\n";
				pageBar += "</li>";
			}
			
			request.setAttribute("list", list);
			request.setAttribute("pageBar", pageBar);
			
			request.getRequestDispatcher("/WEB-INF/views/customerService/faq.jsp").forward(request, response);
		}
		else if(url.indexOf("deleteFaq.do")!=-1) {
			
			String faqCode = request.getParameter("faqCode");
//			System.out.println("faqCode@FAQServlet="+faqCode);
			
			int result = new FAQService().deleteFaq(faqCode);
			
			String msg = "";
			String loc = "/customer/faq/faq.do";
			
			if(result>0) msg = "faq("+faqCode+")가 삭제되었습니다.";
			else msg = "faq("+faqCode+")가 삭제되지 않았습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
		}
		else if(url.indexOf("updateFaq.do")!=-1) {
			
			String faqCode = request.getParameter("faqCode");
//			System.out.println("faqCode@FAQServlet="+faqCode);
			
			FAQ f = new FAQService().selectFaq(faqCode);
			
			request.setAttribute("faq", f);
			
			request.getRequestDispatcher("/WEB-INF/views/customerService/faqUpdateForm.jsp").forward(request, response);
			
		}
		else if(url.indexOf("faqUpdateEnd.do")!=-1) {
			
			String faqCode = request.getParameter("faqCode");
			String faqType = request.getParameter("faqType");
			String faqTitle = request.getParameter("faqTitle");
			String faqContent = request.getParameter("faqContent");
			
			FAQ f = new FAQ(faqCode, faqType, faqTitle, faqContent);
			
			int result = new FAQService().updateFAQ(f);
			
			String msg = "";
			String loc = "/customer/faq/faq.do";
			
			if(result>0) msg = "자주묻는질문("+faqCode+")을 수정하였습니다.";
			else msg = "자주묻는질문("+faqCode+")을 수정하지 못했습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		}
		else if(url.indexOf("faqInsert.do")!=-1) {
			
			request.getRequestDispatcher("/WEB-INF/views/customerService/faqInsertForm.jsp").forward(request, response);
			
		}
		else if(url.indexOf("faqInsertEnd.do")!=-1) {
			
			String faqCode = request.getParameter("faqCode");
			String faqType = request.getParameter("faqType");
			String faqTitle = request.getParameter("faqTitle");
			String faqContent = request.getParameter("faqContent");
			
			FAQ f = new FAQ(faqCode, faqType, faqTitle, faqContent);
			
			int result = new FAQService().insertFAQ(f);
			
			String msg = "";
			String loc = "/customer/faq/faq.do";
			
			if(result>0) msg = "자주묻는질문("+faqCode+")을 작성하였습니다.";
			else msg = "자주묻는질문("+faqCode+")을 작성하지 못했습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
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

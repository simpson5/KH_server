package customer.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import customer.model.service.CsCommentService;
import customer.model.service.CustomerServiceService;
import customer.model.vo.CsComment;
import customer.model.vo.CustomerService;

/**
 * Servlet implementation class CustomerServiceServlet
 */
@WebServlet("/customer/customerService/*")
public class CustomerServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServiceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = request.getRequestURL().toString();
		
		if (url.indexOf("customerService.do") != -1) {
			
			final int numPerPage = 5;
			int cPage = 1;
			
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {
				
			}
			
			List<CustomerService> list = new CustomerServiceService().selectAllCustomerService(cPage, numPerPage);
			//System.out.println(list+"lllll");
			int totalContent = new CustomerServiceService().selectCustomerServiceCnt();
			int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
			//System.out.println("total="+totalContent);
			
			String pageBar = "";
			final int pageBarSize = 5;
			
			int pageStart = pageBarSize*((cPage-1)/pageBarSize)+1;
			int pageEnd = pageStart+pageBarSize-1;
			
			//System.out.println("list="+list);
			//System.out.println("totalContent="+totalContent);
			int pageNo = pageStart;
//			System.out.println("pageNo="+pageNo);
//			System.out.println("cPage="+cPage);
			//이전버튼
			if(pageNo != 1) {
				pageBar += "<li>";
				pageBar += "<a href='"+request.getContextPath()+"/customer/customerService/customerService.do?cPage="+(pageNo-1)+"'><</a>\n";
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
					pageBar += "<a href='"+request.getContextPath()+"/customer/customerService/customerService.do?cPage="+pageNo+"'>"+pageNo+"</a>\n";
					pageBar += "</li>";
				}
				
				pageNo++;
			}
			
			//다음버튼
			if(pageNo <= totalPage) {
				pageBar += "<li>";
				pageBar += "<a href='"+request.getContextPath()+"/customer/customerService/customerService.do?cPage="+pageNo+"'>></a>\n";
				pageBar += "</li>";
			}
			
			request.setAttribute("list", list);
			request.setAttribute("pageBar", pageBar);
			
			
			request.getRequestDispatcher("/WEB-INF/views/customerService/customerService.jsp").forward(request, response);


		}
		else if(url.indexOf("addCustomerService.do") != -1) {

			request.getRequestDispatcher("/WEB-INF/views/customerService/customerServiceForm.jsp").forward(request, response);
		}
		else if(url.indexOf("addCustomerServiceEnd.do") != -1) {
			//파일이 저장될 절대경로 가져오기
			String saveDirectory
				= getServletContext().getRealPath("/upload/board");// / 웹루트 WebContent
//			System.out.println("saveDirectory="+saveDirectory);
			
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
			

			String memberId = multiReq.getParameter("memberId");
			String boardTitle = multiReq.getParameter("boardTitle");
			String boardContent = multiReq.getParameter("boardContent");
			String publicYN = multiReq.getParameter("publicYN");
			String boardType = multiReq.getParameter("boardType");
			
			String originalFileName 
			= multiReq.getOriginalFileName("upFile");//사용자 업로드한 파일명
			String renamedFileName
			= multiReq.getFilesystemName("upFile");//실제 저장된 파일명
			
			CustomerService cs = new CustomerService(0,memberId,boardTitle,boardType,null,boardContent,publicYN,originalFileName,renamedFileName);
			//System.out.println(cs+"ssss");
			int result = new CustomerServiceService().insertCustomerService(cs);
			String msg = "";
			String loc = "/customer/customerService/customerService.do";
			
			if(result>0) msg = "1대1문의 등록을 하였습니다.";
			else msg = "1대1문의 등록을 실패하셨습니다";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		}
		else if(url.indexOf("customerServiceView.do") != -1) {
			
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			CustomerService cs = new CustomerServiceService().selectCustomerServiceByBoardNo(boardNo);
			//댓글 정보도 보내기
			CsComment cc = new CsCommentService().selectCsComment(boardNo);
			
			request.setAttribute("customerService", cs);
			request.setAttribute("cscomment", cc);
		
//			System.out.println("cc="+cc);
			
			request.getRequestDispatcher("/WEB-INF/views/customerService/customerServiceView.jsp").forward(request, response);
			
			
		}
		else if(url.indexOf("customerServiceUpdate.do") != -1) {
			
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			CustomerService cs = new CustomerServiceService().selectCustomerServiceByBoardNo(boardNo);
			
			request.setAttribute("customerService", cs);
			

			request.getRequestDispatcher("/WEB-INF/views/customerService/customerServiceUpdate.jsp").forward(request, response);
			
		}
		else if(url.indexOf("CustomerServiceUpdateEnd.do") != -1) {
			
			//파일이 저장될 절대경로 가져오기
			String saveDirectory
				= getServletContext().getRealPath("/upload/board");// / 웹루트 WebContent
//			System.out.println("saveDirectory="+saveDirectory);
			
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
			
			
			
			//1.parameter handling
			int boardNo = Integer.parseInt(multiReq.getParameter("boardNo"));
			String memberId = multiReq.getParameter("memberId");
			String boardTitle = multiReq.getParameter("boardTitle");
			String boardContent = multiReq.getParameter("boardContent");
			String publicYN = multiReq.getParameter("publicYN");
			String boardType = multiReq.getParameter("boardType");
		
			
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
//					System.out.println("기존첨부파일삭제: "+(result?"성공!":"실패!"));
				}
				//신규첨부파일이 없는 경우: 기존파일 삭제
				else if(multiReq.getParameter("delFileChk")!=null) {
					File delFile = new File(saveDirectory, oldRenamedFileName);
					boolean result = delFile.delete();
//					System.out.println("기존첨부파일삭제: "+(result?"성공!":"실패!"));				
				}
				//신규첨부파일이 없는 경우: 기존파일 유지
				else {
					originalFileName = oldOriginalFileName;
					renamedFileName = oldRenamedFileName;
				}
			}
			CustomerService c = new CustomerService(boardNo,memberId,boardTitle,boardType,null,boardContent,publicYN,originalFileName,renamedFileName);
			
			
			//2.business logic
			int result = new CustomerServiceService().updateCustomerService(c);
			
			String msg = "";
			String loc = "/customer/customerService/customerServiceView.do?boardNo="+boardNo;
			
			if(result>0) {
				msg = "1대1 문의 글 수정 성공!";
			}
			else {
				msg = "1대1 문의 글 수정 실패! 관리자에게 문의하세요.";
			}
			
			
			//3.view단 처리
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			

			
		}
		else if(url.indexOf("customerServiceDelete.do") != -1) {
			
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			int result = new  CustomerServiceService().deleteCustomerService(boardNo);
			
			String msg = "";
			String loc = "/customer/customerService/customerService.do";
			
			if(result>0) {
				msg = "1대1 문의 글 삭제 성공!";
			}
			else {
				msg = "1대1 문의 글 삭제 실패! 관리자에게 문의하세요.";
			}
			
			
			//3.view단 처리
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			
		}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

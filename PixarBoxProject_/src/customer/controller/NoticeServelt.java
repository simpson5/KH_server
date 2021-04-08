package customer.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import customer.model.service.NoticeService;
import customer.model.vo.Notice;

/**
 * Servlet implementation class CustomerServiceServelt
 */
@WebServlet("/customer/notice/*")
public class NoticeServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if(url.indexOf("notice.do")!=-1) {
			NoticeService noticeService = new NoticeService();
			
			final int numPerPage = 10;
			int cPage = 1;
			
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {
				
			}
			
			//a. 컨텐츠영역
			List<Notice> list = noticeService.selectNoticeList(cPage, numPerPage);

			//b. 페이징바영역
			int totalContent = noticeService.selectNoticeCount();
			int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
			
			String pageBar = "";
			final int pageBarSize = 5;
			
			int pageStart = pageBarSize*((cPage-1)/pageBarSize)+1;
			int pageEnd = pageStart+pageBarSize-1;
			
			//증감변수
			int pageNo = pageStart;
			
			//이전버튼
			if(pageNo != 1) {
				pageBar += "<li>";
				pageBar += "<a href='"+request.getContextPath()+"/customer/notice/notice.do?cPage="+(pageNo-1)+"'><</a>\n";
				pageBar += "</li>";
			}
			
			//pageNo섹션
			while(pageNo<=pageEnd && pageNo<=totalPage) {
				//현재페이지인 경우
				if(cPage == pageNo) {
					pageBar += "<li>";
					pageBar += "<span class='cPage'>"+pageNo+"</span>\n";
					pageBar += "</li>";
				}
				else {
					pageBar += "<li>";
					pageBar += "<a href='"+request.getContextPath()+"/customer/notice/notice.do?cPage="+pageNo+"'>"+pageNo+"</a>\n";
					pageBar += "</li>";
				}
				
				pageNo++;
			}
			
			//다음버튼
			if(pageNo <= totalPage) {
				pageBar += "<li>";
				pageBar += "<a href='"+request.getContextPath()+"/customer/notice/notice.do?cPage="+pageNo+"'>></a>\n";
				pageBar += "</li>";
			}
			
			request.setAttribute("list", list);
			request.setAttribute("pageBar", pageBar);
			
			request.getRequestDispatcher("/WEB-INF/views/customerService/notice.jsp").forward(request, response);
		}
		else if(url.indexOf("noticeView.do")!=-1) {
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			
			Notice n = new NoticeService().selectNotice(noticeNo);
			
			String view = "";
			
			if(n!=null) {
				view = "/WEB-INF/views/customerService/noticeView.jsp";
				request.setAttribute("notice", n);
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp";
				
				request.setAttribute("msg", "조회된 공지가 없습니다.");
				request.setAttribute("loc", "/notice/notice.do");
			}
			
			request.getRequestDispatcher(view).forward(request, response);
		}
		else if(url.indexOf("noticeForm.do")!=-1) {
			
			request.getRequestDispatcher("/WEB-INF/views/customerService/noticeForm.jsp").forward(request, response);
		}
		else if(url.indexOf("noticeInsert.do")!=-1) {
			
			String saveDirectory = getServletContext().getRealPath("/upload/notice");
			
			int maxPostSize = 1024 * 1024 * 10;
			
			FileRenamePolicy mvcFileRenamePolicy = new MvcFileRenamePolicy();
			
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", mvcFileRenamePolicy);
			
//			String noticeTitle = request.getParameter("noticeType")+" "+request.getParameter("noticeTitle");
//			String noticeContent = request.getParameter("noticeContent");
			
			String noticeTitle = multiReq.getParameter("noticeType")+" "+multiReq.getParameter("noticeTitle");
			String noticeContent = multiReq.getParameter("noticeContent");
			
			noticeContent = noticeContent.replaceAll("<", "&lt;")
					   					 .replaceAll(">", "&gt;")
					   					 .replaceAll("\\n", "<br/>");
			
			String originalFileName = multiReq.getOriginalFileName("upFile");
			String renamedFileName = multiReq.getFilesystemName("upFile");
			
			Notice n = new Notice(0, noticeTitle, noticeContent, originalFileName, renamedFileName, null);
			
			int result = new NoticeService().insertNotice(n);
			
			String msg = "";
			String loc = "/customer/notice/notice.do";
			
			if(result>0) msg = "공지사항이 등록되었습니다.";
			else msg = "공지사항 등록을 실패하였습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
		}
		else if(url.indexOf("noticeUpdate.do")!=-1) {
			
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
//			System.out.println("noticeNo@noticeUpdate.do="+noticeNo);
			
			Notice n = new NoticeService().selectNotice(noticeNo);
			
			request.setAttribute("notice", n);
			request.getRequestDispatcher("/WEB-INF/views/customerService/noticeUpdateForm.jsp").forward(request, response);
			
			
		}
		else if(url.indexOf("noticeUpdateEnd.do")!=-1) {
			
			String saveDirectory = getServletContext().getRealPath("/upload/notice");
			
			int maxPostSize = 1024 * 1024 * 10;
			
			FileRenamePolicy mvcFileRenamePolicy = new MvcFileRenamePolicy();
			
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", mvcFileRenamePolicy);
			
			int noticeNo = Integer.parseInt(multiReq.getParameter("noticeNo"));
			String noticeTitle = multiReq.getParameter("noticeType")+" "+multiReq.getParameter("noticeTitle");
			String noticeContent = multiReq.getParameter("noticeContent");
			String originalFileName = multiReq.getOriginalFileName("upFile");
			String renamedFileName = multiReq.getFilesystemName("upFile");
			
			String oldOriginalFileName = multiReq.getParameter("oldOriginalFileName");
			String oldRenamedFileName = multiReq.getParameter("oldRenamedFileName");
			
			if(!"".equals(oldOriginalFileName)) {
				File f = multiReq.getFile("upFile");
				
				if(f!=null) {
					File delFile = new File(saveDirectory, oldRenamedFileName);
					boolean result = delFile.delete();
				}
				else if(multiReq.getParameter("delFileChk")!=null) {
					File delFile = new File(saveDirectory, oldRenamedFileName);
					boolean result = delFile.delete();
				}
				else {
					originalFileName = oldOriginalFileName;
					renamedFileName = oldRenamedFileName;
				}
			}
			
			Notice n = new Notice(noticeNo, noticeTitle, noticeContent, originalFileName, renamedFileName, null);
			int result = new NoticeService().updateNotice(n);
			
			String msg = "";
			String loc = "/customer/notice/noticeView.do?noticeNo="+noticeNo;
			if(result>0) msg = "공지사항을 수정하였습니다.";
			else msg = "공지사항을 수정하지 못했습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
		}
		else if(url.indexOf("noticeDelete.do")!=-1) {
			
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			String renamedFileName = request.getParameter("renamedFileName");
			
			int result = new NoticeService().deleteNotice(noticeNo);
			
			if(result>0 && !"".equals(renamedFileName)) {
				String saveDirectory = getServletContext().getRealPath("/upload/notice");
				
				File delFile = new File(saveDirectory, renamedFileName);
				
				String delDirectory = getServletContext().getRealPath("/delete/notice");
				File delFileTo = new File(delDirectory, renamedFileName);
				boolean bool = delFile.renameTo(delFileTo);
			}
			
			String msg = "";
			String loc = "/customer/notice/notice.do";
			
			if(result>0) msg = "공지사항("+noticeNo+")을 삭제하였습니다.";
			else msg = "공지사항("+noticeNo+")을 삭제하지 못했습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		}
		else if(url.indexOf("fileDownload.do")!=-1) {
			
			String rName = request.getParameter("rName");
			String oName = request.getParameter("oName");
			
			String saveDirectory = getServletContext().getRealPath("/upload/notice");
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(saveDirectory+File.separator+rName));
			ServletOutputStream sos = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(sos);
			
			String resFileName = "";
			
			boolean isMSIE = request.getHeader("user-agent").indexOf("MSIE")!=-1 || request.getHeader("user-agent").indexOf("Trident")!=-1;
			
			if(isMSIE) {
				resFileName = URLEncoder.encode(oName, "utf-8");
				
				resFileName = resFileName.replace("\\+", "%20");
			}
			else {
				resFileName = new String(oName.getBytes("utf-8"), "iso-8859-1");
			}
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+resFileName);
			
			int read = -1;
			while((read=bis.read()) != -1) {
				bos.write(read);
			}
			bos.close();
			bis.close();
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

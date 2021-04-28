package com.simp.board.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simp.board.model.service.BoardService;
import com.simp.board.model.vo.Review;

/**
 * Servlet implementation class ReviewUpdateServlet
 */
@WebServlet("/review/update")
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 입력값
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 업무로직
		Review review = boardService.selectOne(no);
		
		// jsp 위임
		request.setAttribute("review", review);
		request.getRequestDispatcher("/WEB-INF/views/review/review_update.jsp")
				.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			// 1. multipartRequest 객체 생성
			// /WebContent/upload/review/업로드파일명.jpg
			// 저장할 객체가 필요 saveDirectory
			// web root dir 를 절대경로로 반환
			String saveDirectory = getServletContext().getRealPath("/upload/review");
			System.out.println("saveDirectory@serlvet = " + saveDirectory);
			
			// 최대 파일 허용 크기 10MB == 10 * 1kb * 1kb byte 단위
			int maxPostSize = 10 * 1024 * 1024;
			// 인코딩
//			String encoding = "utf-8";
			request.setCharacterEncoding("utf-8");
			//	파일명 변경 정책 객체
			// 종복파일인 경우, numbering 처리
//			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			
			//사용자 입력값
			int no = Integer.parseInt(request.getParameter("no")); // form 의 name 값
			
			System.out.println("no@servlet = "+no);
			Date date = new Date(System.currentTimeMillis());
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Date reviewDate = date;
			
			Review review = new Review();
			review.setReviewNo(no);
			review.setTitle(title);
			review.setContent(content);
			review.setReviewDate(reviewDate);
			
			System.out.println("review_post@servlet = " + review);
			
			int result = boardService.updateReview(review);
			
			String msg = (result > 0) ? 
					"게시글 수정성공!" :
						"게시글 수정 실패!";
			System.out.println(msg);
			String location = request.getContextPath()
					+ "/review/board?no=" + review.getReviewNo() ;
			
			//3. DML요청 : 리다이렉트 & 사용자피드백
			// /mvc/board/boardList
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(location);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

}

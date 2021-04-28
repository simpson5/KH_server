package com.simp.board.model.service;

import java.sql.Connection;



import java.util.List;

import com.simp.board.model.vo.Review;
import com.simp.board.model.vo.ReviewComment;
import com.simp.board.model.dao.BoardDao;


import static common.JDBCTemplate.*;




public class BoardService {
	private BoardDao boardDao = new BoardDao();

	public List<Review> selectList() {
		Connection conn = getConnection();
		List<Review> list = boardDao.selectList(conn);
		close(conn);
		return list;
	}
	
	public List<Review> selectListId(String memberId) {
		Connection conn = getConnection();
		List<Review> list = boardDao.selectListId(conn, memberId);
		close(conn);
		return list;
	}
	
	public List<Review> selectList(int start, int end, String movieTitle) {
		Connection conn = getConnection();
		List<Review> list = boardDao.selectList(conn, start, end, movieTitle);
		close(conn);
		return list;
	}
	public List<Review> selectList(int start, int end) {
		Connection conn = getConnection();
		List<Review> list = boardDao.selectList(conn, start, end);
		close(conn);
		return list;
	}
	public Review selectOne(int no) {
		Connection conn = getConnection();
		Review review = boardDao.selectOne(conn, no);
		close(conn);
		return review;
	}

	public int updateReview(Review review) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. board update
			result = boardDao.updateReview(conn,review);
		
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return  result;
	}
	public int selectReviewCount(String movieTitle) {
		Connection conn = getConnection();
		int totalContents = boardDao.selectReviewCount(conn, movieTitle);
		close(conn);
		return totalContents;
	}
	public int selectReviewCount() {
		Connection conn = getConnection();
		int totalContents = boardDao.selectReviewCount(conn);
		close(conn);
		return totalContents;
	}
	public List<Review> selectSearchList(int start, int end,String type, String userSearch) {
		Connection conn = getConnection();
		List<Review> list = boardDao.selectSearchList(conn, start, end, type, userSearch);
		close(conn);
		return list;
	}
	public int selectSearchCount(String type, String userSearch) {
		Connection conn = getConnection();
		int totalContents = boardDao.selectSearchCount(conn, type, userSearch);
		close(conn);
		return totalContents;
	}
	public int insertBoard(Review review) {
		Connection conn = getConnection();
		int result = boardDao.insertBoard(conn, review);
		int reviewNo = boardDao.selectLastBoardNo(conn);
		System.out.println(reviewNo);
		review.setReviewNo(reviewNo);
		close(conn);
		return result;
	}
	public String RecomUp(int r_no, String member_id) {
		Connection conn = getConnection();
		String result = "";
		try {
			// 1. board update
			result = boardDao.RecomUp(conn,r_no,member_id);
		
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return  result;
	}
	public int insertReply(int r_no, String member_id, String r_content) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. board update
			result = boardDao.insertReply(conn,r_no,member_id,r_content);
		
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return  result;
	}
	public List<ReviewComment> ListComment(int start, int end, int no) {
	      Connection conn = getConnection();
	      List<ReviewComment> list = boardDao.ListComment(conn, start, end, no);
	      close(conn);
	      return list;
	   }

	public List<Review> bestReview(String movieTitle) {
		Connection conn = getConnection();
		List<Review> list = boardDao.bestReview(conn, movieTitle);
		close(conn);
		return list;
	}
	
	public int deleteReply(int no) {
	      Connection conn = getConnection();
	      int result = 0;
	      try {
	         // 1. board update
	         result = boardDao.deleteReply(conn, no);
	         System.out.println("서비스 리뷰 삭제 : " + result);
	      
	         commit(conn);
	      }catch(Exception e) {
	         rollback(conn);
	         throw e;
	      }
	      return  result;
	   }
	
	public int selectCommentCount(int no) {
	      Connection conn = getConnection();
	      int totalContents = boardDao.selectCommentCount(conn, no);
	      close(conn);
	      return totalContents;
	   }
	
}

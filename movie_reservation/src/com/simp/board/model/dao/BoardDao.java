package com.simp.board.model.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.simp.board.model.vo.Review;
import com.simp.board.model.vo.ReviewComment;

public class BoardDao {
	public BoardDao() {
		super();
	}

	public List<Review> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from review";
		List<Review> list = null;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			System.out.println(1);
			list = new ArrayList<>();
			while (rset.next()) {
				Review r = new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setWriter(rset.getString("writer"));
				r.setMovieTitle(rset.getString("movie_title"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("content"));
				r.setReadCnt(rset.getInt("read_cnt"));
				r.setRecomCnt(rset.getInt("recom_cnt"));
				r.setReviewDate(rset.getDate("review_date"));
				r.setReviewStatus(rset.getString("review_status"));

				// System.out.println("r@dao" + r);

				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public List<Review> selectListId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from review where writer = ?";
		List<Review> list = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			System.out.println(1);
			list = new ArrayList<>();
			while (rset.next()) {
				Review r = new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setWriter(rset.getString("writer"));
				r.setMovieTitle(rset.getString("movie_title"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("content"));
				r.setReadCnt(rset.getInt("read_cnt"));
				r.setRecomCnt(rset.getInt("recom_cnt"));
				r.setReviewDate(rset.getDate("review_date"));
				r.setReviewStatus(rset.getString("review_status"));

				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public List<Review> selectList(Connection conn, int start, int end, String movieTitle) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select row_number() over(order by review_date desc) rnum, R.* from review R where movie_title like ?) R where rnum between ? and ?";
		List<Review> list = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + movieTitle + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while (rset.next()) {
				Review r = new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setWriter(rset.getString("writer"));
				r.setMovieTitle(rset.getString("movie_title"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("content"));
				r.setReadCnt(rset.getInt("read_cnt"));
				r.setRecomCnt(rset.getInt("recom_cnt"));
				r.setReviewDate(rset.getDate("review_date"));
				r.setReviewStatus(rset.getString("review_status"));

				// System.out.println("r@dao" + r);

				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public List<Review> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select row_number() over(order by review_date desc) rnum, R.* from review R ) R where rnum between ? and ?";
		List<Review> list = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while (rset.next()) {
				Review r = new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setWriter(rset.getString("writer"));
				r.setMovieTitle(rset.getString("movie_title"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("content"));
				r.setReadCnt(rset.getInt("read_cnt"));
				r.setRecomCnt(rset.getInt("recom_cnt"));
				r.setReviewDate(rset.getDate("review_date"));
				r.setReviewStatus(rset.getString("review_status"));

				// System.out.println("r@dao" + r);

				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public Review selectOne(Connection conn, int no) {
		Review r = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from review where review_no = ?";
		String query2 = "update review set read_cnt = read_cnt + 1 where review_no = ?";

		try {
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1, no);
			int result = pstmt.executeUpdate();
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문미완성
			pstmt.setInt(1, no);
			// 쿼리문실행
			// 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();

			if (rset.next()) {
				r = new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setWriter(rset.getString("writer"));
				r.setMovieTitle(rset.getString("movie_title"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("content"));
				r.setReadCnt(rset.getInt("read_cnt"));
				r.setRecomCnt(rset.getInt("recom_cnt"));
				r.setReviewDate(rset.getDate("review_date"));
				r.setReviewStatus(rset.getString("review_status"));

			}
		} catch (Exception e) {
			// 에러는 여기서 찍는게 아님
//			throw new BoardException("게시글 조회 오류", e);
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}

	public int updateReview(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update review set title = ?, content = ?, review_date = ? where review_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getTitle());
			pstmt.setString(2, review.getContent());
			pstmt.setDate(3, review.getReviewDate());
			pstmt.setInt(4, review.getReviewNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
//			throw new BoardException("게시물 수정 오류", e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int selectReviewCount(Connection conn, String movieTitle) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from review where movie_title=?";
		int totalContents = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, movieTitle);
			rset = pstmt.executeQuery();

			while (rset.next()) {

				totalContents = rset.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int selectReviewCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from review";
		int totalContents = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {

				totalContents = rset.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public List<Review> selectSearchList(Connection conn, int start, int end, String type, String userSearch) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if (type.equals("movie_title")) {
			query = "select * from (select row_number() over(order by review_date desc) rnum, R.* from ( select * from review where movie_title like ? ) R ) where rnum between ? and ?";

		} else {
			query = "select * from (select row_number() over(order by review_date desc) rnum, R.* from ( select * from review where writer like ? ) R ) where rnum between ? and ?";
		}
		List<Review> list = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + userSearch + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while (rset.next()) {
				Review r = new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setWriter(rset.getString("writer"));
				r.setMovieTitle(rset.getString("movie_title"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("content"));
				r.setReadCnt(rset.getInt("read_cnt"));
				r.setRecomCnt(rset.getInt("recom_cnt"));
				r.setReviewDate(rset.getDate("review_date"));
				r.setReviewStatus(rset.getString("review_status"));

				// System.out.println("r@dao" + r);

				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int selectSearchCount(Connection conn, String type, String userSearch) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if (type.equals("movie_title")) {
			query = "select count(*) cnt from (select row_number() over(order by review_date desc) rnum, R.* from ( select * from review where movie_title like ? ) R ) ";
		} else {
			query = "select count(*) cnt from (select row_number() over(order by review_date desc) rnum, R.* from ( select * from review where writer like ? ) R ) ";
		}
		int totalContents = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + userSearch + "%");
			rset = pstmt.executeQuery();

			while (rset.next()) {
				totalContents = rset.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int insertBoard(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into review(review_no, writer, movie_title, title, content) values(seq_review_no.nextval, ?,?, ?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getWriter());
			pstmt.setString(2, review.getMovieTitle());
			pstmt.setString(3, review.getTitle());
			pstmt.setString(4, review.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int selectLastBoardNo(Connection conn) {
		int reviewNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select seq_review_no.currval review_no from dual";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				reviewNo = rset.getInt("review_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return reviewNo;
	}

	public String RecomUp(Connection conn, int r_no, String member_id) {
		PreparedStatement pstmt = null;
		String result = "중복추천입니다";
		// 중복 추천 검사
		String sql1 = "insert into recom_check values(?, ?)";
		// 추천수 증가
		String sql = "update review set recom_cnt = recom_cnt + 1 where review_no = ?";
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, r_no);
			pstmt.setString(2, member_id);
			if (pstmt.executeUpdate() != 0) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, r_no);
				if (pstmt.executeUpdate() != 0) {
					result = "추천수 증가";
				}
			} else {
				return result = "중복추천입니다";
			}
		} catch (SQLException e) {
//			throw new BoardException("게시물 수정 오류", e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertReply(Connection conn, int r_no, String member_id, String r_content) {
		PreparedStatement pstmt = null;
		int result = 0;
		// 중복 추천 검사
		String sql = "insert into review_comment(comment_no, comment_writer ,review_no ,content) values(seq_com_no.nextVal,?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, r_no);
			pstmt.setString(3, r_content);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
//			throw new BoardException("게시물 수정 오류", e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<ReviewComment> ListComment(Connection conn,int start, int end, int no) {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = "select * from (select row_number() over(order by reg_date desc) rnum, R.* from ( select * from review_comment where review_no = ? ) R ) where rnum between ? and ?";
	   
	      List<ReviewComment> list = null;
	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, no);
	         pstmt.setInt(2, start);
	         pstmt.setInt(3, end);
	         rset = pstmt.executeQuery();
	         list = new ArrayList<>();
	         while(rset.next()) {
	            ReviewComment r = new ReviewComment();
	            r.setReviewNo(rset.getInt("review_no"));
	            r.setCommentWriter(rset.getString("comment_writer"));
	            r.setContent(rset.getString("content"));
	            r.setCommentNo(rset.getInt("comment_no"));
	            r.setRegDate(rset.getDate("reg_date"));
	            //System.out.println("r@dao" + r);
	            
	            list.add(r);
	         }
	   
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(rset);
	         close(pstmt);
	      }
	      
	      return list;
	   }
	
	public List<Review> bestReview(Connection conn, String movieTitle) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum rnum, r.* from review r where movie_title = ? order by read_cnt + recom_cnt*10 desc) where rnum between 1 and 5";
		List<Review> list = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, movieTitle);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while (rset.next()) {
				Review r = new Review();
				r.setReviewNo(rset.getInt("review_no"));
				r.setWriter(rset.getString("writer"));
				r.setMovieTitle(rset.getString("movie_title"));
				r.setTitle(rset.getString("title"));
				r.setContent(rset.getString("content"));
				r.setReadCnt(rset.getInt("read_cnt"));
				r.setRecomCnt(rset.getInt("recom_cnt"));
				r.setReviewDate(rset.getDate("review_date"));
				r.setReviewStatus(rset.getString("review_status"));
				
				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	
	public int deleteReply(Connection conn, int no) {
	      PreparedStatement pstmt = null;
	      int result = 0;
	      //중복 추천 검사
	      String sql = "delete from review_comment where comment_no = ?";
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, no);   
	         result = pstmt.executeUpdate();
	      } catch (SQLException e) {
//	         throw new BoardException("게시물 수정 오류", e);
	         e.printStackTrace();
	      } finally {
	         close(pstmt);
	      }
	      
	      return result;
	   }

	
	public int selectCommentCount(Connection conn, int no) {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = "select count(*) cnt from review_comment where review_no=?";
	      int totalContents = 0;
	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, no);
	         rset = pstmt.executeQuery();
	         
	         while(rset.next()) {
	            
	            totalContents = rset.getInt("cnt"); 
	         }
	   
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(rset);
	         close(pstmt);
	      }
	      return totalContents;
	   }
}

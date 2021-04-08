package board.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import board.model.exception.BoardException;
import board.model.vo.Attachment;
import board.model.vo.Board;
import member.model.exception.MemberException;
import member.model.vo.Member;

public class BoardDao {

	private Properties prop = new Properties();
	
	public BoardDao() {
		//board-query.properties의 내용읽어와서 prop에 저장
		//resources/sql/board-query.properties가 아니라
		//WEB-INF/classes/sql/board-query.properties에 접근해야함.
//		String fileName = this.getClass() // 클래스 객체
//				.getResource("/sql/member/board-query.properties") // Url 객체
//				.getPath(); // String 객체 : 절대경로
		String fileName = BoardDao.class
								  .getResource("/sql/board/board-query.properties")
								  .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//전체 게시판 조회
	public List<Board> selectList(Connection conn, int start, int end) {
		List<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectPagedList");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 미완성 쿼리문 값대입
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			// 쿼리문실행
			rset = pstmt.executeQuery();

			// 전체조회는 while 문으로
			while (rset.next()) {
				int no = rset.getInt("no");
				String title = rset.getString("title");
				String writer = rset.getString("writer");
				String content = rset.getString("content");
				Date regDate = rset.getDate("reg_date");
				int readCount = rset.getInt("read_count");
				
				System.out.println(rset.getInt("attach_no"));
				//첨부파일이 있는 경우
				Attachment attach = null;
				
				if(rset.getInt("attach_no") != 0) {
					attach = new Attachment();
					attach.setNo(rset.getInt("attach_no"));
					attach.setBoardNo(rset.getInt("no"));
					attach.setOriginalFileName(rset.getString("original_filename"));
					attach.setRenamedFileName(rset.getString("renamed_filename"));
					attach.setStatus("Y".equals(rset.getString("status")) ? true : false);
				}
				
				Board board = new Board(no, title, writer, content, regDate, readCount, attach);
				
				list.add(board);
				
			}
		} catch (SQLException e) {
			throw new MemberException("전체 게시판 조회", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	//전체 게시판 수
	public int countBoardList(Connection conn) {
		int totalContens = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("countBoardList");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 미완성 쿼리문 값대입
			// 쿼리문실행
			rset = pstmt.executeQuery();

			if (rset.next()) {
				totalContens = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			throw new BoardException("전체 게시판 수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContens;
	}

	//게시글 등록
	public int boardEnroll(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("boardEnroll");
		try {
			// 3. PreparedStatement 객체 생성 (미완성 쿼리)
			// 3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());

			// 4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			// 4.1 ResultSet -> Java 객체 옮겨담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시글 등록 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		return result;
	}

	public int selectLastBoardNo(Connection conn) {
		int boardNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLastBoardNo");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 미완성 쿼리문 값대입
			// 쿼리문실행
			rset = pstmt.executeQuery();

			if (rset.next()) {
				boardNo = rset.getInt("board_no");
			}
		} catch (SQLException e) {
			throw new BoardException("게시물 등록 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try {
			// 3. PreparedStatement 객체 생성 (미완성 쿼리)
			// 3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFileName());
			pstmt.setString(3, attach.getRenamedFileName());

			// 4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			// 4.1 ResultSet -> Java 객체 옮겨담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시물 첨부파일 등록 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		return result;
	}

	//게시물 뷰
	public Board selectOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		Board board = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				String title = rset.getString("title");
				String writer = rset.getString("writer");
				String content = rset.getString("content");
				Date regDate = rset.getDate("reg_date");
				int readCount = rset.getInt("read_count") + 1;
				
				board = new Board(no, title, writer, content, regDate, readCount, null);
			}
		} catch(SQLException e) {
			throw new BoardException("게시물 조회 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		
		return board;
	}

	public Attachment findAttach(Connection conn, int no) {
		PreparedStatement pstmt = null;
		Attachment attach = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findAttach");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("board_no"));
				attach.setOriginalFileName(rset.getString("original_filename"));
				attach.setRenamedFileName(rset.getString("renamed_filename"));
				attach.setStatus("Y".equals(rset.getString("status")) ? true : false);
			}
		} catch(SQLException e) {
			throw new BoardException("첨부파일 조회 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		
		return attach;
	}

	//조회수 증가
	public int increaseReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("increaseReadCount");
		try {
			// 3. PreparedStatement 객체 생성 (미완성 쿼리)
			// 3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			// 4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			// 4.1 ResultSet -> Java 객체 옮겨담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("조회수 증가 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		return result;
		
	}

	public int selectLastBoardNo2(Connection conn) {
		int boardNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLastBoardNo2");
		System.out.println(query);

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 미완성 쿼리문 값대입
			// 쿼리문실행
			rset = pstmt.executeQuery();

			//db에서 어떤 오류가 났을까?
			if (rset.next()) {
				boardNo = rset.getInt("last_number") - 9;
				System.out.println("boardNo@dao = " + boardNo);
			}
		} catch (SQLException e) {
			throw new BoardException("게시물 등록 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}

	public int boardDelete(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("boardDelete");
		try {
			// 3. PreparedStatement 객체 생성 (미완성 쿼리)
			// 3.1 ? 값 대임
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			// 4. 실행 : DML(executeUpdate) -> int, DQL(excuteQuery) -> ResultSet
			// 4.1 ResultSet -> Java 객체 옮겨담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시판 삭제 오류", e);
		} finally {
			// 5. 자원반납(생성역순 rset - pstmt)
			close(pstmt);
		}
		return result;
	}

	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시판 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
}

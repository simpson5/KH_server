package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import board.conroller.BoardEnrollServlet;
import board.model.dao.BoardDao;
import board.model.vo.Attachment;
import board.model.vo.Board;

public class BoardService {

	private BoardDao boardDao = new BoardDao();

	public List<Board> selectList(int start, int end) {
		Connection conn = getConnection();
		List<Board> list = boardDao.selectList(conn, start, end);
		close(conn);
		return list;
	}

	public int countBoardList() {
		Connection conn = getConnection();
		int totalContens = boardDao.countBoardList(conn);
		close(conn);
		return totalContens;
	}

	/**
	 * 첨부파일 있는 경우, attach객체를 attachment테이블에 등록한다. - board등록, attachment등록은 하나의
	 * 트랜잭셕으로 처리되어야한다. - 하나의 Connection에 의해 처리되어야한다.
	 * 
	 * @param board
	 * @param bns
	 * @return
	 */
	public int boardEnroll(Board board) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.boardEnroll(conn, board);

			// 생성된 board_no를 가져오기
			// dao에서 한번에 가져오긴느 못하나? +1로
			int boardNo = boardDao.selectLastBoardNo(conn);
			System.out.println("boardNo@Servic = " + boardNo);

			// 보드 넘버 저장
			board.setNo(boardNo);

			if (board.getAttach() != null) {
				// 참조할 boardNo세팅
				System.out.println("null 검사");
				board.getAttach().setBoardNo(boardNo);
				result = boardDao.insertAttachment(conn, board.getAttach());
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Board selectOne(int no) {
		Connection conn = getConnection();
		Board board = boardDao.selectOne(conn, no);
		Attachment attach = boardDao.findAttach(conn, no);
		if (attach != null) {
			board.setAttach(attach);
		}
		// 조회수 증가
//		int result = boardDao.increaseReadCount(conn, no);
//		if(result > 0) {
//			commit(conn);
//		}
//		else {
//			rollback(conn);
//		}
		close(conn);
		return board;
	}

	/**
	 * board_no로 attach 행 조회
	 * 
	 * @param no
	 * @return
	 */
	public Attachment selectOneAttachment(int no) {
		Connection conn = getConnection();
		Attachment attach = boardDao.findAttach(conn, no);
		close(conn);
		return attach;
	}

	public int boardDelete(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.boardDelete(conn, no);
			if(result == 0)
				throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. :" + no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller가 예외처리를 결정할 수 있도록 넘김.
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateBoard(Board board) {
		Connection conn = getConnection();
		int result = 0;
		try {
			//1.board update
			result = boardDao.updateBoard(conn, board);
			//2. attachement insert
			if(board.getAttach() != null)
				result = boardDao.insertAttachment(conn, board.getAttach());
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}
}

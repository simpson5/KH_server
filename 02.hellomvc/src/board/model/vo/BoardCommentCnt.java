package board.model.vo;

import java.sql.Date;

public class BoardCommentCnt extends Board {
	int commentCnt;

	public BoardCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public BoardCommentCnt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardCommentCnt(int no, String title, String writer, String content, Date regDate, int readCount,
			Attachment attach) {
		super(no, title, writer, content, regDate, readCount, attach);
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
}

package movie.model.vo;

public class LikeList {
	
	private int likeNo;
	private String memberId;
	private String movieCode;
	
	public LikeList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeList(int likeNo, String memberId, String movieCode) {
		super();
		this.likeNo = likeNo;
		this.memberId = memberId;
		this.movieCode = movieCode;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}
	
	@Override
	public String toString() {
		return "LikeList [likeNo=" + likeNo + ", memberId=" + memberId + ", movieCode=" + movieCode + "]";
	}
	
	

}

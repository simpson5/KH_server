package common;

public class MvcUtils {
	public static String getPageBar(int cPage, int numPerPage, int totalContents, String url) {
		//메모리 상에서 좀더 이득
		StringBuilder pageBar = new StringBuilder();
		int totalPage = (int)Math.ceil((double)totalContents / numPerPage);
		int pageBarSize = 10;
		//cPage속성 추가전 키워드 작업
		//cPage이외의 다른 사용자 입력값이 있는 경우 대비
		// /mvc/admin/memberFinder?type=id
		url = url.indexOf("?") > -1 ? url + "&" : url + "?";
		
		/**
		 * 1 2 3 4 5
		 * 6 7 8 9 10
		 * 11 12 13 14 15
		 * ...
		 */
		int pageStart = (cPage - 1) / pageBarSize * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;
		//증감변수는 pageStart부터 시작
		int pageNo = pageStart;
		//1. 이전
		if(pageNo == 1) {
			
		}
		else {
			pageBar.append("<a href='" + url + "cPage=" + (pageNo - 1) + "'/>prev</a>\n");
		}
		
		//2. pageNo
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cPage) {
				pageBar.append("<span class='cPage'>" + pageNo + "</span>\n");
			}
			else {
				pageBar.append("<a href='" + url + "cPage=" + pageNo + "'/>" + pageNo + "</a>\n");				
			}
			pageNo++;
		}
		
		//3. 다음
		if(pageNo > totalPage) {
			
		}
		else {
			pageBar.append("<a href='" + url + "cPage=" + pageNo + "'/>next</a>\n");
		}
		
		return pageBar.toString();
	}

	public static String convertLinFeedToBr(String content) {
		return content.replaceAll("\\n", "<br/>");
	}

	public static String escapeHtml(String str) {
		return str.replaceAll("<", "&lt;")
				  .replaceAll(">", "&gt;");
	}
}

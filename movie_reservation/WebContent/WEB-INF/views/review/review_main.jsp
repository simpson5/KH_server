<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@page import="com.simp.product.model.vo.PaymentHistory"%>
<%@page import="java.util.List"%>
<%@page import="com.simp.board.model.vo.Review"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/review_main.css" />
<%
	List<Review> review = (List<Review>)request.getAttribute("list");
	ScreenMovie sm = (ScreenMovie)request.getAttribute("screenMovie"); // 여기가 null 이면 오류?
	List<PaymentHistory> payList = (List<PaymentHistory>)request.getAttribute("payList");
	String userSearch = (String)request.getAttribute("userSearch");
	String type = (String)request.getAttribute("type");
%>
<style>
   .header_common {
      color : black;
      position : static;
   }
   
   .header_common a {
      color : black;
   }
   
   .scroll_menu {
      margin-top : -95px;
   }
	#search-writer{display: <%= type==null || "writer".equals(type) ? "inline-block" : "none" %>}
    #search-movie_title{display : <%= "movie_title".equals(type) ? "inline-block" : "none" %>}
</style>
<article>
	<% if(sm != null) {%>
	<h4>영화 리뷰</h4>
	<div class="choice_movie_info">
		<button class="ticketing_btn" onclick="reservation();" style="z-index: 1">예매하기</button>
		<form action="<%= request.getContextPath() %>/review/write" method="GET" id="reviewFrm">
			<!-- paymentHistory.paymentcontents 안에 movietitle 값이 같으면 버튼 활성화 -->
			<button class="review_write_btn" style="z-index: 1">리뷰 작성하기</button>
			<input type="hidden" name="movie_title" value="<%= sm.getMovieTitle() %>"/>
		</form>
		
		<div class="movie_info_box">
			<form action="<%=request.getContextPath() %>/review/write" method="get" name="reviewWrite">
				<a href=""> 
					<img class="movie_img" src="<%= request.getContextPath() %>/upload/movie/<%= sm.getMainPoster() %>" alt="<%= sm.getMainPoster() %>"> 
					<span class="movie_title"><%= sm.getMovieTitle() %></span>
				</a> 
			
			<table class="movie_info_tb">
				<tr>
					<th>기본정보</th>
					<td><a href=""><%= sm.getGenre() %></a></td>
				</tr>
				<tr>
					<th>감독</th>
					<td><a href=""><%= sm.getDirector() %></a></td>
				</tr>
				<tr>
					<th>출연</th>
					<td><a href=""><%= sm.getDirector() %></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td class="tb_hr" colspan="2"></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<th>예매율</th>
					<td><%= sm.getPurchaseRate() * 100 %> %</td>
				</tr>
			</table>
			
			</form>
		</div>
	</div>
	<% } else {%>
		<h4>최근 영화 리뷰</h4>
	<% } %>
		<div class="review_header">
			<span class="movie_sub_title"> 리뷰 리스트 </span> 
			<div class="review_search"> 
				<select id="review_search" class="review_search_select">
					<option value="writer" <%= "writer".equals(type) ? "selected" : "" %>>글쓴이</option>
					<option value="movie_title" <%= "movie_title".equals(type) ? "selected" : "" %>>영화제목</option>
				</select> 
			</div> 
			<div id="search-writer" class="search-type">
				<form action="<%=request.getContextPath()%>/review/search" method="get" name="reviewSearch">
					<input type="hidden" name="type" value="writer" />
					<input type="search" name="review_search_text" class="review_search_text" placeholder="글쓴이를 입력하세요."
							 value="<%= "writer".equals(type) ? userSearch : "" %>">
					<input type="submit" value="검색" class="review_search_sumit">
				</form>
			</div>
			<div id="search-movie_title" class="search-type">
				<form action="<%=request.getContextPath()%>/review/search" method="get" name="reviewSearch">
					<input type="hidden" name="type" value="movie_title" />
					<input type="search" name="review_search_text" class="review_search_text" placeholder="영화제목을 입력하세요."
						   value="<%= "movie_title".equals(type) ? userSearch : "" %>">
					<input type="submit" value="검색" class="review_search_sumit">
				</form>
			</div>
			<div class="total_review_cnt_box"> 
					총<span	class="total_review_cnt"><%= request.getAttribute("totalContents") %></span> 개의 리뷰가 있습니다. 
			</div>
			
		</div>
	
		<ul class="review_list">
			<li><a href="">전체리뷰</a></li>
			<li><a href="">조회베스트</a></li>
			<li><a href="">추천베스트</a></li>
		</ul>
		<table class="review_tb">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col" class="al">글쓴이·날짜</th>
					<th scope="col" class="al"><a
						href="?st=code&sword=80219&od=goodcnt" class="fs_11 u">추천</a> <img
						src="https://ssl.pstatic.net/imgmovie/2007/img/common/bullet_arrow_bottom_1.gif"
						alt="" width="5" height="3"></th>
					<th scope="col"><a href="?st=code&sword=80219&od=readcnt"
						class="fs_11 u">조회</a> <img
						src="https://ssl.pstatic.net/imgmovie/2007/img/common/bullet_arrow_bottom_1.gif"
						alt="" width="5" height="3"></th>
				</tr>
			</thead>
			<tbody>
			<% if(review != null) { %>
			<% 	for(Review r : review) {
			%>
				<tr>
					<td class="title"><a href="<%= request.getContextPath() %>/review/board?no=<%= r.getReviewNo() %>"><%= r.getTitle() %></a></td>
					<td class="author num"><a href=""><%= r.getWriter() %></a> <br /> <%= r.getReviewDate() %></td>
					<td class="num c_ff4200"><img
						src="https://ssl.pstatic.net/imgmovie/2007/img/common/icon_recommend_1.gif"
						alt="" width="14" height="13"> <%= r.getRecomCnt() != 0 ? r.getRecomCnt() : "" %></td>
					<td class="num"><%= r.getReadCnt() != 0 ? r.getReadCnt() : "" %></td>
				</tr>
				<% 	} %>
				<% }  %>
			</tbody>
		</table>
	
		<div class="paging"><%= request.getAttribute("pageBar") %></div>
	
</article>
<script>
<% if(sm != null) { %>
function reservation(){
	location.href = "<%= request.getContextPath()%>/movie/reservation?movie_title=<%= sm.getMovieTitle() %>";
}
$(".review_write_btn").click(function(){
	$("#reviewFrm").submit();
});
<% } %>

/**
 * #searchType이 변경되면, 해당 form이 노출되어야 한다.
 */
$(review_search).change(function(){
	var searchTypeVal = $(this).val();
	$(".search-type").hide();
	$("#search-" + searchTypeVal).css("display","inline-block");
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

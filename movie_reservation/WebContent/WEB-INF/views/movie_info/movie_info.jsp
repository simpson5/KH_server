<%@page import="com.simp.board.model.vo.Review"%>
<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<%
ScreenMovie sm = (ScreenMovie)request.getAttribute("screenMovie");
String[] steelCut = sm.getSteelCut().split(",");
int audience_cnt = (int)request.getAttribute("audience_cnt");
int female_rate = (int)request.getAttribute("female_rate");
double rate = female_rate * 1.0 / audience_cnt;
double height1 = rate * 200;
double height2 = 200 - height1;
List<Review> list = (List<Review>)request.getAttribute("list");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/movie_info.css" />
<style>
.header_common {
	color : black;
	position : static;
}

.header_common a {
	color : black;
}

.main-menu {
	position : static;
}
</style>
    <!-- 상영 영화 스틸컷 부분 -->
    <div class="screen-poster-container">
      <i class="fas fa-angle-left poster-icon"></i>
      <div class="screen-poster" id="screen-poster0">
        <img
          src="<%= request.getContextPath() %>/upload/movie/<%= steelCut[0] %>"
          alt="<%= steelCut[0] %>"
        />
      </div>
      <div class="screen-poster" id="screen-poster1">
        <img
          src="<%= request.getContextPath() %>/upload/movie/<%= steelCut[1] %>"
          alt="<%= steelCut[1] %>"
        />
      </div>
      <div class="screen-poster" id="screen-poster2">
        <img
          src="<%= request.getContextPath() %>/upload/movie/<%= steelCut[2] %>"
          alt="<%= steelCut[2] %>"
        />
      </div>
      <div class="screen-poster" id="screen-poster3">
        <img
          src="<%= request.getContextPath() %>/upload/movie/<%= steelCut[3] %>"
          alt="<%= steelCut[3] %>"
        />
      </div>
      <div class="screen-poster" id="screen-poster4">
        <img
          src="<%= request.getContextPath() %>/upload/movie/<%= sm.getMainPoster() %>"
          alt="<%= sm.getMainPoster() %>"
        />
      </div>
      <i class="fas fa-angle-right poster-icon"></i>
    </div>
    <!-- 상영 영화 정보 -->
    <div class="movie-info-container">
    	<div class="movie-info-box">
	    	<table>
	    		<tr>
	    			<td>
						<div class="main-poster">
							<img src="<%= request.getContextPath() %>/upload/movie/<%= sm.getMainPoster() %>"
								  alt="<%= sm.getMainPoster() %>" id="movie_btn" />
						</div>
	    			</td>
	    			<td>
						<div class="main-info">
	        				<h2 class="movie-title"><%= sm.getMovieTitle() %></h2>
	        				<p>예매율 : <%= (double)Math.round(sm.getPurchaseRate() * 100) /100 %> | <%= audience_cnt %> </p>
	        				<hr />
	        				<p>장르 : <%= sm.getGenre() %></p>
	        				<p>감독 : <%= sm.getDirector() %></p>
	        				<p> 출연 : <%= sm.getActor() %></p>
	        				<p>
	        					<% if((dipList != null) && dipList.contains(sm.getMovieTitle())) { %>
								<i class="dip_movie fas fa-heart"></i>
								<% } else { %>
								<i class="dip_movie far fa-heart"></i>
								<% } %>	
	        					| <button class="reservation-btn">예매하기</button>
	        				</p>
	        				<br />
	      				</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td colspan=2>
	    				<!-- 영화 줄거리 및 영상 | 선호도 -->
						<div class="Synopsis-box">
	        				<h3>영화 정보</h3>
							<table>
							<tr>
								<td style="vertical-align:top;">
									<div class="Synopsis-box">
			          					<h4>줄거리</h4>
			          					<div class="Synopsis"><%= sm.getSynopsis() %></div>
			        				</div>
								</td>
								<td style="vertical-align:top;">
									<div class="preference-box">
			          					<h4>선호도</h4>
			          					<div class="male-box">
				            				<div class="male" style="text-align:center;">
				            					<div class="male-rate" style="height:<%= height1 %>px"></div>
				            				</div>
				            				<p>남성</p>
			          					</div>
				          				<div class="female-box">
				            				<div class="female" style="text-align:center;">
				            					<div class="female-rate" style="height:<%= height2 %>px"></div>
				            				</div>
				            				<p>여성</p>
					          			</div>
			        				</div>
								</td>
							</tr>
							</table>
	      				</div>
	      			</td>		
	    		</tr>
	    		<tr>
	    			<td>
	    				<!-- 리뷰 -->
					      <div class="review-box">
					        <h3>베스트 리뷰</h3>
					        <input type="button" value="리뷰보기" class="move_review" />
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
			<% if(list != null) { %>
			<% 	for(Review r : list) {
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
					      </div>
	    			</td>
	    		</tr>
	    	</table>
		</div>
    </div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<style>
  .footer{
  	margin-top : 60vh;
  }
  
 /* 리뷰 테이블 부분 */
.review_tb {
	padding: 0;
	border-top: 1px solid rgb(44, 44, 44);
	border-bottom: 1px solid rgb(44, 44, 44);
	width: 100%;
	border-collapse: collapse;
	text-align: center;
}

table.review_tb th, td {
	/* border-bottom: 1px solid gray; */
	padding: 0;
	margin: 0;
}

.review_tb td a {
	text-decoration: none;
	color: rgb(61, 61, 61);
}

.review_tb th {
	height: 35px;
	color: rgb(53, 52, 52);
	border-bottom: 1px solid rgb(202, 202, 202);
}

.review_tb td {
	border-bottom: 1px solid rgb(235, 235, 235);
}

.review_tb th a {
	color: rgb(80, 80, 80);
}

td.title {
	text-align: left;
	font-size: 14px;
}
</style>
<form action="<%= request.getContextPath() %>/movie/reservation"
	  method="GET"
	  id="reservationFrm">
	<input type="hidden" name="movie_title" value="<%= sm.getMovieTitle() %>"/>
</form>

<form action="<%= request.getContextPath() %>/review"
	  method="GET"
	  id="reviewFrm">
	<input type="hidden" name="movie_title" value="<%= sm.getMovieTitle() %>"/>
</form>
</body>
<script src="<%= request.getContextPath() %>/js/index.js"></script>
<script>
$("#movie_btn").click(function(){
	location.href = "<%= sm.getTrailer() %>";
});
$(".reservation-btn").click(function(){
		$("#reservationFrm").submit();
});

$(".move_review").click(function(){
	$("#reviewFrm").submit();
});
$(".dip_movie").click(function(){	
	<% if(loginMember != null) { %>
		var movie_title = "<%= sm.getMovieTitle() %>";
		console.log(movie_title);
		if($(this).hasClass("far")){
			$(this).removeClass("far").addClass("fas");
		} else {
			$(this).removeClass("fas").addClass("far");
		}
		if($(this).hasClass("fas")){
			$.ajax({
				url: "<%= request.getContextPath() %>/movie/dip",
				data: {
					memberId: "<%= loginMember.getMember_id() %>",
					movieTitle: movie_title
				},
				success: function(data){
					if(data > 0) {
						swal("찜", "성공", "success");				
					}
					else {
						swal("찜", "실패", "error");
					}
				},
				error: function(xhr, status, err){
					console.log(xhr, status, err);
				}
			});
		} else {
			$.ajax({
				url: "<%= request.getContextPath() %>/movie/dip",
				method: "POST",
				data: {
					memberId: "<%= loginMember.getMember_id() %>",
					movieTitle: movie_title
				},
				success: function(data){
					if(data > 0) {
						swal("찜 삭제", "성공", "success");				
					}
					else {
						swal("찜 삭제", "실패", "error");
					}
				},
				error: function(xhr, status, err){
					console.log(xhr, status, err);
				}
			});
		}
	<% } else { %>
		swal("찜", "로그인 후 영화 찜하기가 가능합니다.", "error")
	<% } %>
})
</script>
</html>

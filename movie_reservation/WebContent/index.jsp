<%@page import="com.simp.movie.model.vo.Banner"%>
<%@page import="com.simp.movie.model.service.MovieService"%>
<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<% List<ScreenMovie> topList = new MovieService().topList(); %>
<%

List<ScreenMovie> recoList = null;

List<Banner> bannerList = new MovieService().bannerList();
int cnt = 0;

if(loginMember != null){
	//멤버 선호 장르가져오기
	String genre = loginMember.getPreference_genre();
	//멤버 추천 리스트 가져오기
	recoList = new MovieService().recoList(loginMember.getMember_id(), genre);	
}
%>
<style>
.title-menu {
  right: 5px;
  top: 15px;
}
</style>
<% //추천 영화 가져오기 %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
    <!-- 상영 영화 포스터 부분 -->
    <div class="screen-poster-container">
      <i class="fas fa-angle-left poster-icon"></i>
    	<% for(Banner b : bannerList) { %>
      <img
        class="screen-poster"
        id="screen-poster<%= cnt %>"
        src="<%= b.getPoster() %>"
        alt=""
      />
      	<% cnt++; %>
	    <% } %>
      <i class="fas fa-angle-right poster-icon"></i>
    </div>
    <!-- 상영 영화 리스트 -->
<div class="current-screen">
<p style="color:white; margin: 0px; text-align:left; padding-left: 10vw; margin-bottom:-10vh;">인기영화</p>
<%
if(topList != null) {
	for(ScreenMovie si : topList) {
%>
		<div class="current-screen-poster">
			<img src="<%= request.getContextPath() %>/upload/movie/<%= si.getMainPoster() %>" alt="<%= si.getMainPoster() %>"/>
			<table class="screen_info_box">
				<tr class="title-parent">
					<td colspan=2 >
						<span class="screen_info movie-title"><%= si.getMovieTitle() %></span>
					</td>
				</tr>
				<tr class="title-parent-next">
					<td style="margin: 0 3px;">
						<span class="screen_info rate"><%= si.getPurchaseRate() * 100 %> %</span>
					</td>
					<td style="margin: 0 3px;">
						<span class="screen_info heart">
					<% if((dipList != null) && dipList.contains(si.getMovieTitle())) { %>
							<i class="dip_movie fas fa-heart"></i>
					<% } else { %>
							<i class="dip_movie far fa-heart"></i>
					<% } %>					
						</span>
					</td>
				</tr>
			</table>
			<div class="screen_bg">
				<form action="<%= request.getContextPath()%>/movie/reservation">
					<input type="hidden" name="movie_title" value="<%= si.getMovieTitle() %>"/>
					<input class="img-btn1" type="submit" value="예매하기" />
				</form>
				<form action="<%= request.getContextPath()%>/movie/movie_info">
					<input type="hidden" name="movie_title" value="<%= si.getMovieTitle() %>"/>
	            	<input class="img-btn2" type="submit" value="상세정보" />
				</form>
			</div>
		</div>
<%
	}
}
%>
<br />
<p style="color:white; margin: 0px; text-align:left; padding-left: 10vw; margin-bottom:-10vh;">추천영화</p>
<br />
<%
if(recoList != null) {
	for(ScreenMovie si : recoList) {
%>
		<div class="current-screen-poster">
			<img src="<%= request.getContextPath() %>/upload/movie/<%= si.getMainPoster() %>" alt="<%= si.getMainPoster() %>"/>
			<table class="screen_info_box">
				<tr class="title-parent">
					<td colspan=2 >
						<span class="screen_info movie-title"><%= si.getMovieTitle() %></span>
					</td>
				</tr>
				<tr class="title-parent-next">
					<td style="margin: 0 3px;">
						<span class="screen_info rate"><%= si.getPurchaseRate() * 100 %> %</span>
					</td>
					<td style="margin: 0 3px;">
						<span class="screen_info heart">
					<% if((dipList != null) && dipList.contains(si.getMovieTitle())) { %>
							<i class="dip_movie fas fa-heart"></i>
					<% } else { %>
							<i class="dip_movie far fa-heart"></i>
					<% } %>					
						</span>
					</td>
				</tr>
			</table>
			<div class="screen_bg">
				<form action="<%= request.getContextPath()%>/movie/reservation">
					<input type="hidden" name="movie_title" value="<%= si.getMovieTitle() %>"/>
					<input class="img-btn1" type="submit" value="예매하기" />
				</form>
				<form action="<%= request.getContextPath()%>/movie/movie_info">
					<input type="hidden" name="movie_title" value="<%= si.getMovieTitle() %>"/>
	            	<input class="img-btn2" type="submit" value="상세정보" />
				</form>
			</div>
		</div>
<%
	}
}
%>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<%@ include file="/WEB-INF/views/common/foaltingbar_link.jsp"%>
</body>
<script src="js/index.js"></script>
<script>
$(".dip_movie").click(function(){	
<% if(loginMember != null) { %>
	var movie_title = $(this).closest(".title-parent-next").prev().find(".movie-title").html();
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
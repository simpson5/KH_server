<%@page import="com.simp.movie.model.service.MovieService"%>
<%@page import="com.simp.movie.model.vo.Banner"%>
<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<% 
List<ScreenMovie> list = (List<ScreenMovie>)request.getAttribute("list");

List<Banner> bannerList = new MovieService().bannerList();
int cnt = 0;

%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/screen_movie.css" />
<style>
   .header_common {
      color : black;
      position : static;
   }
   
   .header_common a {
      color : black;
   }
   
   .screen-poster-container {
   	 margin-top: 5vh;
   }
</style>
    <!-- 상영작 스크롤 -->
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
    
    <!-- 현재 상영작 -->
    <div class="current-screen">
<%
int n = 0;
if(list != null) {
	for(ScreenMovie si : list) {
		n ++;
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
						<span class="screen_info rate"><%= (double)Math.round(si.getPurchaseRate() * 10000) /100 %> %</span>
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
	<% if(n == 5){ %>
		<br />
<%
			n=0;
		} 
	}
}
%>		
	</div>
      <%@ include file="/WEB-INF/views/common/footer.jsp"%>
      <%@ include file="/WEB-INF/views/common/foaltingbar_link.jsp"%>
  </body>
<script src="<%= request.getContextPath() %>/js/index.js"></script>
<style>
.footer{margin-top : 10vh;}
</style>
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

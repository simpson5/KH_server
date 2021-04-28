<%@page import="com.simp.movie.model.vo.ScreenInfo"%>
<%@page import="com.simp.movie.model.vo.ScreenMovie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<ScreenInfo> list = (List<ScreenInfo>)request.getAttribute("list");
	ScreenMovie sm = (ScreenMovie)request.getAttribute("sm");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/ticketing.css" />
<style>
   .header_common {
      color : black;
      position : static;
   }
   
   .header_common a {
      color : black;
   }
   
   .select_date {
   	background-color : black;
   	color : white;
   }
   
   .hidden {
   	display: none;
   }
</style>
<div class="main_container">
<table class="table_box">
	<tr>
		<td><h3>영화정보</h3></td>
		<td><h3>상영시간 </h3></td>
	</tr>
	<tr>
		<td class="info_box">
			<div>
				<div class="movie_info" style="text-align: center;">
					<div class="title"><%= sm.getMovieTitle() %></div>
					<br />
					<div class="poster">
						<img src="<%= request.getContextPath() %>/upload/movie/<%= sm.getMainPoster() %>" alt="<%= sm.getMainPoster() %>" />
					</div>
				</div>
			</div>
		</td>
		<td class="info_box" style="vertical-align: top;">
			<div class="date_list">
				<p style="	background-color : white; margin: 10px 0;">
					<span class="date select_si select_date" id="date_part1"></span>
					<span class="date select_si" id="date_part2"></span>
					<span class="date select_si" id="date_part3"></span>
					<span class="date select_si" id="date_part4"></span>
					<span class="date select_si" id="date_part5"></span>
					<span class="date select_si" id="date_part6"></span>
					<span class="date select_si" id="date_part7"></span>
				</p>
			</div>
			<div style="text-align: center; padding: 0 20px">
<% if(list != null) { %>			
	<% for(ScreenInfo si : list) { %>
		<% String date = String.format("%02d",(si.getStartTime().getMonth() + 1)) + "-" + String.format("%02d",si.getStartTime().getDate()); %>
					<div class="reservation_button <%= date %> hidden"
					     onclick="location.href='<%= request.getContextPath()%>/movie/reservation/seat?screen_no=<%= si.getScreenNo() %>&memberId=<%= loginMember.getMember_id() %>';">
						<%-- <p><%= (si.getStartTime().getMonth() + 1) + " - " + si.getStartTime().getDate() %></p> --%>
						<p>
							<span>
								<%= si.getStartTime().getHours() + ":" + String.format("%02d", si.getStartTime().getMinutes()) %> ~
								<%= si.getEndTime().getHours() + ":" + String.format("%02d", si.getEndTime().getMinutes()) %>
							</span>
						</p>
						<p><%= si.getTheaterNo() %></p>
					</div>
	<% } %>
<% } else {%>
					<p>상영 정보가 없습니다!</p>
<% } %>			
			</div>
		</td>
	</tr>
</table>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
<script>
function getCurrentWeek() {
	const day = new Date(); 
	var week = new Array('일', '월', '화', '수', '목', '금', '토');
	const sunday = day.getTime() - 86400000 * day.getDay();
	day.setTime(day); 
	const result = [day.toISOString().slice(5, 10)]; 
	for (let i = 1; i < 7; i++) { 
		day.setTime(day.getTime() + 86400000); 
		result.push(day.toISOString().slice(5, 10)); 
		}
	return result; 
}

$("#date_part1").text(getCurrentWeek()[0]);
$("#date_part2").text(getCurrentWeek()[1]);
$("#date_part3").text(getCurrentWeek()[2]);
$("#date_part4").text(getCurrentWeek()[3]);
$("#date_part5").text(getCurrentWeek()[4]);
$("#date_part6").text(getCurrentWeek()[5]);
$("#date_part7").text(getCurrentWeek()[6]);

$(".select_si").click(function(){
	console.log($(this).text());
	
	var date = $(this).text();
	console.log($("."+date));
	
	$(".reservation_button").addClass("hidden");
	$("."+date).toggleClass("hidden");
	
	$(".select_si").removeClass("select_date");
	$(this).toggleClass("select_date");
});

</script>
</html>

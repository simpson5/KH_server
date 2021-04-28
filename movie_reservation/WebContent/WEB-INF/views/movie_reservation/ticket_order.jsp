<%@page import="com.simp.movie.model.vo.ScreenInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<% 
	ScreenInfo si = (ScreenInfo)request.getAttribute("screenInfo");
	String[] seat_arr = (String[])request.getParameter("seat_no").split(",");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/ticket_order.css">
<style>
   .header_common {
      color : black;
      position : static;
   }
   
   .header_common a {
      color : black;
   }
</style>
<div class="main_container">
<table class="table_box">
	<tr>
		<td><h3>영화정보</h3></td>
		<td><h3>결제 정보 </h3></td>
	</tr>
	<tr>
		<td class="info_box">
			<div>
				<div class="movie_info" style="text-align: center;">
					<div class="title"><%= si.getMovieTitle() %></div>
					<br />
					<div class="poster">
						<img src="<%= request.getContextPath() %>/upload/movie/<%= si.getMainPoster() %>" alt="<%= si.getMainPoster() %>" />
					</div>
				</div>
			</div>
		</td>
		<td class="info_box">
			<div class="payment_box">
			  <input type="hidden" name="screen_no" value="<%= request.getParameter("screen_no") %>">
			  <input type="hidden" name="member_id" value="<%= request.getParameter("member_id") %>">
			<table>
				<tr>
					<td style="width:150px;">상영 시간</td>
					<td colspan = 2>
						<%= si.getStartTime().getHours() + ":" + String.format("%02d", si.getStartTime().getMinutes()) %> ~
						<%= si.getEndTime().getHours() + ":" + String.format("%02d", si.getEndTime().getMinutes()) %>
					</td>
				</tr>
				<tr>
					<td>좌석</td>
					<td colspan = 2>
					<!-- 추가한 부분 -->
					<% for(String seat : seat_arr ) { %>
						<span><%= seat %></span>
					<% } %>
						<input type="hidden" name="seat_no" value="<%= request.getParameter("seat_no") %>">
					</td>
				</tr>
				<tr>
					<td>결제 금액</td>
					<td colspan = 2>
						<p id="amount"><%= request.getParameter("amount") %></p>
						<input type="hidden" name="amount" value="<%= request.getParameter("amount") %>">
					</td>
				</tr>
				<tr>
					<td>사용 포인트</td>
					<td style="width:150px;">
					<input type="number" name="point" value=0 step="100" style="width:100px;"/>
					</td>
					<td>
						<p><%= loginMember.getPoint() %></p>
					</td>
				</tr>
				<tr>
					<td colspan = 3 style="text-align : center;">
						<button id="payment_btn">결제하기</button>
					</td>
				</tr>
			</table>
			</div>
		</td>
	</tr>
</table>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
<script>
$("[name=point]").change(function(){
	var p = $(this).val();
	if(p <= <%= loginMember.getPoint() %>){
		var cash = <%= request.getParameter("amount") %>;
		$("#amount").text(cash-p);
		$("[name=amount]").val(cash-p);	
	} else {
		$(this).val(p - 100);
		swal("포인트가 부족합니다", "error")
	}
})

$("#payment_btn").click(function(){
	$.ajax({
		url:'<%= request.getContextPath() %>/payment/moive',
		data: {
			member_id : '<%= loginMember.getMember_id() %>',
			screen_no : $("[name=screen_no]").val(),
			seat_no : $("[name=seat_no]").val(),
			amount : $("[name=amount]").val(),
			point : $("[name=point]").val()
		},
		method:'POST',
		dataType:'json',
		success : function(data){
			// 요청 성공시 실행 콜백 함수, 매개 인자로 응답 message 리턴
			console.log("success call!");
			console.log(data);
			var box = data.next_redirect_pc_url;
			
			var _width = '650';
		    var _height = '500';
		 
		    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
		    var _left = Math.ceil(( window.screen.width - _width )/2);
		    var _top = Math.ceil(( window.screen.height - _height )/2); 
		 
		    window.open(box, 'kakao', 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top );
		 
		},
		error : function(xhr, status, error, data){
			// 요청 오류시 실행 콜백 함수
			console.log("error call!");
			console.log(xhr, status, error);
			console.log(data);
		},
	})
})
</script>
</html>
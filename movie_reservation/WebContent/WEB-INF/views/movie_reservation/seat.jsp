<%@page import="com.simp.product.model.vo.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="com.simp.movie.model.vo.ScreenInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ScreenInfo si = (ScreenInfo)request.getAttribute("screenInfo");
	List<Ticket> tkList = (List<Ticket>)request.getAttribute("tkList");
	List<Integer> seat_cnt = (List<Integer>)request.getAttribute("seat_cnt");
%>	
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<style>
   .header_common {
      color : black;
      position : static;
   }
   
   .header_common a {
      color : black;
   }
   
</style>
<style>
.content {
	margin-top: 40px;
}

.headcount-box {
	width: 800px;
	margin: 0 auto;
	text-align: center;
}

.headcount-box h2 {
	width: 800px;
	background-color: black;
	color: white;
	padding: 10px 0;
	margin-bottom : 5px;
}

.movie-info {
	display: inline-block;
}

.text-info {
	display: inline-block;
	margin-left: 1vw;
}

.text-info > p {
	margin : 0;
}

.cnt-container .btn-box {
	display: inline-block;
	border: solid 1px black;
	width: 6vw;
	height: 3vh;
	text-align: center;
	border-radius: 0.3vw;
	padding-top: 0.3vh;
}

.btn-box span {
	margin: 0 1vw;
}

.btn-box button {
	border: 0;
	outline: 0;
	background-color: white;
}

.headcount-box img {
	width: 4vw;
}

.seat-container {
	position: relative;
	width: 800px;
	height: 600px;
	margin: 0 auto;
	background-color: black;
}

.screen {
	width: 600px;
	height: 20px;
	position: relative;
	left: 50%;
	top: 30px;
	transform: translateX(-50%);
	text-align: center;
	font-size: 15px;
	background-color: gray;
	color: white;
}

.seat-box {
	position: relative;
	left: 50%;
	top: 50px;
	transform: translateX(-50%);
	color: white;
	text-align: center;
	width: 600px;
}

.seat {
	width: 20px;
	height: 20px;
	display: inline-block;
	background-color: white;
	color: black;
	border-radius : 5px 5px 0 0;
	padding-bottom : 2px;
}

.seat_x{
	width: 20px;
	height: 20px;
	display: inline-block;
	background-color: red;
	color: white;
	border-radius : 5px 5px 0 0;
	padding-bottom : 2px;
}

.seat_r{
	background-color: yellow;
	color: black;
}

.seat_r:hover,
.seat:hover {
	cursor:pointer;
}

.seat-row {
	display: inline-block;
	width: 8px;
}
.blank {
	width: 30px;
	height: 20px;
	background-color: black;
	display: inline-block;
}
</style>
</head>
<body>
	<div class="content">
		<div class="headcount-box">
			<h2>인원 / 좌석 선택</h2>
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<td>
									<div class="movie-info">
										<img src="<%= request.getContextPath() %>/upload/movie/<%= si.getMainPoster() %>"
											 alt="<%= si.getMainPoster() %>" />
									</div>
								</td>
								<td>
									<div class="text-info">
										<p><%= si.getMovieTitle() %></p>
										<p>
											<%= si.getStartTime().getHours() + ":" + String.format("%02d", si.getStartTime().getMinutes()) %> ~
											<%= si.getEndTime().getHours() + ":" + String.format("%02d", si.getEndTime().getMinutes()) %>
										</p>
										<p><%= si.getTheaterNo() %></p>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<div class="adult price_check" style="width: 200px;">
							성인
							<div class="btn-box">
								<button class="minus">
									<i class="fas fa-minus"></i>
								</button>
								<span>0</span>
								<button class="plus">
									<i class="fas fa-plus"></i>
								</button>
							</div>
						</div>
					</td>
					<td>
						<div class="child price_check" style="width: 200px;">
							청소년
							<div class="btn-box">
								<button class="minus">
									<i class="fas fa-minus"></i>
								</button>
								<span>0</span>
								<button class="plus">
									<i class="fas fa-plus"></i>
								</button>
							</div>
						</div>
					</td>
					<td>
						<div class="payment">
							<p><span id="amount">0</span> 원</p>
							<p><button id="payment_btn">예매하기</button></p>
						</div>
					</td>
				</tr>
			</table>
		</div>
	<div class="seat-container">
		<div class="screen">S C R E E N</div>
		<div class="seat-box">
<%
int row = 0;
int col = 0;
switch(si.getTheaterNo()) {
case "상영관1" :
	row = 20;
	col = 20;
	break;
case "상영관2" :
	row = 10;
	col = 20;
	break;			
case "상영관3" :
	row = 10;
	col = 20;
	break;			
case "상영관4" :
	row = 10;
	col = 10;
	break;			
case "상영관5" :
	row = 10;
	col = 10;
	break;			
}

//티켓 번호에서 좌석 번호를 배열을 얻음, 좌석 번호 배열을 카운팅 해서 현재 추가하는 좌석과 예매한 좌석의 번호가 일치할 경우 seat의 색상을 변경!
//처음 티켓 생성할때부터 좌석번호를 부여하자
int n = 1;

for(int i = 0; i < row; i++) {

%>
	<span class="seat-row"><%= (char)(i+65) %></span>
<%
	for(int j = 0; j < col; j++) {
		if(j == 10) {
%>
		<div class="blank"></div>
<%  
		}
		if(!seat_cnt.contains(n)) {			
%>
			<div class="seat select"><%= j + 1 %></div>
			<% } else {%>
			<div class="seat_x"><%= j + 1 %></div>
			<% } %>
		<% n++; } %>
			<br />
<% } %>
			</div>	
		</div>
	</div>
	    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
<style>
.footer{
	margin-top : 10vh;
}
</style>
<%@ include file="/WEB-INF/views/common/foaltingbar_link.jsp"%>
</body>
<form action="<%= request.getContextPath()%>/movie/reservation/payment"
	  method="GET"
	  id=paymentFrm>
	<input type="hidden" name="member_id" value="<%= loginMember.getMember_id() %>" />
	<input type="hidden" name="screen_no" value="<%= si.getScreenNo() %>"/>
	<input type="hidden" name="amount"/>
	<input type="hidden" name="seat_no" />
</form>
<script>
var cnt = 0;
var amount = 0;

$(".plus").click(function(){
	var n =  parseInt($(this).prev().text()) + 1;
	cnt = cnt + 1;
	if($(this).closest(".price_check").hasClass("adult")){
		amount = amount + <%= si.getScreen_price() %>;		
	} else {
		amount = amount + <%= si.getScreen_price() - 2000 %>;;
	}
	console.log(amount);
	$(this).prev().text(n);
	$("#amount").text(amount);
})

$(".minus").click(function(){
	if($(this).next().text() > 0){
		if(cnt > $(".seat_r").length){
			var n =  parseInt($(this).next().text()) - 1;
			cnt = cnt - 1;
			if($(this).closest(".price_check").hasClass("adult")){
				amount = amount - <%= si.getScreen_price() %>;		
			} else {
				amount = amount - <%= si.getScreen_price() - 2000 %>;
			}
			console.log(amount);
			$(this).next().text(n);		
			$("#amount").text(amount);			
		} else {
			swal("좌석!", "좌석수를 줄여주세요.", "error");		
		}
	}
})
/* 좌석 선택 */
$(".select").click(function(){
	var row = $(this).prevAll(".seat-row").text();
	var seat_no = row.substring(0,1) + $(this).text()
	if($(this).hasClass("seat_r")){
		$(this).removeClass("seat_r");				
	} else {
		if($(".seat_r").length < cnt){
			$(this).toggleClass("seat_r");					
		} else {
			swal("인원 부족", "인원수를 늘려주세요.", "error");			
		}
	}
})

$("#payment_btn").click(function(){
	if($(".seat_r").length == cnt && cnt > 0){
		var seat_arr = [];
		$(".seat_r").each(function(i, seat){
			var row = $(seat).prevAll(".seat-row").text();
			var seat_no = row.substring(0,1) + $(seat).text()
			console.log(i);
			seat_arr[i] = seat_no;
		})
		$("[name=seat_no]").val(seat_arr);
		$("[name=amount]").val(amount);
		$("#paymentFrm").submit();
	} else {
		swal("인원 부족", "좌석수와 인원수가 일치 하지 않습니다.", "error");
	}
})

</script>
</html>
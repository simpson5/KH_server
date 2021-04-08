<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/mypageHeader.jsp" %>
<%
	Member m = (Member)request.getAttribute("member");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/customerService.css" />
<style>
.cancelList{color: gray;}
.reservationList{color: rgb(98, 80, 211);}
</style>

<section id="content-ticketing">
<div class="sub-contents-wrap inner-wrap">

 		<div class="tab_type02" style="margin-top: 40px; margin-left: 83px;">
		    <ul>
		        <li><a href="avascript:void(0)" class="reservationList" style="font-weight: bold;">예매내역</a></li>
		        <span>|</span>
		        <li><a href="avascript:void(0)" class="cancelList" style="font-weight: bold;">취소내역</a></li>
		    </ul>
		</div>
		
		<div id="tbl_div">
         <table id="tbl_ticketing" class="tbl_notice" style="text-align: center; margin-top: 30px;">
             <tr id = "ticketing_title">
                <th>No</th>
                <th>예매번호</th>
                <th>영화명</th>
                <th>예매좌석</th>
                <th>상영일시</th>
                <th>예매일</th>
                <th class="cancelyn">예매취소</th>
            </tr>
              <tbody id="ticketing_body"></tbody>
        </table>
		</div>
</div>
</section>
	



<script>
	$(".mypoint p").html(<%=m.getPoint()%>);
var num = 1;
/* var cancel_yn = ""; */
/*효정수정1 시작*/
var ticketNo = 0;
var showDate = '';
var startTime = '';
var screenCode = 0;
var seatNo = '';
var memberId = '<%=memberLoggedIn.getMemberId()%>';
/*효정수정1 끝*/
$(()=>{
	
	$("#ticketingList").css("color", "#503396").css("font-weight", "bold");
	
	reservationList();	
	
	
	$(".reservationList").click(function(){
			reservationList();
 			$(this).css("color", "rgb(98, 80, 211)");
 			$(".tab_type02 li a").not($(this)).css("color", "gray");
	});
	
	$(".cancelList").click(function(){
 			cancelList();
 			$(this).css("color", "rgb(98, 80, 211)");
 			$(".tab_type02 li a").not($(this)).css("color", "gray");
	});
	
});

function reservationList(){
	
	$.ajax({
		url: "<%=request.getContextPath()%>/member/memberTicketingList/ticketingList.do",
		data: {memberId: '<%=memberLoggedIn.getMemberId()%>'},		
		type: "POST",
		dataType: "json",
		success: data => {
			
/*         	let html2 = "";
          	html2 += "<table id='tbl_ticketing' class='tbl_notice' style='text-align: center; margin-top: 30px;'>;
            html2 += "<tr id = 'ticketing_title'>";
            html2 += "<th>No</th><th>예매번호</th><th>영화명</th><th>예매좌석</th><th>상영일시</th><th>예매일</th>";
            html2 += "<th class='cancelyn'>예매취소</th></tr>";
            html2 += "<tbody id='ticketing_body'></tbody></table>";
            $("#tbl_div").append(html2); */
			
			$(".cancelyn").html("예매취소");
			
			$("#ticketing_body").html("");
			num = 1;
			
			for(let i in data){
				
				let html = "";
				let t = data[i];
				cancel_yn = t.cancelYN;
				
				html += "<tr>";
				html += "<td>"+num+"</td>"; 
				html += "<td>"+t.ticketNo+"</td>";
				html += "<td>"+t.movieTitle+"</td>";
				html += "<td>"+t.screenCode+"관</br>"+t.seatNo+"열</td>"; //예매좌석
				html += "<td>"+t.showDate+"</br>"+t.startTime+"~"+t.endTime+"</td>"; //상영일시
				html += "<td>"+t.ticketingDate+"</td>";
/* 				html += "<td style='font-weight: bold'>"+((cancel_yn == 'N') ? "<a href='javascript:void(0)' style='color: rgb(98, 80, 211);'>X</a></td>" : "O</td>"); */
				html += "<td style='color: rgb(98, 80, 211); font-weight: bold;'><button id='btn-cancelTicket' onclick='cancelTicket()'>예매취소</button></td>"/*효정수정2*/
				html += "</tr>"
				/*효정수정3 시작*/
				ticketNo = t.ticketNo;
				showDate = t.showDate;
				startTime = t.startTime;
				screenCode = t.screenCode;
				seatNo = t.seatNo;
				/*효정수정3 끝*/
				$("#ticketing_body").append(html);
				num++;
			}
			
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
		
	}); //ajax끝
	
} //function reservationList끝

function cancelList(){
	
	$.ajax({
		url: "<%=request.getContextPath()%>/member/memberTicketingList/cancelList.do",
		data: {memberId: '<%=memberLoggedIn.getMemberId()%>'},		
		type: "POST",
		dataType: "json",
		success: data => {
			$(".cancelyn").html("예매취소일");
			
			$("#ticketing_body").html("");
			num = 1;
			
			for(let i in data){
				let html = "";
				let t = data[i];
				
				html += "<tr>";
				html += "<td>"+num+"</td>"; 
				html += "<td>"+t.ticketNo+"</td>";
				html += "<td>"+t.movieTitle+"</td>";
				html += "<td>"+t.screenCode+"관</br>"+t.seatNo+"열</td>"; //예매좌석
				html += "<td>"+t.showDate+"</br>"+t.startTime+"~"+t.endTime+"</td>"; //상영일시
				html += "<td>"+t.ticketingDate+"</td>";
				html += "<td>"+t.cancelDate+"</td>";
				html += "</tr>"
				
				$("#ticketing_body").append(html);
				num++;
			}
			
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
		
	}); //ajax끝
	
	
}
/*효정수정4 시작*/
function cancelTicket(){
	location.href = '<%=request.getContextPath()%>/member/memberTicketingCancel?showDate='+showDate+"&startTime="+startTime+"&ticketNo="+ticketNo+"&screenCode="+screenCode+"&seatNo="+seatNo+"&memberId="+memberId;
}
/*효정수정4 끝*/

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
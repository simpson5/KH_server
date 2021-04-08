<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/mypageHeader.jsp" %>
<%
	Member m = (Member)request.getAttribute("member");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/customerService.css" />
<style>
.addPoint{color: rgb(98, 80, 211);}
.usePoint{color: gray;}
</style>

<section id="content-mypage">
<div class="sub-contents-wrap inner-wrap">

	<div>
		<div class="tab_type02" style="margin-top: 40px; margin-left: 83px;">
		    <ul>
<!-- 	        <li><a href="member/my/favorite.do">적립</a></li>
		        <span>|</span>
		        <li><a href="member/my/reserve.do">사용</a></li> -->
		        <li><a href="avascript:void(0)" class="addPoint" style="font-weight: bold;">적립</a></li>
		        <span>|</span>
		        <li><a href="avascript:void(0)" class="usePoint" style="font-weight: bold;">사용</a></li>
		    </ul>
		</div>
	</div>
	
    <table id="tbl_point" class="tbl_notice" style="text-align: center; margin-top: 30px;">
        <thead>
        <tr id = "point_title">
            <th>No</th>
            <th class="pointDate">사용일</th>
            <th>영화명</th>
            <th class="amount">사용포인트</th>
        </tr>
        </thead>
        <tbody id="point_body"></tbody>
    </table>
	
</div> <!-- tab-second-block 끝 -->
</section>

<script>
$(".mypoint p").html(<%=m.getPoint()%>);
var num = 1;

$(()=>{
	$("#pointList").css("color", "#503396").css("font-weight", "bold");
	
	addPoint();	
	
	$(".addPoint").click(function(){
			addPoint();
 			$(this).css("color", "rgb(98, 80, 211)");
 			$(".tab_type02 li a").not($(this)).css("color", "gray");
	});
	
	$(".usePoint").click(function(){
 			usePoint();
 			$(this).css("color", "rgb(98, 80, 211)");
 			$(".tab_type02 li a").not($(this)).css("color", "gray");
	});
});

function addPoint(){
	$.ajax({
		url: "<%=request.getContextPath()%>/member/memberPoint/addPoint.do",
		data: {memberId: '<%=memberLoggedIn.getMemberId()%>'},		
		type: "POST",
		dataType: "json",
		success: data => {
			$(".pointDate").html("적립일");
			$(".amount").html("적립포인트");

			$("#point_body").html("");
			num = 1;
			
			for(let i in data){
				
				let html = "";
				let p = data[i];
				
				html += "<tr>";
				html += "<td>"+num+"</td>"; 
				html += "<td>"+p.pointDate+"</td>";
				html += "<td>"+p.movieTitle+"</td>";
				html += "<td>"+p.amount+"</td>";
				html += "</tr>";
				
				$("#point_body").append(html);
				num++;
			}
			
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
	}); //ajax끝
} //function addPoint끝

function usePoint(){
	$.ajax({
		url: "<%=request.getContextPath()%>/member/memberPoint/usePoint.do",
		data: {memberId: '<%=memberLoggedIn.getMemberId()%>'},		
		type: "POST",
		dataType: "json",
		success: data => {
			$(".pointDate").html("사용일");
			$(".amount").html("사용포인트");
			$("#point_body").html("");
			num = 1;
			
			for(let i in data){
				let html = "";
				let p = data[i];
				
				html += "<tr>";
				html += "<td>"+num+"</td>"; 
				html += "<td>"+p.pointDate+"</td>";
				html += "<td>"+p.movieTitle+"</td>";
				html += "<td>"+p.amount+"</td>";
				html += "</tr>"
				
				$("#point_body").append(html);
				num++;
			}
			
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
	});
}

</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>
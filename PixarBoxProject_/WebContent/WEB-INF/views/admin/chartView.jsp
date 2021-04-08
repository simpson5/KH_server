<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/chartView.css" />




<section>


<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/admin/memberList" id="memberList">회원관리</a></li>
		<li><a href="<%=request.getContextPath() %>/admin/chartList/chartListView.do" id="chartListView">매출분석</a></li>
		<li><a href="<%=request.getContextPath() %>/admin/addDeleteMovieNow">현재상영작관리</a></li>
	</ul>
</div>
		
		<form action="<%=request.getContextPath()%>/admin/chartList/chartType.do" method="post">
		 	<select id="searchType" name="searchType">
				<option value="ticketSales" selected> 티켓판매량</option>
				<option value="monthSales">월매출</option>	
			</select> 
			
			
			
			<button type="submit">확인하기</button>
		</form>
	<div id="chart_div"></div>

</section>

<script>

$(()=> {
	$("#chartListView").css("color", "#503396")
					.css("font-weight", "bold");
});

	google.charts.load('current', {packages: ['corechart', 'bar']});
	google.charts.setOnLoadCallback(drawBarColors);

	
	
function drawBarColors() {
	
	
	var json  = $.ajax({
		
		url: "<%=request.getContextPath()%>/admin/chartList/ticketSales.do",
		type: "GET",
		dataType: 'json',
		  async: false
    }).responseText;
	
	
	 var data
     = new google.visualization.DataTable(json);
	     
	    var options = {
	        title: '영화별 티켓 판매량',
	        chartArea: {width: '50%'},
	        colors: ['#806ebc'],
	        width: 1200,
	        height: 800,
	        hAxis: {
	          title: '판매수',
	          minValue: 0
	        },
	        vAxis: {
	          title: 'MovieTitle'
	        }
	      };
	      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
	      chart.draw(data, options);

 
}

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
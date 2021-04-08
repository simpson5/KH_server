<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" />
<style>
td{
text-align: center;
}

/* #month-tbl{
display: none;
}  */
/* 
table#month-tbl {
  display: relative;
    text-align: center;
   
    line-height: 1.5;
    border-style: dotted;
    border-top: 1px solid #ccc; 
    border-bottom: 1px solid #ccc; 
    margin: 20px 10px; 
}

 table#month-tbl thead th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #fff;
    background-color:  #383838;
    margin: 20px 10px;
    font-size:13px;
}
table#month-tbl tbody th {
    width: 150px;
    padding: 10px;
}
table#month-tbl td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    font-size:12px;
}
table#month-tbl .even {
    background: #fdf3f5;
} */
</style>
<script>
window.onload = function () {


}


$(()=>{
	
var $monShow = $("#mon-show");

if($("#mon-show").click()){
		
		$("#month-tbl").show(); 
	}
		
	
});
$(()=> {
	$("#revenueManagement").css("color", "#503396")
					.css("font-weight", "bold");
});

</script>

<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/admin/memberList" id="memberList">회원관리</a></li>
		<li><a href="<%=request.getContextPath() %>/admin/chartList/chartListView.do" id="chartList">매출분석</a></li>
		<li><a href="<%=request.getContextPath() %>/admin/addDeleteMovieNow">현재상영작관리</a></li>
	</ul>
</div>

<button id="mon-show">월 매출 조회</button>
<button>일 매출 조회</button>
<table id="month-tbl">
<thead>
<tr>
	<th>월</th>
	<th>월 매출 총액</th>
	</tr>
</thead>
	
	<tbody>
	
	<tr>
	<td>1</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>2</td>
	<td>test</td>
	</tr>

	<tr>
	<td>3</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>4</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>5</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>6</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>7</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>8</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>9</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>10</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>11</td>
	<td>test</td>
	</tr>
	
	<tr>
	<td>12</td>
	<td>test</td>
	</tr>
	
	</tbody>

</table>


</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
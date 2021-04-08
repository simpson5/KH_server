<%@page import="customer.model.vo.FAQ"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>


<%
%>

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/customerService.css" />

<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/customer/notice/notice.do">공지사항</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/faq/faq.do">FAQ</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/customerService.do" id="customerService">1:1문의</a></li>
	</ul>
</div>

<section id="content-customerService_form">
	<div id="content-customerServiceView">
	
	<h2><span class="sub_title">PixarBox 1:1문의사항</span></h2>
	
	<form id="content-form" action="<%=request.getContextPath()%>/customer/customerService/addCustomerServiceEnd.do" method="post" enctype="multipart/form-data">


<%-- 		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">ID</span>
			</div>
			<input type="text" class="form-control" id="memberId"
				name="memberId" aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" readonly value="<%=memberLoggedIn.getMemberId()%>">
		</div>
 --%>
		<table class="tbl_cs">
		</div>
		
			<tr>
				<th>제목</th>
				
			
				<td>
				<input type="text"
					class="form-control"
					id="memberId"
					name="memberId"
					value="<%=memberLoggedIn.getMemberId()%>" readonly/>
				
				
				<select name="boardType" id="boardType">
						<option value="[일반문의]">일반문의</option>
						<option value="[칭찬]">칭찬</option>
						<option value="[불만]">불만</option>
						<option value="[제안]">제안</option>
				</select> 
				<input type="text" name="boardTitle" id="boardTitle" required/>
				</td>
			</tr>
		
		</table>

		
		<div class="form-group">
			<!-- <label for="exampleFormControlTextarea1" class="labels">내용</label>
		 -->
			<textarea class="form-control" id="boardContent" name="boardContent"
				rows="15" required></textarea>
				<br/><br/>
		</div>

		<div class="form-group">
			<input type="file" class="form-control-file" name="upFile" >
		</div>

	<div class="form-check form-check-inline">
		<p id="test">
	
		 <label for="form-check form-check-inline" >공개여부</label>
	
			<input class="form-check-input" type="radio" name="publicYN"
				id="publicYN1" value="Y" checked> 
				<label class="form-check-label" for="publicYN1">Y</label>
		</input>
			<input class="form-check-input" type="radio" name="publicYN"
				id="publicYN2" value="N"> 
				<label class="form-check-label" for="publicYN2">N</label>
				</input>
		</p>
		</div>

	

		<button type="submit" class="btn btn-secondary btn-lg"
			id="btn-add-movie">등록하기</button>
	</form>



</section>

<script>
$(()=> {
	$("#customerService").css("color", "#503396").css("font-weight", "bold");
});
</script>









<%@ include file="/WEB-INF/views/common/footer.jsp"%>
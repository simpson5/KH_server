<%@page import="customer.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>


<%
	CustomerService c = (CustomerService)request.getAttribute("customerService");
	System.out.println(c.getBoardType()+"boardType");
	
	String selected[] = {"", "", "", ""};
	String boardType =c.getBoardType();
	if("[일반문의]".equals(boardType)) selected[0] = "selected";
	else if("[칭찬]".equals(boardType)) selected[1] = "selected";
	else if("[불만]".equals(boardType)) selected[2] = "selected";
	else if("[제안]".equals(boardType)) selected[3] = "selected";
	
	String checked[] = {"",""};
	String pYN = c.getpublicYN();
	if("Y".equals(pYN)) checked[0] = "checked";
	else if("N".equals(pYN)) checked[1] = "checked";
	
%>

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/customerService.css" />

<div class="tab_type01">
	<ul>
		<li><a
			href="<%=request.getContextPath() %>/customer/notice/notice.do">공지사항</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/faq/faq.do">FAQ</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/customerService/customerService.do" id="customerService">1:1문의</a></li>
	</ul>
</div>

<section id="content-customerService_form">
	<form id="content-form"
		action="<%=request.getContextPath()%>/customer/customerService/CustomerServiceUpdateEnd.do"
		method="post" enctype="multipart/form-data">


		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">ID</span>
			</div>
			<input type="text" class="form-control" id="memberId"
				name="memberId" aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" readonly value="<%=c.getMemberId()%>">
		</div>

		<table class="tbl_cs form">
			<tr>
				<th>제목</th>
				<td><select name="boardType" id="boardType">
						<option value="[일반문의]" <%=selected[0] %>>일반문의</option>
						<option value="[칭찬]" <%=selected[1] %>>칭찬</option>
						<option value="[불만]" <%=selected[2] %>>불만</option>
						<option value="[제안]" <%=selected[3] %>>제안</option>
				</select> <input type="text" name="boardTitle" id="boardTitle" value="<%=c.getBoardTitle()%>" required/></td>
			</tr>
		
		</table>

		<br />
		<div class="form-group">
			<label for="exampleFormControlTextarea1" class="labels">내용</label>
			<br /><br />
			<textarea class="form-control" id="boardContent" name="boardContent"
				rows="15" required><%=c.getBoardContent() %></textarea>
		</div>

		<div class="form-group">
			<input
				type="file" class="form-control-file" name="upFile" >
			<!-- 첨부파일이 있는 경우 파일명 표시 -->
					<div id="file-wrap">
					<span id="fname"><%=c.getOriginalFileName()!=null?c.getOriginalFileName():"" %></span>
					
					<input type="hidden" name="oldOriginalFileName"
						   value="<%=c.getOriginalFileName()!=null?c.getOriginalFileName():""%>" />
					<input type="hidden" name="oldRenamedFileName"
						   value="<%=c.getRenamedFileName()!=null?c.getRenamedFileName():""%>" />
					
					<!-- 기존파일삭제 체크박스-->
					<% if(c.getOriginalFileName()!=null) {%>
					<br />
					<input type="checkbox" name="delFileChk" id="delFileChk" />
					<label for="delFileChk">첨부파일삭제</label>
					<% } %>	
					</div>
					
		</div>
		<input type="hidden" name="boardNo" value="<%=c.getBoardNo()%>"  />

		<br /> <br /> <label for="form-check form-check-inline" >공개여부</label>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="publicYN"
				id="publicYN1" value="Y" <%=checked[0] %>> <label
				class="form-check-label" for="publicYN1">Y</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="radio" name="publicYN"
				id="publicYN2" value="N" <%=checked[1] %>> <label class="form-check-label"
				for="publicYN2">N</label>
		</div>

		<br /> <br />

		<button type="submit" class="btn btn-secondary btn-lg"
			id="btn-add-movie">등록하기</button>
	</form>



</section>

<script>
$("[name=upFile]").change(function(){
	//수정페이지에서 파일태그에 파일을 추가한 경우
	if($(this).val() != ""){
		$("#fname").hide();
		$("#delFileChk").hide().next().hide();
	}
	else{
		$("#fname").show();
		$("#delFileChk").show().next().show();
	}
});
$(()=> {
	$("#customerService").css("color", "#503396").css("font-weight", "bold");
});
</script>









<%@ include file="/WEB-INF/views/common/footer.jsp"%>
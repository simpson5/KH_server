<%@page import="customer.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Notice n = (Notice)request.getAttribute("notice");
	int idx = n.getNoticeTitle().indexOf(" ");
	String noticeType = n.getNoticeTitle().substring(0, idx);
	String noticeTitle = n.getNoticeTitle().substring(idx+1);
	
	String selected[] = {"", ""};
	
	if("[공지]".equals(noticeType)) selected[0] = "selected";
	else if("[이벤트]".equals(noticeType)) selected[1] = "selected";
%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/customerService.css" />

<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/customer/notice/notice.do" id="notice">공지사항</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/faq/faq.do">FAQ</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/customerService/customerService.do">1:1문의</a></li>
	</ul>
</div>

<section id="content-noticeForm">
	<div id="container-noticeView">
		<h2><span class="sub_title">PixarBox 공지사항</span></h2>
		
		<form action="<%=request.getContextPath() %>/customer/notice/noticeUpdateEnd.do" method="post" enctype="multipart/form-data" >
			<table class="tbl_notice form">
				<tr>
					<th>제목</th>
					<td>
						<select name="noticeType" id="noticeType">
							<option value="[공지]" <%=selected[0] %>>공지</option>
							<option value="[이벤트]" <%=selected[1] %>>이벤트</option>
						</select>
						<input type="text" name="noticeTitle" id="noticeTitle" value="<%=noticeTitle %>" />
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td style="position:relative;">
						<input type="file" name="upFile" id="upFile" />
						<span id="fname"><%=n.getNoticeOriginalFileName()!=null?n.getNoticeOriginalFileName():"" %></span>
							
						<input type="hidden" name="oldOriginalFileName" value="<%=n.getNoticeOriginalFileName()!=null?n.getNoticeOriginalFileName():"" %>" />
						<input type="hidden" name="oldRenamedFileName" value="<%=n.getNoticeRenamedFileName()!=null?n.getNoticeRenamedFileName():"" %>" />
						<!-- 기존파일삭제 체크박스 -->
						<% if(n.getNoticeOriginalFileName()!=null) { %>
						<br />
						<input type="checkbox" name="delFileChk" id="delFileChk" />
						<label for="delFileChk">첨부파일삭제</label>
						<% } %>
					</td>
				</tr>
				<!-- <tr>
					<th>등록일</th>
					<td><input type="date" name="noticeDate" id="noticeDate" /></td>
				</tr> -->
			</table>
			<div class="div_contents form">
				<textarea name="noticeContent" id="notice_content" cols="110" rows="30"><%=n.getNoticeContent() %></textarea>
				<br /><br />
				<input type="submit" value="수정" onclick="return noticeValidate();" />
				<input type="reset" value="초기화" />
			</div>
			<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo() %>" />
		</form>
		
		<button class="btn_tolist" onclick="toNoticeList();">목록</button>
	
	
	</div>


</section>

<script>
//document.getElementById('noticeDate').value = new Date().toISOString().substring(0, 10);
$("[name=upFile]").change(function() {
	 //수정페이지에서 파일태그에 파일을 추가한 경우
	if($(this).val() != "") {
		$("#fname").hide();
		$("#delFileChk").hide().next().hide();
	}
	else {
		$("#fname").show();
		$("#delFileChk").show().next().show();
	}
});

function noticeValidate() {
	//제목검사
	var $title = $("[name=noticeTitle]");
	if($title.val().trim().length == 0) {
		alert("제목을 입력하세요.");
		return false;
	}
	
	//내용검사
	var $content = $("[name=noticeContent]");
	if($content.val().trim().length == 0) {
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}

function toNoticeList() {
	location.href = "<%=request.getContextPath() %>/customer/notice/notice.do";
}
$(()=> {
	$("#notice").css("color", "#503396").css("font-weight", "bold");
});
</script>






<%@ include file="/WEB-INF/views/common/footer.jsp" %>
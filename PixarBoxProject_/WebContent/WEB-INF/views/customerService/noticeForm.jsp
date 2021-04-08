<%@page import="customer.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
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
	<div id="container-noticeForm">
		<h2><span class="sub_title">PixarBox 공지사항</span></h2>
		
		<form action="<%=request.getContextPath() %>/customer/notice/noticeInsert.do" method="post" enctype="multipart/form-data" >
			<table class="tbl_notice form">
				<tr>
					<th>제목</th>
					<td>
						<select name="noticeType" id="noticeType">
							<option value="[공지]">공지</option>
							<option value="[이벤트]">이벤트</option>
						</select>
						<input type="text" name="noticeTitle" id="noticeTitle" />
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="upFile" id="upFile" />
					</td>
				</tr>
				<!-- <tr>
					<th>등록일</th>
					<td><input type="date" name="noticeDate" id="noticeDate" /></td>
				</tr> -->
			</table>
			<div class="div_contents form">
				<textarea name="noticeContent" id="notice_content" cols="110" rows="30"></textarea>
				<br /><br />
				<input type="submit" value="등록" onclick="return noticeValidate();" />
				<input type="reset" value="초기화" />
			</div>
		</form>
		
		<button class="btn_tolist" onclick="toNoticeList();">목록</button>
	
	
	</div>


</section>

<script>
//document.getElementById('noticeDate').value = new Date().toISOString().substring(0, 10);
function noticeValidate() {
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
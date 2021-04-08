<%@page import="customer.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Notice n = (Notice)request.getAttribute("notice");
%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/customerService.css" />

<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/customer/notice/notice.do" id="notice">공지사항</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/faq/faq.do">FAQ</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/customerService/customerService.do">1:1문의</a></li>
	</ul>
</div>

<section id="content-noticeView">
	<div id="container-noticeView">
		<h2><span class="sub_title">PixarBox에서 최신 공지사항을 알려드립니다.</span></h2>
		
		<table class="tbl_notice view">
			<tr>
				<th>제목</th>
				<td><%=n.getNoticeTitle() %></td>
				<th>등록일</th>
				<td><%=n.getNoticeDate() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<!-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 -->
					<% if(n.getNoticeOriginalFileName() != null) { %>
					<a href="javascript:fileDownload('<%=n.getNoticeOriginalFileName() %>', '<%=n.getNoticeRenamedFileName() %>');">
						<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width="16px" /><%=n.getNoticeOriginalFileName() %>
					</a>
					<% } %>
				</td>
			</tr>
		</table>
		<div class="div_contents">
			<% if(n.getNoticeOriginalFileName()!=null && (n.getNoticeOriginalFileName().contains(".jpg") || n.getNoticeOriginalFileName().contains(".png") || n.getNoticeOriginalFileName().contains(".pdf"))) { %>
			<img src="<%=request.getContextPath() %>/upload/notice/<%=n.getNoticeRenamedFileName() %>" alt="" />
			<% } %>
			<%=n.getNoticeContent() %>
			<br /><br />
		</div>
		<% if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) { %>
		<input type="button" value="수정" onclick="updateNotice(<%=n.getNoticeNo() %>);" />
		<input type="button" value="삭제" onclick="deleteNotice();" />
		<% } %>
		<button class="btn_tolist" onclick="toNoticeList();">목록</button>
		<form name="noticeDelFrm" action="<%=request.getContextPath()%>/customer/notice/noticeDelete.do" method="post">
			<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo() %>" />
	    	<input type="hidden" name="renamedFileName" value="<%=n.getNoticeRenamedFileName()!=null?n.getNoticeRenamedFileName():"" %>" />
	    </form>
	
	</div>


</section>

<script>
function toNoticeList() {
	location.href = "<%=request.getContextPath() %>/customer/notice/notice.do";
}
function updateNotice(noticeNo) {
	location.href = "<%=request.getContextPath() %>/customer/notice/noticeUpdate.do?noticeNo="+noticeNo; 
}
function deleteNotice() {
	if(!confirm("정말로 삭제하시겠습니까??")) return;
	$("[name=noticeDelFrm]").submit();
	<%-- location.href = "<%=request.getContextPath() %>/customer/notice/noticeDelete.do"; --%>
}

function fileDownload(oName, rName) {
	//ie대비 한글인코딩 명시적처리
	oName = encodeURIComponent(oName);
	console.log(oName);
	
	location.href = "<%=request.getContextPath() %>/customer/notice/fileDownload.do?oName="+oName+"&rName="+rName;
	
}
$(()=> {
	$("#notice").css("color", "#503396").css("font-weight", "bold");
});
</script>























<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<%@page import="customer.model.vo.FAQ"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	FAQ f = (FAQ)request.getAttribute("faq");
	
	String selected[] = {"", ""};
	if("멤버십".equals(f.getFaqType())) selected[0] = "selected";
	else if("영화예매".equals(f.getFaqType())) selected[1] = "selected";
%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/customerService.css" />

<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/customer/notice/notice.do">공지사항</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/faq/faq.do" id="faq">FAQ</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/customerService/customerService.do">1:1문의</a></li>
	</ul>
</div>

<section id="content-faq_update_form">
	<div id="container-noticeView">
		<h2><span class="sub_title">PixarBox 자주묻는질문</span></h2>
		
		<form action="<%=request.getContextPath() %>/customer/faq/faqUpdateEnd.do" method="post" >
			<table class="tbl_notice form">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="faqCode" id="faqCode" value="<%=f.getFaqCode() %>" readonly />
						<select name="faqType" id="faqType">
							<option value="멤버십" <%=selected[0] %>>멤버십</option>
							<option value="영화예매" <%=selected[1] %>>영화예매</option>
						</select>
						<input type="text" name="faqTitle" id="faqTitle" value="<%=f.getFaqTitle() %>" />
					</td>
				</tr>
				<!-- <tr>
					<th>등록일</th>
					<td><input type="date" name="noticeDate" id="noticeDate" /></td>
				</tr> -->
			</table>
			<div class="div_contents form">
				<textarea name="faqContent" id="faq_content" cols="110" rows="30"><%=f.getFaqContent() %></textarea>
				<br /><br />
				<input type="submit" value="등록" />
				<input type="reset" value="초기화" />
			</div>
		</form>
		
		<button class="btn_tolist" onclick="toFaqList();">목록</button>
	
	
	</div>
</section>

<script>
function toFaqList() {
	location.href = "<%=request.getContextPath() %>/customer/faq/faq.do";
}
$(()=> {
	$("#faq").css("color", "#503396").css("font-weight", "bold");
});
</script>









<%@ include file="/WEB-INF/views/common/footer.jsp" %>
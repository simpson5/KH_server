<%@page import="customer.model.vo.FAQ"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<FAQ> list = (List<FAQ>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/customerService.css" />

<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/customer/notice/notice.do">공지사항</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/faq/faq.do" id="faq">FAQ</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/customerService/customerService.do">1:1문의</a></li>
	</ul>
</div>

<section id="content-faq">
	<div id="center_faq">
	<h2><span class="sub_title">PixarBox 자주묻는질문</span></h2>
		<div class="header">
				<span class="hd1"></span>
				<span class="hd2">분류</span>
				<span class="hd3">질문</span>
		</div>
		<%
			if(!list.isEmpty()) {
				for(FAQ f : list) {
		%>
		<div class="panel">
			<a href="#<%=f.getFaqCode() %>">
				<span class="hd1"><%=f.getFaqCode().substring(3) %></span>
				<span class="hd2"><%=f.getFaqType() %></span>
				<span class="hd3"><%=f.getFaqTitle() %></span>
				<% if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) { %>
				<button class="btn_update_faq" onclick="updateFaq(<%=f.getFaqCode() %>);">수정</button>
				<button class="btn_delete_faq" onclick="deleteFaq(<%=f.getFaqCode() %>);">삭제</button>
				<% } %>
			</a>
			<div id="<%=f.getFaqCode() %>" class="faq_body">
				<div class="faq_body2">
				<%=f.getFaqContent() %>
				</div>
			</div>
		</div>
		<%
				}
			}
		%>
		<!-- <div class="panel">
			<a href="#">
				<span class="hd1">1</span>
				<span class="hd2">멤버십</span>
				<span class="hd3">포인트적립을 못받았어요. 어떻게 하나요?</span>
			</a>
			<div id="faq02" class="faq_body">
				<div class="faq_body2">
				<p>멤버십 포인트 적립은 상영시간 전까지만 가능하며,
				<br />상영시간이 지난 티켓에 대해서는 사후 적립이 불가합니다.</p>

				<p>(이용약관 '제 13조 회원의 특전'에 의거 /  http://megabox.co.kr/?menuId=terms)</p>
				</div>
			</div>
		</div> -->
	</div>
	
	<div class="pageBar">
		<ul class="pageBar_list">
		<%=pageBar %>
		</ul>
	</div>
	
	<% if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())) { %>
	<button class="btn_on" onclick="location.href='<%=request.getContextPath() %>/customer/faq/faqInsert.do'">작성</button>
	<% } %>
	
</section>

<script>
$(function() {
	
	$(".panel").on("click", function(e) {
		//console.log($(this));
		$(".faq_body").css("display", "none");
		$('span.hd3').css("color", "#333").css("font-weight", "500");
		//$(this).children('div').css("display", "inline-block");
		$(this).children('div.faq_body').slideDown(100)
			   .children('div.faq_body2').slideDown(100);
		$(this).children('a').children('span.hd3').css("color", "#503396").css("font-weight", "700");
	});
});

function deleteFaq(faqCode) {
	if(!confirm("정말로 삭제하시겠습니까?")) return;
	//console.log(faqCode.id);
	location.href = "<%=request.getContextPath() %>/customer/faq/deleteFaq.do?faqCode="+faqCode.id;
}

function updateFaq(faqCode) {
	location.href = "<%=request.getContextPath() %>/customer/faq/updateFaq.do?faqCode="+faqCode.id;
}
$(()=> {
	$("#faq").css("color", "#503396").css("font-weight", "bold");
});
</script>













<%@ include file="/WEB-INF/views/common/footer.jsp" %>
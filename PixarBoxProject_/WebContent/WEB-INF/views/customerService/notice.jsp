<%@page import="customer.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Notice> list = (List<Notice>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/customerService.css" />

<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/customer/notice/notice.do" id="notice">공지사항</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/faq/faq.do">FAQ</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/customerService/customerService.do">1:1문의</a></li>
	</ul>
</div>

<section id="content-notice">
    <div class="notice_list">
    	<h2><span class="sub_title">PixarBox에서 최신 공지사항을 알려드립니다.</span></h2>
		
        <table class="tbl_notice">
            <tr>
                <th>No</th>
                <th style="width: 100px;"></th>
                <th>공지사항</th>
                <th>날짜</th>
            </tr>
            <%
            	if(!list.isEmpty()) {
            		for(Notice n : list) {
            %>
            <tr>
                <td align="center"><%=n.getNoticeNo() %></td>
                <td></td>
                <td align="left">
                	<a href="<%=request.getContextPath() %>/customer/notice/noticeView.do?noticeNo=<%=n.getNoticeNo() %>">
                		<%=n.getNoticeTitle() %>
                	</a>
                </td>
                <td align="center"><%=n.getNoticeDate() %></td>
            </tr>            
            <%
            		}
            	}
            	else {
            %>
            <tr>
            	<td colspan="4">공지사항이 없습니다.</td>
            </tr>
            <% } %>
        </table>
        
        <div class="pageBar">
        	<ul class="pageBar_list">
        	<%=pageBar %>
        	</ul>
        </div>
    </div>
    <% if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())) { %>
    <input type="button" value="공지등록" id="btn-add" onclick="location.href='<%=request.getContextPath() %>/customer/notice/noticeForm.do';" />
    <% } %>
</section>

<script>
$("ul#pageBar_list>li>a").click(function() {
	console.log($(this));
	$(this).css("background", "#503396");
});

$(()=> {
	$("#notice").css("color", "#503396").css("font-weight", "bold");
});
</script>















<%@ include file="/WEB-INF/views/common/footer.jsp" %>
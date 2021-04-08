<%@page import="customer.model.vo.Notice"%>
<%@page import="java.util.List, customer.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	List<CustomerService> list = (List<CustomerService>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	
%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/customerService.css" />

<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/customer/notice/notice.do">공지사항</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/faq/faq.do">FAQ</a></li>
		<li><a href="<%=request.getContextPath() %>/customer/customerService/customerService.do" id="customerService">1:1문의</a></li>
	</ul>
</div>

<section id="content-customerService">
	<div id="center_customerService">
		<h2><span class="sub_title">궁금한 점이 있다면 1:1 문의를 해주세요.</span></h2>
		<button id="add-customerService" onclick="addFunc();">문의하기</button>
	 
		     <table class="tbl_notice">
            <tr>
                <th>No</th>
                <th>문의유형</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>날짜</th>
                <th>공개</th>
            </tr>
            <%
            
        		int num = 1;
            	if(!list.isEmpty()) {
            		for(CustomerService c : list) {
            %>
            <tr>
                <td align="center"><%=num++ %></td>
                
              
                <td align="center"><%=c.getBoardType() %></td>
                  <td align="left">
                  	<% if(memberLoggedIn!=null&&(c.getMemberId().equals(memberLoggedIn.getMemberId())||memberLoggedIn.getMemberId().equals("admin"))) {%>
                  			<a href="<%=request.getContextPath() %>/customer/customerService/customerServiceView.do?boardNo=<%=c.getBoardNo() %>">
                			<%=c.getBoardTitle() %>
                			</a>
                  	<% } else if(c.getpublicYN().equals("Y")){ %> 
                  			<a href="<%=request.getContextPath() %>/customer/customerService/customerServiceView.do?boardNo=<%=c.getBoardNo() %>">
                			<%=c.getBoardTitle() %>
                			</a>
                  	<%
                  	}else{
                  	%>
                  			<a href="#" onclick="alert('비공개 글 입니다.')";">
                			<%=c.getBoardTitle() %>
                			</a>
             	
                  	<% } %>
                  	
                
                </td>
                <td align="left"><%=c.getMemberId() %></td>
                <td align="center"><%=c.getBoardDate() %></td>
                <td align="center"><%=c.getpublicYN().equals("Y")?"공개":"비공개"%></td>
             
                
            </tr> 
            
            
          

			          
            <%
            		}
            	}
            	else {
            %>
            <tr>
            	<td colspan="4">문의글이 없습니다.</td>
            </tr>
            <% } %>
        </table> 

        	
	
	</div>
	
	 <div class="pageBar">
		<ul class="pageBar_list">
		<%=pageBar %>
		</ul>
	</div> 
	
       
</section>

<script>


function addFunc(){
	
	<% if(memberLoggedIn == null){%>
			alert("로그인 후 이용해 주세요.");
			location.href="<%=request.getContextPath() %>/member/memberLogin";
	
	<%}else{%>

			location.href="<%=request.getContextPath() %>/customer/customerService/addCustomerService.do";
	
	<%}%>
}


$("ul#pageBar_list>li>a").click(function() {
	console.log($(this));
	$(this).css("background", "#503396");
});
$(()=> {
	$("#customerService").css("color", "#503396").css("font-weight", "bold");
});
</script>















<%@ include file="/WEB-INF/views/common/footer.jsp" %>
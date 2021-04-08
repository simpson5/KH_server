<%@page import="customer.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	CustomerService c = (CustomerService) request.getAttribute("customerService");
	CsComment cc = (CsComment) request.getAttribute("cscomment");


%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/customerService.css" />

<div class="tab_type01">
	<ul>
		<li><a
			href="<%=request.getContextPath()%>/customer/notice/notice.do">공지사항</a></li>
		<li><a href="<%=request.getContextPath()%>/customer/faq/faq.do">FAQ</a></li>
		<li><a href="<%=request.getContextPath()%>/customer/customerService/customerService.do" id="customerService">1:1문의</a></li>
	</ul>
</div>

<section id="content-CSView">
	<div id="container-CSView">
		<h2>
			<span class="sub_title">궁금한 점이 있다면 1:1문의 해주세요.</span>
		</h2>

		<table class="tbl_notice view">
			<tr>
				<%-- <th>문의 유형</th>
				<td><%=c.getBoardType()%></td> --%>
				<th>제목</th>
				<td><%=c.getBoardType()+" "+c.getBoardTitle()%></td>

				<th>작성자</th>
				<td><%=c.getMemberId()%></td>
			</tr>
			<tr>
				<th>파일</th>
				<td>
					<%
						if (c.getOriginalFileName() != null) {
					%> <a
					href="javascript:fileDownload('<%=c.getOriginalFileName()%>','<%=c.getRenamedFileName()%>');">
						<img alt="첨부파일"
						src="<%=request.getContextPath()%>/images/file.png" width=16px><%=c.getOriginalFileName()%>
				</a> <%
 	}
 %>
				</td>
				<th>등록일</th>
				<td><%=c.getBoardDate()%></td>
			</tr>
		</table>

		<div class="div_contents">
			<%=c.getBoardContent()%>
			<br />
			<br />
		</div>

		<%
			if (memberLoggedIn != null && ("admin".equals(memberLoggedIn.getMemberId())
					|| c.getMemberId().equals(memberLoggedIn.getMemberId()))) {
		%>
		<input type="button" value="수정"
			onclick="updateCS(<%=c.getBoardNo()%>);" /> <input type="button"
			value="삭제" onclick="deleteCS(<%=c.getBoardNo()%>);" />
		<%
			}
		%>
		<button class="btn_tolist" onclick="toCustomerServiceList();">목록</button>


	</div>

	<div id="content-CsComment">
		<h2>
			<span class="sub_title">답변</span>
		</h2>

		<form
			action="<%=request.getContextPath()%>/customer/csComment/csCommentInsert.do"
			method="post">

		<%
			if (memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())) {
		%>
			<textarea name="cscomment" id="cscomment" cols="30" rows="10"><%=cc.getCscomment()!=null?cc.getCscomment():"" %>
			</textarea>
			
			<input type="hidden" name="boardNo" value="<%=c.getBoardNo()%>"/>
			
			<%if(cc.getCscomment()==null){ %>
				<button type="submit" id="cscomment-insert">댓글 등록</button>
			
		
			<% } else{%>
			
				<button type="button" id="deleteCc-btn" onclick="deleteCc(<%=cc.getCscommentNo()%>,<%=c.getBoardNo()%>)">댓글 삭제</button>
			<% } %>
		</form>
		<%
			
			}else{
		%>
				<textarea name="cscomment" id="cscomment" cols="30" rows="10" readonly><%=cc.getCscomment()!=null?cc.getCscomment():"답변이 작성되지 않았습니다." %>
				</textarea>
			
		<% } %>
		
	</div>


</section>

<script>
function deleteCc(ccno,bno){

	location.href = "<%=request.getContextPath()%>/customer/csComment/deleteCsComment.do?csCommentNo="+ccno+"&boardNo="+bno;
}


function toCustomerServiceList() {
	location.href = "<%=request.getContextPath()%>/customer/customerService/customerService.do";
}
function updateCS(boardNo) {
	location.href = "<%=request.getContextPath()%>/customer/customerService/customerServiceUpdate.do?boardNo="+boardNo; 
}
function deleteCS(boardNo) {
	if(!confirm("정말로 삭제하시겠습니까??")) return;
	location.href = "<%=request.getContextPath()%>/customer/customerService/customerServiceDelete.do?boardNo="+boardNo;
}
$(()=> {
	$("#customerService").css("color", "#503396").css("font-weight", "bold");
});
</script>




<%@ include file="/WEB-INF/views/common/footer.jsp"%>
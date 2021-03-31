<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	//List<Member> list = null;
	List<Member> list = (List<Member>)request.getAttribute("list");
	String type = (String)request.getParameter("searchType");
	String kw = (String)request.getParameter("searchKeyword");
%>
<%!
public Object nullCheck(Object str){
	return str != null ? str : "";
}
public String checked(String str, String type){
	return str.equals(type) ? "selected" : "";
}
public String kwCheck(String str, String type, String kw){
	return str.equals(type) ? kw : "";
}
%>
<!-- 관리자용 admin.css link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<style>
div#search-container {margin:0 0 10px 0; padding:3px; background-color: rgba(0, 188, 212, 0.3);}
div#search-memberId {display: <%= type == null || "memberId".equals(type) ? "inline-block" : "none" %>;}
div#search-memberName{display: <%= "memberName".equals(type) ? "inline-block" : "none" %>;}
div#search-gender{display: <%= "gender".equals(type) ? "inline-block" : "none" %>;}
</style>
<section id="memberList-container">
	<h2>회원관리</h2>
	<div id="search-container">
		검색타입 : 
        <select id="searchType">
            <option value="memberId" <%=checked("memberId", type) %>>아이디</option>		
            <option value="memberName" <%=checked("memberName", type) %>>회원명</option>
            <option value="gender" <%=checked("gender", type) %>>성별</option>
        </select>
        <div id="search-memberId" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="memberId"/>
                <input type="text" name="searchKeyword"  size="25" 
               	value = "<%= kwCheck("memberId", type, kw) %>"
                placeholder="검색할 아이디를 입력하세요."/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-memberName" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="memberName"/>
                <input type="text" name="searchKeyword" size="25"
                value = "<%= kwCheck("memberName", type, kw) %>"
                placeholder="검색할 이름을 입력하세요."/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-gender" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="gender"/>
                <input type="radio" name="searchKeyword" value="M" <%= "gender".equals(type) && "M".equals(kw) ? "checked" : "" %>> 남
                <input type="radio" name="searchKeyword" value="F" <%= "gender".equals(type) && "F".equals(kw) ? "checked" : "" %>> 여
                <button type="submit">검색</button>
            </form>
        </div>
    </div>
	
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>회원권한</th>
				<th>성별</th>
				<th>생년월일</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>취미</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody>
		<% if(list ==null || list.isEmpty()) { %>
			<tr>
				<td colspan="10" style="text-align:center;"> 조회된 회원이 없습니다. </td>
			</tr>
		<% 
		   }
		   else {
			   for(Member m : list){
				   
		%>
			<tr>
				<td><%= m.getMemberID() %></td>
				<td><%= m.getMemberName() %></td>
				<td>
					<select class="member-role" data-member-id="<%= m.getMemberID() %>">
						<option 
							value="<%= MemberService.ADMIN_ROLE %>"
							<%= MemberService.ADMIN_ROLE.equals(m.getMemberRole()) ? "selected" : ""  %>>관리자</option>
						<option 
							value="<%= MemberService.MEMBER_ROLE %>"
							<%= MemberService.MEMBER_ROLE.equals(m.getMemberRole()) ? "selected" : ""  %>>일반</option>
					</select>
				</td>
				<td><%= "M".equals(m.getGender()) ? "남" : "여" %></td>
				<td><%= nullCheck(m.getBirthday()) %></td>
				<td><%= nullCheck(m.getEmail()) %></td>
				<td><%= m.getPhone() %></td>
				<td><%= nullCheck(m.getAddress()) %></td>
				<td><%= nullCheck(m.getHobby()) %></td>
				<td><%= m.getEnrolldate() %></td>
			</tr>
		<% 
			   }
		   } 
		%>
		</tbody>
	</table>
</section>
<form
	action="<%=request.getContextPath() %>/admin/memberRoleUpdate"
	name="memberRoleUpdateFrm"
	method="POST">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="memberRole" />
</form>
<script>
/**
 * #searchType가 변경되면, 해당 form이 노출되어야한다.
 */
$(searchType).change(function(){
	var searchTypeVal = $(this).val();
	$(".search-type")
		.hide()
		.filter("#search-" + searchTypeVal)
		.css("display","inline-block");
});

$(".member-role").on("change", function(){
	var memberId = $(this).data("memberId");
	var memberRole = $(this).val();
	//alert(memberId + " : " + memberRole);
	
	var memberRoleStr =
		memberRole == "<%= MemberService.ADMIN_ROLE%>" ?
				"관리자" : "일반";
	if(confirm("[" + memberId + " ] 회원의 권한을 [" + memberRoleStr + "] 로 변경하시겠습니까?")){
		var $frm = $(document.memberRoleUpdateFrm);
		$frm.find("[name=memberId]").val(memberId);
		$frm.find("[name=memberRole]").val(memberRole);
		$frm.submit();
	}
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

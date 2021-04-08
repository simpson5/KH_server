<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" />

<div class="tab_type01">
	<ul>
		<li><a href="<%=request.getContextPath() %>/admin/memberList" id="memberList">회원관리</a></li>
		<li><a href="<%=request.getContextPath() %>/admin/chartList/chartListView.do">매출분석</a></li>
		<li><a href="<%=request.getContextPath() %>/admin/addDeleteMovieNow">현재상영작관리</a></li>
	</ul>
</div>
<style>
section.listAll{padding-bottom:20%; 

}

#memberList-container{
	padding-left:5%;
	padding-right:5%;
	position: relative;
}
#search-memberId {display: <%="memberId".equals(searchType)||searchType==null?"inline-block":"none"%>;}
#search-memberName {display: <%="memberName".equals(searchType)?"inline-block":"none"%>;}
#search-memberEmail{display: <%="memberEmail".equals(searchType)?"inline-block":"none"%>;}
#search-memberPhone{display: <%="memberPhone".equals(searchType)?"inline-block":"none"%>;}
#search-memberGender{display: <%="memberGender".equals(searchType)?"inline-block":"none"%>;}
#search-memberAge{display: <%="memberAge".equals(searchType)?"inline-block":"none"%>;}
#search-memberPoint{display: <%="memberPoint".equals(searchType)?"inline-block":"none"%>;}


div#pageBar{
	margin-top: 10px;
	text-align: center;

}
div#pageBar span.cPage{
	color: #06f;
	margin-right: 10px;
}

div#pageBar a{
	margin-right: 10px;
}

</style>
<script>
$(()=>{
	var $searchMemberId = $("#search-memberId");
	var $searchMemberName = $("#search-memberName");
	var $searchMemberEmail = $("#search-memberEmail");
	var $searchMemberPhone = $("#search-memberPhone");
	var $searchMemberGender = $("#search-memberGender");
	var $searchMemberAge = $("#search-memberAge");
	var $searchMemberPoint = $("#search-memberPoint");
	
	
	$("#searchType").change(function(){
		$searchMemberId.hide();
		$searchMemberName.hide();
		$searchMemberEmail.hide();
		$searchMemberPhone.hide();
		$searchMemberGender.hide();
		$searchMemberAge.hide();
		$searchMemberPoint.hide();
	
		
		$("#search-"+$(this).val()).css("display", "inline-block");
	});
	
	$("input:submit").click(function() {
		$searchMemberId.hide();
		$searchMemberName.hide();
		$searchMemberEmail.hide();
		$searchMemberPhone.hide();
		$searchMemberGender.hide();
		$searchMemberAge.hide();
		$searchMemberPoint.hide();
		
		//console.log($(this).val());
		
		$("#search-"+$(this).val()).css("display", "inline-block");
	});
	
});
$(()=> {
	$("#memberList").css("color", "#503396")
					.css("font-weight", "bold");
});
</script>
<body>
<section class="listAll" id="memberList-container">
	<br /><br /><br />
	<div id="search-container">
		<!-- <label for="searchType">검색타입 : </label> -->
		<br /><br />
		<select id="searchType">
			<option value="memberId" <%="memberId".equals(searchType)?"selected":""%>>아이디</option>
			<option value="memberName" <%="memberName".equals(searchType)?"selected":""%>>이름</option>
			<option value="memberEmail" <%="memberEmail".equals(searchType)?"selected":""%>>이메일</option>
			<option value="memberPhone" <%="memberPhone".equals(searchType)?"selected":""%>>핸드폰</option>
			<option value="memberGender" <%="memberGender".equals(searchType)?"selected":"" %>>성별</option>
			<option value="memberAge" <%="memberAge".equals(searchType)?"selected":"" %>>나이대</option>
			<option value="memberPoint" <%="memberPoint".equals(searchType)?"selected":""%>>포인트잔액</option>
			
		</select>
		<div id="search-memberId">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberId" />
				<input type="search" name="searchKeyword" id="srch"
					   size="25" 
					   placeholder="검색할 아이디를 입력하세요" value="<%="memberId".equals(searchType)?searchKeyword:""%>"/>
				<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberName">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberName" />
				<input type="search" name="searchKeyword" id="srch"
					   size="25" 
					   placeholder="검색할 회원명을 입력하세요" value="<%="memberName".equals(searchType)?searchKeyword:""%>"/>
				<input type="submit" value="검색" />
			</form>
		</div>
		<!--이메일로 찾기  -->
		<div id="search-memberEmail">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberEmail" />
				<input type="search" name="searchKeyword" id="srch"
					   size="25" 
					   placeholder="검색할 이메일을 입력하세요" value="<%="memberEmail".equals(searchType)?searchKeyword:""%>"/>
				<input type="submit" value="검색" />
			</form>
		</div>
			
		<!--핸드폰으로 찾기  -->
		<div id="search-memberPhone">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberPhone" />
				<input type="search" name="searchKeyword" id="srch"
					   size="25" 
					   placeholder="검색할 핸드폰을 입력하세요" value="<%="memberPhone".equals(searchType)?searchKeyword:""%>"/>
				<input type="submit" value="검색" />
			</form>
		</div>
		
		<!-- 성별로 찾기 -->
		<div id="search-memberGender">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberGender" />
				<input type="radio" name="searchKeyword" value="F" <%="F".equals(searchKeyword)?"checked":"" %> />여자
				<input type="radio" name="searchKeyword" value="M" <%="M".equals(searchKeyword)?"checked":"" %> />남자
				<input type="submit" value="검색" />
			</form>
		</div>
		
		<!-- 나이대로 찾기 -->
		<div id="search-memberAge">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberAge" />
				<input type="radio" name="searchKeyword" value="10" <%="10".equals(searchKeyword)?"checked":"" %> />10대
				<input type="radio" name="searchKeyword" value="20" <%="20".equals(searchKeyword)?"checked":"" %> />20대
				<input type="radio" name="searchKeyword" value="30" <%="30".equals(searchKeyword)?"checked":"" %> />30대
				<input type="radio" name="searchKeyword" value="40" <%="40".equals(searchKeyword)?"checked":"" %> />40대
				<input type="radio" name="searchKeyword" value="50" <%="50".equals(searchKeyword)?"checked":"" %> />50대
				<input type="radio" name="searchKeyword" value="60" <%="60".equals(searchKeyword)?"checked":"" %> />60대 이상
				<input type="submit" value="검색" />
			</form>
		</div>
		<!--포인트으로 찾기  -->
		<div id="search-memberPoint">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
			<p id="ptest">
				<input type="hidden" name="searchType" value="memberPoint" />
				<input type="number" name="searchKeyword1" id="searchKeyword1" value="0" placeholder="0" /> ~
				<input type="number" name="searchKeyword2" id="searchKeyword2" placeholder="5000" />
				<!-- <input type="search" name="searchKeyword" 
					   size="25" 
					   placeholder="검색할 포인트를 입력하세요"/> -->
				<input type="submit" value="검색" value="<%="memberPoint".equals(searchType)?searchKeyword:""%>"/>
			
			</p>
			</form>
		</div>
	</div>
	
	
	<table id="tbl-member">
		<thead>
			<tr>
			
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>핸드폰</th>
				<th>성별</th>
				<th>나이대</th>
				<th>관심장르</th>
				<th>포인트잔액</th>
				<th>가입일자</th>
			</tr>
		</thead>
		<tbody id="mList-tbl">
		<% if(list==null || list.isEmpty()){ %>
            <tr>
                <td colspan="9" align="center"> 검색 결과가 없습니다. </td>
            </tr>
        <% 
            } 
            else {
                for(Member m : list){ 
        %>
             <tr>
               <%--  
              	회원클릭시 회원상세보기 
               <td>
	                <a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=m.getMemberId()%>">
		                <%=m.getMemberId()%>
	                </a>
	            </td> --%>
	                <td>
			            <a href="<%=request.getContextPath()%>/admin/memberView?memberId=<%=m.getMemberId()%>">
	                		<%=m.getMemberId()%>
	                	</a>
	                </td>
	                <td><%=m.getLastName()%> <%=m.getFirstName() %></td>
	                <td><%=m.getEmail()!=null?m.getEmail():""%></td>
	                <td><%=m.getPhone()%></td>
	                <td><%=m.getGender() %></td>
	                <td><%=m.getAge() %>대</td>
	                <td><%=m.getInterests()!=null?m.getInterests():""%></td>
	                <td><%=m.getPoint()%></td> 
	                <td><%=m.getEnrollDate()%></td>		
            </tr>		
        <%		} 
            }
        %>
		
		</tbody>
	</table>
	
	<div id="pageBar">
		<%=pageBar %>
	</div>
	
	
	
	
	
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
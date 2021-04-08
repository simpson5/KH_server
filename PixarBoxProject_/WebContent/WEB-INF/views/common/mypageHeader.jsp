<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//로그인한 경우
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	
	//쿠키관련
	boolean saveId = false;
	String loginId = "";
	Cookie[] cookies = request.getCookies();
	if(cookies != null) {
		for(Cookie c : cookies) {
			String k = c.getName();
			String v = c.getValue();
			
			if("saveId".equals(k)) {
				saveId = true;
				loginId = v;
			}
		}
	}
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>PIXARBOX</title>

<!-- css -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/style.css" />
<!-- jquery -->
<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>
<!--웹폰트추가  -->
<link href="https://fonts.googleapis.com/css?family=Anton|Do+Hyeon|Nanum+Gothic+Coding&display=swap&subset=latin-ext,vietnamese" rel="stylesheet">
</head>
<body>
	<!-- 두번째 헤더 -->

	<!-- 고정헤더 -->
	    <nav id="topUtil" class="top_util">
	        <span>PIXAR</span>
	        <ul class="nav">
	        <% if(memberLoggedIn == null) { %>
	            <li>
	                <a href="<%=request.getContextPath() %>/member/memberLogin" id="login_btn">로그인</a>        
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/member/memberEnroll" id="sign_btn">회원가입</a>   
	
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/customer/notice/notice.do" id="customer_btn">고객센터</a>
	            </li>
	        <% }
	        else { %>
	        	<li>
	                <a href="<%=request.getContextPath() %>" id="home">홈</a>   
	
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/member/memberLogout" id="sign_out_btn">로그아웃</a>   
	
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/member/my/mypage.do?memberId=<%=memberLoggedIn.getMemberId() %>" id="mypage_btn">마이페이지</a>   
	            </li>
	            <li>
	                <a href="<%=request.getContextPath() %>/customer/notice/notice.do" id="customer_btn">고객센터</a>
	            </li>
	    <% } %>
	        </ul>
	    </nav>



	<!-- 기본 헤더와 다른 마이페이지 부분 -->

	<div class="section-mypage-top">
		<div class="mypage-top-left">
			<strong class="mypage-id" id="nickname"><%=memberLoggedIn.getLastName()+memberLoggedIn.getFirstName() %> <span>님</span>
			</strong>
			<div class="mypoint">
				<span>포인트</span>
				<p>
					<strong><%=memberLoggedIn.getPoint() %></strong>
				</p>
			</div>
		</div>
	</div>


	</header>

	<!-- 헤더 끝 -->
	
	<body>
		<div class="tab_type01">
		<ul>
			<li><a href="<%=request.getContextPath() %>/member/my/mypage.do?memberId=<%=memberLoggedIn.getMemberId()%>" id="pointList">포인트내역</a></li>
			<li><a href="<%=request.getContextPath() %>/member/my/like.do?memberId=<%=memberLoggedIn.getMemberId()%>" id="likeList">찜</a></li>
			<li><a href="<%=request.getContextPath() %>/member/my/purchase.do?memberId=<%=memberLoggedIn.getMemberId()%>" id="ticketingList">예매내역</a></li>
			<li><a href="<%=request.getContextPath() %>/member/my/update.do?memberId=<%=memberLoggedIn.getMemberId() %>" id="modifyMemberInfo">개인정보수정</a></li>
		</ul>
	</div>

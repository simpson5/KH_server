<%--
	jsp 주석
	jsp : java + html
	jsp의 모든 자바코드 <%..%>는 모두 서버단에서 처리되고, 그 결과만 html에 반영된다.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>	
<%
	//jsp scriptlet 자바공간
	System.out.println("===================");

	//사용자 입력값 가져오기
	// request, response에 선언없이 접근 가능
	String name = request.getParameter("name");
	String color = request.getParameter("color");
	String animal = request.getParameter("animal");
	String[] foodArr = request.getParameterValues("food");
	
	System.out.println("name@jsp = " + name);
	System.out.println("color@jsp = " + color);
	System.out.println("animal@jsp = " + animal);
	System.out.println("foodArr@jsp = " + Arrays.toString(foodArr));
	
	//저장된 속성 가져오기
	//object로 가져오기 때문에 다운캐스팅을 통해 String으로 변환 시켜야함 | .toString()도 가능
	String recommendation = (String)request.getAttribute("recommendation");
	System.out.println("recommendation@jsp = " + recommendation);
%>	
<!DOCTYPE html>
<html>
<head>
<title>개치 검사 결과</title>
<style>
.recommendation {
	font-size: 2em;
	color: tomato;
	text-decoration: underline;
}
</style>
</head>
<body>
	<h1>개인 취향 검사 결과 jsp</h1>
	<p><%= name %>님의 개인취향 검사 결과는</p>
	<p><%= color %>을 좋아합니다.</p>
	<p>좋아하는 동물은 <%= animal %>입니다.</p>
	<p>
	<% if(foodArr != null) {%>
		종아하는 중식은 <%= Arrays.toString(foodArr) %>입니다.
	<% } else { %>
		좋아하는 중식은 없어!
	<% } %>
	</p>
	<hr>
	<p class='recommendation'>오늘은 <%= recommendation %> 어떠세요?</p>
</body>
</html>

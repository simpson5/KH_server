<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp"%>
<h1>Content1</h1>
<p><%=name%>님, 반갑습니다.
</p>
<a href="/web/jsp/another.jsp">another</a>
<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
	Laudantium temporibus nesciunt totam rem molestias cupiditate expedita
	cum consequatur voluptates incidunt illo sequi repudiandae laboriosam
	corporis accusantium ratione perferendis libero ea.</p>
<script>
	$(function() {
		$("h1").css("color", "red");
	})
</script>
<%@ include file="/jsp/footer.jsp"%>
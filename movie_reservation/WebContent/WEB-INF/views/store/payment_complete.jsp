<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
<div style="text-align: center; font-size: 40px;">
    <h1>결제 완료</h1>
    <p>상품의 결제가 완료 되었습니다</p>
    <button class="close">닫기</button>
</div>
</body>
<style>
    .close{
    width: 245px;
    height: 60px;
    margin-left: 10px;
    font-weight: 500;
    font-size: 18px;
    color: black
    line-height: 60px;
    text-align: center;
    background-color: #121212
    display:inline-block;
    }
</style>
</body>
<script>
opener.document.location.href="<%= request.getContextPath() %>"
/* opener.document.sessionStorage.removeItem("payment_info"); */
self.close();
</script>
</html>
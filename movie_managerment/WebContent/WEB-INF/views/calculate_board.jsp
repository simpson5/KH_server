<%@page import="com.simp.product.model.vo.PaymentHistory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>정산내역</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/calculate_board.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
    <script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
   <%
	
   List<PaymentHistory> list = (List<PaymentHistory>) request.getAttribute("list");
	String type = request.getParameter("searchType");
	String kw = request.getParameter("searchKeyword"); 
	
	int totalPrice = 0;
	if(request.getAttribute("totalPrice") != null){
		totalPrice = (int)request.getAttribute("totalPrice");	
	}
   
   %> 
<style>

section#board-container {
  width: 600px;
  margin: 0 auto;
  text-align: center;
}
section#board-container h2 {
  margin: 10px 0;
}
table#tbl-board {
  width: 100%;
  margin: 0 auto;
  border: 1px solid black;
  border-collapse: collapse;
  clear: both;
}
table#tbl-board th,
table#tbl-board td {
  border: 1px solid;
  padding: 5px 0;
  text-align: center;
}


div#search-container {margin:15px auto; padding:3px; background-color: rgba(219, 219, 219);  width: 450px; height: 50px;}
div#search-memberId {display: <%= type == null || "memberId".equals(type) ? "inline-block" : "none" %>;}
div#search-payDate{display: <%= "payDate".equals(type) ? "inline-block" : "none" %>;}
div#search-movieName{display: <%= "movieName".equals(type) ? "inline-block" : "none" %>;}
</style>
    
  </head>
  
  <body>
    <div class="calculate_content">
      <h1>정산 내역</h1>
      <div id="search-container">
		<span>검색타입 : </span>
        <select id="searchType">
            <option value="memberId" <%= "memberId".equals(type) ? "selected" : "" %>>아이디</option>		
            <option value="payDate" <%= "payDate".equals(type) ? "selected" : "" %>>기간별</option>
            <option value="movieName" <%= "movieName".equals(type) ? "selected" : "" %>>영화 제목별</option>
        </select>
        <div id="search-memberId" class="search-type">
            <form action="<%=request.getContextPath()%>/calculate/search">
                <input type="hidden" name="searchType" value="memberId"/>
                <input type="text" name="searchKeyword"  size="25" value="<%= "memberId".equals(type) ? kw : "" %>" placeholder="검색할 아이디를 입력하세요."/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-payDate" class="search-type">
            <form action="<%=request.getContextPath()%>/calculate/search">
                <input type="hidden" name="searchType" value="payDate"/>
                <input type="text" name="searchKeyword" size="25" value="<%= "payDate".equals(type) ? kw : "" %>" placeholder="검색할 날짜를 입력하세요."/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-movieName" class="search-type">
            <form action="<%=request.getContextPath()%>/calculate/search">
                <input type="hidden" name="searchType" value="movieName"/>
                <input type="text" name="searchKeyword"  size="25" value="<%= "movieName".equals(type) ? kw : "" %>" placeholder="검색할 영화 제목을 입력하세요."/>
                <button type="submit">검색</button>
            </form>
        </div>
    </div>
     

      <link rel="stylesheet" href="/Content/css/calculate_board.css" />
<section id="board-container">
  
    <table id="tbl-board">
      <tr>
        <th>기간</th>
        <th>구매내역</th>
        <th>정산내역</th>
        
      </tr>
   
      <tr>
      
      </tr>
    <% if(list == null || list.isEmpty()){ %>
      <tr>
        <td colspan="3" style="text-align:center;">조회된 내역이 없습니다.</td>
      </tr>
    <% 
		   } 
		   else {
			   for(PaymentHistory p : list){
		%><tr>
				<td><%= p.getPayDate() %></td>
				<td><%= p.getPayContent() %></td>
				<td><%= p.getAmount() %></td>
		 </tr>
				
		 <% 
			   }
		   } 
		%>	
		<tr>
				<td colspan="3" style="text-align:center;">총 구매 금액</td>
		
		</tr>	
		<tr>
			
				<td colspan="3" style="text-align:center;"><%= totalPrice %></td>
		
		</tr>
    </table>
  
    <div id="pageBar">
		<%= request.getAttribute("pageBar") %>
	</div>
  </section>
  </body>
<script>

$(searchType).change(function(){
	var searchTypeVal = $(this).val();
	
	console.log(searchTypeVal);
	
	$(".search-type")
		.hide()
		.filter("#search-" + searchTypeVal)
		.css("display", "inline-block");
	
});


</script>  
</html>

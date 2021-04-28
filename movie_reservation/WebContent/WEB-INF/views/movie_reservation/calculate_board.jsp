<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>정산내역</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/calculate_board.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
    <script src="js/jquery-3.6.0.js"></script>
  </head>
  <body>
    <div class="calculate_content">
      <h1>정산 내역</h1>
      <p class="calculate_bar">
        <span id="date">기간별</span>
        <span id="product">품목별</span>
        <span id="movie">영화별</span>
        <div class="search">
          <input type="text" placeholder="검색어를 입력해주세요.">
     </div>
     <div class="button">
          <button style= "background-color:yellow;" >검색</button>
     </div>
      </p>

      <link rel="stylesheet" href="<%= request.getContextPath() %>/Content/css/calculate_board.css" />
<section id="board-container">
	<h2>기간별</h2>
  
    
   
    <table id="tbl-board">
      <tr>
        <th>기간</th>
        <th>정산내역</th>
        
      </tr>
   
      <tr>
      
      </tr>
    
      <tr>
        <td colspan="6" style="text-align:center;">조회된 내역이 없습니다.</td>
      </tr>
    
    </table>
  
    <div id='pageBar'></div>
  </section>


  </body>
</html>
    
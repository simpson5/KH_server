<%@page import="com.simp.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Product> list = (List<Product>)request.getAttribute("list");%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>상품 관리</title>
    <link rel="stylesheet" href="css/product_managerment.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
    <script src="js/jquery-3.6.0.js"></script>
  </head>
  <body>
    <div class="total_content">
      <h1>상품 관리</h1>
      <p class="select_bar">
          <span id="snack">SNACK</span>
          <span id="drink">DRINK</span>
          <span id="gift">GIFT</span>
          <span id="enroll">ENROLL</span>
      </p>
      <div class="product_container">
        <div class="snack_product">
<% for(Product pd : list) { %>
	<% if(pd.getPdCategory().equals("s")) { %>  
		<form name="productUpdateFrm" 
			  action="<%=request.getContextPath() %>/Product/productUpdate"
			  method="post"
			  id="productUpdateFrm">
			<input type="hidden" name="pd_no" value="<%= pd.getProductNo() %>" />
			<input type="hidden" name="price" />
			<input type="hidden" name="stock" />
			<input type="hidden" name="pdinfo"/>
		</form>
		<div class="product_box">
              <table>
                  <td>
                    <div class="pd_img">
                        <img src="<%= request.getContextPath() %>/upload/product/<%= pd.getPdImg() %>" alt="<%= pd.getProductName() %>">
                    </div>
                  </td>
                  <td>
                    <p class="name"><%= pd.getProductName() %></p>
                    <p class="price">
                        가격 : 
                        <input
                            type="number"
                            name="price"
                            id="price"
                            min="2000"
                            value="<%= pd.getProductPrice() %>"
                            step="1000">
                    </p>
                    
                    <p class="amount">
                        수량 : 
                        <input
                            type="number"
                            name="stock"
                            id="stock"
                            value="<%= pd.getStock() %>"
                            step="1">
                    </p>
                     <p class="enroll_pdinfo">
	              	설명 : <input type="text" name="pdinfo" value="<%= pd.getPdInfo() %>">
	              </p>
                  </td>
              </table>
              <div class="btn-box">
                  <button class="update_btn">수정하기</button>
                    <button class="reset_btn">초기화</button>
                     <button class="del_btn">폐기하기</button>
              </div>
          </div>
	<% } %>          
<% } %>          
        </div>
        
       
        <div class="drink_product">
        <% for(Product pd : list) { %>
	<% if(pd.getPdCategory().equals("d")) { %>  
		<form name="productUpdateFrm" 
			  action="<%=request.getContextPath() %>/Product/productUpdate"
			  method="post"
			  id="productUpdateFrm">
			<input type="hidden" name="pd_no" value="<%= pd.getProductNo() %>" />
			<input type="hidden" name="price" />
			<input type="hidden" name="stock" />
			<input type="hidden" name="pdinfo"/>
		</form>
		<div class="product_box">
              <table>
                  <td>
                    <div class="pd_img">
                        <img src="<%= request.getContextPath() %>/upload/product/<%= pd.getPdImg() %>" alt="<%= pd.getProductName() %>">
                    </div>
                  </td>
                  <td>
                    <p class="name"><%= pd.getProductName() %></p>
                    <p class="price">
                        가격 : 
                        <input
                            type="number"
                            name="price"
                            id="price"
                            min="2000"
                            value="<%= pd.getProductPrice() %>"
                            step="1000">
                    </p>
                    
                    <p class="amount">
                        수량 : 
                        <input
                            type="number"
                            name="stock"
                            id="stock"
                            value="<%= pd.getStock() %>"
                            step="1">
                    </p>
                     <p class="enroll_pdinfo">
	              	설명 : <input type="text" name="pdinfo" value="<%= pd.getPdInfo() %>">
	              </p>
                  </td>
              </table>
              <div class="btn-box">
                  <button class="update_btn">수정하기</button>
                    <button class="reset_btn">초기화</button>
                     <button class="del_btn">폐기하기</button>
              </div>
          </div>
	<% } %>          
<% } %>   
        </div>
        <div class="gift_product">
        <% for(Product pd : list) { %>
	<% if(pd.getPdCategory().equals("p")) { %>  
		<form name="productUpdateFrm" 
			  action="<%=request.getContextPath() %>/Product/productUpdate"
			  method="post"
			  id="productUpdateFrm">
			<input type="hidden" name="pd_no" value="<%= pd.getProductNo() %>" />
			<input type="hidden" name="price" />
			<input type="hidden" name="stock" />
			<input type="hidden" name="pdinfo"/>
		</form>
		<div class="product_box">
              <table>
                  <td>
                    <div class="pd_img">
                        <img src="<%= request.getContextPath() %>/upload/product/<%= pd.getPdImg() %>" alt="<%= pd.getProductName() %>">
                    </div>
                  </td>
                  <td>
                    <p class="name"><%= pd.getProductName() %></p>
                    <p class="price">
                        가격 : 
                        <input
                            type="number"
                            name="price"
                            id="price"
                            min="2000"
                            value="<%= pd.getProductPrice() %>"
                            step="1000">
                    </p>
                    
                    <p class="amount">
                        수량 : 
                        <input
                            type="number"
                            name="stock"
                            id="stock"
                            value="<%= pd.getStock() %>"
                            step="1">
                    </p>
                     <p class="enroll_pdinfo">
	              	설명 : <input type="text" name="pdinfo" value="<%= pd.getPdInfo() %>">
	              </p>
                  </td>
              </table>
              <div class="btn-box">
                  <button class="update_btn">수정하기</button>
                    <button class="reset_btn">초기화</button>
                     <button class="del_btn">폐기하기</button>
              </div>
          </div>
	<% } %>          
<% } %>   
        </div>
        <form action="<%= request.getContextPath()%>/product/productEnroll"
        	  name="productEnrollFrm"
        	  method="POST"
        	  enctype="multipart/form-data">
	        <div class="product_enroll">
	          <table>
	            <td>
	              <p class="enroll_name">
	              	이름 : <input type="text" name="pd_name" id="">
	              </p>
	              <p class="enroll_category">
	              	분류 :
	              		<select id="pd_category" name="pd_category">
	                   		<option value="s">snack</option>
	                   		<option value="d">drink</option>
	               			<option value="p">point</option>
	               	 	</select>
	              </p>
	              <p class="enroll_price">
	                              가격 : 
	                  <input
	                      type="number"
	                      name="pd_price"
	                      id="pd_price"
	                      min="2000"
	                      value="11000"
	                      step="1000">
	              </p>
	              <p class="enroll_img">
	           		이미지 : <input type="file" name="pd_img" id="pd_img">
	              </p>
	              <p class="enroll_pdinfo">
	              	설명 : <input type="text" name="pd_info" id="">
	              </p>
	            </td>
	        </table>
	
	          <div class="btn-box">
	              <input type="submit" value="등록하기" />
	              <input type="reset" value="취소" />
	          </div>
	        </div>
	   </form>     
      </div>
    </div>
    <script src="<%= request.getContextPath() %>/js/product_managerment.js"></script>
<form action="<%= request.getContextPath() %>/Product/productDelete"
	  id = "deleteFrm">
	<input type="hidden" name="delete_pd_name" />
</form>
  </body>
<script>

//상품정보 수정
$(".update_btn").click(function(){
	var updateFrm = $(this).closest(".product_box").prev();
	

	var price = updateFrm.next().find("[name=price]").val();
	var stock = updateFrm.next().find("[name=stock]").val();
	var pdinfo = updateFrm.next().find("[name=pdinfo]").val();
	
	updateFrm.find("[name=price]").val(price);
	updateFrm.find("[name=stock]").val(stock);
	updateFrm.find("[name=pdinfo]").val(pdinfo);
	
	
	updateFrm.submit();
	
});


// 상품정보 초기화
$(".reset_btn").click(function(){
	var updateFrm = $(this).closest(".product_box").prev();
	
	var $price = updateFrm.next().find("[name=price]");
	var $stock = updateFrm.next().find("[name=stock]");
	var $pdinfo = updateFrm.next().find("[name=pdinfo]");
	
	$price.val("");
	$stock.val("");
	$pdinfo.val("");
	
	
});

//상품 삭제하기
$(".del_btn").click(function(){
	if(confirm("해당 상품을 삭제하시겠습니까?")){
		var deleteFrm = $("#deleteFrm");
		
		var pd_name = $(this).parent().prev().find(".name").text();
		
		/* console.log(pd_name); */
		
		deleteFrm.children("[name=delete_pd_name]").val(pd_name);
		
		deleteFrm.submit();
	}
});












</script>
</html>

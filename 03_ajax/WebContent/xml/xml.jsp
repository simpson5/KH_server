<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax - xml</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>
table { border-collapse:collapse; border: 1px solid #000; margin: 5px}
th, td { border: 1px solid #000; }
table img {
	width: 150px;
}
</style>
</head>
<body>
	<h1>XML</h1>
	<button type="button" id="btn">실행</button>
	<button type="button" id="btn-product-sample">상품 xml 가져오기</button>
	<button type="button" id="btn-movie">영화 가져오기</button>
</body>
<script>
$("#btn-movie").click(function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/xml/movie.xml",
		success: function(data){
			console.log(data);
			var $root = $(data).find(":root");
			//console.log($root);
			var $movies = $root.find("channel").find("item");
			var $ul = $("<ul></ul>");
			$movies.each(function(index, movie){
				var title = $(movie).children("title").text();
				//var price = $(movie).children("Price").text();
				$ul.append("<li>" + title + "</li>")
			});
			$("body").append($ul);
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
})

$("#btn-product-sample").click(function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/xml/sample-product.xml",
		success: function(data){
			console.log(data);
			var $root = $(data).find(":root");
			//console.log($root);
			var $products = $root.find("Product");
			var $ul = $("<ul></ul>");
			$products.each(function(index, product){
				var name = $(product).children("Name").text();
				var price = $(product).children("Price").text();
				var $li = $("<li></li>");
				$li.append("<span>" + name + "</span> : ")
				   .append("<span>" + price + "</span>")
				   .appendTo($ul);
			});
			$("body").append($ul);
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
})

$(btn).click(function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/xml",
		success: function(data){
			console.log(data);
			var $root = $(data).find(":root");
			//console.log($root);
			var $members = $root.find("member");
			console.log($members);
			var $table = $("<table></table>");
			$members.each(function(index, member){
				console.log(index, member);
				var id = $(member).children("id").text();
				var name = $(member).children("name").text();
				var profile = $(member).children("profile").text();
				
				var $tr = $("<tr></tr>");
				$tr.append("<td>" + ( index + 1 ) + "</td>")
				   .append("<td><img src='<%= request.getContextPath() %>/images/" + profile + "'/></td>")
				   .append("<td>" + id + "</td>")
				   .append("<td>" + name + "</td>")
				   .appendTo($table);
			});
			$("body").append($table);
		},
		error: function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
});
</script>
</html>
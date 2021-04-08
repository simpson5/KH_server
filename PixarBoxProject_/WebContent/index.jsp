<%@page import="movie.model.service.MovieService"%>
<%@page import="movie.model.vo.Movie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//List<Movie> movieListNow = (List<Movie>)session.getAttribute("movieListNow");
	//List<Movie> movieListNow = new MovieService().selectMovieNow();
	//List<Movie> movieListNow = (List<Movie>)request.getAttribute("movieListNow");
	//System.out.println("movieListNow@index.jsp="+movieListNow);
	
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<section id="content-index">
<%-- <a href="<%=request.getContextPath() %>/movie/movieListNow"></a> --%>
<!-- 본문시작 -->



 <div class="container_">
              </div>
              
              <div class="container">
                <div id="carousel-example-generic" class="carousel slide">
                <!-- Indicators --> 
                <ol class="carousel-indicators carousel-indicators-numbers">
                  <li data-target="#carousel-example-generic" data-slide-to="0" class="active">1</li>
                  <li data-target="#carousel-example-generic" data-slide-to="1">2</li>
                  <li data-target="#carousel-example-generic" data-slide-to="2">3</li>
                </ol>
              
                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                  <div class="item active">
                      <a href="<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode=m13&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" >
                        <img id="meri" src="<%=request.getContextPath() %>/images/메리다.jpg" alt="...">
                    </a>
                    </div>
                  <div class="item">

                    <a href="<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode=m17&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" >
                    <img id="dori" src="<%=request.getContextPath() %>/images/도리.jpg" alt="...">
                     </a>
                  </div>
                  
                  <div class="item">
                    <a href="<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode=m19&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>" >
                    <img id="coco" src="<%=request.getContextPath() %>/images/코코.jpg" alt="..." >
                    </a>  
                </div>
                </div>
              
                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                  <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                  <span class="sr-only">‹</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                  <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                  <span class="sr-only">›</span>
                </a>
              </div>
              </div>


    <div class="deco"></div><!--구분자-->

   
   
   <!--슬라이드2 아래부터 수정  -->
        <div class="container_2">
                <div class="row">
                    <div class="span12">
                        <div class="well"> 
                            <div id="myCarousel" class="carousel slide">
                             
                            <ol class="carousel-indicators" id="posterIndicators">
                                <!-- <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li> -->
                            </ol>
                             
                            <!-- Carousel items -->
                            <div class="carousel-inner" id="poster">
                                
                            <%-- <div class="item active" id="moviePoster1">
                            
                                <div class="row-fluid">
                                  <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M토이스토리4.PNG"" alt="Image" style="max-width:100%;" /></a></div>
                                  <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M업.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                  <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M인사이드아웃.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                  <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M인크레더블.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                </div><!--/row-fluid-->
                            </div><!--/item-->
                             
                            <div class="item" id="moviePoster2">
                                <div class="row-fluid">
                                    <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M인사이드아웃.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                    <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M코코.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                    <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M토이스토리4.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                    <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M코코.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                </div><!--/row-fluid-->
                            </div><!--/item-->
                             
                            <div class="item" id="moviePoster3">
                                <div class="row-fluid">
                                    <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M코코.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                    <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M몬스터대학교.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                    <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M라따뚜이.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                    <div class="span3"><a href="http://www.naver.com" class="thumbnail"><img src="<%=request.getContextPath() %>/images/M코코.PNG" alt="Image" style="max-width:100%;" /></a></div>
                                </div><!--/row-fluid-->
                            </div><!--/item-->
                            <div class="item" id="moviePoster4">
                            </div>
                            <div class="item" id="moviePoster5">
                            </div>
                            <div class="item" id="moviePoster6">
                            </div> --%>
                            </div><!--/carousel-inner-->
                             
                            <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
                            <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
                            </div><!--/myCarousel-->
                             
                        </div><!--/well-->   
                    </div>
                </div>
            </div>
<!-- 슬라이드2 수정 끝 -->



    <div class="deco2"></div><!--구분자-->
            
 <!-- 이벤트 -->
 
 <div class="con_all">
 
 <div class="con_fig">
        <!-- 이벤트 좌측 -->
        <div class="con_event">
            <span class="square"></span>
            <h3>EVENT</h3>
            <button>당첨자발표</button>
        </div><!--div.con_event-->
        <!-- 이벤트 좌측 끝 -->

    <!--이벤트 배너  -->
    <figure class="snip1283">
        <img src="<%=request.getContextPath() %>/images/E1.PNG" alt="sample70" style="width:295px;"/>
            <figcaption>
                <h3>통신사 할인</h3>
                <p>[할인]LG U+ 제휴된 통신사로 VIP 30% 할인 GOLD 15% Silver 10% 혜택을 지금 누리세요!</p><a href="#" class="read-more">Read More</a>
            </figcaption>
    </figure>

    <figure class="snip1283 hover">
        <img src="<%=request.getContextPath() %>/images/E3.PNG" alt="sample71"/>
        <figcaption>
            <h3>마지막주 수요일 할인</h3>
            <p>[할인]매월 마지막주 수요일은 문화의 날 할인! 모든 영화를 5000원에 관람하세요!</p><a href="#" class="read-more">Read More</a>
        </figcaption>
    </figure>
    <figure class="snip1283">
        <img src="<%=request.getContextPath() %>/images/E2.PNG" alt="sample76"/>
            <figcaption>
                <h3>더블 포인트 적립</h3>
                <p>[적립]2020/01/01~2020/12/31 PIXAR 전용관 홍보하고 포인트 적립 2배로 받는 방법!</p><a href="#" class="read-more">Read More</a>
            </figcaption>
            
            
    </figure>
    </div><!--div con_fig-->
</div><!--div.container-->
</div>


</section> 



<script>


 /*잠깐 코드보류  */
<%-- function movieView(movieCode) {
	console.log(movieCode);
	location.href = "<%=request.getContextPath() %>/movie/movieView?movieCode="+movieCode;
}
 --%>



// evnet hover
$(".hover").mouseleave(
    function () {
      $(this).removeClass("hover");
    }
  );

//추가수정
/* $(document).ready(function() {
    $('#myCarousel').carousel({
	    interval: 10000
	})
}); */
$(()=> {
	
	var param = {
			memberId:  "null",
	}	
	let url = "<%=request.getContextPath() %>/movie/movieNow/showMovieNow.do";
	
	$.ajax({
		url: url,
		data: param,
		type: 'post',
		dataType: 'json',
		success: data=> {
			//console.log(Math.ceil(data.length/4));
			var k = 0;
			var l = 4;
			let $divMoviePoster = "";
			let $li = "";
			let html2 = "";
			let html3 = "";
			
			for(var i=1; i<=Math.ceil(data.length/4); i++) {
				if(i==1) {
					$divMoviePoster = $("<div class='item active' id='moviePoster"+i+"'></div>");
					$li = $("<li data-target='#myCarousel' data-slide-to='"+(i-1)+"' class='active'></li>");
					$("ol#posterIndicators").append($li);
					//console.log($divMoviePoster);
				}
				else {
					$divMoviePoster = $("<div class='item' id='moviePoster"+i+"'></div>");
					$li = $("<li data-target='#myCarousel' data-slide-to='"+(i-1)+"'></li>");
					$("ol#posterIndicators").append($li);
					//console.log($divMoviePoster);
				}
				
				if(i!=Math.ceil(data.length/4)) {
					let $div = $("<div class='row-fluid'></div>");
					for(var j=k; j<l; j++) {
						let html = "<div class='span3'><a href='<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode="+data[j].movieCode+"&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>' class='thumbnail'><img src='<%=request.getContextPath() %>/images/"+data[j].movieImg+"' alt='Img' style='max-width:100%;' /></a></div>";
						
						$div.append(html);
					}
					k+=4;
					l+=4;
					$divMoviePoster.append($div);
					html2 += $divMoviePoster[0].outerHTML;
					//console.log($divMoviePoster[0].innerHTML);
					//$("div#moviePoster"+i).html($div);
					//$("#poster").html($divMoviePoster);
				}
				else {
					let $div = $("<div class='row-fluid'></div>");
					for(var j=k; j<data.length; j++) {
						let html = "<div class='span3'><a href='<%=request.getContextPath() %>/movie/movieView/movieDescription.do?movieCode="+data[j].movieCode+"&memberId=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():""%>' class='thumbnail'><img src='<%=request.getContextPath() %>/images/"+data[j].movieImg+"' alt='Img' style='max-width:100%;' /></a></div>";
						
						$div.append(html);
					}
					$divMoviePoster.append($div);
					html2 += $divMoviePoster[0].outerHTML;
					//console.log($divMoviePoster[0].innerHTML);
					//$("div#moviePoster"+i).html($div);
					//$("#poster").html($divMoviePoster);
				}
				//console.log($("#poster"));
			}
				//console.log(html2);
				$("#poster").html(html2);
			
			
			//for(var i)
			//console.log($div);
		},
		error: (x, s, e)=> {
			console.log("ajax실패!!", x, s, e);
		}
	});
});
</script> 



<%@ include file="/WEB-INF/views/common/footer.jsp" %>

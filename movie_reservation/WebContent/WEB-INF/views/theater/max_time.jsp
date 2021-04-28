<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/floatingbar.jsp"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/theater/Max_theater.css" />
<style>
	.header_common {
		color : black;
		position : static;
	}
	
	.header_common a {
		color : black;
	}
</style>
    <!--[if lte IE 9
      ]><script
        type="text/javascript"
        src="https://img.cgv.co.kr/R2014/js/jquery.plugin/jquery.placeholder.js"
      ></script
    ><![endif]-->
    <script
      type="text/javascript"
      src="/Content/js/jquery.dotdotdot.min.js"
    ></script>
    <script
      type="text/javascript"
      src="/Content/js/silverlight_link.js"
    ></script>
    <script
      src="/Content/js/slick.js"
      type="text/javascript"
      charset="utf-8"
    ></script>

    <!-- 각페이지 Header Start-->

    <!--[if lte IE 9]>
      <script type="text/javascript">
        var iever = "msie9";
      </script>
      <script
        type="text/javascript"
        src="https://img.cgv.co.kr/R2014/js/jquery.plugin/jquery.placeholder.js"
      ></script>
    <![endif]-->

   <div class="theater-select">
        <div class="theater-detail-page">  
            <div class="theater-specail">
                <nav class="tab-theater">
                    <ul>
                        <li></li>
                        <li><a href="<%= request.getContextPath()%>/theater/dolby" title="dolby">Dolby</a></li>
                        <li><a href="<%= request.getContextPath()%>/theater/max" title="Max">Max</a></li>
                        <li></li>
                    </ul>
                </nav>
                <p class="name"  >
                    <img src="<%= request.getContextPath()%>/img/W_MAX_LOGO.jpg" >
                </p>
            </div>
        </div>
    </div>

        <div class="inner-wrap pt40"> 
            <div class="tab-list fixed mb40">
                <ul id="tab-list">
                    <li class="on"><a href="<%= request.getContextPath()%>/theater/max">Max Theater 소개</a></li>
                    <li class="on"><a href="<%= request.getContextPath()%>/theater/max_time">상영시간표</a></li>
                </ul>
            </div>
        </div>

        <div class="section-info">
            <div class="bg-wrap">
                <div class="inner-wrap">
                    <div class="tit_box">
                        <p class="tit">
                            <em>Max Theater</em>
                            <span class="info-text">
                                보다 넓은 화면으로 입체감을 즐기다! 
                                <br>
                                1.43:1비율을 적용한 Max Screen으로 생동감 넘치는 영화를
                                <br>
                                입체 dolby Sound와 함께 The SimpSons Movies에서 감상하실 수 있습니다!
                            </span>
                        </p>
                    </div>


                    <h2 class="tit small mt70">영화 관람료</h2>
                    <div class="fee-table-box">
                            <div class="fee-table">
                                    <p class="fee-table-tit">
                                        Max Theater 2D
                                    </p>
                                    <div class="table-wrap">
                                        <table class="data-table a-c" summary="가격표를 요일,상영시간,일반,청소년 순서로 보여줍니다">
                                            <caption>가격표를 요일,상영시간,일반,청소년 순서로 보여줍니다</caption>
                                            <colgroup>
                                                <col style="width: 25%;">
                                                <col style="width: 25%;">
                                                <col style="width: 25%;">
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th scope="col">요일</th>
                                                    <th scope="col">상영시간</th>
                                                    <th scope="col">일반</th>
                                                    <th scope="col">청소년</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="rowgroup" rowspan="3">월~목</th>
                                                    <td>조조(6:00~)</td>
                                                    <td>15,000</td>
                                                    <td>13,000</td>
                                                </tr>
                                                <tr>
                                                    <td>일반(10:00~)</td>
                                                    <td>18,000</td>
                                                    <td>16,000</td>
                                                </tr>
                                                <tr>
                                                    <td>심야(23:00~)</td>
                                                    <td>16,000</td>
                                                    <td>15,000</td>
                                                </tr>
                                                <tr>
                                                    <th scope="rowgroup" rowspan="3">
                                                    금~일
                                                    <br>
                                                    공휴일
                                                    </th>
                                                    <td>조조(6:00~)</td>
                                                    <td>16,000</td>
                                                    <td>14,000</td>
                                                </tr>
                                                <tr>
                                                    <td>일반(10:00~)</td>
                                                    <td>20,000</td>
                                                    <td>17,000</td>
                                                </tr>
                                                <tr>
                                                    <td>심야(23:00~)</td>
                                                    <td>17,000</td>
                                                    <td>15,000</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                
                            </div>
                            
                        </div>
                            
                        </div>
                    </div>

                    
                </div>
                </div>
            </div>
        </div>

        <div class="section mt70 sectionImfo">
            <div class="bg-wrap">
                <div class="inner-wrap">
                    <div class="cont-theater pt00">
                        <div class="col-group col-3">
                            <div class="col">
                                <p class="img">
                                    <img src="<%= request.getContextPath()%>/img/mappp.png" style="width:510px; height: 330px;" >
                                </p>
                                <p class="bottom-txt">
                                    <p class="maps">
                                    <img src="<%= request.getContextPath()%>/img/subway.png" style="width:60px; height: 70px;" >
                                    <br>
                                    4호선 명동역 4번 출구회현역 방향으로 직진
                                    <br>
                                    4호선 회현역 1번 출구명동 방향(남산 3호 터널 방향)으로 직진
                                    </p>
                                    <p class="buss">
                                        <img src="<%= request.getContextPath()%>/img/bus.png" style="width:50px; height: 60px;" >
                                        <br>
                                        <span style="color: rgb(37, 85, 241); font-weight: bold; ">버스B 간선버스</span> 104, 105, 143, 263, 401, 406, 421, 503, 505, 507, 604
                                        <br>
                                        <span style="color: rgb(60, 156, 35); font-weight: bold; ">G 지선버스 </span> 7011, 7013A, 7013B
                                        <br>
                                        <span style="color: rgba(238, 191, 37, 0.986); font-weight: bold; "> Y 순환버스</span> 03

                                        </p>
                                </p>
                            </div>
                            
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>


    </div>

       
      
      <%@ include file="/WEB-INF/views/common/footer.jsp"%>
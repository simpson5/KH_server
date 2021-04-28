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

                    <div class="content_box">
                            <div class="col-group col-1">
                                <div class="col">
                                    <p class="img">
                                        <img src="<%= request.getContextPath()%>/img/max2.jpg" style="width: 500px; height: 370px;" alt="">
                                        <img src="<%= request.getContextPath()%>/img/max1.jpg" style="width: 500px; height: 370px;" alt="">
                                    </p>
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
                                    <img src="<%= request.getContextPath()%>/img/dolby_mark.png" style="width: 300px; height: 150px;" >
                                </p>
                                <p class="bottom-txt">
                                    <strong class="font-roboto regular">
                                        Dynamic Dolby Sound
                                    </strong>

                                </p>
                            </div>
                            <div class="col">
                                <p class="img">
                                    <img src="<%= request.getContextPath()%>/img/W_MAX_LOGO.jpg" style="width: 100px; height: 100px; margin: 30px 0 0 0;">
                                </p>
                                <p class="bottom-txt">
                                    <strong class="font-roboto regular">
                                        Max Screen
                                    </strong>
                                    
                                </p>
                            </div>
                            <div class="col">
                                <p class="img">
                                    <img src="<%= request.getContextPath()%>/img/armchairs.png" style="width: 170px; height: 130px;">
                                </p>
                                <p class="bottom-txt">
                                    <strong class="font-roboto regular">
                                        400 Chairs
                                        <br>

                                    </strong>

                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

       
      
      <%@ include file="/WEB-INF/views/common/footer.jsp"%>
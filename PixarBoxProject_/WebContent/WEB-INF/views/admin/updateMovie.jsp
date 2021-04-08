<%@page import="movie.model.vo.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/movie_boxOffice.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<%
	Movie m = (Movie) request.getAttribute("movie");
	String actor = m.getActor() != null ? m.getActor() : "";
	String description = m.getDescription() != null ? m.getDescription() : "";

	//취미체크박스 처리2
	String[] genresChecked = {"", "", "", "", "", "","","","","",""};

	if (m.getGenres() != null) {
		String[] genre = m.getGenres().split(",");//["운동","독서","등산"]
		for (int i = 0; i < genre.length; i++) {
			genre[i] = genre[i].trim();
		}

		for (String g : genre) {
			switch (g) {
				case "드라마" :
					genresChecked[0] = "checked";
					break;
				case "판타지" :
					genresChecked[1] = "checked";
					break;
				case "아동" :
					genresChecked[2] = "checked";
					break;
				case "코미디" :
					genresChecked[3] = "checked";
					break;
				case "어드벤처" :
					genresChecked[4] = "checked";
					break;
					
				case "가족" :
					genresChecked[5] = "checked";
					break;
					
				case "SF" :
					genresChecked[6] = "checked";
					break;
				
				case "뮤지컬" :
					genresChecked[7] = "checked";
					break;
				
				case "애니메이션" :
					genresChecked[8] = "checked";
					break;
				
				case "모험" :
					genresChecked[9] = "checked";
					break;
				
				case "액션" :
					genresChecked[10] = "checked";
					break;
				
				
			}
		}

	}
%>

<section id="content-boxOffice">
	<div id="menuLine">
		<ul id="movieMenu">
			<li><a
				href="<%=request.getContextPath()%>/movie/boxOffice?memberId=<%=memberLoggedIn.getMemberId()%>"
				id="m1">박스오피스</a></li>
			<li><a href="" id="m2">현재상영작</a></li>
			<li><a
				href="<%=request.getContextPath()%>/movie/scheduleMovie?memberId=<%=memberLoggedIn.getMemberId()%>"
				id="m3">상영예정작</a></li>
			<li><a href="" id="m4">영화찾기</a></li>
		</ul>
	</div>

	<section id="addMovie-container">


		<h2>영화 등록</h2>

		<form action="<%=request.getContextPath()%>/admin/updateMovieEnd"
			method="post" enctype="multipart/form-data">


			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">영화 code</span>
				</div>
				<input type="text" class="form-control" id="movieCode"
					name="movieCode" value="<%=m.getMovieCode()%>"
					aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default" readonly required>
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">영화 제목</span>
				</div>
				<input type="text" class="form-control" id="movieTitle"
					name="movieTitle" value="<%=m.getMovieTitle()%>"
					aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default" required>
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">감독</span>
				</div>
				<input type="text" class="form-control" id="director"
					name="director" value="<%=m.getDirector()%>"
					aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default" required>
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">배우</span>
				</div>
				<input type="text" class="form-control" id="actor" name="actor"
					value="<%=actor%>" aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default">
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">러닝 타임</span>
				</div>
				<input type="number" class="form-control" id="runningTime"
					name="runningTime" value="<%=m.getRunningTime()%>"
					aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default">
			</div>


			<label for="releaseDate">개봉날짜</label>
			<div>
				<input type="date" id="releaseDate" name="releaseDate"
					value="<%=m.getReleaseDate()%>">
			</div>
			<br />


			<div class="form-group">
				<label for="exampleFormControlTextarea1">줄거리</label>
				<textarea class="form-control" id="description" name="description"
					rows="10"><%=description%></textarea>
			</div>

			<div class="form-group">
				<label for="exampleFormControlTextarea1">예고편 유튜브</label>
				<textarea class="form-control" id="movieVideo" name="movieVideo"
					required rows="3"><%=m.getMovieVideo()%></textarea>
			</div>


			<div class="form-group">
				<label for="upFile">포스터 이미지</label> 
				<input type="file" class="form-control-file" name="upFile">
									
				<span id="fname"><%=m.getOriginalFileName()!=null?m.getOriginalFileName():"" %></span>
				
				<input type="hidden" name="oldOriginalFileName"
					value="<%=m.getOriginalFileName() != null ? m.getOriginalFileName() : ""%>" />
				<input type="hidden" name="oldRenamedFileName"
					value="<%=m.getRenamedFileName() != null ? m.getRenamedFileName() : ""%>" />


				<!-- 기존파일삭제 체크박스-->
				<%
					if (m.getOriginalFileName() != null) {
				%>
				<br /> <input type="checkbox" name="delFileChk" id="delFileChk" />
				<label for="delFileChk">첨부파일삭제</label>
				<%
					}
				%>
			</div>
			




			
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id=genre1 value="드라마" <%=genresChecked[0] %>> <label class="form-check-label"
					for="genre1">드라마</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre2" value="판타지" <%=genresChecked[1] %>> <label class="form-check-label"
					for="genre2">판타지</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre3" value="아동" <%=genresChecked[2] %>> <label class="form-check-label"
					for="genre3">아동</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre4" value="코미디" <%=genresChecked[3] %>> <label class="form-check-label"
					for="genre4">코미디</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre5" value="어드벤처" <%=genresChecked[4] %>> <label class="form-check-label"
					for="genre5">어드벤처</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre6" value="가족" <%=genresChecked[5] %>> <label class="form-check-label"
					for="genre6">가족</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre7" value="SF" <%=genresChecked[6] %>> <label class="form-check-label"
					for="genre7">SF</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre8" value="뮤지컬" <%=genresChecked[7] %>> <label class="form-check-label"
					for="genre8">뮤지컬</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre9" value="애니메이션" <%=genresChecked[8] %>> <label class="form-check-label"
					for="genre9">애니메이션</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre10" value="모험" <%=genresChecked[9] %>> <label class="form-check-label"
					for="genre10">모험</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre11" value="액션" <%=genresChecked[10] %>> <label class="form-check-label"
					for="genre11">액션</label>
			</div>
		

			<br />
			<br /> <label for="form-check form-check-inline">상영예정작</label>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="schedule"
					id="schedule1" value="Y"
					<%="Y".equals(m.getSchedule()) ? "checked" : ""%>> <label
					class="form-check-label" for="schedule1">Y</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="schedule"
					id="schedule2" value="N"
					<%="N".equals(m.getSchedule()) ? "checked" : ""%>> <label
					class="form-check-label" for="schedule2">N</label>
			</div>

			<br />
			<br />

			<button type="submit" class="btn btn-secondary btn-lg"
				id="btn-add-movie">등록하기</button>
		</form>

	</section>

</section>

<script>

$("[name=upFile]").change(function(){
	//수정페이지에서 파일태그에 파일을 추가한 경우
	if($(this).val() != ""){
		$("#fname").hide();
		$("#delFileChk").hide().next().hide();
	}
	else{
		$("#fname").show();
		$("#delFileChk").show().next().show();
	}
});
	function validate() {
		//제목검사
		var $title = $("#movieTitle");
		if ($title.val().trim().length == 0) {
			alert("제목을 입력하세요.");
			return false;
		}

		//감독이름검사
		var $director = $("#director");
		if ($director.val().trim().length == 0) {
			alert("감독 이름을  입력하세요.");
			return false;
		}

		//감독이름검사
		var $date = $("#releaseDate");
		if ($date.val().trim().length == 0) {
			alert("개봉일을  입력하세요.");
			return false;
		}

		//이미지 검사
		var $img = $("#movieImg");
		if ($img.val().trim().length == 0) {
			alert("개봉일을  입력하세요.");
			return false;
		}

		return true;

		//장르 체크 박스 검사
		if ($("input:checkbox[name=genre]:checked").length == 0) {
			alert("장르를 체크해 주세요");
			return false;

		}

	}
</script>

<script>

</script>






<%@ include file="/WEB-INF/views/common/footer.jsp"%>

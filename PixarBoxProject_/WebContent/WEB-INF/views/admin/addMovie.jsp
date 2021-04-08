<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/movie_boxOffice.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin.css" />
<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous"> -->

<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<!-- <script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script> -->
<style>
form{
max-width:620px;
border:1px solid lightgray;
border-radius:10px;
top:30px;
left:250px;
padding:50px;

}
.input-group-prepend span{
top:-1px;
left: 0px;
border:1px solid gray;
background-color:lightgray;
padding: 10px;	
border-radius: 3px;
}

#movieCode{
	top:-28px;
	left:110px;
	width:470px;
	height:25px;
}

#movieTitle{
top:-28px;
	left:110px;
	width:470px;
	height:25px;
}

#director{
	top:-30px;
left:110px;
width:470px;
height:26px;
}
#actor{
	top:-30px;
left:110px;
width:470px;
height:26px;
}

#runningTime{
	
	top:-30px;
left:110px;
width:470px;
height:26px;
}

#releaseDate{
	top:-30px;
left:110px;
width:200px;
height:26px;
}

#description{
top:-30px;
left:-25px;
width:470px;
height:300px;	
	
}
#movieVideo{
top:10px;
left:-25px;
width:470px;
height:100px;
}

.form-control-file{
	top:-15px;
left:-25px;
}
#chk{
	left:120px;
}
#schedule1{
	left:-15px;
	
}
#schedule2{
	left:-105px;
}
#y{
	left:-15px;
}
#n{
left:-105px;
} 
</style>
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

		<form action="<%=request.getContextPath()%>/admin/addMovieEnd"
			method="post" enctype="multipart/form-data">


			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">영화 code</span>
				</div>
				<input type="text" class="form-control" id="movieCode"
					name="movieCode" aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default" required>
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">영화 제목</span>
				</div>
				<input type="text" class="form-control" id="movieTitle"
					name="movieTitle" aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default" required>
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">감독</span>
				</div>
				<input type="text" class="form-control" id="director"
					name="director" aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default" required>
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">배우</span>
				</div>
				<input type="text" class="form-control" id="actor" name="actor"
					aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default">
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">러닝 타임</span>
				</div>
				<input type="number" class="form-control" id="runningTime"
					name="runningTime" aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default">
			</div>


			<!-- 	<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <span class="input-group-text" id="inputGroup-sizing-default">개봉날짜</span>
		  </div>
		  <input type="text" class="form-control" id="releaseDate" name="releaseDate" placeholder="1999/01/12" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" required>
		</div> -->
			<label for="releaseDate">개봉날짜</label>
			<div>
				<input type="date" id="releaseDate" name="releaseDate">
			</div>
			<br />


			<div class="form-group">
				<label for="exampleFormControlTextarea1">줄거리</label>
				<textarea class="form-control" id="description" name="description"
					rows="10"></textarea>
			</div>

			<div class="form-group">
				<label for="exampleFormControlTextarea1">예고편</label>
				<textarea class="form-control" id="movieVideo" name="movieVideo"
					required rows="3"></textarea>
			</div>

<br/>

			<div class="form-group">
				<label for="exampleFormControlFile1">포스터 
				<br/>이미지</label> <input
					type="file" class="form-control-file" name="upFile" required>
			</div>

<div id="chk">
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id=genre1 value="드라마"> <label class="form-check-label"
					for="genre1">드라마</label>
		<!-- 	</div> -->
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre2" value="판타지"> <label class="form-check-label"
					for="genre2">판타지</label>
			<!-- </div> -->
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre3" value="아동"> <label class="form-check-label"
					for="genre3">아동</label>
			<!-- </div> -->
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre4" value="코미디"> <label class="form-check-label"
					for="genre4">코미디</label>
			<!-- </div>
			 -->
		
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre5" value="어드벤처"> <label class="form-check-label"
					for="genre5">어드벤처</label>
			<!-- </div> -->
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre6" value="가족"> <label class="form-check-label"
					for="genre6">가족</label>
		<!-- 	</div> -->
		<!-- 	<div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre7" value="SF"> <label class="form-check-label"
					for="genre7">SF</label>
			<!-- </div> -->
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre8" value="뮤지컬"> <label class="form-check-label"
					for="genre8">뮤지컬</label>
			<!-- </div> -->
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre9" value="애니메이션"> <label class="form-check-label"
					for="genre9">애니메이션</label>
		<!-- 	</div> -->
		<!-- 	<div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre10" value="모험"> <label class="form-check-label"
					for="genre10">모험</label>
		<!-- 	</div> -->
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="checkbox" name="genre"
					id="genre11" value="액션"> <label class="form-check-label"
					for="genre11">액션</label>
			<!-- </div>
		 -->
</div>

			<br />
		<label for="form-check form-check-inline">상영예정작</label>
			<!-- <div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="radio" name="schedule"
					id="schedule1" value="Y" checked> 
					<label
					class="form-check-label" id="y" for="schedule1">Y</label>
	<!-- 		</div> -->
		<!-- 	<div class="form-check form-check-inline"> -->
				<input class="form-check-input" type="radio" name="schedule"
					id="schedule2" value="N"> 
					<label class="form-check-label" id="n" 
					for="schedule2">N</label>
			<!-- </div> -->

			<br />
			<br />

			<button type="submit" class="btn btn-secondary btn-lg"
				id="btn-add-movie">등록하기</button>
		</form>

	</section>

</section>

<script>
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

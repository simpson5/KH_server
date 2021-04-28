<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
  />
<style>
.content {
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
}

.headcount-box {
	position: relative;
	text-align: center;
}

.headcount-box h2 {
	background-color: black;
	color: white;
	padding: 1vh 0;
	margin-bottom : 0.5vh;
}

.cnt-container {
	padding: 0 3vw;
}

.movie-info {
	position: relative;
}

.text-info {
	display: inline-block;
	margin-left: 1vw;
	position: absolute;
	top: -1vh;
}

.adult {
	position: absolute;
	top: 53%;
	left: 30%;
}

.child {
	position: absolute;
	top: 53%;
	left: 60%;
}

.cnt-container .btn-box {
	display: inline-block;
	border: solid 1px black;
	width: 6vw;
	height: 3vh;
	text-align: center;
	border-radius: 0.3vw;
	padding-top: 0.3vh;
}

.btn-box span {
	margin: 0 1vw;
}

.btn-box button {
	border: 0;
	outline: 0;
	background-color: white;
}

.headcount-box img {
	width: 4vw;
}

.seat-container {
	position: relative;
	width: 60vw;
	height: 60vh;
	background-color: black;
}

.screen {
	position: absolute;
	width: 50vw;
	height: 3vh;
	background-color: gray;
	left: 50%;
	top: 5vh;
	transform: translateX(-50%);
	text-align: center;
	font-size: 1vw;
	color: white;
}

.seat-box {
	position: absolute;
	left: 50%;
	top: 10vh;
	transform: translateX(-50%);
	color: white;
	text-align: center;
	width: 50vw;
}

.seat {
	width: 1.5vw;
	height: 1.8vh;
	background-color: white;
	display: inline-block;
	color: black;
	border-radius : 0.4vw 0.4vw 0 0;
	padding-bottom : 0.3vh;
}
.seat-row {
	display: inline-block;
	width: 1vw;
}
.blank {
	width: 2.5vw;
	height: 1.5vh;
	background-color: black;
	display: inline-block;
}
</style>
</head>
<body>
	<div class="content">
		<div class="headcount-box">
			<h2>인원 / 좌석 선택</h2>
			<div class="cnt-container" align="left">
				<div class="movie-info">
					<img
						src="https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202101/16908_201_1.jpg"
						alt="" />
					<div class="text-info">
						<p>영화 제목</p>
						<p>상영시간</p>
						<p>상영관</p>
					</div>
				</div>
				<div class="adult">
					성인
					<div class="btn-box">
						<button>
							<i class="fas fa-minus"></i>
						</button>
						<span>0</span>
						<button>
							<i class="fas fa-plus"></i>
						</button>
					</div>
				</div>
				<div class="child">
					청소년
					<div class="btn-box">
						<button>
							<i class="fas fa-minus"></i>
						</button>
						<span>0</span>
						<button>
							<i class="fas fa-plus"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	<div class="seat-container">
		<div class="screen">S C R E E N</div>
		<div class="seat-box">
<% for(int i = 0; i < 20; i++) { %>
	<span class="seat-row"><%= (char)(i+65) %></span>
<%
	for(int j = 0; j < 20; j++) {
		if(j == 10) {
%>
		<div class="blank"></div>
<%  
		}
%>
			<div class="seat"><%= j + 1 %></div>
	<% } %>
			<br />
<% } %>
		</div>
		</div>
	</div>
</body>
</html>
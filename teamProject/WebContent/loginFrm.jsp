<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
header{
	width: 1500px;
	height: 200px;
}
nav{
	width: 1500px;
}
aside{
	background-color: navy;
	width: 200px;
	height: 300px;
	float: left;
}
section{
	background-color: white;
	width: 1100px;
	height:1000px;
	float: left;
}
footer{
	width: 1500px;
	clear: both;
}

</style>
</head>
<body>
<header>
<%@ include file="header.jsp" %>
</header>

<nav>
<hr>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">쇼핑몰 이름</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">쇼핑하기 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="javascript:Aj('newItem','#main')"><button style="background-color:white; border: none;">신상품</button></a></li>
          <li><a href="javascript:Aj('bestItem','#main')"><button style="background-color:white; border: none;">인기상품</button></a></li>
        </ul>
      <li><a href="#">장바구니</a></li>
      ${product}
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="logout"><button style="background-color: #242424; border: none;"><span class="glyphicon glyphicon-log-in"></span>로그아웃</button></a></li>
    </ul>
  </div>
</nav>
<hr>
</nav>

<aside>
<%@ include file="aside.jsp" %>
</aside>
<section>
<h1>상품 리스트</h1>
<div id="main"></div>
</section>
<aside>
<%@ include file="aside.jsp" %>
</aside>

<footer>
<br>
<hr>
<%@ include file="footer.jsp" %>
<hr>
</footer>

<script>
Aj("newItem","#main");
Aj("bestItem","#main");

function Aj(url, position) {
	$.ajax({
		url:url,
		type:'get',
		dataType: "html",
		success: function (page) {
			$(position).html(page);
		},
		error:  function (err) {
			console.log(err);
		}
	});
}
</script>
</body>
</html>
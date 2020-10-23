<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	height:500px;
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
<%@ include file="navnome.jsp" %>
<hr>
</nav>

<aside>
<%@ include file="aside.jsp" %>
</aside>
<section>

<br><h1>로그인</h1><br>
<hr>
<form action="access" method="post">
아이디: <input type="text" name="id" placeholder="아이디입력"><br>
비밀번호: <input type="password" name="pw" placeholder="비밀번호입력"><br>
<span style="color: red;">${msgAccess}</span>
<hr>
		<button>로그인</button>
</form>

</section>
<aside>
<%@ include file="aside.jsp" %>
</aside>

<footer>
<br>
<%@ include file="footer.jsp" %>
</footer>

</body>
</html>
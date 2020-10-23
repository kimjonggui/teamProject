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
	height:1000px;
	float: left;
}
footer{
	width: 1500px;
	clear: both;
}
table{
	margin-left: 400px;
}

</style>
</head>
<body>
<header>
<%@ include file="../header.jsp" %>
</header>

<nav>
<hr>
<%@ include file="../navlogin.jsp" %>
<hr>
</nav>

<aside>
<%@ include file="../aside.jsp" %>
</aside>
<section>
<br><h1>상품등록</h1><br>
<hr>
<form action="insertItem" method="post" enctype="multipart/form-data">

<table>
	<tr>
		<td colspan="2" align="center"> <h3>상품업로드</h3></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="radio" name="ilkind" value="n" checked>신상품
		<input type="radio" name="ilkind" value="b" >인기상품
		</td>
	</tr>
	<tr>
		<td>상품명</td>
		<td><input type="text" name="ilname"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input type="text" name="ilprice"></td>
	</tr>
	<tr>
		<td>재고</td>
		<td><input type="text" name="ilqty"></td>
	</tr>
	<tr>
		<td>상세설명</td>
		<td><input type="text" name="ildetail"></td>
	</tr>
	<tr>
		<td>상품명</td>
		<td><input type="file" name="ilfile"></td>
	</tr>
	<tr align="center">
		<td colspan="2">
		<button>상품등록</button>
		<input type="button" onclick="back()" value="뒤로가기">
		</td>
	</tr>

</table>
</form>

<script>
function back() {
	history.back();
}
</script>
</section>
<aside>
<%@ include file="../aside.jsp" %>
</aside>

<footer>
<br>
<hr>
<%@ include file="../footer.jsp" %>
<hr>
</footer>


</body>
</html>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <title>글 수정</title>
	<STYLE TYPE="text/css">
		<!--
		BODY {background-image: url("dia.jpg"); background-repeat: no-repeat; background-size: cover}
		h1#text1 {}
		div#box1 {border: 5px solid black; width: 1000px; height: 700px; position: relative; left: 400px; top: 10px}
		label#label1 {position: relative; left: 70px; top: 10px}
		input#text2 {width: 800px; height: 20px; position: relative; left: 100px; top: 10px}
		textarea#text3 {width: 930px; height: 580px; position: relative; left: 30px; top: 30px}
		input#submit1 {width: 100px; height: 50px; background-color: black; color: white; position: relative; left: 868px; top: 30px}
		button#button1 {width: 140px; height: 70px; background-color: lightgreen; color: black; position: relative; left: 840px; top: 40px}
		button#button1:hover {background-color: red; color: blue}
		-->
	</STYLE>
</head>
<body>
<h1 id="text1">고객지원</h1>
<div id="box1">
	<form action="customchangeok" method=post>
	<div style="display:none;">
	<p>
		<label>번호<br>
			<Input Type="Text" Name="count1" value="${custom1.count}">
		</label>
	</p>
	</div>
		<label for="text2" id="label1">제목&nbsp;&nbsp;</label>
		<input type="text" name="title1" value="${custom1.title}" id="text2">
		<textarea name="content1" id="text3">${custom1.content}</textarea>
		<input type="submit" value="수정하기" id="submit1">
	</form>
</div>
<button id="button1"><a href="<c:url value="/custom"/>" style="text-decoration:none" >돌아가기</a></button>
</body>
</html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>고객문의 목록</title>
	<STYLE TYPE="text/css">
		<!--
		BODY {background-image: url("dia.jpg"); background-repeat: no-repeat; background-size: cover}
		h1#text1 {}
		div#box1 {width: 990px; height: 550px; border: 5px solid black; position: relative; left: 400px; top: 10px}
		table#table1 {width: 1000px; position: relative; left: 0px; top: 0px}
		td {border-bottom: 1px solid black; border-right: 1px solid black}
		button#button1 {width: 100px; height: 50px; background-color: lightgreen; color: black; position: relative; left: 1310px; top: 10px}
		button#button1:hover {background-color: red; color: blue}
		button#button2 {text-decoration: none; width: 150px; height: 75px; background-color: lightgreen; color: black; position: relative; left: 700px; top: 140px}
		button#button2:hover {background-color: red; color: blue}
		-->
	</STYLE>
</head>
<body>
<h1 id="text1">고객지원</h1>
<div style="overflow:auto; width: 1000px; height: 550px;" id="box1">
	<table id="table1">
		<c:forEach var="questionlist" items="${QuestionList}" varStatus="status">
			<tr>
				<td>
						${questionlist.count}&nbsp;&nbsp;
				</td>
				<td>
					<a href="<c:url value="content?count=${questionlist.count}"/>"
					   style="text-decoration:none">${questionlist.title} </a>
				</td>
				<td>
				</td>
				<td>
						${questionlist.registerDateTime}
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<c:if test="${! empty mem}">
	<button id="button1"><a href="<c:url value="/customwrite" />" style="text-decoration:none">글쓰기 </a></button>
</c:if>
<button id="button2"><a href="<c:url value="/home" />" style="text-decoration:none">메인으로 </a></button>
</body>
</html>

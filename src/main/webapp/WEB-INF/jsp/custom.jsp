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
	BODY {background-image: url('dia.jpg'); background-repeat: no-repeat; background-size: cover}
	h1#text1 {}
	div#box1 {width: 990px; height: 550px; border: 5px solid black; position: relative; left: 400px; top: 10px}
	table#table1 {width: 1000px; position: relative; left: 0px; top: 0px}
	td {border-bottom: 1px solid black; border-right: 1px solid black}
	button#button1 {width: 100px; height: 50px; background-color: lightgreen; color: black; position: relative; left: 1310px; top: 10px}
	button#button1:hover {background-color: red; color: blue}
	button#button2 {width: 150px; height: 75px; background-color: lightgreen; color: black; position: relative; left: 700px; top: 140px}
	button#button2:hover {background-color: red; color: blue}
-->
</STYLE>
</head>
<body>
<h1 id="text1">고객지원</h1>
<c:forEach var="questionlist" items="${QuestionList}" varStatus="status">
	${questionlist.count}&nbsp;&nbsp;
	<a href="<c:url value="/content/${questionlist.count}"/> ">${questionlist.title} </a>
	&nbsp;&nbsp;&nbsp;${questionlist.registerDateTime}
	<br>
</c:forEach>

<c:if test="${! empty mem}">
	<p>
		<a href="<c:url value="/customwrite" />">글쓰기 </a>
	</p>
</c:if>

<p>
	<a href="<c:url value="/home" />">메인으로 </a>
</p>
</body>
</html>

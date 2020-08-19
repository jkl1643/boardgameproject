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
		BODY {background-image: url("board.jpg"); background-repeat: no-repeat; background-size: cover}
		h1#text1 {}
		div#box1 {width: 990px; height: 550px; border: 5px solid black; position: relative; left: 400px; top: 10px}
		table#table1 {width: 1000px; position: relative; left: 0px; top: 0px}
		td {border-bottom: 1px solid black; border-right: 1px solid black}
		button#button1 {width: 100px; height: 50px; background-color: lightgreen; color: black; position: relative; left: 1310px; top: 10px}
		button#button1:hover {background-color: red; color: blue}
		button#button2 {width: 150px; height: 75px; background-color: lightgreen; color: black; position: relative; left: 700px; top: 140px}
		button#button2:hover {background-color: red; color: blue}
		a.no-uline { text-decoration:none }
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
					<a href="<c:url value="/content/${questionlist.count}"/>" style="text-decoration:none">${questionlist.title}  </a> <!-- 얘네 링크 디렉토리 말고 딴걸로 안됨? ㅠ -->
				</td>
				<td>
						<%--${questionlist.name}&nbsp;&nbsp;&nbsp;--%>
				</td>
				<td>
						${questionlist.registerDateTime}
				</td>
			</tr>
		</c:forEach> <!-- 목록 추가될 때마다 글 추가되고 밑줄 반복되게 하기(스크립트로 구현되나요? 여기선 어떻게 보이는지 모르겠어요) -->
	</table>
</div>
<c:if test="${! empty mem}">
	<a href="<c:url value="/customwrite" />" style="text-decoration:none">글쓰기 </a> <!-- 밑줄만 없애고싶은데 왜 밑줄이 생기는지 몰라서 패스 -->
</c:if>
<a href="<c:url value="/home" />" style="text-decoration:none">메인으로 </a>
</body>
</html>

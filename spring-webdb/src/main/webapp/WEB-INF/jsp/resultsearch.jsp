<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>검색 결과</h1>
	<c:forEach var="search" items="${result}" varStatus="status">
		<li> ${status.index+1}. ${search.year}년 ${search.month}월 ${search.day}일 [ 메모 : ${search.memo} ] [ 이미지 : ${search.saveImagePath} ]</li>
	</c:forEach>
	<BR><Input type = "button" value="되돌아가기" onClick="history.back();">
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="member" items="${result}" varStatus="status">
	<li> ${member.email}님의 비밀번호는 ${member.password}입니다.</li>
	</c:forEach>
	
	<form action=login method="post">
		<BR><Input Type = "Submit" Value = "확인">
	</form>
</body>
</html>
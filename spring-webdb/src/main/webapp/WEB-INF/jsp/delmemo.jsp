<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>메모삭제</title>
</head>
<body>
	<h1>메모 삭제</h1>
	<form action="main" method="post">
		<BR><input type="date" name="DATE2"/>
		<Input Type = "Submit" Value = "메모 삭제">
		<Input type = "button" value="되돌아가기" onClick="history.back();">
	</form>

	<script>
		document.getElementById('currentDatetime').value = new Date()
				.toISOString().slice(0, -1);
	</script>

	<c:forEach var="memo" items="${memos}" varStatus="status">
		<li>${status.index+1}. 아이디 : ${memo.email} ${memo.year}년
			${memo.month}월 ${memo.day}일 [메모 : ${memo.memo}] [이미지 :
			${memo.saveImagePath}]</li>
	</c:forEach>
</body>
</html>
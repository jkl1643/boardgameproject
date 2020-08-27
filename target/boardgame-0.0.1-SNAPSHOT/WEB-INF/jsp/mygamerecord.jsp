<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <title>내 전적</title>
</head>
<body>

<p>

총 경기 수 : ${myrecord.getTotal()}
</p>

<p>
승 : ${myrecord.getWin()}
</p>

<p>
무승부 : ${myrecord.getDraw()}

</p>

<p>
패배 : ${myrecord.getLose()}	
</p>

<p>
승 : ${myrecord.getWin()}
</p>

<p>
무승부 : ${myrecord.getDraw()}

</p>

<p>
패배 : ${myrecord.getLose()}	
</p>


</body>
</html>
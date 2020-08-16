<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>내 전적</title>
</head>
<body>
<%-- -닉네임 : ${rec.getNickname()}
총 경기 수 : ${rec.getTotal()}
승 : ${rec.getWin()}
무승부 : ${rec.getDraw()}
패배 : ${rec.getLose()} 	
 	'--%>
닉네임 : ${myrecord.nickname()}
총 경기 수 : ${myrecord.total()}
승 : ${myrecord.win()}
무승부 : ${myrecord.draw()}
패배 : ${myrecord.lose()}

</body>
</html>
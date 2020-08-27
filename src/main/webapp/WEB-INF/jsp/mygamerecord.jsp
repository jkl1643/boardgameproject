<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>내 전적</title>
    <STYLE TYPE="text/css">
        <!--
        BODY {background-image: url("board2.jpg"); background-repeat: no-repeat; background-size: cover}
        .title {text-align: center; font-family: sans-serif; color: brown}
        table#table1 {width: 400px; height: 400px; position: relative; left: 400px; top: 10px}
        input#text2 {position: relative; left: 800px; top: 75px}
        button#button2 {text-decoration: none; width: 150px; height: 75px; background-color: lightgreen; color: black; position: relative; left: 700px; top: 140px}
		button#button2:hover {background-color: red; color: blue}
        input#text3 {background-color: black; color: white; width: 100px; height: 50px; position: relative; left: 400px; top: 110px}
        input#text5 {background-color: black; color: white; width: 100px; height: 50px; position: relative; left: 470px; top: 110px}
        input#text4 {background-color: black; color: white; width: 100px; height: 50px; position: relative; left: 540px; top: 110px}
        -->
    </STYLE>
</head>
<body>
<center>
    <font size = "17" color = "blue" face ="굴림">${mem.getNickname()}님의 게임 전적입니다. </font>
    <br>
    <br>
    <br>
    <p>
        <font size = "12" face ="굴림">총 경기 수 : ${myrecord.getTotal()}</font>
    </p>

    <br>
    <br>

    <p>
        <font size = "12" face ="굴림">승 : ${myrecord.getWin()}</font>
    </p>

    <br>
    <br>

    <p>
        <font size = "12" face ="굴림">무승부 : ${myrecord.getDraw()}</font>
    </p>

    <br>
    <br>

    <p>
        <font size = "12" face ="굴림">패배 : ${myrecord.getLose()}	</font>
    </p>

    <br>
    <br>
    <br>
<button id="button2"><a href="<c:url value="/home" />" style="text-decoration:none">메인으로 </a></button>
</center>
</body>
</html>
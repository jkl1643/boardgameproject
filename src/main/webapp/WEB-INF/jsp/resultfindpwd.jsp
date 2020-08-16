<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>비밀번호 결과</title>
    <STYLE TYPE="text/css">
        <!--
        BODY {
            background-image: url('board.jpg');
            background-repeat: no-repeat;
            background-size: cover
        }

        div#box1 {
            background-color: #bfff00;
            width: 800px;
            height: 300px;
            border: 5px solid black;
            position: relative;
            left: 500px;
            top: 100px
        }

        p#text1 {
            text-align: center;
            font-size: x-large;
            position: relative;
            top: 70px
        }

        button#button1 {
            width: 150px;
            height: 100px;
            position: relative;
            left: 820px;
            top: 250px;
            background-color: black;
            color: white
        }

        -->
    </STYLE>
</head>
<body>
<c:forEach var="member" items="${result}" varStatus="status">
    <div id="box1">
        <p id="text1"> ${member.email}님의 비밀번호는 ${member.password}입니다.</p>
    </div>
</c:forEach>

<button id="button1" onclick="location.href='home'">홈으로</button>
</body>
</html>
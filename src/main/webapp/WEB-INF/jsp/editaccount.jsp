<%@page import="com.example.MemberLogin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>개인정보 수정</title>
    <STYLE TYPE="text/css">
        <!--
        BODY {background-image: url('board2.jpg'); background-repeat: no-repeat; background-size: 2000px 1750px}
        .title {text-align: center; font-family: sans-serif; color: brown}
        table#table1 {width: 400px; height: 400px; position: relative; left: 400px; top: 10px}
        input#text3 {background-color: black; color: white; width: 100px; height: 50px; position: relative; left: 520px; top: 110px}
        input#text4 {background-color: black; color: white; width: 100px; height: 50px; position: relative; left: 620px; top: 110px}
        -->
    </STYLE>
</head>
<body>
<h1 class="title">회원정보 수정</h1>
<form action="login" method="post">
    <table id="table1">
        <tr>
            <td><strong>아이디</strong></td>
            <td><Input Type = "Text" value="아이디" Name = "EMAIL" readonly> <BR></td>
        </tr>
        <tr>
            <td><strong>현재 비밀번호</strong></td>
            <td><Input Type = "PassWord" Name = "oldpwd"> <BR></td>
        </tr>
        <tr>
            <td><strong>새 비밀번호</strong></td>
            <td><Input Type = "PassWord" Name = "pwd"> <BR></td>
        </tr>
        <tr>
            <td><strong>비밀번호 확인</strong></td>
            <td><Input Type = "PassWord" Name = "pwd2"> <BR></td>
        </tr>
        <tr>
            <td><strong>닉네임</strong></td>
            <td><Input Type = "Text" Name = "nickname"> <BR></td>
        </tr>
    </table>
    <Input Type = "Submit" Value = "제출합니다" id="text3">
    <Input type = "button" value="뒤로가기" id="text4" onClick="history.back();">
</form>
</body>
</html>
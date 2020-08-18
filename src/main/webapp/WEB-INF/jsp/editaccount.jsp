<%@page import="com.example.MemberLogin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>정보수정</title>
</head>
<body><!-- sdfasd -->
<h1>정보 수정</h1>
<form action="main" method="post">
    <table>
        <tr>
            <td>아이디</td>
            <td>${userid}</td>
        </tr>
        <tr>
            <td>현재 비밀번호</td>
            <td><Input Type="Text" Name="oldpwd"></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><Input Type="PassWord" Name="pwd"></td>
        </tr>
        <tr>
            <td>비밀번호 확인</td>
            <td><Input Type="PassWord" Name="pwd2"></td>
        </tr>
        <tr>
            <td>닉네임</td>
            <td><Input Type="Text" Name="nickname"></td>
        </tr>
    </table>
    <Input Type="Submit" Value="수정 완료">
    <Input type="button" value="되돌아가기" onClick="history.back();">
</form>
</body>
</html>
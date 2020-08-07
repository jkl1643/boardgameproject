<%@page import="javax.tools.DocumentationTool.Location" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>메인</title>
    <STYLE TYPE="text/css">
        <!--
        BODY {background-image: url('board.jpg'); background-repeat: no-repeat; background-size: cover}
        .part1 {font-family: fantasy; position: relative; left: 750px; font-size: xx-large}
        ul {list-style-type: none; margin: 0; padding: 0; width: 1100px; background-color: #333}
        ul:after {content: ''; display: block; clear: both}
        li {float: left}
        li a {display: block; color: white; text-align: center; padding: 16px 60px; text-decoration: none}
        li a:hover:not(a.active) {background-color: #111}
        .active {background-color: #4CAF50}
        div#search {position: relative; left: 750px; top: -47px; width: 300px; border: 1px solid blue; background: white}
        input#searbut {font-size: 16px; width: 225px; padding: 10px; border: 0px; outline: none; float: left}
        button#searbut1 {width: 50px; height: 40px; border: 0px; background: blue; outline: none; float: right; color: white}
        div#logbox1 {width: 580px; height: 500px; border: 10px solid black; position: relative; right: -1230px; top: -52px}
        #logbox2 {background-image: url('brick.jpg'); width: 565px; height: 100px; border: 1px solid blue; margin-top: 5px; margin-left: 5px; margin-right: 5px}
        #loginbutton1 {position: relative; left: -100px; top: 250px; height: 50px; width: 120px; border-color: #6495ED; background-color: #BCD2EE; border-radius: 5px; margin: auto; text-align: center; font-size: 20px; font-family: impact}
        a#loginbutton2 {position: relative; left: -5px; top: 10px; text-decoration: none; color: brown; font-family: 돋움}
        #signupbutton1 {position: relative; left: 60px; top: 200px; height: 50px; width: 120px; border-color: #6495ED; background-color: #BCD2EE; border-radius: 5px; margin: auto; text-align: center; font-size: 20px; font-family: impact}
        a#signupbutton2 {position: relative; left: -15px; top: 10px; text-decoration: none; color: brown; font-family: 돋움}
        -->
    </STYLE>
</head>
<body>
<div id="logbox1">
    <div id="logbox2"></div>
${userid}님 로그인 되었습니다.<BR>
<table>
    <tr>
        <td>
            <form action="delaccount" method="post">
                <Input Type="Submit" Value="계정삭제 ▶">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="editaccount" method="post">
                <Input Type="Submit" Value="정보수정 ▶">
            </form>
        </td>
        <td>
            <form action="home" method="post">
                <Input Type="Submit" Value="로그아웃 ▶">
            </form>
        </td>
    </tr>
    <tr>
    </tr>
    <%--<form action="main" method="post">
        <tr>
            <td>날 짜</td>
            <td><input type="date" name="DATE"/></td>
        </tr>
        <tr>
            <td>메 모</td>
            <td><Input Type="Text" Name="MEMO" style="width:80px"></td>
        </tr>
        <tr>
            <td>이미지</td>
            <td><input type="file" name="IMAGE"></td>
        </tr>
        <tr>
            <td><Input Type="Submit" Value="메모등록"> <BR></td>
            <td><c:forEach var="memo" items="${memos}" varStatus="status">
                <li> ${status.index+1}. ${memo.year}년 ${memo.month}월 ${memo.day}일 [ 메모 : ${memo.memo} ] [ 이미지
                    : ${memo.saveImagePath} ]
                </li>
            </c:forEach></td>
        </tr>
    </form>--%>
    <%--<%
        boolean editaccount = (boolean) request.getAttribute("editaccount");
        boolean chkpwd = (boolean) request.getAttribute("chkpwd");
        boolean currentpwd = (boolean) request.getAttribute("currentpwd");

    <%if (editaccount == true) {%>
    <BR>정보를 수정했습니다.<BR>
    <%}%>
    <%if (chkpwd == true) {%>
    <BR>확인 비밀번호가 일치하지 않습니다.<BR>
    <%}%>
    <%if (currentpwd == true) {%>
    <BR>현재 비밀번호가 일치하지 않습니다.<BR>
    <%}%>
    <BR>--%>

    <script>
        document.getElementById('currentDatetime').value = new Date().toISOString().slice(0, -1);
    </script>
</table>
</div>
</body>
</html>
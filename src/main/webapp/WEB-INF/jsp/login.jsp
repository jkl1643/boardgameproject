<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>로그인</title>
    <STYLE TYPE="text/css">
        <!--
        BODY {
            background-image: url(board.jpg);
            background-repeat: no-repeat;
            background-size: cover
        }

        .part1 {
            font-family: fantasy;
            position: relative;
            left: 750px;
            font-size: xx-large
        }

        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            width: 1100px;
            background-color: #333
        }

        ul:after {
            content: '';
            display: block;
            clear: both
        }

        li {
            float: left
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 16px 60px;
            text-decoration: none
        }

        li a:hover:not(a.active) {
            background-color: #111
        }

        .active {
            background-color: #4CAF50
        }

        div#search {
            position: relative;
            left: 750px;
            top: -47px;
            width: 300px;
            border: 1px solid #0000ff;
            background: white
        }

        input#searbut {
            font-size: 16px;
            width: 225px;
            padding: 10px;
            border: 0px;
            outline: none;
            float: left
        }

        button#searbut1 {
            width: 50px;
            height: 40px;
            border: 0px;
            background: blue;
            outline: none;
            float: right;
            color: white
        }

        div#logbox1 {
            width: 580px;
            height: 500px;
            border: 10px solid black;
            position: relative;
            right: -1230px;
            top: -52px
        }

        #logbox2 {
            background-image: url('brick.jpg');
            width: 565px;
            height: 100px;
            border: 1px solid blue;
            margin-top: 5px;
            margin-left: 5px;
            margin-right: 5px
        }

        #loginbutton1 {
            position: relative;
            left: -100px;
            top: 250px;
            height: 50px;
            width: 120px;
            border-color: #6495ED;
            background-color: #BCD2EE;
            border-radius: 5px;
            margin: auto;
            text-align: center;
            font-size: 20px;
            font-family: impact
        }

        a#loginbutton2 {
            position: relative;
            left: -5px;
            top: 10px;
            text-decoration: none;
            color: brown;
            font-family: 돋움
        }

        #signupbutton1 {
            position: relative;
            left: 60px;
            top: 200px;
            height: 50px;
            width: 120px;
            border-color: #6495ED;
            background-color: #BCD2EE;
            border-radius: 5px;
            margin: auto;
            text-align: center;
            font-size: 20px;
            font-family: impact
        }

        a#signupbutton2 {
            position: relative;
            left: -15px;
            top: 10px;
            text-decoration: none;
            color: #a52a2a;
            font-family: 돋움
        }

        -->
    </STYLE>
</head>
<body>
<%--<h1> 다이어리프로그램 </h1>--%>
<div id="logbox1">
    <div id="logbox2"></div>
    <form action="main" method="post">
        <div style="margin-left: 200px; margin-top: 20px; float: left; display: inline;">아이디</div>
        <div style="margin-left: 300px; margin-top: -25px; float: left; display: inline;"><input type="text"
                                                                                                 placeholder="아이디 조건"
                                                                                                 Name="id"
                                                                                                 id="inputid1"></div>
        <div style="margin-left: 200px; margin-top: 20px; float: left;">패스워드</div>
        <div style="margin-left: 300px; margin-top: -25px; float: left;"><input type="password" placeholder="비밀번호 조건"
                                                                                Name="pwd"></div>
        <div style="margin-left: 250px; margin-top: 20px; float: left;"></div>
        <div><Input Type="Submit" Value="로그인" id="loginbutton1"> <%--유병렬 입력한것--%></div>
        <div><%
            boolean email = (boolean) request.getAttribute("unknown_email");
            boolean emailpwd = (boolean) request.getAttribute("email_pwd_match");
            boolean logout = (boolean) request.getAttribute("logout");
            boolean delaccount = (boolean) request.getAttribute("delaccount");
            boolean created_account = (boolean) request.getAttribute("created_account");
            boolean error = (boolean) request.getAttribute("error");
            if (email) { %>
            존재하지 않는 아이디입니다.
            <%} else if (email) { %>
            아이디를 입력해주세요.
            <%} else if (emailpwd) {%>
            이메일과 암호가 일치하지 않습니다.
            <%} else if (delaccount) {%>
            계정이 삭제되었습니다.
            <%} else if (logout) {%>
            로그아웃 되었습니다.
            <%
                }
                if (created_account) {
            %> 계정이 생성되었습니다. <%
                }
                if (error) {
            %>    계정이 생성되지 않았습니다.<%} %>
        </div>
        <div style="margin-left: 350px; margin-top: 17px; float: left;"><input type="checkbox" id="check1"></div>
        <div style="margin-left: 370px; margin-top: -20px; float: left; display: inline;">아이디 저장</div>
        <div OnClick="location.href ='findaccount'"
             style="cursor: pointer; margin-left: 300px; margin-top: 20px; float: left; display: inline;">아이디/비밀번호 찾기
        </div>
    </form>
    <div id="signupbutton1"><a href="newaccount" id="signupbutton2">회원가입</a></div>
    <%--유병렬 제거한것--%>
</div>
<BR>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>비밀번호찾기</title>
    <STYLE TYPE="text/css">
        <!--
        BODY {
            background-image: url('board.jpg');
            background-repeat: no-repeat;
            background-size: cover
        }

        h1#text {
            text-align: center
        }

        div#box1 {
            width: 750px;
            height: 300px;
            background-color: #bfff00;
            border: 5px solid black;
            position: relative;
            left: 530px;
            top: 20px
        }

        p#dd1 {
            position: relative;
            left: 200px;
            top: 30px
        }

        input#text1 {
            position: relative;
            left: 300px;
            top: -6px
        }

        p#dd2 {
            position: relative;
            left: 200px;
            top: 20px
        }

        input#text2 {
            position: relative;
            left: 300px;
            top: -15px
        }

        input#submit1 {
            width: 120px;
            height: 50px;
            background-color: black;
            color: white;
            position: relative;
            left: 300px;
            top: 60px
        }

        button#button1 {
            width: 200px;
            height: 100px;
            background-color: black;
            color: white;
            position: relative;
            left: 800px;
            top: 200px
        }

        -->
    </STYLE>
</head>
<%--<body>
	<h1>비밀번호찾기</h1>
	<form action=resultfindpwd method="post">
		이메일 : <Input Type = "Text" Name = "id"> <BR>
		닉네임 : <Input Type = "Text" Name = "tel"> <BR>
		<BR><Input Type = "Submit" Value = "비밀번호 찾기">
	</form>
</body>--%>
<body>
<h1 id="text">비밀번호찾기</h1>
<div id="box1">
    <form action=resultfindpwd method="post">
        <p id="dd1">아이디 : </p>
        <Input Type="Text" Name="id" id="text1"> <BR>
        <p id="dd2">닉네임 : </p>
        <Input Type="Text" Name="nickname" id="text2"> <BR>
        <Input Type="Submit" Value="비밀번호 찾기" id="submit1">
    </form>
</div>
<button id="button1" onClick="history.back();">뒤로가기</button>
</body>
</html>
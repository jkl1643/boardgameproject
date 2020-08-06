<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<STYLE TYPE="text/css">
<!--
	BODY {background-image: url('board2.jpg'); background-repeat: no-repeat; background-size: 2000px 1750px}
	.title {text-align: center; font-family: sans-serif; color: brown}
	table#table1 {width: 400px; height: 400px; position: relative; left: 400px; top: 10px}
	div#textbox1 {width: 700px; height: 250px; border: 1px solid black; position: relative; left: 300px; top: 10px}
	input#text1 {position: relative; left: 800px; top: 35px}
	p#text11 {position: relative; left: 850px; top: 0px}
	div#textbox2 {width: 700px; height: 250px; border: 1px solid black; position: relative; left: 300px; top: 55px}
	p#text12 {position: relative; left: 850px; top: 39px}
	input#text2 {position: relative; left: 800px; top: 75px}
	input#text3 {position: relative; left: 520px; top: 110px}
	input#text4 {position: relative; left: 620px; top: 110px}
-->
</STYLE>
</head>
<body>
	<h1 class="title">회원가입 창</h1>
	<form action="home" method="post">
	<table id="table1">
		<tr>
			<td><strong>아이디</strong></td>
			<td><Input Type = "Text" Name = "EMAIL"> <BR></td>
		</tr>
		<tr>
			<td><strong>비밀번호</strong></td>
			<td><Input Type = "PassWord" Name = "PWD"> <BR></td>
		</tr>
		<tr>
			<td><strong>비밀번호 확인</strong></td>
			<td><Input Type = "PassWord" Name = "PWD2"> <BR></td>
		</tr>
		<tr>
			<td><strong>닉네임</strong></td>
			<td><Input Type = "Text" Name = "NICKNAME"> <BR></td>
		</tr>
	</table>
	<div id="textbox1"></div>
	<Input Type = "checkbox" Name = "check1" id="text1"><p id="text11">약관에 동의합니다.</p>
	<div id="textbox2"></div>
	<Input Type = "checkbox" Name = "check2" id="text2"><p id="text12">약관에 동의합니다.</p>
	<Input Type = "Submit" Value = "제출합니다" id="text3">
	<Input type = "button" value="뒤로가기" id="text4" onClick="history.back();">
	</form>
</body>
</html>
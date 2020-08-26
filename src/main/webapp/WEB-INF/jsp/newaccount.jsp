<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<STYLE TYPE="text/css">
<!--
	BODY {background-image: url("board2.jpg"); background-repeat: no-repeat; background-size: cover}
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
	<script type = "text/javascript">
		function validate(){
			alert("aaa");
			var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
			var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			// 이메일이 적합한지 검사할 정규식

			var email = document.getElementById("EMAIL");
			var pw = document.getElementById("PWD");
			var pwd2 = document.getElementById("PWD2");
			var nickname = document.getElementById("NICKNAME");



			if(email.value==null) {
				alert("이메일을 입력해 주세요");
				email.focus();
				return false;
			}

			if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
				return false;
			}



			if(pw.value==null) {
				alert("비밀번호를 입력해 주세요");
				pw.focus();
				return false;
			}


			if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
				return false;
			}

			if(pwd2.value==null) {
				alert("비밀번호를 입력해 주세요");
				pwd2.focus();
				return false;
			}


			if(pw.value != pwd2.value) {
				alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
				pwd2.value = "";
				pwd2.focus();
				return false;
			}


			if(nickname.value=="") {
				alert("닉네임을 입력해 주세요");
				nickname.focus();
				return false;
			}

		}



		function check(re, what, message) {
			if(re.test(what.value)) {
				return true;
			}
			alert(message);
			what.value = "";
			what.focus();
			return false;
		}


	</script>
</head>
<body>
	<h1 class="title">회원가입 창</h1>
	<form name="join" onsubmit="return validate();" action="home"  method="post" enctype="text/plain">
		<table id="table1">
			<tr>
				<td><strong>이메일</strong></td>
				<td><Input Type="Text" id="EMAIL" Name="EMAIL"> <BR></td>
			</tr>
			<tr>
				<td><strong>비밀번호</strong></td>
				<td><Input Type="PassWord" id="PWD" Name="PWD"> <BR></td>
			</tr>
			<tr>
				<td><strong>비밀번호 확인</strong></td>
				<td><Input Type="PassWord" id="PWD2" Name="PWD2"> <BR></td>
			</tr>
			<tr>
				<td><strong>닉네임</strong></td>
				<td><Input Type="Text" id="NICKNAME" Name="NICKNAME"> <BR></td>
			</tr>
		</table>
		<%--<div id="textbox1"></div>
		<Input Type="checkbox" Name="check1" id="text1">
		<p id="text11">약관에 동의합니다.</p>
		<div id="textbox2"></div>
		<Input Type="checkbox" Name="check2" id="text2">
		<p id="text12">약관에 동의합니다.</p>--%>
		<Input Type="Submit" Value="제출합니다" id="text3">
		<Input type="reset" value="다시입력"/>
		<Input type="button" value="뒤로가기" id="text4" onClick="history.back();">
	</form>
</body>
</html>
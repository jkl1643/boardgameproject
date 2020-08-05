<%-- <%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>계정생성</title>
</head>
<body>
	<h1>계정 생성</h1>
	<form action="home" method="post">
	<table>
		<tr>
			<td>아이디</td>
			<td><Input Type = "Text" Name = "EMAIL"> <BR></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><Input Type = "PassWord" Name = "PWD"> <BR></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><Input Type = "PassWord" Name = "PWD2"> <BR></td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td><Input Type = "Text" Name = "NICKNAME"> <BR></td>
		</tr>
		<tr>
			<td><Input Type = "Submit" Value = "가입 완료"></td>
			<td><Input type = "button" value="되돌아가기" onClick="history.back();"></td>
		</tr>
	</table>		
	</form>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>
</head>
<body>
	<h1> 다이어리프로그램 </h1>

	<table>
		<form action="main" method="post">
		<tr>
			<td>아이디</td>
			<td><Input Type = "Text" Name = "id" style = "width:80px"> <BR></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><Input Type = "PassWord" Name = "pwd" style = "width:80px"> <BR></td>
		</tr>
		<tr>
			<td>

					<Input Type = "Submit" Value = "로그인">
				</form>
			</td>
			<td>
				<form action="newaccount" method="post">
					<Input Type = "Submit" Value = "회원가입">
				</form>	
			</td>
			<td>
				<form action="findpwd" method="post">
					<Input Type = "Submit" Value = "비밀번호찾기">
				</form>
			</td>		
		</tr>
	</table>

	<%boolean email = (boolean)request.getAttribute("unknown_email");
		boolean emailpwd = (boolean)request.getAttribute("email_pwd_match");
		boolean logout = (boolean)request.getAttribute("logout");
		boolean delaccount = (boolean)request.getAttribute("delaccount");
		boolean created_account = (boolean)request.getAttribute("created_account");
		boolean error = (boolean)request.getAttribute("error");
		if(email) {
			%>존재하지 않는 이메일입니다.
		<%} else if (emailpwd) {%>
			이메일과 암호가 일치하지 않습니다.
		<%} else if (delaccount) {%>
			계정이 삭제되었습니다.
		<%} else if (logout) {%>
			로그아웃 되었습니다.
		<%}

		if (created_account) { %> 계정이 생성되었습니다. <%}
		if (error) { %>	계정이 생성되지 않았습니다.<%}	%>
	<BR>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>보드게임: 문제있어?</title>
<STYLE TYPE="text/css">
<!--
	BODY {background-image: url(board.jpg); background-repeat: no-repeat; background-size: cover}
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
div#logbox3 {width: 590px; height: 800px; border: 5px solid black; position: relative; right: -1230px; top: -10px}
div#logbox4 {width: 1100px; height: 700px; border: 5px solid black; position: relative; left: 0px; top: -1300px}
div#logbox5 {width: 1100px; height: 520px; border: 5px solid black; position: relative; left: 0px; top: -1250px}
-->
</STYLE>
</head>
<body>
	<P CLASS="part1"><B> 보드게임: 문제있어? </B></P> <!-- 제목 -->
	<ul>
		<li><a class="active" href="#home">홈</a></li> <!-- 메뉴바의 홈 버튼 -->
		<li><a href="#gamerank">게임순위</a></li> <!-- 메뉴바의 게임순위 버튼 -->
		<li><a href="#theme">테마</a></li> <!-- 메뉴바의 테마 버튼 -->
		<li><a href="<c:url value='/custom'/>">고객문의</a></li> <!-- 메뉴바의 고객문의 버튼 -->
	</ul>
	<div id="search">
			<input type="text" placeholder="게임 검색" id="searbut"> <!-- 게임검색 창의 텍스트 입력칸 -->
			<button id="searbut1">검색</button> <!-- 게임검색 창의 검색 버튼 -->
	</div>
	<%
	int login = (int)request.getAttribute("login");
	%>

	<%--<table>
		<tr>
			<td>${login}
				<%
					if(login == 0){ %>
						<jsp:include page="login.jsp"></jsp:include> &lt;%&ndash;로그인 전&ndash;%&gt;
				<%} else { %>
						<jsp:include page="main.jsp"></jsp:include> &lt;%&ndash;로그인 성공페이지&ndash;%&gt;
				<%}	%>

			</td>
		</tr>
	</table>--%>

	<%--<%
		String id = (String)session.getAttribute("id");%>${id}<%
		if(id != null){	%>
	아이디 널아님

	<%} else{
		session.invalidate();
	}%>--%>

	${id}
	<%--<%
		session.setMaxInactiveInterval(5); // 초 단위
	%>--%>
	<% if(session.getAttribute("id") == null){ %>
		<div id="logbox1">
			<div id="logbox2"></div>
			<form action="main" method="post">
				<div style="margin-left: 200px; margin-top: 20px; float: left; display: inline;">아이디</div>
				<div style="margin-left: 300px; margin-top: -25px; float: left; display: inline;"><input type="text" placeholder="아이디 조건" Name="id" id="inputid1"></div>
				<div style="margin-left: 200px; margin-top: 20px; float: left;">패스워드</div>
				<div style="margin-left: 300px; margin-top: -25px; float: left;"><input type="password" placeholder="비밀번호 조건" Name ="pwd"></div>
				<div style="margin-left: 250px; margin-top: 20px; float: left;"></div>
				<div><Input Type = "Submit" Value = "로그인" id="loginbutton1"> <%--유병렬 입력한것--%></div>
				<div>
					<%
					boolean email = (boolean)request.getAttribute("unknown_email");
					boolean emailpwd = (boolean)request.getAttribute("email_pwd_match");
					boolean logout = (boolean)request.getAttribute("logout");
					boolean delaccount = (boolean)request.getAttribute("delaccount");
					boolean created_account = (boolean)request.getAttribute("created_account");
					boolean error = (boolean)request.getAttribute("error");
					if(email) {	%>
					존재하지 않는 아이디입니다.
					<%} else if (email){ %>
					아이디를 입력해주세요.
					<%}	else if (emailpwd) {%>
					이메일과 암호가 일치하지 않습니다.
					<%} else if (delaccount) {%>
					계정이 삭제되었습니다.
					<%} else if (logout) {%>
					로그아웃 되었습니다.
					<%}
						if (created_account) { %> 계정이 생성되었습니다. <%}
						if (error) { %>	계정이 생성되지 않았습니다.<%}	%>
				</div>
				<div style="margin-left: 350px; margin-top: 17px; float: left;"><input type="checkbox" id="check1"></div>
				<div style="margin-left: 370px; margin-top: -20px; float: left; display: inline;">아이디 저장</div>
				<div OnClick="location.href ='findaccount'" style="cursor: pointer; margin-left: 300px; margin-top: 20px; float: left; display: inline;">아이디/비밀번호 찾기</div>

			</form>
			<div id="signupbutton1"><a href="newaccount" id="signupbutton2">회원가입</a></div> <%--유병렬 제거한것--%>
		</div>
	<%}	else {%>
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
	<%}%>
	   login = ${login}, id = ${id}
	<%--<%
		String id = (String)session.getAttribute("id");
		if(id != null){	%>
		아이디 널아님

	<%} else{
		session.invalidate();
	}%>--%>


	<%--<%
		String id2 = request.getParameter("id2");
		String password = request.getParameter("password");
		%>id2 = ${id2}<%
		//if (login == 1) {
			session.setAttribute("id2", id2);
			/*response.sendRedirect("main.jsp");*/
		//} /*else {
			//session.invalidate();
		//}*/
		String id = "";
		try {
			id = (String) session.getAttribute("id2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		%>
		id2 = ${id2}, id = ${id}
	<%
		if(id == null){
			session.invalidate();
		}
	%>id2 = ${id2}, id = ${id}--%>

<%--		if (id.equals(id2)) {%>--%>
<%--	아이디 같음--%>
<%--	<%--%>
<%--		--%>
<%--	%>--%>

	<div id="logbox3">
	</div>
	<div id="logbox4">
	</div>
	<div id="logbox5">
	</div>
</body>
</html>
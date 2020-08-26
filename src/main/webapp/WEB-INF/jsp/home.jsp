<%@ page import="com.example.RegisterRequest" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.Member" %>
<%@ page import="MyGameRecord.MyGameRecord" %>
<%@ page import="com.example.Dao.Game" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.example.MainController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>보드게임: 문제있어?</title>
	<STYLE TYPE="text/css">
		<!--
		BODY {
			background-image: url("board.jpg");
			background-repeat: no-repeat;
			background-size: cover;
		}
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
		div#logbox2 {background-color: lightblue; width: 565px; height: 100px; border: 1px solid blue; margin-top: 5px; margin-left: 5px; margin-right: 5px}
		p#yuba {font-size: xx-large; text-align: center; position: relative; left: 20px; top: -5px}
		input#loginbutton1 {position: relative; left: -100px; top: 250px; height: 50px; width: 120px; border-color: #6495ED; background-color: #BCD2EE; border-radius: 5px; margin: auto; text-align: center; font-size: 20px; font-family: impact}
		input#signupbutton1 {position: relative; left: 300px; top: 200px; height: 50px; width: 120px; border-color: #6495ED; background-color: #BCD2EE; border-radius: 5px; margin: auto; text-align: center; font-size: 20px; font-family: impact}
		#yu {position: relative; left: 200px; top: -60px}
		#asdf {position: relative; left: 60px}
		div#logbox3 {width: 590px; height: 800px; border: 5px solid black; position: relative; right: -1230px; top: -10px}
		div#logbox4 {width: 1100px; height: 700px; border: 5px solid black; position: relative; left: 0px; top: -1300px}
		div#logbox5 {width: 1100px; height: 520px; border: 5px solid black; position: relative; left: 0px; top: -1250px}
		div#logbox1 {width: 580px; height: 500px; border: 10px solid black; position: relative; right: -1230px; top: -52px}
		table#table1 {width: 400px; height: 100px; float: right; position: relative; right: 50px; top: 0px}
		input#but1 {background-color: black; color: white; position: relative; left: 10px}
		table#table2 {width: 330px; height: 200px; float: right; position: relative; right: 100px; top: 10px}
		input#but2 {background-color: black; color: white}
		input#but3 {background-color: black; color: white}
		input#but4 {background-color: black; color: white}
		input#but5 {background-color: black; color: white}
		gameimage{
			text-align: center;
			padding: 1em;
			background: lightblue;
			border-radius: 10px;
		}

		.img
		{
			height: 300px;
			width: 300px;
		}
		-->

	</STYLE>
</head>
<body>

	<P CLASS="part1"><B> 보드게임: 문제있어? </B></P> <!-- 제목 -->
	<ul>
		<li><a class="active" href="#home">홈</a></li> <!-- 메뉴바의 홈 버튼 -->
		<li><a href="<c:url value='/gamerank'/>">게임순위</a></li> <!-- 메뉴바의 게임순위 버튼 -->
		<li><a href="<c:url value='/custom'/>">게임목록</a></li> <!-- 메뉴바의 테마 버튼 -->
		<li><a href="<c:url value='/custom'/>">고객문의</a></li> <!-- 메뉴바의 고객문의 버튼 -->
	</ul>
	<div id="search">
			<input type="text" placeholder="게임 검색" id="searbut"> <!-- 게임검색 창의 텍스트 입력칸 -->
			<button id="searbut1">검색</button> <!-- 게임검색 창의 검색 버튼 -->
	</div>
	<%
		int login = (int) request.getAttribute("login");
		String idid = (String) session.getAttribute("idid");
		Member mem = (Member) session.getAttribute("mem");
		int users = (int) request.getAttribute("users");
	//	MyGameRecord rec = (MyGameRecord) session.setAttribute();
	//	MyGameRecord rec = (MyGameRecord) session.getAttribute("rec");


		boolean loginduplicate = (boolean) request.getAttribute("loginduplicate");
		System.out.println("jsp mem : " + mem);
		/*if(loginduplicate){
			System.out.println("듀플");
			return;
		} else {
			System.out.println("듀플안됨");
		}*/

		Cookie [] cookie = request.getCookies();
		String cookieId = "";
		if(cookie != null) {
			for(Cookie i : cookie) {
				if(i.getName().equals("saveId")) {
					cookieId = i.getValue();
				}
			}
		}

		System.out.println("mem2 : " + mem);
		if(mem == null){ %>
		<div id="logbox1">
			<div id="logbox2">
				<p id="yuba">현재 접속자 수 : ${users}명</p>
			</div>
			<form action="main" method="post">
				<div style="margin-left: 200px; margin-top: 20px; float: left; display: inline;">아이디</div>
				<div style="margin-left: 300px; margin-top: -25px; float: left; display: inline;"><input type="text" placeholder="아이디 조건" Name="id" id="inputid1" value="<%=cookieId !="" ? cookieId : "" %>"></div>
				<div style="margin-left: 200px; margin-top: 20px; float: left;">패스워드</div>
				<div style="margin-left: 300px; margin-top: -25px; float: left;"><input type="password" placeholder="비밀번호 조건" Name ="pwd"></div>
				<div style="margin-left: 250px; margin-top: 20px; float: left;"></div>
				<div><Input Type = "Submit" Value = "로그인" id="loginbutton1"> <%--유병렬 입력한것--%></div>
			</form>
				<div>
					<form action="newaccount" method="post">
						<Input Type="Submit" Value="회원가입" id="signupbutton1">
					</form>
				</div>
				<div>
					<%
					boolean email = (boolean)request.getAttribute("unknown_email");
					boolean emailpwd = (boolean)request.getAttribute("email_pwd_match");
					boolean logout = (boolean)request.getAttribute("logout");
					boolean delaccount = (boolean)request.getAttribute("delaccount");
					boolean created_account = (boolean)request.getAttribute("created_account");
					boolean error = (boolean)request.getAttribute("error");
					if(email) {	%>
					<div id="yu">존재하지 않는 아이디입니다.</div>
					<%} else if (email){ %>
						<div id="yu">아이디를 입력해주세요.</div>
					<%}	else if (emailpwd) {%>
						<div id="yu">이메일과 암호가 일치하지 않습니다.</div>
					<%} else if (delaccount) {%>
						<div id="yu">계정이 삭제되었습니다.</div>
					<%} else if (logout) {%>
						<div id="yu">로그아웃 되었습니다.</div>
					<%}
						if (created_account) { %> <div id="yu">계정이 생성되었습니다.</div> <%}
						if (error) { %>	<div id="yu">이미 있는 닉네임입니다.</div> <%}


					%>
				</div>
				<div style="margin-left: 350px; margin-top: 17px; float: left;"><input type="checkbox" id="saveId" name="saveId" <%=cookieId!=""?"checked" : ""%>></div>
				<div style="margin-left: 370px; margin-top: -20px; float: left; display: inline;">아이디 저장</div>
				<div OnClick="location.href ='findpwd'" style="cursor: pointer; margin-left: 300px; margin-top: 20px; float: left; display: inline;" id="asdf">비밀번호 찾기</div>

			<%--<div id="signupbutton1"><a href="newaccount" id="signupbutton2">회원가입</a></div>--%> <%--유병렬 제거한것--%>
		</div>
	<%}	else {
		Enumeration en = MainController.loginUsers.keys();

		while(en.hasMoreElements()){
			String key = en.nextElement().toString();
			System.out.println("aaaa");
			if(loginduplicate){
				System.out.println("bbbb");%>

	<script>
		alert("이미 로그인 되어 있습니다.");
	</script>
	<div id="logbox1">
		<div id="logbox2">
			<p id="yuba">현재 접속자 수 : ${users}명</p>
		</div>
		<form action="main" method="post">
			<div style="margin-left: 200px; margin-top: 20px; float: left; display: inline;">아이디</div>
			<div style="margin-left: 300px; margin-top: -25px; float: left; display: inline;"><input type="text" placeholder="아이디 조건" Name="id" id="inputid1" value="<%=cookieId !="" ? cookieId : "" %>"></div>
			<div style="margin-left: 200px; margin-top: 20px; float: left;">패스워드</div>
			<div style="margin-left: 300px; margin-top: -25px; float: left;"><input type="password" placeholder="비밀번호 조건" Name ="pwd"></div>
			<div style="margin-left: 250px; margin-top: 20px; float: left;"></div>
			<div><Input Type = "Submit" Value = "로그인" id="loginbutton1"> <%--유병렬 입력한것--%></div>
		</form>
		<div>
			<form action="newaccount" method="post">
				<Input Type="Submit" Value="회원가입" id="signupbutton1">
			</form>
		</div>
		<div>
			<%
				boolean email = (boolean)request.getAttribute("unknown_email");
				boolean emailpwd = (boolean)request.getAttribute("email_pwd_match");
				boolean logout = (boolean)request.getAttribute("logout");
				boolean delaccount = (boolean)request.getAttribute("delaccount");
				boolean created_account = (boolean)request.getAttribute("created_account");
				boolean error = (boolean)request.getAttribute("error");
				if(email) {	%>
			<div id="yu">존재하지 않는 아이디입니다.</div>
			<%} else if (email){ %>
			<div id="yu">아이디를 입력해주세요.</div>
			<%}	else if (emailpwd) {%>
			<div id="yu">이메일과 암호가 일치하지 않습니다.</div>
			<%} else if (delaccount) {%>
			<div id="yu">계정이 삭제되었습니다.</div>
			<%} else if (logout) {%>
			<div id="yu">로그아웃 되었습니다.</div>
			<%}
				if (created_account) { %> <div id="yu">계정이 생성되었습니다.</div> <%}
			if (error) { %>	<div id="yu">이미 있는 닉네임입니다.</div> <%}


		%>
		</div>
		<div style="margin-left: 350px; margin-top: 17px; float: left;"><input type="checkbox" id="saveId" name="saveId" <%=cookieId!=""?"checked" : ""%>></div>
		<div style="margin-left: 370px; margin-top: -20px; float: left; display: inline;">아이디 저장</div>
		<div OnClick="location.href ='findaccount'" style="cursor: pointer; margin-left: 300px; margin-top: 20px; float: left; display: inline;">아이디/비밀번호 찾기</div>

		<%--<div id="signupbutton1"><a href="newaccount" id="signupbutton2">회원가입</a></div>--%> <%--유병렬 제거한것--%>
	</div>

	<%System.out.println("ccc");
	} else {%>
	<div id="logbox1">
		<div id="logbox2">
			<p id="yuba">현재 접속자 수 : ${users}명</p>
		</div>
		<table id="table1">
			<tr>
				<td>${mem.getEmail()}님 환영합니다!</td>
				<td>
					<form action="editaccount" method="post"> <!-- 내 전적으로 바꿈 -->
						<Input Type="Submit" Value="내 정보 수정 ▶" id="but1">
					</form>
				</td>
			</tr>
		</table>
		<table id="table2">
			<tr>
				<td>
					<form action="record" method="post"> <!-- 내 전적으로 바꿈 -->
						<Input Type="Submit" Value="내 전적 ▶" id="but2">
					</form>
				</td>
				<td>
					<form action="mygamelist" method="get"> <!-- form 태그 안에 내용 바꿔라 -->
						<Input Type="Submit" Value="내 게임 ▶" id="but3">
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<form action="" method="post">
						<Input Type="Submit" Value="위시리스트 ▶" id="but4">
					</form>
				</td>
				<td>
					<form action="logout" method="post">
						<Input Type="Submit" Value="로그아웃 ▶" id="but5">
					</form>
				</td>
			</tr>
		</table>
	</div>
	<%}
			break;
	}
	}%>
	<div id="logbox3">
	</div>
	<div id="logbox4">
		박스
		<script type="text/javascript">
			//window.location.href = "http://stackoverflow.com";
			//api 구현 못함
		</script>
		<input type="button" value="새창"
			   onclick="window.open('http://stackoverflow.com', '팝업창 이름', 'width=1000, height=1000')">
		<input type="button" value="새창1"
			   onclick="window.open('http://naver.com', '팝업창 이름1', 'width=1000, height=1000')">
		<input type="button" value="새창2"
			   onclick="window.open('https://start.spring.io/', '팝업창 이름2', 'width=1000, height=1000')">
	</div>
	<div id="logbox5">

		<table>
			<br>
			<c:forEach var="game" items="${Rank_list}" varStatus="status" begin="0" end="2">
				<gameimage>
					<a href="gameinfo?game=${game.game_number}">
						<img class="img" src="image/${game.game_image}"/>
					</a>
				</gameimage>
			</c:forEach>
		</table>
	</div>
</body>
</html>
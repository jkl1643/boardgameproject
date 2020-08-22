<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <title>고객문의 작성</title>
</head>
<body>


<form action="testok" method="post">
	
	<p>
		<label>총<br>
			<Input Type="Text" Name="total">
		</label>
	</p>
	
	<p>
		<label>승<br>
			<Input Type="Text" Name="win" >
		</label>
	</p>
	<p>
		<label>패<br>
			<Input Type="Text" Name="lose">
		</label>
	</p>
	<p>
		<label>무<br>
			<Input Type="Text" Name="draw" >
		</label>
	</p>
	<p>
		<label>게<br>
			<Input Type="Text" Name="game_number">
		</label>
	</p>
	<p>
		<label>멤버<br>
			<Input Type="Text" Name="mem_number" >
		</label>
	</p>
	
	
	<%-- 
	<div style="display:none;">
	<p>
		<label>작성자<br>
			<Input Type="Text" Name="name1" value="${custom1.name}">
		</label>
	</p>
	<p>
		<label>이메일<br>
			<Input Type="Text" Name="email1" value="${custom1.email}">
		</label>
	</p>
	</div>	
	--%>
	
	<input type="submit" value="수정하기">
</form>



 	<a href="<c:url value="/custom"/>" style="text-decoration:none" >돌아가기 </a>
 	
</body>
</html>
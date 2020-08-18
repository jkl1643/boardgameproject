<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <title>글 수정</title>
</head>
<body>
<form action="customchangeok" method="post">
	<div style="display:none;">
	<p>
		<label>번호<br>
			<Input Type="Text" Name="count1" value="${custom1.count}">
		</label>
	</p>
	</div>
	<p>
		<label>제목<br>
			<Input Type="Text" Name="title1" value="${custom1.title}">
		</label>
	</p>
	<p>
		<label>내용<br>
			<Input Type="Text" Name="content1" value="${custom1.content}">
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


 	
</body>
</html>
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
    <STYLE TYPE="text/css">
        <!--
        BODY {background-image: url('dia.jpg'); background-repeat: no-repeat; background-size: cover}
        h1#text1 {}
        div#box1 {border: 5px solid black; width: 1000px; height: 700px; position: relative; left: 400px; top: 10px}
        label#label1 {position: relative; left: 70px; top: 10px}
        input#text2 {width: 800px; height: 20px; position: relative; left: 100px; top: 10px}
        textarea#text3 {width: 930px; height: 580px; position: relative; left: 30px; top: 30px}
        input#submit1 {width: 100px; height: 50px; background-color: black; color: white; position: relative; left: 868px; top: 30px}
        button#button1 {width: 140px; height: 70px; background-color: lightgreen; color: black; position: relative; left: 840px; top: 40px}
        button#button1:hover {background-color: red; color: blue}
        -->
    </STYLE>
</head>
<body>
<h1 id="text1">고객지원</h1>
<div id="box1">
    <form:form action="customwriteok" modelAttribute="customrequest">
        <label for="text2" id="label1">제목&nbsp;&nbsp;</label>
        <form:input type="text" path="title" id="text2" placeholder="제목을 입력하세요." /> <%--이 부분 수정좀 수명--%>
        <form:textarea path="content" id="text3" placeholder="문의 내용을 입력하세요." /> <%--이 부분 수정좀 수명--%>
        <input type="submit" value="저장하기" id="submit1">
    </form:form>
</div>
<button id="button1"><a href="<c:url value="/custom"/>">메인으로</a></button>
</body>
</html>
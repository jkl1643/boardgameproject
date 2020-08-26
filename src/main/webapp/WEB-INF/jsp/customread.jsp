<%@ page contentType="text/html; charset=utf-8" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>${custom.title}</title>
    <STYLE TYPE="text/css">
        <!--
        BODY {background-image: url("dia.jpg"); background-repeat: no-repeat; background-size: cover}
        h1#text1 {}
        div#box1 {border: 5px solid black; width: 1000px; height: 700px; position: relative; left: 400px; top: 10px}
        label#label1 {position: relative; left: 70px; top: 10px}
        input#text2 {width: 800px; height: 20px; position: relative; left: 100px; top: 10px}
        textarea#text3 {width: 930px; height: 580px; position: relative; left: 30px; top: 30px}
        button#button1 {width: 100px; height: 50px; background-color: white; color: blue; position: relative; left: 350px; top: 30px}
        button#button2 {width: 100px; height: 50px; background-color: white; color: blue; position: relative; left: 450px; top: 30px}
        button#button3 {width: 140px; height: 70px; background-color: lightgreen; color: black; position: relative; left: 840px; top: 40px}
        button#button3:hover {background-color: red; color: blue}
        -->
    </STYLE>
</head>
<body>
<h1 id="text1">고객지원</h1>
<div id="box1">
    <label for="text2" id="label1">제목&nbsp;&nbsp;</label>
    <input type="text" value="${custom.title}" id="text2" readonly>
    <textarea id="text3" readonly>${custom.content}</textarea>
    <c:set var="number" value="${custom.number}"/>
    
    <c:if test="${mem.getId() eq number}">
        <button id="button1"><a href="<c:url value="/delete/${custom.count}" />">글 삭제</a></button>
        <button id="button2"><a href="<c:url value="/customchange/${custom.count}" />">글 수정</a></button>
    </c:if>
</div>
<button id="button3"><a href="<c:url value="/custom"/>" style="text-decoration:none">돌아가기</a></button>
</body>
</html>
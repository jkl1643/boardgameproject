<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>고객문의 목록</title>
</head>
<body>
<c:forEach var="questionlist" items="${QuestionList}" varStatus="status">
    ${questionlist.count}&nbsp;&nbsp;
    <a href="<c:url value="/content/${questionlist.count}"/> ">${questionlist.title} </a>
    ${questionlist.name}&nbsp;&nbsp;&nbsp;${questionlist.registerDateTime}
    <br>
</c:forEach>

<c:if test="${! empty mem}">
    <p>
        <a href="<c:url value="/customwrite" />">글쓰기 </a>
    </p>
</c:if>

<p>
    <a href="<c:url value="/home" />">메인으로 </a>
</p>
</body>
</html>


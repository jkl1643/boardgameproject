<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-08-05
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title></title>
</head>
<body>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>일련번호</th>
            <th>방 이름</th>
            <th>입장버튼</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${Room_list}" varStatus="status">
            <tr>
                <td>${room.ID}</td>
                <td>${room.name}</td>
                <td>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-primary pull-right" href="/new">새로 만들기</a>
</div>
</body>
</html>

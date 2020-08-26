<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-08-12
  Time: 오후 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 30%; /* Could be more or less, depending on screen size */
            height: 30%;
        }
    </style>
</head>
<body>
<table style="width: 100%;">
    <tr align="center">
        <td> 방제목</td>
        <td> 인원</td>
        <td> 상태</td>
        <td> 입장</td>
    </tr>
    <c:forEach var="room" items="${Room_list}" varStatus="status">
        <c:if test="${room.status eq 'Waiting'}">
            <tr align="center">
                <td>${room.name}</td>
                <td>${room.player} / ${room.maxplayer}</td>
                <td>${room.status}</td>
                <td>
                    <button id="${room.ID}" onclick="join1(this.id)">입장</button>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
<div id="joinRoom" class="modal">
    <div class="modal-content">
        <form action="join" Method="post">
            <input type="text" id="joinid" name="joinid"/><br><br>
            비밀번호 : <input type="password" id="joinpw" name="joinpw" value=""/><br><br>
            <Input Type="Submit" Value="방 입장"/><br>
        </form>
        <button id="joinclose">창 닫기</button>
    </div>
</div>
<script type="text/javascript">
    // 방 입장 생성 관련
    var join = document.getElementById('joinRoom');
    var joinclose = document.getElementById('joinclose');

    function join1(roomid) {
        join.style.display = "block";
        document.getElementById('joinid').value = roomid;
    }

    joinclose.onclick = function () {
        join.style.display = "none";
    }
</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-08-05
  Time: 오후 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title></title>
</head>
<body>

<%
    String nick = (String)session.getAttribute("idid");
    if (nick == null || nick.equals(""))
        response.sendRedirect("home");
%>
<div id = "chatroom" style = "width:400px; height: 600px; border:1px solid; background-color : gray"></div>
<label for="message"></label><input type = "text" id = "message" style = "height : 30px; width : 340px" placeholder="내용을 입력하세요" autofocus>
<button class = "btn btn-primary" id = "send">전송</button>

<script type="text/javascript">
    var webSocket;
    var nickname = "<%=nick%>";
    var roomId = "${id}";

    webSocket = new WebSocket("ws://" + location.host + "/chat");
    webSocket.onopen = onOpen;
    webSocket.onclose = onClose;
    webSocket.onmessage = onMessage;

    document.getElementById("send").addEventListener("click",function(){
        send();
    })




    function disconnect(){
        webSocket.send(JSON.stringify({roomID : roomId, type:"disconnect", writer:nickname}));
        webSocket.close();
    }
    function send(){
        msg = document.getElementById("message").value;

        webSocket.send(JSON.stringify({roomID : roomId, type:"chat", writer:nickname, message : msg}));
        document.getElementById("message").value = "";
    }
    function onOpen(){
        webSocket.send(JSON.stringify({roomID : roomId, type:"connect", writer:nickname}));
    }
    function onMessage(e){

        data = e.data;
        chatroom = document.getElementById("chatroom");
        chatroom.innerHTML = chatroom.innerHTML + "<br>" + data;
    }
    function onClose(){
        disconnect();
    }

</script>
</body>
</html>
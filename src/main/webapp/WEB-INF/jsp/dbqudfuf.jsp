<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Title</title>
</head>
<meta charset="UTF-8">
<script language="javascript" type = "text/javascript">
    var wsUri = "ws://" + location.host + "/chat";
    var output;
    function init(){
        output = document.getElementById("output");
        testWebSocket();
    }
    function testWebSocket(){
        webSocket = new WebSocket(wsUri);
        webSocket.onopen = function (evt) { onOpen(evt); };
        webSocket.onclose = function (evt) { onClose(evt); };
        webSocket.onmessage = function (evt) { onMessage(evt); };
        webSocket.onerror = function (evt) { onError(evt); };
    }
    function onOpen(evt){
        writeToScreen("연결완료");
        doSend("테스트 메세지");
    }

    function onClose(evt){
        writeToScreen("연결해제");
    }

    function onMessage(evt){
        writeToScreen('수신 : ' + evt.data);
        webSocket.close();
    }

    function onError(evt){
        writeToScreen('에러 : ' + evt.data);
    }

    function doSend(message){
        writeToScreen("발신 : " + message);
        webSocket.send(message);
    }

    function writeToScreen(message){
        var pre = document.createElement("p");
        pre.style.wordWrap = "break-word";
        pre.innerHTML = message;
        output.appendChild(pre);
    }

    window.addEventListener("load", init, false);
</script>
<body>
    <h2>테스트</h2>
    <div id = "output"></div>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-08-08
  Time: 오후 4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>(게임이름) 게임방</title>

  <style>
    @import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);
    body {
      align-content: center;
      justify-content: center;
      display: grid;
      grid-template-areas:
          "gamename gamemenu gamemenu"
          "roomlist roomlist userlist"
          "chatting chatting userinfo";
      grid-template-rows: minmax(30px, 60px) minmax(210px, 420px) minmax(120px, 240px);
      grid-template-columns: minmax(120px, 240px) minmax(240px, 480px) minmax(140px, 280px);
      grid-row-gap: 10px;
      grid-column-gap: 10px;
      height: 100vh;
      margin: 0;
      background-image: url('tmi.jpg');
      background-repeat: no-repeat;
      background-size: cover

    }
    #pggamename{ grid-area: gamename; text-align: center; font-family: 'Nanum Pen Script', cursive}
    #pggamemenu{ grid-area: gamemenu; }
    #pgroomlist{ grid-area: roomlist; }
    #pguserlist{ grid-area: userlist; }
    #pgchatting{ grid-area: chatting; }
    #pguserinfo{ grid-area: userinfo; }

    .modal {
      display: none;
      position: fixed;
      z-index: 1;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,0.4);
    }

    .modal-content {
      background-color: #fefefe;
      margin: 15% auto; /* 15% from the top and centered */
      padding: 20px;
      border: 1px solid #888;
      width: 30%; /* Could be more or less, depending on screen size */
      height: 30%;
    }

    gamename, gamemenu, roomlist, userlist, chatting, userinfo{
      padding: 1.2em;
      background: #96979A;
      border-radius: 10px;
    }

    ul {list-style-type: none; margin: 0; padding: 0; width: 700px}
    ul:after {content: ''; display: block; clear: both}
    li {float: left}
    li a {display: block; color: black; text-align: center; padding: 1px 80px; text-decoration: none}
    li a:hover {color: blue}
    h1#text1 {position: relative; top: -10px; vertical-align: top; font-size: medium; text-align: center}
    table#table121 {float: left}
  </style>
</head>
<body>
<%--  미 접속 유저 입장 제한  --%>
<%
  String nick = (String)session.getAttribute("idid");

%>

<%-- 게임 이름 출력 --%>
<gamename id="pggamename"> ${Game.game_name} </gamename>

<%--  메뉴  --%>
<gamemenu id="pggamemenu">
  <ul>
    <li><a class="active" href="#home" onclick="create1()">방 생성</a></li>
    <li><a href="#gamerank">전체 방</a></li>
    <li><a href="#theme">대기방</a></li>
  </ul>
</gamemenu>

<%--  방 생성 모달 레이어  --%>
<div id="createRoom" class="modal">
  <div class="modal-content">
    <Form action="createroom" Method="post"><br>
      방제목 : <input type="text" value="<%=nick%>님의 게임방입니다." name="Createroomname"/><br><br>
      게임 : <input type="text" placeholder="게임" name="Createroomgame" readonly="readonly" value="Yahtzee"/><br><br>
      비밀번호 : <input type="text" id="Createroompw" name="Createroompw" disabled="disabled" value=""/><br><br>
      비번 사용 <input type="checkbox" name="Usepw" id="Usepw" value=false/><br><br>
      <Input Type ="Submit" Value="방 생성"/>
    </Form> <button id="createclose">창 닫기</button>
  </div>
</div>


<%-- 방 목록 레이아웃 --%>

<roomlist id="pgroomlist">
  <div id="roomlist" style="height: 100%; overflow:auto;">
  </div>
</roomlist>


<%-- 방 입장 모달레이어 --%>
<div id="joinRoom" class="modal">
  <div class="modal-content">
    <form action="join" Method="post">
      <input type="text" id="joinid" name="joinid"/><br><br>
      비밀번호 : <input type="password" id="joinpw" name="joinpw" value=""/><br><br>
      <Input Type ="Submit" Value="방 입장"/><br>
    </form> <button id="joinclose">창 닫기</button>
  </div>
</div>

<%--  로비 채팅 구현  --%>
<chatting id="pgchatting">
  <div id="lobbychatroom" style="overflow:auto; height: 90%; width:100%"></div>
  <input type = "text" id = "message" style = "height : 15%; width : 85%" placeholder="내용을 입력하세요" autofocus/>
  <button class = "btn btn-primary" id = "send" style="height: 15%; width:10%">전송</button>
</chatting>

<%--  유저 목록 출력  --%>
  <userlist id="pguserlist">
    <div id="userlist" style="height: 100%; overflow:auto;">
    </div>
  </userlist>

<%-- 내 계정 정보 출력 --%>
<userinfo id="pguserinfo">
  <h1 id="text1"><%=nick%>님</h1>
  <table id="table121">
    <tr><td>접속중인 게임 :  ${Game.game_name}</td></tr>
    <tr><td>게임 전적 : ${Stat.total}전 ${Stat.win}승 ${Stat.draw}무 ${Stat.lose}패</td></tr>
    <tr><td><a href="home" title="홈으로">돌아가기</a></td></tr>
  </table>
</userinfo>
</body>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
<script type="text/javascript">
  $(document).ready(function () {$("#roomlist").load("refreshgamelist"); $("#userlist").load("refreshuserlist");});
  // 방 입장 생성 관련
  var create = document.getElementById('createRoom');
  var join = document.getElementById('joinRoom');
  var createclose = document.getElementById('createclose');
  var joinclose = document.getElementById('joinclose');
  var usepw = document.getElementById('Usepw');
  var pw = document.getElementById('Createroompw');

  // 채팅 관련
  var webSocket;
  var nickname = "<%=nick%>";

  webSocket = new WebSocket("ws://" + location.host + "/chat");
  webSocket.onopen = onOpen;
  webSocket.onclose = onClose;
  webSocket.onmessage = onMessage;


  function create1() {
    create.style.display = "block";
  }

  function join1(roomid) {
    join.style.display = "block";
    document.getElementById('joinid').value = roomid;
  }

  createclose.onclick = function () {
    create.style.display = "none";
  }

  joinclose.onclick = function () {
    join.style.display = "none";
  }

  usepw.onclick = function () {
    if (usepw.checked) {
      pw.disabled = false
    } else {
      pw.disabled = true
    }
  }

  // 채팅 // 자바스크립트 나중에 따로 떼어내기
  document.getElementById("send").addEventListener("click", function () {
    send();
  })
  document.addEventListener('keydown', function (e) {
    entersend(e);
  })

  function disconnect(){
    webSocket.send(JSON.stringify({roomID : "lobby", type:"disconnect", writer:nickname}));
    webSocket.close();
  }

  function send(){
    msg = document.getElementById("message").value;
    webSocket.send(JSON.stringify({roomID : "lobby", type:"chat", writer:nickname, message : msg}));
    document.getElementById("message").value = "";
  }

  function entersend(e) {
    if (e.keyCode == 13)
      send();
  }
  function onOpen(){
    webSocket.send(JSON.stringify({roomID : "lobby", type:"connect", writer:nickname}));
  }

  function onMessage(e) {
    var js = JSON.parse(e.data);
    switch (js.type) {
      case "chat":
        $('#userlist').load("refreshuserlist");
        chatroom = document.getElementById("lobbychatroom");
        chatroom.innerHTML = chatroom.innerHTML + "<br>" + js.message;
        chatroom.scrollTop = chatroom.scrollHeight;
        break;

      case "create":
        $('#roomlist').load("refreshgamelist");
        break;

      default:
        alert("설정 되지 않은 타입입니다. [ " + js.type + " ]");
    }


  }

  function onClose(){
    disconnect();
  }

</script>
</html>
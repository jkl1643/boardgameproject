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
  <title>Title</title>

  <style>
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
      background: ivory;

    }
    #pggamename{ grid-area: gamename; }
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
      background: lightblue;
      border-radius: 10px;
    }

  </style>
</head>
<body>
  <gamename id="pggamename"> 게임 이름 <br> parameter 로 받아서 이름을 출력한다. ( 수직, 수평 가운데 정렬 ) </gamename>
  <gamemenu id="pggamemenu"> <button id="Modalbtn" onclick="test()">방 생성</button> <br> 게임 메뉴 버튼. (=방생성, 전체방, 대기방)</gamemenu>
  <div id="createRoom" class="modal">
    <div class="modal-content">
      <Form action="createroom" Method="post"><br>
        방제목 : <input type="text" value="닉네임님의 게임방입니다." name="Createroomname"/><br><br>
        게임 : <input type="text" placeholder="게임" name="Createroomgame" readonly="readonly" value="Yahtzee"/><br><br>
        비밀번호 : <input type="text" id="Createroompw" name="Createroompw" disabled="disabled" valuse=""/><br><br>
        비번 사용 <input type="checkbox" name="Usepw" id="Usepw" value=false/><br><br>
        <Input Type="Submit" Value="방 생성"/>
      </Form>
      <button id="Modalclose">창 닫기</button>
    </div>
  </div>

  <roomlist id="pgroomlist">
    방 목록 출력 <br>( 5개씩 페이지구성 or 스크롤 방식 )
    <table>
      <td> 방제목</td>
      <td> 인원</td>
      <td> 상태</td>
      <td> 입장</td>
      <c:forEach var="room" items="${Room_list}" varStatus="status">
        <tr>
          <td>${room.name}</td>
          <td> ${room.player} / ${room.maxplayer}</td>
          <td> 구현중</td>
          <td>
            <a href="join?roomid=${room.ID}"
               onclick="window.open(this.href,'_blank', 'width=800, height=600'); return false;" href="mainlobby">입장</a>
            // roomid 말고 pw 랑 userid도 주입 해야함.
          </td>
        </tr>
      </c:forEach>
    </table>
  </roomlist>
  <chatting id="pgchatting"> 로비 채팅 구현 <br>( 접속중인 소켓 전체 전송 ) </chatting>
  <userlist id="pguserlist"> 접속중인 유저 목록 <br>( 접속중인 소켓의 닉네임 전체 출력 ) </userlist>
  <userinfo id="pguserinfo"> 내 정보 <br>( 세션으로 계정확인 )</userinfo>
</body>
<script type="text/javascript">
  var modal = document.getElementById('createRoom');
  var modalbtn = document.getElementById('Modalbtn');
  var modalclose = document.getElementById('Modalclose');
  var usepw = document.getElementById('Usepw');
  var pw = document.getElementById('Createroompw');
  function test() { modal.style.display = "block"; }
  modalclose.onclick = function() { modal.style.display = "none"; }
  usepw.onclick = function() { if(usepw.checked) { pw.disabled=false} else {pw.disabled=true}}
</script>
</html>

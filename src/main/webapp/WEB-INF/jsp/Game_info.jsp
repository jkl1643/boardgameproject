<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-08-16
  Time: 오전 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${Game.game_name}-구매페이지</title>
    <style>
        body {
            justify-content: center;
            display: grid;
            grid-template-areas:
            "title title"
			"gameimage gamecontrol"
			"descr descr"
			"rule rule";
            grid-template-rows: 100px 300px 200px;
            grid-template-columns:400px minmax(150px, 210px);
            grid-row-gap: 20px;
            grid-column-gap: 20px;
            height: 100vh;
            margin: 0;
            background-image: url('tmi.jpg');
            background-repeat: no-repeat;
            background-size: cover
        }
        #pgtitle{color: white; text:grid-area: title; text-align: left;}
        #pggameimage{ grid-area: gameimage; }
        #pgcontrol{ grid-area: gamecontrol; }
        #pgdescr{ grid-area: descr; }
        #pgrule{ grid-area: rule; }


        gameimage, control, descr, rule{
            text-align: left;
            padding: 1.2em;
            background: #6A6B6B;
            border-radius: 10px;
        }
        .info_img
        {
            width: 100%;
            height: 100%;
        }

        button#buybtn {font-size: large; text-align: center; background-color: black; color: white; position: relative; width: 120px; height: 100px; left: 20px; top: 10px}
        button#playbtn {font-size: large; text-align: center; background-color: black; color: white; position: relative; width: 150px; height: 50px; left: 5px; top: 60px}
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
        a#te1 {font-size: xx-large; color: white}
        a#te2 {position: relative; top: 800px; font-size: xx-large; color: white}
    </style>
</head>
<body>
<div id="pgtitle"> <H1>${Game.game_name}</H1> </div>
<gameimage id="pggameimage"><img class="info_img" src="image/${Game.game_image}"/></gameimage>
<control id="pgcontrol">
    <button id="buybtn" onclick="buy()"> 게임 구매<BR>10000원 </button>
    <button id="playbtn" onclick="location.href='mygamelist'"> 플레이 하러가기 </button>
</control>
<descr id="pgdescr">${Game.game_info}</descr>
<rule id="pgrule">${Game.game_rule}</rule>


<!-- <%-- 구매 모달레이어 --%>
<div id="buyGame" class="modal">
    <div class="modal-content">
        <form action="buygame" Method="get">
            <input type="text" id="gamenumber" name="gamenumber" value="${Game.game_number}"/><br><br>
            <Input Type ="Submit" Value="구매하기&#13;10000원" id="butot"/><br> 가격도 유동적으로 수정해주면 좋을듯?
        </form> <button id="closebtn">취소</button>
    </div>
</div> -->
<a style="position:fixed;bottom:30px;right:20px;" href="home" title="홈으로" id="te1">홈으로</a>
<a style="position:fixed;bottom:50px;right:20px;" href="gamerank" title="목록으로" id="te2">목록으로</a>
</body>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
<script type="text/javascript">
    var buyGame = document.getElementById('buyGame');
    var buybtn = document.getElementById('buybtn');
    var playbtn = document.getElementById('playbtn');
    var btn = document.getElementById('closebtn');

    $(document).ready(function () { if("${Checking}" == "0") { buybtn.disabled=false; playbtn.disabled=true; } else { buybtn.disabled=true; playbtn.disabled=false;}});

    function buy() { buyGame.style.display = "block"; }
    btn.onclick = function() { buyGame.style.display = "none"; }
</script>
</html>
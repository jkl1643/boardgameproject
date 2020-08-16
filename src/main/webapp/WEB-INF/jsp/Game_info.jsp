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
    <title>Title</title>
    <style>
        body {

            justify-content: center;
            display: grid;
            grid-template-areas:
            "title title"
			"gameimage gamecontrol"
			"descr descr"
			"rule rule";
            grid-template-rows: 150px 300px 200px;
            grid-template-columns:400px minmax(150px, 210px);
            grid-row-gap: 20px;
            grid-column-gap: 20px;
            height: 100vh;
            margin: 0;
            background: ivory;

        }
        #pgtitle{ grid-area: title;}
        #pggameimage{ grid-area: gameimage; }
        #pgcontrol{ grid-area: gamecontrol; }
        #pgdescr{ grid-area: descr; }
        #pgrule{ grid-area: rule; }


        gameimage, control, descr, rule{
            text-align: center;
            padding: 1.2em;
            background: lightblue;
            border-radius: 10px;
        }
        .info_img
        {
            width: 350px;
            height: 250px;
        }
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
    </style>
</head>
<body>
<div id="pgtitle"> <H1>게임정보</H1> </div>
<gameimage id="pggameimage"><img class="info_img" src="image/${Game.game_image}"/></gameimage>
<control id="pgcontrol">
    <button id="buybtn" onclick="buy()"> 게임 구매 </button>
</control>
<descr id="pgdescr">${Game.game_info}</descr>
<rule id="pgrule">${Game.game_rule}</rule>


<%-- 구매 모달레이어 --%>
<div id="buyGame" class="modal">
    <div class="modal-content">
        <form action="buygame" Method="get">
            <input type="text" id="gamenumber" name="gamenumber" value="${Game.game_number}"/><br><br>
            <Input Type ="Submit" Value="구매하기"/><br>
        </form> <button id="closebtn">취소</button>
    </div>
</div>

</body>
<script type="text/javascript">
    var buyGame = document.getElementById('buyGame');
    var btn = document.getElementById('closebtn');

    function buy() { buyGame.style.display = "block"; }
    btn.onclick = function() { buyGame.style.display = "none"; }
</script>
</html>

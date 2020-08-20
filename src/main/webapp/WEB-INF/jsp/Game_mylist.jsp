<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-08-19
  Time: 오후 3:19
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
                "titles titles titles"
				"game game game";
            grid-template-rows: 100px repeat(10, 200px);
            grid-template-columns: 200px 200px 200px;
            grid-row-gap: 10px;
            grid-column-gap: 10px;
            height: 100vh;
            margin: 10px;
            background: ivory;

        }

        #pg2 { grid-area: titles; text-align:center;}
        game{
            align-items: center;
            text-align: center;
            justify-content: center;
            padding: 1.2em;
            background: lightblue;
            border-radius: 10px;
        }
        .img
        {
            height: 150px;
            width: 150px;
        }
    </style>
</head>
<body>
<titles id="pg2"><h1>내 게임 목록 </h1>
    각 게임의 이미지를 클릭하면 해당 게임로비로 이동합니다.<br><br></titles>

<c:forEach var="game" items="${My_list}" varStatus="status">
    <game>
        <a href="gamelobby?gamenumber=${game.game_number}"><img class="img" src="image/${game.game_image}"/></a><br>
        <a href="gameinfo?game=${game.game_number}">${game.game_name}</a>
    </game>
</c:forEach>

<a style="position:fixed;bottom:50px;right:20px;" href="#" title="맨 위로">맨 위로</a>
<a style="position:fixed;bottom:30px;right:20px;" href="main.jsp" title="홈으로">홈으로</a>
</body>
</html>

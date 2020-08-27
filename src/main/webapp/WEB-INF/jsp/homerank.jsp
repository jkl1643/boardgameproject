<%--
  Created by IntelliJ IDEA.
  User: Yu
  Date: 2020-08-22
  Time: 오후 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" %>
<head>
    <title>Title</title>
    <style>
        body {
            justify-content: center;
            display: grid;
            grid-template-areas:
                "title title"
                "gameimage gameinfo";
            grid-template-rows: 150px repeat(10,200px);
            grid-template-columns: 200px 500px;
            grid-row-gap: 10px;
            grid-column-gap: 10px;
            height: 100vh;
            margin: 10px;
            background: ivory;

        }

        gameimage, gameinfo{
            text-align: center;
            padding: 1.2em;
            background: lightblue;
            border-radius: 10px;
        }
        #ddd
        {
            text-align: center;
            grid-area: title;
        }

        .img
        {
            height: 150px;
            width: 150px;
        }


    </style>
</head>
<body>
<c:forEach var="game" items="${Rank_list}" varStatus="status">
    <gameimage><a href="gameinfo?game=${game.game_number}"><img class="img" src="image/${game.game_image}"/></a></gameimage>
</c:forEach>
</body>
</html>

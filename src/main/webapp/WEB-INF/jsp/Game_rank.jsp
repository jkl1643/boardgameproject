<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
    <style>
        body {

            justify-content: center;
            display: grid;
            grid-template-areas:
                "gameimage gameinfo";
            grid-template-rows: repeat(10,200px);
            grid-template-columns: 200px 500px;
            grid-row-gap: 10px;
            grid-column-gap: 10px;
            height: 100vh;
            margin: 10px;
            background: ivory;

        }

        gameimage, gameinfo{
            padding: 1.2em;
            background: lightblue;
            border-radius: 10px;
        }

    </style>
</head>
<body>

<c:forEach var="game" items="${Rank_list}" varStatus="status">
    <gameimage> 이미지 <br> 이미지 클릭시 이동합니다. </gameimage>
    <gameinfo><B>${game.game_name}<br></B>${game.game_info}</gameinfo>
</c:forEach>
<a style="position:fixed;bottom:50px;right:20px;" href="#" title="맨 위로">맨 위로</a>
<a style="position:fixed;bottom:30px;right:20px;" href="main.jsp" title="홈으로">홈으로</a>
</body>
</html>

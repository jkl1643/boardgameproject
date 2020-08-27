<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>게임순위별 목록</title>
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
            background-image: url('tmi.jpg');
            background-repeat: no-repeat;
            background-size: cover
        }

        gameimage, gameinfo{
            text-align: center;
            padding: 1.2em;
            background: #6A6B6B;
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

        a#me2 {float: right ; font-size: xx-large; color: white ; text-decoration: none}
    </style>
</head>
<body>
<div id="ddd">
    <H1> 판매중인 보드게임의 인기순위 </H1>
    각 게임의 이미지를 클릭하면 상세페이지로 이동합니다.<br>
</div>
<c:forEach var="game" items="${Rank_list}" varStatus="status">
    <gameimage><a href="gameinfo?game=${game.game_number}"><img class="img" src="image/${game.game_image}"/></a></gameimage>
    <gameinfo><B>${status.count}위 게임 : ${game.game_name} / 판매 수 : ${Rank_count.get(status.count-1)}<br></B>${game.game_info}</gameinfo>
</c:forEach>

<a style="position:fixed;bottom:30px;right:20px;" href="home" title="홈으로" id="me2">홈으로</a>

</body>
</html>

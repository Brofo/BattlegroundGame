<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 27.02.2020
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session ="false"%>
<html>
<head>
    <title>Battleground Game</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="bs">
<img src="res/scrollSide.png"/>
<div class="readygrid">
    <div class="pcard"></div>
    <div class="ocard"></div>
    <div class="vers"></div>
    <div class="pname">${playerName}: ${playerFighter}</div>
    <div class="oname">${opponentName}: ${opponentFighter}</div>
    <div class="ready">
    <form action="servlets.game.PlayersReadyServlet" method="get">
        <input type="image" src="res/Ready.png">
       </div>
    </form>
    </div>
    </div>
</body>
</html>

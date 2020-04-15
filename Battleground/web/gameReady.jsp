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
<div class="grid">
    <div class="pn"> Your name: ${playerName}  Fighter: ${playerFighter}</div>
    <div class="on"> Opponent name: ${opponentName}  Fighter: ${opponentFighter}</div>
    <form action="servlets.game.PlayersReadyServlet" method="get">
        <input type="submit" value="READY">
    </form>
    </div>
    </div>
</body>
</html>

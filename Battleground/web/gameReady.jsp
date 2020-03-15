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
</head>
<body>
    Your name: ${playerName}  Fighter: ${playerFighter}<br>
    Opponent name: ${opponentName}  Fighter: ${opponentFighter}<br>
    <form action="servlets.game.PlayersReadyServlet" method="get">
        <input type="submit" value="READY">
    </form>
</body>
</html>

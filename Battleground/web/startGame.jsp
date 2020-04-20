<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 27.02.2020
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<html>
<head>
    <title>Start A Game</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <meta name="viewport" content="width=1024">
</head>
<body>
<div class="scroll">
<div class="logo">
    <img src="res/Logo.png"/>
</div>

<div class="menu">

<form action="servlets.menu.CreateGameServlet" method="get">
    <div class="create">
        <p>Crete a new game or type in a game id from an already created game</p>
    <input type="image" src="res/Created%20new%20game.png" alt="Create new game"><br><br>
</div>
</form>

<form action="servlets.menu.WaitForPlayerServlet" method="get">
    <div class="id">
    <input type="text" name="gameID">
    </div>
    <div class="join">
        <input type="image" src="res/Join.png" alt="Join game"><br><br>
    </div>
</form>
</div>
</div>
</div>
</body>
</html>
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
</head>
<body>
    <form action="servlets.menu.CreateGameServlet" method="get">
        <input type="submit" value="Create New Game"><br><br>
    </form>

    <form action="servlets.menu.WaitForPlayerServlet" method="get">
        <input type="text" name="gameID">
        <input type="submit" value="Join Game">
    </form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 26.02.2020
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<html>
<head>
    <title>Game created</title>
</head>
<body>
    <font size="+2">You have successfully created a game.<p>
                    The game ID is</font>
    <font size="+5" color="#b22222">${gameID}</font><p>
    Send this ID to the person you want to play with. When you're done, enter it here, and click the button: <p>

    <form action="servlets.menu.WaitForPlayerServlet" method="get">
        <input type="text" name="gameID" value=${gameID}>
        <input type="submit" value="Join Game">
    </form>
</body>
</html>
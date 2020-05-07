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
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<div class="scroll">
<body>
|     <div class="Cmenu">
    <font >You have successfully created a game.<br>
                    The game ID is</font>
    <font  color="#b22222">${gameID}</font><br>
    <p>Send this ID to the person you want to play with. When you're done, enter it here, and click the button:</p> <br>

    <form action="servlets.menu.WaitForPlayerServlet" method="get">
        <div class="id">
        <input type="text" name="gameID" value=${gameID}>
        </div>
        <div class="join">
        <input type="image" src="res/Join.png">
    </div>
    </form>
</div>

</body>
</div>
</html>

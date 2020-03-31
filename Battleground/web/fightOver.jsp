<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 19.03.2020
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fight Over</title>
</head>
<body>
    <font size="+2">The winner of the fight is </font> <font size="+4" color="#b22222">${winnerName}</font><br>
    ${loserName} has <font size="+2" color="green"> ${loserLife} </font> live(s) left.<br>
    ${winnerName} has <font size="+2" color="green"> ${winnerLife} </font> live(s) left.<br>

    <form action="..." method="get">
        <input type="submit" value="Go to shop">
    </form>

    <br>

    <form action="servlets.game.NextFightServlet" method="get">
        <input type="submit" value="Continue to next fight">
    </form>

</body>
</html>

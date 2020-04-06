<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 10.02.2020
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<html>
  <head>
    <title>Battleground</title>
  </head>
  <body>
      <font size="+5" color="#b22222"><b>Battleground</b></font><br><br>

      <form action="servlets.menu.StartPlayServlet" method="get">
        Choose a username:
        <input type="text" name="playerName" value="Player"><br>
        Choose a fighter:
        <select id="fighter" name="fighterName">
            <option name="Chad">Chad</option>
            <option name="Gopnik">Gopnik</option>
        </select><br>
        <input type="submit" value="Play!">
      </form>



        <br><br><br><br>
        <form action="mysteryChest.jsp">
            <input type="submit" value="Test mystery chest">
        </form>
  </body>
</html>

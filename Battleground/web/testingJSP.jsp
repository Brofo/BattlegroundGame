<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 26.02.2020
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testing form</title>
</head>
<body>
        <form action="servlets.menu.CreateGameServlet" method="get">
            <input type="submit" value="CreateGameServlet">
        </form>

        <form action="createdGame.jsp">
            <input type="submit" value="Join a created game">
        </form>
</body>
</html>

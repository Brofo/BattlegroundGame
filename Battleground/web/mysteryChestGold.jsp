<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 04.04.2020
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mystery Chest</title>
</head>
<body>
    You received:<br>
    <font size="+5" color="#daa520">${mysteryGold} </font>
    <font size="+3"> Gold!</font> <br>
    <form action="servlets.items.ClaimMysteryChestServlet" method="get">
        <input type="submit" value="Claim">
        <input type="hidden" value="${mysteryGold}" name="acquiredGold">
    </form>
</body>
</html>
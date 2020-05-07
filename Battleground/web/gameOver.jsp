<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 02.04.2020
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Over</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="bs">
    <img src="res/scrollSide.png" alt="scroll"/>
    <div class="winnergrid">
<div class="winner">
    <font size="+3">Game over. The winner is</font><br>
    <font size="+5" color="#b22222">${winnerName}</font>

        <br>
    <div class="mainm">
    <form action="index.jsp" method="get">
        <input type="submit" value="main menu">
    </form>
    </div>
</div>
</div>
</div>
</body>
</html>

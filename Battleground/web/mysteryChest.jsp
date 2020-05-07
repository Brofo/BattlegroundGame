<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 04.04.2020
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mystery Chest</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="bs">
    <img src="res/scrollSide.png"/>
    <div class="fotext">

    <h1>Mystery Chest</h1>
    <form action="servlets.items.OpenMysteryChestServlet" method="get">
        <input type="submit" value="Open Mystery Chest">
    </form>
    </div>
</div>
</body>
</html>

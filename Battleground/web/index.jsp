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
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="scroll" >

    <div class="logo">
        <img src="res/Logo.png"/>
    </div>

    <div class="menu">
    <form class="buttons" action="servlets.menu.StartPlayServlet" method="get">

       <div class="un">
        Choose a username:<br/>
        <input type="text" name="playerName" value="Player"><br>
        </div>

        <br/>
        <div class="cf">
        Choose a fighter:<br/>
        <select id="fighter" name="fighterName">
            <option name="Chad">Chad</option>
            <option name="Gopnik">Gopnik</option>
        </select>
        </div>
        <div class="Start">
        <input type="image" src="res/Start.png" alt="play">
    </div>
    </form>
    </div>
</div>
</body>
</html>
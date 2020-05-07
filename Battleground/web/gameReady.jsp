<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 27.02.2020
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session ="false"%>
<html>
<head>
    <title>Battleground Game</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="wbs">
<img src="res/scrollSide.png" alt="scroll"/>
<div class="readygrid">
    <div class="ocard"></div>
    <div class="pcard"></div>
    <div class="vers"></div>
    <div class="pname"><font color="#b22222">${playerName}: ${playerFighter}</font></div>
    <div class="oname"><font color="#b22222">${opponentName}: ${opponentFighter}</font></div>
    <div class="pimg">
        <img src="${playerFighterPic}" alt="player image">
    </div>
    <div class="pstats">
        Health: <mark class="green"> ${playerCurrentHealth} / ${playerBaseHealth} </mark><br>
        Energy:<mark class="yellow"> ${playerCurrentEnergy}</mark> <br>
        Damage: <mark class="red">${playerDamage}</mark> <br>
        Armour: <mark class="blue">${playerArmour} </mark> <br>
        Critical chance: <mark class="orange">${playerCriticalChance} </mark><br>
        Dodge chance:<mark class="purple"> ${playerDodgeChance}</mark> <br>
    </div>
    <div class="ready">
    <form action="servlets.game.PlayersReadyServlet" method="get">
        <input type="image" src="res/Ready.png">
    </form>
    </div>
    <div class="ostats">
        Health:<mark class="green"> ${opponentCurrentHealth} / ${opponentBaseHealth} </mark><br>
        Energy:<mark class="yellow"> ${opponentCurrentEnergy}</mark> <br>
        Damage:<mark class="red"> ${opponentDamage}</mark> <br>
        Armour: <mark class="blue">${opponentArmour} </mark><br>
        Critical chance:<mark class="orange"> ${opponentCriticalChance}</mark> <br>
        Dodge chance:<mark class="purple"> ${opponentDodgeChance}</mark>
    </div>

    <div class="oimg">
        <img src="${opponentFighterPic}" alt="player image">
    </div>
    </div>
    </div>
</body>
</html>

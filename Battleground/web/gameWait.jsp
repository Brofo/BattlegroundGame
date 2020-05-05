<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 13.03.2020
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="bs">
    <img src="res/scrollSide.png"/>
    <div class="fightgrid">
        <div class="vers"></div>
        <div class="pcard"></div>
        <div class="ocard"></div>


        <div class="fpname"> <font size="+2">${playerName} [${playerFighter}]</font>
        Life left: <font size="+2" color="green">${playerLife} </font>
        Gold: <font size="+2" color="#daa520">${gold}</font>
    </div>
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
        ${playerDodged}<br>
    </div>
    <div class="oability">
    ${playerCurrentAbility}<br>
    ${playerCritical_hit} <br>
    ${opponentDodged}
</div>
        <div class="wait">
    <form action="servlets.game.WaitForTurnServlet" method="get">
        <input type="submit" value="Wait for my turn">
    </form>
    </div>

<div class="foname">
    <font size="+2">  ${opponentName} [${opponentFighter}]</font> Life left: <font size="+2" color="green">${opponentLife}</font> <br>
</div>
        <div class="ostats">
            Health:<mark class="green"> ${opponentCurrentHealth} / ${opponentBaseHealth} </mark><br>
            Energy:<mark class="yellow"> ${opponentCurrentEnergy}</mark> <br>
            Damage:<mark class="red"> ${opponentDamage}</mark> <br>
            Armour: <mark class="blue">${opponentArmour} </mark><br>
            Critical chance:<mark class="orange"> ${opponentCriticalChance}</mark> <br>
            Dodge chance:<mark class="purple"> ${opponentDodgeChance}</mark> <br> <br>
        </div>
        <div class="oimg">
            <img src="${opponentFighterPic}" alt="player image">
        </div>
</div>
</div>
</body>
</html>

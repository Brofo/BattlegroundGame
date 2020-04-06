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
</head>
<body>
    <font size="+2">${playerName} [${playerFighter}]</font>
    Life left: <font size="+2" color="green">${playerLife} </font>
    Gold: <font size="+2" color="#daa520">${gold}</font><br>

    Health: ${playerCurrentHealth} / ${playerBaseHealth} <br>
    Energy: ${playerCurrentEnergy} <br>
    Damage: ${playerDamage} <br>
    Armour: ${playerArmour} <br>
    Critical chance: ${playerCriticalChance} <br>
    Dodge chance: ${playerDodgeChance} <br>

    <font size="+3" color="#b22222">${playerCurrentAbility}</font><br>
    <font size="+3" color="#daa520">${playerCritical_hit}</font><br>

    <form action="servlets.game.WaitForTurnServlet" method="get">
        <font size="+1">Your opponent is selecting an attack. <br>
                    Click here to wait for your turn:
        </font>
        <input type="submit" value="Wait for my turn">
    </form>

    <font size="+3" color="#1e90ff">${opponentDodged}</font><br>

    <font size="+2">${opponentName} [${opponentFighter}]</font> Life left: ${opponentLife} <br>
    Health: ${opponentCurrentHealth} / ${opponentBaseHealth} <br>
    Energy: ${opponentCurrentEnergy} <br>
    Damage: ${opponentDamage} <br>
    Armour: ${opponentArmour} <br>
    Critical chance: ${opponentCriticalChance} <br>
    Dodge chance: ${opponentDodgeChance} <br> <br>
</body>
</html>

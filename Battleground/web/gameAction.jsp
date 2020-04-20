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
<div class="playerFighter">
    <font size="+2">${playerName} [${playerFighter}]</font>
    Life left: <font size="+2" color="green">${playerLife} </font>
    Gold: <font size="+2" color="#daa520">${gold}</font><br>

    Health: ${playerCurrentHealth} / ${playerBaseHealth} <br>
    Energy: ${playerCurrentEnergy} <br>
    Damage: ${playerDamage} <br>
    Armour: ${playerArmour} <br>
    Critical chance: ${playerCriticalChance} <br>
    Dodge chance: ${playerDodgeChance} <br>

    <font size="+3" color="#1e90ff">${playerDodged}</font><br>

    <form action="servlets.game.UseAbilityServlet" method="get">
        <font size="+3">Your turn. Use ability: </font><br>
        <input type="submit" value="${basicAttack}" name="basicAttack">
        ${basicAttackDesc} | ${basicAttackEnergy}<br>

        <input type="submit" value="${abilityOne}" name="abilityOne">
        ${abilityOneDesc} | ${abilityOneEnergy}<br>

        <input type="submit" value="${abilityTwo}" name="abilityTwo">
        ${abilityTwoDesc} | ${abilityTwoEnergy}<br>

        <input type="submit" value="${abilityThree}" name="abilityThree">
        ${abilityThreeDesc} | ${abilityThreeEnergy}<br>
    </form>
    <font color="red" size="+1">${notEnoughEnergyError}</font><br>
</div>

    <div class="opponentFighter">
        <font size="+3" color="#b22222">${opponentCurrentAbility}</font><br>
        <font size="+3" color="#daa520">${opponentCritical_hit}</font><br>

        <font size="+2">${opponentName} [${opponentFighter}]</font> Life left: ${opponentLife} <br>
        Health: ${opponentCurrentHealth} / ${opponentBaseHealth} <br>
        Energy: ${opponentCurrentEnergy} <br>
        Damage: ${opponentDamage} <br>
        Armour: ${opponentArmour} <br>
        Critical chance: ${opponentCriticalChance} <br>
        Dodge chance: ${opponentDodgeChance} <br> <br>
    </div>
</body>
</html>

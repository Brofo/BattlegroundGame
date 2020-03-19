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
    <font size="+2">${playerName} [${playerFighter}]</font> Life left: ${playerLife} <br>
    Health: ${playerCurrentHealth} / ${playerMaxHealth} <br>
    Energy: ${playerEnergy} <br>
    Damage: ${playerDamage} <br>
    Armour: ${playerArmour} <br>
    Critical chance: ${playerCritical} <br>
    Dodge chance: ${playerDodge} <br> <br>

    <form action="servlets.game.UseAbilityServlet" method="get">
        <font size="+3">Your turn. Use ability: </font><br>
        <input type="submit" value="${basicAttack}" name="basicAttack">
        ${basicAttackDesc} | ${basicAttackEnergy}<br>

        <input type="submit" value="${abilityOne}" name="abilityOne">
        ${abilityOneDesc} | ${abilityOneEnergy}<br>

        <input type="submit" value="${abilityTwo}" name="abilityTwo">
        ${abilityTwoDesc} | ${abilityTwoEnergy}<br>

        <input type="submit" value="${abilityThree}" name="abilityThree">
        ${abilityThreeDesc} | ${abilityThreeEnergy}
    </form>
    <font color="red" size="+1">${notEnoughEnergyError}</font><br>

    <font size="+2">${opponentName} [${opponentFighter}]</font> Life left: ${opponentLife} <br>
    Health: ${opponentCurrentHealth} / ${opponentMaxHealth} <br>
    Energy: ${opponentEnergy} <br>
    Damage: ${opponentDamage} <br>
    Armour: ${opponentArmour} <br>
    Critical chance: ${opponentCritical} <br>
    Dodge chance: ${opponentDodge} <br> <br>
</body>
</html>

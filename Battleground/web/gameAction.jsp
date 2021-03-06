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


    <div class="fpname"> <font color="#b22222">${playerName} [${playerFighter}]</font>
    Life left: <font size="+4" color="green">${playerLife} </font>
    Gold: <font size="+4" color="#daa520">${gold}</font>
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
</div>
    <div class="atc1">
        <form action="servlets.game.UseAbilityServlet" method="get">
            <input type="submit" value="${basicAttack}" name="basicAttack">
        </form>
    </div>

    <div class="atc1stat">
            ${basicAttackDesc} | ${basicAttackEnergy}
    </div>

    <div class="atc2">
        <form action="servlets.game.UseAbilityServlet" method="get">
          <input type="submit" value="${abilityOne}" name="abilityOne">
        </form>
    </div>
    <div class="atc2stat">
          ${abilityOneDesc} | ${abilityOneEnergy}
    </div>

        <div class="atc3">
            <form action="servlets.game.UseAbilityServlet" method="get">
          <input type="submit" value="${abilityTwo}" name="abilityTwo">
            </form>
        </div>
    <div class="atc3stat">
          ${abilityTwoDesc} | ${abilityTwoEnergy}
        </div>

    <div class="atc4">
                <form action="servlets.game.UseAbilityServlet" method="get">
          <input type="submit" value="${abilityThree}" name="abilityThree">
                </form>
    </div>
    <div class="atc4stat">
          ${abilityThreeDesc} | ${abilityThreeEnergy}
      </div>

    <div class="noenergy">
        <font size="+1" color="red">${notEnoughEnergyError}</font>
</div>
    <div class="oability">
        <font size="+3" color="#b22222"><b>${opponentCurrentAbility}</b></font><br>
        <font size="+2" color="#daa520">${opponentCritical_hit}</font><br>
        <font size="+2" color="#1e90ff">${playerDodged}</font><br>
</div>
    <div class="foname">
        <font color="#b22222">${opponentName} [${opponentFighter}]</font>
        Life left: <font size="+4" color="green">${opponentLife}</font> <br>
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

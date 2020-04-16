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
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
    You received an item!<br>
    <font size="+5" color="#b22222">${itemName}</font><br>
    This item gives <font size="+2" color="#00008b">+${itemAmount} ${itemAttribute}</font><br>
    Store value: <font size="+2" color="#daa520">${itemStorePrice}</font> gold<br>

    <form action="servlets.items.ClaimMysteryChestServlet" method="get">
        <input type="submit" value="Claim Item"><br>
        <input type="hidden" value="${itemName}" name="acquiredItem">
    </form>

    <form action="servlets.items.ClaimMysteryChestServlet" method="get">
        <input type="submit" value="Sell for ${itemSellPrice} gold.">
        <input type="hidden" value="${itemSellPrice}" name="acquiredGold">
    </form>

</body>
</html>

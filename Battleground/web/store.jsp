<%--
  Created by IntelliJ IDEA.
  User: Sindre
  Date: 06.04.2020
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to the store</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="bs">
    <img src="res/scrollSide.png"/>
<div class="storegrid">
    <div class="title">
    <h1>Welcome to the store!</h1>
</div>
    <div class="gold">
    You have <font size="+2" color="#daa520">${gold}</font> gold!
    <font size="+2" color="green">${itemPurchased}</font>
    <font size="+2" color="red">${notEnoughGold}</font>
    <form action="servlets.game.NextFightServlet" method="get">
        <input type="submit" value="Continue to next fight">
    </form>
    </div>

    <div class="item1">
    <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName1}</font><br>
        <font size="+1" color="#00008b">+${itemAmount1} ${itemAttribute1}</font><br>
        <font size="+1" color="#daa520">${itemPrice1}</font> Gold
        <button type="submit" value="${itemName1}" name="boughtItem">Buy Item</button><br><br>
    </form>
</div>

    <div class="item2">
        <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName2}</font><br>
        <font size="+1" color="#00008b">+${itemAmount2} ${itemAttribute2}</font><br>
        <font size="+1" color="#daa520">${itemPrice2}</font> Gold
        <button type="submit" value="${itemName2}" name="boughtItem">Buy Item</button><br><br>
        </form>
    </div>

        <div class="item3">
         <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName3}</font><br>
        <font size="+1" color="#00008b">+${itemAmount3} ${itemAttribute3}</font><br>
        <font size="+1" color="#daa520">${itemPrice3}</font> Gold
        <button type="submit" value="${itemName3}" name="boughtItem">Buy Item</button><br><br>
         </form>
        </div>

            <div class="item4">
         <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName4}</font><br>
        <font size="+1" color="#00008b">+${itemAmount4} ${itemAttribute4}</font><br>
        <font size="+1" color="#daa520">${itemPrice4}</font> Gold
        <button type="submit" value="${itemName4}" name="boughtItem">Buy Item</button><br><br>
         </form>
            </div>

                <div class="item5">
         <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName5}</font><br>
        <font size="+1" color="#00008b">+${itemAmount5} ${itemAttribute5}</font><br>
        <font size="+1" color="#daa520">${itemPrice5}</font> Gold
        <button type="submit" value="${itemName5}" name="boughtItem">Buy Item</button><br><br>
         </form>
                </div>

                    <div class="item6">
         <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName6}</font><br>
        <font size="+1" color="#00008b">+${itemAmount6} ${itemAttribute6}</font><br>
        <font size="+1" color="#daa520">${itemPrice6}</font> Gold
        <button type="submit" value="${itemName6}" name="boughtItem">Buy Item</button><br><br>
         </form>
                    </div>

                        <div class="item7">
        <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName7}</font><br>
        <font size="+1" color="#00008b">+${itemAmount7} ${itemAttribute7}</font><br>
        <font size="+1" color="#daa520">${itemPrice7}</font> Gold
        <button type="submit" value="${itemName7}" name="boughtItem">Buy Item</button><br><br>
        </form>
                        </div>

                            <div class="item8">
        <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName8}</font><br>
        <font size="+1" color="#00008b">+${itemAmount8} ${itemAttribute8}</font><br>
        <font size="+1" color="#daa520">${itemPrice8}</font> Gold
        <button type="submit" value="${itemName8}" name="boughtItem">Buy Item</button><br><br>
        </form>
                            </div>

                                <div class="item9">
        <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName9}</font><br>
        <font size="+1" color="#00008b">+${itemAmount9} ${itemAttribute9}</font><br>
        <font size="+1" color="#daa520">${itemPrice9}</font> Gold
        <button type="submit" value="${itemName9}" name="boughtItem">Buy Item</button><br><br>
        </form>
                                </div>

                                    <div class="item10">
        <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName10}</font><br>
        <font size="+1" color="#00008b">+${itemAmount10} ${itemAttribute10}</font><br>
        <font size="+1" color="#daa520">${itemPrice10}</font> Gold
        <button type="submit" value="${itemName10}" name="boughtItem">Buy Item</button><br><br>
        </form>
                                    </div>

                                        <div class="item11">
       <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName11}</font><br>
        <font size="+1" color="#00008b">+${itemAmount11} ${itemAttribute11}</font><br>
        <font size="+1" color="#daa520">${itemPrice11}</font> Gold
        <button type="submit" value="${itemName11}" name="boughtItem">Buy Item</button><br><br>
       </form>
                                        </div>

                         <div class="item12">
        <form action="servlets.items.ButItemServlet" method="get">
        <font size="+3" color="#b22222">${itemName12}</font><br>
        <font size="+1" color="#00008b">+${itemAmount12} ${itemAttribute12}</font><br>
        <font size="+1" color="#daa520">${itemPrice12}</font> Gold
        <button type="submit" value="${itemName12}" name="boughtItem">Buy Item</button><br><br>
    </form>
</div>

</div>
</body>
</html>

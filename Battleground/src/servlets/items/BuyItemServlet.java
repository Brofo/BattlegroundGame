package servlets.items;

import classes.browser.AddRequestParameters;
import classes.browser.CookieFunctionality;
import classes.itemModule.Item;
import classes.itemModule.ItemCollection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "servlets.items.BuyItemServlet",
            urlPatterns = {"/servlets.items.ButItemServlet"}
)
public class BuyItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ItemCollection itemCol = new ItemCollection();
        CookieFunctionality cf = new CookieFunctionality();
        AddRequestParameters addParam = new AddRequestParameters(request, out);

        String itemName = request.getParameter("boughtItem");
        String playerID = cf.getValue(request,"playerID");
        Item item = itemCol.getItem(itemName);

        try {
            if(item.buyAndUseItem(playerID, out)) {
                request.setAttribute("itemPurchased", "You purchased: " + item.getName() + "!");
            }
            else {
                request.setAttribute("notEnoughGold", "You do not have enough gold to purchase this item.");
            }
            addParam.addGoldParameters(playerID);
            addParam.addStoreParameters();
            request.getRequestDispatcher("store.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

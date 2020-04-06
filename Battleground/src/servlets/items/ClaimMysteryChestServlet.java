package servlets.items;

import classes.browser.AddRequestParameters;
import classes.browser.CookieFunctionality;
import classes.itemModule.Gold;
import classes.itemModule.Item;
import classes.itemModule.ItemCollection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "servlets.items.ClaimMysteryChestServlet",
            urlPatterns = {"/servlets.items.ClaimMysteryChestServlet"}
)

public class ClaimMysteryChestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gold gold = new Gold();
        CookieFunctionality cf = new CookieFunctionality();
        ItemCollection itemCol = new ItemCollection();
        AddRequestParameters addParam = new AddRequestParameters(request, out);

        String playerID = cf.getValue(request, "playerID");
        String acquiredGold = request.getParameter("acquiredGold");
        String acquiredItem = request.getParameter("acquiredItem");


            try {
                if(acquiredGold != null) {
                    gold.addGoldToDB(acquiredGold, playerID, out);
                }
                if(acquiredItem != null) {
                    Item item = itemCol.getItem(acquiredItem);
                    item.useItem(playerID, out);
                }
                addParam.addGoldParameters(playerID);
                addParam.addStoreParameters();
                request.getRequestDispatcher("store.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}

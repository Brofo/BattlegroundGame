package servlets.items;

import classes.browser.AddRequestParameters;
import classes.itemModule.Item;
import classes.itemModule.MysteryChest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(name = "servlets.items.OpenMysteryChestServlet",
            urlPatterns = {"/servlets.items.OpenMysteryChestServlet"}
)

/**
 * This servlet is called when a player opens the mystery chest.
 */
public class OpenMysteryChestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        MysteryChest chest = new MysteryChest();
        Random rng = new Random();
        AddRequestParameters addParam = new AddRequestParameters(request, out);

        int goldOrItem = rng.nextInt(2);
        if(goldOrItem == 0) {
            String gold = chest.getMysteryGold();
            request.setAttribute("mysteryGold", gold);
            request.getRequestDispatcher("mysteryChestGold.jsp").forward(request, response);
        }
        else {
            Item item = chest.getMysteryItem();
            addParam.addMysteryItemParameters(item);
            request.getRequestDispatcher("mysteryChestItem.jsp").forward(request, response);
        }
    }
}

package servlets.menu;

import classes.browser.CookieFunctionality;
import classes.database.DbLib;
import classes.database.PlayerInteractions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "servlets.menu.CreateGameServlet",
        urlPatterns = {"/servlets.menu.CreateGameServlet"}
)

/**
 * This servlet is activated when the player wants to create a new game.
 */
public class CreateGameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DbLib db = new DbLib(out);

        try {
            String gameID = db.createNewGameInDB(); //Creates new game ID, stores it in DB and returns it.

            request.setAttribute("gameID", gameID);
            request.getRequestDispatcher("createdGame.jsp").forward(request, response);
        }
        catch (SQLException e) {
            out.println("Error in CreateGameServlet  " + e);
        }
    }
}
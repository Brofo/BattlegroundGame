package servlets.menu;

import classes.browser.AddRequestParameters;
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

@WebServlet(name = "servlets.menu.WaitForPlayerServlet",
        urlPatterns = {"/servlets.menu.WaitForPlayerServlet"}
)

/**
 * This servlet is called upon when the player has clicked "join game" and is
 * waiting for another opponent to join the same game.
 */
public class WaitForPlayerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DbLib db = new DbLib(out);
        CookieFunctionality cf = new CookieFunctionality();
        PlayerInteractions pi = new PlayerInteractions(out);
        AddRequestParameters addParam = new AddRequestParameters(request, out);

        String playerID = cf.getValue(request, "playerID");
        String playerFighter = cf.getValue(request, "playerFighter");

        try {
            //Put the gameID into the database for the player, and also in a new Cookie.
            String gameID = request.getParameter("gameID");
            db.updateTable("player", "gameID", gameID, "playerID", playerID);

            //Register the fighter's values in the database:
            db.registerFighterValuesInDB(playerID, gameID, playerFighter);

            Cookie gameCookie = new Cookie("gameID", gameID);
            gameCookie.setMaxAge(-1); //The cookie wil be deleted when the player closes the browser.
            response.addCookie(gameCookie);

            boolean ready;
            ready = pi.waitForPlayerToJoin(playerID);
            if (ready) {
                String opponentName = pi.getOpponentAttribute("playerName", playerID, gameID);
                String opponentFighter = pi.getOpponentAttribute("fighterName", playerID, gameID);


                Cookie opponentNameCookie = new Cookie("opponentName", opponentName);
                opponentNameCookie.setMaxAge(-1); //Cookie is deleted when browser is closed.
                Cookie opponentFighterCookie = new Cookie("opponentFighter", opponentFighter);
                opponentFighterCookie.setMaxAge(-1);
                response.addCookie(opponentNameCookie);
                response.addCookie(opponentFighterCookie);

                addParam.addCookieNameParameters();
                request.setAttribute("opponentName", opponentName);
                request.setAttribute("opponentFighter", opponentFighter);
                addParam.addFighterParameters(playerID, gameID);
                request.getRequestDispatcher("gameReady.jsp").forward(request, response);
            }
            else {
                out.println("No player found.");
            }
        }
        catch (SQLException e) {
            out.println(e);
        }
    }
}

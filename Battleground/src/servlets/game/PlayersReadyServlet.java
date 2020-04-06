package servlets.game;

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

@WebServlet(name = "servlets.game.PlayersReadyServlet",
        urlPatterns = {"/servlets.game.PlayersReadyServlet"}
)

/**
 * This servlet is called upon when two players are connected with the same
 * gameID, and have both clicked "Ready". The servlet will make sure a random
 * player starts choosing an ability.
 */
public class PlayersReadyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        CookieFunctionality cf = new CookieFunctionality();
        PlayerInteractions pi = new PlayerInteractions(out);
        AddRequestParameters addParam = new AddRequestParameters(request, out);
        DbLib db = new DbLib(out);

        String playerID = cf.getValue(request, "playerID");
        String gameID = cf.getValue(request, "gameID");
        String playerFighterName = cf.getValue(request, "playerFighter");


        try {

            int ready = pi.selectRandomPlayerToStart(playerID, gameID);
            addParam.addFighterParameters(playerID, gameID);
            addParam.addGoldParameters(playerID);
            addParam.addCookieNameParameters();

            if(ready == 0) {
                response.addCookie(new Cookie("playerStart", "false"));
                request.getRequestDispatcher("gameWait.jsp").forward(request, response);
            }
            if (ready == 1) {
                response.addCookie(new Cookie("playerStart", "true"));
                //Put turns back to 0. This will be incremented when the player uses an ability.
                db.updateTable("player", "turns", "0", "playerID", playerID);
                addParam.addAbilityParameters(playerFighterName, playerID, gameID);
                request.getRequestDispatcher("gameAction.jsp").forward(request, response);
            }
            if (ready < 0) {
                out.println("Could not process ready players. Ready value: " + ready);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package servlets.game;

import classes.browser.AddRequestParameters;
import classes.browser.CookieFunctionality;
import classes.database.DbLib;
import classes.database.PlayerInteractions;
import classes.fighterModule.SelectFighter;
import classes.fighterModule.fighters.Fighter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "servlets.game.NextFightServlet",
        urlPatterns = {"/servlets.game.NextFightServlet"}
)

/**
 * This servlet is used to start a new game, AFTER the first game has been played.
 * This requires another servlet than the "PlayersReadyServlet", because we do not
 * need to assign a random starting value, because the players should start every
 * second fight.
 */
public class NextFightServlet extends HttpServlet {

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
            //Restore values in the player's fighter for the next fight.
            Fighter playerFighter = new SelectFighter(out).getFighter(playerFighterName, playerID, gameID);
            playerFighter.restoreHealthAndEnergy();

            //Set starting priority for the next fight:
            int startPriority = pi.setNextFightStartPriority(response, request, playerID, gameID);

            addParam.addFighterParameters(playerID, gameID);
            addParam.addCookieNameParameters();

            if(startPriority == 0) {
                request.getRequestDispatcher("gameWait.jsp").forward(request, response);
            }

            if(startPriority == 1) {
                addParam.addAbilityParameters(playerFighterName, playerID, gameID);

                //Put turns back to 0. This will be incremented when the player uses an ability.
                db.updateTable("player", "turns", "0", "playerID", playerID);

                request.getRequestDispatcher("gameAction.jsp").forward(request, response);
            }

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}

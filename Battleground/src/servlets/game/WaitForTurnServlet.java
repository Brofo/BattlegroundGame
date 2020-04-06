package servlets.game;

import classes.browser.AddRequestParameters;
import classes.browser.CookieFunctionality;
import classes.database.DbLib;
import classes.database.PlayerInteractions;
import classes.fighterModule.SelectFighter;
import classes.fighterModule.fighters.Fighter;
import classes.itemModule.Gold;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "servlets.game.WaitForTurnServlet",
        urlPatterns = {"/servlets.game.WaitForTurnServlet"}
)

/**
 * This servlet is called upon when a player is waiting for the opponent
 * to use an ability.
 */
public class WaitForTurnServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        PlayerInteractions pi = new PlayerInteractions(out);
        CookieFunctionality cf = new CookieFunctionality();
        AddRequestParameters addParam = new AddRequestParameters(request, out);
        Gold gold = new Gold();

        String playerID = cf.getValue(request, "playerID");
        String gameID = cf.getValue(request, "gameID");
        String playerFighterName = cf.getValue(request, "playerFighter");

        try {
            addParam.addGoldParameters(playerID);
            boolean opponentFinished = pi.waitForOpponentAbility(playerID, gameID, request);
            if(opponentFinished) {
                if(pi.checkIfFightIsLost(playerID)) {
                    if(pi.checkIfGameIsLost(playerID)) {
                        addParam.addFightOverParameters(playerID, gameID, false);
                        request.getRequestDispatcher("gameOver.jsp").forward(request, response);
                    }
                    gold.addGoldToDB("5", playerID, out);
                    addParam.addGoldParameters(playerID);
                    addParam.addFightOverParameters(playerID, gameID, false);
                    request.getRequestDispatcher("fightOver.jsp").forward(request, response);
                }
                else {
                    addParam.addCookieNameParameters();
                    addParam.addAbilityResultParameters(playerID, gameID, false);
                    addParam.addAbilityParameters(playerFighterName, playerID, gameID);
                    addParam.addFighterParameters(playerID, gameID);
                    request.getRequestDispatcher("gameAction.jsp").forward(request, response);
                }
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

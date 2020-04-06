package servlets.game;

import classes.browser.AbilityHandler;
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
import java.net.CookieHandler;
import java.sql.SQLException;

@WebServlet(name = "servlets.game.UseAbilityServlet",
        urlPatterns = {"/servlets.game.UseAbilityServlet"}
)

/**
 * This servlet is called upon when a player has chosen an ability.
 */
public class UseAbilityServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        AbilityHandler ah = new AbilityHandler();
        CookieFunctionality cf = new CookieFunctionality();
        AddRequestParameters addParam = new AddRequestParameters(request, out);
        PlayerInteractions pi = new PlayerInteractions(out);
        Gold gold = new Gold();

        String playerID = cf.getValue(request, "playerID");
        String gameID = cf.getValue(request, "gameID");
        String playerFighterName = cf.getValue(request, "playerFighter");

        Fighter playerFighter = new SelectFighter(out).getFighter(playerFighterName, playerID, gameID);
        String abilityStdName = ah.getStandardAbilitySelected(request);

        try {
            addParam.addGoldParameters(playerID);
            //Ability is used if the player has enough energy.
            boolean enoughEnergy = playerFighter.useAbility(abilityStdName);
            if (enoughEnergy) {
                if(pi.checkIfFightIsWon(playerID, gameID)) {
                    if(pi.checkIfGameIsWon(playerID, gameID)) {
                        addParam.addFightOverParameters(playerID, gameID, true);
                        request.getRequestDispatcher("gameOver.jsp").forward(request, response);
                    }
                    else {
                        gold.addGoldToDB("5", playerID, out);
                        addParam.addFightOverParameters(playerID, gameID, true);
                        addParam.addGoldParameters(playerID);
                        request.getRequestDispatcher("fightOver.jsp").forward(request, response);
                    }
                }
                else {
                    ah.updateCurrentAbility(request, out, playerID);

                    addParam.addAbilityResultParameters(playerID, gameID, true);
                    addParam.addFighterParameters(playerID, gameID);
                    addParam.addCookieNameParameters();
                    request.getRequestDispatcher("gameWait.jsp").forward(request, response);
                }
            }
            else {
                request.setAttribute("notEnoughEnergyError", "You do not have enough energy to use this ability.");
                addParam.addFighterParameters(playerID, gameID);
                addParam.addCookieNameParameters();
                addParam.addAbilityParameters(playerFighterName, playerID, gameID);
                request.getRequestDispatcher("gameAction.jsp").forward(request, response);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}

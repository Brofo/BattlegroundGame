package servlets.game;

import classes.browser.AbilityHandler;
import classes.browser.AddRequestParameters;
import classes.browser.CookieFunctionality;
import classes.database.DbLib;
import classes.fighterModule.SelectFighter;
import classes.fighterModule.fighters.Fighter;

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
        DbLib db = new DbLib(out);

        String playerID = cf.getValue(request, "playerID");
        String gameID = cf.getValue(request, "gameID");
        String playerFighterName = cf.getValue(request, "playerFighter");

        Fighter playerFighter = new SelectFighter(out).getFighter(playerFighterName, playerID, gameID);
        String ability = ah.getAbilitySelected(request);

        try {
            boolean enoughEnergy = playerFighter.useAbility(ability);
            if (enoughEnergy) {

                //Player's turn is finished. Increment turns.
                int turns = Integer.parseInt(db.getField("turns", "player", "playerID", playerID));
                db.updateTable("player", "turns", Integer.toString(turns+1), "playerID", playerID);

                addParam.addFighterParameters(playerID, gameID);
                addParam.addCookieNameParameters();
                request.getRequestDispatcher("gameWait.jsp").forward(request, response);
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
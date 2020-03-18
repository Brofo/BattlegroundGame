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

@WebServlet(name = "servlets.game.WaitForTurnServlet",
        urlPatterns = {"/servlets.game.WaitForTurnServlet"}
)

public class WaitForTurnServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        PlayerInteractions pi = new PlayerInteractions(out);
        CookieFunctionality cf = new CookieFunctionality();
        DbLib db = new DbLib(out);
        AddRequestParameters addParam = new AddRequestParameters(request, out);

        String playerID = cf.getValue(request, "playerID");
        String gameID = cf.getValue(request, "gameID");
        String playerFighter = cf.getValue(request, "playerFighter");

        try {
            boolean opponentFinished = pi.waitForOpponentAbility(playerID, gameID, request);
            if(opponentFinished) {

                addParam.addCookieNameParameters();
                addParam.addAbilityParameters(playerFighter, playerID, gameID);
                addParam.addFighterParameters(playerID, gameID);

                request.getRequestDispatcher("gameAction.jsp").forward(request, response);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

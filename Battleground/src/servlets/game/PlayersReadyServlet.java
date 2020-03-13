package servlets.game;

import classes.browser.AddRequestParameters;
import classes.browser.CookieFunctionality;
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
public class PlayersReadyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        CookieFunctionality cf = new CookieFunctionality();
        PlayerInteractions pi = new PlayerInteractions(out);

        Cookie existingCookies[] = request.getCookies();
        String playerID = cf.getValue(existingCookies, "playerID");
        String gameID = cf.getValue(existingCookies, "gameID");

        int ready = pi.selectRandomPlayerToStart(playerID, gameID);
            try {
                new AddRequestParameters().addFighterParameters(request, out, playerID, gameID);
                if(ready == 0) {
                    request.getRequestDispatcher("gameWait.jsp").forward(request, response);
                }
                if (ready == 1) {
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

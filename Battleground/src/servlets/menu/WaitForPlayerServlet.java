package servlets.menu;

import classes.browser.CookieFunctionality;
import classes.database.DbLib;
import classes.database.PlayerInteractions;
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
import java.sql.SQLException;

@WebServlet(name = "servlets.menu.WaitForPlayerServlet",
        urlPatterns = {"/servlets.menu.WaitForPlayerServlet"}
)

public class WaitForPlayerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DbLib db = new DbLib(out);
        CookieFunctionality cf = new CookieFunctionality();
        PlayerInteractions pi = new PlayerInteractions(out);

        Cookie existingCookies[] = request.getCookies();
        String playerID = cf.getValue(existingCookies, "playerID");

        try {
            //Put the gameID into the database for the player, and also in a new Cookie.
            String gameID = request.getParameter("gameID");
            db.updateTable("player", "gameID", gameID, "playerID", playerID);
            Cookie gameCookie = new Cookie("gameID", gameID);
            gameCookie.setMaxAge(-1); //The cookie wil be deleted when the player closes the browser.
            response.addCookie(gameCookie);

            boolean ready;
            ready = pi.waitForPlayerToJoin(playerID);
            if (ready) {
                request.setAttribute("opponentName", pi.getOpponentAttribute("playerName", playerID, gameID));
                request.setAttribute("opponentFighterName", pi.getOpponentAttribute("fighterName", playerID, gameID));
                request.setAttribute("playerName", db.getField("playerName", "player", "playerID", playerID));
                request.setAttribute("playerFighterName", db.getField("fighterName", "player", "playerID", playerID));
                request.getRequestDispatcher("gameReady.jsp").forward(request, response);
            }
            else {
                out.println("No player found.");
            }
        }
        catch (SQLException | InterruptedException e) {
            out.println(e);
        }
    }
}

package servlets.menu;

import classes.database.DbLib;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "servlets.menu.StartPlayServlet",
        urlPatterns = {"/servlets.menu.StartPlayServlet"}
)

/**
 * This servlet is called upon when the player has chosen a name and a fighter, and clicks
 * the play-button on the index.jsp page. It will register this information in the database,
 * along with an ID that will be stored in the cookie that is created.
 */
public class StartPlayServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DbLib db = new DbLib(out);

        String playerName = request.getParameter("playerName");
        String fighterName = request.getParameter("fighterName");

        try {
            String playerID = db.registerPlayerInDB(playerName, fighterName);

            Cookie playerCookie = new Cookie("playerID", playerID);
            playerCookie.setMaxAge(-1); //The cookie wil be deleted when the player closes the browser.
            Cookie fighterCookie = new Cookie("fighter", fighterName);
            fighterCookie.setMaxAge(-1);

            response.addCookie(playerCookie);
            response.addCookie(fighterCookie);
            request.getRequestDispatcher("startGame.jsp").forward(request, response);
        } catch (SQLException e) {
            out.println("Error in StartPlayServlet:  " + e);
        }
    }
}

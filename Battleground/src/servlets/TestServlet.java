package servlets;

import classes.TestClass;
import classes.database.DbLib;
import classes.database.GenerateID;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "servlets.TestServlet",
        urlPatterns = {"/servlets.TestServlet"}
)

/**
 * Dette er bare en midlertidlig testklasse for å teste ut ting med en servlet,
 * hvis ikke vi gidder å sette opp en ny servlet for å teste.
 */

public class TestServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();

        String gameID = request.getParameter("gameID");
        out.println(gameID);
    }
}

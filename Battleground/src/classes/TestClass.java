package classes;

import classes.database.DbTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.PrintWriter;

/**
 * Klasse for å teste ut enkle funksjoner o.l som kan kobles opp med TestServlet.
 * Bare slett det man tester får man pusher / ikke push denne filen.
 */

public class TestClass {

    private PrintWriter out;
    private Connection con;

    public TestClass(PrintWriter out) {
        this.out = out;
    }




}

package classes.database;
/*
 * Hensikten med klassen er etablere kontakt med databasen
 */

import java.io.PrintWriter;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;


/**
 *

 */
public class DbTool {
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;


    public Connection logIn(PrintWriter out) {
        try {
            // Step 1: Allocate a classes.database 'Connection' object
            Context cont = new InitialContext();
            DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/battlegroundDB");
            conn = ds.getConnection();
            if(conn != null){
                return conn;
            } else{
                conn = ds.getConnection();
            }


        }
        catch (SQLException ex ) {
            out.println("Not connected to classes.database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
        return null;

    }  // end loggInn
}

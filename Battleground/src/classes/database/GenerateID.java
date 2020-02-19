package classes.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Class generates ID for player and game.
 */
public class GenerateID {
    private Random r;
    private Connection con;
    private PrintWriter out;

    public GenerateID() {
        r = new Random();
    }


    /**
     * Generate ID for player. The ID that is returned will always be unique.
     * @return playerID.
     * @throws SQLException
     */
    public String getNewPlayerID() throws SQLException {
        con = new DbTool().logIn(out);

        int id = r.nextInt(1000000);
        String playerID = Integer.toString(id);

        try {
            String stmt = "SELECT playerID FROM battlegroundDB.player";
            PreparedStatement pst = con.prepareStatement(stmt);
            ResultSet result = pst.executeQuery();

            result.beforeFirst();
            while(result.next()) {
                String idInDB = result.getString(1);
                if (idInDB.equals(playerID)) {
                    id = r.nextInt(1000000);
                    playerID = Integer.toString(id);
                    result.beforeFirst();
                }
            }
            return playerID;
        }
        catch (SQLException e){
            System.err.print("Error in GenerateID.getNewPlayerID " + e);
        }
        finally {
            if (con != null){
                con.close();
            }
        }

        return null;
    }


    /**
     * Generates ID for a game. Does exactly the same as getNewPlayerID(), except
     * the gameID returns a lower number. (To make it easier for each player).
     * @return
     * @throws SQLException
     */
    public String getNewGameID() throws SQLException {
        con = new DbTool().logIn(out);

        int id = r.nextInt(10000);
        String gameID = Integer.toString(id);

        try {
            String stmt = "SELECT gameID FROM battlegroundDB.game";
            PreparedStatement pst = con.prepareStatement(stmt);
            ResultSet result = pst.executeQuery();

            result.beforeFirst();
            while(result.next()) {
                String idInDB = result.getString(1);
                if (idInDB.equals(gameID)) {
                    id = r.nextInt(10000);
                    gameID = Integer.toString(id);
                    result.beforeFirst();
                }
            }
            return gameID;
        }
        catch (SQLException e){
            System.err.print("Error in GenerateID.getNewGameID " + e);
        }
        finally {
            if (con != null){
                con.close();
            }
        }

        return null;
    }

}

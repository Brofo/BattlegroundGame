package classes.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * This class will contain database functionality where players will send and receive
 * information to one another.
 */
public class PlayerInteractions {

    private PrintWriter out;
    private Connection con;

    public PlayerInteractions(PrintWriter out) {
        this.out = out;
    }

    /**
     * This method will continously check if another player has joined the same gameID
     * as the player who is waiting for another player. It will check for 300 seconds
     * (5 minutes) until it gives up.
     * @param playerID - ID of the player with the gameID that will be searched for.
     * @return true if a player has joined. false if no one has joined for the 5 minutes.
     * @throws SQLException
     * @throws InterruptedException
     */
    public boolean waitForPlayerToJoin(String playerID) throws SQLException, InterruptedException {
        DbLib db = new DbLib(out);

        try {
            int i = 0;
            while (i <= 150) {
                boolean ready = db.checkReadyPlayers(playerID);
                if (ready) {
                    return true;
                }
                i++;
                TimeUnit.SECONDS.sleep(2); //Wait 2 seconds before looping again.
            }
        }
        catch (SQLException | InterruptedException e) {
            out.println("Error in PlayerInteractions.waitForPlayerToJoin()  - " + e);
        }
        return false;
    }

    /**
     * This method could be changed to "getOpponentAttribute" so it will get
     * any attribute from the opponent, not just the name.
     * @param playerID
     * @param gameID
     * @return
     * @throws SQLException
     */
    public String getOpponentName(String playerID, String gameID) throws SQLException {
        con = new DbTool().logIn(out);
        DbLib db = new DbLib(out);

        try {
            String stmt =   "SELECT playerID FROM battlegroundDB.player WHERE gameID = ?";

            PreparedStatement pst = con.prepareStatement(stmt);
            pst.setString(1, gameID);
            ResultSet searchResultSet = pst.executeQuery();

            int i = 1;
            while(searchResultSet.next()){
                String opponentID = searchResultSet.getString(i);
                if(!opponentID.equals(playerID)) {
                    System.out.println(opponentID);
                    return db.getField("playerName", "player", "playerID", opponentID);
                }
                i++;
            }
        }
        catch (SQLException e){
            out.println("Exeption in getOpponentName: " + e);
        }finally {
            if (con != null){
                con.close();
            }
        }
        return null;
    }
}

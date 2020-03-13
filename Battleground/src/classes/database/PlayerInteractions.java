package classes.database;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This class will contain database functionality where players will send and receive
 * information to one another.
 */
public class PlayerInteractions {

    private PrintWriter out;
    private DbLib db;

    public PlayerInteractions(PrintWriter out) {
        this.out = out;
        db = new DbLib(out);
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

    public int selectRandomPlayerToStart(String playerID, String gameID) {
        try {
            int opponentsTurn = Integer.parseInt(getOpponentAttribute("myTurn", playerID, gameID));

            if (opponentsTurn < 0) {
                //Opponent has not yet clicked ready. Assign random start value to player.
                //1 = true, this player start. 0 = false, this player does not start.
                Random ran = new Random();
                int turn = ran.nextInt(2);
                db.updateTable("player", "myTurn", Integer.toString(turn), "playerID", playerID);
                int i = 0;
                while (i <= 60) {
                    //Wait for other player to get ready.
                    opponentsTurn = Integer.parseInt(getOpponentAttribute("myTurn", playerID, gameID));
                    if (opponentsTurn >= 0) {
                        return turn;
                    }
                    i++;
                    TimeUnit.SECONDS.sleep(2); //Wait 2 seconds before looping again.
                }
            }
            else {
                //Opponent has clicked ready, and has been assigned a start value.
                if(opponentsTurn == 0) {
                    db.updateTable("player", "myTurn", "1", "playerID", playerID);
                    return 1;
                }
                else {
                    db.updateTable("player", "myTurn", "0", "playerID", playerID);
                    return 0;
                }
            }
        }
        catch(SQLException | InterruptedException e) {
            System.out.println("Exception in classes.database - PlayerInteractions()  " + e);
        }
        System.out.println("Problem in PlayerInteractions selectRandomPlayerToStart()");
        return -1;
    }

    /**
     * Get a specific attribute from the opponent.
     * @param attribute - The attribute to retrieve.
     * @param playerID - The player's ID. (NOT the opponent's ID).
     * @param gameID - The game ID.
     * @return The value of the requested attribute.
     * @throws SQLException
     */
    public String getOpponentAttribute(String attribute, String playerID, String gameID) throws SQLException {
        String opponentID = db.getOpponentID(playerID, gameID);
        return db.getField(attribute, "player", "playerID", opponentID);
    }

    /**
     *  Update a specific attribute for the opponent.
     * @param attribute - The attribute to update.
     * @param attributeValue - The value to be inserted into the attribute.
     * @param playerID - The ID of the player (NOT the opponent).
     * @param gameID - The ID of the game.
     * @throws SQLException
     */
    public void updateOpponentAttribute(String attribute, String attributeValue, String playerID, String gameID) throws SQLException {
        String opponentID = db.getOpponentID(playerID, gameID);
        db.updateTable("player", attribute, attributeValue, "playerID", opponentID);
    }
}

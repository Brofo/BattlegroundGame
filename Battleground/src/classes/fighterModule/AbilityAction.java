package classes.fighterModule;

import classes.database.DbLib;

import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * This class is used to access the player's and the opponents values from the Database.
 * The methods will either just retrieve values, or change them. The idea here is
 * to call upon methods from this class when a player uses an ability.
 */
public class AbilityAction {
    private DbLib db;

    public AbilityAction(PrintWriter out) {
        db = new DbLib(out);
    }

    /**
     * Changes a value in the database for the specified player's opponent.
     */
    public String changeOpponentValue(String playerID, String gameID, String valueToChange, String newValue) throws SQLException {
        try {
            String opponentID = db.getOpponentID(playerID, gameID);
            db.updateTable("player", valueToChange, newValue, "playerID", opponentID);

            //Return the updated value, in case we want to use it.
            return getOpponentValue(valueToChange, playerID, gameID);
        }
        catch (SQLException e) {
            System.out.println("Exception in classes.figherModule.AbilityAction - changeOpponentValue()  " + e);
        }
        return null;
    }

    /**
     * Change the value in the database for the specified player.
     */
    public String changeOwnValue(String playerID, String valueToChange, String newValue) throws SQLException {
        try {
            db.updateTable("player", valueToChange, newValue, "playerID", playerID);

            //Return the update value, in case we want to use it.
            return getPlayerValue(valueToChange, playerID);
        }
        catch(SQLException e) {
            System.out.println("Error in classes.fighterModule.AbilityAction - changeOwnValue()  " + e);
        }
        return null;
    }

    /**
     * Get the value of the specified player's opponent.
     */
    public String getOpponentValue(String valueToGet, String playerID, String gameID) throws SQLException {
        try {
            String opponentID = db.getOpponentID(playerID, gameID);
            return db.getField(valueToGet, "player", "playerID", opponentID);
        }
        catch(SQLException e) {
            System.out.println("Error in classes.fighterModule.AbilityAction - getOpponentValue()  " + e);
        }
        return null;
    }

    /**
     * Get the value of the specified player.
     */
    public String getPlayerValue(String valueToGet, String playerID) throws SQLException {
        try {
            return db.getField(valueToGet, "player", "playerID", playerID);
        }
        catch (SQLException e) {
            System.out.println("Error in classes.fighterModule.AbilityAction - getPlayerValue()  " + e);
        }
        return null;
    }
}

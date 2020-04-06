package classes.database;

import classes.browser.CookieFunctionality;
import classes.fighterModule.AbilityAction;
import classes.fighterModule.SelectFighter;
import classes.fighterModule.fighters.Fighter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * This method will continuously check if another player has joined the same gameID
     * as the player who is waiting for another player. It will check for 300 seconds
     * (5 minutes) until it gives up.
     * @param playerID - ID of the player with the gameID that will be searched for.
     * @return true if a player has joined. false if no one has joined for the 5 minutes.
     */
    public boolean waitForPlayerToJoin(String playerID) {
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
     * When the two players are connected, they will click "Ready" to begin the game.
     * One player is randomly selected to start. This method will check if the other player
     * has clicked ready, and either generate a random number, or give the player the opposite
     * number of the opponent, based on whether the opponent has clicked ready yet or not.
     * @return  0: The player does not start.
     *          1: The player does start.
     *          -1: The opponent never clicked ready.
     */
    public int selectRandomPlayerToStart(String playerID, String gameID) {
        try {
            int opponentsTurn = Integer.parseInt(getOpponentAttribute("turns", playerID, gameID));

            if (opponentsTurn < 0) {
                //Opponent has not yet clicked ready. Assign random start value to player.
                //1 = this player start. 0 = this player does not start.
                Random ran = new Random();
                int turn = ran.nextInt(2);
                db.updateTable("player", "turns", Integer.toString(turn), "playerID", playerID);
                int i = 0;
                while (i <= 60) {
                    //Wait for other player to get ready.
                    opponentsTurn = Integer.parseInt(getOpponentAttribute("turns", playerID, gameID));
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
                    db.updateTable("player", "turns", "1", "playerID", playerID);
                    return 1;
                }
                else {
                    db.updateTable("player", "turns", "0", "playerID", playerID);
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
     * When it is not the player's turn to use an ability, they need to wait for the other
     * player. This method will check whether the opponent has used an ability, based on how
     * many turns the players have completed, and based on which player had the first turn.
     * @return - True if the opponent finished their turn.
     *           False if time has run out and the opponent didnt do anything.
     */
    public boolean waitForOpponentAbility(String playerID, String gameID, HttpServletRequest request) throws SQLException, InterruptedException {
        CookieFunctionality cf = new CookieFunctionality();
        int i = 0;
        while (i <= 150) {
            int opponentTurn = Integer.parseInt(getOpponentAttribute("turns", playerID,  gameID));
            int playerTurn = Integer.parseInt(db.getField("turns", "player", "playerID", playerID));
            boolean playerStart = Boolean.parseBoolean(cf.getValue(request, "playerStart"));

            if (playerStart) {
                if (playerTurn == opponentTurn) {
                    //The opponent has selected an ability.
                    return true;
                }
            }
            else {
                if ((playerTurn + 1) == opponentTurn) {
                    //The opponent has selected an ability
                    return true;
                }
            }
            if (opponentTurn < 0) {
                //The player has won the fight.
                return true;
            }
            i++;
            TimeUnit.SECONDS.sleep(2); //Wait 2 seconds before looping again.
        }
        return false; //Opponent never chose an ability.
    }

    /**
     * Check if the player's current health is 0 or less. If it is, the figher is dead,
     * and the fight is lost.
     */
    public boolean checkIfFightIsLost(String playerID) throws SQLException {
        int playerHealth = Integer.parseInt(db.getField("currentHealth", "player", "playerID", playerID));
        if (playerHealth <= 0) {
            //Set turns to -1 for later.
            db.updateTable("player", "turns", "-1", "playerID", playerID);
            return true;
        }
        return false;
    }

    public boolean checkIfGameIsLost(String playerID) throws SQLException {
        int playerLife = Integer.parseInt(db.getField("life", "player", "playerID", playerID));
        return playerLife <= 0;
    }
    /**
     * Check if the opponent's current health is 0 or less. If it is, the fighter is dead,
     * and the fight is won. Remove one life from the opponent's database.
     */
    public boolean checkIfFightIsWon(String playerID, String gameID) throws SQLException {
        AbilityAction action = new AbilityAction(out);
        int opponentHealth = Integer.parseInt(getOpponentAttribute("currentHealth", playerID, gameID));
        if (opponentHealth <= 0) {
            int opponentLife = Integer.parseInt(action.getOpponentValue("life", playerID, gameID));
            action.changeOpponentValue(playerID, gameID, "life", Integer.toString(opponentLife - 1));
            //Set turns to -1 for later.
            db.updateTable("player", "turns", "-1", "playerID", playerID);
            return true;
        }
        //Player's turn is finished. Increment turns.
        int turns = Integer.parseInt(db.getField("turns", "player", "playerID", playerID));
        db.updateTable("player", "turns", Integer.toString(turns + 1), "playerID", playerID);
        return false;
    }

    public boolean checkIfGameIsWon(String playerID, String gameID) throws SQLException {
        AbilityAction action = new AbilityAction(out);
        int opponentLife = Integer.parseInt(action.getOpponentValue("life", playerID, gameID));
        return opponentLife <= 0;
    }

    /**
     * Set the starting priority for the next fight, based on which player started the
     * previous fight. Also check whether the other player is ready to start a new fight.
     * Start priority "-2" means the player will not start the next game.
     * Start priority "-3" means the player will start the next game.
     */
    public int setNextFightStartPriority(HttpServletResponse response, HttpServletRequest request, String playerID, String gameID) throws SQLException, InterruptedException {
        CookieFunctionality cf = new CookieFunctionality();
        int startPriority;
        boolean playerStart = Boolean.parseBoolean(cf.getValue(request, "playerStart"));
        if(playerStart) {
            //Next round, the player should NOT start the game.
            cf.replaceCookieValue(response, request, "playerStart", "false");
            db.updateTable("player", "turns", "-2", "playerID", playerID);
            startPriority = -2;
        }
        else {
            //Next round, the player SHOULD start the game.
            cf.replaceCookieValue(response, request, "playerStart", "true");
            db.updateTable("player", "turns", "-3", "playerID", playerID);
            startPriority = -3;
        }
        int i = 0;
        while (i <= 150) {
            //Wait for other player to get ready.
            int opponentReady = Integer.parseInt(getOpponentAttribute("turns", playerID, gameID));
            if (opponentReady < -1) {
                TimeUnit.SECONDS.sleep(2); //In case the other player is waiting for loop.
                return startPriority;
            }
            i++;
            TimeUnit.SECONDS.sleep(1); //Wait 2 seconds before looping again.
        }
        System.out.println("Error in PlayerInteractions - setNextFightStartPriority()");
        return 0;
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

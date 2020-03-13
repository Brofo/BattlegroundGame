package classes.fighterModule;

import classes.fighterModule.fighters.Fighter;
import classes.fighterModule.fighters.FighterChad;

import java.io.PrintWriter;

/**
 * This class is used to determine which fighter should be selected.
 */
public class SelectFighter {
    private PrintWriter out;

    public SelectFighter(PrintWriter out) {
        this.out = out;
    }

    /**
     * Return a figher based on the fighterName the player has chosen.
     * Add more fighters to the switch-case when they are added to the game.
     */
    public Fighter getFighter(String fighterName, String playerID, String gameID) {
        switch(fighterName) {
            case "Chad": return new FighterChad(out, playerID, gameID);
            //case "NewFighterHere": return new FighterClass(playerID, GameID);
        }

        System.out.println("No fighter with that name. Add fighter to classes.fighters.SelectFighter");
        return null;
    }
}

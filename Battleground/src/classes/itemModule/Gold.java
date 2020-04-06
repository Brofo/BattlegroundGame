package classes.itemModule;

import classes.fighterModule.AbilityAction;

import java.io.PrintWriter;
import java.sql.SQLException;

public class Gold {


    public void addGoldToDB(String amount, String playerID, PrintWriter out) throws SQLException {
        AbilityAction action = new AbilityAction(out);
        int currentGold = Integer.parseInt(action.getPlayerValue("gold", playerID));
        String newGold = Integer.toString(currentGold + Integer.parseInt(amount));
        action.changeOwnValue(playerID, "gold", newGold);
    }
}

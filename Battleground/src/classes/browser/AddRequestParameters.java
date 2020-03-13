package classes.browser;

import classes.fighterModule.AbilityAction;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AddRequestParameters {

    public void addFighterParameters(HttpServletRequest request, PrintWriter out, String playerID, String gameID) throws SQLException {
        AbilityAction action = new AbilityAction(out);

        request.setAttribute("playerLife", action.getPlayerValue("life", playerID));
        request.setAttribute("playerHealth", action.getPlayerValue("health", playerID));
        request.setAttribute("playerEnergy", action.getPlayerValue("energy", playerID));
        request.setAttribute("playerDamage", action.getPlayerValue("damage", playerID));
        request.setAttribute("playerArmour", action.getPlayerValue("armour", playerID));
        request.setAttribute("playerCritical", action.getPlayerValue("critical_chance", playerID) + "%");
        request.setAttribute("playerDodge", action.getPlayerValue("dodge_chance", playerID) + "%");

        request.setAttribute("opponentLife", action.getOpponentValue("life", playerID, gameID));
        request.setAttribute("opponentHealth", action.getOpponentValue("health", playerID, gameID));
        request.setAttribute("opponentEnergy", action.getOpponentValue("energy", playerID, gameID));
        request.setAttribute("opponentDamage", action.getOpponentValue("damage", playerID, gameID));
        request.setAttribute("opponentArmour", action.getOpponentValue("armour", playerID, gameID));
        request.setAttribute("opponentCritical", action.getOpponentValue("critical_chance", playerID, gameID) + "%");
        request.setAttribute("opponentDodge", action.getOpponentValue("dodge_chance", playerID, gameID) + "%");
    }
}

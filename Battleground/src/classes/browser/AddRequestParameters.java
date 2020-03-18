package classes.browser;

import classes.fighterModule.AbilityAction;
import classes.fighterModule.AbilityDescription;
import classes.fighterModule.SelectFighter;
import classes.fighterModule.fighters.Fighter;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class AddRequestParameters {
    private PrintWriter out;
    private HttpServletRequest request;

    public AddRequestParameters(HttpServletRequest request, PrintWriter out) {
        this.out = out;
        this.request = request;
    }

    public void addCookieNameParameters() {
        CookieFunctionality cf = new CookieFunctionality();
        request.setAttribute("playerName", cf.getValue(request, "playerName"));
        request.setAttribute("playerFighter", cf.getValue(request, "playerFighter"));
        request.setAttribute("opponentName", cf.getValue(request, "opponentName"));
        request.setAttribute("opponentFighter", cf.getValue(request, "opponentFighter"));
    }

    public void addFighterParameters(String playerID, String gameID) throws SQLException {
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

    public void addAbilityParameters(String playerFighter, String playerID, String gameID) {
        Fighter fighter = new SelectFighter(out).getFighter(playerFighter, playerID, gameID);
        HashMap<String, AbilityDescription> abilityMap = fighter.getAbilityMap();

        AbilityDescription basicAttack = abilityMap.get("basicAttack");
        request.setAttribute("basicAttack", basicAttack.getName());
        request.setAttribute("basicAttackDesc", basicAttack.getDescription() + " (Restores 1 energy) ");
        request.setAttribute("basicAttackEnergy", "[" + basicAttack.getEnergyCost() + " energy cost]");

        AbilityDescription abilityOne = abilityMap.get("abilityOne");
        request.setAttribute("abilityOne", abilityOne.getName());
        request.setAttribute("abilityOneDesc", abilityOne.getDescription());
        request.setAttribute("abilityOneEnergy", abilityOne.getEnergyCost());

        AbilityDescription abilityTwo = abilityMap.get("abilityTwo");
        request.setAttribute("abilityTwo", abilityTwo.getName());
        request.setAttribute("abilityTwoDesc", abilityTwo.getDescription());
        request.setAttribute("abilityTwoEnergy", abilityTwo.getEnergyCost());

        AbilityDescription abilityThree = abilityMap.get("abilityThree");
        request.setAttribute("abilityThree", abilityThree.getName());
        request.setAttribute("abilityThreeDesc", abilityThree.getDescription());
        request.setAttribute("abilityThreeEnergy", abilityThree.getEnergyCost());
    }
}

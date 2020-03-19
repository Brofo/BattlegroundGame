package classes.browser;

import classes.fighterModule.AbilityAction;
import classes.fighterModule.AbilityDescription;
import classes.fighterModule.SelectFighter;
import classes.fighterModule.fighters.Fighter;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * This class will add parameters to the request (parameters will be displayed on the .jsp file).
 */
public class AddRequestParameters {
    private PrintWriter out;
    private HttpServletRequest request;

    public AddRequestParameters(HttpServletRequest request, PrintWriter out) {
        this.out = out;
        this.request = request;
    }

    /**
     * Add request parameters from cookie. These are all static variables, they won't
     * change during the course of the game.
     */
    public void addCookieNameParameters() {
        CookieFunctionality cf = new CookieFunctionality();
        request.setAttribute("playerName", cf.getValue(request, "playerName"));
        request.setAttribute("playerFighter", cf.getValue(request, "playerFighter"));
        request.setAttribute("opponentName", cf.getValue(request, "opponentName"));
        request.setAttribute("opponentFighter", cf.getValue(request, "opponentFighter"));
    }

    /**
     * Add all the attributes for the player's fighter and the opponent's fighter.
     * These parameter are dynamic and will change for each ability used.
     */
    public void addFighterParameters(String playerID, String gameID) throws SQLException {
        AbilityAction action = new AbilityAction(out);

        request.setAttribute("playerLife", action.getPlayerValue("life", playerID));
        request.setAttribute("playerMaxHealth", action.getPlayerValue("maxHealth", playerID));
        request.setAttribute("playerCurrentHealth", action.getPlayerValue("currentHealth", playerID));
        request.setAttribute("playerEnergy", action.getPlayerValue("energy", playerID));
        request.setAttribute("playerDamage", action.getPlayerValue("damage", playerID));
        request.setAttribute("playerArmour", action.getPlayerValue("armour", playerID));
        request.setAttribute("playerCritical", action.getPlayerValue("critical_chance", playerID) + "%");
        request.setAttribute("playerDodge", action.getPlayerValue("dodge_chance", playerID) + "%");

        request.setAttribute("opponentLife", action.getOpponentValue("life", playerID, gameID));
        request.setAttribute("opponentMaxHealth", action.getOpponentValue("maxHealth", playerID, gameID));
        request.setAttribute("opponentCurrentHealth", action.getOpponentValue("currentHealth", playerID, gameID));
        request.setAttribute("opponentEnergy", action.getOpponentValue("energy", playerID, gameID));
        request.setAttribute("opponentDamage", action.getOpponentValue("damage", playerID, gameID));
        request.setAttribute("opponentArmour", action.getOpponentValue("armour", playerID, gameID));
        request.setAttribute("opponentCritical", action.getOpponentValue("critical_chance", playerID, gameID) + "%");
        request.setAttribute("opponentDodge", action.getOpponentValue("dodge_chance", playerID, gameID) + "%");
    }

    /**
     * Add request parameters that will contain all the abilities and information about each
     * ability for the selected fighter.
     * @param playerFighter - The name of the fighter that was selected.
     */
    public void addAbilityParameters(String playerFighter, String playerID, String gameID) {
        Fighter fighter = new SelectFighter(out).getFighter(playerFighter, playerID, gameID);
        HashMap<String, AbilityDescription> abilityMap = fighter.getAbilityMap();

        AbilityDescription basicAttack = abilityMap.get("basicAttack");
        request.setAttribute("basicAttack", basicAttack.getName());
        request.setAttribute("basicAttackDesc", basicAttack.getDescription() + " (Restores 1 energy) ");
        request.setAttribute("basicAttackEnergy", " Energy cost: [" + basicAttack.getEnergyCost() + "]");

        AbilityDescription abilityOne = abilityMap.get("abilityOne");
        request.setAttribute("abilityOne", abilityOne.getName());
        request.setAttribute("abilityOneDesc", abilityOne.getDescription());
        request.setAttribute("abilityOneEnergy", " Energy cost: [" + abilityOne.getEnergyCost() + "]");

        AbilityDescription abilityTwo = abilityMap.get("abilityTwo");
        request.setAttribute("abilityTwo", abilityTwo.getName());
        request.setAttribute("abilityTwoDesc", abilityTwo.getDescription());
        request.setAttribute("abilityTwoEnergy", " Energy cost: [" + abilityTwo.getEnergyCost() + "]");

        AbilityDescription abilityThree = abilityMap.get("abilityThree");
        request.setAttribute("abilityThree", abilityThree.getName());
        request.setAttribute("abilityThreeDesc", abilityThree.getDescription());
        request.setAttribute("abilityThreeEnergy", " Energy cost: [" + abilityThree.getEnergyCost() + "]");
    }
}

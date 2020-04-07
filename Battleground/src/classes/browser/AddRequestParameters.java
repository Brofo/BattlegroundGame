package classes.browser;

import classes.database.DbLib;
import classes.fighterModule.AbilityAction;
import classes.fighterModule.AbilityDescription;
import classes.fighterModule.SelectFighter;
import classes.fighterModule.fighters.Fighter;
import classes.itemModule.Item;
import classes.itemModule.ItemCollection;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
        request.setAttribute("playerBaseHealth", action.getPlayerValue("baseHealth", playerID));
        request.setAttribute("playerCurrentHealth", action.getPlayerValue("currentHealth", playerID));
        request.setAttribute("playerCurrentEnergy", action.getPlayerValue("currentEnergy", playerID));
        request.setAttribute("playerDamage", action.getPlayerValue("damage", playerID));
        request.setAttribute("playerArmour", action.getPlayerValue("armour", playerID));
        request.setAttribute("playerCriticalChance", action.getPlayerValue("critical_chance", playerID) + "%");
        request.setAttribute("playerDodgeChance", action.getPlayerValue("dodge_chance", playerID) + "%");

        request.setAttribute("opponentLife", action.getOpponentValue("life", playerID, gameID));
        request.setAttribute("opponentBaseHealth", action.getOpponentValue("baseHealth", playerID, gameID));
        request.setAttribute("opponentCurrentHealth", action.getOpponentValue("currentHealth", playerID, gameID));
        request.setAttribute("opponentCurrentEnergy", action.getOpponentValue("currentEnergy", playerID, gameID));
        request.setAttribute("opponentDamage", action.getOpponentValue("damage", playerID, gameID));
        request.setAttribute("opponentArmour", action.getOpponentValue("armour", playerID, gameID));
        request.setAttribute("opponentCriticalChance", action.getOpponentValue("critical_chance", playerID, gameID) + "%");
        request.setAttribute("opponentDodgeChance", action.getOpponentValue("dodge_chance", playerID, gameID) + "%");
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

    public void addFightOverParameters(String playerID, String gameID, boolean win) throws SQLException {
        CookieFunctionality cf = new CookieFunctionality();
        AbilityAction action = new AbilityAction(out);
        String playerName = cf.getValue(request, "playerName");
        String opponentName = cf.getValue(request, "opponentName");
        String playerLives = action.getPlayerValue("life", playerID);
        String opponentLives = action.getOpponentValue("life", playerID, gameID);

        if (win) {
            request.setAttribute("winnerName", playerName);
            request.setAttribute("loserName", opponentName);
            request.setAttribute("winnerLife", playerLives);
            request.setAttribute("loserLife", opponentLives);
        }
        else {
            request.setAttribute("winnerName", opponentName);
            request.setAttribute("loserName", playerName);
            request.setAttribute("winnerLife", opponentLives);
            request.setAttribute("loserLife", playerLives);
        }
    }

    public void addAbilityResultParameters(String playerID, String gameID, boolean useAbility) throws SQLException {
        AbilityHandler ah = new AbilityHandler();
        AbilityAction action = new AbilityAction(out);

        if (useAbility) {
            String playerAbilityName = ah.updateCurrentAbility(request, out, playerID);
            String playerCritical_hit = action.getPlayerValue("critical_hit", playerID);
            String opponentDodged = action.getOpponentValue("dodged", playerID, gameID);
            request.setAttribute("playerCurrentAbility", playerAbilityName);
            if(playerCritical_hit.equals("1")) {
                request.setAttribute("playerCritical_hit", "Critical Hit!");
            }
            if(opponentDodged.equals("1")) {
                request.setAttribute("opponentDodged", "Dodge!");
            }
        }
        else {
            String opponentAbility = action.getOpponentValue("currentAbility", playerID, gameID);
            String opponentCritical_hit = action.getOpponentValue("critical_hit", playerID, gameID);
            String playerDodged = action.getPlayerValue("dodged", playerID);
            request.setAttribute("opponentCurrentAbility", opponentAbility);
            if(opponentCritical_hit.equals("1")) {
                request.setAttribute("opponentCritical_hit", "Critical Hit!");
            }
            if(playerDodged.equals("1")) {
                request.setAttribute("playerDodged", "Dodge!");
            }
        }
    }

    public void addGoldParameters(String playerID) throws SQLException {
        DbLib db = new DbLib(out);
        String gold = db.getField("gold", "player", "playerID", playerID);
        request.setAttribute("gold", gold);
    }

    /**
     * Creates new parameters by incrementing number for each item.
     */
    public void addMysteryItemParameters(Item item) {
        request.setAttribute("itemName", item.getName());
        request.setAttribute("itemAttribute", item.getFrontendAttribute());
        request.setAttribute("itemAmount", item.getAmount());
        request.setAttribute("itemStorePrice", Integer.toString(item.getPrice()));
        request.setAttribute("itemSellPrice", Integer.toString(item.getSellPrice()));
    }

    public void addStoreParameters() {
        ItemCollection itemCol = new ItemCollection();
        ArrayList<Item> items = itemCol.getAllItems();

        int i = 1;
        for(Item item : items) {
            String itemName = "itemName" + i;
            String itemAttribute = "itemAttribute" + i;
            String itemAmount = "itemAmount" + i;
            String itemPrice = "itemPrice" + i;

            request.setAttribute(itemName, item.getName());
            request.setAttribute(itemAttribute, item.getFrontendAttribute());
            request.setAttribute(itemAmount, item.getAmount());
            request.setAttribute(itemPrice, Integer.toString(item.getPrice()));
            i++;
        }
    }
}

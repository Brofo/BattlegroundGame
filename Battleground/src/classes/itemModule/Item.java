package classes.itemModule;

import classes.fighterModule.AbilityAction;

import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * This class contains all the information and operations of an item.
 */
public class Item {
    private String name; //Name of the item.
    private String attribute; //The attribute to boost. Example damage or baseHealth.
    private String amount; //How much the attribute should be boosted.
    private int price; //The price of the item.
    private int tier; //The item tier describes the rarity of the item. The tiers are Tier 1, 2, 3 and 4.

    public Item(String name, String attribute, String amount, int price, int tier) {
        this.name = name;
        this.attribute = attribute;
        this.amount = amount;
        this.price = price;
        this.tier = tier;
    }

    /**
     * This method is used to buy and use an item. Buy and use are in the same method,
     * because they are operations that will always be done coherently.
     * @return - true if the player can afford the item. false if not.
     */
    public boolean buyAndUseItem(String playerID, PrintWriter out) throws SQLException {
        AbilityAction action = new AbilityAction(out);
        int currentGold = Integer.parseInt(action.getPlayerValue("gold", playerID));
        if(currentGold >= this.price) {
            useItem(playerID, out);
            int newGold = currentGold - this.price;
            action.changeOwnValue(playerID, "gold", Integer.toString(newGold));
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Use item without paying for it.
     */
    public void useItem(String playerID, PrintWriter out) throws SQLException {
        AbilityAction action = new AbilityAction(out);
        double oldValue = Double.parseDouble(action.getPlayerValue(this.attribute, playerID));

        //If the amount is in percent, remove the percent in order to parse it.
        this.amount = this.amount.replace("%", "");

        double newValue = oldValue + Double.parseDouble(this.amount);
        action.changeOwnValue(playerID, this.attribute, Double.toString(newValue));
    }

    public void sellItem(String playerID, PrintWriter out) throws SQLException {
        AbilityAction action = new AbilityAction(out);
        int currentGold = Integer.parseInt(action.getPlayerValue("gold", playerID));
        int sellPrice = getSellPrice();
        String newGold = Integer.toString(currentGold + sellPrice);
        action.changeOwnValue(playerID, "gold", newGold);
    }

    public int getSellPrice() {
        double tempValue = this.price * 0.75;
        return (int)Math.round(tempValue);
    }

    /**
     * @return - A string that shows a frontend friendly attribute name.
     */
    public String getFrontendAttribute() {
        switch(attribute) {
            case "baseHealth": return "Health";
            case "baseEnergy": return "Energy";
            case "damage": return "Damage";
            case "armour": return "Armour";
            case "critical_chance": return "Critical chance";
            case "dodge_chance": return "Dodge chance";
            default: return attribute;
        }
    }

    public String getName() {
        return name;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public int getTier() {
        return tier;
    }
}

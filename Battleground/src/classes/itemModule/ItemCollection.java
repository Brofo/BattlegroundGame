package classes.itemModule;
import java.util.ArrayList;

/**
 * This class is used to create instances of Item. It will include all the possible items
 * in the game.
 */
public class ItemCollection {
private ArrayList<Item> items;

    public ItemCollection() {
        items = new ArrayList<>();
        createItems();
    }

    /**
     * Method that creates items. New items to be added in the game are create here.
     * Price ranges for items are:
     * 1 - 12 Gold: Tier 1
     * 13 - 25 Gold: Tier 2
     * 26 - 39 Gold: Tier 3
     * 40+ Gold: Tier 4
     */
    private void createItems() {
        items.add(new Item("Rabbit foot", "dodge_chance", "5%", 10, 1));
        items.add(new Item("Stamina potion", "baseEnergy", "1", 10, 1));
        items.add(new Item("Amulet of life", "baseHealth", "25", 7, 1));
        items.add(new Item("Spiky gloves", "critical_chance", "5%", 8, 1));
        items.add(new Item("Dagger", "damage", "5", 10, 1));
        items.add(new Item("Berserker gloves", "critical_chance", "15%", 20, 2));
        items.add(new Item("Lignum shield", "armour", "10", 16, 2));
        items.add(new Item("War spear", "damage", "20", 25, 2));
        items.add(new Item("Smooth shoes", "dodge_chance", "15%", 26, 3));
        items.add(new Item("Giant's belt", "baseHealth", "120", 30, 3));
        items.add(new Item("Mithril armour", "armour", "25", 30, 3));
        items.add(new Item("Death blade", "damage", "100", 60, 4));
    }

    public Item getItem(String itemName) {
        for(Item item : items) {
            if(item.getName().equals(itemName)) {
                return item;
            }
        }
        System.out.println("Error in ItemCollection - getItem() - No item found.");
        return null;
    }

    public ArrayList<Item> getAllItems() {
        return items;
    }
}

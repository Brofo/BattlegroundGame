package classes.itemModule;

import java.util.ArrayList;
import java.util.Random;

public class MysteryChest {
    private ItemCollection ic;
    private Random rng;
    private ArrayList<Item> tier1;
    private ArrayList<Item> tier2;
    private ArrayList<Item> tier3;
    private ArrayList<Item> tier4;

    public MysteryChest() {
        ic = new ItemCollection();
        rng = new Random();
        tier1 = new ArrayList<>();
        tier2 = new ArrayList<>();
        tier3 = new ArrayList<>();
        tier4 = new ArrayList<>();
        sortItemTiers();
    }

    /**
     * This method will return a mystery item. The odds of getting the different items are calculated
     * based on the item's tier. Tier 1 items have a 50% chance, Tier 2 items have a 35% chance,
     * tier 3 items have a 14% chance, and tier 4 items have a 1% chance.
     * @return - A mystery item.
     */
    public Item getMysteryItem() {
        int tierChanceNumber = rng.nextInt(100) +1;
        int itemNumber;
        if(tierChanceNumber > 50) {
            itemNumber = rng.nextInt(tier1.size());
            return tier1.get(itemNumber);
        }
        if(tierChanceNumber > 15) {
            itemNumber = rng.nextInt(tier2.size());
            return tier2.get(itemNumber);
        }
        if(tierChanceNumber > 1) {
            itemNumber = rng.nextInt(tier3.size());
            return tier3.get(itemNumber);
        }
        if(tierChanceNumber == 1) {
            itemNumber = rng.nextInt(tier4.size());
            return tier4.get(itemNumber);
        }
        System.out.println("Error in getMysteryItem()");
        return null;
    }

    /**
     * This method will return an amount of gold based on the random number that was generated.
     * It is also tier based like in the getMysteryItem() method.
     * @return - The amount of gold as a String.
     */
    public String getMysteryGold() {
        int tierChanceNumber = rng.nextInt(100) +1;
        if(tierChanceNumber > 50) {
            return "6";
        }
        if(tierChanceNumber > 15) {
            return "12";
        }
        if(tierChanceNumber > 1) {
            return "18";
        }
        if(tierChanceNumber == 1) {
            return "24";
        }
        System.out.println("Error in getMysteryGold()");
        return null;
    }

    private void sortItemTiers() {
        ArrayList<Item> items = ic.getAllItems();
        for(Item item : items) {

            int tier = item.getTier();
            switch(tier) {
                case 1: tier1.add(item);
                case 2: tier2.add(item);
                case 3: tier3.add(item);
                case 4: tier4.add(item);
            }
        }
    }
}

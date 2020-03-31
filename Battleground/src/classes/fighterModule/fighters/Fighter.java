package classes.fighterModule.fighters;

import classes.fighterModule.AbilityAction;
import classes.fighterModule.AbilityDescription;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

/**
 * The parent class of all fighters.
 */
public abstract class Fighter {
    //Making all fields package-private.
    AbilityAction action;

    String playerID;
    String gameID;

    //All fighter attributes will be changed later.
    double baseHealth = 0;
    double currentHealth = 0;
    int baseEnergy = 0;
    int currentEnergy = 0;
    double damage = 0;
    double armour = 0;
    double critical_chance = 0;
    double dodge_chance = 0;

    public Fighter(PrintWriter out, String playerID, String gameID) {
        this.gameID = gameID;
        this.playerID = playerID;

        action = new AbilityAction(out);
    }

    /**
     * This method is used to register fighter values in a database for the first time
     * in a game, and to reset a fighter's value if necessary. This is where the base values
     * for each fighter is determined.
     */
    public abstract void setFighterToBaseValues();

    /**
     * For each round, we want to restore the fighter's health and energy, without affecting
     * other attributes because of items that have been added to the fighter. This method
     * will just restore the currentHealth and currentEnergy of the fighter.
     */
    public void restoreHealthAndEnergy() throws SQLException {
        setFighterToBaseValues(); //Set fighter to base values, just to restore health and energy.
        action.changeOwnValue(playerID,"currentHealth", Double.toString(this.baseHealth));
        action.changeOwnValue(playerID, "currentEnergy", Integer.toString(this.baseEnergy));
    }

    /**
     * Basic attack applies to all fighters, and the functionality is therefore put into the superclass.
     * However, every fighter has a unique name for basic attack.
     * @return true if the player has enough energy to perform attack. Basic attack is always true.
     */
    public boolean basicAttack() throws SQLException {
        syncFighterWithDB(); //Update values to match the Database.
        dealDamageToOpponent(this.damage);
        useEnergy(-1); //Negative one energy is spent, because we gain one energy.
        return true; //Always enough energy to use basic attack.
    }

    //Every fighter needs a HashMap with the abilities and description of the abilities.
    public abstract HashMap<String, AbilityDescription> getAbilityMap();

    //Every fighter has 3 abilities + the basic attack.
    public abstract boolean abilityOne() throws SQLException;
    public abstract boolean abilityTwo() throws SQLException;
    public abstract boolean abilityThree() throws SQLException;

    //Set methods are currently not in use, but may be needed later for Items.
    public abstract void setBaseHealth();
    public abstract void setCurrentHealth();
    public abstract void setBaseEnergy();
    public abstract void setCurrentEnergy();
    public abstract void setDamage();
    public abstract void setArmour();
    public abstract void setCritical_chance();
    public abstract void setDodge_chance();

    //Get methods are used to register the fighter in the Database.
    public double getBaseHealth() {return baseHealth; }
    public double getCurrentHealth() {
        return currentHealth;
    }
    public int getBaseEnergy() { return baseEnergy; }
    public int getCurrentEnergy() {
        return currentEnergy;
    }
    public double getDamage() {
        return damage;
    }
    public double getArmour() { return armour; }
    public double getCritical_chance() {
        return critical_chance;
    }
    public double getDodge_chance() {
        return dodge_chance;
    }

    /**
     * Based on the ability that was chosen by the player, execute the ability.
     * @param ability - The ability selected.
     * @return True if the player has enough energy to use the ability. (The ability is executed).
     *         False if the player does not have enough energy. (The ability is not executed).
     */
    public boolean useAbility(String ability) throws SQLException {
        switch(ability) {
            case "basicAttack" : return basicAttack();
            case "abilityOne" : return abilityOne();
            case "abilityTwo" : return abilityTwo();
            case "abilityThree" : return abilityThree();
        }
        return false;
    }

    /**
     * Get all the fighter's attributes from the database, and update the fields in the object.
     * The fighter's attributes are only stored in the database, so we need to sync the fighter
     * with the database each time we perform an action (like using an ability), in order to make
     * sure the attributes are updated.
     */
    protected void syncFighterWithDB() {
        try {
            baseHealth = Integer.parseInt(action.getPlayerValue("baseHealth", playerID));
            currentHealth = Integer.parseInt(action.getPlayerValue("currentHealth", playerID));
            baseEnergy = Integer.parseInt(action.getPlayerValue("baseEnergy", playerID));
            currentEnergy = Integer.parseInt(action.getPlayerValue("currentEnergy", playerID));
            damage = Integer.parseInt(action.getPlayerValue("damage", playerID));
            armour = Integer.parseInt(action.getPlayerValue("armour", playerID));
            critical_chance = Integer.parseInt(action.getPlayerValue("critical_chance", playerID));
            dodge_chance = Integer.parseInt(action.getPlayerValue("dodge_chance", playerID));
        }
        catch(SQLException e) {
            System.out.println("Error in classes.fighterModule.fighters.Fighter - syncFighterWithDB()  " + e);
        }
    }

    /**
     * Change the opponent's attribute in the database, based on how much damage is dealt.
     * The total damage to deal is selected in the method of each ability, but it is also
     * necessary to use the calculateDamage() method to get a potential critical strike,
     * and to calculate damage based on opponent's armour.
     */
    protected void dealDamageToOpponent(double totalDamageToDeal) throws SQLException {
        double opponentHealth = Double.parseDouble(action.getOpponentValue("currentHealth", playerID, gameID));
        double damageToDeal = calculateDamage(totalDamageToDeal);
        String opponentNewHealth = Double.toString(opponentHealth - damageToDeal);
        action.changeOpponentValue(playerID, gameID, "currentHealth", opponentNewHealth);
    }

    /**
     * This method will update the energy of the player's fighter. If the amount of
     * energy is negative, it will be added instead of subtracted to the total energy.
     * @param amount - Amount of energy that will be changed. If the number is positive,
     *                 the energy will be subtracted from the database. If the number is
     *                 negative, the energy will be added to the database.
     */
    protected void useEnergy(int amount) throws SQLException {
        currentEnergy = currentEnergy - amount;
        action.changeOwnValue(playerID, "currentEnergy", Integer.toString(currentEnergy));
    }

    /**
     * Calculate damage based on critical chance and the opponents armour. The opponent
     * can also dodge the attack, resulting in 0 damage dealt. If the player inflicts a critical
     * hit, or the opponent dodges, the database will be updated with these values.
     *
     * dodged and critical_hit are set to 0 (false) first, to update the value from a previous
     * ability.
     */
    protected double calculateDamage(double damageFromAbility) throws SQLException {
        int opponentArmour = Integer.parseInt(action.getOpponentValue("armour", playerID, gameID));
        double opponentDodgeChance = Double.parseDouble(action.getOpponentValue("dodge_chance", playerID, gameID));

        boolean critical_hit = abilityDice(critical_chance);
        boolean opponentDodged = abilityDice(opponentDodgeChance);

        action.changeOwnValue(playerID, "critical_hit", "0");
        action.changeOpponentValue(playerID, gameID, "dodged", "0");

        if (critical_hit) {
            action.changeOwnValue(playerID, "critical_hit", "1");
            damageFromAbility *= 2;
        }
        if (opponentDodged) {
            action.changeOpponentValue(playerID, gameID, "dodged", "1");
            return 0; //Return 0 because 0 damage is dealt.
        }

        double calculatedDamage = damageFromAbility - opponentArmour;
        if (calculatedDamage > 0) {
            return calculatedDamage;
        }
        else {
            return 0;
        }
    }

    /**
     * This method will check if the player hits the percentage for a critical hit or
     * the opponent's percentage of a dodge.
     * @param - critical_chance or dodge_chance
     * @return - True if the percentage is hit.
     */
    private boolean abilityDice(double value) {
        Random ran = new Random();
        int diceRoll = ran.nextInt(100) +1;
        return value >= diceRoll;
    }
}

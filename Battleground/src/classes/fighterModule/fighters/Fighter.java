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
    double maxHealth = 0;
    double currentHealth = 0;
    int energy = 0;
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
    public abstract void setMaxHealth();
    public abstract void setCurrentHealth();
    public abstract void setEnergy();
    public abstract void setDamage();
    public abstract void setArmour();
    public abstract void setCritical_chance();
    public abstract void setDodge_chance();

    //Get methods are used to register the fighter in the Database.
    public double getMaxHealth() {return maxHealth; }
    public double getCurrentHealth() {
        return currentHealth;
    }
    public int getEnergy() {
        return energy;
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
            maxHealth = Integer.parseInt(action.getPlayerValue("maxHealth", playerID));
            currentHealth = Integer.parseInt(action.getPlayerValue("currentHealth", playerID));
            energy = Integer.parseInt(action.getPlayerValue("energy", playerID));
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
        energy = energy - amount;
        action.changeOwnValue(playerID, "energy", Integer.toString(energy));
    }

    /**
     * Calculate damage based on critical chance and the opponents armour.
     * Dodging will be calculated somewhere else.
     */
    protected double calculateDamage(double damageFromAbility) throws SQLException {
        int opponentArmour = Integer.parseInt(action.getOpponentValue("armour", playerID, gameID));

        if (critical_chance != 0) {
            Random ran = new Random();
            int diceRoll = ran.nextInt(100) +1;
            if (critical_chance >= diceRoll) {
                damageFromAbility = damageFromAbility * 2;
            }
        }

        double calculatedDamage = damageFromAbility - opponentArmour;
        if (calculatedDamage > 0) {
            return calculatedDamage;
        }
        else {
            return 0;
        }
    }
}

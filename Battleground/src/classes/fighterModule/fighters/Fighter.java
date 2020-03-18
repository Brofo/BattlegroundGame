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
    AbilityAction action;

    //Making all fields package-private.
    String playerID;
    String gameID;
    double health;
    int energy;
    double damage;
    double armour;
    double critical_chance;
    double dodge_chance;

    public Fighter(PrintWriter out, String playerID, String gameID, int health, int energy, int damage, int armour, int critical_chance, int dodge_chance) {
        this.playerID = playerID;
        this.gameID = gameID;
        this.health = health;
        this.energy = energy;
        this.damage = damage;
        this.armour = armour;
        this.critical_chance = critical_chance;
        this.dodge_chance = dodge_chance;

        action = new AbilityAction(out);
    }

    /**
     * Basic attack applies to all fighters.
     */
    public boolean basicAttack() throws SQLException {
        syncFighterWithDB();
        dealDamageToOpponent(this.damage);
        useEnergy(-1); //Negative one energy is spent, because we gain one energy.
        return true; //Always enough energy to use basic attack.
    }

    public abstract HashMap<String, AbilityDescription> getAbilityMap();
    public abstract boolean abilityOne() throws SQLException;
    public abstract boolean abilityTwo() throws SQLException;
    public abstract boolean abilityThree() throws SQLException;

    public abstract void setHealth();
    public abstract void setEnergy();
    public abstract void setDamage();
    public abstract void setArmour();
    public abstract void setCritical_chance();
    public abstract void setDodge_chance();

    public double getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public double getDamage() {
        return damage;
    }

    public double getArmour() {
        return armour;
    }

    public double getCritical_chance() {
        return critical_chance;
    }

    public double getDodge_chance() {
        return dodge_chance;
    }

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
     * Get all the fighter's attributes from the database, and update the fields.
     */
    protected void syncFighterWithDB() {
        try {
            health = Integer.parseInt(action.getPlayerValue("health", playerID));
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

    protected void dealDamageToOpponent(double totalDamageToDeal) throws SQLException {
        double opponentHealth = Double.parseDouble(action.getOpponentValue("health", playerID, gameID));
        double damageToDeal = calculateDamage(totalDamageToDeal);
        String opponentNewHealth = Double.toString(opponentHealth - damageToDeal);
        action.changeOpponentValue(playerID, gameID, "health", opponentNewHealth);
    }

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

package classes.fighterModule.fighters;

import classes.fighterModule.AbilityDescription;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * This is a subclass of Fighter
 */
public class FighterChad extends Fighter {
    private HashMap<String, AbilityDescription> abilityMap;

    private double abilityOneDamage;
    private double abilityThreeDamage;
    private int abilityOneEnergy;
    private int abilityThreeEnergy;

    public FighterChad(PrintWriter out, String playerID, String gameID) {
        super(out, playerID, gameID);

        //Every time we get a new fighter object, we want to make sure the fighter
        //has updated values from the database. This method needs to be called in
        //EVERY new subclass of fighter.
        syncFighterWithDB();

        //Ability attributes is calculated here.
        abilityOneDamage = damage * 1.5;
        abilityOneEnergy = 3;
        abilityThreeDamage = damage * 3;
        abilityThreeEnergy = 8;

        //Add the name and description of each ability for this fighter.
        //The key should ALWAYS be basicAttack, abilityOne, abilityTwo, abilityThree.
        //The format of the description of the ability should be similar.
        abilityMap = new HashMap<>();
        abilityMap.put("basicAttack", new AbilityDescription("Punch", "Basic Attack. [" + damage + " damage]", 0));
        abilityMap.put("abilityOne", new AbilityDescription("Throw beerpong ball", "[" + abilityOneDamage + " damage]", abilityOneEnergy));
        abilityMap.put("abilityTwo", new AbilityDescription("Pass out", "[Restores 2 energy]", 0));
        abilityMap.put("abilityThree", new AbilityDescription("Drunken Rage", "[" + abilityThreeDamage + " damage]", abilityThreeEnergy));
    }

    /**
     * This method is used to register fighter values in a database for the first time
     * in a game, and to reset a fighter's value if necessary. This is where the base values
     * for the fighter is determined.
     */
    @Override
    public void setFighterToBaseValues() {
        this.baseHealth = 850;
        this.currentHealth = baseHealth;
        this.baseEnergy = 5;
        this.currentEnergy = baseEnergy;
        this.damage = 80;
        this.armour = 0;
        this.critical_chance = 15;
        this.dodge_chance = 0;
        this.fighterPic = "css/fighterPics/chadPic.jpg";
    }

    @Override
    public HashMap<String, AbilityDescription> getAbilityMap() {
        return abilityMap;
    }

    @Override
    public boolean abilityOne() throws SQLException {
        if (this.currentEnergy >= abilityOneEnergy) {
            dealDamageToOpponent(abilityOneDamage);
            useEnergy(abilityOneEnergy);
            return true;
        }
        return false;
    }

    @Override
    public boolean abilityTwo() throws SQLException {
        useEnergy(-2); //Negative 2 energy is spent, because we gain 2 energy.
        return true;
    }

    @Override
    public boolean abilityThree() throws SQLException {
        if (this.currentEnergy >= abilityThreeEnergy) {
            dealDamageToOpponent(abilityThreeDamage);
            useEnergy(abilityThreeEnergy);
            return true;
        }
        return false;
    }

    @Override
    public void setBaseHealth() {

    }

    @Override
    public void setCurrentHealth() {

    }

    @Override
    public void setBaseEnergy() {

    }

    @Override
    public void setCurrentEnergy() {

    }

    @Override
    public void setDamage() {

    }

    @Override
    public void setArmour() {
    }

    @Override
    public void setCritical_chance() {

    }

    @Override
    public void setDodge_chance() {

    }
}

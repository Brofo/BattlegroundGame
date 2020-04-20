package classes.fighterModule.fighters;

import classes.fighterModule.AbilityDescription;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * This is a subclass of Fighter.
 */
public class AAA_FighterSetUpClass extends Fighter {
    private HashMap<String, AbilityDescription> abilityMap;

    private double abilityOneDamage;
    private double abilityTwoDamage;
    private double abilityThreeDamage;
    private int abilityOneEnergy;
    private int abilityTwoEnergy;
    private int abilityThreeEnergy;

    public AAA_FighterSetUpClass(PrintWriter out, String playerID, String gameID) {
        super(out, playerID, gameID);

        //Every time we get a new fighter object, we want to make sure the fighter
        //has updated values from the database. This method needs to be called in
        //EVERY new subclass of fighter.
        syncFighterWithDB();

        //Ability attributes is calculated here.
        abilityOneDamage = damage * 0;
        abilityOneEnergy = 0;
        abilityTwoDamage = damage * 0;
        abilityTwoEnergy = 0;
        abilityThreeDamage = damage * 0;
        abilityThreeEnergy = 0;

        //Add the name and description of each ability for this fighter.
        //The key should ALWAYS be basicAttack, abilityOne, abilityTwo, abilityThree.
        //The format of the description of the ability should be similar.
        abilityMap = new HashMap<>();
        abilityMap.put("basicAttack", new AbilityDescription("x", "Basic Attack. [" + damage + " damage]", 0));
        abilityMap.put("abilityOne", new AbilityDescription("x", "[" + abilityOneDamage + " damage]", abilityOneEnergy));
        abilityMap.put("abilityTwo", new AbilityDescription("x", "[" + abilityTwoDamage + "damage]", abilityTwoEnergy));
        abilityMap.put("abilityThree", new AbilityDescription("x", "[" + abilityThreeDamage + " damage]", abilityThreeEnergy));
    }

    /**
     * This method is used to register fighter values in a database for the first time
     * in a game, and to reset a fighter's value if necessary. This is where the base values
     * for the fighter is determined.
     */
    @Override
    public void setFighterToBaseValues() {
        this.baseHealth = 1000;
        this.currentHealth = baseHealth;
        this.baseEnergy = 5;
        this.currentEnergy = baseEnergy;
        this.damage = 50;
        this.armour = 0;
        this.critical_chance = 0;
        this.dodge_chance = 0;
        this.fighterPic = "css/fighterPics/fighterPic.jpg";
    }

    @Override
    public HashMap<String, AbilityDescription> getAbilityMap() {
        return abilityMap;
    }

    @Override
    public boolean abilityOne() throws SQLException {
        //Check if we have enough energy to use the ability.
        if (this.currentEnergy >= abilityOneEnergy) {
            //Enough energy. Use ability (this ability deals damage):
            dealDamageToOpponent(abilityOneDamage);
            //Use the energy the ability cost:
            useEnergy(abilityOneEnergy);
            //Return true because we have enough energy, and the ability have been used.
            return true;
        }
        //Not enough energy to use the ability.
        return false;
    }

    @Override
    public boolean abilityTwo() throws SQLException {
        if (this.currentEnergy >= abilityTwoEnergy) {
            dealDamageToOpponent(abilityTwoDamage);
            useEnergy(abilityTwoEnergy);
            return true;
        }
        return false;
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

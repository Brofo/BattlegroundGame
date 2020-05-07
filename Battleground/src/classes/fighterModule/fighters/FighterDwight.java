package classes.fighterModule.fighters;

import classes.fighterModule.AbilityDescription;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class FighterDwight extends Fighter
{
    private HashMap<String, AbilityDescription> abilityMap;


    private double abilityOneDamage;
    private double abilityThreeDamage;
    private int abilityOneEnergy;
    private int abilityTwoEnergy;
    private int abilityThreeEnergy;

    public FighterDwight(PrintWriter out, String playerID, String gameID)
    {
        super(out, playerID, gameID);


        //Every time we get a new fighter object, we want to make sure the fighter
        //has updated values from the database. This method needs to be called in
        //EVERY new subclass of fighter.
        syncFighterWithDB();

        //Ability attributes is calculated here.
        abilityOneDamage = damage * 1.5;
        abilityOneEnergy = 4;
        abilityTwoEnergy = 6;
        abilityThreeDamage = damage * 4;
        abilityThreeEnergy = 11;

        //Add the name and description of each ability for this fighter.
        //The key should ALWAYS be basicAttack, abilityOne, abilityTwo, abilityThree.
        //The format of the description of the ability should be similar.
        abilityMap = new HashMap<>();
        abilityMap.put("basicAttack", new AbilityDescription("Pepper spray", "Basic Attack. [" + damage + " damage]", 0));
        abilityMap.put("abilityOne", new AbilityDescription("MICHAEL", "[" + abilityOneDamage + " damage]", abilityOneEnergy));
        abilityMap.put("abilityTwo", new AbilityDescription("Eat Beets", "[HEAL 200 HP]", abilityTwoEnergy));
        abilityMap.put("abilityThree", new AbilityDescription("Bear roar", "[" + abilityThreeDamage + " damage]" + "[HEAL 100 HP]", abilityThreeEnergy));

    }


    /**
     * This method is used to register fighter values in a database for the first time
     * in a game, and to reset a fighter's value if necessary. This is where the base values
     * for the fighter is determined.
     */
    @Override
    public void setFighterToBaseValues()
    {
        this.baseHealth = 1000;
        this.currentHealth = baseHealth;
        this.baseEnergy = 6;
        this.currentEnergy = baseEnergy;
        this.damage = 50;
        this.armour = 15;
        this.critical_chance = 15;
        this.dodge_chance = 0;
    }

    @Override
    public HashMap<String, AbilityDescription> getAbilityMap()
    {
        return abilityMap;
    }

    @Override
    public boolean abilityOne() throws SQLException
    {
        //Check if we have enough energy to use the ability.
        if (this.currentEnergy >= abilityOneEnergy)
        {
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
    public boolean abilityTwo() throws SQLException
    {
        if (this.currentEnergy >= abilityTwoEnergy)
        {

            String newHealth = Double.toString(this.currentHealth + 200);
            action.changeOwnValue(playerID, "currentHealth", newHealth);
            useEnergy(abilityTwoEnergy);
            return true;
        }
        return false;
    }

    @Override
    public boolean abilityThree() throws SQLException
    {
        if (this.currentEnergy >= abilityThreeEnergy)
        {
            dealDamageToOpponent(abilityThreeDamage);
            String newHealth = Double.toString(this.currentHealth + 100);
            action.changeOwnValue(playerID, "currentHealth", newHealth);
            useEnergy(abilityThreeEnergy);
            return true;
        }
        return false;
    }

    @Override
    public void setBaseHealth()
    {

    }

    @Override
    public void setCurrentHealth()
    {

    }

    @Override
    public void setBaseEnergy()
    {

    }

    @Override
    public void setCurrentEnergy()
    {

    }

    @Override
    public void setDamage()
    {

    }

    @Override
    public void setArmour()
    {
    }

    @Override
    public void setCritical_chance()
    {

    }

    @Override
    public void setDodge_chance()
    {

    }


}
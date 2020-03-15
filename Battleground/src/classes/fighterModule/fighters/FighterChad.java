package classes.fighterModule.fighters;

import classes.fighterModule.AbilityDescription;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class FighterChad extends Fighter {
    private HashMap<String, AbilityDescription> abilityMap;

    public FighterChad(PrintWriter out, String playerID, String gameID) {
        super(out, playerID, gameID,850,5, 80, 0, 15, 0);

        //Add the name and description of each ability for this fighter:
        abilityMap = new HashMap<>();
        abilityMap.put("basicAttack", new AbilityDescription("Punch", "Basic Attack [" + damage + "] damage", 0));
        abilityMap.put("abilityOne", new AbilityDescription("Throw beerpong ball", "Deals [" + damage * 1.5 + "] damage", 3));
        abilityMap.put("abilityTwo", new AbilityDescription("Pass out", "Restores [2] energy", 0));
        abilityMap.put("abilityThree", new AbilityDescription("Drunken Rage", "Deals [" + damage * 3 + "] damage", 8));
    }

    @Override
    public HashMap<String, AbilityDescription> getAbilityMap() {
        return abilityMap;
    }

    @Override
    public void abilityOne() throws SQLException {
        syncFighterWithDB();
        dealDamageToOpponent(this.damage * 1.5);
        useEnergy(3);
    }

    @Override
    public void abilityTwo() throws SQLException {
        syncFighterWithDB();
        useEnergy(-2); //Negative 2 energy is spent, because we gain 2 energy.
    }

    @Override
    public void abilityThree() throws SQLException {
        syncFighterWithDB();
        dealDamageToOpponent(this.damage * 3);
        useEnergy(8);
    }

    @Override
    public void setHealth() {

    }

    @Override
    public void setEnergy() {

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

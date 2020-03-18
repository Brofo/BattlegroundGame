package classes.fighterModule;

/**
 * This class is intended to give a name and description to different abilities,
 * but not functionality to the abilities.
 */
public class AbilityDescription {
    private String name; //Name of the ability.
    private String description; //Description of the ability (e.g how much damage it deals).
    private int energyCost; //The energy cost of the ability.

    public AbilityDescription(String name, String description, int energyCost) {
        this.name = name;
        this.description = description;
        this.energyCost = energyCost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getEnergyCost() { return energyCost; }
}
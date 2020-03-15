package classes.fighterModule;

/**
 * This class is intended to give a name and description to different abilities,
 * but not direct functionality to the abilities.
 */
public class AbilityDescription {
    private String name;
    private String description;
    private int energyCost;

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
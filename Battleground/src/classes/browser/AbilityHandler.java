package classes.browser;

import classes.database.DbLib;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * This class will handle the abilities that are selected in the browser.
 */
public class AbilityHandler {

    /**
     * All abilities have different names. This class will retrieve a standardized name
     * for the abilities.
     */
    public String getStandardAbilitySelected(HttpServletRequest request) {
        String ability = request.getParameter("basicAttack");
        if (ability != null) {
            return "basicAttack";
        }
        ability = request.getParameter("abilityOne");
        if (ability !=  null) {
            return "abilityOne";
        }
        ability = request.getParameter("abilityTwo");
        if (ability != null) {
            return "abilityTwo";
        }
        ability = request.getParameter("abilityThree");
        if (ability != null) {
            return "abilityThree";
        }
        System.out.println("Error in AbilityHandler - No ability found.");
        return null;
    }

    /**
     * This method will find the unique name of the ability that was used, and update the
     * "currentAbility" attribute in the database with this value.
     * @return - The name of the ability
     */
    public String updateCurrentAbility(HttpServletRequest request, PrintWriter out, String playerID) throws SQLException {
        DbLib db = new DbLib(out);

        String ability = request.getParameter("basicAttack");
        if (ability == null) {
            ability = request.getParameter("abilityOne");
        }
        if (ability ==  null) {
            ability = request.getParameter("abilityTwo");
        }
        if (ability == null) {
            ability = request.getParameter("abilityThree");
        }

        db.updateTable("player", "currentAbility", ability, "playerID", playerID);
        return ability;
    }
}

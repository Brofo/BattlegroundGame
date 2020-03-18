package classes.browser;

import javax.servlet.http.HttpServletRequest;

/**
 * This class will handle the abilities that are selected in the browser.
 */
public class AbilityHandler {

    /**
     * All abilities have different names. This class will retrieve a standardized name
     * for the abilities.
     */
    public String getAbilitySelected(HttpServletRequest request) {
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
}

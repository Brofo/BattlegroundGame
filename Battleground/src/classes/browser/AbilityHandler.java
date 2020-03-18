package classes.browser;

import javax.servlet.http.HttpServletRequest;

public class AbilityHandler {

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

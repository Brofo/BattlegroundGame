package classes.browser;

import javax.servlet.http.Cookie;

public class CookieFunctionality {

    /**
     * This method will return the value of the specified cookie.
     * @param cookies An array of all cookies that are stored in the client's browser.
     * @param cookieName Name of the cookie that will have its value retrieved.
     * @return
     */
    public String getValue(Cookie cookies[], String cookieName) {
        int i = 0;
        while (i < cookies.length) {
            String cookie = cookies[i].getName();
            if (cookie.equals(cookieName)) {
                return cookies[i].getValue();
            }
            i++;
        }
        System.err.println("Error in classes.browser.CookieHandler.java - Can not find value of Cookie.");
        return null;
    }
}

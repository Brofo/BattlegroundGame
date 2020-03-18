package classes.browser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieFunctionality {

    /**
     * This method will return the value of the specified cookie.
     * @param cookieName Name of the cookie that will have its value retrieved.
     * @return
     */
    public String getValue(HttpServletRequest request, String cookieName) {
        Cookie cookies[] = request.getCookies();
        int i = 0;
        while (i < cookies.length) {
            String cookie = cookies[i].getName();
            if (cookie.equals(cookieName)) {
                return cookies[i].getValue();
            }
            i++;
        }
        System.err.println("Error in classes.browser.CookieFunctionality.java - Can not find value of Cookie.");
        return null;
    }

    public Cookie[] replaceCookieInArray(Cookie cookies[], String cookieName, String cookieValue) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                cookie.setValue(cookieValue);
            }
        }
        return cookies;
    }

    public void deleteAllCookies(Cookie cookies[], HttpServletResponse response) {
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                Cookie overwriteCookie = new Cookie(cookie.getName(), "");
                overwriteCookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}

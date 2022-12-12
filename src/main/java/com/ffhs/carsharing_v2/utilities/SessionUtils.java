package com.ffhs.carsharing_v2.utilities;

import jakarta.faces.context.*;
import jakarta.servlet.http.*;

/**
 * Java utility class to get the Session and the session attribute "username"
 * which is created when the user logs in
 *
 * @author Aris M. Accola and Andreas Schwyter
 */
public class SessionUtils {
    public static HttpSession getSessionFalse() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpSession getSessionTrue() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static String getUsername() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
}
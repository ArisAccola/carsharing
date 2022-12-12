package com.ffhs.carsharing_v2.controllers;

import com.ffhs.carsharing_v2.utilities.SessionUtils;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

@Named
@SessionScoped
public class LogoutController implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * logout event, invalidate current session
     *
     * @return index.xhtml
     */
    public String logout() {
        HttpSession session = SessionUtils.getSessionFalse();
        session.invalidate();
        return "/index.xhtml?faces-redirect=true";
    }
}

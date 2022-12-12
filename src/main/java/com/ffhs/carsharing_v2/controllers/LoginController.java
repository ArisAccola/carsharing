package com.ffhs.carsharing_v2.controllers;

import com.ffhs.carsharing_v2.dto.User;
import com.ffhs.carsharing_v2.helpers.LoginHelper;
import com.ffhs.carsharing_v2.utilities.SessionUtils;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;


/**
 * Java Managed Bean for Login
 *
 * @author Aris M. Accola and Andreas Schwyter
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private int loginAttempts = 3;

    /**
     * Create local user object
     */
    User userDTO = new User();

    /**
     * Getter function for local user object
     *
     * @return user
     */
    public User getUserDTO() {
        return userDTO;
    }

    /**
     * Setter function for local user object
     *
     * @param user
     */
    public void setUserDTO(User user) {
        this.userDTO = user;
    }

    /**
     * Constructor without parameters
     */
    public LoginController() {
        super();
    }

    /**
     * Managed Bean for Login
     *
     * @param username the username entered on the index.xhtml
     * @param password the password entered on the index.xhtml
     * @return admin.xhtml if login successful and user is admin,
     * home.xhtml if login successfully or error message if login unsuccessful
     */
    public String login(String username, String password) {

        /**
         * Prevent Brut Force attack whilst limiting numbers to a maximum of 4 tries and if exceeded deactivate login
         * form whilst LoginHelper is no longer involved and no request is sent to the backend.
         */
        if(loginAttempts > 0) {

             /**
             * Involve Helper Function to check whether username exists and password matches with username
             * which returns true if user is found in the database and credential matches or false if username does
             * not exist or credentials do not match
             */
            boolean valid = LoginHelper.validateUserLogin(username, password);

            /**
             *  if credential matches and user is admin proceed to admin.xhtml
             *
             *  if credential matches and user is not admin proceed to home.xhtml
             *
             *  on error show error message and retry
             */
            if (valid) {
                HttpSession session = SessionUtils.getSessionFalse();
                /**
                 * Prevent Session Hijacking whilst invalidating current session and afterwards creating new session for
                 * new user login
                 */
                session.invalidate();
                session = SessionUtils.getSessionTrue();
                session.setAttribute("username", username);

                if (username.equals("admin")) {
                    return "/admin/admin.xhtml?faces-redirect=true";
                } else {
                    return "home.xhtml?faces-redirect=true";
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username or Password, remaining attempts " + loginAttempts, "Please enter correct Username and Password"));
                loginAttempts--;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login attempts have been exceeded and Login has been disabled", "To many login attempts have been recorded, login is blocked"));
        }
        return null;
    }
}


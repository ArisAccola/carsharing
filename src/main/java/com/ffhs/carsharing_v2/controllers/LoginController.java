package com.ffhs.carsharing_v2.controllers;

import java.io.*;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.ffhs.carsharing_v2.helpers.LoginHelper;
import com.ffhs.carsharing_v2.utilities.SessionUtils;


public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    private String message;

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username set new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password set new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message set new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * logout event, invalidate current session
     *
     * @return index.xhtml
     */
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
    }

    public String login() {
        boolean valid = LoginHelper.validateUserLogin(username, password);

        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", username);
            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Password", "Please enter correct Username and Password"));
            return "index";
        }
    }
}

/**
     *
     * Old version
     *
    private HttpSession session;
    private String url;
    private int loginAttempts;


    public LoginController(){
        super(); //Does nothing
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        // User has clicked the logout link
        session = request.getSession();

        //check to make sure we have clicked link
        if(request.getParameter("Logout") != null) {

            //logout and redirect to homepage
            logout();
            url=INDEX_HTML;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        //get our current session
        session = request.getSession();

        //get the numbers of logins
        if (session.getAttribute("loginAttempts") == null) {
            loginAttempts = 0;
        }

        //exceeded logins
        if (loginAttempts > 2) {

            String errorMessage = "Error: Number of Login Attemps Exceeded";
            request.setAttribute("errorMessage", errorMessage);
            url = INDEX_HTML;

        } else {

            //encrypt the password to check against what's stored in DB
            PasswordService psw = new PasswordService();
            String encryptedPass = psw.encrypt(password);

            System.out.println(username);
            System.out.println(password);
            System.out.println(encryptedPass);

            //create a user connect class to make databse calls, and call authentificate user method
            UserHelper uc = new UserHelper();
            User user = uc.authenticateUser(username, encryptedPass);
            //User user = uc.authenticateUser("Admin", "jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=");


            //we have found a user that matches the credentials
            if (user != null) {

                //invalidate current session, then get new session for our user (combat: sesson hijacking)
                session.invalidate();
                session = request.getSession(true);
                session.setAttribute("username", user);
                url = "userAccount.jsp";

            } else {

                //user doesn't exist, redirect to previous page and show error
                String errorMessage = "Error: Unrecognized Username or Password <br> Login attempts remaining: " + (3 - (loginAttempts));
                request.setAttribute("errorMessage", errorMessage);

                //track login attempts (combat: brute force attacks)
                session.setAttribute("loginAttempts", loginAttempts++);
                url = INDEX_HTML;
            }

            return;
        }

        //forward our request along
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
} */


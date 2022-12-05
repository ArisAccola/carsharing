package com.ffhs.carsharing_v2.dto;

/**
 * Java Representation of the User table in the database
 *
 * @author Aris M. Accola and Andreas Schwyter
 */

public class User {
    private int userId;
    private String username;
    private String password;


    /**
     * Constructor
     *
     * @param userId   the unique user id
     * @param username the unique username
     * @param password the password associated with the username
     */

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    /**
     * @return id
     */
    public int getId() {
        return userId;
    }

    /**
     * @param userId set new id
     */
    public void setId(int userId) {
        this.userId = userId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *
     * set new username
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
}

package com.ffhs.carsharing_v2.pojos;

/**
 * Java Representation of the User table in the database
 * 
 *  @author Aris M. Accola
 */

public class User {
    private int id;
    private String username;
    private String password;
    private String permission;

    /**
     * Constructor
     * @param id the unique user id
     * @param username the unique user's name
     * @param password the password associated with the account
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id set new id
     */
    public void setId(int id)
    {
        this.id = id;
    }

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
}

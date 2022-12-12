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
     * Constructor of object User without parameters
     */
    public User() {
        super();
    }

    /**
     * Constructor of object User with parameters
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

    /**
     * Getter Function for the user id
     *
     * @return the userId of User
     */
    public int getId() {
        return userId;
    }

    /**
     * Setter function for the user id
     *
     * @param userId the unique userId associated to the User
     */
    public void setId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter function for the username
     *
     * @return username of User
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter function for the username
     *
     * @param username the unique username associated to the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter Function for the password
     *
     * @return password of the User
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter Function for the password
     *
     * @param password the password associated to the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

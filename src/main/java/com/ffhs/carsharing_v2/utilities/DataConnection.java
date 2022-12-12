package com.ffhs.carsharing_v2.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Java utility class to connect to database and close the Connection with the database and close
 * PreparedStatement und ResultSet used for database queries
 *
 * @author Aris M. Accola and Andreas Schwyter
 */
public class DataConnection {
    /**
     * @return connection or null
     *
     * Create Connection with Database
     */
    public static Connection getConnection() {
        try {
            // Set up connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing", "root", "root");

            //Test if Connection has been established
            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Failed to make connection");
            }
            //return connection
            return connection;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /**
     * Function to close connection which is used for connection to the database
     *
     * @param connection Close Connection to Database
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    /**
     * Function to close PreparedStatement which is used for database query
     *
     * @param statement PreparedStatement used for database query
     */
    public static void close(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    /**
     * Function to close ResultSet which is used for database query
     *
     * @param rs Close ResultSet used for Database Fetching
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }
}

package com.ffhs.carsharing_v2.utilities;

import java.sql.*;
public class DataConnection
{
    public static Connection getConnection()
    {
        try {
            // Set up connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing", "root", "root");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static void close(Connection connection)
    {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

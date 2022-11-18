package com.ffhs.carsharing_v2.helpers;

import java.sql.*;

import com.ffhs.carsharing_v2.utilities.DataConnection;

public class LoginHelper {


    public static boolean validateUserLogin(String username, String password)
    {
        PreparedStatement authenticateUserStatement = null;
        Connection connection = null;

        try{
            connection = DataConnection.getConnection();
            authenticateUserStatement = connection.prepareStatement("select * from users where username=? and password=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            authenticateUserStatement.setString(1,username);
            authenticateUserStatement.setString(2, password);

            ResultSet rs = authenticateUserStatement.executeQuery();

            //User with Username and Password found
            if(rs.next()){
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
        }
        return false;
    }
}

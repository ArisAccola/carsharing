package com.ffhs.carsharing_v2.helpers;

import java.sql.*;

import com.ffhs.carsharing_v2.utilities.DataConnection;

/**
 * Helper to assist LoginController performing the validation of the User Login
 */
public class LoginHelper {

    /**
     * @param username username input from Login Form
     * @param password password input from Login Form
     * @return true --> if user with matching username and password is found
     *         false --> if user is not found or the username and password do not match
     */
    public static boolean validateUserLogin(String username, String password)
    {
        PreparedStatement authenticateUserStatement = null;
        Connection connection = null;
        ResultSet rs = null;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            authenticateUserStatement = connection.prepareStatement("select * from users where username=? and password=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            authenticateUserStatement.setString(1,username);
            authenticateUserStatement.setString(2, password);

            rs = authenticateUserStatement.executeQuery();

            //User with Username and Password found
            if(rs.next()){
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
            DataConnection.close(rs);
            DataConnection.close(authenticateUserStatement);
        }
        return false;
    }
}

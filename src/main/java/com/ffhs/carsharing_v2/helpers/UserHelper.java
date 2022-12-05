package com.ffhs.carsharing_v2.helpers;

import com.ffhs.carsharing_v2.dto.User;
import com.ffhs.carsharing_v2.utilities.DataConnection;
import jakarta.faces.context.FacesContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserHelper {

    private static UserHelper instance;

    public static UserHelper getInstance() throws Exception {

        try{
            if (instance == null) {
            instance = new UserHelper();
            }
            return instance;
        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }


    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement userStatement = null;

        try {
            connection = DataConnection.getConnection();
            assert connection != null;
            userStatement = connection.prepareStatement("select * from users");
            ResultSet rs = userStatement.executeQuery();

            while(rs.next()) {
                int userId = rs.getInt("userId");
                String username = rs.getString("username");
                String password = rs.getString("password");

                //System.out.println(userId + " " + username + " " + password);

                User user = new User(userId, username, password);
                users.add(user);
            }
            return users;
        }
        catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        finally {
            DataConnection.close(connection);
            DataConnection.close(userStatement);
        }
    }

    public static boolean createUser(String username, String password) {
        PreparedStatement createUserStatement = null;
        Connection connection = null;
        int rs = 0;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            createUserStatement= connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");

            // Add parameters to the ?'s in the prepareStatement and execute
            createUserStatement.setString(1,username);
            createUserStatement.setString(2, password);

            System.out.println(createUserStatement);

            rs =  createUserStatement.executeUpdate();

            //User with Username and Password found
            if(rs == 1) {
                System.out.println("User successfully created");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
            DataConnection.close(createUserStatement);
        }
        return false;
    }

    public static boolean deleteUser(int userId){
        PreparedStatement deleteUserStatement = null;
        Connection connection = null;
        int rs = 0;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            deleteUserStatement= connection.prepareStatement("DELETE FROM users WHERE userId=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            deleteUserStatement.setInt(1,userId);

            System.out.println(deleteUserStatement);

            rs =  deleteUserStatement.executeUpdate();

            //User with Username and Password found
            if(rs == 1) {
                System.out.println("User successfully deleted");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
            DataConnection.close(deleteUserStatement);
        }
        return false;
    }

    public static void loadEditUser(int editUserId){

        User editUser = null;
        Connection connection = null;
        PreparedStatement editUserStatement = null;

        try {
            connection = DataConnection.getConnection();
            assert connection != null;
            editUserStatement = connection.prepareStatement("SELECT * FROM users WHERE userId=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            editUserStatement.setInt(1,editUserId);

            ResultSet rs = editUserStatement.executeQuery();

            editUser = new User();

            rs.next();
            editUser.setId(rs.getInt("userId"));
            editUser.setUsername(rs.getString("username"));
            editUser.setPassword(rs.getString("password"));

            System.out.println(editUser.getUsername());

            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("editUser", editUser);
        }
        catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        finally {
            DataConnection.close(connection);
            DataConnection.close(editUserStatement);
        }
    }

    public static boolean updateUser(int userId, String username, String password){
        PreparedStatement updateUserStatement = null;
        Connection connection = null;
        int rs = 0;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            updateUserStatement= connection.prepareStatement("UPDATE users SET username=?, password=? WHERE userId=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            updateUserStatement.setString(1, username);
            updateUserStatement.setString(2, password);
            updateUserStatement.setInt(3,userId);

            System.out.println(updateUserStatement);

            rs =  updateUserStatement.executeUpdate();

            //User with Username and Password found
            if(rs == 1) {
                System.out.println("User successfully updated");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
            DataConnection.close(updateUserStatement);
        }
        return false;
    }
}

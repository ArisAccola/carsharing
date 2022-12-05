package com.ffhs.carsharing_v2.controllers;

import com.ffhs.carsharing_v2.helpers.UserHelper;
import com.ffhs.carsharing_v2.dto.User;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public UserController() {
        super();
    }

    User userDTO = new User();

    public User getUserDTO(){
        return userDTO;
    }

    public void setUserDTO(User user) {
        this.userDTO = user;
    }

    public String addUserForward(){
        return "addUser";
    }

    /**
     * Get list of Cars from Database
     *
     * @return List<User> users or null if error
     */
    public List<User> getUser() {
        try {
            return UserHelper.loadUsers();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public String addUser(String username, String password){

        boolean valid = UserHelper.createUser(username, password);

        if(valid)
        {
            return "users";
        }
        else {
           return "error";
        }
    }

    public String editUser(int userId){

        try {
            UserHelper.loadEditUser(userId);
            return "editUser?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

   public String updateUser(int userId, String username, String password){

        boolean valid = UserHelper.updateUser(userId, username, password);

        if(valid)
        {
            return "users";
        }
        else {
            return "error";
        }
    }

    public String deleteUser(int userId) {

        boolean valid = UserHelper.deleteUser(userId);

        if(valid)
        {
            return "users";
        }
        else {
            return "error";
        }
    }

}



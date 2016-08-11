package com.github.beisser.controller;

import com.github.beisser.model.User;
import com.github.beisser.model.UserDAO;
import com.github.beisser.util.AppUtils;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Nico on 08.08.2016.
 */
@Named
@SessionScoped
public class UserController implements Serializable {

    private List<User> users;
    private UserDAO userDAO;
    private Logger logger = Logger.getLogger(getClass().getName());

    public UserController() throws Exception {
        users = new ArrayList<User>();
        userDAO = UserDAO.getInstance();
    }

    public void loadUsers() {

        users.clear();

        try {

            // get all users from database
            users = userDAO.getUsers();

        } catch (Exception exc) {
            // send FacesMessage to next page to display errors
            AppUtils.addErrorMessage(exc);
        }
    }

    public String addUser(User user) {
        try {
            userDAO.addUser(user);
        } catch(Exception e) {
            AppUtils.addErrorMessage(e);
        }
        return "users";
    }

    // fetches the object, adding it to the request map, send it to updateUserForm
    public String loadUser(int id) {
        try {
            User user = userDAO.getUser(id);

            // helper to add data to memory
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            // add data to request
            Map<String, Object> requestMap = externalContext.getRequestMap();
            requestMap.put("user", user);
        } catch(Exception e) {
            AppUtils.addErrorMessage(e);
        }
        return "updateUserForm";
    }

    public String updateUser(User user) {
        try {
            userDAO.updateUser(user);
        } catch(Exception e) {
            AppUtils.addErrorMessage(e);
        }
        return "users";
    }

    public String deleteUser(int id) {
        try {
            userDAO.deleteUser(id);
        } catch(Exception e) {
            AppUtils.addErrorMessage(e);
        }
        return "users";
    }

    public String goTo() {
        return true ? "success" : "fail";
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}

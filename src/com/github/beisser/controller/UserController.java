package com.github.beisser.controller;

import com.github.beisser.model.User;
import com.github.beisser.model.UserDAO;
import com.github.beisser.util.AppUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Nico on 08.08.2016.
 */
@ManagedBean
@SessionScoped
public class UserController {

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}

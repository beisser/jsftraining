package com.github.beisser.controller;

import com.github.beisser.model.User;
import com.github.beisser.service.UserService;
import com.github.beisser.util.AppUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Nico on 08.08.2016.
 */
@Named
@SessionScoped
public class UserController implements Serializable {

    private List<User> users;
    private User user = new User();

    @Inject
    private UserService userService;
    private Logger logger = Logger.getLogger(getClass().getName());

    public UserController() throws Exception {
        users = new ArrayList<User>();
//        userService = UserDAO.getInstance();
    }

    public void loadUsers() {

        users.clear();

        try {

            // get all users from database
            users = userService.findAll();

        } catch (Exception exc) {
            // send FacesMessage to next page to display errors
            AppUtils.addErrorMessage(exc);
        }
    }

    public String save(User user) {
        String test = "da";
        try {
            userService.save(user);
        } catch(Exception e) {
            AppUtils.addErrorMessage(e);
        }
        return "users";
    }

    // fetches the object, adding it to the request map, send it to updateUserForm
    public String loadUser(int id) {
        try {
            User currentUser = userService.findById(id);

            // helper to add data to memory
//            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            // add data to request
//            Map<String, Object> requestMap = externalContext.getRequestMap();
//            requestMap.put("user", currentUser);
            user = currentUser;
        } catch(Exception e) {
            AppUtils.addErrorMessage(e);
        }
        return "updateUserForm";
    }

    public String deleteUser(int id) {
        try {
            userService.delete(id);
        } catch(Exception e) {
            AppUtils.addErrorMessage(e);
        }
        return "users";
    }

    public void resetUser(){
        if (this.user != null) {
            this.user = new User();
        }
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

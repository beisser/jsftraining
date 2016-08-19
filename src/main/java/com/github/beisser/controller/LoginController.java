package com.github.beisser.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Nico on 18.08.2016.
 */
@Named
@SessionScoped
public class LoginController implements Serializable{
    private String username;
    private String password;

    public String logIn() {
        return "users.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.github.beisser.controller;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Nico on 29.08.2016.
 */
@Named
@SessionScoped
public class AjaxController implements Serializable{

    private String firstName,lastName;
    private Boolean displayed = true;

    public void displayedChanged(ValueChangeEvent e) {
        Boolean value = (Boolean) e.getNewValue();

        if(value != null) {
            this.displayed = value;
        }
    }

    public Boolean getDisplayed() {
        return displayed;
    }

    public void setDisplayed(Boolean displayed) {
        this.displayed = displayed;
    }

    public String getFullName() {
        return firstName + "" + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

package com.github.beisser.model;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Nico on 11.08.2016.
 */
//@Named
//@FlowScoped(value="wizard")
public class Wizard implements Serializable{

    private String firstName;
    private String lastName;

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

    public String complete() {
        return "index.xthml?faces-redirect=true";
    }
}

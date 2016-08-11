package com.github.beisser.model;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by Nico on 10.08.2016.
 */
@Named
@RequestScoped
public class ViewParameterContainer {
    private String searchFor;

    public String getSearchFor() {
        return searchFor;
    }

    public void setSearchFor(String searchFor) {
        this.searchFor = searchFor;
    }
}

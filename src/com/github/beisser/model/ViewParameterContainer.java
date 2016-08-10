package com.github.beisser.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Nico on 10.08.2016.
 */
@ManagedBean
public class ViewParameterContainer {
    private String searchFor;

    public String getSearchFor() {
        return searchFor;
    }

    public void setSearchFor(String searchFor) {
        this.searchFor = searchFor;
    }
}

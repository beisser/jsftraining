package com.github.beisser.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Created by Nico on 10.08.2016.
 */
@ManagedBean
@RequestScoped
public class NavigationController {

    public String returnJsfVersion() {
        return FacesContext.class.getPackage().getImplementationVersion();
    }

    public String processIndex() {
        return "index.xthml?faces-redirect=true";
    }

    public String processUsers() {
        return "users.xhtml?faces-redirect=true";
    }

    public String processViewParameters() {
        return "viewParameters.xhtml?faces-redirect=true&amp;searchFor=google";
    }
}

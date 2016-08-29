package com.github.beisser.controller;


import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Created by Nico on 10.08.2016.
 */
@Named
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

    public String processBasicProtected() {
        return "basic/index.xthml?faces-redirect=true";
    }

    public String processWizard() {
        return "wizard";
    }
}

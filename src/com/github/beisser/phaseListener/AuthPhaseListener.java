package com.github.beisser.phaseListener;

import com.github.beisser.model.User;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 * Created by Nico on 10.08.2016.
 */
public class AuthPhaseListener implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        FacesContext facesContext = phaseEvent.getFacesContext();
        String currentRoute = facesContext.getViewRoot().getViewId();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        User user = (User) session.getAttribute("currentUser");

        if (user == null && !currentRoute.endsWith("index.xhtml")) {
            NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

            navigationHandler.handleNavigation(facesContext,null,"/index.xhml?faces-redirect=true");
        }

    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {

    }

    @Override
    public PhaseId getPhaseId() {
        // which phase
        return PhaseId.RESTORE_VIEW;
    }
}

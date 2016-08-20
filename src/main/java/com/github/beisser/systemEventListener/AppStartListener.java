package com.github.beisser.systemEventListener;

import com.github.beisser.service.DbServiceJPA;

import javax.el.ELContext;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import java.util.logging.Logger;

/**
 * Created by Nico on 10.08.2016.
 */
public class AppStartListener implements SystemEventListener{
    @Override
    public void processEvent(SystemEvent systemEvent) throws AbortProcessingException {

        Logger logger = Logger.getLogger(getClass().getName());

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        DbServiceJPA bean = (DbServiceJPA) elContext.getELResolver().getValue(elContext,null,"dbServiceJPA");

        // which event should be handled
        if (systemEvent instanceof PostConstructApplicationEvent) {
            logger.info("JUST STARTED");
        } else if (systemEvent instanceof PreDestroyApplicationEvent) {
            bean.closeEm();
            logger.info("TO BE DESTROYED");
        }
    }

    @Override
    public boolean isListenerForSource(Object o) {
        return o instanceof Application;
    }
}

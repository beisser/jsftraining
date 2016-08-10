package com.github.beisser.exception;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;

/**
 * Created by Nico on 10.08.2016.
 */
public class AppExceptionHandler extends ExceptionHandlerWrapper{

    private ExceptionHandler wrapped;

    public AppExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator iterator = getHandledExceptionQueuedEvents().iterator();

        while(iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent)iterator.next();
            ExceptionQueuedEventContext context = event.getContext();

            Throwable throwable = context.getException();

            try {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
                navigationHandler.handleNavigation(facesContext,null,"/error?faces-redirect=true");
                facesContext.renderResponse();
            }
            finally {
                iterator.remove();
            }
        }

        getWrapped().handle();
    }
}

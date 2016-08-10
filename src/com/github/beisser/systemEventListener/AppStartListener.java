package com.github.beisser.systemEventListener;

import javax.faces.application.Application;
import javax.faces.event.*;
import java.util.logging.Logger;

/**
 * Created by Nico on 10.08.2016.
 */
public class AppStartListener implements SystemEventListener{
    @Override
    public void processEvent(SystemEvent systemEvent) throws AbortProcessingException {

        Logger logger = Logger.getLogger(getClass().getName());

        // which event should be handled
        if (systemEvent instanceof PostConstructApplicationEvent) {
            logger.info("JUST STARTED");
        } else if (systemEvent instanceof PreDestroyApplicationEvent) {
            logger.info("TO BE DESTROYED");
        }
    }

    @Override
    public boolean isListenerForSource(Object o) {
        return o instanceof Application;
    }
}

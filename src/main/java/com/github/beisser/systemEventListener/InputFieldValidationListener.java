package com.github.beisser.systemEventListener;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 * Created by Nico on 10.08.2016.
 */
public class InputFieldValidationListener implements SystemEventListener {

    @Override
    public void processEvent(SystemEvent systemEvent) throws AbortProcessingException {
        UIInput input = (UIInput)systemEvent.getSource();

        if (input instanceof EditableValueHolder) {
            if (!input.isValid()) {
                input.getAttributes().put("style","border-color:red");
            } else {
                input.getAttributes().put("style","border-color:#ccc");
            }
        }
    }

    @Override
    public boolean isListenerForSource(Object o) {
        return o instanceof UIInput;
    }
}

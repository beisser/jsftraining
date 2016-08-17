package com.github.beisser.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Nico on 10.08.2016.
 */
@FacesValidator("com.github.beisser.validator.UpperCaseValidator")
public class UpperCaseValidator implements Validator{

    private String alternativeMessage;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) {
            return;
        }

        String data = o.toString();
        String validatorMessage = alternativeMessage != null ? alternativeMessage : "Alles muss groß geschrieben werden";

        if (!data.equals(data.toUpperCase())) {
            FacesMessage message = new FacesMessage(validatorMessage);
            throw new ValidatorException(message);
        }
    }

    public String getAlternativeMessage() {
        return alternativeMessage;
    }

    public void setAlternativeMessage(String alternativeMessage) {
        this.alternativeMessage = alternativeMessage;
    }
}

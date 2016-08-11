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
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) {
            return;
        }

        String data = o.toString();

        if (!data.equals(data.toUpperCase())) {
            FacesMessage message = new FacesMessage("Alles muss groß geschrieben werden");
            throw new ValidatorException(message);
        }
    }
}

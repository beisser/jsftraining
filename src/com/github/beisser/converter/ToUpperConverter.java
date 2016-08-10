package com.github.beisser.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Nico on 10.08.2016.
 */
@FacesConverter("com.github.beisser.converter.ToUpperConverter")
public class ToUpperConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return s.toUpperCase();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String value = (String) o;
        return value.toLowerCase();
    }
}

package com.github.beisser.gui.jsf.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Nico on 21.08.2016.
 */
@FacesComponent("com.github.beisser.gui.jsf.component.ToUpperRenderer")
public class ToUpperRenderer extends UIComponentBase {

    public static final String COMPONENT_TYPE = "com.github.beisser.gui.jsf.component.ToUpperRenderer";

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter responseWriter = context.getResponseWriter();
        Map<String,Object> attrs = this.getAttributes();
        String styleClass = (String)attrs.get("styleClass");
        Object value = attrs.get("value");

        responseWriter.startElement("p",this);
            responseWriter.writeAttribute("id",this.getClientId(),"id");

            if (styleClass != null) {
                responseWriter.writeAttribute("class", styleClass, "class");
            }

            if (value != null) {
                responseWriter.write(value.toString().toUpperCase());
            }
        responseWriter.endElement("p");

    }

    @Override
    public String getFamily() {
        return COMPONENT_TYPE;
    }
}

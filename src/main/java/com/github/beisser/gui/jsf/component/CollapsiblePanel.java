package com.github.beisser.gui.jsf.component;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Created by Nico on 28.08.2016.
 */
@FacesComponent("com.github.beisser.gui.jsf.component.CollapsiblePanel")
public class CollapsiblePanel extends UINamingContainer{

    enum PropertyKeys{collapsed}

    public Boolean isCollapsed() {
        return (Boolean)getStateHelper().eval(
                PropertyKeys.collapsed,Boolean.FALSE
        );
    }

    public void setCollapsed(boolean collapsed) {
        getStateHelper().put(PropertyKeys.collapsed,collapsed);
    }

    public void toogle(ActionEvent e) {
        setCollapsed(!isCollapsed());
        _syncCollapsedValueExpression();
    }

    /**
     * if user assigned a value expression to collapsed attribute the
     * current value has to be written back
     */
    private void _syncCollapsedValueExpression() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        ValueExpression valueExpression = getValueExpression(PropertyKeys.collapsed.name());

        if (valueExpression != null) {
            valueExpression.setValue(elContext,isCollapsed());
        }
    }
}

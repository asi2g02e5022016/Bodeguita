/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.renderer;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.context.RequestContext;
import org.primefaces.util.HTML;

/**
 *
 * @author luis_portillo
 */
public class CalendarRenderer extends org.primefaces.component.calendar.CalendarRenderer {
    
    @Override
    protected void encodeInput(FacesContext context, Calendar calendar, String id, String value, boolean popup)
    throws IOException
  {
    ResponseWriter writer = context.getResponseWriter();
    String type = popup ? "text" : "hidden";
    String labelledBy = calendar.getLabelledBy();
    
    writer.startElement("input", null);
    writer.writeAttribute("id", id, null);
    writer.writeAttribute("name", id, null);
    writer.writeAttribute("type", type, null);
    if (calendar.isRequired()) {
      writer.writeAttribute("aria-required", "true", null);
    }
    if (!isValueBlank(value)) {
      writer.writeAttribute("value", value, null);
    }
    if (popup)
    {
     String inputStyleClass = "ui-inputfield ui-widget ui-state-default ui-corner-all";
      if(calendar.isRequired() && !calendar.isDisabled() ){
        inputStyleClass = inputStyleClass + " requiredInput";
      }
        
      if (calendar.isDisabled()) {
        inputStyleClass = inputStyleClass + " ui-state-disabled";
      }
      if (!calendar.isValid()) {
        inputStyleClass = inputStyleClass + " ui-state-error";
      }
      writer.writeAttribute("class", inputStyleClass, null);
      if ((calendar.isReadonly()) || (calendar.isReadonlyInput())) {
        writer.writeAttribute("readonly", "readonly", null);
      }
      if (calendar.isDisabled()) {
        writer.writeAttribute("disabled", "disabled", null);
      }
      renderPassThruAttributes(context, calendar, HTML.INPUT_TEXT_ATTRS_WITHOUT_EVENTS);
      renderDomEvents(context, calendar, HTML.INPUT_TEXT_EVENTS);
    }
    if (labelledBy != null) {
      writer.writeAttribute("aria-labelledby", labelledBy, null);
    }
    if (RequestContext.getCurrentInstance().getApplicationContext().getConfig().isClientSideValidationEnabled()) {
      renderValidationMetadata(context, calendar);
    }
    writer.endElement("input");
  }

    
}

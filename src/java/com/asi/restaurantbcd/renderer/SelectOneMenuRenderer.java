/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.renderer;

import java.io.IOException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.SelectItem;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author luis_portillo
 */
public class SelectOneMenuRenderer extends org.primefaces.component.selectonemenu.SelectOneMenuRenderer {
    

    @Override
    protected void encodeLabel(FacesContext context, SelectOneMenu menu, List<SelectItem> selectItems)
    throws IOException
  {
    ResponseWriter writer = context.getResponseWriter();
    String valueToRender = org.primefaces.util.ComponentUtils.getValueToRender(context, menu);
    if (menu.isEditable())
    {
      writer.startElement("input", null);
      writer.writeAttribute("type", "text", null);
      writer.writeAttribute("name", menu.getClientId(context) + "_editableInput", null);
      if(menu.isRequired() && !menu.isDisabled()){
        writer.writeAttribute("class", "ui-selectonemenu-label ui-inputfield ui-corner-all requiredInput", null);
      }
      else {
        writer.writeAttribute("class", "ui-selectonemenu-label ui-inputfield ui-corner-all", null);
      }
      
      if (menu.getTabindex() != null) {
        writer.writeAttribute("tabindex", menu.getTabindex(), null);
      }
      if (menu.isDisabled()) {
        writer.writeAttribute("disabled", "disabled", null);
      }
      if (valueToRender != null) {
        writer.writeAttribute("value", valueToRender, null);
      }
      if (menu.getMaxlength() != Integer.MAX_VALUE) {
        writer.writeAttribute("maxlength", Integer.valueOf(menu.getMaxlength()), null);
      }
      writer.endElement("input");
    }
    else
    {
      writer.startElement("label", null);
      writer.writeAttribute("id", menu.getClientId(context) + "_labelid", null);
      if(menu.isRequired() && !menu.isDisabled()){
        writer.writeAttribute("class", "ui-selectonemenu-label ui-inputfield ui-corner-all requiredInput", null);
      }
      else {
        writer.writeAttribute("class", "ui-selectonemenu-label ui-inputfield ui-corner-all", null);
      }
      writer.write("&nbsp;");
      writer.endElement("label");
    }
  }
    
}

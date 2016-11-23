/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.renderer;

import java.io.IOException;
import javax.faces.context.FacesContext;
import org.primefaces.component.inputnumber.InputNumber;

/**
 *
 * @author luis_portillo
 */
public class InputNumberRenderer extends org.primefaces.component.inputnumber.InputNumberRenderer {
    
    @Override
    protected void encodeOutput(FacesContext context, InputNumber inputNumber, String clientId)
    throws IOException
  {
      String stly = inputNumber.getInputStyleClass();
      if(inputNumber.isRequired() && !inputNumber.isDisabled() ){
        stly = stly + " requiredInput"
                ;
      }
     inputNumber.setInputStyleClass(stly);
     super.encodeOutput(context, inputNumber,  clientId);
     
  }
}

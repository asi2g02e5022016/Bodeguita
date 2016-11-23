/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.renderer;

import org.primefaces.component.inputtext.InputText;


/**
 *
 * @author luis_portillo
 */
public class InputTextRenderer extends org.primefaces.component.inputtext.InputTextRenderer {
    
    @Override
    protected String createStyleClass(InputText inputText) {
      String stly = super.createStyleClass(inputText);
      if(inputText.isRequired() && !inputText.isDisabled() ){
        stly = stly + " requiredInput"
                ;
      }
      return stly;
    }
    
}

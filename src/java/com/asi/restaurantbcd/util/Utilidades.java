/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.util;

import javax.faces.context.FacesContext;

/**
 *
 * @author samaelopez
 */
public class Utilidades {
        public static <T> T findBean(String beanName) {
    FacesContext context = FacesContext.getCurrentInstance();
    return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
}
    
}

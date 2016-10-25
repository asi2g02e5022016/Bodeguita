/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author samaelopez
 */
public class Utilidades {
    /**
     * Metodo general para mandar a llamar a un managet beans
     * @param <T> Retorna un metodo generico.
     * @param beanName Benas nombre de sesion.
     * @return 
     */
     public static <T> T findBean(String beanName) {
    FacesContext context = FacesContext.getCurrentInstance();
    return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
}
    /**
     * Metodo para obtener el valor de un Map.
     * @param <T> Clase Generico  de java.
     * @param object Objeto Key de el Map;
     * @param filtros Map con filtros.
     * @return Objeto generico.
     */
    public static < T> T getParemetro(Object object, Map filtros) {
        if (filtros == null || object == null) {
            return null;
        }
        return (T) filtros.get(object);
    } 
}

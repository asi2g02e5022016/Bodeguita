/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.job;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author siman
 */
@ManagedBean(name = "jobController")
@ViewScoped
public class JobControllerMB implements Serializable{
   
    TareaEJBLocal procesaFacturasEJB = lookupprocesaFacturasEJBLocal();
     private TareaEJBLocal lookupprocesaFacturasEJBLocal() {
        try {
            Context c = new InitialContext();
            return (TareaEJBLocal) c.lookup("java:global/RestaurantBDC//procesaFacturasEJB!com.asi.restaurantebcd.job.TareaEJBLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    public void procesarFacturas(){
       procesaFacturasEJB.process(null);
    }
}

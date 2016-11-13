/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Facturainterface;
import javax.ejb.Local;
import javax.naming.NamingException;
import javax.transaction.SystemException;

/**
 *
 * @author siman
 */
@Local
public interface CrearFacturaEJBLocal {
   
    public void procesarFactura(Facturainterface f) throws IllegalStateException, SecurityException, SystemException, NamingException;
    
}

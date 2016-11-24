/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Caja;
import com.asi.restaurantbcd.modelo.Facturainterface;
import com.asi.restaurantbcd.modelo.Numerofiscal;
import com.asi.restaurantbcd.modelo.Ordenpedido;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.List;
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
   
    public void procesarFactura(Ordenpedido p, String serie, Integer numeroDocu, Integer idCaja) throws IllegalStateException, SecurityException, SystemException, NamingException;
    
    public List<Numerofiscal> numeroFiscalList(Sucursal s) throws IllegalStateException, SecurityException, SystemException, NamingException;
    
    public List<Numerofiscal> numeroFiscalList(Sucursal s, Integer tipoDocumento) throws IllegalStateException, SecurityException, SystemException, NamingException;
    
    public List<Caja> cajaList(Sucursal s) throws IllegalStateException, SecurityException, SystemException, NamingException;
}

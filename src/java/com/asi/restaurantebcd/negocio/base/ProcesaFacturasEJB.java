/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantebcd.negocio.base.CrearFacturaEJBLocal;
import com.asi.restaurantbcd.modelo.Facturainterface;
import com.asi.restaurantebcd.job.TareaEJBLocal;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;

/**
 *
 * @author siman
 */
@Stateless(name = "/procesaFacturasEJB")
public class ProcesaFacturasEJB implements TareaEJBLocal {

     @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
     
     @EJB
     CrearFacturaEJBLocal creaFacturaEJB;       
    
     List<Facturainterface> encabezados;
            
    public void process(Map<String,String> args)  {
        System.out.println("Entro.. facturas");
        encabezados = em.createQuery("select f from Facturainterface f where f.cargada = false").getResultList();
        System.out.println("encabezados..");
        for(Facturainterface f:encabezados){
            try {
                creaFacturaEJB.procesarFactura(f);
            } catch (IllegalStateException ex) {
                Logger.getLogger(ProcesaFacturasEJB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(ProcesaFacturasEJB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(ProcesaFacturasEJB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(ProcesaFacturasEJB.class.getName()).log(Level.SEVERE, null, ex);
            }
    } }
  

  }

    


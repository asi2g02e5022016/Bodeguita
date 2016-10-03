/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Compra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Samael lopez
 */
@Stateless
public class BusquedasCompras implements BusquedasComprasLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 
    
    /**
     * Obtener el ultimovalor de la compra.
     * @return Integrt.
     * @throws Exception  Error gnerico.
     */
    @Override
    public Integer obtenerCorreltivoCompra() throws Exception {
        Integer valor;
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idcompra) FROM Compra ");
        
        Query query = em.createNativeQuery(slq.toString());
        valor = (Integer) query.getSingleResult();
        if (valor == null) {
            valor = Integer.parseInt("1");
        }
        valor   =  valor + 1;
        return valor;
    }
}

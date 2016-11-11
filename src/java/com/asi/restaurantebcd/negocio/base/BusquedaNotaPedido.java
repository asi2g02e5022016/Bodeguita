/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author siman
 */
@Stateless
public class BusquedaNotaPedido implements BusquedaNotaPedidoLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 
        
    @Override
    public Integer obtenerCorreltivoPedido(Integer idSucursal) throws Exception {
        
        Integer valor;
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idnotapedido) FROM notapedido ");
        slq.append(" WHERE idsucursal = ");
        slq.append(idSucursal.toString());
        Query query = em.createNativeQuery(slq.toString());
        valor = (Integer) query.getSingleResult();
        if (valor == null) {
            valor = Integer.parseInt("0");
        }
        valor   =  valor + 1;
        return valor;
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    

}

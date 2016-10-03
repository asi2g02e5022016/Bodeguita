/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Producto;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author samaelopez
 */
@Stateless
public class BusquedasProductos implements BusquedasProductosLocal {

     @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 
    /**
     * 
     * @param map
     * @return
     * @throws Exception 
     */
    public List<Producto> buscarProductos(Map map) throws Exception {
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT p FROM Producto p ");
        slq.append(" WHERE p.producto = :producto ");
        
        Query query = em.createQuery(slq.toString());
        query.setParameter("producto", map.get("producto").toString());
        return query.getResultList();
        
    }
}

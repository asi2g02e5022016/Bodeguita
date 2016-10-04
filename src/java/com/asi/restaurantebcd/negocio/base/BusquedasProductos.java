/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Producto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
     @Override
    public List<Producto> buscarProductos(Map map) throws Exception {
        if (map == null) {
            map = new HashMap();
        }
        StringBuilder slq = new StringBuilder();
        try {
        slq.append("SELECT p FROM Producto p ");
         slq.append(" WHERE 1 = 1");
        if (map.containsKey("producto") 
                && map.get("producto") != null
                && !map.get("producto").equals("")) {
        slq.append(" AND p.producto = :producto ");
        }
            System.out.println("slq.toString().." +slq.toString());
        Query query = em.createQuery(slq.toString());
          if (map.containsKey("producto") && map.get("producto") != null
                  && !map.get("producto").equals("")) {
           query.setParameter("producto", map.get("producto").toString());
        }
            System.out.println("query.getResultList();.." + query.getResultList());
        return query.getResultList();
        } catch (NoResultException nr) {
            return null; 
        } catch (Exception e) {
            throw e;
            }
        
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Proveedor;
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
public class BusquedasProveedores implements BusquedasProveedoresLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    /**
     * Busca proveedores por medio de un mapa.
     * 
     * @param filtroMap
     * @return 
     */
    @Override
    public List <Proveedor> buscarProveedors(Map filtroMap) {
        if (filtroMap == null) {
            return null;
        }
        try {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT o FROM Proveedor o WHERE 1 = 1 ");
        if (filtroMap.containsKey("nombre") 
                && filtroMap.get("nombre") != null) {
            jpql.append(" AND UPPER(o.proveedor) like :nombre ");
            
        }
        Query query = em.createQuery(jpql.toString());
                if (filtroMap.containsKey("nombre") 
                && filtroMap.get("nombre") != null) {
         query.setParameter("nombre", "%" 
                 + filtroMap.get("nombre").toString().toUpperCase() + "%");
        }
        
        
        return null;
        } catch(NoResultException nre) {
            return null;
        } catch (Exception e) {
            throw e;
        }
        
    }

    
}

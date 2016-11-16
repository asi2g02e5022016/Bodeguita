/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Notapedido;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author siman
 */
@Stateless
public class NotaPedidoEJB implements NotaPedidoEJBLocal {
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    
      @Override
    public List<Sucursal> buscarSucursalOrigen(Map filtroMap) throws Exception {
         
        try {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT o FROM Sucursal o WHERE 1 = 1 ");
        if (filtroMap.containsKey("sucursal") 
                && filtroMap.get("sucursal") != null) {
            jpql.append(" AND UPPER(o.sucursal) NOT LIKE UPPER(CONCAT('%',:sucursal,'%'))");
        }
        Query query = em.createQuery(jpql.toString());
                if (filtroMap.containsKey("sucursal") 
                && filtroMap.get("sucursal") != null) {
          query.setParameter("sucursal", filtroMap.get("sucursal").toString());
        }
        
        
        return  query.getResultList();
        } catch(NoResultException nre) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Notapedido> buscarNotasPedido(Map filtroMap) throws Exception {
         try {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT o FROM Notapedido o WHERE 1 = 1 ");
        if (filtroMap.containsKey("sucursal") 
                && filtroMap.get("sucursal") != null) {
            jpql.append(" AND (o.sucursal = :sucursal");
            jpql.append(" OR o.idSucursalOrigen = :sucursal)");
        }
        Query query = em.createQuery(jpql.toString());
                if (filtroMap.containsKey("sucursal") 
                && filtroMap.get("sucursal") != null) {
          query.setParameter("sucursal", (Sucursal)filtroMap.get("sucursal"));
        }
        
        
        return  query.getResultList();
        } catch(NoResultException nre) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }


}

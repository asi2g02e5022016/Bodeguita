/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Receta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author samaelopez
 */
@Stateless
public class ProcesosInv implements ProcesosInvLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    
    
    public List <Receta > buscarRecetas() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT r FROM Receta r ");
        Query query =  em.createQuery(jpql.toString());
        
        return query.getResultList();
    }
}

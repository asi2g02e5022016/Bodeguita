/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Compania;
import com.asi.restaurantbcd.modelo.Piso;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author siman
 */
@Stateless
public class BusquedasPisos implements BusquedasPisosLocal {
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    @Override
    public List<Piso> buscarPiso(Compania idCompania) throws Exception {
       StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM Piso p where p.idsucursal.idcompania =:c");
        Query query = em.createQuery(jpql.toString()).setParameter("c", idCompania);
        return query.getResultList();         
        
    }

    

    
}

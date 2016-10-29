/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;


import com.asi.restaurantbcd.modelo.Piso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

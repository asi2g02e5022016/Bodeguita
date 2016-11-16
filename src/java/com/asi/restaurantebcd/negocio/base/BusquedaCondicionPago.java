/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;
import com.asi.restaurantbcd.modelo.Condicionpago;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
@Stateless
public class BusquedaCondicionPago implements BusquedaCondicionPagoLocal {

     @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 

    @Override
    public List<Condicionpago> buscarCondicionPago(){
        
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Condicionpago a");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();        
        
    }


     
             
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

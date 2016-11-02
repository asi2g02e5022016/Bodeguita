/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Configuracion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class BusquedaConfiguracion implements BusquedaConfiguracionLocal {
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    @Override
    public List <Configuracion> buscarConfiguracion() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Configuracion a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }    

}

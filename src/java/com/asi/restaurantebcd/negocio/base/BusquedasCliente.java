/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luis
 */
@Stateless
public class BusquedasCliente implements BusquedasClienteLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Cliente> buscarCliente() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Cliente a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Tipodocumento;
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
public class BusquedaTipoDocumento implements BusquedaTipoDocumentoLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    @Override
    public List <Tipodocumento> buscarTipodocumento() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Tipodocumento a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }    
}

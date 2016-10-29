/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Mesa;
import com.asi.restaurantbcd.modelo.Piso;
import com.asi.restaurantbcd.modelo.Sucursal;
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
    public List<Piso> buscarPiso() throws Exception {
       StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM Piso p");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();         
        
    }

    @Override
    public List<Piso> buscarPiso(Sucursal idSucursal) throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM Piso p where p.idsucursal =:c");
        Query query = em.createQuery(jpql.toString()).setParameter("c", idSucursal);
        return query.getResultList(); 
    }

    @Override
    public List<Mesa> buscarMesa(Piso idPiso) throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM Mesa p where p.idpiso =:c");
        Query query = em.createQuery(jpql.toString()).setParameter("c", idPiso);
        return query.getResultList(); 
    }

    @Override
    public List<Mesa> buscarMesa(Sucursal idSucursal) throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM Mesa p where p.idpiso.idsucursal =:c");
        Query query = em.createQuery(jpql.toString()).setParameter("c", idSucursal);
        return query.getResultList(); 
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Vexistxsucsal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PROGRAMADOR
 */
@Stateless
public class VBusquedasExistencias implements VBusquedasExistenciasLocal {
    
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 

    @Override
    public List <Vexistxsucsal> buscarExistencia() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Vexistxsucsal a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
    
    @Override
    public List <Vexistxsucsal> buscarExistenciaxSucsal(String _sucsal) throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Vexistxsucsal a where sucursal like '%" + _sucsal + "%'" );
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
    
    @Override
    public List <Vexistxsucsal> buscarExistenciaxProducto(String _producto) throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Vexistxsucsal a where producto like '%" + _producto + "%'" );
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }    
}

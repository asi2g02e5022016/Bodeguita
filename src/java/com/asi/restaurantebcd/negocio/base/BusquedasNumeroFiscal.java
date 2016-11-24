/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Caja;
import com.asi.restaurantbcd.modelo.Numerofiscal;
import com.asi.restaurantbcd.modelo.Sucursal;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author "Joaquin Sanchez SA101110"
 */
@Stateless
public class BusquedasNumeroFiscal implements BusquedasNumeroFiscalLocal {

    /**
     * Unidad de persistencia asociada a la coneccion.
     */
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    @Override
    public List<Numerofiscal> buscarNumeroFiscal() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Numerofiscal a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    @Override
    public List<Sucursal> buscarSucursal() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Sucursal a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    @Override
    public List<Caja> buscarCaja(Sucursal sucursal) throws Exception {
        try {
            StringBuilder jpql = new StringBuilder();
            //Construimos query según los parametros ingresados
            jpql.append("SELECT a FROM Caja a WHERE 1 = 1 ");
            if (sucursal != null) {
                jpql.append(" AND a.idsucursal.idsucursal = :sucursal ");
            }
            Query query = em.createQuery(jpql.toString());
            //asignamos valores a parametros del query
            if (sucursal != null) {
                query.setParameter("sucursal", sucursal.getIdsucursal());
            }
            return query.getResultList();
        } catch (NoResultException ne) {
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
//        StringBuilder jpql = new StringBuilder();
//        jpql.append("SELECT a FROM Caja a ");
//        Query query = em.createQuery(jpql.toString());
//        return query.getResultList();
    }
    
    @Override
    public List<Caja> buscarCaja() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Caja a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
}

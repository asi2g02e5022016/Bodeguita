/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Ordenpedido;
import javax.ejb.Stateless;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JAEM
 */
@Stateless
public class BusquedaPedido implements BusquedaPedidoLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    @Override
    public List<Ordenpedido> buscarOrdenPedido(Sucursal sucursal, Date fechaIni,
            Date fechaFin) throws Exception {
        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append(" SELECT a from Ordenpedido a WHERE 1 = 1 ");
            if (sucursal != null) {
                jpql.append(" AND a.ordenpedidoPK.idsucursal = :sucursal ");
            }
            if (fechaIni != null && fechaFin != null) {
                jpql.append(" AND a.fechapedido BETWEEN :fechaIni AND :fechaFin ");
            }
            Query query = em.createQuery(jpql.toString());

            // asignamos valores a los parametros del query 
            if (sucursal != null) {
                query.setParameter("sucursal", sucursal.getIdsucursal());
            }
            if (fechaIni != null && fechaFin != null) {
                query.setParameter("fechaIni", fechaIni);
                query.setParameter("fechaFin", fechaFin);
            }
            return query.getResultList();

        } catch (NoResultException ne) {
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public String nombreSucursal(Sucursal suc) throws Exception {
        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append(" SELECT a.sucursal from Sucursal a WHERE 1 = 1 ");
            if (suc != null) {
                jpql.append(" AND a.idsucursal = :sucursal ");
            }          
            Query query = em.createQuery(jpql.toString());

            // asignamos valores a los parametros del query 
            if (suc != null) {
                query.setParameter("sucursal", suc.getIdsucursal());
            }           
            return query.getResultList().toString();

        } catch (NoResultException ne) {
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}

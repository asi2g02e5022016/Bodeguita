/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Facturaencabezado;
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
public class BusquedaFactura implements BusquedaFacturaLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    @Override
    public List<Facturaencabezado> buscarFacturas(Sucursal sucursal, Date fechaIni,
            Date fechaFin) throws Exception {
        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append(" SELECT a from Facturaencabezado a WHERE 1 = 1 ");
            if (sucursal != null) {
                jpql.append(" AND a.facturaencabezadoPK.idsucursal = :sucursal ");
            }
            if (fechaIni != null && fechaFin != null) {
                jpql.append(" AND a.fechafactura BETWEEN :fechaIni AND :fechaFin ");
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
}

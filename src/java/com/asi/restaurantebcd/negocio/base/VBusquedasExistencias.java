/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Vexistxsucsal;
import java.util.List;
import java.util.Map;
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
    /**
     * 
     * @param filtro
     * @return
     * @throws Exception 
     */
    @Override
    public List <Vexistxsucsal> buscarExistenciaFiltros(Map filtro) 
                throws Exception {
            if (filtro == null){
                return null;
            }
            Integer codcia = (Integer) filtro.get("codcia");
            Integer codsuc = (Integer) filtro.get("codsuc");
            String producto = (String) filtro.get("producto");
            String tipo = (String) filtro.get("tipo");
            StringBuilder jpql = new StringBuilder();
            jpql.append("SELECT a FROM Vexistxsucsal a where 1 = 1");
            if (codcia != null) {
                jpql.append(" AND a.idcompania = :codcia");
            }
            if (codsuc != null) {
                jpql.append(" AND a.idsucursal = :codsuc");
            }
            if (producto != null) {
                jpql.append(" AND a.idproducto = :producto");
            }
             if (tipo != null) {
                jpql.append(" AND a.idtipoproducto = :tipo");
            }
            Query query = em.createQuery(jpql.toString());
            if (codcia != null) {
                query.setParameter("codcia", codcia);
            }
            if (codsuc != null) {
                query.setParameter("codsuc", codsuc);
            }
            if (producto != null) {
               query.setParameter("producto", producto);
            }
             if (tipo != null) {
                query.setParameter("tipo", tipo);
            }
        return query.getResultList();
    }   
}

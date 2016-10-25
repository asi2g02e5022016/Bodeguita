/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Vwproductos;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author samaelopez
 */
@Stateless
public class BusquedasProductos implements BusquedasProductosLocal {

     @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 

    /**
     * Mwtodo que de busqueda de productos por medio de filtros.
     * @param filtros Map de filtros, si el mapa es vacio devuelve 
     * todo los productos.
     * @return Lista de productos de la vista Vwproductos.
     */
     @Override
    public List < Vwproductos > buscarProducto(Map filtros) {
        if (filtros == null) {
            return null;
        }
        StringBuilder jpql =  new StringBuilder();
        try {
        Integer codigoProducto = Utilidades.getParemetro("codigo", filtros);
        String descripcion = Utilidades.getParemetro("descripcion", filtros);
        Integer tipo = Utilidades.getParemetro("tipo", filtros);
        Integer grupo = Utilidades.getParemetro("grupo", filtros);
        Integer marca = Utilidades.getParemetro("marca", filtros);
        Integer medida = Utilidades.getParemetro("medida", filtros);
        Integer activo = Utilidades.getParemetro("activo", filtros);
        Integer vendible = Utilidades.getParemetro("vendible", filtros);
        jpql.append(" SELECT p FROM Vwproductos p WHERE 1 = 1 ");
        if (codigoProducto != null) {
            jpql.append(" AND p.vwproductosPK.idproducto = :codigoProducto ");
        }
        if (descripcion != null) {
            jpql.append(" AND p.producto = :descripcion ");
        }
        if (tipo != null) {
            jpql.append(" AND p.idtipoproducto = :tipo  ");
        }
        if (grupo != null) {
            jpql.append(" AND p.idgrupoproducto = :grupo  ");
        }
        if (marca != null) {
            jpql.append(" AND p.idmarcaproducto = :marca ");
        }
        if (medida != null) {
            jpql.append(" AND p.idmedidaproducto = :medida ");
        }
        if (activo != null) {
            jpql.append(" AND p.activo = :activo  ");
        }
        if (vendible != null) {
            jpql.append(" AND p.vendible = :vendible  ");
        }
        
        Query query = em.createQuery(jpql.toString());
        if (codigoProducto != null) {
            query.setParameter("codigoProducto", codigoProducto);
        }
        if (descripcion != null) {
            query.setParameter("descripcion", descripcion);
        }
        if (tipo != null) {
            query.setParameter("tipo", tipo);
        }
        if (grupo != null) {
            query.setParameter("grupo", grupo);
        }
        if (marca != null) {
            query.setParameter("marca", marca);
        }
        if (medida != null) {
            query.setParameter("medida", medida);
        }
        if (activo != null) {
            query.setParameter("activo", activo);
        }
        if (vendible != null) {
            query.setParameter("vendible", vendible);
        }
            System.out.println("jpql.." +jpql);
        return query.getResultList();        
        } catch (NoResultException nre) {
        return null;
        } catch (Exception e) {
            throw e;
        }
    }
    
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Compania;
import com.asi.restaurantbcd.modelo.Proveedor;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author samaelopez
 */
@Stateless
public class BusquedasMtto implements BusquedasMttoLocal {
    
    /**
     * Unidad de persistencia asociada a la coneccion.
     */
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 
        
    /**
     * Obtener el ultimo correlativo de compania.
     * @return LCorrelativo Siguinte.
     * @throws Exception Error generico.
     */   
    @Override
    public Integer obtenerCorreltivoCompanias() throws Exception {
        Integer valor;
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idcompania) FROM Compania ");
        Query query = em.createNativeQuery(slq.toString());
        valor = (Integer) query.getSingleResult();
        if (valor == null) {
            valor = Integer.parseInt("0");
        }
        valor   =  valor + 1;
        return valor;
    }
    /**
     * Obtiene la lista de companias.
     * @return List Companias.
     * @throws Exception Error generico.
     */
    @Override
    public List <Compania> buscarCompania() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Compania a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
   /**
     * Obtiene la lista de Sucursales.
     * @return List Sucursales.
     * @throws Exception Error generico.
     */
    @Override
    public List <Sucursal> buscarSucursales() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Sucursal a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
    
}

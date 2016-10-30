/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Departamento;
import com.asi.restaurantbcd.modelo.Empleado;
import com.asi.restaurantbcd.modelo.Impuesto;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Puesto;
import java.util.List;
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
//    @Override
//    public List <Compania> buscarCompania() throws Exception {
//        StringBuilder jpql = new StringBuilder();
//        jpql.append("SELECT a FROM Compania a ");
//        Query query = em.createQuery(jpql.toString());
//        return query.getResultList();
//    }
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
    
    /**
     * Obtiene la lista de Impuestos.
     * @return List Impuesto.
     * @throws Exception Error generico.
     */
    @Override
    public List <Impuesto> buscarImpuesto() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Impuesto a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
     /**
     * Obtiene la lista de Empleados.
     * @return List Empleado.
     * @throws Exception Error generico.
     */
    @Override
    public List <Empleado> buscarEmpleado() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Empleado a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
    
      /**
     * Obtiene la lista de Empleados.
     * @return List Empleado.
     * @throws Exception Error generico.
     */
    @Override
    public List <Puesto> buscarPuesto() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Puesto a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
    
    @Override
    public List <Departamento> buscarDepartamento() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Departamento a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    /**
     * Obtiene la lista de Forma de Pago.
     * @return List Formapago.
     * @throws Exception Error generico.
     */
    
    /**@Override
    public List <Formapago> buscarFormapago() throws Exception{
        StringBuilder jpql = new StringBuilder();
        jpql.append("Select a From formapago a");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }*/
    
}

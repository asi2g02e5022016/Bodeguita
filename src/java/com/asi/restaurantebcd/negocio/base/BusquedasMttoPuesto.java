/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Departamento;
import com.asi.restaurantbcd.modelo.Puesto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Walter
 */
@Stateless
public class BusquedasMttoPuesto implements BusquedasMttoPuestoLocal {
    /**
* Unidad de persistencia asociada a la coneccion.
*/
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 
    
   
    @Override
    public List <Puesto> buscarPuesto() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Estado a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    @Override
    public Integer obtenerCorreltivoPuesto() throws Exception {
        Integer valor;
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idpuesto) FROM puesto ");
        Query query = em.createNativeQuery(slq.toString());
        valor = (Integer) query.getSingleResult();
        if (valor == null) {
            valor = Integer.parseInt("0");
        }
        valor   =  valor + 1;
        return valor;
    }

    @Override
    public List<Departamento> buscarDepartamento() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM departamento a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

   
}

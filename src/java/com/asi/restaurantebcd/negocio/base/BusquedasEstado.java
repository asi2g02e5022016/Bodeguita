/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Estado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author SANCHEZ
 */
@Stateless
public class BusquedasEstado implements BusquedasEstadoLocal {
/**
* Unidad de persistencia asociada a la coneccion.
*/
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 
    
/**
 * Obtener el ultimo correlativo de documentos estado.
 * @return LCorrelativo Siguinte.
 * @throws Exception Error generico.
*/       
    @Override
    public Integer obtenerCorreltivoEstado() throws Exception {
        Integer valor;
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idEstado) FROM Estado ");
        Query query = em.createNativeQuery(slq.toString());
        valor = (Integer) query.getSingleResult();
        if (valor == null) {
            valor = Integer.parseInt("0");
        }
        valor   =  valor + 1;
        return valor;
    }
    
/**
 * Obtener la lista de documentos estado.
 * @return LCorrelativo Siguinte.
 * @throws Exception Error generico.
*/
    @Override
    public List <Estado> buscarEstado() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Estado a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
    
    
    
    
    
}

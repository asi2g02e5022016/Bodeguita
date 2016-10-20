/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Tipoproducto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author hp
 * esto es una clase (O un EJB)
 * SE DEFINE QUE ES EJB porque lleva la anotacion Staless
 * que eso quiere decir sin estado
 * ok
 * cpara llamar los metodos de un ejb 
 * solo podes accesar a el por medio de una interfaz
 */
@Stateless
public class BusquedasTipoProducto implements BusquedasTipoProductoLocal{
    
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
    public Integer obtenerCorreltivoTipoProducto() throws Exception {
        Integer valor;
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idtipoproducto) FROM tipoproducto ");
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
    public List <Tipoproducto> buscarTipoProducto() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Tipoproducto a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
                
    }     
    
    
    
}

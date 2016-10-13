/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Marcaproducto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author Melendez
 */
@Stateless
public class BusquedasMarcaProducto implements BusquedasMarcaProductoLocal{

 @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 
 
/**
 * Obtener el ultimo correlativo de documentos marca producto.
 * @return LCorrelativo Siguinte.
 * @throws Exception Error generico.
*/ 
 @Override
 public Integer obtenerCorreltivoMarcaProductos() throws Exception {
        Integer valor;
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idMarcaProducto) FROM marcaproducto ");
        Query query = em.createNativeQuery(slq.toString());
        valor = (Integer) query.getSingleResult();
        if (valor == null) {
            valor = Integer.parseInt("0");
        }
        valor   =  valor + 1;
        return valor;
    }
  
    @Override
    public List<Marcaproducto> buscarMarcaProductos() throws Exception {
      StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM marcaproducto a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList(); 
    }
    
}

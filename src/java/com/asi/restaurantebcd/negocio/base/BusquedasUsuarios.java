/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author "Joaquin Sanchez SA101110"
 */
@Stateless
public class BusquedasUsuarios implements BusquedasUsuariosLocal{
    /**
* Unidad de persistencia asociada a la coneccion.
*/
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 

    /**
 * Obtener la lista de los usuarios ingresados
 * @return LCorrelativo Siguinte.
 * @throws Exception Error generico.
*/
    @Override
    public List<Usuario> buscarUsuario() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Usuario a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
    
}

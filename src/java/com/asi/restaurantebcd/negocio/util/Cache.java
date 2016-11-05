/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author samaelopez
 */
public class Cache {
    
    
    public final void invalidarCacheEM(String nombrePU)
            throws Exception {
        EntityManagerFactory factory
              = Persistence.createEntityManagerFactory(nombrePU);
        EntityManager eme = factory.createEntityManager();
        eme.getEntityManagerFactory().getCache().evictAll();
    }
}

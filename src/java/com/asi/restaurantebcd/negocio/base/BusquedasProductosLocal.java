/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Producto;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author samaelopez
 */
@Local
public interface BusquedasProductosLocal {
    
   /**
     * 
     * @param map
     * @return
     * @throws Exception 
     */
    public List<Producto> buscarProductos(Map map) throws Exception;
}

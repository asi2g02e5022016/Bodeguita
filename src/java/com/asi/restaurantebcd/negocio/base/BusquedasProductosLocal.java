/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Vwproductos;
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
     * Mwtodo que de busqueda de productos por medio de filtros.
     * @param filtros Map de filtros, si el mapa es vacio devuelve 
     * todo los productos.
     * @return Lista de productos de la vista Vwproductos.
     */
    public List < Vwproductos > buscarProducto(Map filtros);
}

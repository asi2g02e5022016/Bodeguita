/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Proveedor;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author samaelopez
 */
@Local
public interface BusquedasProveedoresLocal {
   /**
     * Busca proveedores por medio de un mapa.
     * 
     * @param filtroMap
     * @return 
     */
     List <Proveedor> buscarProveedors(Map filtroMap);
    
}

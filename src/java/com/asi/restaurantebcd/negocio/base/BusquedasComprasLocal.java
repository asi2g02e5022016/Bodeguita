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
 * @author samael  lopez
 */
@Local
public interface BusquedasComprasLocal {

    /**
     * Buscar Proveedores por medio de una MAP filtros.
     * @param map Map.
     * @return Lista de proveedores.
     * @throws Exception  Error generico.
     */
    
    public List <Proveedor> buscarProveedores(Map map) throws Exception;

    public Integer obtenerCorreltivoCompra(Integer codsuc, Class clase, String identificador)
            throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import javax.ejb.Local;

/**
 *
 * @author samael  lopez
 */
@Local
public interface BusquedasComprasLocal {
    /**
     * Obtener el ultimovalor de la compra.
     * @return Integrt.
     * @throws Exception  Error gnerico.
     */
    public Integer obtenerCorreltivoCompra() throws Exception;
    
}

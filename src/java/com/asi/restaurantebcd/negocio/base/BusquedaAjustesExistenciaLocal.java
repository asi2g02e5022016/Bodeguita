/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Ajuste;
import com.asi.restaurantbcd.modelo.Ajustedetalle;
import com.asi.restaurantbcd.modelo.Producto;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JAEM
 */
@Local
public interface BusquedaAjustesExistenciaLocal {

    /**
     * Obtiene lista de ajustes
     *
     * @return List Ajuste encabezado.
     * @throws Exception Error generico.
     */
    public List<Ajuste> buscarAjustesExistenciaEnc() throws Exception;

    /**
     * Obtiene lista de detalle de ajustes
     *
     * @return List detalle de ajuste.
     * @throws Exception Error generico.
     */
    public List<Ajustedetalle> buscarAjustesExistenciaDet() throws Exception;
    
       /**
     * Obtiene lista de detalle de productos
     *
     * @return List detalle de ajuste.
     * @throws Exception Error generico.
     */
    public List<Producto> buscarProducto() throws Exception;
    
    
     public Integer obtenerCorreltivoAjuste(Integer codsuc, Class clase, String identificador)
            throws Exception;
}

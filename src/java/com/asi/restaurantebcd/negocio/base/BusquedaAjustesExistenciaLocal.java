/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Ajuste;
import com.asi.restaurantbcd.modelo.Ajustedetalle;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.Date;

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
     * @param sucursal
     * @param fechaIni
     * @param fechaFin
     * @return List Ajuste encabezado.
     * @throws Exception Error generico.
     */
    public List<Ajuste> buscarAjustesExistenciaEnc(Sucursal sucursal,
            Date fechaIni, Date fechaFin) throws Exception;

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

    /*
      * Metodo de busqueda de ajustes.
     * @param sucursal sucursal 
     * @param fechaInicia fecha inicial de ajuste.
     * @param fechaFinal fecha final de ajuste.
     * @return
     * @throws Exception 
     */

    public String nombreSucursal(Sucursal suc) throws Exception;

}

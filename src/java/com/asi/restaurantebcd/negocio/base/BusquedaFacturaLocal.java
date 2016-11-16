/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Facturaencabezado;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JAEM
 */
@Local
public interface BusquedaFacturaLocal {
      /**
     * Obtiene lista de facturas
     *
     * @param sucursal sucursal donde se genera la factura
     * @param fechaIni
     * @param fechaFin
     * @return List Facturaencabezado.
     * @throws Exception Error generico.
     */
    public List<Facturaencabezado> buscarFacturas(Sucursal sucursal, Date fechaIni,
            Date fechaFin)throws Exception;

}

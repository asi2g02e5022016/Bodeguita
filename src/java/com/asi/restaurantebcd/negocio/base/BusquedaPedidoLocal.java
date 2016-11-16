/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Ordenpedido;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JAEM
 */
@Local
public interface BusquedaPedidoLocal {
    
    
    public List<Ordenpedido> buscarOrdenPedido(Sucursal sucursal, Date fechaIni,
            Date fechaFin)throws Exception;
    
    
    public String nombreSucursal(Sucursal suc) throws Exception;
}

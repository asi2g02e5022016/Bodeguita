/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Notapedido;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author siman
 */
@Local
public interface NotaPedidoEJBLocal {
    
    public List <Sucursal> buscarSucursalOrigen(Map map) throws Exception;
    
    public List <Notapedido> buscarNotasPedido(Map map) throws Exception;
    
}

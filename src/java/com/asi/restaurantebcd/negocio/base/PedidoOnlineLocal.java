/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Cliente;
import com.asi.restaurantbcd.modelo.Ordenpedido;
import com.asi.restaurantbcd.modelo.Ordenpedidodetalle;
import com.asi.restaurantbcd.modelo.OrdenpedidodetalleDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author samaelopez
 */
@Local
public interface PedidoOnlineLocal {

  public void guardarPedidoOnline(Integer codigoCliente,
            String usr,
            Integer idsucursal, List < OrdenpedidodetalleDTO > lstDetalle)
            throws Exception;
  
  
  
      public  Cliente lstClientes(String usuario, String password) throws Exception ;

    public List<Ordenpedido> lstPedido();
    
}

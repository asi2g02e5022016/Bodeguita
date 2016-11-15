/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Cliente;
import com.asi.restaurantbcd.modelo.Estado;
import com.asi.restaurantbcd.modelo.Ordenpedido;
import com.asi.restaurantbcd.modelo.OrdenpedidoPK;
import com.asi.restaurantbcd.modelo.Ordenpedidodetalle;
import com.asi.restaurantbcd.modelo.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author samaelopez
 */
@Stateless
public class PedidoOnline implements PedidoOnlineLocal {

    @EJB
    private BusquedasComprasLocal busquedasCompras;

    @EJB
    private CrudBDCLocal crudBDC;
 
    
    
    
    /**
     * 
     * @param codigoCliente
     * @param usr
     * @param idsucursal
     * @param lstDetalle
     * @throws java.lang.Exception
     */
    @Override
    public void guardarPedidoOnline(Integer codigoCliente,
            String usr, Integer idsucursal,
            List < Ordenpedidodetalle > lstDetalle) throws Exception {
        OrdenpedidoPK idOt = new OrdenpedidoPK();
        Integer idCoorr = busquedasCompras
                .obtenerCorreltivoCompra(idsucursal, 
                        Ordenpedido.class, usr, usr);
        idOt.setIdordenpedido(0);
        idOt.setIdsucursal(0);
        Ordenpedido ped =  new Ordenpedido();
        ped.setFechapedido(new Date());
        Cliente cli  = 
                crudBDC.buscarEntidad(Cliente.class, codigoCliente);
        ped.setIdcliente(cli);
        Estado est = crudBDC.buscarEntidad(Estado.class, 10);
        ped.setIdestado(est);
        Usuario usuario = crudBDC.buscarEntidad(Usuario.class,  usr);
        ped.setIdusuario(usuario);
        ped.setMesa(null);
        
//        ped.setOrdenpedidoPK(facturaencabezadoList);
//        ped.setSucursal(facturaencabezadoList);
        
    }
  
}

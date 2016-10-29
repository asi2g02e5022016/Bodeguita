/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;


import com.asi.restaurantbcd.modelo.Mesa;
import com.asi.restaurantbcd.modelo.Piso;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luis_portillo
 */
@Local
public interface BusquedasPisosLocal {
    
    List <Piso> buscarPiso() throws Exception;  
    
    public List<Piso> buscarPiso(Sucursal idSucursal) throws Exception;
    
    public List<Mesa> buscarMesa(Piso idPiso) throws Exception;
    
    public List<Mesa> buscarMesa(Sucursal idSucursal) throws Exception;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Caja;
import com.asi.restaurantbcd.modelo.Numerofiscal;
import com.asi.restaurantbcd.modelo.Sucursal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author "Joaquin Sanchez SA101110"
 */
@Local
public interface BusquedasNumeroFiscalLocal {
    public List <Numerofiscal> buscarNumeroFiscal () throws Exception;
    public List <Sucursal> buscarSucursal () throws Exception;
    public List <Caja> buscarCaja (Sucursal sucursal) throws Exception;
    public List <Caja> buscarCaja () throws Exception;
    
}

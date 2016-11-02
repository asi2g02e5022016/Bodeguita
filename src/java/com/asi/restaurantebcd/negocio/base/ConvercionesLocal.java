/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Medida;
import javax.ejb.Local;

/**
 *
 * @author samaelopez
 */
@Local
public interface ConvercionesLocal {

    public Double getValorConvercion(Medida medidaInicial, Medida medidaFinal, Double cantida) throws Exception;
    
}

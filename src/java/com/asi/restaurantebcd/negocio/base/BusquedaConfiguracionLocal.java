/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Configuracion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface BusquedaConfiguracionLocal {
List <Configuracion> buscarConfiguracion() throws Exception;        
}

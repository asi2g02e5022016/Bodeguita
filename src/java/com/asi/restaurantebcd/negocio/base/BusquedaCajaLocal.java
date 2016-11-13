/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Caja;
import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
@Local
public interface BusquedaCajaLocal {
    public List<Caja> BuscarCaja();
   
}

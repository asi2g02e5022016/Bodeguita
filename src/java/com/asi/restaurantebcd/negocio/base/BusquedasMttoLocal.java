/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Compania;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author samaelopez
 */
@Local
public interface BusquedasMttoLocal {
      /**
     * Obtener el ultimo correlativo de compania.
     * @return LCorrelativo Siguinte.
     * @throws Exception Error generico.
     */   
     Integer obtenerCorreltivoCompanias() throws Exception;
       /**
     * Obtiene la lista de companias.
     * @return List Companias.
     * @throws Exception Error generico.
     */
     List <Compania> buscarCompania() throws Exception;
}

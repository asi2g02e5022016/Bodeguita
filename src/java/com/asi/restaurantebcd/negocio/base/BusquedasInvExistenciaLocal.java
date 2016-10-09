/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Existencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SANCHEZ
 */
@Local
public interface BusquedasInvExistenciaLocal {
         /**
     * Obtener el ultimo correlativo de existencia.
     * @return LCorrelativo Siguinte.
     * @throws Exception Error generico.
     */   
     Integer obtenerCorrelativoInvExistencia() throws Exception;
     
     /**
     * Obtiene la lista de existencia.
     * @return List existencias.
     * @throws Exception Error generico.
     */
     List <Existencia> buscarInvExistencia() throws Exception;
}

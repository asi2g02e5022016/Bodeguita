/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Estado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SANCHEZ
 */
@Local
public interface BusquedasEstadoLocal {
     /**
     * Obtener el ultimo correlativo de estado.
     * @return LCorrelativo Siguinte.
     * @throws Exception Error generico.
     */   
     Integer obtenerCorreltivoEstado() throws Exception;
       /**
     * Obtiene la lista de estado documentos.
     * @return List Estado documento.
     * @throws Exception Error generico.
     */
     List <Estado> buscarEstado() throws Exception;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Departamento;
import com.asi.restaurantbcd.modelo.Puesto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Walter
 */
@Local
public interface BusquedasMttoPuestoLocal {
     /**
     * Obtener el ultimo correlativo de estado.
     * @return LCorrelativo Siguinte.
     * @throws Exception Error generico.
     */   
     Integer obtenerCorreltivoPuesto() throws Exception;
       /**
     * Obtiene la lista de estado documentos.
     * @return List Estado documento.
     * @throws Exception Error generico.
     */
     List <Puesto> buscarPuesto() throws Exception;

     List<Departamento> buscarDepartamento() throws Exception;
}

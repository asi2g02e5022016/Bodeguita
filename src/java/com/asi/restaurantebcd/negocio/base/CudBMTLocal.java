/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Samaellopez
 */
@Local
public interface CudBMTLocal {
        /**
     * Permite persistir una entidad, esta operación solo permite hacer un
     * "INSERT" y no verifica la existencia de la entidad en la tabla. Por
     * tanto podría ocacionar un error de constraint de llave única.
     * @param entidad Entidad a persistir.
     * @throws java.lang.Exception Error genérico.
     */
    void persistirEntidad(final Object entidad) throws Exception;
    /**
     * Permite guardar una entidad, esta operación verifica la existencia
     * del registro en la tabla, de existir ejecuta un "UPDATE", caso contrario
     * ejecuta un INSERT.
     * @param <T> Genérico.
     * @param entidad Entidad a guardar.
     * @return Entidad
     * @throws java.lang.Exception Error genérico.
     */
    < T > T guardarEntidad(final Object entidad) throws Exception;
    /**
     * Ejecuta la operación guardarEntidad sobre una lista de entidades.
     * @param entidades Lista de entidades.
     * @return true en caso de exito.
     * @throws java.lang.Exception Error genérico.
     */
    boolean guardarEntidades(final List entidades) throws Exception;
    /**
     * Permite eliminar una entidad del modelo del dominio, asi como de la
     * base de datos.
     * @param entidad Entidad a eliminar.
     * @return true en caso de exito.
     * @throws java.lang.Exception Error genérico.
     */
    boolean eliminarEntidad(final Object entidad) throws Exception;
    /**
     * Permite ejecutar la acción eliminarEntidad sobre una lista de entidades.
     * @param entidades Lista de entidades a eliminar.
     * @return true en caso de exito
     * @throws java.lang.Exception Error genérico.
     */
    boolean eliminarEntidades(final List entidades) throws Exception;
    
}

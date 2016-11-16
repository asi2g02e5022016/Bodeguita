/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Configuracion;
import com.asi.restaurantbcd.modelo.Programaciondetalle;
import com.asi.restaurantbcd.modelo.Programaciontareas;
import com.asi.restaurantbcd.modelo.Tarea;
import com.asi.restaurantbcd.modelo.Tipotarea;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JAEM
 */
@Local
public interface BusquedaProgramacionTareasLocal {
    
    public Integer obtenerCorreltivoAjuste( Class clase) throws Exception ;
     /**
     * Obtiene lista de configuraciones
     *
     * @return List detalle de configuraciones.
     * @throws Exception Error generico.
     */
    public List<Configuracion> buscarConfiguracion() throws Exception;
     /**
     * Obtiene lista de tareas
     *
     * @return List detalle de ajuste.
     * @throws Exception Error generico.
     */
    public List<Tarea> buscarTarea() throws Exception;
    
    /**
     * Obtiene lista de tipo de tareas
     *
     * @return List detalle de ajuste.
     * @throws Exception Error generico.
     */
    public List<Tipotarea> buscarTipoTarea() throws Exception;
    /**
     * Obtiene lista programaciones de tareas
     *
     * @return List detalle de ajuste.
     * @throws Exception Error generico.
     */
    public List<Programaciontareas> buscarProgramacionTareas() throws Exception;
    
    
    /**
     * Obtiene lista detalle programación de tareas
     *
     * @return List detalle de ajuste.
     * @throws Exception Error generico.
     */
    public List<Programaciondetalle> buscarProgramacionDetalle() throws Exception;
    
    /**
     * Obtiene lista detalle por programacion     *
     * @param idProgramacion
     * @return List detalle de ajuste.
     * @throws Exception Error generico.
     */
    public List<Programaciondetalle> buscarProgramacionDetalle(Integer idProgramacion) throws Exception;
    
    /**
     * Obtiene nombre de tarea
     * @param idTarea
     * @return List detalle de idTarea.
     * @throws Exception Error generico.
     */
    public String buscarNombreTarea(Integer idTarea) throws Exception;
    
    /**
     * Obtiene nombre de host
     * @param host
     * @return List detalle de idTarea.
     * @throws Exception Error generico.
     */
    public String buscarNombreHost(Integer host) throws Exception ;
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Compania;
import com.asi.restaurantbcd.modelo.Empleado;
import com.asi.restaurantbcd.modelo.Formapago;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Impuesto;
import com.asi.restaurantbcd.modelo.Puesto;
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
   /**
     * Obtiene la lista de Sucursales.
     * @return List Sucursales.
     * @throws Exception Error generico.
     */
    public List <Sucursal> buscarSucursales() throws Exception;
    /**
     * Obtiene la lista de Impuestos.
     * @return List Impuestos.
     * @throws Exception Error generico.
     */
    public List <Impuesto> buscarImpuesto() throws Exception;
    /**
    * Obtiene la lista de Empleados.
     * @return List Empleado.
     * @throws Exception Error generico.
     */
    public List <Empleado> buscarEmpleado() throws Exception;
    
     /**
    * Obtiene la lista de Empleados.
     * @return List Empleado.
     * @throws Exception Error generico.
     */
    public List <Puesto> buscarPuesto() throws Exception; 
    /**
     * Obtiene la lista de Formas de Pago.
     * @return List forma pago.
     * @throws Exception Error generico.
     */
    /**public List <Formapago> buscarFormapago() throws Exception;*/
}

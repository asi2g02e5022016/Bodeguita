/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Usuario;
import javax.ejb.Local;

/**
 *
 * @author samaelopez
 */
@Local
public interface ProcesosInventariosLocal {
    /**
     * Calculo de existencia de productos.
     * @param cantidad Cantidad a transaccionar de el documento.
     * @param producto Entidad productos asociado a cargar o abonar el inventario.
     * @param usr codigode usuario que ejecuta la accion.
     * @param sucursal Sucursal que esta afectando la existencia.
     * @param costo costo de producto.
     * @param descargaInv true si quiere descargar cantidades de inventario,
     *  false si quiere cargar existencias al inventario.
     * @param calcularCstProm true si quiere que recalcule el costo promedio.
     * @throws Exception Error generico.
     */
    public void afectarExistencia(Double cantidad, Producto producto,
            Usuario usr, Sucursal sucursal, Double costo, boolean descargaInv,
            boolean  calcularCstProm) throws Exception;
}

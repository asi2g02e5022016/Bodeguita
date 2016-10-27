/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Existencia;
import com.asi.restaurantbcd.modelo.ExistenciaPK;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author samaelopez
 */
@Stateless
public class ProcesosInventarios implements ProcesosInventariosLocal {

    @EJB
    private CrudBDCLocal crudBDC;
    
    
    
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
    @Override
    public void afectarExistencia(Double cantidad, Producto producto,
            Usuario usr, Sucursal sucursal, Double costo, boolean descargaInv,
            boolean  calcularCstProm) throws Exception {
        try {
            if (cantidad == null) {
                throw new Exception("La cantidad es obligatorio.");
            }
            Double exisActual;
            Double costoPromedioTotalActual = Double.valueOf("0");
            Double costoPromedioActual = Double.valueOf("0");
            Double costoTotalTransaccion = costo *  cantidad ;
            Double exisTotal;
            Double costoTotal;
            Double costoPromedioNew;
            ExistenciaPK idExis = new ExistenciaPK();
            idExis.setIdsucursal(sucursal.getIdsucursal());
            idExis.setIdproducto(producto.getIdproducto());
            Existencia exis =  crudBDC.buscarEntidad(Existencia.class, idExis);
            if (exis == null) {
                exis = new Existencia();
                exis.setExistenciaPK(idExis);
            }
            if (exis.getValor() != null &&
                    !exis.getValor().equals(Double.valueOf("0"))) {
                exisActual = exis.getValor();
                if (descargaInv) {
                     exisTotal = exisActual - cantidad;
                } else {
                     exisTotal = exisActual + cantidad;
                }
                if (costoPromedioActual != null) {
                    costoPromedioTotalActual = exisActual * exis.getValor();
                }
            } else {
                exisTotal = cantidad;
            }
            costoTotal = costoPromedioTotalActual + costoTotalTransaccion;
            costoPromedioNew = costoTotal / exisTotal;
            exis.setCostounitario(costoPromedioNew);
            exis.setValor(exisTotal);
            exis.setSucursal(sucursal);
            crudBDC.guardarEntidad(exis);
        } catch (Exception ex) {
            Logger.getLogger(ProcesosInventarios.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}

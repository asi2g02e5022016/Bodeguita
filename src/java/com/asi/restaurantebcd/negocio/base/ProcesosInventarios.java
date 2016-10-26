/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Existencia;
import com.asi.restaurantbcd.modelo.ExistenciaPK;
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
     * 
     * @param cantidad
     * @param codproducto
     * @param usr
     * @param sucursal
     * @param entidad
     * @throws Exception 
     */
    public void afectarExistencia(Integer cantidad, Integer codproducto,
            Usuario usr, Sucursal sucursal, Object entidad) throws Exception {
        try {
            if (cantidad == null) {
                throw new Exception("La cantidad es obligatorio.");
            }
            ExistenciaPK idExis = new ExistenciaPK();
            idExis.setIdexistencia(0);
            Existencia Exis =  crudBDC.buscarEntidad(Existencia.class, entidad);
                 
        } catch (Exception ex) {
            Logger.getLogger(ProcesosInventarios.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}

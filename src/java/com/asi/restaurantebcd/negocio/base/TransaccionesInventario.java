/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Existencia;
import com.asi.restaurantbcd.modelo.ExistenciaPK;
import com.asi.restaurantbcd.modelo.Ordenproduccion;
import com.asi.restaurantbcd.modelo.Ordenproducciondetalle;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

/**
 *
 * @author samaelopez
 */

@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
public class TransaccionesInventario implements TransaccionesInventarioLocal {

    @EJB
    private CrudBDCLocal crudBDC;

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;
            @EJB
    private ProcesosInventariosLocal ejbProInv;

    
    public void guardarOrdenCompra(Ordenproduccion ordenPro) throws Exception {

        try {
            utx.begin();
            if (ordenPro == null) {
                throw new Exception("La orden de produccion es obligatorio. ");
                
            }
            
            if (ordenPro.getOrdenproducciondetalleList() == null
                    || ordenPro.getOrdenproducciondetalleList().isEmpty()) {
                throw new Exception("La orden de produccion no tiene detalle.");
            }
            for (Ordenproducciondetalle 
                    object : ordenPro.getOrdenproducciondetalleList()) {
                ExistenciaPK idPKExis = new ExistenciaPK();
                idPKExis.setIdproducto(object.getIdproducto().getIdproducto());
                idPKExis.setIdsucursal(object.getOrdenproducciondetallePK().getIdSucursal());
                Existencia exis = crudBDC.buscarEntidad(Existencia.class, idPKExis);
                if (exis == null) {
                    throw new Exception("El articulo no tiene existencia.");
                }
                ejbProInv.afectarExistencia(object.getCantidadconfirmada(),
                        object.getIdproducto(), ordenPro.getIdusuario(),
                        ordenPro.getSucursal(), 
                        object.getCostounitario(), false, false);
                
            }
            crudBDC.guardarEntidad(ordenPro);
            utx.commit();
        } catch (Exception e) {
            if (utx.getStatus() != Status.STATUS_NO_TRANSACTION) {
                utx.rollback();
            }
            throw e;
        }

    }
    
    
}

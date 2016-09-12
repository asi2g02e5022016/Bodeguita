/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.negocio.utilidades;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

/**
 *
 * @author Samaellopez
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CudBMT implements CudBMTLocal {

    @Resource
    private UserTransaction utx;
     /**EJB relacionado a las operaciones CRUD de POS.*/
    @EJB
    private CrudBDCLocal crud;
    
       /**
     * Permite persistir una entidad, esta operación solo permite hacer un
     * "INSERT" y no verifica la existencia de la entidad en la tabla. Por
     * tanto podría ocacionar un error de constraint de llave única.
     * @param entidad Entidad a persistir.
     * @throws java.lang.Exception Error genérico.
     */
    @Override
    public final void persistirEntidad(final Object entidad) throws Exception {
        try {
          utx.begin();
          crud.persistirEntidad(entidad);
          utx.commit();
        } catch (Exception e) {
          if (utx.getStatus() != Status.STATUS_NO_TRANSACTION) {
              utx.rollback();
          }
          throw e;
        }
    }

    /**
     * Permite guardar una entidad, esta operación verifica la existencia
     * del registro en la tabla, de existir ejecuta un "UPDATE", caso contrario
     * ejecuta un INSERT.
     * @param <T> Genérico.
     * @param entidad Entidad a guardar.
     * @return Entidad
     * @throws java.lang.Exception Error genérico.
     */
    @Override
    public final < T > T guardarEntidad(final Object entidad) throws Exception {
        try {
          utx.begin();
          T t = (T) crud.guardarEntidad(entidad);
          utx.commit();
          return t;
        } catch (Exception e) {
          if (utx.getStatus() != Status.STATUS_NO_TRANSACTION) {
              utx.rollback();
          }
          throw e;
        }
    }

    /**
     * Ejecuta la operación guardarEntidad sobre una lista de entidades.
     * @param entidades Lista de entidades.
     * @return true en caso de exito.
     * @throws java.lang.Exception Error genérico.
     */
    @Override
    public final boolean guardarEntidades(final List entidades)
           throws Exception {
        boolean ok = false;
        try {
          utx.begin();
          ok = crud.guardarEntidades(entidades);
          utx.commit();
        } catch (Exception e) {
          if (utx.getStatus() != Status.STATUS_NO_TRANSACTION) {
              utx.rollback();
          }
          ok = false;
        }
        return ok;
    }

    /**
     * Permite eliminar una entidad del modelo del dominio, asi como de la
     * base de datos.
     * @param entidad Entidad a eliminar.
     * @return true en caso de exito.
     * @throws java.lang.Exception Error genérico.
     */
    @Override
    public final boolean eliminarEntidad(final Object entidad)
           throws Exception {
        boolean ok = false;
        try {
          utx.begin();
          ok = crud.eliminarEntidad(entidad);
          utx.commit();
        } catch (Exception e) {
          if (utx.getStatus() != Status.STATUS_NO_TRANSACTION) {
              utx.rollback();
          }
          ok = false;
        }
        return ok;
    }

    /**
     * Permite ejecutar la acción eliminarEntidad sobre una lista de entidades.
     * @param entidades Lista de entidades a eliminar.
     * @return true en caso de exito
     * @throws java.lang.Exception Error genérico.
     */
    @Override
    public final boolean eliminarEntidades(final List entidades)
           throws Exception {
        boolean ok = false;
        try {
          utx.begin();
          ok = crud.eliminarEntidades(entidades);
          utx.commit();
        } catch (Exception e) {
          if (utx.getStatus() != Status.STATUS_NO_TRANSACTION) {
              utx.rollback();
          }
          ok = false;
        }
        return ok;
    }
    
}

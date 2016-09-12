/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.negocio.utilidades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Samaellopez
 */
@Stateless
public class CrudBDC implements CrudBDCLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    
    // <editor-fold defaultstate="collapsed" desc="Métodos de Negocio">
    /**
     * Método que permite buscar una entidad del modelo del dominio, tanto en
     * el contexto y base de datos.
     * @param <T> Generico
     * @param clazz Tipo de clase a retornar.
     * @param key Objeto de llave primaria
     * @return Entidad DTA
     * @throws java.lang.Exception Error genérico.
     */
    @Override
    public final < T > T buscarEntidad(final Class clazz, final Object key)
           throws Exception {
        if (clazz == null || key == null) { return null; }
        return (T) em.find(clazz, key);
    }

    /**
     * Permite persistir una entidad, esta operación solo permite hacer un
     * "INSERT" y no verifica la existencia de la entidad en la tabla. Por
     * tanto podría ocacionar un error de constraint de llave única.
     * @param entidad Entidad a persistir.
     * @throws java.lang.Exception Error genérico.
     */
    @Override
    public final void persistirEntidad(final Object entidad) throws Exception {
        em.persist(entidad);
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
        if (entidad == null) { return null; }
        T t = (T) em.merge(entidad);
        return t; // Si todo bien retorna la entidad almacenada ;)
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
        if (entidades == null || (entidades != null && entidades.isEmpty())) {
            return ok;
        }
        for (Object entidad : entidades) {
             em.merge(entidad);
        }
        ok = true; // EEEXXXIIIITTOOO :P
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
        if (entidad == null) { return ok; }
        em.remove(em.merge(entidad));
        ok = true;
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
        if (entidades == null || entidades.isEmpty()) {
            return false;
        }
        for (Object entidad : entidades) {
             em.remove(em.merge(entidad));
        }
        return true;
    }
    // </editor-fold>
}

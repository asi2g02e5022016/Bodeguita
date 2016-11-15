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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JAEM
 */
@Stateless
public class BusquedaProgramacionTareas implements BusquedaProgramacionTareasLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    /**
     * Obtener el ultimo id de ajuste.
     *
     * @param codsuc codigo de sucursal.
     * @param clase Clase que se quiere obtenerel correlativo.
     * @param identificador Identificados
     * @return Integrt.
     * @throws Exception Error gnerico.
     */
    @Override
    public Integer obtenerCorreltivoAjuste(Class clase) throws Exception {
        Integer valor;
//        System.out.println("clase.getName()..." + clase.getName());
//        System.out.println("clase.getSimpleName().." + clase.getSimpleName());
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idprogramacion) FROM ");
        slq.append(clase.getSimpleName());
//        slq.append(" where ");
//        slq.append(identificador);
//        slq.append(" = ?1");
        Query query = em.createNativeQuery(slq.toString());
//        query.setParameter(1, codsuc);
        valor = (Integer) query.getSingleResult();
        if (valor == null) {
            valor = Integer.parseInt("1");
        }
        valor = valor + 1;
        return valor;
    }

    @Override
    public List<Tarea> buscarTarea() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Tarea a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    @Override
    public List<Configuracion> buscarConfiguracion() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Configuracion a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    @Override
    public List<Tipotarea> buscarTipoTarea() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Tipotarea a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    @Override
    public List<Programaciontareas> buscarProgramacionTareas() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Programaciontareas a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    @Override
    public List<Programaciondetalle> buscarProgramacionDetalle() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Programaciondetalle a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    @Override
    public List<Programaciondetalle> buscarProgramacionDetalle(Integer idProgramacion) throws Exception {
        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append(" SELECT a FROM Programaciondetalle a WHERE 1 = 1 ");
            if (idProgramacion > 0) {
                jpql.append(" AND a.idprogramacion.idprogramacion = :idprogramacion ");
            }
            Query query = em.createQuery(jpql.toString());
            // asignamos valores a los parametros del query 
            if (idProgramacion > 0) {
                query.setParameter("idprogramacion", idProgramacion);
            }
            return query.getResultList();
        } catch (NoResultException ne) {
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public String buscarNombreTarea(Integer idTarea) throws Exception {
        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append(" SELECT a.nombre FROM Tarea a WHERE 1 = 1 ");
            if (idTarea > 0) {
                jpql.append(" AND a.idtarea = :idtarea ");
            }
            Query query = em.createQuery(jpql.toString());
            // asignamos valores a los parametros del query 
            if (idTarea > 0) {
                query.setParameter("idtarea", idTarea);
            }

            return query.getResultList().toString();
        } catch (NoResultException ne) {
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public String buscarNombreHost(Integer host) throws Exception {
        try {
            StringBuilder jpql = new StringBuilder();
            jpql.append(" SELECT a.descripcion FROM Configuracion a WHERE 1 = 1 ");
            if (host > 0) {
                jpql.append(" AND a.idconfiguracion = :idconfiguracion ");
            }
            Query query = em.createQuery(jpql.toString());
            // asignamos valores a los parametros del query 
            if (host > 0) {
                query.setParameter("idconfiguracion", host);
            }

            return query.getResultList().toString();
        } catch (NoResultException ne) {
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}

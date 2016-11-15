/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantbcd.modelo.Usuario;
import com.asi.restaurantbcd.modelo.Empleado;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author "Joaquin Sanchez SA101110"
 */
@Stateless
public class BusquedasUsuarios implements BusquedasUsuariosLocal {

    /**
     * Unidad de persistencia asociada a la coneccion.
     */
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    /**
     * Obtener la lista de los usuarios ingresados
     *
     * @param filtroUsr
     * @return LCorrelativo Siguinte.
     * @throws Exception Error generico.
     */
    /*@Override
    public List<Usuario> buscarUsuario() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Usuario a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }    
    @Override
    public List<Perfil> buscarPerfil() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Perfil a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }    
    @Override
    public List<Empleado> buscarEmpleado() throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Empleado a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    } */
    @Override
    public List<Usuario> buscarUsuario(Map filtroUsr) throws Exception {
        /*if (filtroUsr == null) {
            return null;
        }*/
        StringBuilder jpql = new StringBuilder();
        try {

            String codusr = Utilidades.getParemetro("codusr", filtroUsr);
            jpql.append("SELECT a FROM Usuario a where 1 = 1");

            if (codusr != null) {
                jpql.append(" AND a.idusuario = :codusr");
            }
            Query query = em.createQuery(jpql.toString());
            if (codusr != null) {
                query.setParameter("codusr", codusr);
            }
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Perfil> buscarPerfil(Map filtroPfl) throws Exception {
        /*if (filtroPfl == null) {
            return null;
        }*/
        StringBuilder jpql = new StringBuilder();
        try {
            String codpfl = Utilidades.getParemetro("codpfl", filtroPfl);
            jpql.append("SELECT a FROM Perfil a where 1 = 1");

            if (codpfl != null) {
                jpql.append(" AND a.idperfil = :codpfl");
            }
            Query query = em.createQuery(jpql.toString());
            if (codpfl != null) {
                query.setParameter("codpfl", codpfl);
            }
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (Exception e) {
            throw e;
        }     
    }

    @Override
    public List<Empleado> buscarEmpleado(Map filtroEmp) throws Exception {
        /*if (filtroEmp == null) {
            return null;
        }*/
        StringBuilder jpql = new StringBuilder();
        try {
            String codemp = Utilidades.getParemetro("codemp", filtroEmp);
            jpql.append("SELECT a FROM Empleado a where 1 = 1");

            if (codemp != null) {
                jpql.append(" AND a.idempleado = :codemp");
            }
            Query query = em.createQuery(jpql.toString());
            if (codemp != null) {
                query.setParameter("codemp", codemp);
            }
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        } catch (Exception e) {
            throw e;
        }          
    }

}

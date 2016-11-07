/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Ajuste;
import com.asi.restaurantbcd.modelo.Ajustedetalle;
import com.asi.restaurantbcd.modelo.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JAEM
 */
@Stateless
public class BusquedaAjustesExistencia implements BusquedaAjustesExistenciaLocal {
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    /**
     * Obtener el ultimo id de ajuste.
     * @param codsuc codigo de sucursal.
     * @param clase Clase que se quiere obtenerel correlativo.
     * @param identificador Identificados 
     * @return Integrt.
     * @throws Exception  Error gnerico.
     */
   
    @Override
    public Integer obtenerCorreltivoAjuste(Integer codsuc, Class clase,
            String identificador) throws Exception {
        Integer valor;
        System.out.println("clase.getName()..."+clase.getName());
        System.out.println("clase.getSimpleName().." + clase.getSimpleName());
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idajuste) FROM ");
        slq.append(clase.getSimpleName());
        slq.append(" where ");
         slq.append(identificador);
         slq.append(" = ?1");
        Query query = em.createNativeQuery(slq.toString());
        query.setParameter(1, codsuc);
        valor = (Integer) query.getSingleResult();
        if (valor == null) {
            valor = Integer.parseInt("1");
        }
        valor   =  valor + 1;
        return valor;
    }
    

    @Override
    public List<Ajuste> buscarAjustesExistenciaEnc () throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT  a FROM Ajuste a  ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
    @Override
    public List<Ajustedetalle> buscarAjustesExistenciaDet () throws Exception {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Ajustedetalle a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
    
     /**
     * Buscar Producto
     *
     * @return Lista de producto.
     * @throws Exception Error generico.
     */
    @Override
    public List<Producto> buscarProducto() throws Exception {
         StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Producto a ");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
        
     

    }
}

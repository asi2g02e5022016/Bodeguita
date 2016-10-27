/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Compra;
import com.asi.restaurantbcd.modelo.Proveedor;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Samael lopez
 */
@Stateless
public class BusquedasCompras implements BusquedasComprasLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em; 
    
    /**
     * Obtener el ultimovalor de la compra.
     * @param codsuc codigo de sucursal.
     * @param clase Clase que se quiere obtenerel correlativo.
     * @param identificador Identificados 
     * @return Integrt.
     * @throws Exception  Error gnerico.
     */
   
    @Override
    public Integer obtenerCorreltivoCompra(Integer codsuc, Class clase,
            String identificador) throws Exception {
        Integer valor;
        System.out.println("clase.getName()..."+clase.getName());
        System.out.println("clase.getSimpleName().." + clase.getSimpleName());
        StringBuilder slq = new StringBuilder();
        slq.append("SELECT MAX(idcompra) FROM ");
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
    /**
     * Buscar Proveedores por medio de una MAP filtros.
     * @param map Map.
     * @return Lista de proveedores.
     * @throws Exception  Error generico.
     */
    @Override
    public List <Proveedor> buscarProveedores(Map map) throws Exception {
        if (map == null){
            return null;
        }
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Proveedor a ");
        if (map.containsKey("nombre") && map.get("nombre") != null) {
            jpql.append(" WHERE a.proveedor = :nombre ");
        }
        Query query = em.createQuery(jpql.toString());
        if (map.containsKey("nombre") && map.get("nombre") != null) {
            query.setParameter("nombre", map.get("nombre").toString());
        }
        return query.getResultList();
    }
    
    /**
     * 
     * @param map
     * @return
     * @throws Exception 
     */
    public List <Compra> buscarCompras(Map map) throws Exception {
        if (map == null || map.isEmpty()){
            return null;
        }
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT a FROM Compra a ");
          jpql.append(" WHERE 1 = 1 ");
        if (map.containsKey("idcompra") && map.get("idcompra") != null) {
            jpql.append(" AND a.idcompra = :idcompra ");
        }
        if (map.containsKey("fechaInicial")
                && map.get("fechaInicial") != null
                && map.containsKey("fechaFinal")
                && map.get("fechaFinal") != null) {
            jpql.append(" AND o.fechacompra between :fechaInicial and :fechaFinal ");
        }
        Query query = em.createQuery(jpql.toString());
        if (map.containsKey("idcompra") && map.get("idcompra") != null) {
            query.setParameter("idcompra", Integer.parseInt(map.get("idcompra")
                    .toString().trim()));
        }
        if (map.containsKey("fechaInicial")
                && map.get("fechaInicial") != null
                && map.containsKey("fechaFinal")
                && map.get("fechaFinal") != null) {
            query.setParameter("fechaInicial", (Date) map.get("fechaInicial"));
            query.setParameter("fechaFinal", (Date) map.get("fechaFinal"));
        }
        return query.getResultList();
    }
}



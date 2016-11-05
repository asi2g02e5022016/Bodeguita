/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.base;

import com.asi.restaurantbcd.modelo.Medida;
import com.asi.restaurantbcd.modelo.Producto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author samaelopez
 */
@Stateless
public class Converciones implements ConvercionesLocal {

    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;

    
    
public Double getValorConvercion(Medida medidaInicial, Medida medidaFinal, 
        Double cantidad) throws Exception {
    try {

      Double factor = null;
      
      //Validar si no se pide convertir medidas base
      if(medidaInicial.isMedidaBase() && medidaFinal.isMedidaBase()){
          throw new Exception("No se pueden realizar conversión de dos medidas base");
      }
      
      //Validar si la conversion es valida
      if(!medidaInicial.getMedidaPadre().equals(medidaFinal.getMedidaPadre())){
          throw new Exception("No existe conversión de la medida" + medidaInicial.getReferencia() + " a la medida " + medidaFinal.getReferencia());
      }
      
      if(medidaInicial.getMedidaPadre().equals(medidaFinal)) {
         factor = cantidad * medidaInicial.getConversion();
      }
      else {
         factor = cantidad * medidaInicial.getConversion();
         factor = factor/medidaFinal.getConversion();
      }
    
    return factor;
            
    } catch (Exception e) {
        throw new Exception(e);
    }
 }
/**
 * 
 * @return 
 */
private Double  obtenerfactor(Integer medIni , Integer medFin) {
    Double dofactor;
    StringBuilder jpql = new StringBuilder();
    jpql.append("    select  conversion from medida ");
     jpql.append("   where medidabase = ?1 ");
     jpql.append("   and idmedida = ?2 ");
    Query q = em.createNativeQuery(jpql.toString());
    System.out.println("factor.." +  q.getSingleResult());
    q.setParameter(1, medIni);
    q.setParameter(2, medFin);
    
    return  (Double) q.getSingleResult();
            }
} 

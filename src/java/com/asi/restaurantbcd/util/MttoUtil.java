/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.util;

import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luis_portillo
 */
public abstract class MttoUtil<E> {
    
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;  
    
    private List<E> resultList;
    private String jpql;
    
    private E instance;
    
    private Object key;
 
    @EJB
    CrudBDCLocal ejbCrud;
    
     @SuppressWarnings("unchecked")
	public List<E> getResultList() {
		resultList = em.createQuery(jpql).getResultList();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<E> getResultList(Integer firstResult,Integer maxResult) {		
		resultList = em.createQuery(jpql)
				.setFirstResult(firstResult)
				.setMaxResults(maxResult)
				.getResultList();
		return resultList;
	}

	public void setResultList(List<E> resultList) {
		this.resultList = resultList;
	}

	public String getJpql() {
		return jpql;
	}

	public void setJpql(String jpql) {
		this.jpql = jpql;
	}

    /**
     * @return the instance
     */
    public E getInstance() {
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public void setInstance(E instance) {
        this.instance = instance;
    }

    /**
     * @return the key
     */
    public Object getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(Object key) {
        this.key = key;
    }
    
    public void loadInstance() {
       if (instance == null && key != null) {
           try {
               System.out.println("Ejecutado loadInstance key = " + key + " ---");
               instance = ejbCrud.buscarEntidad(this.getNew().getClass(), key);
           } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, ex);
           }
       } else{
          instance = getNew();
       }
    }
    
    public void load(){
         onLoad();
         loadInstance();
         System.out.println("Instance key = " + instance.toString() + " ---");
    };
    
    protected abstract E getNew();
    
    protected abstract void onLoad();
    
    

}

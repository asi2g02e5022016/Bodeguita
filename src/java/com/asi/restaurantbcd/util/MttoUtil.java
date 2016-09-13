/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.util;

import java.util.List;
import javax.annotation.Resource;
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
        
}

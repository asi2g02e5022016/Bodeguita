package com.asi.restaurantebcd.negocio.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class QueryUtil<E> {
	private List<E> resultList;
	private String jpql;
        
	@PersistenceContext(unitName = "RestaurantBDC-WebPU")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<E> getResultList() {
		resultList = entityManager.createQuery(jpql).getResultList();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<E> getResultList(Integer firstResult,Integer maxResult) {		
		resultList = entityManager.createQuery(jpql)
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

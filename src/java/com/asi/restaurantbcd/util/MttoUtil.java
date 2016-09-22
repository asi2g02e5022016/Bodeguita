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
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
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
    
    private Object action;
    
    private FacesContext facesContext;
    
    protected final Severity ERROR = FacesMessage.SEVERITY_ERROR;
 
    protected final Severity INFO = FacesMessage.SEVERITY_INFO;
    
    protected final Severity WARNING = FacesMessage.SEVERITY_WARN;
    
    @EJB
    CrudBDCLocal ejbCrud;
    
    public MttoUtil(){
        facesContext = FacesContext.getCurrentInstance();
    }
    
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
       if (action != null && key != null && "Editar".equals(action.toString())) {
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
    
   public void save() {
       System.out.println("Guardando----");
       if (instance != null) {
           try {
               ejbCrud.persistirEntidad(instance);
               postSave();
           } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, ex);
               addMessage(ERROR, "Error", ex.getMessage());
           }
       }
   }
    
   @PostConstruct
    public void load(){
         System.out.println("Instance key = " + key + " ---");
         onLoad();
         loadInstance();
         System.out.println("Instance key = " + instance.toString() + " ---");
    };
    
    protected void addMessage(Severity severity, String summary ,String message){
       facesContext.addMessage(null, new FacesMessage(severity, summary, message));
    };
    
    protected abstract E getNew();
    
    protected abstract void onLoad();
    
    protected abstract void postSave();

    /**
     * @return the action
     */
    public Object getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Object action) {
        this.action = action;
    }
    
    

}

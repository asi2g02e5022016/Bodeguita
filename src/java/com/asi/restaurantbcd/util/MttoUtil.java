/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.util;

import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;



public abstract class MttoUtil<E> {
    
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;  
    
    @Inject
    private Conversation conversation;
    
    private List<E> resultList;
    private String jpql;
    
    private E instance;
    
    private FacesContext facesContext;

    
    protected final Severity ERROR = FacesMessage.SEVERITY_ERROR;
 
    protected final Severity INFO = FacesMessage.SEVERITY_INFO;
    
    protected final Severity WARNING = FacesMessage.SEVERITY_WARN;
    
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

        
   public void save() {
       facesContext = FacesContext.getCurrentInstance();
       System.out.println("Guardando----");
       if (instance != null) {
           try {
               if(validateSave()){
                        ejbCrud.persistirEntidad(instance);
                        postSave();
               }
               this.endConversation();
           } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
               beanValidations();
               addMessage(ERROR, "Error", ex.getMessage());
               facesContext.validationFailed();
           }
       }
   }
   
   public void delete() {
              facesContext = FacesContext.getCurrentInstance();
       System.out.println("Eliminando----");
       if (instance != null) {
           try {
/*      
        return null;
    }else{*/
               ejbCrud.eliminarEntidad(instance);
               postDelete();
               this.endConversation();
       
    //}

           } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
               beanValidations();
               facesContext.validationFailed();
           }
       }
   }

   public void update() {
              facesContext = FacesContext.getCurrentInstance();
       System.out.println("Actualizando----");
       if (instance != null) {
           try {

               ejbCrud.guardarEntidad(instance);
               postUpdate();
               this.endConversation();

           } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
               beanValidations();
               facesContext.validationFailed();
           }
       }
   }
   
    public void startModify(Object key){
            this.beginConversation();
            instance = this.select(key);
            prepareUpdate();
    }
   

    public void startNew(){
            this.beginConversation();
            this.instance=this.getNew();
            prepareCreate();
    }
    
    protected void addMessage(Severity severity, String summary ,String message){
        facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(severity, summary, message));
    };
    
    protected abstract E getNew();
    
    protected abstract void prepareCreate();
    
    protected abstract void prepareUpdate();
    
    protected abstract boolean validateSave();
    
    protected abstract boolean validateDelete();

    protected abstract boolean validateUpdate();
    
    protected abstract void postSave();
    
    protected abstract void postDelete();

    protected abstract void postUpdate();

    protected abstract E select(Object key);
    
    private void beanValidations(){
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                 javax.validation.Validator validator = factory.getValidator();
                 Set<ConstraintViolation<E>> constraintViolations = validator.validate(instance);
                 if(constraintViolations.size() > 0){
                     Iterator<ConstraintViolation<E>> iterator = constraintViolations.iterator();
                     while(iterator.hasNext()){
                         ConstraintViolation<E> cv = iterator.next();
                         Logger.getLogger(this.getClass().getName()).log(Level.WARNING,cv.getRootBeanClass().getName()+"."+cv.getPropertyPath() + " " +cv.getMessage());
                         addMessage(WARNING, "Error", cv.getPropertyPath() + " " +cv.getMessage());
                            }
                     }
    }
    
    	public void beginConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}
        
        protected EntityManager getEntityManager(){
          return em;
        }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Grupoproducto;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.MttoUtil;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author siman
 */
@Named(value = "mttoGrupo")
@ConversationScoped
public class MttoGrupoProducto extends MttoUtil<Grupoproducto>implements Serializable{
   
	
    private static final long serialVersionUID = 1L;
    
    private List<Grupoproducto> grupoList;
    
    private Grupoproducto parentGroup;
    
    private boolean newInstance;
    
    @EJB
    CrudBDCLocal ejbCrud;

    public MttoGrupoProducto(){
        setJpql("select g from Grupoproducto g where g.grupodependencia is null order by g.nivel");
    }
    
    @PostConstruct
    public void load(){
       System.out.println("postConstruct");
       this.setNewInstance(false);
      if(this.getGrupoList()==null){
           this.setGrupoList(this.getResultList());
      }
    }
    
    @Override
    protected Grupoproducto getNew() {
        return new Grupoproducto();
    }

    @Override
    protected void prepareCreate() {
       this.setNewInstance(true);
    }

    @Override
    protected void prepareUpdate() {
        System.out.println("prepareConstruct: " + this.getInstance().getGrupoproducto());
        if(this.getInstance().getGrupodependencia() == null){
           this.setParentGroup(this.getInstance());
            this.grupoList = this.getEntityManager()
                                .createQuery("select g from Grupoproducto g where g.grupodependencia = :p order by g.nivel")
                                .setParameter("p", this.getInstance())
                                .getResultList();
            this.setNewInstance(false);
            this.setInstance(null);
        }
    }

    @Override
    protected boolean validateSave() {
        if(this.getParentGroup()==null){
            this.getInstance().setNivel(0);
            this.getInstance().setGrupodependencia(null);
        }else{
           this.getInstance().setNivel(this.getParentGroup().getNivel()+1);  
          this.getInstance().setGrupodependencia(this.getParentGroup());
        }
        return true;
    }

    /**
     * @return the menuList
     */
    public List<Grupoproducto> getGrupoList() {
        return grupoList;
    }

    /**
     * @param menuList the menuList to set
     */
    public void setGrupoList(List<Grupoproducto> grupoList) {
        this.grupoList = grupoList;
    }

    /**
     * @return the parentMenu
     */
    public Grupoproducto getParentGroup() {
        return parentGroup;
    }

    /**
     * @param parentMenu the parentMenu to set
     */
    public void setParentGroup(Grupoproducto parentGroup) {
        this.parentGroup = parentGroup;
    }

    protected boolean validateDelete() {
        return true;
    }

    protected boolean validateUpdate() {
        System.out.println("Validado----");
        /*if(this.getInstance().getUrl()!=null&&!this.getInstance().getUrl().endsWith(".xhtml")){
            addMessage(ERROR, "Error", "URL Invalida");
            this.setMenuList(this.getResultList());
            return false;
        } */
        return true;
    }

    @Override
    protected void postSave() {
        this.setNewInstance(false);
         if(this.getParentGroup()==null){
            this.grupoList = this.getEntityManager()
                                .createQuery("select g from Grupoproducto g where g.grupodependencia is null order by g.nivel")
                                .getResultList();
            
        }else{
            this.grupoList = this.getEntityManager()
                                .createQuery("select g from Grupoproducto g where g.grupodependencia = :p order by g.nivel")
                                .setParameter("p", this.getParentGroup())
                                .getResultList();
        }
    }

    @Override
    protected void postDelete() {
        
    }

    @Override
    protected void postUpdate() {
         System.out.println("Actualizado---- Orden " + this.getInstance().getNivel());
    }

    @Override
    protected Grupoproducto select(Object key) {
                try{  
                if(key!=null){
                        key = Integer.parseInt(key.toString()); //Conversión del Key a tipo numerico
                     } 
                  Grupoproducto result =ejbCrud.buscarEntidad(Grupoproducto.class, key);
                  System.out.println("select: " + result.getGrupoproducto());
		return result;
               } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
               return null;
           }
    }
    
     public void onRowEdit(RowEditEvent event) {
        this.setInstance((Grupoproducto) event.getObject());
        update();

    }
     
    public void onRowCancel(RowEditEvent event) {

    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {

    }
    }

    /**
     * @return the newInstance
     */
    public boolean isNewInstance() {
        return newInstance;
    }

    /**
     * @param newInstance the newInstance to set
     */
    public void setNewInstance(boolean newInstance) {
        this.newInstance = newInstance;
    }
    
    public void goToParent(){
         this.setNewInstance(false);
         this.setParentGroup(this.getParentGroup().getGrupodependencia());
         
       if(this.getParentGroup()==null){
            this.grupoList = this.getEntityManager()
                                .createQuery("select g from Grupoproducto g where g.grupodependencia is null order by g.nivel")
                                .getResultList();
            
        }else{
            this.grupoList = this.getEntityManager()
                                .createQuery("select g from Grupoproducto g where g.grupodependencia = :p order by g.nivel")
                                .setParameter("p", this.getParentGroup())
                                .getResultList();
        }
    }
    
    public void immediateDelete(Grupoproducto grupo){
      this.setInstance(grupo);
      this.delete();
             if(this.getParentGroup()==null){
            this.grupoList = this.getEntityManager()
                                .createQuery("select g from Grupoproducto g where g.grupodependencia is null order by g.nivel")
                                .getResultList();
            
        }else{
            this.grupoList = this.getEntityManager()
                                .createQuery("select g from Grupoproducto g where g.grupodependencia = :p order by g.nivel")
                                .setParameter("p", this.getParentGroup())
                                .getResultList();
        }
      
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Opcionmenu;
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
@Named(value = "mttoMenu")
@ConversationScoped
public class MttoMenu extends MttoUtil<Opcionmenu>implements Serializable{
   
	
    private static final long serialVersionUID = 1L;
    
    private List<Opcionmenu> menuList;
    
    private Opcionmenu parentMenu;
    
    private boolean newInstance;
    
    @EJB
    CrudBDCLocal ejbCrud;

    public MttoMenu(){
        setJpql("select m from Opcionmenu m where m.menuPadre is null order by m.orden");
    }
    
    @PostConstruct
    public void load(){
       System.out.println("postConstruct");
       this.setNewInstance(false);
      if(this.getMenuList()==null){
           this.setMenuList(this.getResultList());
      }
    }
    
    @Override
    protected Opcionmenu getNew() {
        return new Opcionmenu();
    }

    @Override
    protected void prepareCreate() {
       this.setNewInstance(true);
    }

    @Override
    protected void prepareUpdate() {
        System.out.println("prepareConstruct: " + this.getInstance().getEtiqueta());
        if(this.getInstance().isParentMenu() || true){
           this.setParentMenu(this.getInstance());
            this.menuList = this.getEntityManager()
                                .createQuery("select m from Opcionmenu m where m.menuPadre = :p order by m.orden")
                                .setParameter("p", this.getInstance())
                                .getResultList();
            this.setNewInstance(false);
            this.setInstance(null);
        }
    }

    @Override
    protected boolean validateSave() {
        if(this.getParentMenu()==null){
            this.getInstance().setMenuPadre(null);
        }else{
          this.getInstance().setMenuPadre(this.getParentMenu());
        }
        return true;
    }

    /**
     * @return the menuList
     */
    public List<Opcionmenu> getMenuList() {
        return menuList;
    }

    /**
     * @param menuList the menuList to set
     */
    public void setMenuList(List<Opcionmenu> menuList) {
        this.menuList = menuList;
    }

    /**
     * @return the parentMenu
     */
    public Opcionmenu getParentMenu() {
        return parentMenu;
    }

    /**
     * @param parentMenu the parentMenu to set
     */
    public void setParentMenu(Opcionmenu parentMenu) {
        this.parentMenu = parentMenu;
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
         if(this.getParentMenu()==null){
            this.menuList = this.getEntityManager()
                                .createQuery("select m from Opcionmenu m where m.menuPadre is null order by m.orden")
                                .getResultList();
            
        }else{
            this.menuList = this.getEntityManager()
                                .createQuery("select m from Opcionmenu m where m.menuPadre = :p order by m.orden")
                                .setParameter("p", this.getParentMenu())
                                .getResultList();
        }
    }

    @Override
    protected void postDelete() {
        
    }

    @Override
    protected void postUpdate() {
         System.out.println("Actualizado---- Orden " + this.getInstance().getOrden());
    }

    @Override
    protected Opcionmenu select(Object key) {
                try{  
                if(key!=null){
                        key = Integer.parseInt(key.toString()); //Conversión del Key a tipo numerico
                     } 
                  Opcionmenu result =ejbCrud.buscarEntidad(Opcionmenu.class, key);
                  System.out.println("select: " + result.getEtiqueta());
		return result;
               } catch (Exception ex) {
               Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
               return null;
           }
    }
    
     public void onRowEdit(RowEditEvent event) {
        this.setInstance((Opcionmenu) event.getObject());
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
         this.setParentMenu(this.getParentMenu().getMenuPadre());
         
       if(this.getParentMenu()==null){
            this.menuList = this.getEntityManager()
                                .createQuery("select m from Opcionmenu m where m.menuPadre is null order by m.orden")
                                .getResultList();
            
        }else{
            this.menuList = this.getEntityManager()
                                .createQuery("select m from Opcionmenu m where m.menuPadre = :p order by m.orden")
                                .setParameter("p", this.getParentMenu())
                                .getResultList();
        }
    }
    
    public void immediateDelete(Opcionmenu menu){
      this.setInstance(menu);
      this.delete();
             if(this.getParentMenu()==null){
            this.menuList = this.getEntityManager()
                                .createQuery("select m from Opcionmenu m where m.menuPadre is null order by m.orden")
                                .getResultList();
            
        }else{
            this.menuList = this.getEntityManager()
                                .createQuery("select m from Opcionmenu m where m.menuPadre = :p order by m.orden")
                                .setParameter("p", this.getParentMenu())
                                .getResultList();
        }
      
    }
}

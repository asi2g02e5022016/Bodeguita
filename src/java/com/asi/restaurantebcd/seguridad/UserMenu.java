/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.seguridad;

import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

/**
 *
 * @author luis_portillo
 */
@ManagedBean(name = "userMenu")
@SessionScoped
public class UserMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    private MenuModel model;
    
    	

    HashMap<Integer, Submenu> addedMenus;
    
    @PostConstruct
    public void loadUserMenus() {
	model = new DefaultMenuModel();
           
        DefaultMenuItem item = new DefaultMenuItem("Inicio");
        item.setUrl("/home.xhtml");
         
        model.addElement(item);
         
        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Seguridad");
 
        item = new DefaultMenuItem("Menu");
        item.setUrl("/mantenimientos/admin/opcionMenu/lista.xhtml");
        secondSubmenu.addElement(item);
        
        
        item = new DefaultMenuItem("Usuario");
        item.setUrl("/mantenimientos/MttoUsuarios.xhtml");
        secondSubmenu.addElement(item);
         
      
        model.addElement(secondSubmenu);

	}

    /**
     * @return the model
     */
    public MenuModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(MenuModel model) {
        this.model = model;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.seguridad;

import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import com.asi.restaurantbcd.modelo.Opcionmenu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis_portillo
 */
@Named(value = "userMenu")
@SessionScoped
public class UserMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    private MenuModel model;
    
    private List<Opcionmenu> menusUsuario = new ArrayList<Opcionmenu>();
    
    @Inject
    SessionUsr sesion;
    
    @Inject
    private MenuChildList childList;

    HashMap<Integer, DefaultSubMenu> addedMenus;
    
    @PostConstruct
    public void loadUserMenus() {
	model = new DefaultMenuModel();
           
        DefaultMenuItem item = new DefaultMenuItem("Inicio");
        item.setUrl("/home.xhtml");
         
        model.addElement(item);
        
        if(sesion==null || sesion.getToken()==null){
        item = new DefaultMenuItem("Log In");
        item.setUrl("/Loggin.xhtml");
        model.addElement(item);
        } 
        else {  
           for (Opcionmenu om : sesion.getUsuario().getIdperfil().getOpcionesDeMenu()) {
				if (!menusUsuario.contains(om))
					menusUsuario.add(om);
          }
           
          for (Opcionmenu om : childList.getResultList()) {
				if (menusUsuario.contains(om)) {
					buildMenuItem(om);
			}
		}
        item = new DefaultMenuItem("Log Out");
        item.setCommand("{logginBean.logOut()}");
        item.setAjax(false);
        item.setImmediate(true);
        model.addElement(item);    
        }
         
       
        

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
    
    	private void buildMenuItem(Opcionmenu om) {
		
		if (!om.isRootMenu() && !addedMenus.containsKey(om.getMenuPadre().getId()))
			buildMenuItem(om.getMenuPadre());
		
		if (om.isParentMenu()) {
			DefaultSubMenu submenu = new DefaultSubMenu();
			submenu.setLabel(om.getEtiqueta());
			if (om.isRootMenu()) {
				model.addElement(submenu);
				submenu.setStyleClass("ui-root-menu-item");
			}else{				
				addedMenus.get(om.getMenuPadre().getId()).getElements().add(submenu);
			}			
			addedMenus.put(om.getId(), submenu);
		} else {
			DefaultMenuItem item = new DefaultMenuItem();
			item.setUrl(om.getUrl());
			item.setValue(om.getEtiqueta());
			addedMenus.get(om.getMenuPadre().getId()).getElements().add(item);
		}

	}

    
    
}

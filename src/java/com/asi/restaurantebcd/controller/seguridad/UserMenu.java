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
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

/**
 *
 * @author luis_portillo
 */
@Named(value = "userMenu")
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
        
        item = new DefaultMenuItem("Perfil");
        item.setUrl("/mantenimientos/admin/perfil/lista.xhtml");
        secondSubmenu.addElement(item);
         model.addElement(secondSubmenu);
         
                 // Menu de mantenimientos.
        DefaultSubMenu menuMtto = new DefaultSubMenu("Mantenimientos");
        
        item = new DefaultMenuItem("Estado");
        item.setUrl("/mantenimientos/MttoEstado.xhtml");
        menuMtto.addElement(item);
        
        item = new DefaultMenuItem("Companias");
        item.setUrl("/mantenimientos/MttoCompania.xhtml");
        menuMtto.addElement(item);
        
        item = new DefaultMenuItem("Sucursales");
        item.setUrl("/mantenimientos/MttoSucursales.xhtml");
        menuMtto.addElement(item);
                     
         model.addElement(menuMtto);
         
         ////////////
        
        DefaultSubMenu terserSubmenu = new DefaultSubMenu("Compras");
       
        item = new DefaultMenuItem("Compras");
        item.setUrl("/compras/CompraProductos.xhtml");
        terserSubmenu.addElement(item);
        model.addElement(terserSubmenu);
        
        
        
        //INVENTARIO
        DefaultSubMenu invExistencia = new DefaultSubMenu("Inventario");
       
        item = new DefaultMenuItem("Inventario");
        item.setUrl("/inventario/InvExistenciaBean.xhtml");
        invExistencia.addElement(item);
        model.addElement(invExistencia);

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

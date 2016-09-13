/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

   
import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantbcd.util.MttoUtil;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author luis_portillo
 */
@Named(value = "mttoPerfil")
@RequestScoped
public class MttoPerfilMB extends MttoUtil<Perfil> {
   

    public MttoPerfilMB() { 
        setJpql("select p from Perfil p");
    }

    
    
    
}


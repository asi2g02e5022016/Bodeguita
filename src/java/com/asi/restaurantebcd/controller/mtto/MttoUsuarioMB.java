/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author desaext1
 */
@Named(value = "mttoUsuario")
@RequestScoped
public class MttoUsuarioMB {

       //<editor-fold  defaultstate="collapsed" desc="constructor" >
    /**
     * Creates a new instance of MttoUsuarioManagedBean
     */
    public MttoUsuarioMB() {
    }
    //</editor-fold >
       
       //<editor-fold  defaultstate="collapsed" desc="Variable globales" >
   private String codigoUsr;
   private String contrasenaUsr;
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
   private List lstPerfile = new ArrayList();
    private String mensaje;
    //</editor-fold >
   
   public void btnGuardarUsuario() {
       System.out.println("aqui estoy");
       if (codigoUsr == null || codigoUsr.equals("")){
           
            RequestContext requestContext = RequestContext.getCurrentInstance();  
  requestContext.execute("PF('dialog').show();");
  mensaje = "El odigo de usuario es bligatorio.";
           return;
       }
       if (contrasenaUsr == null) {
           System.out.println("");
       }
       Usuario usr =  new Usuario();
       usr.setIdUsuario(codigoUsr);
       usr.setClave(codigoUsr);
       persist(usr);
       
   }
   
   
    //<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
   public String getCodigoUsr() {
        return codigoUsr;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setCodigoUsr(String codigoUsr) {
        this.codigoUsr = codigoUsr;
    }

    public String getContrasenaUsr() {
        return contrasenaUsr;
    }

    public void setContrasenaUsr(String contrasenaUsr) {
        this.contrasenaUsr = contrasenaUsr;
    }

    public List getLstPerfile() {
        return lstPerfile;
    }

    public void setLstPerfile(List lstPerfile) {
        this.lstPerfile = lstPerfile;
    }
    //</editor-fold >

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

 
    
}

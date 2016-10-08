/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Usuario;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author samael lopez
 */
@Named(value = "mttoUsuario")
@RequestScoped
public class MttoUsuarioMB implements Serializable {

      //<editor-fold  defaultstate="collapsed" desc="Constructor" >
    /**
     * Creates a new instance of MttoUsuarioManagedBean
     */
    public MttoUsuarioMB() {
    }
    //</editor-fold >
   
      //<editor-fold  defaultstate="collapsed" desc="Variables" >
   private String codigoUsr;
   private String contrasenaUsr;
   private List lstPerfile = new ArrayList();
   private String mensaje;
   @EJB
   CrudBDCLocal ejbCrud;
   
    //</editor-fold >
   
      //<editor-fold  defaultstate="collapsed" desc="Metodos" >
   /**
    * Metodo que guarda un usuaio.
    */
   public void btnGuardarUsuario() {
        try {
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
            usr.setIdusuario(codigoUsr);
            usr.setClave(codigoUsr);
            ejbCrud.guardarEntidad(usr);
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
       
   }
       //</editor-fold >
   
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
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.seguridad;

import com.asi.restaurantbcd.modelo.Usuario;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

/**
 *
 * @author samaelopez
 */


@ManagedBean( name = "logginBean")
@ConversationScoped
public class LogginBean implements Serializable {

    /**
     * Creates a new instance of LogginBean
     */
   private String usuario;
   private String password;
   private Dialog dialogo;
    SessionUsr sesion = Utilidades.findBean("sessionBean");
   private String mensaje;
   @EJB
   private CrudBDCLocal crud;
    public LogginBean() {
        
    }
    
    
    
    public String ingresarSistema() {
        System.out.println("entroo...");
       try {
           if (usuario == null
                   || usuario.trim().equals("")) {
               mensaje = "El codigo de usuario es obligatorio.";
               System.out.println("usr.." + usuario);
                 alert(mensaje,FacesMessage.SEVERITY_INFO );
               return null;
               
           }
           if (password == null
                   || password.trim().equals("")) {
               mensaje = "El password de usuario es obligatorio.";
                alert(mensaje,FacesMessage.SEVERITY_INFO );
               return null;
               
           }
           System.out.println(" password.hashCode().. "
                   +  password.hashCode());
           Usuario usr = crud.buscarEntidad(Usuario.class, usuario);
           if (usr != null) {
               System.out.println("password.hashCode().." +password.hashCode());
               System.out.println("usr.getClave().." +usr.getClave());
                  if (!String.valueOf(
                   password.hashCode()).equals(usr.getClave()) ) { 
                      mensaje ="El usuario o clave son invalidos ";
                alert(mensaje,FacesMessage.SEVERITY_INFO );
               return null;
               
             }
                  if (sesion == null) {
               sesion = new SessionUsr();
           }
                  String token = 
                          usr.getIdusuario()+ usr.getClave() + new Date().toString();
               //sesion.setCodusr(usuario);
               sesion.setFecha(new Date());
              // sesion.setSucursal(BigInteger.ONE);
               sesion.setToken(token);
               sesion.setFecha(new Date());
               sesion.setCompania(usr.getIdempleado().getIdsucursal().getIdcompania());
               sesion.setEmpleSucursal(usr.getIdempleado());
               sesion.setPerfil(usr.getIdperfil());
               System.out.println("usr.getIdEmpleado().."+usr.getIdempleado());
               //sesion.setSucursal(usr.get);
               sesion.setUsuario(usr);
           
           
           } else {
               mensaje = "No existe usuario.";
                 alert(mensaje,FacesMessage.SEVERITY_INFO );
               return null;
           }
           

           
           return "/home?faces-redirect=true";
      
       } catch (Exception ex) {
           Logger.getLogger(LogginBean.class.getName()).log(Level.SEVERE, null, ex);
           alert(ex.getMessage(),FacesMessage.SEVERITY_INFO );
           return null;
              
       }
    }
      public void alert(String mensaje, Severity faces) {
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje);
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dialog getDialogo() {
        return dialogo;
    }

    public void setDialogo(Dialog dialogo) {
        this.dialogo = dialogo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}

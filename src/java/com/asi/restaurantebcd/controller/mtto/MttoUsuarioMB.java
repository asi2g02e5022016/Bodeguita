/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Empleado;
import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantbcd.modelo.Usuario;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Joaquin Sánchez
 */
@Named(value = "mttoUsuario")
@ViewScoped
public class MttoUsuarioMB implements Serializable {

//<editor-fold  defaultstate="collapsed" desc="Inicializar" >
    /**
     * Creates a new instance of MttoUsuarioManagedBean
     */
    public MttoUsuarioMB() {
    }

    @PostConstruct
    public void postConstruction() {
        try {
            sesion = Utilidades.findBean("sessionUsr");
            if (sesion == null) {
                alert("Debe Iniciar Sesion", FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().getViewRoot().
                        getViewMap().clear();
                String url = "http://localhost:8080/RestaurantBDC";
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(url);
            }
            //buscarEstados();
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    //</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Variables" >
    private Usuario usuarioConst;
    private SessionUsr sesion; //Busca beans session activa.
    private String codigoUsr;
    private String claveUsr;
    private String confclaveUsr;
    private String nombreEmpl;
    private String nombrePerfil;
    private Perfil idPerfil;
    private Empleado idEmpleado;
    private boolean activo;
    private List lstPerfile = new ArrayList();

    @EJB
    CrudBDCLocal ejbCrud;
//</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Metodos" >
    /**
     * Metodo para limpiar informacion de pantalla.
     */
    public void limpiarPantalla() {
        usuarioConst = null;
        codigoUsr = null;
        claveUsr = null;
        confclaveUsr = null;
        nombreEmpl = null;
        nombrePerfil = null;
        idEmpleado = null;
        idPerfil = null;
        activo = false;
        lstPerfile = null;
    }

    /**
     * Metodo para buscar los usuarios ingresados.
     */
    public void buscarUsuario() {
        try {
            //Falta EJB
            if (lstPerfile == null || lstPerfile.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoEstado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metodo que guarda un usuaio.
     */
    public void guardarUsuario() {
        try {
            if (codigoUsr == null || codigoUsr.equals("")) {
                alert("Código de usuario es obligatorio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (claveUsr == null) {
                alert("Clave es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (confclaveUsr == null) {
                alert("Confirmación de clave es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (idEmpleado == null) {
                alert("Empleado es obligatorio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (idPerfil == null) {
                alert("Perfil es obligatorio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            usuarioConst = new Usuario();
            usuarioConst.setIdusuario(codigoUsr);
            usuarioConst.setIdperfil(idPerfil);
            usuarioConst.setIdEmpleado(idEmpleado);                    
            usuarioConst.setClave(claveUsr);
            usuarioConst.setActivo(activo);
            ejbCrud.guardarEntidad(usuarioConst);
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metódo para actualizar la informacion del usuario
     */
    public void actualizarUsuario() {
    }

    /**
     * Metodo que se ejecuta cuando se esta editando la grilla del formulario
     *
     * @param event
     *
     */
    public void eventoEdit(RowEditEvent event) {
        //this.guardarEstado();
        alert("Registro modificado exitosamente.",
                FacesMessage.SEVERITY_INFO);
    }

    /**
     * Metodo que se ejecuta cuando se cancela la acción de edición en la grilla
     * del formulario
     *
     * @param event
     */
    public void eventoCancel(RowEditEvent event) {
        alert("Se ha cancelado la acción.",
                FacesMessage.SEVERITY_INFO);
//        System.out.println("event.getObject().." +event.getObject());
    }

    /**
     * Mensaje de alerta que se muestra en pantalla.
     *
     * @param mensaje Mensaje que quiere mostrar.
     * @param faces constantes definidas en FacesMessage tipos:
     * FacesMessage.SEVERITY_INFO informativo. FacesMessage.SEVERITY_ERROR
     * error. FacesMessage.SEVERITY_WARN adventencia.
     */
    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
//</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
    public Usuario getUsuarioConst() {
        return usuarioConst;
    }

    public void setUsuarioConst(Usuario usuarioConst) {
        this.usuarioConst = usuarioConst;
    }

    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public String getCodigoUsr() {
        return codigoUsr;
    }

    public void setCodigoUsr(String codigoUsr) {
        this.codigoUsr = codigoUsr;
    }

    public String getClaveUsr() {
        return claveUsr;
    }

    public void setClaveUsr(String claveUsr) {
        this.claveUsr = claveUsr;
    }

    public String getConfclaveUsr() {
        return confclaveUsr;
    }

    public void setConfclaveUsr(String confclaveUsr) {
        this.confclaveUsr = confclaveUsr;
    }

    public String getNombreEmpl() {
        return nombreEmpl;
    }

    public void setNombreEmpl(String nombreEmpl) {
        this.nombreEmpl = nombreEmpl;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List getLstPerfile() {
        return lstPerfile;
    }

    public void setLstPerfile(List lstPerfile) {
        this.lstPerfile = lstPerfile;
    }

    public CrudBDCLocal getEjbCrud() {
        return ejbCrud;
    }

    public void setEjbCrud(CrudBDCLocal ejbCrud) {
        this.ejbCrud = ejbCrud;
    }
//</editor-fold >      
}

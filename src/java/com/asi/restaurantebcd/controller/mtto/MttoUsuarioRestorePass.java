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
import com.asi.restaurantebcd.negocio.base.BusquedasUsuariosLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.context.RequestContext;

/**
 *
 * @author "Joaquin Sanchez SA101110"
 */
@ManagedBean(name = "mttoUsuarioRestorePass")
@ViewScoped
public class MttoUsuarioRestorePass implements Serializable {

//<editor-fold  defaultstate="collapsed" desc="Variables" >
    private Usuario usuarioConst;
    private Empleado empleadoConst;
    private Perfil perfilConst;
    private Perfil idPerfil;
    private Empleado idEmpleado;
    private AccordionPanel formPanel = new AccordionPanel();
    private SessionUsr sesion; //Busca beans session activa.
    private String codigoUsr;
    private String claveUsr;
    private String nombreEmpl;
    private String nombrePerfil;
    private Date fechaIngreso;
   
    private int codperfil;
    @EJB
    private CrudBDCLocal crud;
    @EJB
    private BusquedasUsuariosLocal ejbBusqUsrLcal;
    
//</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Inicializar" >
    public MttoUsuarioRestorePass(){
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
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
            alert("error de post", FacesMessage.SEVERITY_FATAL);
        }
    }
//</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Metodos" >
    /**
     * Metodo para limpiar informacion de pantalla.
     */
    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
     /**
     * Metodo para buscar los usuarios ingresados.
     */
    public void buscarUsuario() {
        try {
            Map filtro = new HashMap();
            if (codigoUsr != null) {
                filtro.put("codusr", codigoUsr);
            }

            Usuario usr = crud.buscarEntidad(Usuario.class, codigoUsr);
            
            if (usr == null) {
                alert("Usuario no encontrado", FacesMessage.SEVERITY_INFO);
                return;
            }
            
            Empleado emp = crud.buscarEntidad(Empleado.class, usr.getIdempleado().getIdempleado());
            Perfil per = crud.buscarEntidad(Perfil.class, usr.getIdperfil().getIdPerfil());

            codigoUsr = usr.getIdusuario();
            nombreEmpl = emp.getNombre();
            nombrePerfil = per.getNombre();
            claveUsr = usr.getClave();

        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
//</editor-fold >  

//<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
    public Usuario getUsuarioConst() {
        return usuarioConst;
    }

    public void setUsuarioConst(Usuario usuarioConst) {
        this.usuarioConst = usuarioConst;
    }

    public Empleado getEmpleadoConst() {
        return empleadoConst;
    }

    public void setEmpleadoConst(Empleado empleadoConst) {
        this.empleadoConst = empleadoConst;
    }

    public Perfil getPerfilConst() {
        return perfilConst;
    }

    public void setPerfilConst(Perfil perfilConst) {
        this.perfilConst = perfilConst;
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

    public AccordionPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(AccordionPanel formPanel) {
        this.formPanel = formPanel;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getCodperfil() {
        return codperfil;
    }

    public void setCodperfil(int codperfil) {
        this.codperfil = codperfil;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedasUsuariosLocal getEjbBusqUsrLcal() {
        return ejbBusqUsrLcal;
    }

    public void setEjbBusqUsrLcal(BusquedasUsuariosLocal ejbBusqUsrLcal) {
        this.ejbBusqUsrLcal = ejbBusqUsrLcal;
    }
//</editor-fold >  

    
}

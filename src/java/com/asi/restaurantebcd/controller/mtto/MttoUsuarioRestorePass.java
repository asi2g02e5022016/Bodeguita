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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import org.primefaces.event.SelectEvent;

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
    private String email;
    private String fechaIngreso;
    private List<Usuario> lstUsuario;

    private int codperfil;
    @EJB
    private CrudBDCLocal crud;
    @EJB
    private BusquedasUsuariosLocal ejbBusqUsrLcal;

//</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Inicializar" >
    public MttoUsuarioRestorePass() {
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
            limpiarPantalla();
            buscarUsuario();
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
            alert("error de post", FacesMessage.SEVERITY_FATAL);
        }
    }
//</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Metodos" >
    /**
     * Metodo para limpiar informacion de pantalla.
     *
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
     * Metodo para limpiar informacion de pantalla.
     */
    private void limpiarPantalla() {
        usuarioConst = null;
        empleadoConst = null;
        perfilConst = null;
        this.formPanel.setActiveIndex("0");
        codigoUsr = null;
        claveUsr = null;
        nombreEmpl = null;
        nombrePerfil = null;
        fechaIngreso = null;
        idEmpleado = null;
        idPerfil = null;
        codperfil = 0;
        lstUsuario = null;
    }

    /**
     * Metodo para buscar los usuarios ingresados.
     */
    public void buscarUsuario() {
        try {
            limpiarPantalla();
            lstUsuario = ejbBusqUsrLcal.buscarUsuario(null);
            if (lstUsuario == null || lstUsuario.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioRestorePass.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metódo para mostrar la pantalla de los usuarios registrados
     */
    public void mostrarDialogUsuario() {
        System.out.println("entro...");
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoUsuario').show();");
    }

    /**
     * Metódo para seleccionar un empleado registrado
     *
     * @param event
     */
    public void selUsuario(SelectEvent event) {
        try {
            usuarioConst = (Usuario) event.getObject();
            if (usuarioConst == null) {
                alert("Usuario no encontrado", FacesMessage.SEVERITY_INFO);
                return;
            }
            Empleado emp = crud.buscarEntidad(Empleado.class, usuarioConst.getIdempleado().getIdempleado());
            Perfil per = crud.buscarEntidad(Perfil.class, usuarioConst.getIdperfil().getIdPerfil());

            codigoUsr = usuarioConst.getIdusuario();
            nombreEmpl = emp.getNombre();
            nombrePerfil = per.getNombre();
            claveUsr = usuarioConst.getClave();
            //fechaIngreso = usuarioConst.getFechaingreso();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
            fechaIngreso = sdf.format(usuarioConst.getFechaingreso()); 
            
            email = emp.getEmail();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogoUsuario').hide();");
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioRestorePass.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metódo para actualizar la informacion del usuario
     *
     */
    public void restaurarPass() {
        try {                        
            if (usuarioConst == null) {
                alert("Seleccione un usuario..",FacesMessage.SEVERITY_INFO);                  
                return;
            }          
            if (Boolean.FALSE.equals(usuarioConst.isActivo())) {
                alert("Usuario seleccionado está desactivado..",FacesMessage.SEVERITY_INFO);
                return;
            }
            
            if (sesion.getUsuario().getIdusuario().equals(codigoUsr)){
                alert("Seleccione un usuario distinto al login",FacesMessage.SEVERITY_INFO);                  
                return;
            }
            
            if (email == null || email.isEmpty()){
                alert("Usuario no posee correo electronico",FacesMessage.SEVERITY_INFO);                  
                return;
            }
            
            //******************************************
            //Generando contraseña aleatoria
            //******************************************
            String newPass = "";
            Random rnd = new Random();
            String abecedario = "ABCDEFGHIJKLMOPQRSTUVWXYZ";
            String cadena = "";
            int m = 0, pos = 0, num;
            while (m < 1) {
                pos = (int) (rnd.nextDouble() * abecedario.length() - 1 + 0);
                num = (int) (rnd.nextDouble() * 9999 + 1000);
                cadena = cadena + abecedario.charAt(pos) + num;
                pos = (int) (rnd.nextDouble() * abecedario.length() - 1 + 0);
                cadena = cadena + abecedario.charAt(pos);
                newPass = cadena;
                cadena = "";
                m++;
            }
            //******************************************
            
            String passSql = String.valueOf(newPass.hashCode());
            usuarioConst.setClave(passSql);
            crud.guardarEntidad(usuarioConst);
            alert("Contraseña restaurada exitosamente..." + newPass,FacesMessage.SEVERITY_INFO);                                
            buscarUsuario();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }   

    /*public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }*/

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }   

    public int getCodperfil() {
        return codperfil;
    }

    public void setCodperfil(int codperfil) {
        this.codperfil = codperfil;
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
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

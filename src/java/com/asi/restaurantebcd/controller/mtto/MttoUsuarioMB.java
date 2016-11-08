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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Joaquin Sánchez
 */
@ManagedBean(name = "mttoUsuario")
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
            limpiarPantalla();
            buscarUsuario();
            buscarPerfil();
            buscarEmpleado();
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
            alert("error de post", FacesMessage.SEVERITY_FATAL);
        }
    }
    //</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Variables" >
    private Usuario usuarioConst;
    private Empleado empleadoConst;
    private Perfil perfilConst;
    private AccordionPanel formPanel = new AccordionPanel();
    private SessionUsr sesion; //Busca beans session activa.
    private String codigoUsr;
    private String claveUsr;
    private String confclaveUsr;
    private String nombreEmpl;
    private String nombrePerfil;
    private Date fechaIngreso;
    private Date fechaBaja;
    private Perfil idPerfil;
    private Empleado idEmpleado;
    private boolean activo;
    //private int activo;
    private int codperfil;
    private DataTable dtUsuario = new DataTable();
    private List<Usuario> lstUsuario;
    private List<Perfil> lstPerfil;
    private List<Empleado> lstEmpleado;
    private List LstEstados = new ArrayList();

    @EJB
    private CrudBDCLocal crud;
    @EJB
    private BusquedasUsuariosLocal ejbBusqUsrLcal;

//</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Metodos" >
    /**
     * Metodo para limpiar informacion de pantalla.
     */
    public void limpiarPantalla() {
        usuarioConst = null;
        empleadoConst = null;
        perfilConst = null;
        this.formPanel.setActiveIndex("0");
        codigoUsr = null;
        claveUsr = null;
        confclaveUsr = null;
        nombreEmpl = null;
        nombrePerfil = null;
        fechaIngreso = null;
        fechaBaja = null;
        idEmpleado = null;
        idPerfil = null; 
        codperfil = 0;
        activo = false;
        //activo = 0;
        dtUsuario = new DataTable();
        lstUsuario = null;
        lstPerfil = null;
        lstEmpleado = null;
    }

    /**
     * Metodo para buscar los usuarios ingresados.
     */
    public void buscarUsuario() {
        try {
            lstUsuario = ejbBusqUsrLcal.buscarUsuario();
            if (lstUsuario == null || lstUsuario.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metodo para buscar los usuarios ingresados.
     */
    public void buscarPerfil() {
        try {
            lstPerfil = ejbBusqUsrLcal.buscarPerfil();
            if (lstPerfil == null || lstPerfil.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metodo para buscar los usuarios ingresados.
     */
    public void buscarEmpleado() {
        try {
            lstEmpleado = ejbBusqUsrLcal.buscarEmpleado();
            if (lstEmpleado == null || lstEmpleado.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metodo que se ejecuta cuando se esta editando la grilla del formulario
     *
     * @param event
     *
     */
    public void eventoEdit(RowEditEvent event) {
        this.guardarUsuario();
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
    }

    /**
     * Metódo para mostrar la pantalla de los empleados registrados
     */
    public void mostrarDialogEmpleado() {
        System.out.println("entro...");
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoEmpleado').show();");
    }
       
    /**
     * Metódo para seleccionar un empleado registrado
     * @param event
     */
    public void selEmpleado(SelectEvent event) {
        try {
            empleadoConst = (Empleado) event.getObject();
            if (empleadoConst != null) {              
                nombreEmpl = empleadoConst.getNombre();
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogoEmpleado').hide();");
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    /**
     * Metódo para seleccionar una fecha 
     * @param event
     */
    public void SeleccionarFecha(CellEditEvent event) {
        try {
            if (this.dtUsuario.getRowData() != null) {                             
                usuarioConst = this.lstUsuario.get(this.dtUsuario.getRowIndex());
                
                Date fecIng = usuarioConst.getFechaingreso();
                Date fecBaj = usuarioConst.getFechabaja();
                                               
                System.out.println("fecha ingreso.." + fecIng);
                System.out.println("fecha baja.." + fecBaj);                
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }    
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
             if (!claveUsr.equals(confclaveUsr)) {
                alert("Claves no coinciden",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (codperfil==0) {
                 alert("Seleccione un perfil",
                        FacesMessage.SEVERITY_INFO);
                return;
            }             
            if (empleadoConst == null) {
                alert("Empleado es obligatorio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            
            if (fechaIngreso ==null){
            alert("Fecha ingreso es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
           
            activo = true;
            
            Empleado em = crud.buscarEntidad(Empleado.class, empleadoConst.getIdempleado());
            Perfil perf = crud.buscarEntidad(Perfil.class, codperfil);
            String password = String.valueOf(confclaveUsr.hashCode());           

            usuarioConst = new Usuario();
            usuarioConst.setIdusuario(codigoUsr);
            usuarioConst.setIdperfil(perf);
            usuarioConst.setIdempleado(em);
            usuarioConst.setClave(password);
            usuarioConst.setFechaingreso(fechaIngreso);
            usuarioConst.setFechabaja(fechaBaja);
            usuarioConst.setActivo(activo);
            
            
            crud.guardarEntidad(usuarioConst);
             alert("Usuario guardado exitosamente",FacesMessage.SEVERITY_INFO);
            limpiarPantalla();
            buscarUsuario();
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metódo para actualizar la informacion del usuario
     */
    public void actualizarUsuario() {
        try {
            if (this.dtUsuario.getRowData() != null) {                             
                usuarioConst = this.lstUsuario.get(this.dtUsuario.getRowIndex());
                
                /*if (Boolean.FALSE.equals(usuarioConst.isActivo())) {
                    System.out.println("Usuario desactivado.." + usuarioConst.isActivo());
                }*/
                Date fecIng = usuarioConst.getFechaingreso();
                Date fecBaj = usuarioConst.getFechabaja();
                                                
                
                System.out.println("fecha ingreso" + fecIng);
                System.out.println("fecha baja" + fecBaj);
                
                if (fecBaj == null){
                    alert("Fecha de baja no puede ser vacia. Verifique..",
                        FacesMessage.SEVERITY_INFO);
                return;
                }      
                if (fecBaj.before(fecIng)){
                    alert("Fecha de baja no debe ser menor a la de ingreso. Verifique..",
                        FacesMessage.SEVERITY_INFO);
                return;
                }                
                crud.guardarEntidad(usuarioConst);
                alert("Usuario actualizado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
                this.usuarioConst = null;
                this.lstUsuario = this.ejbBusqUsrLcal.buscarUsuario();
            }
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
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

    /*public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }*/
    
    public int getCodperfil() {
        return codperfil;
    }

    public void setCodperfil(int codperfil) {
        this.codperfil = codperfil;
    }    

    public DataTable getDtUsuario() {
        return dtUsuario;
    }

    public void setDtUsuario(DataTable dtUsuario) {
        this.dtUsuario = dtUsuario;
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public List<Perfil> getLstPerfil() {
        return lstPerfil;
    }

    public void setLstPerfil(List<Perfil> lstPerfil) {
        this.lstPerfil = lstPerfil;
    }

    public List<Empleado> getLstEmpleado() {
        return lstEmpleado;
    }

    public void setLstEmpleado(List<Empleado> lstEmpleado) {
        this.lstEmpleado = lstEmpleado;
    }

    public List getLstEstados() {
        return LstEstados;
    }

    public void setLstEstados(List LstEstados) {
        this.LstEstados = LstEstados;
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

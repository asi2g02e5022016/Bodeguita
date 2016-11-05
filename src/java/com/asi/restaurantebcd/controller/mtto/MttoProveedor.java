/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Proveedor;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasMttoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Phillips
 */
@ManagedBean(name = "mttoProveedor")
@ViewScoped
public class MttoProveedor implements Serializable {

    /**
     * Creates a new instance of MttoProveedor
     */
    public MttoProveedor() {
    }
        //    Metodo que se ejecuta al momento de 
        //    cargar la pagina
    @PostConstruct
    public void postContruction() {
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
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }

//<editor-fold  defaultstate="collapsed" desc="Variables" >
    /**
     * Busca beans session activa.
     */
    private Boolean actualizar;

    private AccordionPanel formPanel = new AccordionPanel();
    
    private SessionUsr sesion;
    // Atributo que muestra en pantalla el codigo del Proveedor
    private Integer idProveedor;
    // nombre de Proveedor
    private String nomProveedor;
    // N° de Registro Proveedor
    private String noRegistro;
    // NIT Proveedor
    private String nitProveedor;
    // direccion del Proveedor
    private String direccion;
    // telefono Proveedor
    private String telefono;
    // email Proveedor
    private String email;
    // listado de proveedores
    private List<Proveedor> lstProveedor;
    // constructor de Proveedor
    private Proveedor constProveedor;
    //Binding de dataTable que muestra Proveedores
    private DataTable tablaProveedor = new DataTable();
    //Bindin de dataTable que muestra Sucursales
    /**
     * EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
     */
    @EJB
    private CrudBDCLocal crud;

    /**
     * EJB de busquedas de mantenimiento.
     */
    @EJB
    private BusquedasMttoLocal ejbBusqMtto;
//</editor-fold > 

//<editor-fold  defaultstate="collapsed" desc="Setter & Getter" >    
    public Boolean getActualizar() {
        return actualizar;
    }

    public void setActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
    }

    public AccordionPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(AccordionPanel formPanel) {
        this.formPanel = formPanel;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNomProveedor() {
        return nomProveedor;
    }

    public void setNomProveedor(String nomProveedor) {
        this.nomProveedor = nomProveedor;
    }

    public String getNoRegistro() {
        return noRegistro;
    }

    public void setNoRegistro(String noRegistro) {
        this.noRegistro = noRegistro;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Proveedor> getLstProveedor() {
        return lstProveedor;
    }

    public void setLstProveedor(List<Proveedor> lstProveedor) {
        this.lstProveedor = lstProveedor;
    }

    public Proveedor getConstProveedor() {
        return constProveedor;
    }

    public void setConstProveedor(Proveedor constProveedor) {
        this.constProveedor = constProveedor;
    }

    public DataTable getTablaProveedor() {
        return tablaProveedor;
    }

    public void setTablaProveedor(DataTable tablaProveedor) {
        this.tablaProveedor = tablaProveedor;
    }
//</editor-fold > 
    
    
//<editor-fold  defaultstate="collapsed" desc="Metodos"  >
    public void limpiarPantalla() {
        idProveedor = null;
        nomProveedor = null;
        noRegistro = null;
        nitProveedor = null;
        direccion = null;
        telefono = null;
        email = null;
        this.actualizar = false;
        this.formPanel.setActiveIndex("0");
        this.tablaProveedor = null;
        
    }

    public void insertarProveedor() {
        try {
            System.out.println("entro");
//            if (this.tablaEmpleado.getRowData() != null) {
            if (idProveedor != null) {
                System.out.println("Actualizar");
                this.actualizarProveedor();
            } else {
                System.out.println("nuevoRegistro");
                this.guardarProveedor();
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoEmpleado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void guardarProveedor() {
        try {
            if (nomProveedor == null || nomProveedor.equals("")) {
                alert("El Nombre del Proveedor no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (noRegistro == null || noRegistro.equals("")) {
                alert("El Numero de Registro no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (nitProveedor == null || nitProveedor.equals("")) {
                alert("El NIT del Proveedor no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (direccion == null || direccion.equals("")) {
                alert("El campo dirección no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (telefono == null || telefono.equals("")) {
                alert("El telefono del Proveedor no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (email == null || email.equals("")) {
                alert("El email del Proveedor no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            
            constProveedor = new Proveedor();
                   
            constProveedor.setProveedor(nomProveedor.trim().toUpperCase());
            constProveedor.setNoregistro(noRegistro);
            constProveedor.setNit(nitProveedor);      
            constProveedor.setDireccion(direccion.trim().toUpperCase());
            constProveedor.setTelefono(telefono);
            constProveedor.setEmail(email.trim().toUpperCase());


            crud.guardarEntidad(this.constProveedor);
            alert("Proveedor ingresao exitosamente.",
                    FacesMessage.SEVERITY_INFO);

        } catch (Exception ex) {

            Logger.getLogger(MttoProveedor.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void actualizarProveedor() {
        try {
            System.out.print(idProveedor);
            // Empleado emp;
            constProveedor = new Proveedor();
            // System.out.println(this.lstEmpleado.get(this.tablaEmpleado.getRowIndex()).toString());    
            constProveedor.setIdproveedor(idProveedor);
            System.out.print(idProveedor);
            constProveedor.setProveedor(nomProveedor.trim().toUpperCase());
            System.out.print(nomProveedor);
            constProveedor.setNoregistro(noRegistro);
            System.out.print(noRegistro);
            constProveedor.setNit(nitProveedor);
            System.out.print(nitProveedor);
            constProveedor.setDireccion(direccion.trim().toUpperCase());
            System.out.print(direccion);
            constProveedor.setTelefono(telefono);
            System.out.print(telefono);
            constProveedor.setEmail(email.trim().toUpperCase());
            System.out.print(email);
            System.out.print(constProveedor);
            crud.guardarEntidad(constProveedor);
            constProveedor = null;
            this.limpiarPantalla();
            this.formPanel.setActiveIndex("");
            this.lstProveedor = null;
            this.tablaProveedor = null;
            this.lstProveedor = this.ejbBusqMtto.buscarProveedor();
            alert("Proveedor actualizado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoProveedor.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
     public void eliminarProveedor() {
        try {
            if (this.tablaProveedor.getRowData() != null) {
                Proveedor emp = this.lstProveedor.get(this.tablaProveedor.getRowIndex());
                System.out.print(emp);
                if (crud.eliminarEntidad(emp) == true) {
                    System.out.print("si lo elimino");
                    this.lstProveedor.remove(this.tablaProveedor.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    limpiarPantalla();
                    this.lstProveedor = this.ejbBusqMtto.buscarProveedor();

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MttoProveedor.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     
        public void buscarProveedor() {
        try {
            this.lstProveedor = this.ejbBusqMtto.buscarProveedor();
            if (this.lstProveedor == null || this.lstProveedor.isEmpty()) {
                alert("No se encontraron Proveedores.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoProveedor.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        }
        
        public void onEdit(RowEditEvent event) {
        this.formPanel.setActiveIndex("0");
        alert("si entro", FacesMessage.SEVERITY_INFO);
        }

        public void onSelect() {
        try {
            this.formPanel.setActiveIndex("0");
            if (this.tablaProveedor.getRowData() != null) {
                Proveedor emp = this.lstProveedor.get(this.tablaProveedor.getRowIndex());
                this.idProveedor = emp.getIdproveedor();
                this.nomProveedor = emp.getProveedor();
                this.nitProveedor = emp.getNit();
                this.direccion = emp.getDireccion();
                this.telefono = emp.getTelefono();
                this.email = emp.getEmail();
                this.actualizar = true;

            }

        } catch (Exception ex) {
            Logger.getLogger(MttoProveedor.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
//</editor-fold > 

    }
 
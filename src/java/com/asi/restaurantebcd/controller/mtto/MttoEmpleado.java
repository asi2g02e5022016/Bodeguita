/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Empleado;
import com.asi.restaurantbcd.modelo.Puesto;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasMttoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.awt.MenuBar;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.menu.MenuItem;

/**
 *
 * @author JAEM
 */
@ManagedBean(name = "mttoEmpleado")
@ViewScoped
public class MttoEmpleado implements Serializable {

    /**
     * Creates a new instance of MttoEmpleado
     */
    public MttoEmpleado() {
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
            this.lstPuesto = this.ejbBusqMtto.buscarPuesto();
            this.lstSucursal = this.ejbBusqMtto.buscarSucursales();
            this.lstEmpleado = this.ejbBusqMtto.buscarEmpleado();

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
    // Atributo que muestra en pantalla el codigo del empleado
    private Integer idEmpleado;
    // Fk de entidad Puesto
    private Integer idPuesto;
    // nombre de empleado
    private String nombre;
    // apellido de empleado
    private String apellido;
    // telefono de empleado
    private String telefono;
    // direccion de empleado
    private String direccion;
    // email de empleado
    private String email;
    // fk tabla sucursal 
    private Integer idSucursal;
    //constructor clase Puesto
    private Puesto puestoConstructor;
    // constructor clase Sucursal
    private Sucursal sucursalConstructor;
    // constructor empleado
    private Empleado empleadoConstructor;
    // listado de puestos
    private List<Puesto> lstPuesto;
    // listado de sucursales
    private List<Sucursal> lstSucursal;
    // listado de empleados
    private List<Empleado> lstEmpleado;
    //Binding de dataTable que muestra Puestos
    private DataTable tablaPuesto = new DataTable();
    //Bindin de dataTable que muestra Sucursales
    private DataTable tablaSucursal = new DataTable();
    //Bindin de dataTable que muestra Empleados
    private DataTable tablaEmpleado = new DataTable();
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
//<editor-fold  defaultstate="collapsed" desc="Getter y setter"  >

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

    public Empleado getEmpleadoConstructor() {
        return empleadoConstructor;
    }

    public void setEmpleadoConstructor(Empleado empleadoConstructor) {
        this.empleadoConstructor = empleadoConstructor;
    }

    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public List<Empleado> getLstEmpleado() {
        return lstEmpleado;
    }

    public void setLstEmpleado(List<Empleado> lstEmpleado) {
        this.lstEmpleado = lstEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Puesto getPuestoConstructor() {
        return puestoConstructor;
    }

    public void setPuestoConstructor(Puesto puestoConstructor) {
        this.puestoConstructor = puestoConstructor;
    }

    public Sucursal getSucursalConstructor() {
        return sucursalConstructor;
    }

    public void setSucursalConstructor(Sucursal sucursalConstructor) {
        this.sucursalConstructor = sucursalConstructor;
    }

    public List<Puesto> getLstPuesto() {
        return lstPuesto;
    }

    public void setLstPuesto(List<Puesto> lstPuesto) {
        this.lstPuesto = lstPuesto;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public DataTable getTablaPuesto() {
        return tablaPuesto;
    }

    public void setTablaPuesto(DataTable tablaPuesto) {
        this.tablaPuesto = tablaPuesto;
    }

    public DataTable getTablaSucursal() {
        return tablaSucursal;
    }

    public DataTable getTablaEmpleado() {
        return tablaEmpleado;
    }

    public void setTablaEmpleado(DataTable tablaEmpleado) {
        this.tablaEmpleado = tablaEmpleado;
    }

    public void setTablaSucursal(DataTable tablaSucursal) {
        this.tablaSucursal = tablaSucursal;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedasMttoLocal getEjbBusqMtto() {
        return ejbBusqMtto;
    }

    public void setEjbBusqMtto(BusquedasMttoLocal ejbBusqMtto) {
        this.ejbBusqMtto = ejbBusqMtto;
    }

    //</editor-fold > 
//<editor-fold  defaultstate="collapsed" desc="Metodos"  >
    public void limpiarPantalla() {
        idEmpleado = null;
        idPuesto = null;
        nombre = null;
        apellido = null;
        telefono = null;
        direccion = null;
        email = null;
        idSucursal = null;
        this.actualizar = false;
        this.formPanel.setActiveIndex("0");
        this.tablaEmpleado = null;
        this.lstEmpleado = null;

    }

    public void insertarEmpleado() {
        try {
            System.out.println("entro");
//            if (this.tablaEmpleado.getRowData() != null) {
            if (idEmpleado != null) {
                System.out.println("actualizar");
                this.actualizarEmpleado();
            } else {
                System.out.println("nuevoRegistro");
                this.guardarEmpleado();
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoEmpleado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void guardarEmpleado() {
        try {
            if (idPuesto == null || idPuesto.equals("")) {
                alert("Seleccione el puesto del empleado.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (nombre == null || nombre.equals("")) {
                alert("El campo nombre no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (apellido == null || apellido.equals("")) {
                alert("El campo apellido no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (telefono == null || telefono.equals("")) {
                alert("El campo telefono no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (direccion == null || direccion.equals("")) {
                alert("El campo dirección no puede quedar vacio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (idSucursal == null || idSucursal == 0) {
                alert("Seleccione la sucursal a la que pertenece el empleado.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            alert(idPuesto.toString(),
                    FacesMessage.SEVERITY_INFO);

            empleadoConstructor = new Empleado();
            this.puestoConstructor = new Puesto();
            this.sucursalConstructor = new Sucursal();

            puestoConstructor.setIdpuesto(idPuesto);
            sucursalConstructor.setIdsucursal(idSucursal);

            empleadoConstructor.setNombre(nombre.trim().toUpperCase());
            empleadoConstructor.setApellido(apellido.trim().toUpperCase());
            empleadoConstructor.setTelefono(telefono);
            empleadoConstructor.setDireccion(direccion.trim().toUpperCase());
            empleadoConstructor.setEmail(email.trim().toUpperCase());
            empleadoConstructor.setIdpuesto(puestoConstructor);
            empleadoConstructor.setIdsucursal(sucursalConstructor);

            crud.guardarEntidad(this.empleadoConstructor);
            alert("Empleado ingresado exitosamente.",
                    FacesMessage.SEVERITY_INFO);

        } catch (Exception ex) {

            Logger.getLogger(MttoEmpleado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void actualizarEmpleado() {
        try {
            System.out.print(idEmpleado);
            System.out.print(idPuesto);
//            Empleado emp;
            empleadoConstructor = new Empleado();
            // System.out.println(this.lstEmpleado.get(this.tablaEmpleado.getRowIndex()).toString());    
            puestoConstructor = new Puesto();
            sucursalConstructor = new Sucursal();
            puestoConstructor.setIdpuesto(idPuesto);
            System.out.print(puestoConstructor);
            sucursalConstructor.setIdsucursal(idSucursal);
            System.out.print(sucursalConstructor);
            empleadoConstructor.setIdempleado(idEmpleado);
            System.out.print(idEmpleado);
            empleadoConstructor.setNombre(nombre.trim().toUpperCase());
            System.out.print(nombre);
            empleadoConstructor.setApellido(apellido.trim().toUpperCase());
            System.out.print(apellido);
            empleadoConstructor.setTelefono(telefono);
            System.out.print(telefono);
            empleadoConstructor.setDireccion(direccion.trim().toUpperCase());
            System.out.print(direccion);
            empleadoConstructor.setEmail(email.trim().toUpperCase());
            System.out.print(email);
            empleadoConstructor.setIdpuesto(puestoConstructor);
            empleadoConstructor.setIdsucursal(sucursalConstructor);
            System.out.print(empleadoConstructor);
            crud.guardarEntidad(empleadoConstructor);
            this.puestoConstructor = null;
            this.sucursalConstructor = null;
            empleadoConstructor = null;
            this.limpiarPantalla();
            this.formPanel.setActiveIndex("");
            this.lstEmpleado = null;
            this.tablaEmpleado = null;
            this.lstEmpleado = this.ejbBusqMtto.buscarEmpleado();
            alert("Empleado actualizado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoEmpleado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void eliminarEmpleado() {
        try {
            if (this.tablaEmpleado.getRowData() != null) {
                Empleado emp = this.lstEmpleado.get(this.tablaEmpleado.getRowIndex());
                System.out.print(emp);
                if (crud.eliminarEntidad(emp) == true) {
                    System.out.print("si lo elimino");
                    this.lstEmpleado.remove(this.tablaEmpleado.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    limpiarPantalla();
                    this.lstEmpleado = this.ejbBusqMtto.buscarEmpleado();

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MttoEmpleado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void buscarEmpleado() {
        try {
            this.lstEmpleado = this.ejbBusqMtto.buscarEmpleado();
            if (this.lstEmpleado == null || this.lstEmpleado.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoEmpleado.class.getName())
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
            if (this.tablaEmpleado.getRowData() != null) {
                Empleado emp = this.lstEmpleado.get(this.tablaEmpleado.getRowIndex());
                this.idEmpleado = emp.getIdempleado();
                this.nombre = emp.getNombre();
                this.apellido = emp.getApellido();
                this.direccion = emp.getDireccion();
                this.telefono = emp.getTelefono();
                this.email = emp.getEmail();
                this.actualizar = true;

            }

        } catch (Exception ex) {
            Logger.getLogger(MttoEmpleado.class.getName())
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

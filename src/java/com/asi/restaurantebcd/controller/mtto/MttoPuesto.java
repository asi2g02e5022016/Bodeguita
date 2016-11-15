/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;
import com.asi.restaurantbcd.modelo.Departamento;
import com.asi.restaurantbcd.modelo.Puesto;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasMttoPuestoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Walter
 */
@ManagedBean(name = "mttoPuesto")
@ViewScoped
public class MttoPuesto implements Serializable {

    private boolean actualizar;
/**
* Creates a new instance of MttoImp
*/
public MttoPuesto(){   
}
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
            lstPuesto = this.ejbBusqMtto.buscarPuesto();
            lstDepartamento = this.ejbBusqMtto.buscarDepartamento();
           System.out.println(lstDepartamento);            
            System.out.println("load");
        } catch (Exception e) {
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }  
//<editor-fold  defaultstate="collapsed" desc="Variables" >
    /* busca beas de seciones activas */
    private SessionUsr sesion;

    /* variable de pk tabla impuesto*/
    private Integer idPuesto;

    /* variable de fk tabla impuesto*/
    private Integer idDepartamento;

    /* variable de descripcion del tipo de impuesto*/
    private String Puesto;
    
    /* constructor clase Impuesto  */
    private Puesto puestoConstructor;
    private AccordionPanel formPanel = new AccordionPanel();
    private Departamento departamentoConstructor;
    //atributo que muestra en pantalla listado de impuestos
    private List<Puesto> lstPuesto;
    private List<Departamento> lstDepartamento;
    /**
     * Bindin de DataTable que muestra companias.
     */
    private DataTable tablaPuesto = new DataTable();
    /**
     * EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
     */
    @EJB
    private CrudBDCLocal crud;

    /**
     * EJB de busquedas de mantenimiento.
     */
    @EJB
    private BusquedasMttoPuestoLocal ejbBusqMtto;
    
    
//</editor-fold>
//<editor-fold  defaultstate="collapsed" desc="Metodos" > 
    public void limpiarPantalla() {
        idPuesto = null;
        idDepartamento = null;
        Puesto = null;
        this.actualizar = false;
        lstPuesto = null;
        this.formPanel.setActiveIndex("0");
        } 
    public void insertarPuesto() {
        try {
            System.out.println("entro");
//            if (this.tablaEmpleado.getRowData() != null) {
            if (idPuesto != null) {
                System.out.println("actualizar");
                this.actualizarPuesto();
            } else {
                System.out.println("nuevoRegistro");
                this.guardarPuesto();
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoPuesto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
 
    }
    public void guardarPuesto() {
        try {
            if (Puesto == null || Puesto.equals("")) {
                alert("Descripcion del Puesto es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }

            if (idDepartamento == null) {
                alert("Selecione una Departamento.", FacesMessage.SEVERITY_WARN);
                return;
            }
            puestoConstructor = new Puesto();
            departamentoConstructor = new Departamento();
            departamentoConstructor.setIddepartamento(idDepartamento);
            puestoConstructor.setIddepartamento(departamentoConstructor);
            puestoConstructor.setPuesto(Puesto.trim().toUpperCase());
            
            crud.guardarEntidad(puestoConstructor);
            alert("Puesto ingresado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
            this.lstPuesto = this.ejbBusqMtto.buscarPuesto();
            this.puestoConstructor = null;
            this.limpiarPantalla();
        } catch (Exception ex) {

            Logger.getLogger(MttoPuesto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);

        }
    }
    public void actualizarPuesto() {
        try {
            if (this.tablaPuesto.getRowData() != null) {
                Puesto imp = this.lstPuesto.get(this.tablaPuesto.getRowIndex());
                departamentoConstructor = new Departamento();
                departamentoConstructor.setIddepartamento(idDepartamento);
                imp.setIddepartamento(departamentoConstructor);
                crud.guardarEntidad(imp);
                alert("Puesto actualizado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
                this.puestoConstructor = null;
                this.departamentoConstructor = null;
                imp = null;
                this.tablaPuesto = null;
                this.lstPuesto = this.ejbBusqMtto.buscarPuesto();

            }
        } catch (Exception ex) {
            Logger.getLogger(MttoPuesto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void eliminarPuesto() {
        try {
            if (tablaPuesto.getRowData() != null) {
                Puesto imp = this.lstPuesto.get(this.tablaPuesto.getRowIndex());
                if (crud.eliminarEntidad(imp) == true) {
                    lstPuesto.remove(this.tablaPuesto.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    this.lstPuesto = this.ejbBusqMtto.buscarPuesto();
                    this.puestoConstructor = null;

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoPuesto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }
    public void buscarPuesto() {
        try {
            this.lstPuesto = this.ejbBusqMtto.buscarPuesto();
            if (this.lstPuesto == null || this.lstPuesto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoPuesto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }
    public void onEdit(RowEditEvent event) {
        this.guardarPuesto();
        alert("Puesto modificado exitosamente.",
                FacesMessage.SEVERITY_INFO);
    }
    public void onSelect() {
        try {
            this.formPanel.setActiveIndex("0");
            if (this.tablaPuesto.getRowData() != null) {
                Puesto emp = this.lstPuesto.get(this.tablaPuesto.getRowIndex());
                this.idPuesto = emp.getIdpuesto();
                this.Puesto = emp.getPuesto();
                this.actualizar = true;

            }

        } catch (Exception ex) {
            Logger.getLogger(MttoPuesto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    private void alert(String mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
//</editor-fold>

    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String Puesto) {
        this.Puesto = Puesto;
    }

    public Puesto getPuestoConstructor() {
        return puestoConstructor;
    }

    public void setPuestoConstructor(Puesto puestoConstructor) {
        this.puestoConstructor = puestoConstructor;
    }

    public AccordionPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(AccordionPanel formPanel) {
        this.formPanel = formPanel;
    }

    public Departamento getDepartamentoConstructor() {
        return departamentoConstructor;
    }

    public void setDepartamentoConstructor(Departamento departamentoConstructor) {
        this.departamentoConstructor = departamentoConstructor;
    }

    public List<Puesto> getLstPuesto() {
        return lstPuesto;
    }

    public void setLstPuesto(List<Puesto> lstPuesto) {
        this.lstPuesto = lstPuesto;
    }

    public List<Departamento> getLstDepartamento() {
        return lstDepartamento;
    }

    public void setLstDepartamento(List<Departamento> lstDepartamento) {
        this.lstDepartamento = lstDepartamento;
    }

    public DataTable getTablaPuesto() {
        return tablaPuesto;
    }

    public void setTablaPuesto(DataTable tablaPuesto) {
        this.tablaPuesto = tablaPuesto;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedasMttoPuestoLocal getEjbBusqMtto() {
        return ejbBusqMtto;
    }

    public void setEjbBusqMtto(BusquedasMttoPuestoLocal ejbBusqMtto) {
        this.ejbBusqMtto = ejbBusqMtto;
    }

   

   
}

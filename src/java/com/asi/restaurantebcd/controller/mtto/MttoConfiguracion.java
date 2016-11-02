/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;
import com.asi.restaurantbcd.modelo.Configuracion;
import com.asi.restaurantebcd.negocio.base.BusquedaConfiguracionLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
/**
 *
 * @author User
 */
@ManagedBean(name = "mttoConfiguracion")
@ViewScoped
public class MttoConfiguracion implements Serializable {
    //<editor-fold  defaultstate="collapsed" desc="Inializar" >
    /**
     * Creates a new instance of MttoCompanias
     */
    public MttoConfiguracion() {
    }

    @PostConstruct
    public void postConstruction() {
        try {

            buscarConfiguracion();
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    //</editor-fold>
        //<editor-fold  defaultstate="collapsed" desc="Variables locales" >
    /**
     * Creates a new instance of MttoMedidas
     */
    @EJB
    private BusquedaConfiguracionLocal ejbBusquedaConfiguracionLocal;

    @EJB
    private CrudBDCLocal crudBDC;

    private int idconfiguracion;
    private String descripcion;
    private String valor;
    private Configuracion configuracion;
    private List<Configuracion> lstConfiguracion;
    private DataTable tablaConfiguracion = new DataTable();
    //private DataTable dtConfiguracion  =  new DataTable();
    //</editor-fold>
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    /**
     * Creates a new instance of MttoCompanias
     */
    
    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "No jodas";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void limpiarPantalla() {
            idconfiguracion = 0;
            descripcion = null;
            valor = null;
            lstConfiguracion = null;
    }

    public void guardarConfiguracion() {
        try {
            if (descripcion == null || descripcion.equals("")) {
                alert("La descripcion es obligatoria", FacesMessage.SEVERITY_ERROR);
                return;
            }
            if (valor == null || valor.equals("")) {
                alert("Ingrese un valor de correcto", FacesMessage.SEVERITY_ERROR);
                return;
            }

            configuracion = new Configuracion();
            configuracion.setDescripcion(descripcion);
            configuracion.setValor(valor);
            crudBDC.guardarEntidad(configuracion);
            alert("La configuracion se ha guardado con exito", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(MttoMedidasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarConfiguracion() {
        try {
            lstConfiguracion = ejbBusquedaConfiguracionLocal.buscarConfiguracion();
            if (lstConfiguracion == null || lstConfiguracion.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoConfiguracion.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }
    //</editor-fold>
    //<editor-fold  defaultstate="collapsed" desc="Getters and setters" >
    /**
     * Creates a new instance of MttoCompanias
     */

    public int getIdConfiguracion() {
        return idconfiguracion;
    }

    public void setIdConfiguracion(int idconfiguracion) {
        this.idconfiguracion = idconfiguracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Configuracion> getLstConfiguracion() {
        return lstConfiguracion;
    }
public DataTable getTablaConfiguracion() {
        return tablaConfiguracion;
    }
    public void setLstConfiguracion(List<Configuracion> lstConfiguracion) {
        this.lstConfiguracion = lstConfiguracion;
    }
    //</editor-fold>
}
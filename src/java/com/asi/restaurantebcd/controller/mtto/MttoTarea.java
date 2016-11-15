/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Tarea;
import com.asi.restaurantbcd.modelo.Tipotarea;
import com.asi.restaurantebcd.negocio.base.BusquedaProgramacionTareas;
import com.asi.restaurantebcd.negocio.base.BusquedaProgramacionTareasLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasMttoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author JAEM
 */
@ManagedBean(name = "mttoTarea")
@ViewScoped

public class MttoTarea {

    /**
     * Creates a new instance of MttoTarea
     */
    public MttoTarea() {
    }

    @PostConstruct
    public void postContruction() {
        try {
            constultarTipoTarea();
            consutarTarea();
        } catch (Exception e) {
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }
//<editor-fold  defaultstate="collapsed" desc="LocalVariables">
    private Integer idTarea;
    private String idTipoTarea;
    private String nombre;
    private String ejecutable;
    //var formulario    
    private Boolean btnActivo = true;
    private Boolean txtNombredis = true;
    private Boolean cbTipoTarDis = true;
    private Boolean txtEjecDis = true;
    private Boolean mostrarCerrar = false;

    private Boolean activo;
    private List<Tarea> lstTarea;
    private List<Tipotarea> lstTipoTarea;
    private DataTable dtTarea = new DataTable();
    private Tarea constructorTarea;
    private Tipotarea constructorTipoTarea;

    /**
     * EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
     */
    @EJB
    private CrudBDCLocal crud;
    /**
     * EJB de busquedas de mantenimiento.
     */
    @EJB
    private BusquedaProgramacionTareasLocal ejbBusqProgTar;
//</editor-fold> 
//<editor-fold  defaultstate="collapsed" desc="Metodos">

    public void eliminarTarea() {
        try {
            if (dtTarea.getRowData() != null) {
                Tarea tar = this.lstTarea.get(this.dtTarea.getRowIndex());
                if (crud.eliminarEntidad(tar) == true) {
                    lstTarea.remove(this.dtTarea.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    consutarTarea();

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoTarea.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void constultarTipoTarea() {
        try {
            lstTipoTarea = ejbBusqProgTar.buscarTipoTarea();
            if (this.lstTipoTarea == null || this.lstTipoTarea.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoTarea.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void consutarTarea() {
        try {
            lstTarea = ejbBusqProgTar.buscarTarea();
            if (this.lstTarea == null || this.lstTarea.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoTarea.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void cerrar() {
        btnActivo = false;
        btnActivo = false;
        txtNombredis = false;
        cbTipoTarDis = false;
        txtEjecDis = false;
        mostrarCerrar = true;
        limpiar();
    }

    public void nuevo() {
        limpiar();
        btnActivo = false;
        btnActivo = false;
        txtNombredis = false;
        cbTipoTarDis = false;
        txtEjecDis = false;
        mostrarCerrar = true;
    }

    public void limpiar() {
        idTarea = null;
        idTipoTarea = null;
        nombre = null;
        ejecutable = null;
        activo = null;
        lstTarea = null;
    }

    public void onEdit(RowEditEvent event) {
        this.guardarTarea();
        alert("Registro modificado exitosamente.",
                FacesMessage.SEVERITY_INFO);
    }

    public void onCancel(RowEditEvent event) {
        alert("Se ha cancelado la acción.",
                FacesMessage.SEVERITY_INFO);
    }

    public void actualizarTarea(RowEditEvent event) {
        try {

            Tarea tar = (Tarea) event.getObject();
            crud.guardarEntidad(tar);
            alert("Registro actualizado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
            tar = null;
            limpiar();
            consutarTarea();

        } catch (Exception ex) {
            Logger.getLogger(MttoTarea.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void guardarTarea() {
        try {
            if (nombre == null || nombre.isEmpty()) {
                alert("Nombre de tarea es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (ejecutable == null || ejecutable.isEmpty()) {
                alert("Campo ejecutable de tarea es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            constructorTarea = new Tarea();
            constructorTipoTarea = new Tipotarea();
            constructorTipoTarea.setIdtipotarea(idTipoTarea);
            constructorTarea.setNombre(nombre);
            constructorTarea.setEjecutable(ejecutable);
            constructorTarea.setActivo(activo);
            constructorTarea.setIdtipotarea(constructorTipoTarea);
            crud.guardarEntidad(constructorTarea);
            alert("Registro ingresado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
            limpiar();
            consutarTarea();
        } catch (Exception ex) {
            Logger.getLogger(MttoTarea.class
                    .getName())
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
//</editor-fold>
//<editor-fold  defaultstate="collapsed" desc="Get Set">

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getBtnActivo() {
        return btnActivo;
    }

    public void setBtnActivo(Boolean btnActivo) {
        this.btnActivo = btnActivo;
    }

    public String getEjecutable() {
        return ejecutable;
    }

    public void setEjecutable(String ejecutable) {
        this.ejecutable = ejecutable;
    }

    public Boolean getTxtNombredis() {
        return txtNombredis;
    }

    public void setTxtNombredis(Boolean txtNombredis) {
        this.txtNombredis = txtNombredis;
    }

    public Boolean getCbTipoTarDis() {
        return cbTipoTarDis;
    }

    public void setCbTipoTarDis(Boolean cbTipoTarDis) {
        this.cbTipoTarDis = cbTipoTarDis;
    }

    public Boolean getTxtEjecDis() {
        return txtEjecDis;
    }

    public void setTxtEjecDis(Boolean txtEjecDis) {
        this.txtEjecDis = txtEjecDis;
    }

    public Boolean getMostrarCerrar() {
        return mostrarCerrar;
    }

    public void setMostrarCerrar(Boolean mostrarCerrar) {
        this.mostrarCerrar = mostrarCerrar;
    }

//    public Boolean getActivo() {
//        return activo;
//    }
//
//    public void setActivo(Boolean activo) {
//        this.activo = activo;
//    }
    public String getIdTipoTarea() {
        return idTipoTarea;
    }

    public void setIdTipoTarea(String idTipoTarea) {
        this.idTipoTarea = idTipoTarea;
    }

    public List<Tipotarea> getLstTipoTarea() {
        return lstTipoTarea;
    }

    public void setLstTipoTarea(List<Tipotarea> lstTipoTarea) {
        this.lstTipoTarea = lstTipoTarea;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Tarea getConstructorTarea() {
        return constructorTarea;
    }

    public void setConstructorTarea(Tarea constructorTarea) {
        this.constructorTarea = constructorTarea;
    }

    public Tipotarea getConstructorTipoTarea() {
        return constructorTipoTarea;
    }

    public void setConstructorTipoTarea(Tipotarea constructorTipoTarea) {
        this.constructorTipoTarea = constructorTipoTarea;
    }

    public List<Tarea> getLstTarea() {
        return lstTarea;
    }

    public void setLstTarea(List<Tarea> lstTarea) {
        this.lstTarea = lstTarea;
    }

    public DataTable getDtTarea() {
        return dtTarea;
    }

    public void setDtTarea(DataTable dtTarea) {
        this.dtTarea = dtTarea;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedaProgramacionTareasLocal getEjbBusqProgTar() {
        return ejbBusqProgTar;
    }

    public void setEjbBusqProgTar(BusquedaProgramacionTareasLocal ejbBusqProgTar) {
        this.ejbBusqProgTar = ejbBusqProgTar;
    }
//</editor-fold>
}

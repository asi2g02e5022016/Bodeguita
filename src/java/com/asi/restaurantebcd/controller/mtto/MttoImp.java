/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Compania;
import com.asi.restaurantbcd.modelo.Impuesto;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author JAEM
 */
@ManagedBean(name = "mttoImp")
@ViewScoped
public class MttoImp implements Serializable {

    /**
     * Creates a new instance of MttoImp
     */
    public MttoImp() {
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
            lstImpuesto = this.ejbBusqMtto.buscarImpuesto();
            lstCompania = this.ejbBusqMtto.buscarCompania();
//           System.out.println(lstCompania);            
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
    private Integer idImpuesto;

    /* variable de fk tabla impuesto*/
    private Integer idCompania;

    /* variable de descripcion del tipo de impuesto*/
    private String impuesto;

    /* variable que contiene el valor del impuesto*/
    private Float porcentaje;
    /* constructor clase Impuesto  */
    private Impuesto impuestoConstructor;
    /*constructor clase compania*/
    private Compania companiaConstructor = null;
    //atributo que muestra en pantalla listado de impuestos
    private List<Impuesto> lstImpuesto;
    private List<Compania> lstCompania;

    /**
     * Bindin de DataTable que muestra companias.
     */
    private DataTable tablaImpuesto = new DataTable();
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
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    //Limpiar variables
    public void limpiarPantalla() {
        idImpuesto = null;
        idCompania = null;
        impuesto = null;
        porcentaje = null;

    }

    // metodo para registrar un nuevo impuesto
    public void guardarImpuesto() {
        try {
            if (impuesto == null || impuesto.equals("")) {
                alert("Descripcion de impuesto es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }

            if (idCompania == null) {
                alert("Selecione una compania.", FacesMessage.SEVERITY_WARN);
                return;
            }
            impuestoConstructor = new Impuesto();
            companiaConstructor = new Compania();
            companiaConstructor.setIdcompania(idCompania);
            impuestoConstructor.setIdcompania(companiaConstructor);
            impuestoConstructor.setImpuesto(impuesto.trim().toUpperCase());
            impuestoConstructor.setPorcentaje(porcentaje);
            crud.guardarEntidad(impuestoConstructor);
            alert("Impuesto ingresado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
            this.lstImpuesto = this.ejbBusqMtto.buscarImpuesto();
            this.impuestoConstructor = null;
            this.limpiarPantalla();
        } catch (Exception ex) {

            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);

        }
    }

    //metodo para actualizar un impuesto
    public void actualizarImpuesto() {
        try {
            if (this.tablaImpuesto.getRowData() != null) {
                Impuesto imp = this.lstImpuesto.get(this.tablaImpuesto.getRowIndex());
                companiaConstructor = new Compania();
                companiaConstructor.setIdcompania(idCompania);
                imp.setIdcompania(companiaConstructor);
                crud.guardarEntidad(imp);
                alert("Impuesto actualizado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
                this.impuestoConstructor = null;
                this.companiaConstructor = null;
                imp = null;
                this.tablaImpuesto = null;
                this.lstImpuesto = this.ejbBusqMtto.buscarImpuesto();

            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void eliminarImpuesto() {
        try {
            if (tablaImpuesto.getRowData() != null) {
                Impuesto imp = this.lstImpuesto.get(this.tablaImpuesto.getRowIndex());
                if (crud.eliminarEntidad(imp) == true) {
                    lstImpuesto.remove(this.tablaImpuesto.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    this.lstImpuesto = this.ejbBusqMtto.buscarImpuesto();
                    this.impuestoConstructor = null;

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void buscarImpuesto() {
        try {
            this.lstImpuesto = this.ejbBusqMtto.buscarImpuesto();
            if (this.lstImpuesto == null || this.lstImpuesto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void onEdit(RowEditEvent event) {
        this.guardarImpuesto();
        alert("Registro modificado exitosamente.",
                FacesMessage.SEVERITY_INFO);
    }

    public void onCancel(RowEditEvent event) {
        alert("Se ha cancelado la acción.",
                FacesMessage.SEVERITY_INFO);
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

    //<editor-fold  defaultstate="collapsed" desc="Getter y setter" >
    public Integer getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public DataTable getTablaImpuesto() {
        return tablaImpuesto;
    }

    public void setTablaImpuesto(DataTable tablaImpuesto) {
        this.tablaImpuesto = tablaImpuesto;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public List<Impuesto> getLstImpuesto() {
        return lstImpuesto;
    }

    public void setLstCompanias(List<Impuesto> lstImpuesto) {
        this.lstImpuesto = lstImpuesto;
    }

    public List<Compania> getLstCompania() {
        return lstCompania;
    }

    public void setLstCompania(List<Compania> lstCompania) {
        this.lstCompania = lstCompania;
    }

    public Integer getIdCompania() {
        return idCompania;
    }

    public void setIdCompania(Integer idCompania) {
        this.idCompania = idCompania;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Compania getCompaniaConstructor() {
        return companiaConstructor;
    }

    public void setCompaniaConstructor(Compania companiaConstructor) {
        this.companiaConstructor = companiaConstructor;
    }
    //</editor-fold>

}

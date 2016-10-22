/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Formapago;
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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Luis
 */
@ManagedBean(name = "mttoFormaPago")
@ViewScoped
public class MttoFormaPago implements Serializable {

    @EJB
    private CrudBDCLocal crudBDC;
    
    @EJB
    private BusquedasMttoLocal ejbBusqMtto;
    
    @PostConstruct
    public void postConstruction(){
        try{
            sesion = Utilidades.findBean("sessionUsr");
            if(sesion == null){
                alert("Debe Iniciar Sesion",FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().getViewRoot().
                        getViewMap().clear();
            String url = "http://localhost:8080/RestaurantBDC";
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect(url);
            } 
            buscarFormaPago();
        } catch (Exception e) {
                alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    //<editor-fold  defaultstate="collapsed" desc="Inializar" >

    /**
     * Creates a new instance of MttoFormaPago
     */
    public MttoFormaPago() {
    }
    
    private Integer idformapago;
    
    private String descformapago;
    
    private Formapago formapago;
    
    private SessionUsr sesion ;
    
    private List <Formapago> listFormapago;
    
    private DataTable dtFormapago = new DataTable();

    public void limpiarpantalla(){
        idformapago = null;
        descformapago = null;
        formapago = null;
        listFormapago = null;
        dtFormapago = new DataTable();
    }
    
    
    public void guardarFormaPago(){
        try {
            if(descformapago == null || descformapago.equals("")){
                alert("Descripcion de forma de pago es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            
            formapago = new Formapago();
            formapago.setFormapago(descformapago);
            crudBDC.guardarEntidad(formapago);
            alert("La descripción de forma de pago se ha guardado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoFormaPago.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void buscarFormaPago(){
        try {
            listFormapago  = ejbBusqMtto.buscarFormapago();
            if (listFormapago == null || listFormapago.isEmpty()){
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoFormaPago.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
     private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje =  "No existen formas de Pago";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public Integer getIdformapago() {
        return idformapago;
    }

    public void setIdformapago(Integer idformapago) {
        this.idformapago = idformapago;
    }

    public String getDescformapago() {
        return descformapago;
    }

    public void setDescformapago(String descformapago) {
        this.descformapago = descformapago;
    }

    public Formapago getFormapago() {
        return formapago;
    }

    public void setFormapago(Formapago formapago) {
        this.formapago = formapago;
    }

    public List<Formapago> getListFormapago() {
        return listFormapago;
    }

    public void setListFormapago(List<Formapago> listFormapago) {
        this.listFormapago = listFormapago;
    }

    public DataTable getDtFormapago() {
        return dtFormapago;
    }

    public void setDtFormapago(DataTable dtFormapago) {
        this.dtFormapago = dtFormapago;
    }
    
    
    
   
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Compania;
import com.asi.restaurantbcd.modelo.Condicionpago;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Phillips
 */
@ManagedBean(name = "mttoCondicionPago")
@ViewScoped 
public class MttoCondicionPago implements Serializable{

    /**
     * Creates a new instance of MttoCondicionPago
     */
    public MttoCondicionPago() {
    }
        /**
     * Metodo que se ejecutara inmediatamente al cargar pagina.
     */
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
            buscarCondicionPago();
        } catch (Exception e) {
                alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
     //</editor-fold >
    
    //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /**Atributo que se muestra en pantalla el codigo de Condicion de Pago.*/
    private Integer idcondicionpago;
      /**Atributo que muestrara en pantalla el nombre de la Condicion de Pago.*/
    private String condicionpago;
    /**Busca beans session activa.*/
    private SessionUsr sesion ;
    
    /**Entidad que guardara las Condiciones de Pago.*/
    private Condicionpago Condicionpago;
      /**Atributo que se muestra en pantalla la lista de Condiciones de Pago.*/
    private List <Condicionpago> lstCondicionPago;
    /**Bindin de DataTable que muestrara las Condiciones de Pago.*/
    private DataTable tablaCondicionPago  =  new DataTable();
     /**
      * EJB Quecon tiene metodos utilitarios como:
      * Guardar, Eliminar, Buscar... 
      */
     @EJB
    private CrudBDCLocal crud;
     /**
      * EJB 
      * de busquedas  de mantenimiento.
      */
     @EJB
    private BusquedasMttoLocal ejbBusqMtto;
        //</editor-fold >
        
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
     /**
      * Metodo para limpiar informacion de pantalla.
      */
     public void limpiarCondicionPago() {
         lstCondicionPago = null;
         Condicionpago = null;
         condicionpago = null;
         idcondicionpago = null;
    }
     /**
      * Metodo que se ejecuta cuando se 
      * va a guardar una compania.
      */
     public void guardarCondicionPago() {
        try {
            if (condicionpago == null || condicionpago.equals("")) {
                alert("La descripcion de la Condicion de Pago es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
           
            Integer codigo = ejbBusqMtto.obtenerCorreltivoCompanias();
            Condicionpago = new Condicionpago();
            Condicionpago.setCondicionpago(condicionpago.trim().toUpperCase());
           // compania.setIdCompania(codigo);
            
            crud.guardarEntidad(Condicionpago);
            alert("La Condicion de Pago se ha guardado exitosamente.", 
                    FacesMessage.SEVERITY_INFO);
            this.idcondicionpago = codigo;
        } catch (Exception ex) {
            Logger.getLogger(MttoCondicionPago.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     /**
      * Metodo para guardar una Condicion de Pago.
      */
     public void buscarCondicionPago() {
        try {
            lstCondicionPago =  ejbBusqMtto.buscarCondicionPago();
            if (lstCondicionPago == null || lstCondicionPago.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCondicionPago.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     
     /**
      * Metodo que se ejecuta para Actualizar una Condicion de Pago.
      */
     public void actualizarCondicionPago() {
        try {
                
            if (tablaCondicionPago.getRowData() != null) {
                Condicionpago =  (Condicionpago) tablaCondicionPago.getRowData();
                System.out.println("compania.." +Condicionpago);
                crud.eliminarEntidad(Condicionpago);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCondicionPago.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     /**
      * Metodo que se ejecuta cuando se 
      * va a Eliminar una compania.
      */
     public void eliminarCompanias() {
        try {
         if (tablaCondicionPago.getRowData() != null) {
                Condicionpago =  lstCondicionPago.remove(tablaCondicionPago.getRowIndex());
                crud.eliminarEntidad(Condicionpago);
                lstCondicionPago.remove(Condicionpago);
                
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCompanias.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
   public void onEdit(RowEditEvent event) {  
        System.out.println("event.getObject().." +event.getObject());    
    }  
       
    public void onCancel(RowEditEvent event) {  
        System.out.println("event.getObject().." +event.getObject());
    } 
     /**
      * Mensaje de alerta que se muestra en pantalla.
      * @param mensaje Mensaje que quiere mostrar.
      * @param faces constantes definidas en FacesMessage tipos:
      * FacesMessage.SEVERITY_INFO  informativo.
      * FacesMessage.SEVERITY_ERROR error.
      * FacesMessage.SEVERITY_WARN adventencia.
      */
    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje =  "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
        //</editor-fold >
     
    //<editor-fold  defaultstate="collapsed" desc="Getter y setter" >
    public Integer getidcondicionpago() {
        return idcondicionpago;
    }

    public void setidcondicionpago(Integer idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }

    public DataTable getTablaCondicionPago() {
        return tablaCondicionPago;
    }

    public void setTablaCondicionPago(DataTable tablaCondicionPago) {
        this.tablaCondicionPago = tablaCondicionPago;
    }

    public String getcondicionpago() {
        return condicionpago;
    }

    public void setcondicionpago(String condicionpago) {
        this.condicionpago = condicionpago;
    }

    public List<Condicionpago> getLstCondicionPago() {
        return lstCondicionPago;
    }

    public void setLstCondicionPago(List<Condicionpago> lstCondicionPago) {
        this.lstCondicionPago = lstCondicionPago;
    } 
        //</editor-fold >
}

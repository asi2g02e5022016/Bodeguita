/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;
import com.asi.restaurantbcd.modelo.Tipodocumento;
import com.asi.restaurantebcd.negocio.base.BusquedaTipoDocumentoLocal;
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
@ManagedBean(name = "mttoTipoDocumento")
@ViewScoped
public class MttoTipoDocumento implements Serializable{

    @EJB
    private BusquedaTipoDocumentoLocal busquedasTipoDocumento;

    @EJB
    private CrudBDCLocal crudBDC;
    
    private Integer idTipoDocumento;
    private String nombreTipoDocumento;
    private Tipodocumento tipodocumento;
    private List <Tipodocumento> listaTipoDocumento;
    private DataTable tablaTipoDocumento  =  new DataTable();
    

    /**
     * Creates a new instance of MttoMedidasBeans
     */
    public MttoTipoDocumento() {
    }
    
        @PostConstruct
    public void postConstruction(){
        try{
     
            buscarTipoDocumento();
        } catch (Exception e) {
                alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
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
    
         public void limpiarPantalla() {
         idTipoDocumento = null;
         tipodocumento = null;
         listaTipoDocumento = null;
    }

    
    public void guardarTipoDocumento(){
        try {
            if (nombreTipoDocumento == null || nombreTipoDocumento.equals("")){
                alert("El nombre del tipo de documento es obligatorio", FacesMessage.SEVERITY_ERROR);
                return;
            }
            tipodocumento = new Tipodocumento();
            tipodocumento.setTipodocumento(nombreTipoDocumento);
            crudBDC.guardarEntidad(tipodocumento);
            alert("El tipo de documento se ha guardado con exito", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(MttoTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buscarTipoDocumento(){
        
         try {
            listaTipoDocumento = busquedasTipoDocumento.buscarTipodocumento();
            if (listaTipoDocumento == null || listaTipoDocumento.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoTipoDocumento.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreTipoDocumento() {
        return nombreTipoDocumento;
    }

    public void setNombreTipoDocumento(String nombreTipoDocumento) {
        this.nombreTipoDocumento = nombreTipoDocumento;
    }

    public List<Tipodocumento> getListaTipodocumentos() {
        return listaTipoDocumento;
    }
    
    public DataTable getTablaTipoDocumento() {
        return tablaTipoDocumento;
    }
    
     public void onEdit(RowEditEvent event) {  
        System.out.println("event.getObject().." +event.getObject());    
    }  
       
    public void onCancel(RowEditEvent event) {  
        System.out.println("event.getObject().." +event.getObject());
    } 
    public void setListaTipoDocumento(List<Tipodocumento> listaTipodocumentos) {
        this.listaTipoDocumento = listaTipodocumentos;
    }  
    
}
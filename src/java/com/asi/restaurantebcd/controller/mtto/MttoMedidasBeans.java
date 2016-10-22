/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;
import com.asi.restaurantbcd.modelo.Medida;
import com.asi.restaurantebcd.negocio.base.BusquedasMedidasLocal;
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
@ManagedBean(name = "mttoMedidas")
@ViewScoped
public class MttoMedidasBeans implements Serializable{

    @EJB
    private BusquedasMedidasLocal busquedasMedidas;

    @EJB
    private CrudBDCLocal crudBDC;
    
    private Integer idMedida;
    private String nombreMedida;
    private Medida medida;
    private List <Medida> listaMedidas;
    private DataTable tablaMedidas  =  new DataTable();
    

    /**
     * Creates a new instance of MttoMedidasBeans
     */
    public MttoMedidasBeans() {
    }
    
        @PostConstruct
    public void postConstruction(){
        try{
     
            buscarMedidas();
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
         idMedida = null;
         medida = null;
         listaMedidas = null;
    }

    
    public void guardarMedidas(){
        try {
            if (nombreMedida == null || nombreMedida.equals("")){
                alert("El nombre medida es obligatorio", FacesMessage.SEVERITY_ERROR);
                return;
            }
            medida = new Medida();
            medida.setMedida(nombreMedida);
            crudBDC.guardarEntidad(medida);
            alert("La medida se ha guardado con exito", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(MttoMedidasBeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buscarMedidas(){
        System.out.println("jkfhdhf");
         try {
            listaMedidas = busquedasMedidas.buscarMedida();
            if (listaMedidas == null || listaMedidas.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoMedidasBeans.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public Integer getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Integer idMedida) {
        this.idMedida = idMedida;
    }

    public String getNombreMedida() {
        return nombreMedida;
    }

    public void setNombreMedida(String nombreMedida) {
        this.nombreMedida = nombreMedida;
    }

    public List<Medida> getListaMedidas() {
        return listaMedidas;
    }
    
    public DataTable getTablaMedidas() {
        return tablaMedidas;
    }
    
     public void onEdit(RowEditEvent event) {  
        System.out.println("event.getObject().." +event.getObject());    
    }  
       
    public void onCancel(RowEditEvent event) {  
        System.out.println("event.getObject().." +event.getObject());
    } 
    public void setListaMedidas(List<Medida> listaMedidas) {
        this.listaMedidas = listaMedidas;
    }  
    
}
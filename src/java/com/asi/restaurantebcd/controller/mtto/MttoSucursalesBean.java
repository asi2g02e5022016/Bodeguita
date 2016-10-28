/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Compania;
import com.asi.restaurantbcd.modelo.Sucursal;
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
 * @author samaelopez
 */
@ManagedBean(name = "mttoSucursalesBean")
@ViewScoped
public class MttoSucursalesBean implements   Serializable {
   
    //<editor-fold  defaultstate="collapsed" desc="Inializar" >

    
    /**
     * Metodo   ue se ejecuta no mas cargar pagina.
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
            buscarSucursal();
        } catch (Exception e) {
                alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
     //</editor-fold >
    
    //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /**Atributo que se muestra en pantalla el codigo de compania.*/
    private Integer idSucursal;
      /**Atributo que se muestra en pantalla el codigo de compania.*/
    private String nombreSucursal;
    /**Atributo que se muestra en pantalla direccion de sucursal .*/
    private String direccionSucursal;
    /**Atributo que se muestra en pantalla telefono de sucursal.*/
    private String telefonoSucursal;
      /**Busca beans session activa.*/
    private SessionUsr sesion ;
      /**Entidad que guardara la Sucursal.*/
    private Sucursal sucursal;
      /**Atributo que se muestra en pantalla la lista de companias.*/
    private List <Sucursal> lstSucursal;
    /**Bindin de DataTable que muestra Sucursal.*/
    private DataTable tablaSucursal  =  new DataTable();
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
     public void limpiarPantalla() {
         lstSucursal = null;
         sucursal = null;
         nombreSucursal = null;
         idSucursal = null;
         direccionSucursal = null;
         telefonoSucursal = null;
    }
     /**
      * Metodo que se ejecuta cuando se 
      * va a guardar una compania.
      */
     public void guardarSucursal() {
        try {
            if (nombreSucursal == null || nombreSucursal.equals("")) {
                alert("Nombre de sucursal es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (direccionSucursal == null || direccionSucursal.equals("")) {
                alert("Direccion de su es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            if (telefonoSucursal == null || telefonoSucursal.equals("")) {
                alert("Telefono de sucursal es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            sucursal = new Sucursal();
            sucursal.setDireccion(direccionSucursal.trim().toUpperCase());
            sucursal.setSucursal(nombreSucursal.trim().toUpperCase());
            sucursal.setTelefono(telefonoSucursal.trim().toUpperCase());
            //sucursal.setIdcompania(sesion.getCompania());
            
            crud.guardarEntidad(sucursal);
            alert("La Sucursal se guardo exitosamente.", 
                    FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoSucursalesBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     /**
      * Metodo que se ejecuta cuando se 
      * va a guardar una compania.
      */
     public void buscarSucursal() {
        try {
            lstSucursal =  ejbBusqMtto.buscarSucursales();
            if (lstSucursal == null || lstSucursal.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
            System.out.println("{,,..." +lstSucursal);
        } catch (Exception ex) {
            Logger.getLogger(MttoCompanias.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     
     /**
      * Metodo que se ejecuta cuando se 
      * va a Actualizar una compania.
      */
     public void actualizarSucursal() {
        try {
                
            if (tablaSucursal.getRowData() != null) {
                sucursal =  (Sucursal) tablaSucursal.getRowData();
                System.out.println("sucursal.." +sucursal);
                crud.eliminarEntidad(sucursal);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoSucursalesBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     /**
      * Metodo que se ejecuta cuando se 
      * va a Eliminar una compania.
      */
     public void eliminarSucursal() {
        try {
         if (tablaSucursal.getRowData() != null) {
                sucursal =  lstSucursal.remove(tablaSucursal.getRowIndex());
                crud.eliminarEntidad(sucursal);
                lstSucursal.remove(sucursal);
                
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


    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }

    public String getTelefonoSucursal() {
        return telefonoSucursal;
    }

    public void setTelefonoSucursal(String telefonoSucursal) {
        this.telefonoSucursal = telefonoSucursal;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public DataTable getTablaSucursal() {
        return tablaSucursal;
    }

    public void setTablaSucursal(DataTable tablaSucursal) {
        this.tablaSucursal = tablaSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }


        //</editor-fold >

    
}

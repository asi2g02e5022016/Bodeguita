/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Caja;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.negocio.base.BusquedaCajaLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasMttoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
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
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author hp
 */
@ManagedBean(name = "mttoCaja")
@ViewScoped
public class MttoCaja implements Serializable {

    /**
     * Creates a new instance of MttoCaja
     */
    public MttoCaja() {
    }
   @PostConstruct
   public void postConstruct(){
       try{
           
           this.lstCaja = this.ejbBusqCaja.BuscarCaja();
           this.lstSucursal = this.ejbBusqMtto.buscarSucursales();
           this.activo = false;
           
       }catch(Exception e){
           e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
       }
       
   }
    
   //<editor-fold  defaultstate="collapsed" desc="Variables" >
   private Integer idCaja;
   private Integer idSucursal;
   private String caja;
   private Boolean activo;
   
   private Caja cajaConstructor;
   private Sucursal sucursalConstructor;
   
   private List<Caja> lstCaja;
   private List<Sucursal> lstSucursal;
   
   
   @EJB
    private CrudBDCLocal crud;
   @EJB
    private BusquedasMttoLocal ejbBusqMtto;
   @EJB
   private BusquedaCajaLocal ejbBusqCaja;
   
   //</editor-fold >
    
   //<editor-fold  defaultstate="collapsed" desc="Metodos" >
   
   public void limpiarPantalla(){
       idCaja = null;
       idSucursal = null;
       caja=null;
       activo=null;
 lstCaja = null;
 
      
       
   }
   
   
   public void guardarCaja(){
       try{
       
           if(caja == null || caja.equals("")){
               alert("Descripción de caja obligatoria.", FacesMessage.SEVERITY_WARN);
                return;
               
           }
           if(idSucursal == null){
               alert("Seleccione una Sucursal.", FacesMessage.SEVERITY_WARN);
                return;
               }
           
           cajaConstructor = new Caja();
           sucursalConstructor = new Sucursal();
           
           sucursalConstructor.setIdsucursal(idSucursal);
           cajaConstructor.setIdsucursal(sucursalConstructor);
           
           cajaConstructor.setCaja(caja);
           cajaConstructor.setActivo(activo);
         
            crud.guardarEntidad(this.cajaConstructor);
            this.limpiarPantalla();
            alert("Producto ingresado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
            
       }catch(Exception ex){
           Logger.getLogger(MttoCaja.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
       }
       
   }
   
  
   public void actualizarCaja(RowEditEvent event){
       
       try{
           if(event.getObject() != null){
               
               Caja cj = (Caja) event.getObject();
               crud.guardarEntidad(cj);
            
               this.cajaConstructor = null;
               this.sucursalConstructor = null;
              this.lstCaja = this.ejbBusqCaja.BuscarCaja();
            cj = null;
              alert("Caja actualizada exitosamente.",
                        FacesMessage.SEVERITY_INFO);
           }
                   
       }catch(Exception ex){
          Logger.getLogger(MttoCaja.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);     
           
       }
       
       
   }
   
   public void eliminarCaja(Caja c){
   try{
       if(c != null){
           Caja cj = c;
                if (crud.eliminarEntidad(cj) == true) {
                    lstCaja.remove(cj);
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
//                    this.lstProducto = this.ejbBusMtto.buscarProducto();
                    this.cajaConstructor = null;
                }  
       }
      
   }catch(Exception ex){
      Logger.getLogger(MttoCaja.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR); 
       
   }            
               
       }
   
   
   public void buscarCaja(){
   try{
       
       this.lstCaja = this.ejbBusqCaja.BuscarCaja();
       this.lstSucursal = this.ejbBusqMtto.buscarSucursales();
        
         System.out.println(lstCaja);
         
         if (this.lstCaja == null || this.lstCaja.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
       
   }catch(Exception e){
       
   }    
       
   }
   
    public void onCancel(RowEditEvent event) {
        alert("Se ha cancelado la acción.",
                FacesMessage.SEVERITY_INFO);
    }

    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "Problemas al realizar operación";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
   
   
   //</editor-fold >

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Caja getCajaConstructor() {
        return cajaConstructor;
    }

    public void setCajaConstructor(Caja cajaConstructor) {
        this.cajaConstructor = cajaConstructor;
    }

    public Sucursal getSucursalConstructor() {
        return sucursalConstructor;
    }

    public void setSucursalConstructor(Sucursal sucursalConstructor) {
        this.sucursalConstructor = sucursalConstructor;
    }

    public List<Caja> getLstCaja() {
        return lstCaja;
    }

    public void setLstCaja(List<Caja> lstCaja) {
        this.lstCaja = lstCaja;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
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

    public BusquedaCajaLocal getEjbBusqCaja() {
        return ejbBusqCaja;
    }

    public void setEjbBusqCaja(BusquedaCajaLocal ejbBusqCaja) {
        this.ejbBusqCaja = ejbBusqCaja;
    }
    
    
    
    
}




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

//import com.asi.restaurantbcd.modelo.Compania;
import com.asi.restaurantbcd.modelo.Condicionpago;
import com.asi.restaurantebcd.negocio.base.BusquedaCondicionPagoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
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

            this.lstCondicionpago = this.ejbMttCondicionPago.buscarCondicionPago();
            
        } catch (Exception e) {
               e.printStackTrace();
            alert("error en el postcons", FacesMessage.SEVERITY_FATAL);
        }
    }
     //</editor-fold >
    
    //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /**Atributo que se muestra en pantalla el codigo de Condicion de Pago.*/
   private Integer idCondicionPago;
   private String CondicionPago;
   private List <Condicionpago> lstCondicionpago;
   private Condicionpago CondicionpConstructor;
  
    @EJB
    private BusquedaCondicionPagoLocal ejbMttCondicionPago;
       @EJB
    private CrudBDCLocal crud;
    
    
        //</editor-fold >
        
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
     
   public void limpiarpantalla(){
       lstCondicionpago = null;
       CondicionPago = null;
       CondicionpConstructor = null;
       idCondicionPago = null;
       
   }
   /// metodo guardar condiciones de pago
   public void guardarCondicionpago(){
       try{
          if(CondicionPago == null || CondicionPago.equals("")){
              alert("Descripcion de Condicion de pago es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
          } 
          CondicionpConstructor = new Condicionpago();
          CondicionpConstructor.setCondicionpago(CondicionPago.trim().toUpperCase());
          crud.guardarEntidad(this.CondicionpConstructor);
         alert("Condicion de Pago ingresada exitosamente.",
                    FacesMessage.SEVERITY_INFO);
         
         this.CondicionPago = null;
         this.CondicionpConstructor= null;
         this.ejbMttCondicionPago.buscarCondicionPago();
           
       }catch(Exception ex){
           Logger.getLogger(MttoTipoProducto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
       }
       
   }
   
   public void actualizarCondicionP(RowEditEvent event){
       
       try{
           
           if(event.getObject() != null){
               Condicionpago conp = (Condicionpago) event.getObject();
               crud.guardarEntidad(conp);
               alert("Condicion de Pago actualizado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
               CondicionpConstructor = null;
               CondicionPago = null;
               conp = null;
               
               this.lstCondicionpago  = this.ejbMttCondicionPago.buscarCondicionPago();
               
           }
           
       }catch(Exception ex){
             Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert("error aqui condicion pago ", FacesMessage.SEVERITY_ERROR);
       }
     
   }
   
   public void buscarCondicionPago(){
       try{
           this.lstCondicionpago = this.ejbMttCondicionPago.buscarCondicionPago();
           if (this.lstCondicionpago == null || this.lstCondicionpago.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
           
       }catch(Exception ex){
           Logger.getLogger(MttoDepartamento.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert("error", FacesMessage.SEVERITY_ERROR);
       }
   }
   
   public void eliminarCondicionP(Condicionpago p){
       try{
           
           if(p != null){
               
               Condicionpago cp = p;
               if (crud.eliminarEntidad(cp) == true) {
                    lstCondicionpago.remove(cp);
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
//                    this.lstProducto = this.ejbBusMtto.buscarProducto();
                    this.CondicionpConstructor = null;

                }
               
               
           }
           
       }catch(Exception ex){
           Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
       }
       
       
       
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
   
   
   
   
   
    //</editor-fold >
     
    //<editor-fold  defaultstate="collapsed" desc="Getter y setter" >
   public BusquedaCondicionPagoLocal getEjbMttCondicionPago() {
        return ejbMttCondicionPago;
    }

    public void setEjbMttCondicionPago(BusquedaCondicionPagoLocal ejbMttCondicionPago) {
        this.ejbMttCondicionPago = ejbMttCondicionPago;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public Integer getIdCondicionPago() {
        return idCondicionPago;
    }

    public void setIdCondicionPago(Integer idCondicionPago) {
        this.idCondicionPago = idCondicionPago;
    }

    public String getCondicionPago() {
        return CondicionPago;
    }

    public void setCondicionPago(String CondicionPago) {
        this.CondicionPago = CondicionPago;
    }

    public List<Condicionpago> getLstCondicionpago() {
        return lstCondicionpago;
    }

    public void setLstCondicionpago(List<Condicionpago> lstCondicionpago) {
        this.lstCondicionpago = lstCondicionpago;
    }

    public Condicionpago getCondicionpConstructor() {
        return CondicionpConstructor;
    }

    public void setCondicionpConstructor(Condicionpago CondicionpConstructor) {
        this.CondicionpConstructor = CondicionpConstructor;
    }
        //</editor-fold >

    
}

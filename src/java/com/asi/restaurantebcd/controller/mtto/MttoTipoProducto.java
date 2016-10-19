/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;


import com.asi.restaurantbcd.modelo.Tipoproducto;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasTipoProducto;
import com.asi.restaurantebcd.negocio.base.BusquedasTipoProductoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.util.logging.Logger;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
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
como lo llamas aqui  en el la propieda name= ""
* asi tenes llamarlo desde la vist
 * @author hp
 */
@ManagedBean(name = "mttoTipoProd")
@ViewScoped
public class MttoTipoProducto implements Serializable {

    @EJB
    private BusquedasTipoProductoLocal ejbBusqMtto;
    //<editor-fold  defaultstate="collapsed" desc="Inializar" >
    /**
     * Creates a new instance of MttoCompanias
     */
    public MttoTipoProducto(){
        
    }
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
            buscarTipoProducto();
        } catch (Exception e) {
                alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    
    /* </editor-fold>
    
   // <editor-fold  defaultstate="collapsed" desc="Variables" >
    /**
     * Creates a new instance of MttoTipoProducto
     */
    private Integer idTipoProducto;
    private String TipoProducto;
    private SessionUsr sesion ;
    
    private Tipoproducto tipoproducto;
      /**Atributo que se muestra en pantalla la lista de companias.*/
    private List <Tipoproducto> lstTipoProducto;
    /**constructor de la clase tipoProducto.*/
    private Tipoproducto tproductoConstructor;
    /**Bindin de DataTable que muestra companias.*/
    private DataTable tablaTipoProducto  =  new DataTable();
    
    /**
      * EJB Quecon tiene metodos utilitarios como:
      * Guardar, Eliminar, Buscar... 
      */
    @EJB
    private CrudBDCLocal crud;
    /**
      * EJB 
      * de busquedas  de mantenimiento.
      * aeui estas llamano al EJB 
      * ESTA MAL 
      * tenes que llamar a la interfaz 
      * 
      * AUI
      */
//      @EJB
//      private BusquedasTipoProducto ejbBusqMtto;  
//      
//      // ojo
//      c
      
     //</editor-fold >
      
  //<editor-fold  defaultstate="collapsed" desc="Metodos" >
     /**
      * Metodo para limpiar informacion de pantalla.
      */
     public void limpiarPantalla(){
         lstTipoProducto = null;
         tipoproducto = null;
         TipoProducto = null;
         idTipoProducto = null;        
     } 
     
     public void guardarTipoProducto(){
         try{
             if(TipoProducto == null || TipoProducto.equals("")){
                alert("Descripcion de tipo de Producto es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return; 
             }
             Integer codigo = ejbBusqMtto.obtenerCorreltivoTipoProducto();
             tipoproducto = new Tipoproducto();
             tipoproducto.setTipoproducto(TipoProducto.trim().toUpperCase());
             tipoproducto.setIdtipoproducto(codigo);
             
             crud.guardarEntidad(tipoproducto);
             alert("El tipo de producto se guardo exitosamente.", 
                    FacesMessage.SEVERITY_INFO);
            this.idTipoProducto = codigo;
            
             
         }catch(Exception ex){
         Logger.getLogger(MttoTipoProducto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
                     
         }
     }
   /**
      * Metodo que se ejecuta cuando se 
      * va a guardar un tipo de producto.
      */
     public void buscarTipoProducto(){
         try{
           lstTipoProducto = ejbBusqMtto.buscarTipoProducto();
           
           if(lstTipoProducto == null || lstTipoProducto.isEmpty()){
               alert("No se encontraron resultados", FacesMessage.SEVERITY_INFO);               
           }
             
         }catch(Exception ex){
        Logger.getLogger(MttoTipoProducto.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);             
         }
     }
     
     public void actualizarTipoProducto(){
         
         
         try {
            if (this.tablaTipoProducto.getRowData() != null) {
                Tipoproducto tp = this.lstTipoProducto.get(this.tablaTipoProducto.getRowIndex());
                                     
                crud.guardarEntidad(tp);
                alert("tipo de Producto actualizado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
                this.tproductoConstructor=null;
                this.tproductoConstructor=null;
                tp = null;
                this.lstTipoProducto = this.ejbBusqMtto.buscarTipoProducto();

            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
     }
     
     public void eliminarTipoProducto(){
         try {
            if (tablaTipoProducto.getRowData() != null) {

                Tipoproducto tp = this.lstTipoProducto.get(this.tablaTipoProducto.getRowIndex());
                if (crud.eliminarEntidad(tp) == true) {
                    
                    lstTipoProducto.remove(this.tablaTipoProducto.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    this.lstTipoProducto = this.ejbBusqMtto.buscarTipoProducto();
                    this.tproductoConstructor = null;

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
     }
     
     
      public void onEdit(RowEditEvent event) {  
          this.guardarTipoProducto();
          alert("Registro modificado exitosamente.",
                FacesMessage.SEVERITY_INFO);
//  System.out.println("event.getObject().." +event.getObject());    
    }  
       
    public void onCancel(RowEditEvent event) {  
        alert("Se ha cancelado la acción.",
                FacesMessage.SEVERITY_INFO);
//        System.out.println("event.getObject().." +event.getObject());
    } 
     
     private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje =  "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
     //</editor-fold >
     
    //<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
     
    //</editor-fold >

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getTipoProducto() {
        return TipoProducto;
    }

    public void setTipoProducto(String TipoProducto) {
        this.TipoProducto = TipoProducto;
    }

    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public Tipoproducto getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(Tipoproducto tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    public List<Tipoproducto> getLstTipoProducto() {
        return lstTipoProducto;
    }

    public void setLstTipoProducto(List<Tipoproducto> lstTipoProducto) {
        this.lstTipoProducto = lstTipoProducto;
    }

    public DataTable getTablaTipoProducto() {
        return tablaTipoProducto;
    }

    public void setTablaTipoProducto(DataTable tablaTipoProducto) {
        this.tablaTipoProducto = tablaTipoProducto;
    }



    
    
}

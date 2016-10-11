/*
 * Este es el ManagedBean de el Mantenimiento de Marca de Producto
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Producto;
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
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Melendez
 */
@ManagedBean(name = "mttoMarcaProducto")
@ConversationScoped
public class MttoMarcaProducto implements Serializable {

   

    public MttoMarcaProducto(){
      
        
}
@PostConstruct
public void postConstruction(){
        try{
            sesion = Utilidades.findBean("SessionUsr");
                if (sesion == null){
             alert ("Debe Iniciar Sesion",FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().getViewRoot().
                        getViewMap().clear();
            String url = "http://localhost:8080/RestaurantBDC";
            FacesContext.getCurrentInstance().getExternalContext(). redirect(url);
            }
            buscarMarcaProductos();
        }catch(Exception e){
             alert (e.getMessage(), FacesMessage.SEVERITY_FATAL);
    }
}

//<editor-fold  defaultstate="collapsed" desc="Variables" >
/**Busca beans session activa.*/
private SessionUsr sesion ;
/**Atributo que se muestra en pantalla el codigo de Marca Producto.*/
private Integer idMarcaProducto;
/**Atributo que se muestra en pantalla el codigo de Marca Producto.*/
private String marcaProducto;

//Entidad que guardara la Marca del Producto
private Producto producto;
//Entidad que muestra la lista de marcas del producto
private List <Producto> lstProductos;
/**Bindin de DataTable que muestra las Marcas del Producto.*/
private DataTable tablaProductos = new DataTable();

/**
      * EJB Quecon tiene metodos utilitarios de 
      * eliminar, de gurdar, de buscar y de mas..... 
      */
@EJB
    private CrudBDCLocal crud;
/**
      * EJB 
      * hace la busqueda de los parametros de los mantenimientos.
      */
@EJB
    private BusquedasMttoLocal ejbBusqMtto;

//</editor-fold >
//<editor-fold  defaultstate="collapsed" desc="Metodos" >

/**
* Metodo para limpiar informacion de pantalla.
*/
    public void limpiarPantalla(){

         lstProductos = null;
         producto = null;
         marcaProducto = null;
         idMarcaProducto = null;
    }
 /**
      * Metodo que se ejecuta cuando se 
      * va a guardar una marca de producto.
      */
    public void guardarProductos(){
        try{
            if(marcaProducto == null || marcaProducto.equals("")){
                alert("Descripcion obligatoria de la Marca del Producto", FacesMessage.SEVERITY_INFO);
                return;
            }
            Integer codigo = ejbBusqMtto.obtenerCorrelativoProductos();
            
            producto = new Producto();
            producto.setMarcaProducto(marcaProducto.trim().toUpperCase());
            producto.setIdMarcaProducto(codigo);
            
            crud.guardarEntidad(producto);
            alert("La Marca del Producto se Guardo Exitosamente", FacesMessage.SEVERITY_INFO);
            this.idMarcaProducto = codigo;
        }catch(Exception ex){
            Logger.getLogger(MttoMarcaProducto.class.getName())
              .log(Level.SEVERE,null,ex);
        alert(ex.getMessage(),FacesMessage.SEVERITY_INFO);
        }
    }
    public void buscarMarcaProductos() {
        try{
            lstProductos = ejbBusqMtto.buscarMarcaProducto();
            if(lstProductos == null || lstProductos.isEmpty()){
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
    }catch(Exception ex){
        Logger.getLogger(MttoMarcaProducto.class.getName())
              .log(Level.SEVERE,null,ex);
        alert(ex.getMessage(),FacesMessage.SEVERITY_INFO);
    }
    }
    public void actualizarProducto(){
        try {
           if(tablaProductos.getRowData() !=null){
               producto =(Producto) tablaProductos.getRowData();
               System.out.println("Marca Producto.." + producto);
            } 
        }catch(Exception ex){
        Logger.getLogger(MttoMarcaProducto.class.getName())
              .log(Level.SEVERE,null,ex);
        alert(ex.getMessage(),FacesMessage.SEVERITY_INFO);
        }
    }
    
    public void eliminarProducto(){
        try {
        lstProductos = ejbBusqMtto.buscarMarcaProducto();
        if (tablaProductos.getRowData() !=null){
            producto = lstProductos.get(tablaProductos.getRowIndex());
            System.out.println("Marca Producto.." + producto);
        }
        }catch(Exception ex){
        Logger.getLogger(MttoMarcaProducto.class.getName())
              .log(Level.SEVERE,null,ex);
        alert(ex.getMessage(),FacesMessage.SEVERITY_INFO); 
            
        }
    }
    private void alert(String mensaje, FacesMessage.Severity faces) {
       
         if (mensaje == null) {
            mensaje =  "-";
        }
        FacesMessage message;
        message = new FacesMessage(faces,
                "Mensaje", mensaje);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    //</editor-fold >
//<editor-fold  defaultstate="collapsed" desc="Getter y setter" >
    
    
    public Integer getIdMarcaProducto() {
        return idMarcaProducto;
    }
    public void setIdMarcaProducto(Integer idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }
    public DataTable getTablaProductos() {
        return tablaProductos;
    }
    public void setTablaCompanias(DataTable tablaProductos) {
        this.tablaProductos = tablaProductos;
    }
    public String getMarcaProducto() {
        return marcaProducto;
    }
    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }
    public List<Producto> getLstProductos() {
        return lstProductos;
    }
    public void setLstProductos(List<Producto> lstProductos) {
        this.lstProductos = lstProductos;
    } 
 

//</editor-fold >
}

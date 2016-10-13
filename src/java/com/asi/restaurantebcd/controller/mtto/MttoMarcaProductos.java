/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Marcaproducto;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasMarcaProductoLocal;
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
 * @author Melendez
 */
@ManagedBean(name = "mttoMarcaProducto")
@ViewScoped
public class MttoMarcaProductos implements Serializable {

//<editor-fold  defaultstate="collapsed" desc="Inializar" >
/**
     * Metodo  cuando carga la pagina.
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
            buscarMarcaproducto();
        } catch (Exception e) {
                alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }    
//</editor-fold >   
//<editor-fold  defaultstate="collapsed" desc="Declaracion de Variables" >    
  /**Atributo que se muestra en pantalla el codigo de estado.*/
    private Integer idmarcaproducto;
      /**Atributo que se muestra en pantalla el nombre de estado documento.*/
    private String nombremarcaproducto;
      /**Busca beans session activa.*/
    private SessionUsr sesion ;
      /**Entidad que guardara el estado.*/
    private Marcaproducto marcaproducto;
      /**Atributo que se muestra en pantalla la lista de estado.*/
    private List <Marcaproducto> listmarcaproducto;
    /**Bindin de DataTable que muestra los estado documentos.*/
    private DataTable dtmarcaproducto  =  new DataTable();
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
    private BusquedasMarcaProductoLocal ejbBusqLocal;
//</editor-fold >
//<editor-fold  defaultstate="collapsed" desc="Metodos" >    
public void limpiarPantalla() {
         listmarcaproducto = null;
         marcaproducto = null;
         nombremarcaproducto = null;
         idmarcaproducto = null;
         dtmarcaproducto = new DataTable();
    }
public void guardarMarcaproducto() {
        try {
            if (nombremarcaproducto == null || nombremarcaproducto.equals("")) {
                alert("Descripcion de la Marca del Producto es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
           
            Integer codigo = ejbBusqLocal.obtenerCorreltivoMarcaProductos();
            marcaproducto = new Marcaproducto();
            marcaproducto.setMarcaproducto(nombremarcaproducto.trim().toUpperCase());
            marcaproducto.setIdmarcaproducto(codigo);
            
            crud.guardarEntidad(marcaproducto);
            alert("La Marca del Producto documento se guardo exitosamente.", 
                    FacesMessage.SEVERITY_INFO);
            this.idmarcaproducto = codigo;
        } catch (Exception ex) {
            Logger.getLogger(MttoMarcaProductos.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
public void actualizarMarcaproducto() {
        try {
                
            if (dtmarcaproducto.getRowData() != null) {
                marcaproducto =  (Marcaproducto) dtmarcaproducto.getRowData();
                System.out.println("Marca del Producto.." + marcaproducto);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoMarcaProductos.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
public void buscarMarcaproducto() {
        try {
            listmarcaproducto =  ejbBusqLocal.buscarMarcaProductos();
            if (listmarcaproducto == null || listmarcaproducto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoMarcaProductos.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        } 
}
public void eliminarMarcaproducto() {
        try {
            listmarcaproducto =  ejbBusqLocal.buscarMarcaProductos();
         if (dtmarcaproducto.getRowData() != null) {
                marcaproducto =  listmarcaproducto.get(dtmarcaproducto.getRowIndex());
                System.out.println("Marca del Producto.." + marcaproducto);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoEstado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
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
//<editor-fold  defaultstate="collapsed" desc="Getters y Setters" >    
    public Integer getIdmarcaproducto() {
        return idmarcaproducto;
    }
    public void setIdmarcaproducto(Integer idmarcaproducto) {
        this.idmarcaproducto = idmarcaproducto;
    }
    public String getNombremarcaproducto() {
        return nombremarcaproducto;
    }
    public void setNombremarcaproducto(String nombremarcaproducto) {
        this.nombremarcaproducto = nombremarcaproducto;
    }
    public SessionUsr getSesion() {
        return sesion;
    }
    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }
    public Marcaproducto getMarcaproducto() {
        return marcaproducto;
    }
    public void setMarcaproducto(Marcaproducto marcaproducto) {
        this.marcaproducto = marcaproducto;
    }
    public List<Marcaproducto> getListmarcaproducto() {
        return listmarcaproducto;
    }
    public void setListmarcaproducto(List<Marcaproducto> listmarcaproducto) {
        this.listmarcaproducto = listmarcaproducto;
    }
    public DataTable getDtmarcaproducto() {
        return dtmarcaproducto;
    }
    public void setDtmarcaproducto(DataTable dtmarcaproducto) {
        this.dtmarcaproducto = dtmarcaproducto;
    }
    public CrudBDCLocal getCrud() {
        return crud;
    }
    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }
    public BusquedasMarcaProductoLocal getEjbBusqLocal() {
        return ejbBusqLocal;
    }
    public void setEjbBusqLocal(BusquedasMarcaProductoLocal ejbBusqLocal) {
        this.ejbBusqLocal = ejbBusqLocal;
    }
//</editor-fold>
}

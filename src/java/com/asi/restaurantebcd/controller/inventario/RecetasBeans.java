/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Receta;
import com.asi.restaurantbcd.modelo.Recetadetalle;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
/**
 *
 * @author samaelopez
 */
@ManagedBean(name = "recetas")
@ViewScoped
public class RecetasBeans {

       @Inject
    private SessionUsr sesion;
    /**
     * Creates a new instance of Recetas
     */
    public RecetasBeans() {
    }
    private List<Recetadetalle > lstRecetaDetalle;
    private Receta receta;
    private Integer noreceta;
     private String descripcion;
    private String usuarioIniciador;
    private String productoTerminado;
    private Date fecha;
    
    public void limpiarPantalla() {
        lstRecetaDetalle = null;
        receta  = null;
        noreceta = null;
        descripcion = null;
        usuarioIniciador = null;
        productoTerminado = null;
        fecha =null;
    }
    
        public void guaradarReceta() {
            
       if(lstRecetaDetalle == null || lstRecetaDetalle.isEmpty()) {
           alert("La receta no tiene detalle.", FacesMessage.SEVERITY_WARN);
           return;
       }
       if (descripcion == null) {
           alert("La descripcion de la receta es obligatorio.", 
                   FacesMessage.SEVERITY_WARN); 
           return;
       }
       receta = new Receta();
       receta.setDescripcion(descripcion);
       receta.setEstado(1);
       receta.setFechacreacion(new Date());
      //receta.setIdusuariocrea(sesion.getSucursal().getIdsucursal());
    }
    
    
    
    
    
        public void mostrarDialogProd() {
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProducto').show();");
    }
    
                public void mostrarDialogMonitor() {
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoMonitor').show();");
    }
    
         public void alert(String mensaje, FacesMessage.Severity faces) {
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje);
         } 
    
    
   
        
    public List<Recetadetalle> getLstRecetaDetalle() {
        return lstRecetaDetalle;
    }

    public void setLstRecetaDetalle(List<Recetadetalle> lstRecetaDetalle) {
        this.lstRecetaDetalle = lstRecetaDetalle;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Integer getNoreceta() {
        return noreceta;
    }

    public void setNoreceta(Integer noreceta) {
        this.noreceta = noreceta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioIniciador() {
        return usuarioIniciador;
    }

    public void setUsuarioIniciador(String usuarioIniciador) {
        this.usuarioIniciador = usuarioIniciador;
    }

    public String getProductoTerminado() {
        return productoTerminado;
    }

    public void setProductoTerminado(String productoTerminado) {
        this.productoTerminado = productoTerminado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}

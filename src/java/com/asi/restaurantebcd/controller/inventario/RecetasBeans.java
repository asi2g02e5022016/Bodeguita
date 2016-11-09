/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Receta;
import com.asi.restaurantbcd.modelo.Recetadetalle;
import com.asi.restaurantbcd.modelo.RecetadetallePK;
import com.asi.restaurantbcd.modelo.Vwproductos;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
/**
 *
 * @author samaelopez
 */
@ManagedBean(name = "recetas")
@ViewScoped
public class RecetasBeans implements Serializable{

    @EJB
    private BusquedasProductosLocal busquedasProductos;

    @EJB
    private CrudBDCLocal crudBDC;

       @Inject
    private SessionUsr sesion;
 
       
       
       
    /**
     * Creates a new instance of Recetas
     */
    public RecetasBeans() {
    }
    private List<Recetadetalle > lstRecetaDetalle;
        private List<Receta > lstRecetaMon;
        private List<Vwproductos > lstProducto;
    private Receta receta;
    private Integer noreceta;
      private String descripProducto;
     private String descripcion;
    private String usuarioIniciador;
    private String productoTerminado;
    private boolean  mostrarGuardar;
    private Date fecha;
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private Double cantidad;
    
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
            
        try {
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
            receta.setIdusuariocrea(sesion.getUsuario().getIdusuario());
            receta.setRecetadetalleList(lstRecetaDetalle);
            for (Recetadetalle recetadetalle : lstRecetaDetalle) {
                recetadetalle.getRecetadetallePK().setIdreceta(0);
                recetadetalle.setReceta(receta);
            }
            crudBDC.guardarEntidad(receta);
            alert("La receta se guardo exitosamente.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(RecetasBeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
            public void mostrarDialogProd() {
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProducto').show();");
    }
        
        public void buscarProducto() {
        try {
            Map filtro = new HashMap();
            if (descripProducto != null) {
                filtro.put("producto", descripProducto.trim());
                
            }
            filtro.put("activo", 1);
            filtro.put("tipo", 1);
            
            lstProducto = busquedasProductos.buscarProducto(filtro);
            System.out.println("lstProducto.." +lstProducto);
            if (lstProducto == null || lstProducto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
         } catch (Exception ex) {
            Logger.getLogger(Receta.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
      }
        
        public void limpiarDiaoloProd(){
            descripProducto = null;
            cantidad = null;
            lstProducto =  null;
                 
            
        }
     public void onRowSelectProduc(SelectEvent event) {
        try {
            Vwproductos idP  =  ((Vwproductos) event.getObject());
            System.out.println("w33");
            Producto pr = crudBDC.buscarEntidad(Producto.class, idP.getIdproducto());
            System.out.println("yytewhge");
            if (lstRecetaDetalle == null) {
                lstRecetaDetalle = new ArrayList<>();
            }
            RecetadetallePK idDer = new RecetadetallePK();
            idDer.setIdproducto(idP.getIdproducto());
            idDer.setIdreceta(1);
            
            Recetadetalle recDet = new Recetadetalle();
            recDet.setCantidad(cantidad);
            recDet.setFactorconvercion(cantidad);
            recDet.setIdmedidacargo(idP.getIdmedida());
            recDet.setSalida(noreceta);
             recDet.setProducto(pr);
            recDet.setRecetadetallePK(idDer);
            lstRecetaDetalle.add(0, recDet);
            System.out.println("lstCompradeta.." +lstRecetaDetalle);
            RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProducto').hide();");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Receta.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
        
        
                public void mostrarDialogMonitor() {
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoRecetas').show();");
    }
    
               public void buscarRecetas() {
        try {
            Query q = em.createNamedQuery("Receta.findAll");
            lstRecetaMon =  q.getResultList();
            
            System.out.println("lstProducto.." +lstRecetaMon);
            if (lstRecetaMon == null || lstRecetaMon.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
         } catch (Exception ex) {
            Logger.getLogger(Receta.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
      }
     
               
               public void onRowSelectMon(SelectEvent event) {
        try {
            receta =  (Receta) event.getObject();
            lstRecetaDetalle = new ArrayList<>();
            lstRecetaDetalle.addAll(receta.getRecetadetalleList());
            System.out.println("lstCompradeta.." +lstRecetaDetalle);
            RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoRecetas').hide();");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Receta.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
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

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
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

    public String getDescripProducto() {
        return descripProducto;
    }

    public void setDescripProducto(String descripProducto) {
        this.descripProducto = descripProducto;
    }

    public List<Vwproductos> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<Vwproductos> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public List<Receta> getLstRecetaMon() {
        return lstRecetaMon;
    }

    public void setLstRecetaMon(List<Receta> lstRecetaMon) {
        this.lstRecetaMon = lstRecetaMon;
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public boolean isMostrarGuardar() {
        return mostrarGuardar;
    }

    public void setMostrarGuardar(boolean mostrarGuardar) {
        this.mostrarGuardar = mostrarGuardar;
    }
    
    
    
    
    
}

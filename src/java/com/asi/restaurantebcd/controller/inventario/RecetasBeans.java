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
import com.asi.restaurantebcd.negocio.util.EstadoEnum;
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
          private List<Vwproductos > lstProductoPT;
    private Receta receta;
    private Recetadetalle recetaDetaPT;
    private Integer noreceta;
      private String descripProducto;
     private String descripcion;
    private String usuarioIniciador;
       private String productoPT;
      private Double cantidadPT;
           private String medidaProductoMP;
       private String productoMP;
      private Double cantidadMP;
    private String productoTerminado;
    private boolean  mostrarGuardar;
    private Date fecha;
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private Double cantidad;
    
    public void limpiarPantalla() {
        productoMP = null;
        medidaProductoMP = null;
        cantidadMP= null;
        cantidad = null;
        cantidadPT = null;
        descripProducto =  null;
        descripcion =  null;
        lstProducto = null;
        lstProductoPT = null;
        lstRecetaDetalle =  null;
        lstRecetaMon = null;
        lstRecetaDetalle = null;
        receta  = null;
        productoMP =  null;
        productoPT = null;
        productoTerminado = null;
        noreceta = null;
        descripcion = null;
        usuarioIniciador = null;
        productoTerminado = null;
        mostrarGuardar = true;
        fecha =null;
    }
    
        public void guaradarReceta() {
            System.out.println("entro... ");
        try {
            if(lstRecetaDetalle == null || lstRecetaDetalle.isEmpty()) {
                alert("La receta no tiene detalle.", FacesMessage.SEVERITY_WARN);
                return;
            }
            if (recetaDetaPT == null) {
                alert("Debe elegor un produto PT", FacesMessage.SEVERITY_INFO);
                return;
            }
            System.out.println("que paso...");
            if (descripcion == null || descripcion.equals("")) {
                alert("La descripcion de la receta es obligatorio.",
                        FacesMessage.SEVERITY_WARN);
                return;
            }
            receta = new Receta();
            receta.setDescripcion(descripcion);
            receta.setIdreceta(idRecdeta());
            receta.setEstado(EstadoEnum.TERMINADO.getInteger());
            receta.setFechacreacion(new Date());
            receta.setIdusuariocrea(sesion.getUsuario().getIdusuario());
            mostrarGuardar =  false;
            Producto idRecero =  null;
            
            List <Recetadetalle> lstDeta = new ArrayList<>();
            lstDeta.addAll(lstRecetaDetalle);
            lstDeta.add(recetaDetaPT);
            for (Recetadetalle recetadetalle : lstDeta) {
            if (recetadetalle.getSalida().toString().equals("0"))    {
                idRecero =  recetadetalle.getProducto();
            }
            RecetadetallePK idDer = new RecetadetallePK();
            idDer.setIdproducto(recetadetalle.getProducto().getIdproducto());
            idDer.setIdreceta(receta.getIdreceta());
                recetadetalle.setRecetadetallePK(idDer);
                recetadetalle.setReceta(receta);
            } 
            if (idRecero == null) {
                alert("No se pudo encontrar la receta a cargar a inventario.",
                        FacesMessage.SEVERITY_WARN);
                return;
            }
            
            RecetadetallePK idDer = new RecetadetallePK();
            idDer.setIdreceta(receta.getIdreceta());
            idDer.setIdproducto(recetaDetaPT.getProducto().getIdproducto());
            recetaDetaPT.setReceta(receta);
            recetaDetaPT.setRecetadetallePK(idDer);
            receta.setRecetadetalleList(lstDeta);
            crudBDC.guardarEntidad(receta);
            idRecero.setIdreceta(receta);
            crudBDC.guardarEntidad(idRecero);
            alert("La receta se guardo exitosamente.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(RecetasBeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
            public void mostrarDialogProd() {
//                if (productoPT == null || 
//                        productoPT.equals("")) {
//                    alert("Debe selecionar un producto terminado", FacesMessage.SEVERITY_INFO);
//                    return;
//                }
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
        
         public void buscarProductoPT() {
        try {
            Map filtro = new HashMap();
            if (productoPT != null) {
                filtro.put("producto", productoPT.trim());
                
            }
            filtro.put("activo", 1);
            filtro.put("tipo", 1);
            
            lstProductoPT = busquedasProductos.buscarProducto(filtro);
            System.out.println("lstProducto.." +lstProductoPT);
            if (lstProductoPT == null || lstProductoPT.isEmpty()) {
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
        
        private Integer idRecdeta() {
            Integer valor = null ;
            
            Query q = em.createNativeQuery("SELECT max(idreceta) FROM receta ");
            valor = (Integer) q.getSingleResult();
              if (valor == null) {
            valor = Integer.parseInt("1");
        }
        valor   =  valor + 1;
            return valor;
        }
                
     
     public void onRowSelectProduc(SelectEvent event) {
        try {
            if (cantidadMP == null || 
                    cantidadMP.equals(Double.parseDouble("0"))) {
                alert("La cantidad tiene que ser mayor a cero. ", 
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            Vwproductos idP  =  ((Vwproductos) event.getObject());
            System.out.println("w33");
            Producto pr = crudBDC.buscarEntidad(Producto.class, idP.getIdproducto());
            System.out.println("yytewhge");
            if (lstRecetaDetalle == null) {
                lstRecetaDetalle = new ArrayList<>();
            }
            Recetadetalle recDet = new Recetadetalle();
            recDet.setCantidad(cantidadMP);
            recDet.setSalida(1);
            recDet.setProducto(pr);
           
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
            public void eliminarProducto(Recetadetalle p) {
        try {
            if (p != null) {
                lstRecetaDetalle.remove(p);
       
            }
        } catch (Exception ex) {
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
            for (Recetadetalle recetadetalle : receta.getRecetadetalleList()) { 
                if (recetadetalle.getSalida() == 1) {
                    
                    Producto p = crudBDC.buscarEntidad(Producto.class,
                            recetadetalle.getRecetadetallePK().getIdproducto());
                   recetadetalle.setProducto(p);
                   recetadetalle.setSalida(1);
                lstRecetaDetalle.add(recetadetalle);
                } else {
                    Producto p = crudBDC.buscarEntidad(Producto.class,
                            recetadetalle.getRecetadetallePK().getIdproducto());
                    productoPT =  p.getProducto();
                    medidaProductoMP = p.getIdmedida().getMedida();
                     medidaProductoMP = p.getIdmedida().getMedida();
                     cantidadPT = recetadetalle.getCantidad();
                     
                    
                }
            }
            descripcion = receta.getDescripcion();
            mostrarGuardar =  false;
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
               RequestContext.getCurrentInstance().showMessageInDialog(message);
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

    public void persist1(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }



    public String getProductoPT() {
        return productoPT;
    }

    public void setProductoPT(String productoPT) {
        this.productoPT = productoPT;
    }

    public Double getCantidadPT() {
        return cantidadPT;
    }

    public void setCantidadPT(Double cantidadPT) {
        this.cantidadPT = cantidadPT;
    }

    public String getMedidaProductoMP() {
        return medidaProductoMP;
    }

    public void setMedidaProductoMP(String medidaProductoMP) {
        this.medidaProductoMP = medidaProductoMP;
    }

    public String getProductoMP() {
        return productoMP;
    }

    public void setProductoMP(String productoMP) {
        this.productoMP = productoMP;
    }

    public Double getCantidadMP() {
        return cantidadMP;
    }

    public void setCantidadMP(Double cantidadMP) {
        this.cantidadMP = cantidadMP;
        

        
    }

    public List<Vwproductos> getLstProductoPT() {
        return lstProductoPT;
    }

    public void setLstProductoPT(List<Vwproductos> lstProductoPT) {
        this.lstProductoPT = lstProductoPT;
    }
    
    

            
            public void mostrarDialogProdusStoPT() {

//                }
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProductoPT').show();");
    }
    
    
    
     public void onRowSelectProducuctoPT(SelectEvent event) {
        try {
            if (cantidadPT == null || 
                    cantidadPT.equals(Double.parseDouble("0"))) {
                alert("La cantidad tiene que ser mayor a cero. ", 
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            Vwproductos idP  =  ((Vwproductos) event.getObject());
        
            Producto pr = crudBDC.buscarEntidad(Producto.class, idP.getIdproducto());
          
            if (lstRecetaDetalle == null) {
                lstRecetaDetalle = new ArrayList<>();
            }
            recetaDetaPT  = new Recetadetalle();
            recetaDetaPT.setCantidad(cantidadPT);
            recetaDetaPT.setSalida(0);
            recetaDetaPT.setProducto(pr);
           productoPT =  idP.getProducto();
           medidaProductoMP = idP.getMedida();
          //  lstRecetaDetalle.add(0, recDet);
            RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProducto').hide();");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Receta.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
     
    
}

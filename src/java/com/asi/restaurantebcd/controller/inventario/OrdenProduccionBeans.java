/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Estado;
import com.asi.restaurantbcd.modelo.Ordenproduccion;
import com.asi.restaurantbcd.modelo.OrdenproduccionPK;
import com.asi.restaurantbcd.modelo.Ordenproducciondetalle;
import com.asi.restaurantbcd.modelo.OrdenproducciondetallePK;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Receta;
import com.asi.restaurantbcd.modelo.Recetadetalle;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasComprasLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal;
//import com.asi.restaurantebcd.negocio.base.ConvercionesLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.base.ProcesosInventariosLocal;
import com.asi.restaurantebcd.negocio.base.TransaccionesInventarioLocal;
import com.asi.restaurantebcd.negocio.util.EstadoEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@ManagedBean(name = "ordenProduccionBeans")
@ViewScoped
public class OrdenProduccionBeans implements Serializable {

    @EJB
    private TransaccionesInventarioLocal transaccionesInventario;
    
    /**
     * Creates a new instance of OrdenProduccion
     */
    public OrdenProduccionBeans() {
        
        
    }
    private Ordenproduccion ordenProd;
    private List <Ordenproduccion> lstOrdenProd;
     private List <Ordenproducciondetalle> lstOrdenProdDetalle;
      private Receta receta;
    private List <Receta> lstReceta;
    private String descripcionReceta;
    private String producoPT;
    private String medidaPT;
    
    
     @EJB
    private BusquedasComprasLocal ejbBusComp;
        @EJB
    private CrudBDCLocal crud;
    @EJB
    private BusquedasProductosLocal ejbBusProd;
    @EJB
    private ProcesosInventariosLocal ejbProInv;
    @Inject
    private SessionUsr sesion;
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private Double cantidadArealizar;
    
    
    
    
    /**
     * 
     */
    public void limpiarPantalla() {
        this.lstOrdenProd = null;
        this.lstOrdenProdDetalle = null;
        this.lstReceta = null;
        this.ordenProd = null;
    } 
    /**
     * 
     */
    public void guardarOrden() {
        try {
            if (lstOrdenProdDetalle == null) {
                alert("La orden no tiene detalle.", FacesMessage.SEVERITY_INFO);
                return;
            }
            OrdenproduccionPK idOrdenPK = new  OrdenproduccionPK();
            Integer codigoCom =  ejbBusComp.obtenerCorreltivoCompra(
                    sesion.getSucursal().getIdsucursal(),
                    Ordenproduccion.class, "idsucursal", "idordenproduccion");
            ordenProd = new Ordenproduccion();
            idOrdenPK.setIdordenproduccion(codigoCom);
            idOrdenPK.setIdsucursal(sesion.getSucursal().getIdsucursal());
            ordenProd.setOrdenproduccionPK(idOrdenPK);
            ordenProd.setFechapedido(new Date());
            Estado est = crud.buscarEntidad(Estado.class,
                    EstadoEnum.TERMINADO.getInteger());
            ordenProd.setIdestado(est);
             
            int corel = 0;
            for (Ordenproducciondetalle det : lstOrdenProdDetalle) {
                
                if (true) {
                    
                }
                corel++;
                    OrdenproducciondetallePK idPK = new OrdenproducciondetallePK();
                    idPK.setIdSucursal(idOrdenPK.getIdsucursal());
                    idPK.setIdordenproduccion(idOrdenPK.getIdordenproduccion());
                    idPK.setIdordenproducciondetalle(corel);
                    det.setOrdenproducciondetallePK(idPK);
                    det.setOrdenproduccion(ordenProd);
                  
            }
            ordenProd.setOrdenproducciondetalleList(lstOrdenProdDetalle);
            ordenProd.setSucursal(sesion.getSucursal());
            ordenProd.setIdusuario(sesion.getUsuario());
            transaccionesInventario.guardarOrdenCompra(ordenProd);
            alert("La orden fue ejecutada exitosamente.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(OrdenProduccionBeans.class.getName())
                    .log(Level.SEVERE, null, ex); 
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
        
    
      //<editor-fold  defaultstate="collapsed" desc="Monitor recetas">
    
               public void mostrarDialogMonirOT() {
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoMonitor').show();");
    }
            public void mostrarDialogRecetas() {
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoRecetas').show();");
    }
                    public void buscarOrdeneProd() {
             Query q = em.createNamedQuery("Ordenproduccion.findAll");
        lstOrdenProd = q.getResultList();
        if (lstOrdenProd == null){
            alert("No se encontraron resultados.", FacesMessage.SEVERITY_WARN);
            return;
        }
           }
                    
                    
                    
           public void buscarRecetas() {
             Query q = em.createNamedQuery("Receta.findAll");
        lstReceta = q.getResultList();
        if (lstReceta == null){
            alert("No se encontraron resultado.", FacesMessage.SEVERITY_WARN);
            return;
        }
           }
        
                   
    private List<Recetadetalle> buscarDetallesRecetas(Integer idreceta) {
        StringBuilder jp = new StringBuilder();
        Recetadetalle o;
        jp.append(" SELECT o FROM Recetadetalle o ");
        jp.append(" WHERE o.recetadetallePK.idreceta = :receta");
        Query q = em.createQuery(jp.toString());
        q.setParameter("receta", idreceta);
        
        return q.getResultList();
    }
    /**
     * 
     * @param event 
     */       
    public void onRowSelectCompra(SelectEvent event) {
        try {
            receta = (Receta) event.getObject();
            System.out.println("receta.." +receta);
            if (receta == null) {
                alert("La receta es obligatorio.", FacesMessage.SEVERITY_ERROR);
                return;
            }
            List <Recetadetalle> lstDet = buscarDetallesRecetas(receta.getIdreceta());            
            lstOrdenProdDetalle = new ArrayList<>();
            for (Recetadetalle recDeta : lstDet) {
                if (recDeta.getSalida() == 1) {
                Ordenproducciondetalle detaOP = new Ordenproducciondetalle();
                detaOP.setCantidadconfirmada(recDeta.getCantidad().doubleValue());
                detaOP.setCostounitario(recDeta.getCantidad().doubleValue());
                Producto prod = crud.buscarEntidad(Producto.class,
                        recDeta.getRecetadetallePK().getIdproducto());
                detaOP.setIdproducto(prod);
                lstOrdenProdDetalle.add(detaOP);
                } else {
                    Producto prod = crud.buscarEntidad(Producto.class,
                        recDeta.getRecetadetallePK().getIdproducto());
                    producoPT =  prod.getProducto();
                    medidaPT =  prod.getIdmedida().getMedida();
                    descripcionReceta = receta.getDescripcion();
                    
                }
            }
            
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogoRecetas').hide();");
        } catch (Exception ex) {
            Logger.getLogger(OrdenProduccionBeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
        public void onRowSelectMonito(SelectEvent event) {
        try {
            ordenProd = (Ordenproduccion) event.getObject();
            
            lstOrdenProdDetalle = new ArrayList<>();
            for (Ordenproducciondetalle det : ordenProd.getOrdenproducciondetalleList()) {
                
                lstOrdenProdDetalle.add(det);
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogoMonitor').hide();");
        } catch (Exception ex) {
            Logger.getLogger(OrdenProduccionBeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //</editor-fold >
    
    
    
    
    
    /**
     * 
     * @param mensaje
     * @param faces 
     */
    public void alert(String mensaje, FacesMessage.Severity faces) {
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje);
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public Ordenproduccion getOrdenProd() {
        return ordenProd;
    }

    public void setOrdenProd(Ordenproduccion ordenProd) {
        this.ordenProd = ordenProd;
    }

    public List<Ordenproduccion> getLstOrdenProd() {
        return lstOrdenProd;
    }

    public void setLstOrdenProd(List<Ordenproduccion> lstOrdenProd) {
        this.lstOrdenProd = lstOrdenProd;
    }

    public List<Receta> getLstReceta() {
        return lstReceta;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public void setLstReceta(List<Receta> lstReceta) {
        this.lstReceta = lstReceta;
    }

    public List<Ordenproducciondetalle> getLstOrdenProdDetalle() {
        return lstOrdenProdDetalle;
    }

    public String getDescripcionReceta() {
        return descripcionReceta;
    }

    public void setDescripcionReceta(String descripcionReceta) {
        this.descripcionReceta = descripcionReceta;
    }

    public void setLstOrdenProdDetalle(List<Ordenproducciondetalle> lstOrdenProdDetalle) {
        this.lstOrdenProdDetalle = lstOrdenProdDetalle;
    }

    public String getProducoPT() {
        return producoPT;
    }

    public void setProducoPT(String producoPT) {
        this.producoPT = producoPT;
    }

    public String getMedidaPT() {
        return medidaPT;
    }

    public void setMedidaPT(String medidaPT) {
        this.medidaPT = medidaPT;
    }



   
    
    
    
    
    
    
   
}

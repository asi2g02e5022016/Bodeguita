/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Compra;
import com.asi.restaurantbcd.modelo.Estado;
import com.asi.restaurantbcd.modelo.Ordenproduccion;
import com.asi.restaurantbcd.modelo.OrdenproduccionPK;
import com.asi.restaurantbcd.modelo.Ordenproducciondetalle;
import com.asi.restaurantbcd.modelo.OrdenproducciondetallePK;
import com.asi.restaurantbcd.modelo.Receta;
import com.asi.restaurantbcd.modelo.Recetadetalle;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasComprasLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal;
import com.asi.restaurantebcd.negocio.base.ConvercionesLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.base.ProcesosInventariosLocal;
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
public class OrdenProduccionBeans {

    @EJB
    private ConvercionesLocal converciones;

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
                    Compra.class, "idsucursal");
            ordenProd = new Ordenproduccion();
            ordenProd.setOrdenproduccionPK(idOrdenPK);
            ordenProd.setFechapedido(new Date());
            Estado est = crud.buscarEntidad(Estado.class, 10);
            ordenProd.setIdestado(est);
            int corel = 0;
            for (Ordenproducciondetalle det : lstOrdenProdDetalle) {
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
            alert("La orden fue ejecutada exitosamente.", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(OrdenProduccionBeans.class.getName())
                    .log(Level.SEVERE, null, ex); 
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
        
    
      //<editor-fold  defaultstate="collapsed" desc="Monitor recetas">
    
           public void mostrarDialogRecetas() {
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoRecetas').show();");
    }
           
              public void buscarRecetas() {
             Query q = em.createNamedQuery("Receta.findAll");
        lstReceta = q.getResultList();
        if (lstReceta == null){
            alert("No se encontraron resultado.", FacesMessage.SEVERITY_WARN);
            return;
        }
                    
    }        
    public void onRowSelectCompra(SelectEvent event) {
        receta  = (Receta) event.getObject();
        if (receta == null) {
            alert("La receta es obligatorio.", FacesMessage.SEVERITY_ERROR);
            return;
        }
        if (receta.getRecetadetalleList() == null) {
            alert("La receta no tiene detalle.", FacesMessage.SEVERITY_WARN);
            return;
        }
        
        lstOrdenProdDetalle = new  ArrayList<>();
        for (Recetadetalle recDeta : receta.getRecetadetalleList()) {
            Ordenproducciondetalle detaOP = new Ordenproducciondetalle();
//            
//            Double cant = converciones.getValorConvercion(recDeta.getCantidad(), 
//                    recDeta.GET, cantidadArealizar);
            detaOP.setCantidadconfirmada(recDeta.getCantidad().doubleValue());
            detaOP.setCosto(recDeta.getCantidad().doubleValue());
          //  detaOP.setIdproducto(0);
//            detaOP.setIva(detaOP.getIva());
//            detaOP.setPrecio(0);
            lstOrdenProdDetalle.add(detaOP);
            
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

    public void setLstOrdenProdDetalle(List<Ordenproducciondetalle> lstOrdenProdDetalle) {
        this.lstOrdenProdDetalle = lstOrdenProdDetalle;
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

   
    
    
    
    
    
    
   
}

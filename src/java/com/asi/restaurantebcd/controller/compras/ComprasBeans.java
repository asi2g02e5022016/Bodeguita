/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.compras;

import com.asi.restaurantbcd.modelo.Compra;
import com.asi.restaurantbcd.modelo.Compradetalle;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Proveedor;
import com.asi.restaurantbcd.modelo.Vwproductos;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasComprasLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author samaelopez
 */
@ManagedBean( name = "comprasBeans")
@ViewScoped
public class ComprasBeans implements  Serializable {
   
      //<editor-fold  defaultstate="collapsed" desc="Constructor" >
    /**
     * Creates a new instance of CompraBean
     */
    public ComprasBeans() {
    }
       
//      @PostConstruct
//    public void postConstruction(){
//        try{
//            if(sesion == null){
//                System.out.println("redirect docs");
//                alert("Debe Iniciar Sesion",FacesMessage.SEVERITY_FATAL);
//                FacesContext.getCurrentInstance().getViewRoot().
//                        getViewMap().clear();
//            String url = "http://localhost:8080/RestaurantBDC";
//            FacesContext.getCurrentInstance().getExternalContext().
//                    redirect(url);
//            } 
//        } catch (Exception e) {
//                alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
//        }
//    }
    //</editor-fold >
   
      //<editor-fold  defaultstate="collapsed" desc="Variables" >
    private Integer nodocu;
    private String observacion;
    private String estado;
    private String usr;
    private Dialog dialogProductos = new Dialog();
    private String codigo;
    private String serie;
    private String nombreProveedor;
    private String descripcionProducto;
    private BigDecimal cantidadSolic;
    private BigDecimal cantidadConfirmada;
    private Proveedor proveedor;
    private Vwproductos producto;
            SessionUsr sesion = Utilidades.findBean("sessionBean");
    private Date fecha;
    private String mensaje;
    private Compra compraEnca;
    private Compradetalle compraDeta;
    private DataTable tablaProd  =  new DataTable();
    private List < Compra > lstCompraMonitor =  new ArrayList<>();
    private List < Compradetalle > lstCompradeta =  new ArrayList<>();
    private List < Proveedor > lstProveedor =  new ArrayList<>();
      private List < Vwproductos > lstProducto =  new ArrayList<>();
    @EJB
    private CrudBDCLocal crud;
    @EJB
    private BusquedasComprasLocal ejbBusComp;
    @EJB
    private BusquedasProductosLocal ejbBusProd;
   
    //</editor-fold >

      //<editor-fold  defaultstate="collapsed" desc="Metodos principales" >
   /**
    * 
    */
   public void nuevaCompra() {
       lstCompradeta.clear();
       nodocu = null;
       estado = null;
       nombreProveedor = null;
       fecha = null;
       observacion = null;
       usr = null;
       codigo = null;
       serie = null;
       
   }
   /**
    * 
    */
   public void guardarCompra() {
       
        try {
            
            compraEnca = new Compra();
            
//            compraEnca.setCodigoFactura(codigo);
//            compraEnca.setCompraDetalleList(lstCompradeta);
//            compraEnca.setFechaCompra(new Date());
//            compraEnca.setIdCompra(ejbBusComp.obtenerCorreltivoCompra());
//            Estado est = crud.buscarEntidad(Estado.class, 2);
//            compraEnca.setIdEstado(est);
//            compraEnca.setIdProveedor(proveedor);
//            compraEnca.setIdSucursal(sesion.getSucursal());
//            compraEnca.setIdUsuario(sesion.getUsuario());
//            compraEnca.setSerieFactura(serie);
            alert("El documento se guardo exitosamente", FacesMessage.SEVERITY_INFO);
            
        } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
      public void ActualizarExistenciaCompra() {
          
      }
  /**
    * 
    */
   public void anularCompra() {
       
   }
  /**
    * 
    */
   public void buscarCompra() {
       
   }
//  /**
//    * 
//    */
//   public void imprimitCompra() {
//       try {
//            Map param = new HashMap();
//                    
//            FacesContext fc = FacesContext.getCurrentInstance();
//            HttpServletRequest request = (HttpServletRequest) fc
//                               .getExternalContext().getRequest();
//            String url = request.getContextPath() + "/Reporte";
//             request.getSession().setAttribute("datasourse","jdbc/ifbc");
//            request.getSession().setAttribute("url",
//              "/com/asi/estarurantebcd/reportes"
//                    +"Compra.jasper");
//            request.getSession().setAttribute("format","PDF");
//            request.getSession().setAttribute("parameters", param);
////            RequestContext context = RequestContext.getCurrentInstance();
////             context.execute("window.open('resource.jsp', '_newtab')");
//                        RequestContext context = RequestContext.getCurrentInstance();
//             context.execute(             "window.open('" + url
//                   + "','Rpt','location=0,menubar=0,resizable=1,"
//                   + "status=0,toolbar=0');");
////            JavascriptContext.addJavascriptCall(
////                    FacesContext.getCurrentInstance(),
////                    "window.open('" + url
////                   + "','Rpt','location=0,menubar=0,resizable=1,"
////                   + "status=0,toolbar=0');");
//        } catch (Exception e) {
//            alert(e.getMessage(), FacesMessage.SEVERITY_ERROR);
//        }
//   }
         public void alert(String mensaje, FacesMessage.Severity faces) {
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje);
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

       //</editor-fold >
         
      //<editor-fold  defaultstate="collapsed" desc="PopupProveedor" >
      /**
       * 
       */
      public void buscarProveedor() {
        try {
            Map filtro = new HashMap();
            if (nombreProveedor != null) {
                filtro.put("nombre", nombreProveedor.trim());
                
            }
            lstProveedor = ejbBusComp.buscarProveedores(filtro);
            if (lstProveedor == null || lstProveedor.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
         } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
      }
      /**
       * 
       */
      public void limpiarProveedor() {
       lstProveedor = null;
       nombreProveedor = null;
      }
             //</editor-fold >
      
      //<editor-fold  defaultstate="collapsed" desc="PopupProveedor" >
      /**
       * 
       */
      public void buscarProducto() {
        try {
            Map filtro = new HashMap();
            if (descripcionProducto != null) {
                filtro.put("producto", descripcionProducto.trim());
                
            }
            filtro.put("activo", 1);
            filtro.put("tipo", 1);
            
            lstProducto = ejbBusProd.buscarProducto(filtro);
            System.out.println("lstProducto.." +lstProducto);
            if (lstProducto == null || lstProducto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
         } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
      }
      /**
       * 
       */
      public void limpiarProducto() {
       lstProducto = null;
       descripcionProducto = null;
      
      }
      public void imprimirReporteCompra() {

       try {
            Map param = new HashMap();
                    
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc
                               .getExternalContext().getRequest();
            String url = request.getContextPath() + "/Reporte";
             request.getSession().setAttribute("datasourse","jdbc/ifbc");
            request.getSession().setAttribute("url",
              "/com/asi/restaurantebcd/reportes/compra/"
                    +"RptCompras.jasper");
            request.getSession().setAttribute("format","PDF");
            request.getSession().setAttribute("parameters", param);
//            RequestContext context = RequestContext.getCurrentInstance();
//             context.execute("window.open('resource.jsp', '_newtab')");
                        RequestContext context = RequestContext.getCurrentInstance();
             context.execute(             "window.open('" + url
                   + "','Rpt','location=0,menubar=0,resizable=1,"
                   + "status=0,toolbar=0');");
//            JavascriptContext.addJavascriptCall(
//                    FacesContext.getCurrentInstance(),
//                    "window.open('" + url
//                   + "','Rpt','location=0,menubar=0,resizable=1,"
//                   + "status=0,toolbar=0');");
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
             //</editor-fold >
      
      //<editor-fold  defaultstate="collapsed" desc="Monitor" >
      /**
       * 
       */
      public void buscarMonitor() {
        try {
            Map filtro = new HashMap();
            if (descripcionProducto != null) {
                filtro.put("producto", descripcionProducto.trim());
                
            }
            lstProducto = ejbBusProd.buscarProducto(filtro);
            if (lstProveedor == null || lstProveedor.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
         } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
      }
      /**
       * 
       */
      public void limpiarMonitor() {
       lstProducto = null;
       descripcionProducto = null;
      }
             //</editor-fold >
         
      //<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
 public Integer getNodocu() {
        return nodocu;
    }

    public Dialog getDialogProductos() {
        return dialogProductos;
    }

    public DataTable getTablaProd() {
        return tablaProd;
    }

    public void setTablaProd(DataTable tablaProd) {
        this.tablaProd = tablaProd;
    }

    public void setDialogProductos(Dialog dialogProductos) {
        this.dialogProductos = dialogProductos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getSerie() {
        return serie;
    }

    public List<Compra> getLstCompraMonitor() {
        return lstCompraMonitor;
    }

    public void setLstCompraMonitor(List<Compra> lstCompraMonitor) {
        this.lstCompraMonitor = lstCompraMonitor;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
 

    public void setNodocu(Integer nodocu) {
        this.nodocu = nodocu;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Proveedor> getLstProveedor() {
        return lstProveedor;
    }

    public void setLstProveedor(List<Proveedor> lstProveedor) {
        this.lstProveedor = lstProveedor;
    }
//
//    public List<CompraDetalle> getLstCompradeta() {
//        return lstCompradeta;
//    }
//
//    public void setLstCompradeta(List<CompraDetalle> lstCompradeta) {
//        this.lstCompradeta = lstCompradeta;
//    }


    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public BigDecimal getCantidadSolic() {
        return cantidadSolic;
    }

    public void setCantidadSolic(BigDecimal cantidadSolic) {
        this.cantidadSolic = cantidadSolic;
    }

    public BigDecimal getCantidadConfirmada() {
        return cantidadConfirmada;
    }

    public void setCantidadConfirmada(BigDecimal cantidadConfirmada) {
        this.cantidadConfirmada = cantidadConfirmada;
    }

    public Compra getCompraEnca() {
        return compraEnca;
    }

    public List<Compradetalle> getLstCompradeta() {
        return lstCompradeta;
    }
    

    public void setLstCompradeta(List<Compradetalle> lstCompradeta) {
        this.lstCompradeta = lstCompradeta;
    }

    public List<Vwproductos> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<Vwproductos> lstProducto) {
        this.lstProducto = lstProducto;
    }


    public void setCompraEnca(Compra compraEnca) {
        this.compraEnca = compraEnca;
    }
    //</editor-fold >
    
    public void mostrarDialogProd() {
    dialogProductos.setVisible(true);
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProducto').show();");
    }
        public void mostrarDialogProveedor() {
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProveedor').show();");
    }
         public void selectProducto() {
             if (tablaProd.getSelection() != null) {
                 Producto pro = (Producto) tablaProd.getRowData();
                 System.out.println("pro.." +pro);
             }
         }
       
       public void onRowSelect(SelectEvent event) {
        try {
            Vwproductos idP  =  ((Vwproductos) event.getObject());
            System.out.println("yd.,,, " + idP);
            producto = ((Vwproductos) event.getObject());
            
            System.out.println("producto,.. " +producto);
            System.out.println("u..");
            Producto pro = crud.buscarEntidad(Producto.class,
                    producto.getIdproducto());
            if (lstCompradeta == null ) {
                lstCompradeta = new ArrayList<>();
            }
            compraDeta = new Compradetalle();
            compraDeta.setIdproducto(pro);
            
            lstCompradeta.add(0, compraDeta);
        } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
      public void onRowSelectProveedor(SelectEvent event) {
       proveedor  =  (Proveedor) event.getObject();
       if (proveedor != null ) {
           nombreProveedor = proveedor.getProveedor();
       }
        
    }
    
}


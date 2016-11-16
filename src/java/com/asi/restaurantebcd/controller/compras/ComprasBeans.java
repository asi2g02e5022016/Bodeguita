/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.compras;

import com.asi.restaurantbcd.modelo.Compra;
import com.asi.restaurantbcd.modelo.CompraPK;
import com.asi.restaurantbcd.modelo.Compradetalle;
import com.asi.restaurantbcd.modelo.CompradetallePK;
import com.asi.restaurantbcd.modelo.Estado;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Proveedor;
import com.asi.restaurantbcd.modelo.Vwproductos;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasComprasLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasProveedoresLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.base.ProcesosInventariosLocal;
import com.asi.restaurantebcd.negocio.util.EstadoEnum;
import com.asi.restaurantebcd.negocio.util.Utilidades;
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
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
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

      //<editor-fold  defaultstate="collapsed" desc="Variables" >
    private Integer nodocu;
    private String observacion;
    private String estado;
    private String usr;
    private String sucursal;
    
    private String codigo;
    private String serie;
    private String nombreProveedor;
    private String descripcionProducto;
    private boolean mostrarCantConfirmada = false;
    
    private Double cantidadSolic;
    private BigDecimal cantidadConfirmada;
    private Proveedor proveedor;
    private Vwproductos producto;
    private boolean mostrarBtnGuardar = false;
    private boolean mostrarBtnActualizarExit = false;
    private Date fecha;
    private Date fechaIniMonitor = null;
    private Date fechaFinMonitor = null;
    private String mensaje;
    private Compra compraEnca;
    private List  <Compra > lstComprasMonitor;
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
    private BusquedasProveedoresLocal ejbBuqProv;
    @EJB
    private BusquedasProductosLocal ejbBusProd;
        @EJB
    private ProcesosInventariosLocal ejbProInv;
    @Inject
    private SessionUsr sesion;
   
    //</editor-fold >

      //<editor-fold  defaultstate="collapsed" desc="Metodos principales" >
   /**
    * 
    */
   public void nuevaCompra() {
       compraEnca = null;
       lstCompradeta.clear();
       lstProducto = null;
       lstProveedor = null;
       nodocu = null;
       estado = null;
       sucursal = null;
       nombreProveedor = null;
       fecha = null;
       observacion = null;
       usr = null;
       codigo = null;
       serie = null;
       mostrarBtnActualizarExit = false;
       mostrarBtnGuardar = true;
       
   }
   /**
    * 
    */
   public void guardarCompra() {
       
        try {
            if (proveedor == null) {
                alert("El proveedor obligatorio.", FacesMessage.SEVERITY_WARN);
                return;
            }   
            if (lstCompradeta == null || lstCompradeta.isEmpty()) {
                alert("El documento no tiene detalle.", FacesMessage.SEVERITY_FATAL);
                return;
            }

            Integer codigoCom =  ejbBusComp.obtenerCorreltivoCompra(
                    sesion.getSucursal().getIdsucursal(),
                    Compra.class, "idsucursal","idcompra");
                        
            compraEnca = new Compra();
            CompraPK idCompra = new CompraPK();
            idCompra.setIdcompra(codigoCom);
            idCompra.setIdsucursal(sesion.getSucursal().getIdsucursal());
            compraEnca.setCompraPK(idCompra);
                        if (codigo != null && !codigo.equals("")) {
            compraEnca.setCodigofactura(codigo);
            }
             if (serie != null && !serie.equals("")) {
            compraEnca.setCodigofactura(codigo);
            }
             int correl = 0;
            for (Compradetalle cmDet : lstCompradeta) {
                correl++;
                CompradetallePK idDePK  = new CompradetallePK();
                idDePK.setIdcompra(idCompra.getIdcompra());
                idDePK.setIdcompradetalle(correl);
                idDePK.setIdsucursal(idCompra.getIdsucursal());
                cmDet.setCompradetallePK(idDePK);
                cmDet.setCompra(compraEnca);
            }
            compraEnca.setCompradetalleList(lstCompradeta);
            compraEnca.setFechacompra(new Date());
            Estado est = crud.buscarEntidad(Estado.class, 
                    EstadoEnum.PENDIENTE_ALMACENAMIENTO.getInteger());
            compraEnca.setIdestado(est);
            compraEnca.setIdproveedor(proveedor);
            compraEnca.setSucursal(sesion.getSucursal());
            compraEnca.setIdusuario(sesion.getUsuario());
            compraEnca.setObservacion(observacion);
            crud.guardarEntidad(compraEnca);
            alert("El documento se guardo exitosamente", FacesMessage.SEVERITY_INFO);
            nodocu = compraEnca.getCompraPK().getIdsucursal();
           // usr = compraEnca.getIdusuario().getIdempleado().getNombre();
            estado = compraEnca.getIdestado().getEstado();
            fecha = compraEnca.getFechacompra();
            usr = compraEnca.getIdusuario().getIdusuario();
            sucursal =  compraEnca.getSucursal().getSucursal();
            mostrarBtnGuardar = false;
            mostrarBtnActualizarExit = true;
            
        } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName()).log(Level.SEVERE, null, ex);
            alert("Error: " +ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
     }
   /**
    * 
    */
      public void ActualizarExistenciaCompra() {
          if (compraEnca == null) { 
              alert("El documento de  compra es obligtorio.", FacesMessage.SEVERITY_WARN);
              return;
          }
          if (compraEnca.getCompradetalleList() == null 
                  || compraEnca.getCompradetalleList().isEmpty()) {
              alert("El documento no tiene detalle.", FacesMessage.SEVERITY_WARN);
              return;
          }
          
          try {    
          Estado estad = crud.buscarEntidad(Estado.class, EstadoEnum.TERMINADO.getInteger());
          if (estad == null) {
              alert("El estado terminado no existe.",
                      FacesMessage.SEVERITY_WARN);
              return;
          }
      
          for (Compradetalle compDet : lstCompradeta) {

                  ejbProInv.afectarExistencia(
                          compDet.getCantidadconfirmada(),
                          compDet.getIdproducto(),
                          sesion.getUsuario(),
                          sesion.getSucursal(),
                          compDet.getPrecio(),
                          false,
                          true);
          }
                if (codigo != null && !codigo.equals("")) {
            compraEnca.setCodigofactura(codigo);
            }
             if (serie != null && !serie.equals("")) {
            compraEnca.setCodigofactura(codigo);
            }
          estado = estad.getEstado();
          compraEnca.setIdestado(estad);
          crud.guardarEntidad(compraEnca);
          mostrarBtnActualizarExit  = false;
              alert("El documento se actualizo exitosamente.", FacesMessage.SEVERITY_INFO);
          } catch (Exception ex) {
                  Logger.getLogger(ComprasBeans.class.getName())
                          .log(Level.SEVERE, null, ex);
                  alert("Error: ", FacesMessage.SEVERITY_ERROR);
              }
          
          
      }
      
  /**
    * 
    */
   public void imprimitCompra() {
       try {
           if (compraEnca == null) {
               alert("Debe selecionar una compra", FacesMessage.SEVERITY_INFO);
               return;
           }
            Map param = new HashMap();
            param.put("CODSUC", String.valueOf(compraEnca.getCompraPK().getIdsucursal()));
            param.put("IDCOMPRA", String.valueOf(compraEnca.getCompraPK().getIdcompra()));
                    
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
                        RequestContext context = RequestContext.getCurrentInstance();
             context.execute(             "window.open('" + url
                   + "','Rpt','location=0,menubar=0,resizable=1,"
                   + "status=0,toolbar=0');");
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
   }
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
            if (nombreProveedor != null && !nombreProveedor.trim().equals("")) {
                filtro.put("nombre", nombreProveedor.trim());
                
            }
            lstProveedor = ejbBuqProv.buscarProveedors(filtro);
            System.out.println("proveedre.." +lstProveedor);
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
           // filtro.put("tipo", 1);
            
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
     
             //</editor-fold >HttpServletRequest
      
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
      
      //<editor-fold  defaultstate="collapsed" desc="Metodos" >
         
    

    public void mostrarDialogProd() {
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
            System.out.println("camtidad... " +cantidadSolic);
            producto = ((Vwproductos) event.getObject());
            if (cantidadSolic == null || cantidadSolic.toString().equals("0")){
                alert("La Cantidad Es obligatorio", FacesMessage.SEVERITY_WARN);
//                            RequestContext requestContext = RequestContext.getCurrentInstance();
//                requestContext.execute("PF('dialogoProducto').show();");
                return;
            }
            System.out.println("producto,.. " +producto);
            System.out.println("u..");
            Producto pro = crud.buscarEntidad(Producto.class,
                    producto.getIdproducto());
            if (lstCompradeta == null ) {
                lstCompradeta = new ArrayList<>();
            }
            compraDeta = new Compradetalle();
            compraDeta.setIdproducto(pro);
            compraDeta.setCantidadconfirmada(cantidadSolic);
            compraDeta.setCantidadsolicitada(cantidadSolic);
            compraDeta.setPrecio(producto.getPrecioventa());
            compraDeta.setMonto(cantidadSolic * compraDeta.getPrecio());
            Double totaliva = compraDeta.getMonto() * Double.valueOf("0.13");
            compraDeta.setIva(totaliva);
            compraDeta.setTotal(totaliva + compraDeta.getMonto());
            lstCompradeta.add(0, compraDeta);
            System.out.println("lstCompradeta.." +lstCompradeta);
            RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProducto').hide();");
        } catch (Exception ex) {
            ex.printStackTrace();
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
       
       RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProveedor').hide();");
        
    }

    public boolean isMostrarCantConfirmada() {
        return mostrarCantConfirmada;
    }

    public void setMostrarCantConfirmada(boolean mostrarCantConfirmada) {
        this.mostrarCantConfirmada = mostrarCantConfirmada;
    }
    
    
    
      public void onRowEdit(RowEditEvent event) {
        try {
            compraDeta =  (Compradetalle) event.getObject();
           // crud.guardarEntidad(compraDeta);
        } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }
     
          //</editor-fold >
      
      //<editor-fold  defaultstate="collapsed" desc="Monitor de compras">
      /**
       * 
       */
      public void mostrarDialogoMonCompras() {
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoCompras').show();");
      }
    /**
     * 
     */
    public void buscarCompras() {
        try {
            if (fechaIniMonitor != null
                    && fechaFinMonitor != null) {
                if (fechaIniMonitor.after(fechaFinMonitor)) {
                    alert("La fecha inicial tiene que ser menor que la final.",
                            FacesMessage.SEVERITY_WARN);
                    return;
                }
            }
            Utilidades util = new Utilidades();
            lstCompraMonitor = ejbProInv.buscarCompras(sesion.getSucursal(),
                    util.getFiltroDeFecha(fechaIniMonitor, 0) ,   util.getFiltroDeFecha(fechaFinMonitor, 1)  );
            System.out.println("lstCompraMonitor,,,.. " + lstCompraMonitor);
            if (lstCompraMonitor == null || lstCompraMonitor.isEmpty()) {
                alert("NO se encontraron resultados.", FacesMessage.SEVERITY_WARN);
            }
        } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    /**
     * 
     */
    public void limpiarMonitorCompras() {
    lstCompraMonitor = null;
    fechaIniMonitor = new Date();
    fechaFinMonitor = new Date();
    }
    
    public void onRowSelectCompra(SelectEvent event) {
        compraEnca = (Compra) event.getObject();
        if (compraEnca == null) {
            alert("No se a podido obtener el objeto compra.", FacesMessage.SEVERITY_ERROR);
            return;
        }
        nodocu = compraEnca.getCompraPK().getIdcompra();
        usr = compraEnca.getIdusuario().getIdusuario();
        serie =  compraEnca.getSeriefactura();
        codigo = compraEnca.getCodigofactura();
        usr =  compraEnca.getIdusuario().getIdusuario();
        sucursal =  compraEnca.getSucursal().getSucursal();
        nombreProveedor = compraEnca.getIdproveedor().getProveedor();
        estado = compraEnca.getIdestado().getEstado();
        fecha = compraEnca.getFechacompra();
        observacion = compraEnca.getObservacion();
        lstCompradeta = new ArrayList<>();
        lstCompradeta.addAll(compraEnca.getCompradetalleList());
        
        
    }
    
    
            //</editor-fold >
            
      //<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
 public Integer getNodocu() {
        return nodocu;
    }

    public Date getFechaIniMonitor() {
        return fechaIniMonitor;
    }

    public boolean isMostrarBtnGuardar() {
        return mostrarBtnGuardar;
    }

    public void setMostrarBtnGuardar(boolean mostrarBtnGuardar) {
        this.mostrarBtnGuardar = mostrarBtnGuardar;
    }

    public boolean isMostrarBtnActualizarExit() {
        return mostrarBtnActualizarExit;
    }

    public void setMostrarBtnActualizarExit(boolean mostrarBtnActualizarExit) {
        this.mostrarBtnActualizarExit = mostrarBtnActualizarExit;
    }

    public void setFechaIniMonitor(Date fechaIniMonitor) {
        this.fechaIniMonitor = fechaIniMonitor;
    }

    public Date getFechaFinMonitor() {
        return fechaFinMonitor;
    }

    public void setFechaFinMonitor(Date fechaFinMonitor) {
        this.fechaFinMonitor = fechaFinMonitor;
    }


    public List<Compra> getLstComprasMonitor() {
        return lstComprasMonitor;
    }

    public void setLstComprasMonitor(List<Compra> lstComprasMonitor) {
        this.lstComprasMonitor = lstComprasMonitor;
    }

    public DataTable getTablaProd() {
        return tablaProd;
    }

    public void setTablaProd(DataTable tablaProd) {
        this.tablaProd = tablaProd;
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

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
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

    public Double getCantidadSolic() {
        return cantidadSolic;
    }

    public void setCantidadSolic(Double cantidadSolic) {
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
    

} 


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Compradetalle;
import com.asi.restaurantbcd.modelo.Estado;
import com.asi.restaurantbcd.modelo.Notapedido;
import com.asi.restaurantbcd.modelo.Notapedidodetalle;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Proveedor;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Vwproductos;
import com.asi.restaurantebcd.controller.compras.ComprasBeans;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursalLocal;
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
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author siman
 */
@ManagedBean(name = "notaPedido")
@ViewScoped
public class NotaPedidoBean implements Serializable{
    
    private boolean mostrarBtnGuardar = false;
    private boolean mostrarCantConfirmada = false;
    
    private Integer nodocu;
    private String observacion;
    private String estado;
    private String usr;
    private String sucursalDestino;
    private Date fecha;
    private String sucursalOrigen;
    private List<Notapedidodetalle> lstPeddeta = new ArrayList<Notapedidodetalle>();
    private List<Sucursal> lstSucursalOrigen = new ArrayList<Sucursal>();
    private List<Vwproductos> lstProducto = new ArrayList<Vwproductos>();
    private List<Notapedido> lstPedidoMonitor = new ArrayList<Notapedido>();
    
    private String sucursalFilter;
    private String descripcionProducto;
    private Date fechaIniMonitor = null;
    private Date fechaFinMonitor = null;
    private Double cantidadSolic;
    private Vwproductos producto;
    private Notapedidodetalle notaDeta;
    private Notapedido notaEnca;
    
    @EJB
    private BusquedasSucursalLocal ejbSucursal;
    
    @EJB
    private BusquedasProductosLocal ejbBusProd;
    
    @EJB
    private CrudBDCLocal crud;
    
    @Inject
    private SessionUsr session;
    
    public void nuevoPedido(){
    Estado estadoPed=null;
        try {
           estadoPed = crud.buscarEntidad(Estado.class, EstadoEnum.GENERADO);
        }catch(Exception e){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e, null);
        }
        estado=estadoPed.getEstado();
        notaEnca=new Notapedido();
        notaEnca.setSucursal(session.getSucursal());
        notaEnca.setIdusuariod(session.getUsuario());
        notaEnca.setFechaingreso(new Date());
        notaEnca.setIdestado(estadoPed);
        
    nodocu = null;
    observacion = null;
    
    usr = session.getUsuario().getIdusuario();
    sucursalDestino=session.getSucursal().getSucursal();
    
    
    fecha = new Date();
    sucursalOrigen = null;
    lstPeddeta.clear();
    sucursalFilter = null;
    descripcionProducto = null;
    fechaIniMonitor = null;
    fechaFinMonitor = null;
    cantidadSolic = null;
    
    }
    
    public void guardarPedido(){};
    
    public void enviarNotaPedido() {};
    
    public void imprimirReporteCompra(){};
    
    public void mostrarDialogoMonNotPedido(){
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoNotaPedidos').show();");
    };
    
    public void mostrarDialogOrigen(){
     RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoSucursal').show();");    
    };
    
    public void mostrarDialogProd() {
         RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProducto').show();");  
    };
    
    public void limpiarSucursalOrigen() {
         this.setSucursalOrigen(null);
         notaEnca.setIdSucursalOrigen(null);
    };
    
    public void buscarSucursal() {
       try {
            Map filtro = new HashMap();
            if (sucursalFilter != null && !sucursalFilter.equals("")) {
                filtro.put("sucursal", sucursalFilter.trim()); 
            }
            lstSucursalOrigen = ejbSucursal.buscarSucursal(filtro);
            System.out.println("sucursal.." +lstSucursalOrigen);
            if (lstSucursalOrigen == null || lstSucursalOrigen.isEmpty()) {
               // alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
         } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName()).log(
                    Level.SEVERE, null, ex);
            //alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    }; 
    
    public void limpiarProducto() {};
    
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
    };
    
    public void buscarPedidos() {};
    
    public void onRowEdit(RowEditEvent event) {
        try {
            //compraDeta =  (Compradetalle) event.getObject();
           // crud.guardarEntidad(compraDeta);
        } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName())
                    .log(Level.SEVERE, null, ex);
           // alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }
    
      public void onRowSelectSucursal(SelectEvent event) {
          this.sucursalOrigen = ((Sucursal) event.getObject()).getSucursal();
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
            if (lstPeddeta == null ) {
                lstPeddeta = new ArrayList<>();
            }
            notaDeta = new Notapedidodetalle();
            notaDeta.setIdproducto(pro);
            notaDeta.setCantidadsolicitada(cantidadSolic.intValue());
            notaDeta.setPrecio(0);
            lstPeddeta.add(0, notaDeta);
            System.out.println("lstPeddeta.." +lstPeddeta);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogoProducto').hide();");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ComprasBeans.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
      
    public void onRowSelectPedido(SelectEvent event) {

        
    }

    public boolean isMostrarCantConfirmada() {
        return mostrarCantConfirmada;
    }

    public void setMostrarCantConfirmada(boolean mostrarCantConfirmada) {
        this.mostrarCantConfirmada = mostrarCantConfirmada;
    }
       

    /**
     * @return the mostrarBtnGuardar
     */
    public boolean isMostrarBtnGuardar() {
        return mostrarBtnGuardar;
    }

    /**
     * @param mostrarBtnGuardar the mostrarBtnGuardar to set
     */
    public void setMostrarBtnGuardar(boolean mostrarBtnGuardar) {
        this.mostrarBtnGuardar = mostrarBtnGuardar;
    }

    /**
     * @return the nodocu
     */
    public Integer getNodocu() {
        return nodocu;
    }

    /**
     * @param nodocu the nodocu to set
     */
    public void setNodocu(Integer nodocu) {
        this.nodocu = nodocu;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the usr
     */
    public String getUsr() {
        return usr;
    }

    /**
     * @param usr the usr to set
     */
    public void setUsr(String usr) {
        this.usr = usr;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the sucursalDestino
     */
    public String getSucursalDestino() {
        return sucursalDestino;
    }

    /**
     * @param sucursalDestino the sucursalDestino to set
     */
    public void setSucursalDestino(String sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }

    /**
     * @return the sucursalOrigen
     */
    public String getSucursalOrigen() {
        return sucursalOrigen;
    }

    /**
     * @param sucursalOrigen the sucursalOrigen to set
     */
    public void setSucursalOrigen(String sucursalOrigen) {
        this.sucursalOrigen = sucursalOrigen;
    }

    /**
     * @return the lstPeddeta
     */
    public List<Notapedidodetalle> getLstPeddeta() {
        return lstPeddeta;
    }

    /**
     * @param lstPeddeta the lstPeddeta to set
     */
    public void setLstPeddeta(List<Notapedidodetalle> lstPeddeta) {
        this.lstPeddeta = lstPeddeta;
    }

    /**
     * @return the sucursalFilter
     */
    public String getSucursalFilter() {
        return sucursalFilter;
    }

    /**
     * @param sucursalFilter the sucursalFilter to set
     */
    public void setSucursalFilter(String sucursalFilter) {
        this.sucursalFilter = sucursalFilter;
    }

    /**
     * @return the lstSucursalOrigen
     */
    public List<Sucursal> getLstSucursalOrigen() {
        return lstSucursalOrigen;
    }

    /**
     * @param lstSucursalOrigen the lstSucursalOrigen to set
     */
    public void setLstSucursalOrigen(List<Sucursal> lstSucursalOrigen) {
        this.lstSucursalOrigen = lstSucursalOrigen;
    }

    /**
     * @return the lstProducto
     */
    public List<Vwproductos> getLstProducto() {
        return lstProducto;
    }

    /**
     * @param lstProducto the lstProducto to set
     */
    public void setLstProducto(List<Vwproductos> lstProducto) {
        this.lstProducto = lstProducto;
    }

    /**
     * @return the descripcionProducto
     */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     * @param descripcionProducto the descripcionProducto to set
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    /**
     * @return the fechaIniMonitor
     */
    public Date getFechaIniMonitor() {
        return fechaIniMonitor;
    }

    /**
     * @param fechaIniMonitor the fechaIniMonitor to set
     */
    public void setFechaIniMonitor(Date fechaIniMonitor) {
        this.fechaIniMonitor = fechaIniMonitor;
    }

    /**
     * @return the fechaFinMonitor
     */
    public Date getFechaFinMonitor() {
        return fechaFinMonitor;
    }

    /**
     * @param fechaFinMonitor the fechaFinMonitor to set
     */
    public void setFechaFinMonitor(Date fechaFinMonitor) {
        this.fechaFinMonitor = fechaFinMonitor;
    }

    /**
     * @return the lstPedidoMonitor
     */
    public List<Notapedido> getLstPedidoMonitor() {
        return lstPedidoMonitor;
    }

    /**
     * @param lstPedidoMonitor the lstPedidoMonitor to set
     */
    public void setLstPedidoMonitor(List<Notapedido> lstPedidoMonitor) {
        this.lstPedidoMonitor = lstPedidoMonitor;
    }

    /**
     * @return the cantidadSolic
     */
    public Double getCantidadSolic() {
        return cantidadSolic;
    }

    /**
     * @param cantidadSolic the cantidadSolic to set
     */
    public void setCantidadSolic(Double cantidadSolic) {
        this.cantidadSolic = cantidadSolic;
    }
    
    
    public void alert(String mensaje, FacesMessage.Severity faces) {
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje);
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    /**
     * @return the notaEnca
     */
    public Notapedido getNotaEnca() {
        return notaEnca;
    }

    /**
     * @param notaEnca the notaEnca to set
     */
    public void setNotaEnca(Notapedido notaEnca) {
        this.notaEnca = notaEnca;
    }
    
}

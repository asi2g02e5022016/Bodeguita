/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Compra;
import com.asi.restaurantbcd.modelo.Compradetalle;
import com.asi.restaurantbcd.modelo.Estado;
import com.asi.restaurantbcd.modelo.Notapedido;
import com.asi.restaurantbcd.modelo.NotapedidoPK;
import com.asi.restaurantbcd.modelo.Notapedidodetalle;
import com.asi.restaurantbcd.modelo.NotapedidodetallePK;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Proveedor;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Vwexistencias;
import com.asi.restaurantbcd.modelo.Vwproductos;
import com.asi.restaurantebcd.controller.compras.ComprasBeans;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedaNotaPedido;
import com.asi.restaurantebcd.negocio.base.BusquedaNotaPedidoLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasProductosLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursalLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.base.NotaPedidoEJBLocal;
import com.asi.restaurantebcd.negocio.base.ProcesosInventariosLocal;
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
    private boolean mostrarCantRecibida = false;
    
    private Integer nodocu;
    private String observacion;
    private String estado;
    private String usr;
    private String sucursalDestino;
    private Date fecha;
    private String sucursalOrigenNombre;
    private Sucursal sucursalOrigen;
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
    private boolean inactivarSelectorSucursal=true;
    
    private boolean mostrarBtnCancelar = false;
    
    private boolean mostrarBtnEnviar=false;
    private boolean mostrarBtnRecibir=false;
    
    @EJB
    private NotaPedidoEJBLocal ejbNota;
    
    @EJB
    private BusquedasProductosLocal ejbBusProd;
    
    @EJB
    private CrudBDCLocal crud;
    
    @EJB
    private BusquedaNotaPedidoLocal ejbPedido;
    @PersistenceContext(unitName = "RestaurantBDC-WebPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    @Inject
    private SessionUsr session;
    
    @EJB
    ProcesosInventariosLocal ejbInv;
    
    
    public void limpiarPantalla(){
          notaEnca = null;
          nodocu = null;
          observacion = null;
          usr=null;
          sucursalDestino=null;
          sucursalOrigen=null;
          sucursalOrigenNombre=null;
           sucursalOrigenNombre = null;
            lstPeddeta = null;
            sucursalFilter = null;
            descripcionProducto = null;
            fechaIniMonitor = null;
            fechaFinMonitor = null;
            cantidadSolic = null;
            mostrarBtnGuardar = false;
             mostrarBtnCancelar = false;
             inactivarSelectorSucursal=true;
             setMostrarBtnEnviar(false);
    }

    public void recibirPedido(){
      try {
            this.notaEnca = crud.buscarEntidad(Notapedido.class, notaEnca.getNotapedidoPK());
            for(Notapedidodetalle pdet:notaEnca.getNotapedidodetalleList()){
               if(pdet.getCantidadconfirmada()!=pdet.getCantidadrecibida() && (this.observacion==null||this.observacion.trim().equals(""))){
                   alert("La observación es obligatoria cuando no se recibe completo",FacesMessage.SEVERITY_WARN);
                   return;
               }
            
            }
            
            for(Notapedidodetalle pdet:notaEnca.getNotapedidodetalleList()){
                ejbInv.afectarExistencia((new Integer(pdet.getCantidadrecibida())).doubleValue(), pdet.getIdproducto() , session.getUsuario(), notaEnca.getSucursal(), 0D,false,false);
                ejbInv.afectarTransito((new Integer(pdet.getCantidadconfirmada())).doubleValue(), pdet.getIdproducto() , session.getUsuario(), notaEnca.getSucursal(), (new Float(pdet.getCosto()).doubleValue()),true,false);
                
                // si hay inconsistencia entre lo confirmado y recibido
                if(pdet.getCantidadconfirmada()!=pdet.getCantidadrecibida()){
                    
                    //si la cantidad recibida es mayor que la confirmada, se resta sino se sumara
                    boolean signo=pdet.getCantidadrecibida()>pdet.getCantidadconfirmada();
                    
                    //usamos el valor absoluto
                    int cantidad = Math.abs(pdet.getCantidadrecibida()-pdet.getCantidadconfirmada());
                    
                    ejbInv.afectarExistencia((new Integer(cantidad)).doubleValue(), pdet.getIdproducto() , session.getUsuario(), notaEnca.getIdSucursalOrigen(), 0D,signo,false);
                
                }
                
            
            }
             Estado estado = new Estado();
            estado.setIdestado(EstadoEnum.TERMINADO.getInteger());
            notaEnca.setIdestado(estado);
            notaEnca.setObservacion(observacion);
            crud.guardarEntidad(notaEnca);
            
            limpiarPantalla();
            alert("Pedido recibido", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(NotaPedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void enviarPedido(){
            
        try {
            this.notaEnca = crud.buscarEntidad(Notapedido.class, notaEnca.getNotapedidoPK());
            boolean enviar = false;
            for(Notapedidodetalle pdet:notaEnca.getNotapedidodetalleList()){
                if(pdet.getCantidadconfirmada()>0){
                 enviar=true;
                }
            }
            
            if(!enviar){
              alert("No se puede enviar el pedido sin confirmar ninguna unidad",FacesMessage.SEVERITY_WARN);
                   return;
            }
            
            for(Notapedidodetalle pdet:notaEnca.getNotapedidodetalleList()){
                ejbInv.afectarExistencia((new Integer(pdet.getCantidadconfirmada())).doubleValue(), pdet.getIdproducto() , session.getUsuario(), notaEnca.getIdSucursalOrigen(), 0D,true,false);
                ejbInv.afectarTransito((new Integer(pdet.getCantidadconfirmada())).doubleValue(), pdet.getIdproducto() , session.getUsuario(), notaEnca.getSucursal(), (new Float(pdet.getCosto()).doubleValue()),false,true);
                ejbInv.afectarReservado((new Integer(pdet.getCantidadsolicitada())).doubleValue(), pdet.getIdproducto() , session.getUsuario(), notaEnca.getIdSucursalOrigen(), 0D,true,false);
            
            }
             Estado estado = new Estado();
            estado.setIdestado(EstadoEnum.TRANSITO.getInteger());
            notaEnca.setIdestado(estado);
            notaEnca.setObservacion(observacion);
            crud.guardarEntidad(notaEnca);
            
            limpiarPantalla();
            alert("Pedido despachado", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(NotaPedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    public void nuevoPedido(){
    Estado estadoPed=null;
        try {
           estadoPed = crud.buscarEntidad(Estado.class, EstadoEnum.GENERADO.getInteger());
        }catch(Exception e){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e, null);
        }
        estado=estadoPed.getEstado();
        notaEnca=new Notapedido();
        notaEnca.setSucursal(session.getSucursal());
        notaEnca.setIdusuarios(session.getUsuario().getIdusuario());
        notaEnca.setFechageneracion(new Date());
        notaEnca.setIdestado(estadoPed);
        
    nodocu = null;
    observacion = null;
    inactivarSelectorSucursal = false;
    usr = session.getUsuario().getIdusuario();
    sucursalDestino=session.getSucursal().getSucursal();
    
    
    fecha = new Date();
    sucursalOrigenNombre = null;
    lstPeddeta.clear();
    sucursalFilter = null;
    descripcionProducto = null;
    fechaIniMonitor = null;
    fechaFinMonitor = null;
    cantidadSolic = null;
    mostrarBtnGuardar = true;
     mostrarBtnCancelar = false;
    
    }
    
    public void guardarPedido(){
          Integer codigoCom=0;
        try {
            codigoCom =  ejbPedido.obtenerCorreltivoPedido(session.getSucursal().getIdsucursal());
            this.nodocu = codigoCom;
        } catch (Exception ex) {
            Logger.getLogger(NotaPedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        NotapedidoPK pk = new NotapedidoPK();
        pk.setIdnotapedido(codigoCom);
        pk.setIdsucursal(session.getSucursal().getIdsucursal());
        
        notaEnca.setNotapedidoPK(pk);
        
        int correl = 0;
        for (Notapedidodetalle det: lstPeddeta){
           correl++;
           NotapedidodetallePK pkdet = new NotapedidodetallePK();
           pkdet.setIdnotapedido(this.nodocu);
           pkdet.setIdnotapeddet(correl);
           pkdet.setIdsucursal(session.getSucursal().getIdsucursal());
           det.setNotapedidodetallePK(pkdet);
        }
        
        notaEnca.setNotapedidodetalleList(lstPeddeta);
        
        try {
            notaEnca.setObservacion(observacion);
            crud.guardarEntidad(notaEnca);
            for(Notapedidodetalle pdet:lstPeddeta){
                System.out.println("Reservando:"  + pdet.getIdproducto().getProducto());
                ejbInv.afectarReservado((new Integer(pdet.getCantidadsolicitada())).doubleValue(), pdet.getIdproducto() , session.getUsuario(), notaEnca.getIdSucursalOrigen(), 0D,false,false);
            }
            limpiarPantalla();
            alert("Pedido guardado", FacesMessage.SEVERITY_INFO);
            
        } catch (Exception ex) {
            Logger.getLogger(NotaPedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
    public void cancelarPedido() {
        try {
             if((this.observacion==null||this.observacion.trim().equals(""))){
                   alert("La observación es obligatoria cuando se anula un pedido",FacesMessage.SEVERITY_WARN);
                   return;
               }
             
            Estado estado = new Estado();
            estado.setIdestado(EstadoEnum.ANULADO.getInteger());
            notaEnca.setIdestado(estado);
            notaEnca.setObservacion(observacion);
            crud.guardarEntidad(notaEnca);
            
            for(Notapedidodetalle pdet:this.lstPeddeta){
                ejbInv.afectarReservado((new Integer(pdet.getCantidadsolicitada())).doubleValue(), pdet.getIdproducto() , session.getUsuario(), notaEnca.getIdSucursalOrigen(), 0D,true,false);
            }
            alert("Pedido cancelado", FacesMessage.SEVERITY_INFO);
            limpiarPantalla();
        } catch (Exception ex) {
            Logger.getLogger(NotaPedidoBean.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    };
    
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
         this.setSucursalOrigenNombre(null);
         notaEnca.setIdSucursalOrigen(null);
    };
    
    public void buscarSucursal() {
       try {
            Map filtro = new HashMap();
            
               filtro.put("sucursal", session.getSucursal().getSucursal()); 
          
            lstSucursalOrigen = ejbNota.buscarSucursalOrigen(filtro);
            System.out.println("sucursal.." +lstSucursalOrigen);
            if (lstSucursalOrigen == null || lstSucursalOrigen.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
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
            //filtro.put("tipo", 2);  Solo materia Prima
            
            lstProducto = ejbBusProd.buscarProducto(filtro);
            System.out.println("lstProduto.." +lstProducto);
            if (lstProducto == null || lstProducto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
         } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    };
    
    public void buscarPedidos() {
    
        try {
            if (fechaIniMonitor != null
                    && fechaFinMonitor != null) {
                if (fechaIniMonitor.after(fechaFinMonitor)) {
                    alert("La fecha inicial tiene que ser menor que la final.",
                            FacesMessage.SEVERITY_WARN);
                    return;
                }
            }
            
            Map filterMap;
            filterMap = new HashMap<String,Object>();
            
            filterMap.put("sucursal", session.getSucursal());
            lstPedidoMonitor = ejbNota.buscarNotasPedido(filterMap);
            if (lstPedidoMonitor == null || lstPedidoMonitor.isEmpty()) {
                alert("NO se encontraron resultados.", FacesMessage.SEVERITY_WARN);
            }
        } catch (Exception ex) {
            Logger.getLogger(NotaPedidoBean.class.getName()).log(Level.SEVERE, null, ex);
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    };
    
    public void onRowEdit(RowEditEvent event) {
        try {
           notaDeta =  (Notapedidodetalle) event.getObject();
           if(!mostrarBtnGuardar)
           crud.guardarEntidad(notaDeta);
           
        } catch (Exception ex) {
            Logger.getLogger(ComprasBeans.class.getName())
                    .log(Level.SEVERE, null, ex);
           // alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }
    
      public void onRowSelectSucursal(SelectEvent event) {
          this.sucursalOrigenNombre = ((Sucursal) event.getObject()).getSucursal();
          this.notaEnca.setIdSucursalOrigen((Sucursal) event.getObject());
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
            Producto pro = crud.buscarEntidad(Producto.class,
                    producto.getIdproducto());
            if (lstPeddeta == null ) {
                lstPeddeta = new ArrayList<>();
            }
            notaDeta = new Notapedidodetalle();
            notaDeta.setIdproducto(pro);
            notaDeta.setCantidadsolicitada(cantidadSolic.intValue());
            notaDeta.setCantidadconfirmada(0);
            notaDeta.setNotapedido(notaEnca);
            notaDeta.setCosto(Double.valueOf(idP.getPreciocompra()).floatValue());
            notaDeta.setLineaNueva(true);
            lstPeddeta.add(0, notaDeta);
            System.out.println("Preciocompra: " + idP.getPreciocompra());
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
     notaEnca = (Notapedido) event.getObject();
        if (notaEnca == null) {
            alert("No se a podido obtener el objeto compra.", FacesMessage.SEVERITY_ERROR);
            return;
        }
        System.out.println("notaEnca.getSucursal().getIdsucursal() " + notaEnca.getSucursal().getIdsucursal());
        System.out.println("session.getSucursal().getIdsucursal() " + session.getSucursal().getIdsucursal());
        System.out.println("notaEnca.getIdestado().getIdestado() " + notaEnca.getIdestado().getIdestado());
        System.out.println("EstadoEnum.GENERADO.getInteger() " + EstadoEnum.GENERADO.getInteger());
        
        
        
        if(notaEnca.getIdestado().getIdestado().equals(EstadoEnum.GENERADO.getInteger())){
            mostrarBtnCancelar = true; System.out.println("true"); } else {mostrarBtnCancelar = false; System.out.println("false");}
       
        inactivarSelectorSucursal=true;
                
         if(notaEnca.getIdSucursalOrigen().getIdsucursal().equals(session.getSucursal().getIdsucursal()) 
               && notaEnca.getIdestado().getIdestado().equals(EstadoEnum.GENERADO.getInteger())){
            setMostrarBtnEnviar(true);mostrarCantConfirmada = true; 
            System.out.println("No se permitira enviar cantidades mayores a la existencia actual"); 
            alert("No se permitira enviar cantidades mayores a la existencia actual",FacesMessage.SEVERITY_INFO);
         } else {
              setMostrarBtnEnviar(false); 
              mostrarCantConfirmada = false; 
              System.out.println("false");
         }
        
          if(notaEnca.getSucursal().getIdsucursal().equals(session.getSucursal().getIdsucursal()) 
               && notaEnca.getIdestado().getIdestado().equals(EstadoEnum.TRANSITO.getInteger())){
            setMostrarBtnRecibir(true);setMostrarCantRecibida(true); System.out.println("true"); } else {setMostrarBtnRecibir(false); setMostrarCantRecibida(false); System.out.println("false");}
          
        nodocu = notaEnca.getNotapedidoPK().getIdnotapedido();
     estado = notaEnca.getIdestado().getEstado();
    usr = notaEnca.getIdusuarios();
     sucursalDestino = notaEnca.getSucursal().getSucursal();
      fecha = notaEnca.getFechageneracion();
     sucursalOrigenNombre = notaEnca.getIdSucursalOrigen().getSucursal();
      sucursalOrigen = notaEnca.getIdSucursalOrigen();
    
    
    List<Notapedidodetalle> lstPeddNv = new ArrayList<Notapedidodetalle>();
     
    for(Notapedidodetalle pdet:notaEnca.getNotapedidodetalleList()){
       Vwexistencias pex=null;
         try{ 
      if(mostrarCantConfirmada){
       pex = (Vwexistencias) em.createQuery("select p from Vwexistencias p where p.idproducto = :id and p.idsucursal = :idsuc")
                        .setParameter("id", pdet.getIdproducto().getIdproducto())
                         .setParameter("idsuc", notaEnca.getIdSucursalOrigen().getIdsucursal())
                        .getSingleResult();}}catch(Exception ex){ex.printStackTrace();}
         
    if(pex==null){
          pdet.setExistencia(0);
     }else{
          pdet.setExistencia(pex.getValor());
    }
      lstPeddNv.add(pdet);
    }

    lstPeddeta = lstPeddNv; 
    observacion = notaEnca.getObservacion();
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
     * @return the sucursalOrigenNombre
     */
    public String getSucursalOrigenNombre() {
        return sucursalOrigenNombre;
    }

    /**
     * @param sucursalOrigenNombre the sucursalOrigenNombre to set
     */
    public void setSucursalOrigenNombre(String sucursalOrigenNombre) {
        this.sucursalOrigenNombre = sucursalOrigenNombre;
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

    /**
     * @return the sucursalOrigen
     */
    public Sucursal getSucursalOrigen() {
        return sucursalOrigen;
    }

    /**
     * @param sucursalOrigen the sucursalOrigen to set
     */
    public void setSucursalOrigen(Sucursal sucursalOrigen) {
        this.sucursalOrigen = sucursalOrigen;
    }

    /**
     * @return the mostrarBtnCancelar
     */
    public boolean isMostrarBtnCancelar() {
        return mostrarBtnCancelar;
    }

    /**
     * @param mostrarBtnCancelar the mostrarBtnCancelar to set
     */
    public void setMostrarBtnCancelar(boolean mostrarBtnCancelar) {
        this.mostrarBtnCancelar = mostrarBtnCancelar;
    }

    /**
     * @return the inactivarSelectorSucursal
     */
    public boolean isInactivarSelectorSucursal() {
        return inactivarSelectorSucursal;
    }

    /**
     * @param inactivarSelectorSucursal the inactivarSelectorSucursal to set
     */
    public void setInactivarSelectorSucursal(boolean inactivarSelectorSucursal) {
        this.inactivarSelectorSucursal = inactivarSelectorSucursal;
    }

    /**
     * @return the mostrarBtnEnviar
     */
    public boolean isMostrarBtnEnviar() {
        return mostrarBtnEnviar;
    }

    /**
     * @param mostrarBtnEnviar the mostrarBtnEnviar to set
     */
    public void setMostrarBtnEnviar(boolean mostrarBtnEnviar) {
        this.mostrarBtnEnviar = mostrarBtnEnviar;
    }

    /**
     * @return the mostrarBtnRecibir
     */
    public boolean isMostrarBtnRecibir() {
        return mostrarBtnRecibir;
    }

    /**
     * @param mostrarBtnRecibir the mostrarBtnRecibir to set
     */
    public void setMostrarBtnRecibir(boolean mostrarBtnRecibir) {
        this.mostrarBtnRecibir = mostrarBtnRecibir;
    }

    /**
     * @return the mostrarCantRecibida
     */
    public boolean isMostrarCantRecibida() {
        return mostrarCantRecibida;
    }

    /**
     * @param mostrarCantRecibida the mostrarCantRecibida to set
     */
    public void setMostrarCantRecibida(boolean mostrarCantRecibida) {
        this.mostrarCantRecibida = mostrarCantRecibida;
    }
    
}

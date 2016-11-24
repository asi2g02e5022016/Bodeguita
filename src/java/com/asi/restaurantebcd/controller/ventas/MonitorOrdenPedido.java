/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.ventas;

import com.asi.restaurantbcd.modelo.Caja;
import com.asi.restaurantbcd.modelo.Configuracion;
import com.asi.restaurantbcd.modelo.Numerofiscal;
import com.asi.restaurantbcd.modelo.Ordenpedido;
import com.asi.restaurantbcd.modelo.Ordenpedidodetalle;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedaPedido;
import com.asi.restaurantebcd.negocio.base.BusquedaPedidoLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursalLocal;
import com.asi.restaurantebcd.negocio.base.ConsumerWSLocal;
import com.asi.restaurantebcd.negocio.base.CrearFacturaEJBLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.EstadoEnum;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author JAEM
 */
@ManagedBean(name = "monitorOrdenPedido")
@ViewScoped

public class MonitorOrdenPedido implements Serializable {

    /**
     * @return the lstCaja
     */
    public List<Caja> getLstCaja() {
        return lstCaja;
    }

    /**
     * @param lstCaja the lstCaja to set
     */
    public void setLstCaja(List<Caja> lstCaja) {
        this.lstCaja = lstCaja;
    }

    /**
     * @return the idCaja
     */
    public Integer getIdCaja() {
        return idCaja;
    }

    /**
     * @param idCaja the idCaja to set
     */
    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    /**
     * @return the pedidoActual
     */
    public Ordenpedido getPedidoActual() {
        return pedidoActual;
    }

    /**
     * @param pedidoActual the pedidoActual to set
     */
    public void setPedidoActual(Ordenpedido pedidoActual) {
        this.pedidoActual = pedidoActual;
    }

    /**
     * @return the lstNumeroFiscal
     */
    public List<Numerofiscal> getLstNumeroFiscal() {
        return lstNumeroFiscal;
    }

    /**
     * @param lstNumeroFiscal the lstNumeroFiscal to set
     */
    public void setLstNumeroFiscal(List<Numerofiscal> lstNumeroFiscal) {
        this.lstNumeroFiscal = lstNumeroFiscal;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the numeroFactura
     */
    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * @param numeroFactura the numeroFactura to set
     */
    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    @EJB
    private ConsumerWSLocal consumerWS;
    
    @EJB 
    private CrearFacturaEJBLocal ejbFactura;

    public MonitorOrdenPedido() {
    }
//<editor-fold  defaultstate="collapsed" desc="LocalVariables" >
    //parametros de busqueda
    private Date fechaIni;
    private Date fechaFin;
    private SessionUsr sesion;
    private Integer idOrdenPedido;
    private Integer idSucursal;
    private Integer idCliente;
    private Integer idEstado;
    private Date fechaPedido;
    private Integer mesa;
    private String idUsuario;
    private String sucursal;
    private String cliente;
    private String estado;
    private String tipoPedido;
    private Integer numeroFactura;
    private String serie;
    private Ordenpedido pedidoActual;
    private Integer idCaja;
    

    private List<Ordenpedido> lstOrdenPedido;
    private List<Ordenpedidodetalle> lstOrdenPedidoDet;
    private List<Sucursal> lstSucursal;
    private List<Numerofiscal> lstNumeroFiscal;
    private List<Caja> lstCaja;

    private DataTable dtOrdenPedido = new DataTable();

    @EJB // Contine metodos de guardar,eliminar,buscar
    private CrudBDCLocal crud;

    @EJB
    private BusquedaPedidoLocal ejbBuscarPedido;
    @EJB
    private BusquedasSucursalLocal ejbBuscarSucursal;

    private Ordenpedido constructorOrdenPedido;
    private Ordenpedidodetalle constructorOrdenPedDet;
    
    private boolean activarEnvioPOS = false;

    //</editor-fold>
//<editor-fold  defaultstate="collapsed" desc="Get & Set" >
    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BusquedaPedidoLocal getEjbBuscarPedido() {
        return ejbBuscarPedido;
    }

    public void setEjbBuscarPedido(BusquedaPedidoLocal ejbBuscarPedido) {
        this.ejbBuscarPedido = ejbBuscarPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getIdOrdenPedido() {
        return idOrdenPedido;
    }

    public void setIdOrdenPedido(Integer idOrdenPedido) {
        this.idOrdenPedido = idOrdenPedido;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Ordenpedido> getLstOrdenPedido() {
        return lstOrdenPedido;
    }

    public void setLstOrdenPedido(List<Ordenpedido> lstOrdenPedido) {
        this.lstOrdenPedido = lstOrdenPedido;
    }

    public List<Ordenpedidodetalle> getLstOrdenPedidoDet() {
        return lstOrdenPedidoDet;
    }

    public void setLstOrdenPedidoDet(List<Ordenpedidodetalle> lstOrdenPedidoDet) {
        this.lstOrdenPedidoDet = lstOrdenPedidoDet;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public DataTable getDtOrdenPedido() {
        return dtOrdenPedido;
    }

    public void setDtOrdenPedido(DataTable dtOrdenPedido) {
        this.dtOrdenPedido = dtOrdenPedido;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedasSucursalLocal getEjbBuscarSucursal() {
        return ejbBuscarSucursal;
    }

    public void setEjbBuscarSucursal(BusquedasSucursalLocal ejbBuscarSucursal) {
        this.ejbBuscarSucursal = ejbBuscarSucursal;
    }

    public Ordenpedido getConstructorOrdenPedido() {
        return constructorOrdenPedido;
    }

    public void setConstructorOrdenPedido(Ordenpedido constructorOrdenPedido) {
        this.constructorOrdenPedido = constructorOrdenPedido;
    }

    public Ordenpedidodetalle getConstructorOrdenPedDet() {
        return constructorOrdenPedDet;
    }

    public void setConstructorOrdenPedDet(Ordenpedidodetalle constructorOrdenPedDet) {
        this.constructorOrdenPedDet = constructorOrdenPedDet;
    }

        public void facturar() {
        try {
            ejbFactura.procesarFactura(pedidoActual, serie, numeroFactura, idCaja);
            limpiar();
            alert("Factura creada satisfactoriamente",FacesMessage.SEVERITY_INFO);
        } catch (IllegalStateException ex) {
            alert(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(MonitorOrdenPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            alert(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(MonitorOrdenPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            alert(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(MonitorOrdenPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            alert(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(MonitorOrdenPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    public void buscarOrdenPedido() {
        try {
            if (idSucursal != null) {
                if (fechaIni != null && fechaFin != null) {
                    if (fechaIni.after(fechaFin)) {
                        alert("La fecha inicial tiene que ser menor que la final.",
                                FacesMessage.SEVERITY_WARN);
                        return;
                    }
                } else {
                    alert("No puede dejar ningun parametro de busqueda vacio", FacesMessage.SEVERITY_FATAL);
                    return;
                }
            } else {
                alert("El campo sucursal no puede quedar vacio.", FacesMessage.SEVERITY_FATAL);
                return;
            }

            Sucursal suc = new Sucursal();
            suc.setIdsucursal(idSucursal);
            Utilidades uti = new Utilidades();
            sucursal = suc.getSucursal();
            fechaFin = uti.getFiltroDeFecha(fechaFin, 1);
            lstOrdenPedido = ejbBuscarPedido.buscarOrdenPedido(suc, fechaIni, fechaFin);
            if (lstOrdenPedido == null || lstOrdenPedido.isEmpty()) {
                alert("No se encontrarón resultados.", FacesMessage.SEVERITY_FATAL);
            }
        } catch (Exception ex) {
            Logger.getLogger(MonitorOrdenPedido.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    public void limpiar() {
        
        idOrdenPedido = null;
        idCliente = null;
        idEstado = null;
        fechaPedido = null;
        mesa = null;
        idUsuario = null;
        sucursal=null;
        cliente=null;
        estado=null;
        lstOrdenPedidoDet = null;
        idSucursal = null;
        tipoPedido=null;
        numeroFactura=null;
        serie = null;
        idCaja = null;
        pedidoActual = null;
        
          
    }

    public void limpiarDialogo() {
        fechaIni = null;
        fechaFin = null;
        idSucursal = null;
        lstOrdenPedido = null;
    }

    public void onRowSelectPedido(SelectEvent event) {
        try {
            Ordenpedido op = (Ordenpedido) event.getObject();
            pedidoActual = op;
            if (op != null) {
                Sucursal su = new Sucursal();
                idOrdenPedido = op.getOrdenpedidoPK().getIdordenpedido();
                idSucursal = op.getOrdenpedidoPK().getIdsucursal();
                su.setIdsucursal(idSucursal);
                sucursal = ejbBuscarPedido.nombreSucursal(su);
                lstNumeroFiscal = ejbFactura.numeroFiscalList(op.getSucursal(), 1);
                lstCaja = ejbFactura.cajaList(op.getSucursal());
                cliente = op.getIdcliente().getNombre() + " " + op.getIdcliente().getApellido();
                estado = op.getIdestado().getEstado();
                fechaPedido = op.getFechapedido();
                mesa = op.getMesa();
                idUsuario = op.getIdusuario().getIdusuario();
                lstOrdenPedidoDet = op.getOrdenpedidodetalleList();
                tipoPedido = op.getTipoPedido();
                if(op.getWeb().equals(1) && op.getIdestado().getIdestado().equals(EstadoEnum.PENDIENTE_DESPACHAR.getInteger())){
                  activarEnvioPOS = true;
                }else
                {
                    activarEnvioPOS = false;
                }
                limpiarDialogo();
            }
        } catch (Exception ex) {
            Logger.getLogger(MonitorOrdenPedido.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    public void buscarSucursal() {
        try {
            lstSucursal = ejbBuscarSucursal.buscarSucursal();
            if (lstSucursal == null || lstSucursal.isEmpty()) {
                alert("No se encontraron resultado", FacesMessage.SEVERITY_WARN);
            }
        } catch (Exception ex) {
            Logger.getLogger(MonitorOrdenPedido.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    public void mostrarDialogoPedidos() {
        buscarSucursal();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dMonPed').show();");
    }

    public void descargarPedidos() {
        
        try {
            Configuracion config =  crud.buscarEntidad(Configuracion.class, 7);
            if (config == null) {
                alert("No existe configuracion de host para el servidor principal.", FacesMessage.SEVERITY_WARN);
                return;
            }
            if (config.getValor() == null) {
                alert("No existe valor de la configuracio.", 
                        FacesMessage.SEVERITY_INFO);
                return;
            }
            String url = config.getValor().trim();
            System.out.println("url.. " +url);
            String jsonRetur = this.consumerWS.consumirWebservices(
                    "", "",
                    url + "ConsultaPedidosWS");
            System.out.println("jsonRetur... " + jsonRetur);
            Ordenpedido ped = new Gson().fromJson(jsonRetur,Ordenpedido.class);
            System.out.println("lstSucursal..." + lstSucursal);
            if (lstOrdenPedido == null || lstOrdenPedido.isEmpty()) {
                alert("No se encontraron resultados de pedidos.", 
                        FacesMessage.SEVERITY_INFO);
            }
            List <Ordenpedido> lstPedido = new ArrayList<>();
            
            for (Ordenpedido ordenpedido : lstOrdenPedido) {
                if (ordenpedido.getSucursal() != null
                        && ordenpedido.getSucursal().getIdsucursal() != null
                        && ordenpedido.getSucursal().getIdsucursal().equals(
                                sesion.getSucursal().getIdsucursal())) {
                    lstPedido.add(ordenpedido);
                }
            }
            
            crud.guardarEntidades(lstPedido);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MonitorOrdenPedido.class.getName()).log(Level.SEVERE, null, ex);
            alert("Error: ", FacesMessage.SEVERITY_FATAL);
        }
    }
    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    //</editor-fold>

    /**
     * @return the tipoPedido
     */
    public String getTipoPedido() {
        return tipoPedido;
    }

    /**
     * @param tipoPedido the tipoPedido to set
     */
    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    /**
     * @return the activarEnvioPOS
     */
    public boolean isActivarEnvioPOS() {
        return activarEnvioPOS;
    }

    /**
     * @param activarEnvioPOS the activarEnvioPOS to set
     */
    public void setActivarEnvioPOS(boolean activarEnvioPOS) {
        this.activarEnvioPOS = activarEnvioPOS;
    }
    
    
    
}

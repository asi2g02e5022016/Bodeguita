/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.ventas;

import com.asi.restaurantbcd.modelo.Facturadetalle;
import com.asi.restaurantbcd.modelo.Facturaencabezado;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedaFactura;
import com.asi.restaurantebcd.negocio.base.BusquedaFacturaLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursalLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author samaelopez
 */
@ManagedBean(name = "monitorFacturacion")
@ViewScoped
public class MonitorFacturacion implements Serializable {

    /**
     * Creates a new instance of MonitorFacturacion
     */
    public MonitorFacturacion() {
    }
    //<editor-fold  defaultstate="collapsed" desc="LocalVariables" >
    private SessionUsr sesion;
    //Parametros de busqueda
    private Integer idSucursal;
    private Date fechaIni;
    private Date fechaFin;
    //Var encabezado de factura
    private Integer idFactura;
    private String idSerie;
    private Integer idTipoDocumento;
    private Integer idCliente;
    private Integer idFormaPago;
    private Date fechaFactura;
    private String idUsuario;
    private Boolean anulado;
    private Integer idCaja;
    private Integer idOrdenPedido;
    private String sucursal;
    private String tipoDocumento;
    private String cliente;
    private String formaPago;
    private String condicionPago;
    private String usuario;
    private String caja;

    private List<Facturaencabezado> lstFacturaEnc;
    private List<Facturadetalle> lstFacturaDet;
    private List<Sucursal> lstSucursal;
    private DataTable dtFactEnc = new DataTable();
    @EJB // Contine metodos de guardar,eliminar,buscar
    private CrudBDCLocal crud;
    @EJB
    private BusquedaFacturaLocal ejbBuscarFactura;
    @EJB
    private BusquedasSucursalLocal ejbBuscarSucursal;

    private Facturaencabezado constructorFacturaEnc;
    private Sucursal constructorSucursal;
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Metodos">
    public void limpiar() {
        idFactura = null;
        idSerie = null;
        idSucursal = null;
        tipoDocumento = null;
        cliente =null;
        formaPago =null;
        condicionPago =null;
        fechaFactura = null;
        usuario =null;
        anulado = null;
        caja = null;
        lstFacturaDet = null;
    }

    public void limpiarDialogo() {
        lstFacturaEnc = null;
        fechaIni = null;
        fechaFin = null;
        idSucursal = null;
    }

    public void onRowSelectFactura(SelectEvent event) {
        System.out.print("entro");
        Facturaencabezado fe = (Facturaencabezado) event.getObject();
        System.out.print(fe);
        if (fe != null) {

            idFactura = fe.getFacturaencabezadoPK().getIdfactura();
            idSerie = fe.getFacturaencabezadoPK().getIdserie();
            idSucursal = fe.getFacturaencabezadoPK().getIdsucursal();
            tipoDocumento = fe.getIdtipodocumento().getTipodocumento();
            cliente = fe.getIdcliente().getNombre() + fe.getIdcliente().getApellido();
            formaPago = fe.getIdformapago().getFormapago();
            condicionPago = fe.getIdcondicionpago().getCondicionpago();
            fechaFactura = fe.getFechafactura();
            usuario = fe.getIdusuario().getIdusuario();
            anulado = fe.getAnulado();
            caja = fe.getIdcaja().getCaja();
            lstFacturaDet = fe.getFacturadetalleList();
//            sucursal = getSucursal();
            limpiarDialogo();

        }
    }

    public void mostrarDialogoFacturas() {
        buscarSucursal();

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dMonFact').show();");
    }

    public void buscarSucursal() {
        try {
            lstSucursal = ejbBuscarSucursal.buscarSucursal();
            if (lstSucursal == null || lstSucursal.isEmpty()) {
                alert("No se encontraron resultado", FacesMessage.SEVERITY_WARN);
            }
        } catch (Exception ex) {
            Logger.getLogger(MonitorFacturacion.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    public void buscarFactura() {
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
            lstFacturaEnc = ejbBuscarFactura.buscarFacturas(suc, fechaIni, fechaFin);
            if (lstFacturaEnc == null || lstFacturaEnc.isEmpty()) {
                System.out.print("la lista esta vacia");
                alert("No se encontrarón resultados.", FacesMessage.SEVERITY_FATAL);
            }
        } catch (Exception ex) {
            Logger.getLogger(MonitorFacturacion.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

//    public void buscarFacturas() {
//        try {
//            System.out.print("si entro en el buscar");
//            System.out.print(fechaIni + " " + fechaFin + " " + idSucursal.toString());
//            if (idSucursal != null) {
//                if (fechaIni != null && fechaFin != null) {
//                    if (fechaIni.after(fechaFin)) {
//                        alert("La fecha inicial tiene que ser menor que la final.",
//                                FacesMessage.SEVERITY_WARN);
//                        return;
//                    }
//                } else {
//                    alert("No puede dejar ningun parametro de busqueda vacio", FacesMessage.SEVERITY_FATAL);
//                    return;
//                }
//            } else {
//                alert("El campo sucursal no puede quedar vacio.", FacesMessage.SEVERITY_FATAL);
//                return;
//            }
//            Sucursal suc = new Sucursal();
//            suc.setIdsucursal(idSucursal);
//            System.out.print(fechaIni + " " + fechaFin + " " + idSucursal.toString());
//            lstFacturaEnc = ejbBuscarFactura.buscarFacturas(suc, fechaIni, fechaFin);
//            if (lstFacturaEnc == null || lstFacturaEnc.isEmpty()) {
//                System.out.print("la lista esta vacia");
//                alert("No se encontrarón resultados.", FacesMessage.SEVERITY_FATAL);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(MonitorFacturacion.class.getName()).log(
//                    Level.SEVERE, null, ex);
//            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
//        }
//    }

    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    //</editor-fold >

    //<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(String condicionPago) {
        this.condicionPago = condicionPago;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public List<Facturadetalle> getLstFacturaDet() {
        return lstFacturaDet;
    }

    public void setLstFacturaDet(List<Facturadetalle> lstFacturaDet) {
        this.lstFacturaDet = lstFacturaDet;
    }

    public BusquedaFacturaLocal getEjbBuscarFactura() {
        return ejbBuscarFactura;
    }

    public void setEjbBuscarFactura(BusquedaFacturaLocal ejbBuscarFactura) {
        this.ejbBuscarFactura = ejbBuscarFactura;
    }

    public BusquedasSucursalLocal getEjbBuscarSucursal() {
        return ejbBuscarSucursal;
    }

    public void setEjbBuscarSucursal(BusquedasSucursalLocal ejbBuscarSucursal) {
        this.ejbBuscarSucursal = ejbBuscarSucursal;
    }

    public Sucursal getConstructorSucursal() {
        return constructorSucursal;
    }

    public void setConstructorSucursal(Sucursal constructorSucursal) {
        this.constructorSucursal = constructorSucursal;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
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

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(Integer idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public Integer getIdOrdenPedido() {
        return idOrdenPedido;
    }

    public void setIdOrdenPedido(Integer idOrdenPedido) {
        this.idOrdenPedido = idOrdenPedido;
    }

    public List<Facturaencabezado> getLstFacturaEnc() {
        return lstFacturaEnc;
    }

    public void setLstFacturaEnc(List<Facturaencabezado> lstFacturaEnc) {
        this.lstFacturaEnc = lstFacturaEnc;
    }

    public DataTable getDtFactEnc() {
        return dtFactEnc;
    }

    public void setDtFactEnc(DataTable dtFactEnc) {
        this.dtFactEnc = dtFactEnc;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public Facturaencabezado getConstructorFacturaEnc() {
        return constructorFacturaEnc;
    }

    public void setConstructorFacturaEnc(Facturaencabezado constructorFacturaEnc) {
        this.constructorFacturaEnc = constructorFacturaEnc;
    }

    //</editor-fold>
}

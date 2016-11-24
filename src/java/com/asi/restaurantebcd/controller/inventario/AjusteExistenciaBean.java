/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Ajuste;
import com.asi.restaurantbcd.modelo.AjustePK;
import com.asi.restaurantbcd.modelo.Ajustedetalle;
import com.asi.restaurantbcd.modelo.AjustedetallePK;
import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedaAjustesExistenciaLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.base.ProcesosInventariosLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author JAEM
 */
@ManagedBean(name = "ajusteExistenciaBean")
@ViewScoped

public class AjusteExistenciaBean implements Serializable {

    /**
     * Creates a new instance of AjusteExistenciaBean
     */
    public AjusteExistenciaBean() {
    }

    @PostConstruct
    public void postContruction() {
        try {
            System.out.println("entro al manage");
            sesion = Utilidades.findBean("sessionUsr");
//            fechaCreacion = new Date();
//            idUsuarioCrea = sesion.getUsuario().getIdusuario().toUpperCase();
//            buscarProducto();

        } catch (Exception e) {
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    //<editor-fold  defaultstate="collapsed" desc="LocalVariables" >
    private SessionUsr sesion;
    //var navegación formulario principal
    private boolean mostrarBtnGuardar = false;
    private boolean habilitarObservacion = true;
    private boolean mostrarBtnProductos = true;
    private boolean mostrarEliminar = true;

    private String sucursal;
    // parametros de busqueda monitor
    private Date fechaIni;
    private Date fechaFin;
    //Encabezado
    private Integer idAjuste;
    private Integer idSucursal;
    private Date fechaCreacion;
    private String idUsuarioCrea;
    private String estado;
    private Boolean autorizado;
    private String observacion;
    //Detalle
    private Integer idproducto;
    private String producto;
    private Double cantidad;
    private Double costoUnitario;

    private List<Producto> lstProducto;
    private List<Sucursal> lstSucursal;
    private List<Ajuste> lstAjusteEnc;
    private List<Ajustedetalle> lstAjusteDet;

    private DataTable dtProducto = new DataTable();
    private DataTable dtDetAjuste = new DataTable();
    @EJB // Contine metodos de guardar,eliminar,buscar
    private CrudBDCLocal crud;
    @EJB
    private BusquedaAjustesExistenciaLocal ejbBuscarAjuste;
    @EJB
    private ProcesosInventariosLocal ejbProInv;
    private Ajustedetalle consturctorAjusteDet;
    private Ajuste constructorAjuste;

    //</editor-fold>
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    public void nuevo() throws Exception {

        idAjuste = null;
        this.lstAjusteDet = null;
        this.fechaCreacion = new Date();
        this.observacion = "";
        idUsuarioCrea = sesion.getUsuario().getIdusuario().toUpperCase();
        mostrarBtnGuardar = true;
        habilitarObservacion = false;
        mostrarBtnProductos = false;
        sucursal = sesion.getSucursal().getSucursal();
        idAjuste = this.ejbBuscarAjuste.obtenerCorreltivoAjuste(sesion.getSucursal().getIdsucursal(), Ajuste.class, "idajuste");
        mostrarEliminar = true;
        observacion = null;
    }

    public void limpiar() {
        idUsuarioCrea = null;
        this.lstAjusteDet = null;
        this.fechaCreacion = null;
        this.observacion = null;
        mostrarBtnProductos = false;
        mostrarBtnGuardar = false;
        idAjuste = null;
        idUsuarioCrea = null;
        idSucursal = null;
        sucursal = null;
        habilitarObservacion = false;
        mostrarEliminar = true;
    }

    public void limpiarProducto() {
        this.cantidad = null;
    }

    public void mostrarDialogoAjustes() {
        limpiar();
        System.out.print("entro al mostrar");
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoMonAjustes').show();");
    }

    public void buscarAjustes() {
        try {
            //Validaciones de parametros de busqueda
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
            System.out.print(fechaIni + " " + fechaFin + " " + sesion.getSucursal());
            Utilidades uti = new Utilidades();
            fechaFin = uti.getFiltroDeFecha(fechaFin, 1);
            lstAjusteEnc = ejbBuscarAjuste.buscarAjustesExistenciaEnc(sesion.getSucursal(),
                    fechaIni, fechaFin);
            if (lstAjusteEnc == null || lstAjusteEnc.isEmpty()) {
                System.out.print("la lista esta vacia");
                alert("No se encontrarón resultados.", FacesMessage.SEVERITY_FATAL);
                return;
            }

        } catch (Exception ex) {
            Logger.getLogger(AjusteExistenciaBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }

    public void guardar() {
        try {
            System.out.println("ENTRO");
            System.out.println(idAjuste);
//             alert(idAjuste.toString(), FacesMessage.SEVERITY_INFO);
            if (lstAjusteDet == null || lstAjusteDet.isEmpty()) {
                alert("El documento no tiene detalle.", FacesMessage.SEVERITY_FATAL);
                return;
            }
            if (observacion == null || observacion.isEmpty()) {
                alert("El campo de observación no puede quedar vacio.", FacesMessage.SEVERITY_FATAL);
                return;
            }
//            idAjuste = this.ejbBuscarAjuste.obtenerCorreltivoAjuste(sesion.getSucursal().getIdsucursal(), Ajuste.class, "idajuste");
//            alert(String.valueOf(idAjuste), FacesMessage.SEVERITY_INFO);
            if (idAjuste > 0) {
                alert(idAjuste.toString(), FacesMessage.SEVERITY_INFO);
                this.constructorAjuste = new Ajuste();
                AjustePK pkIdAjute = new AjustePK();
                pkIdAjute.setIdajuste(idAjuste);
                pkIdAjute.setIdsucursal(sesion.getSucursal().getIdsucursal());
                constructorAjuste.setAjustePK(pkIdAjute);
                int i = 0;
                for (Ajustedetalle ajstDet : lstAjusteDet) {
                    i++;
                    AjustedetallePK pkIdAjstDet = new AjustedetallePK();
                    pkIdAjstDet.setIdajuste(pkIdAjute.getIdajuste());
                    pkIdAjstDet.setIdajustedetalle(i);
                    pkIdAjstDet.setIdsucursal(pkIdAjute.getIdsucursal());
                    ajstDet.setAjustedetallePK(pkIdAjstDet);
                    ajstDet.setAjuste(constructorAjuste);
                }
                constructorAjuste.setIdusuariocrea(idUsuarioCrea);
                constructorAjuste.setAjustedetalleList(lstAjusteDet);
                constructorAjuste.setFechacreacion(fechaCreacion);
                constructorAjuste.setAutorizado(false);
                constructorAjuste.setObservacion(observacion);
                System.out.println(pkIdAjute);
                crud.guardarEntidad(constructorAjuste);
                alert("Registro ingresado exitosamente", FacesMessage.SEVERITY_INFO);
                this.ajustarExistencia();
                limpiar();
            }
        } catch (Exception ex) {
            Logger.getLogger(AjusteExistenciaBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    public void eliminarDetalle() {
        try {
            if (dtDetAjuste.getRowData() != null) {
                lstAjusteDet.remove(this.dtDetAjuste.getRowIndex());
                dtDetAjuste.setValue(lstAjusteDet);
                alert("Registro eliminado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(AjusteExistenciaBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void ajustarExistencia() {
        try {
            if (lstAjusteDet != null) {
                boolean carga;
                Integer i = 0;
                Double can = 0.0;
                for (Ajustedetalle ajstDet : lstAjusteDet) {
                    can = ajstDet.getCantidad();
                    i++;
                    if (ajstDet.getCantidad() > 0) {
                        carga = false;
                    } else {
                        carga = true;
                        can = can * -1;
                    }

                    System.out.println("cantidad" + can);
                    System.out.println("producto" + ajstDet.getIdproducto());
                    System.out.println("usuario" + sesion.getUsuario());
                    System.out.println("sucursal" + ajstDet.getCostounitario());
                    System.out.println("Carga" + carga);

                    ejbProInv.afectarExistencia(
                            can,
                            ajstDet.getIdproducto(),
                            sesion.getUsuario(),
                            sesion.getSucursal(),
                            ajstDet.getCostounitario(),
                            carga,
                            false
                    );
                    can = 0.00;
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(AjusteExistenciaBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }

    }

    public void onRowSelectAjuste(SelectEvent event) {
        try {
            System.out.print("entro");
            Ajuste ajt = (Ajuste) event.getObject();
            System.out.print(ajt);
            if (ajt != null) {
                idAjuste = ajt.getAjustePK().getIdajuste();
                idSucursal = ajt.getAjustePK().getIdsucursal();
                Sucursal su = new Sucursal();
                su.setIdsucursal(idSucursal);
                sucursal = ejbBuscarAjuste.nombreSucursal(su);
                fechaCreacion = ajt.getFechacreacion();
                observacion = ajt.getObservacion();
                idUsuarioCrea = ajt.getIdusuariocrea();
                lstAjusteDet = ajt.getAjustedetalleList();
                mostrarBtnGuardar = false;
                lstAjusteEnc = null;
                fechaIni = null;
                fechaFin = null;
                mostrarEliminar = false;
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoMonAjustes').hide()");
            }
        } catch (Exception ex) {
            Logger.getLogger(AjusteExistenciaBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    }

    public void onRowSelectProducto(SelectEvent event) {
        try {
            if (cantidad != null && cantidad != 0) {
                Double precioVenta;
                Producto pro = (Producto) event.getObject();
                if (pro != null) {
                    consturctorAjusteDet = new Ajustedetalle();
                    consturctorAjusteDet.setIdproducto(pro);
                    consturctorAjusteDet.setCantidad(cantidad);
                    precioVenta = pro.getPreciocompra();

                    consturctorAjusteDet.setCostounitario(pro.getPreciocompra());
                    if (consturctorAjusteDet != null) {
                        if (lstAjusteDet == null) {
                            lstAjusteDet = new ArrayList<>();
                        }
                        lstAjusteDet.add(consturctorAjusteDet);
//                   
                    } else {
                        alert("Error por favor comunicarse con el administrador de sistemas.",
                                FacesMessage.SEVERITY_INFO);
                    }

                }
                limpiarProducto();
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProducto').hide();");

            } else if (cantidad == 0 || cantidad == null) {
                alert("La cantidad de productosno puede ser cero.",
                        FacesMessage.SEVERITY_INFO);
            } else {
                alert("Ingresar la cantidad de productos que desea ajustar.",
                        FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(AjusteExistenciaBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    }

    public void mostrarProducto() {
        try {
            limpiarProducto();
            buscarProducto();
            System.out.println("entro en el mostrar");
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogoProducto').show();");
        } catch (Exception ex) {

            Logger.getLogger(AjusteExistenciaBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    }

    public void buscarProducto() {
        try {
            lstProducto = ejbBuscarAjuste.buscarProducto();
            System.out.println("lstProducto.." + lstProducto.toString());
            if (lstProducto == null || lstProducto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(AjusteExistenciaBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
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

    //<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
    public boolean isMostrarEliminar() {
        return mostrarEliminar;
    }

    public void setMostrarEliminar(boolean mostrarEliminar) {
        this.mostrarEliminar = mostrarEliminar;
    }

    public Integer getIdAjuste() {
        return idAjuste;
    }

    public void setIdAjuste(Integer idAjuste) {
        this.idAjuste = idAjuste;
    }

    public boolean isMostrarBtnProductos() {
        return mostrarBtnProductos;
    }

    public void setMostrarBtnProductos(boolean mostrarBtnProductos) {
        this.mostrarBtnProductos = mostrarBtnProductos;
    }

    public boolean isHabilitarObservacion() {
        return habilitarObservacion;
    }

    public void setHabilitarObservacion(boolean habilitarObservacion) {
        this.habilitarObservacion = habilitarObservacion;
    }

    public boolean isMostrarBtnGuardar() {
        return mostrarBtnGuardar;
    }

    public void setMostrarBtnGuardar(boolean mostrarBtnGuardar) {
        this.mostrarBtnGuardar = mostrarBtnGuardar;
    }

    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Boolean getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Boolean autorizado) {
        this.autorizado = autorizado;
    }

    public Ajustedetalle getConsturctorAjusteDet() {
        return consturctorAjusteDet;
    }

    public void setConsturctorAjusteDet(Ajustedetalle consturctorAjusteDet) {
        this.consturctorAjusteDet = consturctorAjusteDet;
    }

    public Ajuste getConstructorAjuste() {
        return constructorAjuste;
    }

    public void setConstructorAjuste(Ajuste constructorAjuste) {
        this.constructorAjuste = constructorAjuste;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public List<Ajuste> getLstAjusteEnc() {
        return lstAjusteEnc;
    }

    public void setLstAjusteEnc(List<Ajuste> lstAjusteEnc) {
        this.lstAjusteEnc = lstAjusteEnc;
    }

    public List<Ajustedetalle> getLstAjusteDet() {
        return lstAjusteDet;
    }

    public void setLstAjusteDet(List<Ajustedetalle> lstAjusteDet) {
        this.lstAjusteDet = lstAjusteDet;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getIdUsuarioCrea() {
        return idUsuarioCrea;
    }

    public void setIdUsuarioCrea(String idUsuarioCrea) {
        this.idUsuarioCrea = idUsuarioCrea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public List<Producto> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<Producto> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public DataTable getDtProducto() {
        return dtProducto;
    }

    public void setDtProducto(DataTable dtProducto) {
        this.dtProducto = dtProducto;
    }

    public DataTable getDtDetAjuste() {
        return dtDetAjuste;
    }

    public void setDtDetAjuste(DataTable dtDetAjuste) {
        this.dtDetAjuste = dtDetAjuste;
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

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    //</editor-fold>
}

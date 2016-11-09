/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Producto;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedaAjustesExistencia;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

/**
 *
 * @author JAEM
 */
@ManagedBean(name = "ajusteExistencia")
@ViewScoped
public class AjusteExistencia implements Serializable{

    /**
     * Creates a new instance of AjusteExistencia
     */
    public AjusteExistencia() {
    }
 @PostConstruct
    public void postContruction() {
        try {
            System.out.println("entro al manage");
             sesion = Utilidades.findBean("sessionUsr");
             buscarProducto();
//             lstAjusteDet=ejbBuscarAjuste.buscarAjustesExistenciaDet();
        }catch (Exception e) {
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    
    //<editor-fold  defaultstate="collapsed" desc="LocalVariables" >
    private SessionUsr sesion;
    //Encabezado
    private Integer idAjuste;
    private Integer idSucursal;
    private Date fechaCreacion;
    private String idUsuarioCrea;
    private String estado;
    private String observacion;
    //Detalle
    private Integer idproducto;
    private String producto;
    private Double cantidad;
    private Double costoUnitario;

    private List<Producto> lstProducto;
    private List<Sucursal> lstSucursal;
    private List<AjusteExistencia> lstAjusteEnc;
    private List<AjusteExistencia> lstAjusteDet;
    private DataTable dtProducto = new DataTable();
    private DataTable dtDetAjuste = new DataTable();
    @EJB // Contine metodos de guardar,eliminar,buscar
    private CrudBDCLocal crud;
    @EJB
    private BusquedaAjustesExistencia ejbBuscarAjuste;

    //</editor-fold>
    public void buscarProducto() {
        try {
            lstProducto = ejbBuscarAjuste.buscarProducto();
            System.out.println("lstProducto.." + lstProducto.toString());
            if (lstProducto == null || lstProducto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(AjusteExistencia.class.getName()).log(
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

    //<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
    public Integer getIdAjuste() {
        return idAjuste;
    }

    public void setIdAjuste(Integer idAjuste) {
        this.idAjuste = idAjuste;
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

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public List<AjusteExistencia> getLstAjusteEnc() {
        return lstAjusteEnc;
    }

    public void setLstAjusteEnc(List<AjusteExistencia> lstAjusteEnc) {
        this.lstAjusteEnc = lstAjusteEnc;
    }

    public List<AjusteExistencia> getLstAjusteDet() {
        return lstAjusteDet;
    }

    public void setLstAjusteDet(List<AjusteExistencia> lstAjusteDet) {
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

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedaAjustesExistencia getEjbBuscarAjuste() {
        return ejbBuscarAjuste;
    }

    public void setEjbBuscarAjuste(BusquedaAjustesExistencia ejbBuscarAjuste) {
        this.ejbBuscarAjuste = ejbBuscarAjuste;
    }
    //</editor-fold>

}

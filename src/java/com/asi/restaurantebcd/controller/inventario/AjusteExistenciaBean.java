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
import com.asi.restaurantbcd.modelo.Proveedor;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedaAjustesExistencia;
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
            idUsuarioCrea = sesion.getUsuario().getIdusuario().toUpperCase();
            buscarProducto();

        } catch (Exception e) {
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
    public void guardar() {
        try {
            if (lstAjusteDet == null || lstAjusteDet.isEmpty()) {
                alert("El documento no tiene detalle.", FacesMessage.SEVERITY_FATAL);
                return;
            }
            if (observacion == null || observacion.isEmpty()) {
                alert("El campo de observación no puede quedar vacio.", FacesMessage.SEVERITY_FATAL);
                return;
            }
            idAjuste = this.ejbBuscarAjuste.obtenerCorreltivoAjuste(sesion.getSucursal().getIdsucursal(), Ajuste.class, "idajuste");
            if (idAjuste > 0) {
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
//                constructorAjuste.setIdsucursal1(sesion.getSucursal().getIdsucursal());
                constructorAjuste.setIdusuariocrea(idUsuarioCrea);
                constructorAjuste.setAjustedetalleList(lstAjusteDet);
                constructorAjuste.setFechacreacion(fechaCreacion);
                constructorAjuste.setAutorizado(false);
                constructorAjuste.setObservacion(observacion);
                System.out.println(pkIdAjute);
                crud.guardarEntidad(constructorAjuste);
                
                alert("Guardo xd", FacesMessage.SEVERITY_INFO);
                
//                this.ajustarExistencia();
            }

        } catch (Exception ex) {
            Logger.getLogger(AjusteExistenciaBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    
    public void ajustarExistencia() throws Exception{
        if (lstAjusteDet != null){
            boolean carga;
            for(Ajustedetalle ajstDet : lstAjusteDet){
                if (ajstDet.getCantidad()>0){
                    carga = true;
                }else{
                    carga = false;
                }
                ejbProInv.afectarExistencia(
                        ajstDet.getCantidad(),
                        ajstDet.getIdproducto(),
                        sesion.getUsuario(),
                        sesion.getSucursal(),    
                        ajstDet.getCostounitario(),
                        carga,
                        true 
                );
            }
        }
    }
    public void onRowSelectProducto(SelectEvent event) {
        if (cantidad != null && cantidad != 0) {

            Producto pro = (Producto) event.getObject();
            if (pro != null) {
//                idproducto = pro.getIdproducto();
//                producto = pro.getProducto();
                consturctorAjusteDet = new Ajustedetalle();
                consturctorAjusteDet.setIdproducto(pro);
                consturctorAjusteDet.setCantidad(cantidad);
                consturctorAjusteDet.setCostounitario(pro.getPrecioventa());

                if (consturctorAjusteDet != null) {
                    if (lstAjusteDet == null) {
                        lstAjusteDet = new ArrayList<>();
                    }
                    lstAjusteDet.add(consturctorAjusteDet);
//                   
                } else {
                    alert("NULOOOOO",
                            FacesMessage.SEVERITY_INFO);
                }

            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogoProducto').hide();");
        }

    }

    public void mostrarProducto() {
        try {
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

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    //</editor-fold>
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Existencia;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Vwexistencias;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import com.asi.restaurantebcd.negocio.base.BusquedasExistenciasLocal;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 *
 * @author SANCHEZ
 */
@ManagedBean(name = "invExistenciasBean")
@ViewScoped
public class InvExistenciasBean implements Serializable {

    @EJB
    private BusquedasExistenciasLocal BusquedasExistencias;

    //<editor-fold  defaultstate="collapsed" desc="Inializar" >
    public InvExistenciasBean() {
    }

    @PostConstruct
    public void postConstruction() {
        try {
            sesion = Utilidades.findBean("sessionUsr");
            if (sesion == null) {
                alert("Debe Iniciar Sesion", FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().getViewRoot().
                        getViewMap().clear();
                String url = "http://localhost:8080/RestaurantBDC";
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(url);
            }
            codsucursal = sesion.getSucursal().getIdsucursal();
            this.ltsVwExistencias = ejbBucdaExistencias.buscarExistencias(null);
            this.lstSucursal = ejbBucdaExistencias.buscarSucursal();
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="LocalVariables" >
    private Existencia existencia;
    private Vwexistencias vwexistencias;
    
    private int codsucursal;
    //private int codproducto;
    //private int param;
    //private int paramFiltro;*/
    String sucursal;
    String producto;

    private float valor; //Atributo que se muestra en pantalla el valor del producto.
    private float costounitario; //Atributo que se muestra en pantalla el costo unitario del producto.
    private float valreservado;
    private float transito;
    @Inject
    private SessionUsr sesion; //Busca beans session activa.
    private DataTable dtExistencia = new DataTable();
    private Sucursal sucursalConst;
    private List<Vwexistencias> ltsVwExistencias = new ArrayList<>();
    private List<Sucursal> lstSucursal ;
    @EJB //EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
    private CrudBDCLocal crud;

    @EJB
    private BusquedasExistenciasLocal ejbBucdaExistencias;

    //</editor-fold>
    
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    public void limpiarPantalla() {
        try {
        ltsVwExistencias = null;
        //codproducto = 0;
        codsucursal = 0;
        producto = null;
        sucursal = null;
        } catch (Exception ex) {
            Logger.getLogger(InvExistenciasBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }        
    }

    /*public void buscarExistencias() {
        try {
                      
            Map filtro = new HashMap();
            
            System.out.println("trae: "+param);
            System.out.println("filtro: "+paramFiltro);
            
            if (param == 1){
                codsucursal = 0;
                codproducto = paramFiltro;
            }
            if (param == 2){
                codsucursal = paramFiltro;
                codproducto = 0;
            }
            
            if (codsucursal != 0) {
                filtro.put("codsuc", codsucursal);
            }
            if (codproducto != 0) {
                filtro.put("codprod", codproducto);
            }
            
            ltsVwExistencias = ejbBucdaExistencias.buscarExistencias(filtro);
            System.out.println("lstVwExistencias.." +ltsVwExistencias);
            if (ltsVwExistencias == null || ltsVwExistencias.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(InvExistenciasBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    }*/
    
    public void buscarExistencias() {
        try {
                      
            Map filtro = new HashMap();
            
            if (codsucursal != 0){
                filtro.put("codsuc", codsucursal);
            }            
            if (producto != null) {
                filtro.put("prod", producto);
            }
            if (sucursal != null) {
                filtro.put("sucsal", sucursal);
            }
            
            ltsVwExistencias = ejbBucdaExistencias.buscarExistencias(filtro);
            System.out.println("lstVwExistencias.." +ltsVwExistencias);
            if (ltsVwExistencias == null || ltsVwExistencias.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(InvExistenciasBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    }
    
    public void buscarSucursales(){
        try {
             lstSucursal = ejbBucdaExistencias.buscarSucursal();
             if (lstSucursal == null || lstSucursal.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(InvExistenciasBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
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

    //<editor-fold  defaultstate="collapsed" desc="Getter and setter" >
    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
        
    public int getCodsucursal() {
        return codsucursal;
    }

    public void setCodsucursal(int codsucursal) {
        this.codsucursal = codsucursal;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getCostounitario() {
        return costounitario;
    }

    public void setCostounitario(float costounitario) {
        this.costounitario = costounitario;
    }    

    public float getValreservado() {
        return valreservado;
    }

    public void setValreservado(float valreservado) {
        this.valreservado = valreservado;
    }

    public float getTransito() {
        return transito;
    }

    public void setTransito(float transito) {
        this.transito = transito;
    }

    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public Existencia getExistencia() {
        return existencia;
    }

    public void setExistencia(Existencia existencia) {
        this.existencia = existencia;
    }

    public DataTable getDtExistencia() {
        return dtExistencia;
    }

    public void setDtExistencia(DataTable dtExistencia) {
        this.dtExistencia = dtExistencia;
    }

    public Sucursal getSucursalConst() {
        return sucursalConst;
    }

    public void setSucursalConst(Sucursal sucursalConst) {
        this.sucursalConst = sucursalConst;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public BusquedasExistenciasLocal getBusquedasExistencias() {
        return BusquedasExistencias;
    }

    public void setBusquedasExistencias(BusquedasExistenciasLocal BusquedasExistencias) {
        this.BusquedasExistencias = BusquedasExistencias;
    }

    public Vwexistencias getVwexistencias() {
        return vwexistencias;
    }

    public void setVwexistencias(Vwexistencias vwexistencias) {
        this.vwexistencias = vwexistencias;
    }

    public List<Vwexistencias> getLtsVwExistencias() {
        return ltsVwExistencias;
    }

    public void setLtsVwExistencias(List<Vwexistencias> ltsVwExistencias) {
        this.ltsVwExistencias = ltsVwExistencias;
    }

    public BusquedasExistenciasLocal getEjbBucdaExistencias() {
        return ejbBucdaExistencias;
    }

    public void setEjbBucdaExistencias(BusquedasExistenciasLocal ejbBucdaExistencias) {
        this.ejbBucdaExistencias = ejbBucdaExistencias;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }
    
   @Override
    public String toString() {
        return "InvExistenciasBean{" + "BusquedasExistencias=" + BusquedasExistencias + ", existencia=" + existencia + ", vwexistencias=" + vwexistencias + ", codsucursal=" + codsucursal + ", sucursal=" + sucursal + ", producto=" + producto + ", valor=" + valor + ", costounitario=" + costounitario + ", sesion=" + sesion + ", dtExistencia=" + dtExistencia + ", sucursalConst=" + sucursalConst + ", ltsVwExistencias=" + ltsVwExistencias + ", lstSucursal=" + lstSucursal + ", crud=" + crud + ", ejbBucdaExistencias=" + ejbBucdaExistencias + '}';
    }
    //</editor-fold>    

    
}

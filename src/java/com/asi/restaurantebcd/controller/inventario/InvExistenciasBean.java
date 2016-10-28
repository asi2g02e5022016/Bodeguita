/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Existencia;
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
import javax.ejb.Stateless;
import javax.swing.tree.DefaultTreeCellEditor;

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

    /*@PostConstruct
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
            buscarExistenciasActuales();
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }*/
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="LocalVariables" >
    /**
     * Entidad que buscará las existencias.
     */
    private Existencia existencia;
    /**
     * Vista de las existencias.
     */
    private Vwexistencias vwexistencias;
    /**
     * Atributo que se muestra en pantalla el idsucursal.
     */
    //private String codsucursal;
    /**
     * Atributo que se muestra en pantalla el idproducto.
     */
    //private String codproducto;
    
    private int codsucursal;
    private int codproducto;

    /**
     * Atributo que se muestra en pantalla el valor del producto.
     */
    private float valor;
    /**
     * Atributo que se muestra en pantalla el costo unitario del producto.
     */
    private float costounitario;
    /**
     * Busca beans session activa.
     */
    private SessionUsr sesion;

    /**
     * Atributo que se muestra en pantalla la lista de existencias.
     */
    /**
     * Bindin de DataTable que muestra las existencias.
     */
    private DataTable dtExistencia = new DataTable();
    private List<Vwexistencias> ltsVwExistencias = new ArrayList<>();
    /**
     * EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
     */
    @EJB
    private CrudBDCLocal crud;

    @EJB
    private BusquedasExistenciasLocal ejbBucdaExistencias;

    //</editor-fold>
    
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    public void limpiarPantalla() {
        ltsVwExistencias = null;
        //codproducto = null;
        //codsucursal = null;
        codproducto = 0;
        codsucursal = 0;
    }

    public void buscarExistenciasActuales() {
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

    //<editor-fold  defaultstate="collapsed" desc="PopupExistencias" >
    public void buscarExistencias() {
        try {
            Map filtro = new HashMap();
            
            System.out.println("el filtro es" +codsucursal);
            System.out.println("el filtro es" +codproducto);

            if (codsucursal != 0) {
                filtro.put("codsuc", codsucursal);
            }
            if (codproducto != 0) {
                filtro.put("codprod", codproducto);
            }
            
            ltsVwExistencias = ejbBucdaExistencias.buscarExistenciaFiltros(filtro);
            System.out.println("lstProducto.." +ltsVwExistencias);
            if (ltsVwExistencias == null || ltsVwExistencias.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(InvExistenciasBean.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Getter and setter" >
    /*public String getCodsucursal() {
        return codsucursal;
    }

    public void setCodsucursal(String codsucursal) {
        this.codsucursal = codsucursal;
    }

    public String getCodproducto() {
        return codproducto;
    }
    

    public void setCodproducto(String codproducto) {
        this.codproducto = codproducto;
    }*/

    public int getCodsucursal() {
        return codsucursal;
    }

    public void setCodsucursal(int codsucursal) {
        this.codsucursal = codsucursal;
    }

    public int getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(int codproducto) {
        this.codproducto = codproducto;
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
        return "InvExistenciasBean{" + "BusquedasExistencias=" + BusquedasExistencias + ", existencia=" + existencia + ", vwexistencias=" + vwexistencias + ", codsucursal=" + codsucursal + ", codproducto=" + codproducto + ", valor=" + valor + ", costounitario=" + costounitario + ", sesion=" + sesion + ", dtExistencia=" + dtExistencia + ", ltsVwExistencias=" + ltsVwExistencias + ", crud=" + crud + ", ejbBucdaExistencias=" + ejbBucdaExistencias + '}';
    }
    //</editor-fold>

    
}

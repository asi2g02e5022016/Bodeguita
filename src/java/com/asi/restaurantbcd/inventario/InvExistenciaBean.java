/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.inventario;

import com.asi.restaurantbcd.modelo.Existencia;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasInvExistenciaLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.io.Serializable;
import java.util.List;
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

/**
 *
 * @author SANCHEZ
 */
@ManagedBean(name = "invExistenciaBean")
@ViewScoped
public class InvExistenciaBean implements Serializable {
    
    //<editor-fold  defaultstate="collapsed" desc="Inializar" >
    public InvExistenciaBean(){
    }
    
    @PostConstruct
    public void postConstruction(){
        try{
            sesion = Utilidades.findBean("sessionUsr");
            if(sesion == null){
                alert("Debe Iniciar Sesion",FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().getViewRoot().
                        getViewMap().clear();
            String url = "http://localhost:8080/RestaurantBDC";
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect(url);
            } 
            buscarInvExistencia();
        } catch (Exception e) {
                alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    //</editor-fold>
    
    //<editor-fold  defaultstate="collapsed" desc="LocalVariables" >
    /**Atributo que se muestra en pantalla el codigo de existencia.*/
    private Integer idExistencia;
    /**Atributo que se muestra en pantalla el valor del producto.*/
    private float valor;
      /**Atributo que se muestra en pantalla el costo unitario del producto.*/
    private float costoUnitario;
      /**Busca beans session activa.*/
    private SessionUsr sesion ;
      /**Entidad que buscará las existencias.*/
    private Existencia existencia;
      /**Atributo que se muestra en pantalla la lista de existencias.*/
    private List <Existencia> listExistencia;
    /**Bindin de DataTable que muestra las existencias.*/
    private DataTable dtExistencia  =  new DataTable();
     /**
      * EJB Quecon tiene metodos utilitarios como:
      * Guardar, Eliminar, Buscar... 
      */
     @EJB
    private CrudBDCLocal crud;
     /**
      * EJB 
      * de busquedas  de mantenimiento.
      */
     @EJB
    private BusquedasInvExistenciaLocal ejbBusqInvExstLocal;
    //</editor-fold>
    
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
     public void limpiarPantalla() {
         listExistencia = null;
         existencia = null;
         idExistencia = null;
         valor = 0;
         costoUnitario = 0;
         dtExistencia = new DataTable();
    }
     
     public void buscarInvExistencia() {
        try {
            listExistencia =  ejbBusqInvExstLocal.buscarInvExistencia();
            if (listExistencia == null || listExistencia.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(InvExistenciaBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje =  "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
     //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Getter and setter" >
    public Integer getIdExistencia() {
        return idExistencia;
    }

    public void setIdExistencia(Integer idExistencia) {
        this.idExistencia = idExistencia;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
        this.costoUnitario = costoUnitario;
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

    public List<Existencia> getListExistencia() {
        return listExistencia;
    }

    public void setListExistencia(List<Existencia> listExistencia) {
        this.listExistencia = listExistencia;
    }

    public DataTable getDtExistencia() {
        return dtExistencia;
    }

    public void setDtExistencia(DataTable dtExistencia) {
        this.dtExistencia = dtExistencia;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedasInvExistenciaLocal getEjbBusqInvExstLocal() {
        return ejbBusqInvExstLocal;
    }

    public void setEjbBusqInvExstLocal(BusquedasInvExistenciaLocal ejbBusqInvExstLocal) {
        this.ejbBusqInvExstLocal = ejbBusqInvExstLocal;
    }
    //</editor-fold>
    
}

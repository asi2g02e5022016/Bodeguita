/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Estado;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasEstadoLocal;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author SANCHEZ
 */
@ManagedBean(name = "mttoEstado")
@ViewScoped
public class MttoEstado implements Serializable {

//<editor-fold  defaultstate="collapsed" desc="Inializar" >
    /**
     * Creates a new instance of MttoCompanias
     */
    public MttoEstado() {
    }

    /**
     * Metodo cuando carga la pagina.
     */
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
            buscarEstados();
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    //</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="LocalVariables" >
    private Integer idEstado; //Atributo que se muestra en pantalla el codigo de estado.
    private String nombreEstado; //Atributo que se muestra en pantalla el nombre de estado documento.
    private SessionUsr sesion; //Busca beans session activa.
    private Estado estadoConst; //Constructor de Entidad que hará los cruds del estado.
    private List<Estado> listEstado; //Atributo que se muestra en pantalla la lista de estado.
    private DataTable dtEstado = new DataTable();//Bindin de DataTable que muestra los estado documentos.
    @EJB
    private CrudBDCLocal crud; //EJB Quecon tiene metodos utilitarios como: Guardar,Actualizar, Eliminar, Buscar...
    @EJB
    private BusquedasEstadoLocal ejbBusqEstLocal; //EJB de busquedas  de mantenimiento.
    //</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Metodos" >
    /**
     * Metodo para limpiar informacion de pantalla.
     */
    public void limpiarPantalla() {
        listEstado = null;
        estadoConst = null;
        nombreEstado = null;
        idEstado = null;
        dtEstado = new DataTable();
    }

    /**
     * Metodo que se ejecuta cuando se desea guardar un estado documento.
     */
    public void buscarEstados() {
        try {
            listEstado = ejbBusqEstLocal.buscarEstado();
            if (listEstado == null || listEstado.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoEstado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metodo que se ejecuta cuando se desea guardar un estado documento.
     */
    public void guardarEstado() {
        try {
            if (nombreEstado == null || nombreEstado.equals("")) {
                alert("Descripcion de estado es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }

            Integer codigo = ejbBusqEstLocal.obtenerCorreltivoEstado();
            estadoConst = new Estado();
            estadoConst.setEstado(nombreEstado.trim().toUpperCase());
            estadoConst.setIdestado(codigo);

            crud.guardarEntidad(estadoConst);
            alert("El estado documento se guardo exitosamente.",
                    FacesMessage.SEVERITY_INFO);
            this.idEstado = codigo;
        } catch (Exception ex) {
            Logger.getLogger(MttoEstado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metodo que se ejecuta cuando se desea actualizar un estado documento.
     */
    public void actualizarEstado() {
        try {

            if (this.dtEstado.getRowData() != null) {
                Estado est = this.listEstado.get(this.dtEstado.getRowIndex());
                crud.guardarEntidad(est);
                alert("Estado documento actualizado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
                this.estadoConst = null;
                est = null;
                this.listEstado = this.ejbBusqEstLocal.buscarEstado();
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoEstado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metodo que se ejecuta cuando se va a Eliminar un estado documento
     */
    public void eliminarEstado() {
        try {
            listEstado = ejbBusqEstLocal.buscarEstado();
            if (dtEstado.getRowData() != null) {
                estadoConst = listEstado.get(dtEstado.getRowIndex());
                System.out.println("estado.." + estadoConst);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoEstado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Metodo que se ejecuta cuando se esta editando la grilla del formulario
     * @param event
     **/
    public void eventoEdit(RowEditEvent event) {
        this.guardarEstado();
        alert("Registro modificado exitosamente.",
                FacesMessage.SEVERITY_INFO);
    }

    /**
     * Metodo que se ejecuta cuando se cancela la acción de edición en la grilla
     * del formulario
     *
     * @param event
     */
    public void eventoCancel(RowEditEvent event) {
        alert("Se ha cancelado la acción.",
                FacesMessage.SEVERITY_INFO);
//        System.out.println("event.getObject().." +event.getObject());
    }

    /**
     * Mensaje de alerta que se muestra en pantalla.
     *
     * @param mensaje Mensaje que quiere mostrar.
     * @param faces constantes definidas en FacesMessage tipos:
     * FacesMessage.SEVERITY_INFO informativo. FacesMessage.SEVERITY_ERROR
     * error. FacesMessage.SEVERITY_WARN adventencia.
     */
    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    //</editor-fold >

//<editor-fold  defaultstate="collapsed" desc="Getter y setter" >
    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public Estado getEstadoConst() {
        return estadoConst;
    }

    public void setEstadoConst(Estado estadoConst) {
        this.estadoConst = estadoConst;
    }

    public List<Estado> getListEstado() {
        return listEstado;
    }

    public void setListEstado(List<Estado> listEstado) {
        this.listEstado = listEstado;
    }

    public DataTable getDtEstado() {
        return dtEstado;
    }

    public void setDtEstado(DataTable dtEstado) {
        this.dtEstado = dtEstado;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedasEstadoLocal getEjbBusqEstLocal() {
        return ejbBusqEstLocal;
    }

    public void setEjbBusqEstLocal(BusquedasEstadoLocal ejbBusqEstLocal) {
        this.ejbBusqEstLocal = ejbBusqEstLocal;
    }
//</editor-fold >     
}

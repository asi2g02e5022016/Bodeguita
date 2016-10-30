/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Mesa;
import com.asi.restaurantbcd.modelo.Piso;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasPisosLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursalLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 /* @author siman */

@ManagedBean(name = "mttoMesa")
@ViewScoped
public class MttoMesa implements Serializable {

    //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /* busca beas de seciones activas */
    @Inject
    private SessionUsr sesion;

    /* variable de pk tabla impuesto*/
    private Integer idMesa;

    /* variable de fk tabla impuesto*/
    private Integer idPiso;

    /* variable de descripcion del tipo de impuesto*/
    private String nombre;
    
    /* constructor clase Piso  */
    private Mesa mesaConstructor;
    /*constructor clase Sucursal*/
    private Piso pisoConstructor = null;
    //atributo que muestra en pantalla listado de impuestos
    private List<Mesa> lstMesa;
    private List<Piso> lstPiso;
    
    private Integer x;
    
    private Integer y;

    /**
     * Bindin de DataTable que muestra companias.
     */
    private DataTable tablaMesa = new DataTable();
    /**
     * EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
     */
    @EJB
    private CrudBDCLocal crud;


       @EJB
    private BusquedasPisosLocal ejbBusqPiso;
    
    /**
     * Creates a new instance of MttoImp
     */
    public MttoMesa() {
    }

//    Metodo que se ejecuta al momento de 
//    cargar la pagina
    @PostConstruct
    public void postContruction() {
        try {
            
            setLstMesa(ejbBusqPiso.buscarMesa(sesion.getSucursal()));
            setLstPiso(ejbBusqPiso.buscarPiso(sesion.getSucursal()));
//           System.out.println(lstCompania);            
            System.out.println("load");
        } catch (Exception e) {
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }

    //</editor-fold >
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    //Limpiar variables
    public void limpiarPantalla() {
        idMesa = null;
        idPiso = null;
        nombre = null;
        x= null;
        y=null;
        tablaMesa = new DataTable();
        this.setLstMesa(new ArrayList<Mesa>());

    }

    // metodo para registrar un nuevo impuesto
    public void guardarMesa() {
        try {
            if (nombre == null || nombre.equals("")) {
                alert("Nombre de Mesa es obligatorio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }

            if (idPiso == null) {
                alert("Selecione una sucursal.", FacesMessage.SEVERITY_WARN);
                return;
            }
            setMesaConstructor(new Mesa());
            setPisoConstructor(new Piso());
            getPisoConstructor().setIdpiso(idPiso);
            getMesaConstructor().setIdpiso(getPisoConstructor());
            getMesaConstructor().setNombre(nombre.trim().toUpperCase());
            getMesaConstructor().setX(x);
            getMesaConstructor().setY(y);
            crud.guardarEntidad(getMesaConstructor());
            alert("Mesa ingresada exitosamente.",FacesMessage.SEVERITY_INFO);
            this.setLstMesa(this.ejbBusqPiso.buscarMesa(sesion.getSucursal()));
            this.setMesaConstructor(null);
        } catch (Exception ex) {

            Logger.getLogger(MttoPiso.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);

        }
    }

    //metodo para actualizar un impuesto
    public void actualizarMesa() {
        try {
            if (this.tablaMesa.getRowData() != null) {
                Mesa imp = this.getLstMesa().get(this.tablaMesa.getRowIndex());                
//                this.mesaConstructor = (Impuesto) tablaMesa.getRowData();
                setPisoConstructor(new Piso());
                getPisoConstructor().setIdpiso(imp.getIdpiso().getIdpiso());
                imp.setIdpiso(getPisoConstructor());
//                this.mesaConstructor.setIdcompania(pisoConstructor);
//                System.out.println(this.mesaConstructor);
                crud.guardarEntidad(imp);
                alert("Mesa actualizada exitosamente.",
                        FacesMessage.SEVERITY_INFO);
                this.setMesaConstructor(null);
                this.setPisoConstructor(null);
                imp = null;
                this.setLstMesa(this.ejbBusqPiso.buscarMesa(sesion.getSucursal()));

            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void eliminarPiso() {
        try {
            if (tablaMesa.getRowData() != null) {

                Mesa imp = this.getLstMesa().get(this.tablaMesa.getRowIndex());
                if (crud.eliminarEntidad(imp) == true) {
                    getLstMesa().remove(this.tablaMesa.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    this.setLstMesa(this.ejbBusqPiso.buscarMesa(sesion.getSucursal()));
                    this.setMesaConstructor(null);

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void buscarPiso() {
        try {
            this.setLstMesa(this.ejbBusqPiso.buscarMesa(sesion.getSucursal()));
            if (this.getLstMesa() == null || this.getLstMesa().isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void onEdit(RowEditEvent event) {
        this.actualizarMesa();
        alert("Registro modificado exitosamente.",
                FacesMessage.SEVERITY_INFO);
//        System.out.println("entro");
//        System.out.println("event.getObject().." + event.getObject());
    }

    public void onCancel(RowEditEvent event) {
        alert("Se ha cancelado la acción.",
                FacesMessage.SEVERITY_INFO);
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

    //<editor-fold  defaultstate="collapsed" desc="Getter y setter" >
    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public DataTable getTablaMesa() {
        return tablaMesa;
    }

    public void setTablaMesa(DataTable tablaMesa) {
        this.tablaMesa = tablaMesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Integer getIdPiso() {
        return idPiso;
    }

    public void setIdPiso(Integer idPiso) {
        this.idPiso = idPiso;
    }


    
    //</editor-fold>

    /**
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public Integer getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * @return the lstPiso
     */
    public List<Piso> getLstPiso() {
        return lstPiso;
    }

    /**
     * @param lstPiso the lstPiso to set
     */
    public void setLstPiso(List<Piso> lstPiso) {
        this.lstPiso = lstPiso;
    }

    /**
     * @return the lstMesa
     */
    public List<Mesa> getLstMesa() {
        return lstMesa;
    }

    /**
     * @param lstMesa the lstMesa to set
     */
    public void setLstMesa(List<Mesa> lstMesa) {
        this.lstMesa = lstMesa;
    }

    /**
     * @return the mesaConstructor
     */
    public Mesa getMesaConstructor() {
        return mesaConstructor;
    }

    /**
     * @param mesaConstructor the mesaConstructor to set
     */
    public void setMesaConstructor(Mesa mesaConstructor) {
        this.mesaConstructor = mesaConstructor;
    }

    /**
     * @return the pisoConstructor
     */
    public Piso getPisoConstructor() {
        return pisoConstructor;
    }

    /**
     * @param pisoConstructor the pisoConstructor to set
     */
    public void setPisoConstructor(Piso pisoConstructor) {
        this.pisoConstructor = pisoConstructor;
    }
    }

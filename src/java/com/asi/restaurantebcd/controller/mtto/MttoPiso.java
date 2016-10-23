/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Compania;
import com.asi.restaurantbcd.modelo.Impuesto;
import com.asi.restaurantbcd.modelo.Piso;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasMttoLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasPisosLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasSucursalLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author JAEM
 */
@ManagedBean(name = "mttoPiso")
@ViewScoped
public class MttoPiso implements Serializable {

    //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /* busca beas de seciones activas */
    @Inject
    private SessionUsr sesion;

    /* variable de pk tabla impuesto*/
    private Integer idPiso;

    /* variable de fk tabla impuesto*/
    private Integer idSucursal;

    /* variable de descripcion del tipo de impuesto*/
    private String nombre;
    
    /* constructor clase Piso  */
    private Piso pisoConstructor;
    /*constructor clase Sucursal*/
    private Sucursal sucursalConstructor = null;
    //atributo que muestra en pantalla listado de impuestos
    private List<Piso> lstPiso;
    private List<Sucursal> lstSucursal;

    /**
     * Bindin de DataTable que muestra companias.
     */
    private DataTable tablaPiso = new DataTable();
    /**
     * EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
     */
    @EJB
    private CrudBDCLocal crud;

    /**
     * EJB de busquedas de mantenimiento.
     */
    @EJB
    private BusquedasSucursalLocal ejbBusqMtto;

       @EJB
    private BusquedasPisosLocal ejbBusqPiso;
    
    /**
     * Creates a new instance of MttoImp
     */
    public MttoPiso() {
    }

//    Metodo que se ejecuta al momento de 
//    cargar la pagina
    @PostConstruct
    public void postContruction() {
        try {
            
            lstPiso = ejbBusqPiso.buscarPiso(sesion.getCompania());
            lstSucursal = ejbBusqMtto.buscarSucursal(sesion.getCompania());
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
        idPiso = null;
        idSucursal = null;
        nombre = null;

    }

    // metodo para registrar un nuevo impuesto
    public void guardarPiso() {
        try {
            if (nombre == null || nombre.equals("")) {
                alert("Nombre dePiso es obligatorio.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }

            if (idSucursal == null) {
                alert("Selecione una sucursal.", FacesMessage.SEVERITY_WARN);
                return;
            }
            pisoConstructor = new Piso();
            sucursalConstructor = new Sucursal();
            sucursalConstructor.setIdsucursal(idSucursal);
            pisoConstructor.setIdsucursal(sucursalConstructor);
            pisoConstructor.setNombre(nombre.trim().toUpperCase());
            crud.guardarEntidad(pisoConstructor);
            alert("Piso ingresado exitosamente.",FacesMessage.SEVERITY_INFO);
            this.lstPiso = this.ejbBusqPiso.buscarPiso(sesion.getCompania());
            this.pisoConstructor = null;
        } catch (Exception ex) {

            Logger.getLogger(MttoCompanias.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);

        }
    }

    //metodo para actualizar un impuesto
    public void actualizarImpuesto() {
        try {
            if (this.tablaPiso.getRowData() != null) {
                Piso imp = this.lstPiso.get(this.tablaPiso.getRowIndex());                
//                this.pisoConstructor = (Impuesto) tablaPiso.getRowData();
                sucursalConstructor = new Sucursal();
                sucursalConstructor.setIdsucursal(idSucursal);
                imp.setIdsucursal(sucursalConstructor);
//                this.pisoConstructor.setIdcompania(sucursalConstructor);
//                System.out.println(this.pisoConstructor);
                crud.guardarEntidad(imp);
                alert("Impuesto actualizado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
                this.pisoConstructor = null;
                this.sucursalConstructor = null;
                imp = null;
                this.lstPiso = this.ejbBusqPiso.buscarPiso(sesion.getCompania());

            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void eliminarImpuesto() {
        try {
            if (tablaPiso.getRowData() != null) {

                Piso imp = this.lstPiso.get(this.tablaPiso.getRowIndex());
                if (crud.eliminarEntidad(imp) == true) {
                    lstPiso.remove(this.tablaPiso.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    this.lstPiso = this.ejbBusqPiso.buscarPiso(sesion.getCompania());
                    this.pisoConstructor = null;

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
            this.lstPiso = this.ejbBusqPiso.buscarPiso(sesion.getCompania());
            if (this.lstPiso == null || this.lstPiso.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void onEdit(RowEditEvent event) {
        this.guardarPiso();
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
    public Integer getIdPiso() {
        return idPiso;
    }

    public void setIdPiso(Integer idPiso) {
        this.idPiso = idPiso;
    }

    public DataTable getTablaPiso() {
        return tablaPiso;
    }

    public void setTablaPiso(DataTable tablaPiso) {
        this.tablaPiso = tablaPiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Piso> getLstPiso() {
        return lstPiso;
    }

    public void setLstPiso(List<Piso> lstPiso) {
        this.lstPiso = lstPiso;
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

    public void setIdCompania(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }


    public Sucursal getSucursalConstructor() {
        return sucursalConstructor;
    }

    public void setSucursalConstructor(Sucursal sucursalConstructor) {
        this.sucursalConstructor = sucursalConstructor;
    }
    
    //</editor-fold>

}

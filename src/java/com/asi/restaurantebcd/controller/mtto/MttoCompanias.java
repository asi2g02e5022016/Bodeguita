/*
 * ManagedBeans de mantenimientos de companias.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Compania;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasMttoLocal;
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
 * @author samaelopez
 */
@ManagedBean(name = "mttoCompanias")
@ViewScoped
public class MttoCompanias implements Serializable {
    
    //<editor-fold  defaultstate="collapsed" desc="Inializar" >
    /**
     * Creates a new instance of MttoCompanias
     */
    public MttoCompanias() {
        
        
    }
    
    /**
     * Metodo   ue se ejecuta no mas cargar pagina.
     */
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
            buscarCompanias();
        } catch (Exception e) {
                alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
     //</editor-fold >
    
    //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /**Atributo que se muestra en pantalla el codigo de compania.*/
    private Integer idCompania;
      /**Atributo que se muestra en pantalla el codigo de compania.*/
    private String nombreCompania;
      /**Busca beans session activa.*/
    private SessionUsr sesion ;
    
      /**Entidad que guardara la compania.*/
    private Compania compania;
      /**Atributo que se muestra en pantalla la lista de companias.*/
    private List <Compania> lstCompanias;
    /**Bindin de DataTable que muestra companias.*/
    private DataTable tablaCompanias  =  new DataTable();
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
    private BusquedasMttoLocal ejbBusqMtto;
        //</editor-fold >
        
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
     /**
      * Metodo para limpiar informacion de pantalla.
      */
     public void limpiarPantalla() {
         lstCompanias = null;
         compania = null;
         nombreCompania = null;
         idCompania = null;
    }
     /**
      * Metodo que se ejecuta cuando se 
      * va a guardar una compania.
      */
     public void guardarCompanias() {
        try {
            if (nombreCompania == null || nombreCompania.equals("")) {
                alert("Descripcion de compania es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }
           
            Integer codigo = ejbBusqMtto.obtenerCorreltivoCompanias();
            compania = new Compania();
            compania.setCompania(nombreCompania.trim().toUpperCase());
            compania.setIdCompania(codigo);
            
            crud.guardarEntidad(compania);
            alert("La compania se guardo exitosamente.", 
                    FacesMessage.SEVERITY_INFO);
            this.idCompania = codigo;
        } catch (Exception ex) {
            Logger.getLogger(MttoCompanias.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     /**
      * Metodo que se ejecuta cuando se 
      * va a guardar una compania.
      */
     public void buscarCompanias() {
        try {
            lstCompanias =  ejbBusqMtto.buscarCompania();
            if (lstCompanias == null || lstCompanias.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCompanias.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     
     /**
      * Metodo que se ejecuta cuando se 
      * va a Actualizar una compania.
      */
     public void actualizarCompanias() {
        try {
                
            if (tablaCompanias.getRowData() != null) {
                compania =  (Compania) tablaCompanias.getRowData();
                System.out.println("compania.." +compania);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCompanias.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     /**
      * Metodo que se ejecuta cuando se 
      * va a Eliminar una compania.
      */
     public void eliminarCompanias() {
        try {
            lstCompanias =  ejbBusqMtto.buscarCompania();
         if (tablaCompanias.getRowData() != null) {
                compania =  lstCompanias.get(tablaCompanias.getRowIndex());
                System.out.println("compania.." +compania);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCompanias.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
     /**
      * Mensaje de alerta que se muestra en pantalla.
      * @param mensaje Mensaje que quiere mostrar.
      * @param faces constantes definidas en FacesMessage tipos:
      * FacesMessage.SEVERITY_INFO  informativo.
      * FacesMessage.SEVERITY_ERROR error.
      * FacesMessage.SEVERITY_WARN adventencia.
      */
    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje =  "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
        //</editor-fold >
     
    //<editor-fold  defaultstate="collapsed" desc="Getter y setter" >
    public Integer getIdCompania() {
        return idCompania;
    }

    public void setIdCompania(Integer idCompania) {
        this.idCompania = idCompania;
    }

    public DataTable getTablaCompanias() {
        return tablaCompanias;
    }

    public void setTablaCompanias(DataTable tablaCompanias) {
        this.tablaCompanias = tablaCompanias;
    }

    public String getNombreCompania() {
        return nombreCompania;
    }

    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }

    public List<Compania> getLstCompanias() {
        return lstCompanias;
    }

    public void setLstCompanias(List<Compania> lstCompanias) {
        this.lstCompanias = lstCompanias;
    } 
        //</editor-fold >

   
}

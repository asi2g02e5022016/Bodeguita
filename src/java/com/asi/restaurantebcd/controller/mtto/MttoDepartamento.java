 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;
import com.asi.restaurantbcd.modelo.Departamento;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedasMttoLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author renewezzo
 */
@ManagedBean(name = "mttoDepartamento")
@ViewScoped
public class MttoDepartamento implements Serializable{

    /**
     * Creates a new instance of MttoDepartamento
     */
    public MttoDepartamento() {
    }
    //    Metodo que se ejecuta al momento de 
    //    cargar la pagina
    
    @PostConstruct
    public void postContruction() { 
        try {
//            sesion = Utilidades.findBean("sessionUsr");
//            if (sesion == null) {
//                alert("Debe Iniciar Sesion", FacesMessage.SEVERITY_FATAL);
//                FacesContext.getCurrentInstance().getViewRoot().
//                        getViewMap().clear();
//                String url = "http://localhost:8080/RestaurantBDC";
//                FacesContext.getCurrentInstance().getExternalContext().
//                        redirect(url);
//            }
          lstDepartamento = this.ejbBusqMtto.buscarDepartamento();
//            lstCompania = this.ejbBusqMtto.buscarCompania();
         
//           System.out.println(lstCompania);            
            System.out.println("load");
        } catch (Exception e) {
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
        
    }
      //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /* busca beas de seciones activas */
    private SessionUsr sesion;

    /* variable de pk tabla departamento*/
    private Integer idDepartamento;
    
    
    /* variable de fk tabla departamento*/
    private Integer idCompania;

    /* variable de descripcion del departamento*/
    private String departamento;
    

    /* variable que contiene la fecha de creacion de departamento*/
    private Date fechaCreacion;
    /* constructor clase departamento  */
    private Departamento departamentoConstructor;
    /*constructor clase compania*/
//    private Compania companiaConstructor = null;
    
    
    //atributo que muestra en pantalla listado de impuestos
    private List<Departamento> lstDepartamento;
//    private List<Compania> lstCompania;
  
    /**
     * Bindin de DataTable que muestra companias.
     */
    private DataTable tablaDepartamento = new DataTable();
    /**
     * EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
     */
    @EJB
    private CrudBDCLocal crud;

    /**
     * EJB de busquedas de mantenimiento.
     */
    @EJB
    private BusquedasMttoLocal ejbBusqMtto;
    
    //</editor-fold >
    
    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    //Limpiar variables
    public void limpiarPantalla() {
        idDepartamento = null;
       idCompania = null;
        departamento = null;
        fechaCreacion = null;
       lstDepartamento = null;
//        lstCompania = null;
       }   
    
    //metodo para guardar un nuevo departamento
    public void guardarDepartamento() {
        try {
            if (departamento == null || departamento.equals("")) {
                alert("Descripcion de departamento es obligatoria.",
                        FacesMessage.SEVERITY_INFO);
                return;
            }

//            if (idCompania == null) {
//                alert("Selecione una compania.", FacesMessage.SEVERITY_WARN);
//                return;
//            }
              System.out.println("entro");
            departamentoConstructor = new Departamento();
//            companiaConstructor = new Compania();
//            
//            companiaConstructor.setIdcompania(idCompania);
//            departamentoConstructor.setIdcompania(companiaConstructor);
            departamentoConstructor.setDepartamento(departamento.trim().toUpperCase());
            departamentoConstructor.setFechacreacion(new Date());
            crud.guardarEntidad(this.departamentoConstructor);
            
            alert("Departamento ingresado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
            this.departamentoConstructor = null;
            this.departamento = null;
//            this.companiaConstructor = null;
            this.lstDepartamento = this.ejbBusqMtto.buscarDepartamento();
           
        } catch (Exception ex) {

            Logger.getLogger(MttoDepartamento.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);

        }
    }
    
    ///////////////////////////////////////
    public void actualizarDepartamento(RowEditEvent event) {
        
         try {
            if (event.getObject() != null) {
                Departamento imp = (Departamento) event.getObject();                
                 crud.guardarEntidad(imp);
                
                alert("Impuesto actualizado exitosamente.",
                        FacesMessage.SEVERITY_INFO);
                this.departamentoConstructor = null;
                this.departamento = null;
            imp = null;
           this.lstDepartamento= this.ejbBusqMtto.buscarDepartamento();
            }
      }catch (Exception ex) {
            Logger.getLogger(MttoImp.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert("error aqui ", FacesMessage.SEVERITY_ERROR);
        }
         }
    
    
    // eliminar departamentos
     public void eliminarDepartamento() {
        try {
                       
            if (tablaDepartamento.getRowData() != null) {
                Departamento imp = this.lstDepartamento.get(this.tablaDepartamento.getRowIndex());
                if (crud.eliminarEntidad(imp) == true) {
                    lstDepartamento.remove(this.tablaDepartamento.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    
                     this.departamentoConstructor = null;

            this.lstDepartamento = this.ejbBusqMtto.buscarDepartamento();


                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoDepartamento.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }
     
     public void buscarDepartamento() {
        try {
            this.lstDepartamento = this.ejbBusqMtto.buscarDepartamento();
            if (this.lstDepartamento == null || this.lstDepartamento.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoDepartamento.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

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
        
    //<editor-fold  defaultstate="collapsed" desc="Getter and Setter" >
    
    
    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdCompania() {
        return idCompania;
    }

    public void setIdCompania(Integer idCompania) {
        this.idCompania = idCompania;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Departamento getDepartamentoConstructor() {
        return departamentoConstructor;
    }

    public void setDepartamentoConstructor(Departamento departamentoConstructor) {
        this.departamentoConstructor = departamentoConstructor;
    }


 

    public List<Departamento> getLstDepartamento() {
        return lstDepartamento;
    }

    public void setLstDepartamento(List<Departamento> lstDepartamento) {
        this.lstDepartamento = lstDepartamento;
    }


    public DataTable getTablaDepartamento() {
        return tablaDepartamento;
    }

    public void setTablaDepartamento(DataTable tablaDepartamento) {
        this.tablaDepartamento = tablaDepartamento;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedasMttoLocal getEjbBusqMtto() {
        return ejbBusqMtto;
    }

    public void setEjbBusqMtto(BusquedasMttoLocal ejbBusqMtto) {
        this.ejbBusqMtto = ejbBusqMtto;
    }
    
    //</editor-fold >
    
}

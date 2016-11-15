/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Configuracion;
import com.asi.restaurantbcd.modelo.Programaciondetalle;
import com.asi.restaurantbcd.modelo.Programaciontareas;
import com.asi.restaurantbcd.modelo.Tarea;
import com.asi.restaurantebcd.negocio.base.BusquedaProgramacionTareasLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author JAEM
 *
 */
@ManagedBean(name = "mttoProgramacionTareas")
@ViewScoped

public class MttoProgramacionTareas implements Serializable {

    /**
     * Creates a new instance of MttoProgramacionTareas
     */
    public MttoProgramacionTareas() {
    }
//<editor-fold  defaultstate="collapsed" desc="LocalVariables">
    private Integer idProgramacion;
    private Date inicio;
    private Date fin;
    private Double frecuencia;

    //var formulario
    private Boolean deshabilitarInicio = true;
    private Boolean deshabilitarFin = true;
    private Boolean deshabilitarFrecuencia = true;
    private Boolean deshabilitarBtnTarea = true;
    private Boolean mostrarGuardar = false;
    private Boolean mostrarBuscar = true;
    private DataTable dtDetalle = new DataTable();

    private Boolean actualizacion = null;

    private Integer idTarea;
    private String nombreTarea;
    private Integer host;
    private String nombreHost;
    private String parametros;
    private Integer orden;

    private List<Programaciontareas> lstPrograTar;
    private List<Tarea> lstTarea;
    private List<Programaciondetalle> lstDetalle;
    private List<Configuracion> lstConfig;
    private Programaciontareas constructorProgTar;
    private Programaciondetalle constructorProgDet;
    private Tarea constructorTarea;
    private Configuracion constructorConfig;

    /**
     * EJB Quecon tiene metodos utilitarios como: Guardar, Eliminar, Buscar...
     */
    @EJB
    private CrudBDCLocal crud;
    /**
     * EJB de busquedas de mantenimiento.
     */
    @EJB
    private BusquedaProgramacionTareasLocal ejbBusqProgTar;
//</editor-fold>
//<editor-fold  defaultstate="collapsed" desc="Metodos">

    public void onRowSelectProg(SelectEvent event) {
        System.out.print("entro al select");
        Programaciontareas proTar = (Programaciontareas) event.getObject();
        if (proTar != null) {
            nuevo();
            System.out.print("no es nulo");
            actualizacion = true;
            idProgramacion = proTar.getIdprogramacion();
            inicio = proTar.getInicio();
            fin = proTar.getFin();
            frecuencia = proTar.getFrecuencia();
            lstDetalle = proTar.getProgramaciondetalleList();

        }
    }

    public void buscarTareasProg() {
        buscarProgramacionTareas();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoBusqTareas').show();");
    }

    public void llenarLstTarea() {
        try {
            lstTarea = ejbBusqProgTar.buscarTarea();
        } catch (Exception ex) {
            Logger.getLogger(MttoProgramacionTareas.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }

    public void LlenarLstConfiguraciones() {
        try {
            lstConfig = ejbBusqProgTar.buscarConfiguracion();
        } catch (Exception ex) {
            Logger.getLogger(MttoProgramacionTareas.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void nuevo() {
        deshabilitarInicio = false;
        deshabilitarFin = false;
        deshabilitarFrecuencia = false;
        deshabilitarBtnTarea = false;
        mostrarGuardar = true;
        mostrarBuscar = false;
        actualizacion = false;

        limpiarDet();
        limpiarEnc();
    }

    public void mostrarDialogoDetalle() {
        System.out.print("entro al mostrar");
        LlenarLstConfiguraciones();
        llenarLstTarea();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoTareas').show();");
    }

    public void limpiarEnc() {
        idProgramacion = null;
        inicio = null;
        fin = null;
        frecuencia = null;
        lstPrograTar = null;
        lstDetalle = null;

    }

    public void limpiarDet() {
        orden = null;
        idTarea = null;
        host = null;
        parametros = null;
    }

    public void eliminarDetalle() {
        try {
            if (actualizacion == false) {
                if (dtDetalle.getRowData() != null) {
                    System.out.print(actualizacion);
                    System.out.print(dtDetalle.getRowData());
                    lstDetalle.remove(this.dtDetalle.getRowIndex());
                    dtDetalle.setValue(lstDetalle);
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    limpiarDet();
                }
            } else if (dtDetalle.getRowData() != null) {
                Programaciondetalle imp = this.lstDetalle.get(this.dtDetalle.getRowIndex());
                if (crud.eliminarEntidad(imp) == true) {
                    lstDetalle.remove(this.dtDetalle.getRowIndex());
                    alert("Registro eliminado exitosamente.",
                            FacesMessage.SEVERITY_INFO);
                    this.lstDetalle = this.ejbBusqProgTar.buscarProgramacionDetalle(idProgramacion);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoProgramacionTareas.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void agregarDetalle() {
        try {
            constructorProgDet = new Programaciondetalle();
            constructorProgTar = new Programaciontareas();
            constructorConfig = new Configuracion();    
            
            constructorConfig.setIdconfiguracion(host);
            nombreHost=ejbBusqProgTar.buscarNombreHost(host);
            constructorConfig.setDescripcion(nombreHost);
            
            constructorTarea = new Tarea();
            constructorTarea.setIdtarea(idTarea);
            nombreTarea = ejbBusqProgTar.buscarNombreTarea(idTarea);
            
            constructorTarea.setNombre(nombreTarea);            
            constructorProgDet.setIdtarea(constructorTarea);          
            constructorProgDet.setHost(constructorConfig);
            constructorProgDet.setParametros(parametros);
            constructorProgDet.setOrden(orden);
            if (actualizacion == false) {
                if (constructorProgDet != null) {
                    if (lstDetalle == null) {
                        lstDetalle = new ArrayList<>();
                        lstDetalle.add(constructorProgDet);
                    } else {
                        lstDetalle.add(constructorProgDet);
                    }
                } else {
                    alert("Error por favor comunicarse con el administrador de sistemas.",
                            FacesMessage.SEVERITY_INFO);
                }
            } else if (actualizacion == true) {
                constructorProgTar.setIdprogramacion(idProgramacion);
                constructorProgDet.setIdprogramacion(constructorProgTar);
                crud.guardarEntidad(constructorProgDet);
                this.lstDetalle = this.ejbBusqProgTar.buscarProgramacionDetalle(idProgramacion);
            }
            limpiarDet();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogoTareas').hide();");
        } catch (Exception ex) {
            Logger.getLogger(MttoProgramacionTareas.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void actualizarProgramacionTareas(RowEditEvent event) {
        try {
            Programaciontareas prog = (Programaciontareas) event.getObject();
            crud.guardarEntidad(prog);
            alert("Registro actualizado exitosamente.",
                    FacesMessage.SEVERITY_INFO);
            prog = null;

            buscarProgramacionTareas();
        } catch (Exception ex) {
            Logger.getLogger(MttoTarea.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void guardarProgramacionTareas() {
        try {
            if (inicio == null) {
                alert("Fecha inicio no puede quedar vacio.",
                        FacesMessage.SEVERITY_WARN);
            }
            if (fin == null) {
                alert("Fecha fin no puede quedar vacio.",
                        FacesMessage.SEVERITY_WARN);
            }
            if (frecuencia == null) {
                alert("El campo frecuencia no puede quedar vacio.",
                        FacesMessage.SEVERITY_WARN);
            }
            if (actualizacion == false) {
                idProgramacion = ejbBusqProgTar.obtenerCorreltivoAjuste(Programaciontareas.class);
                if (idProgramacion != null && idProgramacion > 0) {
                    constructorProgTar = new Programaciontareas();
                    constructorProgTar.setIdprogramacion(idProgramacion);
                    constructorProgTar.setInicio(inicio);
                    constructorProgTar.setFin(fin);
                    constructorProgTar.setFrecuencia(frecuencia);
                    int i = 0;
                    for (Programaciondetalle prDet : lstDetalle) {
                        i++;
                        prDet.setIdprogramacion(constructorProgTar);
                    }
                    constructorProgTar.setProgramaciondetalleList(lstDetalle);
                    constructorProgTar.setProgramaciondetalleList(lstDetalle);
                    crud.guardarEntidad(constructorProgTar);
                    alert("Registro ingresado exitosamente", FacesMessage.SEVERITY_INFO);
                    limpiarDet();
                    limpiarEnc();
                    lstDetalle = null;
                    lstPrograTar = null;
                    mostrarGuardar = false;
                }
            } else if (actualizacion == true) {
                System.out.print("entro en el actualizar");
                constructorProgTar = new Programaciontareas();
                constructorProgTar.setIdprogramacion(idProgramacion);
                constructorProgTar.setInicio(inicio);
                constructorProgTar.setFin(fin);
                constructorProgTar.setFrecuencia(frecuencia);
                if (dtDetalle.getRowData() != null) {
                    constructorProgTar.setProgramaciondetalleList(lstDetalle);
                    crud.guardarEntidad(constructorProgTar);
                    alert("Registro ingresado exitosamente", FacesMessage.SEVERITY_INFO);
                    limpiarDet();
                    limpiarEnc();
                    lstDetalle = null;
                    lstPrograTar = null;
                    mostrarGuardar = false;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoProgramacionTareas.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void buscarProgramacionTareas() {
        try {
            lstPrograTar = ejbBusqProgTar.buscarProgramacionTareas();
            if (lstPrograTar == null || lstPrograTar.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);

            }

        } catch (Exception ex) {
            Logger.getLogger(MttoProgramacionTareas.class
                    .getName())
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
//</editor-fold >
//<editor-fold  defaultstate="collapsed" desc="Get y Set">

    public Integer getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Integer idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Boolean getDeshabilitarInicio() {
        return deshabilitarInicio;
    }

    public void setDeshabilitarInicio(Boolean deshabilitarInicio) {
        this.deshabilitarInicio = deshabilitarInicio;
    }

    public Boolean getDeshabilitarFin() {
        return deshabilitarFin;
    }

    public void setDeshabilitarFin(Boolean deshabilitarFin) {
        this.deshabilitarFin = deshabilitarFin;
    }

    public Boolean getDeshabilitarFrecuencia() {
        return deshabilitarFrecuencia;
    }

    public void setDeshabilitarFrecuencia(Boolean deshabilitarFrecuencia) {
        this.deshabilitarFrecuencia = deshabilitarFrecuencia;
    }

    public Boolean getDeshabilitarBtnTarea() {
        return deshabilitarBtnTarea;
    }

    public void setDeshabilitarBtnTarea(Boolean deshabilitarBtnTarea) {
        this.deshabilitarBtnTarea = deshabilitarBtnTarea;
    }

    public Boolean getMostrarGuardar() {
        return mostrarGuardar;
    }

    public void setMostrarGuardar(Boolean mostrarGuardar) {
        this.mostrarGuardar = mostrarGuardar;
    }

    public Boolean getMostrarBuscar() {
        return mostrarBuscar;
    }

    public void setMostrarBuscar(Boolean mostrarBuscar) {
        this.mostrarBuscar = mostrarBuscar;
    }

    public List<Configuracion> getLstConfig() {
        return lstConfig;
    }

    public void setLstConfig(List<Configuracion> lstConfig) {
        this.lstConfig = lstConfig;
    }

    public Double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public DataTable getDtDetalle() {
        return dtDetalle;
    }

    public void setDtDetalle(DataTable dtDetalle) {
        this.dtDetalle = dtDetalle;
    }

    public String getNombreHost() {
        return nombreHost;
    }

    public void setNombreHost(String nombreHost) {
        this.nombreHost = nombreHost;
    }

    public List<Programaciontareas> getLstPrograTar() {
        return lstPrograTar;
    }

    public void setLstPrograTar(List<Programaciontareas> lstPrograTar) {
        this.lstPrograTar = lstPrograTar;
    }

    public Programaciontareas getConstructorProgTar() {
        return constructorProgTar;
    }

    public void setConstructorProgTar(Programaciontareas constructorProgTar) {
        this.constructorProgTar = constructorProgTar;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public Tarea getConstructorTarea() {
        return constructorTarea;
    }

    public void setConstructorTarea(Tarea constructorTarea) {
        this.constructorTarea = constructorTarea;
    }

    public Programaciondetalle getConstructorProgDet() {
        return constructorProgDet;
    }

    public void setConstructorProgDet(Programaciondetalle constructorProgDet) {
        this.constructorProgDet = constructorProgDet;
    }

    public List<Programaciondetalle> getLstDetalle() {
        return lstDetalle;
    }

    public void setLstDetalle(List<Programaciondetalle> lstDetalle) {
        this.lstDetalle = lstDetalle;
    }

    public Configuracion getConstructorConfig() {
        return constructorConfig;
    }

    public void setConstructorConfig(Configuracion constructorConfig) {
        this.constructorConfig = constructorConfig;
    }

    public List<Tarea> getLstTarea() {
        return lstTarea;
    }

    public void setLstTarea(List<Tarea> lstTarea) {
        this.lstTarea = lstTarea;
    }

    public BusquedaProgramacionTareasLocal getEjbBusqProgTar() {
        return ejbBusqProgTar;
    }

    public void setEjbBusqProgTar(BusquedaProgramacionTareasLocal ejbBusqProgTar) {
        this.ejbBusqProgTar = ejbBusqProgTar;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Boolean actualizacion) {
        this.actualizacion = actualizacion;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Integer getHost() {
        return host;
    }

    public void setHost(Integer host) {
        this.host = host;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }
    //</editor-fold>

}

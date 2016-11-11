/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Medida;
import com.asi.restaurantebcd.negocio.base.BusquedasMedidasLocal;
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
import org.primefaces.component.datatable.DataTable;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Quincho
 */
@ManagedBean(name = "mttoMedidas")
@ViewScoped
public class MttoMedidasBean implements Serializable {

    //<editor-fold  defaultstate="collapsed" desc="Inializar" >
    /**
     * Creates a new instance of MttoCompanias
     */
    public MttoMedidasBean() {
    }

    @PostConstruct
    public void postConstruction() {
        try {

            buscarMedidas();
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }

    //</editor-fold>
    
    //<editor-fold  defaultstate="collapsed" desc="Variables locales" >
    /**
     * Creates a new instance of MttoMedidas
     */
    @EJB
    private BusquedasMedidasLocal ejbBusquedasMedidas;

    @EJB
    private CrudBDCLocal crudBDC;

    private int idmedida;
    private String medida;
    private String referencia;
    private double conversion;
    private Medida medidaConst;
    private List<Medida> lstMedida;
    //private DataTable dtMedidas  =  new DataTable();
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    /**
     * Creates a new instance of MttoCompanias
     */
    
    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void limpiarPantalla() {
        try {
            idmedida = 0;
            medida = null;
            referencia = null;
            conversion = 0;
            lstMedida = null;
        } catch (Exception ex) {
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(MttoMedidasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarMedidas() {
        try {
            if (medida == null || medida.equals("")) {
                alert("El nombre medida es obligatorio", FacesMessage.SEVERITY_ERROR);
                return;
            }           

            medidaConst = new Medida();
            medidaConst.setMedida(medida);           
            crudBDC.guardarEntidad(medidaConst);
            alert("La medida se ha guardado con exito", FacesMessage.SEVERITY_INFO);
            buscarMedidas();
            
        } catch (Exception ex) {
            alert("Error: " + ex.getMessage(), FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(MttoMedidasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarMedidas() {
        try {
            lstMedida = ejbBusquedasMedidas.buscarMedida();
            if (lstMedida == null || lstMedida.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoMedidasBean.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }

    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Getters and setters" >
    /**
     * Creates a new instance of MttoCompanias
     */
    
    public void onEdit(RowEditEvent event) {
        System.out.println("event.getObject().." + event.getObject());
    }

    public void onCancel(RowEditEvent event) {
        System.out.println("event.getObject().." + event.getObject());
    }

    public BusquedasMedidasLocal getEjbBusquedasMedidas() {
        return ejbBusquedasMedidas;
    }

    public void setEjbBusquedasMedidas(BusquedasMedidasLocal ejbBusquedasMedidas) {
        this.ejbBusquedasMedidas = ejbBusquedasMedidas;
    }

    public CrudBDCLocal getCrudBDC() {
        return crudBDC;
    }

    public void setCrudBDC(CrudBDCLocal crudBDC) {
        this.crudBDC = crudBDC;
    }

    public int getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(int idmedida) {
        this.idmedida = idmedida;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getConversion() {
        return conversion;
    }

    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    public Medida getMedidaConst() {
        return medidaConst;
    }

    public void setMedidaConst(Medida medidaConst) {
        this.medidaConst = medidaConst;
    }

    public List<Medida> getLstMedida() {
        return lstMedida;
    }

    public void setLstMedida(List<Medida> lstMedida) {
        this.lstMedida = lstMedida;
    }
    //</editor-fold>
    
}

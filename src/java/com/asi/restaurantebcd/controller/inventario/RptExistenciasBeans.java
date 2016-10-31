/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.inventario;

import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantebcd.negocio.base.BusquedasExistenciasLocal;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author samaelopez
 */
@ManagedBean(name = "rptExistenciasBeans")
@ViewScoped
public class RptExistenciasBeans implements Serializable {
            @EJB
    private BusquedasExistenciasLocal ejbBucdaExistencias;
 private List<Sucursal> lstSucursal ;
   private int codsucursal;
    /**
     * Creates a new instance of RptExistenciasBeans
     */
    public RptExistenciasBeans() {
    }
    @PostConstruct
    public void iniciar() {
        try {
            lstSucursal = ejbBucdaExistencias.buscarSucursal();
        } catch (Exception ex) {
            Logger.getLogger(RptExistenciasBeans.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

    public void imprimirReporteCompra() {

       try { 
            Map param = new HashMap();
            param.put("CODSUC", String.valueOf(codsucursal));
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc
                               .getExternalContext().getRequest();
            String url = request.getContextPath() + "/Reporte";
             request.getSession().setAttribute("datasourse","jdbc/ifbc");
            request.getSession().setAttribute("url",
              "/com/asi/restaurantebcd/reportes/inventario/"
                    +"RptExistencias.jasper");
            request.getSession().setAttribute("format","PDF");
            request.getSession().setAttribute("parameters", param);
                        RequestContext context = RequestContext.getCurrentInstance();
             context.execute(             "window.open('" + url
                   + "','Rpt','location=0,menubar=0,resizable=1,"
                   + "status=0,toolbar=0');");
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    
    public void alert(String mensaje, FacesMessage.Severity faces) {
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje);
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    
    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public int getCodsucursal() {
        return codsucursal;
    }

    public void setCodsucursal(int codsucursal) {
        this.codsucursal = codsucursal;
    }
    
    
    

    
    
}

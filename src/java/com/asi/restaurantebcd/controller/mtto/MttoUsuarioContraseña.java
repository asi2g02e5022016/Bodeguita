/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
/**
 *
 * @author Luis
 */



@ManagedBean (name = "mttoUsuarioContraseña")
@ViewScoped
public class MttoUsuarioContraseña implements Serializable{

    /**
     * Creates a new instance of MttoUsuarioContraseña
     */
    public MttoUsuarioContraseña() {
    }
    
//<editor-fold  defaultstate="collapsed" desc="Variables" >
    private Boolean actualizar;
    private AccordionPanel formPanel = new AccordionPanel();
    private Integer idUsuario;
    private String nombreUsuario;
    private String contraseña;
    private Usuario contraseñaConstructor;
    
    private List<Usuario> lstUsuario;
    
    private DataTable tableUsuario = new DataTable();
    //</editor-fold >
    
    
//<editor-fold  defaultstate="collapsed" desc="Getter y setter"  >    
    public Boolean getActualizar(){
        return actualizar;
        
    }

    public void setActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
    }

    public AccordionPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(AccordionPanel formPanel) {
        this.formPanel = formPanel;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Usuario getcontraseñaConstructor() {
        return contraseñaConstructor;
    }

    public void setcontaseñaConstructor(Usuario contraseñaConstructor) {
        this.contraseñaConstructor = contraseñaConstructor;
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public DataTable getTableUsuario() {
        return tableUsuario;
    }



    public void setTableUsuario(DataTable tableUsuario) {
        this.tableUsuario = tableUsuario;
    }    

//</editor-fold >
//<editor-fold  defaultstate="collapsed" desc="Metodos"  >
    public void limpiarPantalla() {
        idUsuario = null;
        nombreUsuario = null;
        contraseña = null;
        this.tableUsuario = null;
        this.lstUsuario = null;
        
        this.tableUsuario = null;
    }
    
    public void insertarUsuarioContraseña(){
        System.out.println("entro");
        
    }
    
    public void guardarUsuarioContraseña(){
        if (idUsuario == null || idUsuario.equals("")){
            alert("Selecione un usuario.", FacesMessage.SEVERITY_INFO);
            return;
        }
        
        if (nombreUsuario == null || nombreUsuario.equals("")){
            alert("Selecione un usuario.", FacesMessage.SEVERITY_INFO);
            return;
        }
        
        if (contraseña == null || contraseña.equals("")){
            alert("El Campo Contraseña no puede estar vacio", FacesMessage.SEVERITY_INFO);
            return;
        }
     
     //contraseñaConstructor.setIdusuario(idUsuario.trim());
        
    }
    
//</editor-fold >

    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Cliente;
import com.asi.restaurantebcd.negocio.base.BusquedasCliente;
import com.asi.restaurantebcd.negocio.base.BusquedasClienteLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Luis
 */
@Named(value = "mttoCliente")
@Dependent
public class MttoCliente implements Serializable{

    @EJB
    private BusquedasClienteLocal busquedasCliente;

    @EJB
    private CrudBDCLocal crudBDC;
    

    /**
     * Creates a new instance of MttoCliente
     */
    public MttoCliente() {
        }
    
        
        private Integer idCliente;
        
        private String nomCliente;
        
        private String apeCliente; 
        
        private String dirCliente;
        
        private String telCliente;
        
        private String nitCliente;
        
        private String nrcCliente;
        
        private String duiCliente;
        
        private String exCliente;
        
        private Cliente cliente;
        
        private List <Cliente> listaCliente;
        
        private DataTable dtCliente = new DataTable();
        
        public void limpiarpantalla(){
        
            idCliente = null;
            nomCliente = null;
            apeCliente = null;
            dirCliente = null;
            telCliente = null;
            nitCliente = null;
            nrcCliente = null;
            duiCliente = null;
            exCliente = null;
            listaCliente = null;
            dtCliente = new DataTable();
        }
        
        public void guardarnomCliente(){
        try {
            if(nomCliente == null || nomCliente.equals("") ){
                alert("Nombre de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            cliente = new Cliente();
            cliente.setNombre(nomCliente);
            crudBDC.guardarEntidad(cliente);
            alert("El nombre se ha guardado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        }
        

        public void guardarapeCliente(){
        try {
            if(apeCliente == null || apeCliente.equals("") ){
                alert("Apellido de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            cliente = new Cliente();
            cliente.setApellido(apeCliente);
            crudBDC.guardarEntidad(cliente);
            alert("El apellido se ha guardado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        }
        
        
        public void guardardirCliente(){
        try {
            if(dirCliente == null || dirCliente.equals("") ){
                alert("Direccion de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            cliente = new Cliente();
            cliente.setDireccion(dirCliente);
            crudBDC.guardarEntidad(cliente);
            alert("La Direccion se ha guardado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        }
        
        public void guardartelCliente(){
        try {
            if(telCliente == null || telCliente.equals("") ){
                alert("Telefono de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            cliente = new Cliente();
            cliente.setTelefono(telCliente);
            crudBDC.guardarEntidad(cliente);
            alert("El telefono se ha guardado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        }
        
        public void guardarnitCliente(){
        try {
            if(nitCliente == null || nitCliente.equals("") ){
                alert("Telefono de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            cliente = new Cliente();
            cliente.setNit(nitCliente);
            crudBDC.guardarEntidad(cliente);
            alert("El telefono se ha guardado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        }
        
        public void guardarnrcCliente(){
        try {
            if(nrcCliente == null || nrcCliente.equals("") ){
                alert("NRC de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            cliente = new Cliente();
            cliente.setNrc(nrcCliente);
            crudBDC.guardarEntidad(cliente);
            alert("El NRC se ha guardado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        }
        
        public void guardarduiCliente(){
        try {
            if(duiCliente == null || duiCliente.equals("") ){
                alert("DUI de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            cliente = new Cliente();
            cliente.setDui(duiCliente);
            crudBDC.guardarEntidad(cliente);
            alert("El DUI se ha guardado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        }
        
        public void guardarexCliente(){
        try {            
            cliente = new Cliente();
            cliente.setDui(duiCliente);
            crudBDC.guardarEntidad(cliente);
            alert("El DUI se ha guardado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        }

        
public void buscarCliente(){
        try {
            listaCliente = busquedasCliente.buscarCliente();
            if (listaCliente == null || listaCliente.isEmpty()){
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }        


        

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getApeCliente() {
        return apeCliente;
    }

    public void setApeCliente(String apeCliente) {
        this.apeCliente = apeCliente;
    }

    public String getDirCliente() {
        return dirCliente;
    }

    public void setDirCliente(String dirCliente) {
        this.dirCliente = dirCliente;
    }

    public String getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(String telCliente) {
        this.telCliente = telCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getNrcCliente() {
        return nrcCliente;
    }

    public void setNrcCliente(String nrcCliente) {
        this.nrcCliente = nrcCliente;
    }

    public String getDuiCliente() {
        return duiCliente;
    }

    public void setDuiCliente(String duiCliente) {
        this.duiCliente = duiCliente;
    }

    public String getExCliente() {
        return exCliente;
    }

    public void setExCliente(String exCliente) {
        this.exCliente = exCliente;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public DataTable getDtCliente() {
        return dtCliente;
    }

    public void setDtCliente(DataTable dtCliente) {
        this.dtCliente = dtCliente;
    }
        
        
     private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje =  "-";
        }
        FacesMessage message = new FacesMessage(faces, "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
        
    
    
}

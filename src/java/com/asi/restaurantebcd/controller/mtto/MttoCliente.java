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
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Luis
 */
@ManagedBean(name = "mttoCliente")
@ViewScoped
public class MttoCliente implements Serializable{
    
    public MttoCliente() {
    }

    
    

    /**
     * Creates a new instance of MttoCliente
     */
    
    

            
    
    //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /**
     * Busca beans session activa.
     */
        private AccordionPanel formPanel = new AccordionPanel();
        
        private Integer idCliente;
        
        private String nomCliente;
        
        private String apeCliente; 
        
        private String dirCliente;
        
        private String telCliente;
        
        private String nitCliente;
        
        private String nrcCliente;
        
        private String duiCliente;
        
        private String exCliente;
        
        private Cliente clienteConstructor;
        
        
        private List <Cliente> listaCliente;
        
        private DataTable tablaCliente = new DataTable();
        
            @EJB
            private CrudBDCLocal crudBDC;

            @EJB
            private BusquedasClienteLocal ejbBusqMtto;


        
        
        //</editor-fold >
        



        

//<editor-fold  defaultstate="collapsed" desc="Getter y setter"  >

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
//
//    public DataTable getDtCliente() {
//        return dtCliente;
//    }
//
//    public void setDtCliente(DataTable dtCliente) {
//        this.dtCliente = dtCliente;
//    }
        
//</editor-fold > 
    
    //<editor-fold  defaultstate="collapsed" desc="Metodos"  >
    
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
            
        }
     
    
    public void insertarCliente() {
        try {
            System.out.println("entro");
//            if (this.tablaEmpleado.getRowData() != null) {
            if (idCliente != null) {
                System.out.println("actualizar");
                this.acutalizarCliente();
            } else {
                System.out.println("nuevoRegistro");
                this.guardarCliente();
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoEmpleado.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void guardarCliente(){
        try {
            if (idCliente == null || idCliente.equals("")){
                alert("Seleccione el cliente", FacesMessage.SEVERITY_INFO);
                return;
            }
            if(nomCliente == null || nomCliente.equals("") ){
                alert("Nombre de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            if(apeCliente == null || apeCliente.equals("") ){
                alert("Apellido de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            if(dirCliente == null || dirCliente.equals("") ){
                alert("Direccion de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            if(telCliente == null || telCliente.equals("") ){
                alert("Telefono de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            if(nitCliente == null || nitCliente.equals("") ){
                alert("Telefono de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            if(nrcCliente == null || nrcCliente.equals("") ){
                alert("NRC de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            if(duiCliente == null || duiCliente.equals("") ){
                alert("DUI de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            
            clienteConstructor = new Cliente();
            
            clienteConstructor.setNombre(nomCliente.trim().toUpperCase());
            clienteConstructor.setApellido(apeCliente.trim().toUpperCase());
            clienteConstructor.setTelefono(telCliente);
            clienteConstructor.setDireccion(dirCliente.trim().toUpperCase());
            clienteConstructor.setDui(duiCliente);
            clienteConstructor.setNrc(nrcCliente);
            clienteConstructor.setNit(nomCliente);
            
            crudBDC.guardarEntidad(this.clienteConstructor);
            alert("Cliente ha sido ingresado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
     
    
    public void acutalizarCliente(){
        try {
            System.out.print(idCliente);
            
            clienteConstructor = new Cliente();
            
            clienteConstructor.setIdcliente(idCliente);
            System.out.print(idCliente);
            clienteConstructor.setNombre(nomCliente.trim().toUpperCase());
            System.out.print(nomCliente);
            clienteConstructor.setApellido(apeCliente.trim().toUpperCase());
            System.out.print(apeCliente);
            clienteConstructor.setDireccion(dirCliente.trim().toUpperCase());
            System.out.print(dirCliente);
            clienteConstructor.setTelefono(telCliente.trim().toUpperCase());
            System.out.print(telCliente);
            clienteConstructor.setDui(duiCliente.trim().toUpperCase());
            System.out.print(duiCliente);
            clienteConstructor.setNit(nomCliente.trim().toUpperCase());
            System.out.print(nomCliente);
            clienteConstructor.setNrc(nrcCliente.trim().toUpperCase());
            System.out.print(nrcCliente);
            clienteConstructor = null;
            this.limpiarpantalla();
            this.formPanel.setActiveIndex("");
            this.listaCliente = null;
            this.tablaCliente = null;
            this.listaCliente = this.ejbBusqMtto.buscarCliente();
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
        }
    }
    
    
    public void eliminarCliente(){
        try {
        if (this.tablaCliente.getRowData() != null){
            Cliente emp = this.listaCliente.get(this.tablaCliente.getRowIndex());
            System.out.print(emp);
            if (crudBDC.eliminarEntidad(emp) == true){
                    System.out.print("si lo elimino");
                    this.listaCliente.remove(this.tablaCliente.getRowIndex());
                    alert("Registro eliminado exitosamente.", FacesMessage.SEVERITY_INFO);
                    limpiarpantalla();
                    this.listaCliente = this.ejbBusqMtto.buscarCliente();
                } 
            }
        }catch (Exception ex) {
                    Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
     
    public void buscarCliente(){
        try {
            this.listaCliente = this.ejbBusqMtto.buscarCliente();
            if (this.listaCliente == null || this.listaCliente.isEmpty()){
                alert("No se encontraron resultados", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(),FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void onEdit(RowEditEvent event){
        this.formPanel.setActiveIndex("0");
        alert("si entro", FacesMessage.SEVERITY_INFO);    
    }
    
    public void onSelect(){
        try {
        this.formPanel.setActiveIndex("0");
        if (this.tablaCliente.getRowData() != null){
            Cliente emp = this.listaCliente.get(this.tablaCliente.getRowIndex());
            this.idCliente = emp.getIdcliente();
            this.nomCliente = emp.getNombre();
            this.apeCliente = emp.getApellido();
            this.dirCliente = emp.getDireccion();
            this.telCliente = emp.getTelefono();
            this.duiCliente = emp.getDui();
            this.nitCliente = emp.getNit();
            this.nrcCliente = emp.getNrc();
        }
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName())
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
    
}

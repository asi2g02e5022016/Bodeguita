/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Cliente;

import com.asi.restaurantebcd.negocio.base.BusquedasClienteLocal;
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
public class MttoCliente implements Serializable {

    public MttoCliente() {
    }

    @PostConstruct
    public void inicial() {
        try {
//            this.listaCliente = this.ejbBusqMtto.buscarCliente();
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Creates a new instance of MttoCliente
     */
//<editor-fold  defaultstate="collapsed" desc="Variables" >
    /**
     * Busca beans session activa.
     */

    private Boolean deshabilitarTxtUsuario = false;
//    private AccordionPanel formPanel = new AccordionPanel();
    private Boolean mostrarBtnGuardar = false;
    private Boolean deshabilitarTxtContrasenia = false;

    private Integer idCliente;

    private String nomCliente;

    private String apeCliente;

    private String dirCliente;

    private String telCliente;

    private String nitCliente;

    private String nrcCliente;

    private String duiCliente;

    private Boolean exCliente;

    private String usuarioCli;
    private String contrasenia;
    private String email;
    private String giro;
    private Boolean vip;

    private Cliente clienteConstructor;

    private List<Cliente> listaCliente;

    private DataTable tablaCliente = new DataTable();

    @EJB
    private CrudBDCLocal crudBDC;

    @EJB
    private BusquedasClienteLocal ejbBusqMtto;

    //</editor-fold >
//<editor-fold  defaultstate="collapsed" desc="Getter y setter"  >
//    public AccordionPanel getFormPanel() {
//        return formPanel;
//    }
//
//    public void setFormPanel(AccordionPanel formPanel) {
//        this.formPanel = formPanel;
//    }
    public Cliente getClienteConstructor() {
        return clienteConstructor;
    }

    public void setClienteConstructor(Cliente clienteConstructor) {
        this.clienteConstructor = clienteConstructor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Boolean getMostrarBtnGuardar() {
        return mostrarBtnGuardar;
    }

    public void setMostrarBtnGuardar(Boolean mostrarBtnGuardar) {
        this.mostrarBtnGuardar = mostrarBtnGuardar;
    }

    public Boolean getDeshabilitarTxtUsuario() {
        return deshabilitarTxtUsuario;
    }

    public void setDeshabilitarTxtUsuario(Boolean deshabilitarTxtUsuario) {
        this.deshabilitarTxtUsuario = deshabilitarTxtUsuario;
    }

    public String getUsuarioCli() {
        return usuarioCli;
    }

    public void setUsuarioCli(String usuarioCli) {
        this.usuarioCli = usuarioCli;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public Boolean getExCliente() {
        return exCliente;
    }

    public void setExCliente(Boolean exCliente) {
        this.exCliente = exCliente;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public DataTable getTablaCliente() {
        return tablaCliente;
    }

    public void setTablaCliente(DataTable tablaCliente) {
        this.tablaCliente = tablaCliente;
    }

    public Boolean getDeshabilitarTxtContrasenia() {
        return deshabilitarTxtContrasenia;
    }

    public void setDeshabilitarTxtContrasenia(Boolean deshabilitarTxtContrasenia) {
        this.deshabilitarTxtContrasenia = deshabilitarTxtContrasenia;
    }

//</editor-fold >   
//<editor-fold  defaultstate="collapsed" desc="Metodos"  >
    public void mostrarCamposUsuario() {
        if (vip == true) {
            deshabilitarTxtContrasenia = false;
            deshabilitarTxtUsuario = false;
        } else {
            deshabilitarTxtContrasenia = true;
            deshabilitarTxtUsuario = true;
        }

    }

    public void nuevo() {
        limpiarpantalla();
        mostrarBtnGuardar = true;
        deshabilitarTxtContrasenia = true;
        deshabilitarTxtUsuario = true;
    }

    public void limpiarpantalla() {

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
        usuarioCli = "";
        contrasenia = "";
        email = null;
        vip = null;

    }

    public void insertarCliente() {
        try {
            System.out.println("entro");
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

    public void guardarCliente() {
        try {

            if (nomCliente == null || nomCliente.equals("")) {
                alert("Nombre de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            if (apeCliente == null || apeCliente.equals("")) {
                alert("Apellido de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }
            if (dirCliente == null || dirCliente.equals("")) {
                alert("Direccion de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }

            if (duiCliente == null || duiCliente.equals("")) {
                alert("DUI de Cliente es obligatorio", FacesMessage.SEVERITY_INFO);
                return;
            }

            clienteConstructor = new Cliente();

            if (vip == true) {
                clienteConstructor.setVip(vip);
                if (usuarioCli != null && usuarioCli.trim().length() > 0 && contrasenia != null && contrasenia.trim().length() > 0) {
                    String pwd;
                    pwd = String.valueOf(contrasenia.hashCode());
                    clienteConstructor.setUsuario(usuarioCli);
                    clienteConstructor.setPassword(pwd);
                }
            }
            clienteConstructor.setNombre(nomCliente.trim().toUpperCase());
            clienteConstructor.setApellido(apeCliente.trim().toUpperCase());
            clienteConstructor.setTelefono(telCliente);
            clienteConstructor.setDireccion(dirCliente.trim().toUpperCase());
            clienteConstructor.setDui(duiCliente);
            clienteConstructor.setNrc(nrcCliente);
            clienteConstructor.setNit(nitCliente);
            clienteConstructor.setEmail(email);
            clienteConstructor.setExcento(exCliente);

            crudBDC.guardarEntidad(this.clienteConstructor);
            alert("Cliente ha sido ingresado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void acutalizarCliente() {
        try {
            System.out.print(idCliente);
            clienteConstructor = new Cliente();
            clienteConstructor.setIdcliente(idCliente);
            if (usuarioCli != null && usuarioCli.trim().length() > 0 && contrasenia != null && contrasenia.trim().length() > 0) {
                String pwd;
                pwd = String.valueOf(contrasenia.hashCode());
                clienteConstructor.setUsuario(usuarioCli);
                clienteConstructor.setPassword(pwd);
            }

            clienteConstructor.setNombre(nomCliente.trim().toUpperCase());
            clienteConstructor.setApellido(apeCliente.trim().toUpperCase());
            clienteConstructor.setTelefono(telCliente);
            clienteConstructor.setDireccion(dirCliente.trim().toUpperCase());
            clienteConstructor.setDui(duiCliente);
            clienteConstructor.setNrc(nrcCliente);
            clienteConstructor.setNit(nitCliente);
            clienteConstructor.setExcento(exCliente);
            crudBDC.guardarEntidad(this.clienteConstructor);
            alert("Información actualizada exitosamente.", FacesMessage.SEVERITY_INFO);
//            clienteConstructor = new Cliente();
//
//            clienteConstructor.setIdcliente(idCliente);
//            System.out.print(idCliente);
//            clienteConstructor.setNombre(nomCliente.trim().toUpperCase());
//            System.out.print(nomCliente);
//            clienteConstructor.setApellido(apeCliente.trim().toUpperCase());
//            System.out.print(apeCliente);
//            clienteConstructor.setDireccion(dirCliente.trim().toUpperCase());
//            System.out.print(dirCliente);
//            clienteConstructor.setTelefono(telCliente.trim().toUpperCase());
//            System.out.print(telCliente);
//            clienteConstructor.setDui(duiCliente.trim().toUpperCase());
//            System.out.print(duiCliente);
//            clienteConstructor.setNit(nomCliente.trim().toUpperCase());
//            System.out.print(nomCliente);
//            clienteConstructor.setNrc(nrcCliente.trim().toUpperCase());
//            System.out.print(nrcCliente);
//            clienteConstructor = null;
            this.limpiarpantalla();
//            this.formPanel.setActiveIndex("");
            this.listaCliente = null;
            this.tablaCliente = null;
            this.listaCliente = this.ejbBusqMtto.buscarCliente();
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void eliminarCliente() {
        try {
            if (this.tablaCliente.getRowData() != null) {
                Cliente emp = this.listaCliente.get(this.tablaCliente.getRowIndex());
                System.out.print(emp);
                if (crudBDC.eliminarEntidad(emp) == true) {
                    System.out.print("si lo elimino");
                    this.listaCliente.remove(this.tablaCliente.getRowIndex());
                    alert("Registro eliminado exitosamente.", FacesMessage.SEVERITY_INFO);
                    limpiarpantalla();
                    this.listaCliente = this.ejbBusqMtto.buscarCliente();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarCliente() {
        try {
            mostrarBtnGuardar = false;
            this.listaCliente = this.ejbBusqMtto.buscarCliente();
            if (this.listaCliente == null || this.listaCliente.isEmpty()) {
                alert("No se encontraron resultados", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoCliente.class.getName()).log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void onEdit(RowEditEvent event) {
//        this.formPanel.setActiveIndex("0");
        mostrarBtnGuardar = true;

        alert("si entro", FacesMessage.SEVERITY_INFO);
    }

    public void onSelect() {
        try {
//            this.formPanel.setActiveIndex("0");
            if (this.tablaCliente.getRowData() != null) {
                Cliente emp = this.listaCliente.get(this.tablaCliente.getRowIndex());
                this.idCliente = emp.getIdcliente();
                this.nomCliente = emp.getNombre();
                this.apeCliente = emp.getApellido();
                this.dirCliente = emp.getDireccion();
                this.telCliente = emp.getTelefono();
                this.duiCliente = emp.getDui();
                this.nitCliente = emp.getNit();
                this.nrcCliente = emp.getNrc();
                this.usuarioCli = emp.getUsuario();
                this.email = emp.getEmail();
                this.vip = emp.isVip();
                deshabilitarTxtContrasenia=true;
                if ( vip == true /*usuarioCli == null || usuarioCli.isEmpty() == true || usuarioCli.length() == 0*/) {
                    deshabilitarTxtUsuario = false;
                    usuarioCli = "";
                    contrasenia = "";
                } else {
                    deshabilitarTxtUsuario = true;
                }
                mostrarBtnGuardar = true;
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

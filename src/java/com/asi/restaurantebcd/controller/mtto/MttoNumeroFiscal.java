/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.mtto;

import com.asi.restaurantbcd.modelo.Caja;
import com.asi.restaurantbcd.modelo.Numerofiscal;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Tipodocumento;
import com.asi.restaurantebcd.controller.seguridad.SessionUsr;
import com.asi.restaurantebcd.negocio.base.BusquedaTipoDocumentoLocal;
import com.asi.restaurantebcd.negocio.base.BusquedasNumeroFiscalLocal;
import com.asi.restaurantebcd.negocio.base.CrudBDCLocal;
import com.asi.restaurantebcd.negocio.util.Utilidades;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author "Joaquin Sanchez SA101110"
 */
@ManagedBean(name = "mttoNumeroFiscal")
@ViewScoped
public class MttoNumeroFiscal implements Serializable {

    //<editor-fold  defaultstate="collapsed" desc="Inicializar" >
    /**
     * Creates a new instance of MttoUsuarioManagedBean
     */
    public MttoNumeroFiscal() {
    }

    @PostConstruct
    public void postConstruction() {
        try {
            sesion = Utilidades.findBean("sessionUsr");
            if (sesion == null) {
                alert("Debe Iniciar Sesion", FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().getViewRoot().
                        getViewMap().clear();
                String url = "http://localhost:8080/RestaurantBDC";
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect(url);
            }
//            limpiarPantalla();
//            buscarNumeroFiscal();
            buscarSucursal();
//            buscarCajas();
            buscarTipoDocumento();
        } catch (Exception e) {
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
            alert("error de post", FacesMessage.SEVERITY_FATAL);
        }
    }

    //</editor-fold >
    //<editor-fold  defaultstate="collapsed" desc="Variables" >
    /**
     * Creates a new instance of MttoUsuarioManagedBean
     */
    private AccordionPanel formPanel = new AccordionPanel();
    private Boolean mostrarBtnGuardar;
    private Integer idNumeroFiscal;
    private Numerofiscal numerofiscalCons;
    private Caja cajaConst;
    private Sucursal sucsalConst;
    private SessionUsr sesion; //Busca beans session activa.
    private Sucursal idSucursal;
    private Caja idCaja;
    private Integer idTipodocumento;
    private int codnumfiscal;
    private int codsucursal;
    private String serie;
    private int codcaja;
    private Date fecharesolucion;
    private int codinicial;
    private int codactual;
    private int codfinal;
    private List<Numerofiscal> lstNumFiscal;
    private List<Sucursal> lstSucsal;
    private List<Tipodocumento> lstTipoDoc;
    private List<Caja> lstCaja;
    private DataTable dtNumFiscal = new DataTable();

    @EJB
    private CrudBDCLocal crud;
    @EJB
    private BusquedasNumeroFiscalLocal EjbBusqNumFiscLocal;
    @EJB
    private BusquedaTipoDocumentoLocal ejbBusqTipoDoc;
    //</editor-fold >

    //<editor-fold  defaultstate="collapsed" desc="Metodos" >
    /**
     * Creates a new instance of MttoNumeroFiscal
     */
    public void nuevo() {
        limpiarPantalla();
        mostrarBtnGuardar = true;
    }

    public void limpiarPantalla() {
//        this.formPanel.setActiveIndex("0");
        numerofiscalCons = null;
        cajaConst = null;
        sucsalConst = null;
        idSucursal = null;
        idCaja = null;
        codnumfiscal = 0;
        codsucursal = 0;
        serie = "";
        codcaja = 0;
        fecharesolucion = null;
        codinicial = 0;
        codactual = 0;
        codfinal = 0;

    }

    public void onSelect() throws Exception {
        if (this.dtNumFiscal.getRowData() != null) {
            Numerofiscal nf = (Numerofiscal) this.lstNumFiscal.get(this.dtNumFiscal.getRowIndex());
            buscarSucursal();
            buscarTipoDocumento();
            codsucursal = nf.getIdsucursal().getIdsucursal();
            buscarCajaSuc();
            codcaja = nf.getIdcaja().getIdcaja();
            idTipodocumento = nf.getIdtipodocumento().getIdtipodocumento();
            serie = nf.getSerie();
            codinicial = nf.getCodinicial();
            codfinal = nf.getCodfinal();
            codactual = nf.getCodactual();
            idNumeroFiscal = nf.getIdnumfiscal();
            mostrarBtnGuardar = true;
        }
    }

    public void buscarTipoDocumento() {
        try {
            lstTipoDoc = ejbBusqTipoDoc.buscarTipodocumento();
            if (lstTipoDoc == null || lstNumFiscal.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {

        }
    }

    /**
     * Buscar los numeros fiscales ingresados
     */
    public void buscarNumeroFiscal() {
        try {
            limpiarPantalla();
            mostrarBtnGuardar = false;
            lstNumFiscal = EjbBusqNumFiscLocal.buscarNumeroFiscal();
            if (lstNumFiscal == null || lstNumFiscal.isEmpty()) {
                alert("No se encontro numero fiscal.", FacesMessage.SEVERITY_INFO);
            }

        } catch (Exception e) {

        }
    }

    /**
     * Buscar las sucursales ingresadas
     */
    public void buscarSucursal() {
        try {
            lstSucsal = EjbBusqNumFiscLocal.buscarSucursal();
            if (lstSucsal == null || lstSucsal.isEmpty()) {
                alert("No se encontraron sucursales.", FacesMessage.SEVERITY_INFO);
            }

        } catch (Exception e) {

        }
    }

    /**
     * Buscar las cajas ingresadas
     *
     */
    public void buscarCajaSuc() throws Exception {
//        System.out.println(idSucursal);
        System.out.println(codsucursal);
        sucsalConst = new Sucursal();
        sucsalConst.setIdsucursal(codsucursal);
        lstCaja = EjbBusqNumFiscLocal.buscarCaja(sucsalConst);
        if (lstCaja == null || lstCaja.isEmpty()) {
            alert("No se encontraron cajas.", FacesMessage.SEVERITY_INFO);
        }
    }

    public void buscarCajas() {
        try {
            System.out.println(idSucursal);
            System.out.println(codsucursal);
            sucsalConst = new Sucursal();
            sucsalConst.setIdsucursal(codsucursal);
            lstCaja = EjbBusqNumFiscLocal.buscarCaja(sucsalConst);
            if (lstCaja == null || lstCaja.isEmpty()) {
                alert("No se encontraron cajas.", FacesMessage.SEVERITY_INFO);
            }

        } catch (Exception e) {

        }
    }

    /**
     * Metodo que se ejecuta cuando se esta editando la grilla del formulario
     *
     * @param event
     *
     */
    public void eventoEdit(RowEditEvent event) {
        //this.guardarUsuario();
        alert("Registro modificado exitosamente.",
                FacesMessage.SEVERITY_INFO);
    }

    /**
     * Metodo que se ejecuta cuando se cancela la acción de edición en la grilla
     * del formulario
     *
     * @param event
     */
    public void eventoCancel(RowEditEvent event) {
        alert("Se ha cancelado la acción.",
                FacesMessage.SEVERITY_INFO);
    }

    public void insertarNumeroFiscal() {
        try {
            Date fechaAhora = new Date();
            numerofiscalCons = new Numerofiscal();
            Sucursal suc = new Sucursal();
            Caja caj = new Caja();
            Tipodocumento tipoDoc = new Tipodocumento();
            suc.setIdsucursal(codsucursal);
            caj.setIdcaja(codcaja);
            tipoDoc.setIdtipodocumento(idTipodocumento);
            numerofiscalCons.setSerie(serie);
            numerofiscalCons.setFecharesolusion(fechaAhora);
            numerofiscalCons.setCodinicial(codinicial);
            numerofiscalCons.setCodfinal(codfinal);
            numerofiscalCons.setCodactual(codactual);
            numerofiscalCons.setIdcaja(caj);
            numerofiscalCons.setIdsucursal(suc);
            numerofiscalCons.setIdtipodocumento(tipoDoc);

            crud.guardarEntidad(numerofiscalCons);
            alert("Registro ingresado exitosamente.", FacesMessage.SEVERITY_INFO);
            limpiarPantalla();
            buscarNumeroFiscal();
            mostrarBtnGuardar = false;
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que guarda un usuaio.
     */
    public void guardarNumeroFiscal() {
        try {
            if (idNumeroFiscal == null) {
                System.out.println("Entro al insertar");
                insertarNumeroFiscal();
            } else {
                System.out.println("Entro al actualizar");
                actualizarNumeroFiscal();
            }
        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metódo para actualizar la informacion del usuario
     *
     * @param event
     */
    public void actualizarNumeroFiscal() {
        try {
            Date fechaAhora = new Date();
            numerofiscalCons = new Numerofiscal();
            Sucursal suc = new Sucursal();
            Caja caj = new Caja();
            Tipodocumento tipoDoc = new Tipodocumento();
            suc.setIdsucursal(codsucursal);
            caj.setIdcaja(codcaja);
            tipoDoc.setIdtipodocumento(idTipodocumento);
            numerofiscalCons.setIdnumfiscal(idNumeroFiscal);
            numerofiscalCons.setSerie(serie);
            numerofiscalCons.setFecharesolusion(fechaAhora);
            numerofiscalCons.setCodinicial(codinicial);
            numerofiscalCons.setCodfinal(codfinal);
            numerofiscalCons.setCodactual(codactual);
            numerofiscalCons.setIdcaja(caj);
            numerofiscalCons.setIdsucursal(suc);
            numerofiscalCons.setIdtipodocumento(tipoDoc);

            crud.guardarEntidad(numerofiscalCons);
            alert("Registro actualizado exitosamente.", FacesMessage.SEVERITY_INFO);
            limpiarPantalla();
            buscarNumeroFiscal();
            mostrarBtnGuardar = false;

        } catch (Exception ex) {
            Logger.getLogger(MttoUsuarioMB.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Mensaje de alerta que se muestra en pantalla.
     *
     * @param mensaje Mensaje que quiere mostrar.
     * @param faces constantes definidas en FacesMessage tipos:
     * FacesMessage.SEVERITY_INFO informativo. FacesMessage.SEVERITY_ERROR
     * error. FacesMessage.SEVERITY_WARN adventencia.
     */
    private void alert(CharSequence mensaje, FacesMessage.Severity faces) {
        if (mensaje == null) {
            mensaje = "-";
        }
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    //</editor-fold >
    //<editor-fold  defaultstate="collapsed" desc="Getter y Setter" >
    /**
     * Creates a new instance of MttoNumeroFiscal
     *
     * @return
     */
    public AccordionPanel getFormPanel() {
        return formPanel;
    }

    public Boolean getMostrarBtnGuardar() {
        return mostrarBtnGuardar;
    }

    public void setMostrarBtnGuardar(Boolean mostrarBtnGuardar) {
        this.mostrarBtnGuardar = mostrarBtnGuardar;
    }

    public Integer getIdNumeroFiscal() {
        return idNumeroFiscal;
    }

    public void setIdNumeroFiscal(Integer idNumeroFiscal) {
        this.idNumeroFiscal = idNumeroFiscal;
    }

    public Numerofiscal getNumerofiscalCons() {
        return numerofiscalCons;
    }

    public Integer getIdTipodocumento() {
        return idTipodocumento;
    }

    public void setIdTipodocumento(Integer idTipodocumento) {
        this.idTipodocumento = idTipodocumento;
    }

    public void setNumerofiscalCons(Numerofiscal numerofiscalCons) {
        this.numerofiscalCons = numerofiscalCons;
    }

    public Caja getCajaConst() {
        return cajaConst;
    }

    public void setCajaConst(Caja cajaConst) {
        this.cajaConst = cajaConst;
    }

    public Sucursal getSucsalConst() {
        return sucsalConst;
    }

    public void setSucsalConst(Sucursal sucsalConst) {
        this.sucsalConst = sucsalConst;
    }

    public SessionUsr getSesion() {
        return sesion;
    }

    public void setSesion(SessionUsr sesion) {
        this.sesion = sesion;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Caja getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Caja idCaja) {
        this.idCaja = idCaja;
    }

    public int getCodnumfiscal() {
        return codnumfiscal;
    }

    public void setCodnumfiscal(int codnumfiscal) {
        this.codnumfiscal = codnumfiscal;
    }

    public int getCodsucursal() {
        return codsucursal;
    }

    public void setCodsucursal(int codsucursal) {
        this.codsucursal = codsucursal;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public DataTable getDtNumFiscal() {
        return dtNumFiscal;
    }

    public void setDtNumFiscal(DataTable dtNumFiscal) {
        this.dtNumFiscal = dtNumFiscal;
    }

    public int getCodcaja() {
        return codcaja;
    }

    public void setCodcaja(int codcaja) {
        this.codcaja = codcaja;
    }

    public Date getFecharesolucion() {
        return fecharesolucion;
    }

    public void setFecharesolucion(Date fecharesolucion) {
        this.fecharesolucion = fecharesolucion;
    }

    public int getCodinicial() {
        return codinicial;
    }

    public void setCodinicial(int codinicial) {
        this.codinicial = codinicial;
    }

    public int getCodactual() {
        return codactual;
    }

    public void setCodactual(int codactual) {
        this.codactual = codactual;
    }

    public int getCodfinal() {
        return codfinal;
    }

    public void setCodfinal(int codfinal) {
        this.codfinal = codfinal;
    }

    public List<Numerofiscal> getLstNumFiscal() {
        return lstNumFiscal;
    }

    public void setLstNumFiscal(List<Numerofiscal> lstNumFiscal) {
        this.lstNumFiscal = lstNumFiscal;
    }

    public List<Sucursal> getLstSucsal() {
        return lstSucsal;
    }

    public void setLstSucsal(List<Sucursal> lstSucsal) {
        this.lstSucsal = lstSucsal;
    }

    public List<Caja> getLstCaja() {
        return lstCaja;
    }

    public void setLstCaja(List<Caja> lstCaja) {
        this.lstCaja = lstCaja;
    }

    public List<Tipodocumento> getLstTipoDoc() {
        return lstTipoDoc;
    }

    public void setLstTipoDoc(List<Tipodocumento> lstTipoDoc) {
        this.lstTipoDoc = lstTipoDoc;
    }

    public CrudBDCLocal getCrud() {
        return crud;
    }

    public void setCrud(CrudBDCLocal crud) {
        this.crud = crud;
    }

    public BusquedasNumeroFiscalLocal getEjbBusqNumFiscLocal() {
        return EjbBusqNumFiscLocal;
    }

    public void setEjbBusqNumFiscLocal(BusquedasNumeroFiscalLocal EjbBusqNumFiscLocal) {
        this.EjbBusqNumFiscLocal = EjbBusqNumFiscLocal;
    }

    //</editor-fold >
}

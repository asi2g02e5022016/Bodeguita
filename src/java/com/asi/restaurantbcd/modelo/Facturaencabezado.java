/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "facturaencabezado")
@NamedQueries({
    @NamedQuery(name = "Facturaencabezado.findAll", query = "SELECT f FROM Facturaencabezado f")})
public class Facturaencabezado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacturaencabezadoPK facturaencabezadoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechafactura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mesa")
    private int mesa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulado")
    private boolean anulado;
    @JoinColumn(name = "idcaja", referencedColumnName = "idcaja")
    @ManyToOne(optional = false)
    private Caja idcaja;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "idcondicionpago", referencedColumnName = "idcondicionpago")
    @ManyToOne(optional = false)
    private Condicionpago idcondicionpago;
    @JoinColumn(name = "idformapago", referencedColumnName = "idformapago")
    @ManyToOne(optional = false)
    private Formapago idformapago;
    @JoinColumn(name = "idtipodocumento", referencedColumnName = "idtipodocumento")
    @ManyToOne(optional = false)
    private Tipodocumento idtipodocumento;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaencabezado")
    private List<Facturaanulada> facturaanuladaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaencabezado")
    private List<Facturadetalle> facturadetalleList;

    public Facturaencabezado() {
    }

    public Facturaencabezado(FacturaencabezadoPK facturaencabezadoPK) {
        this.facturaencabezadoPK = facturaencabezadoPK;
    }

    public Facturaencabezado(FacturaencabezadoPK facturaencabezadoPK, Date fechafactura, int mesa, boolean anulado) {
        this.facturaencabezadoPK = facturaencabezadoPK;
        this.fechafactura = fechafactura;
        this.mesa = mesa;
        this.anulado = anulado;
    }

    public Facturaencabezado(int idfactura, int idserie, int idsucursal) {
        this.facturaencabezadoPK = new FacturaencabezadoPK(idfactura, idserie, idsucursal);
    }

    public FacturaencabezadoPK getFacturaencabezadoPK() {
        return facturaencabezadoPK;
    }

    public void setFacturaencabezadoPK(FacturaencabezadoPK facturaencabezadoPK) {
        this.facturaencabezadoPK = facturaencabezadoPK;
    }

    public Date getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(Date fechafactura) {
        this.fechafactura = fechafactura;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public Caja getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(Caja idcaja) {
        this.idcaja = idcaja;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Condicionpago getIdcondicionpago() {
        return idcondicionpago;
    }

    public void setIdcondicionpago(Condicionpago idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }

    public Formapago getIdformapago() {
        return idformapago;
    }

    public void setIdformapago(Formapago idformapago) {
        this.idformapago = idformapago;
    }

    public Tipodocumento getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(Tipodocumento idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public List<Facturaanulada> getFacturaanuladaList() {
        return facturaanuladaList;
    }

    public void setFacturaanuladaList(List<Facturaanulada> facturaanuladaList) {
        this.facturaanuladaList = facturaanuladaList;
    }

    public List<Facturadetalle> getFacturadetalleList() {
        return facturadetalleList;
    }

    public void setFacturadetalleList(List<Facturadetalle> facturadetalleList) {
        this.facturadetalleList = facturadetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaencabezadoPK != null ? facturaencabezadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturaencabezado)) {
            return false;
        }
        Facturaencabezado other = (Facturaencabezado) object;
        if ((this.facturaencabezadoPK == null && other.facturaencabezadoPK != null) || (this.facturaencabezadoPK != null && !this.facturaencabezadoPK.equals(other.facturaencabezadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Facturaencabezado[ facturaencabezadoPK=" + facturaencabezadoPK + " ]";
    }
    
}

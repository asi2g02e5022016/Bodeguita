/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "facturaanulada")
@NamedQueries({
    @NamedQuery(name = "Facturaanulada.findAll", query = "SELECT f FROM Facturaanulada f")})
public class Facturaanulada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfacturaanulada")
    private Integer idfacturaanulada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaanulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaanulacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "observacion")
    private String observacion;
    @JoinColumns({
        @JoinColumn(name = "idfactura", referencedColumnName = "idfactura"),
        @JoinColumn(name = "idserie", referencedColumnName = "idserie"),
        @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")})
    @ManyToOne(optional = false)
    private Facturaencabezado facturaencabezado;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Facturaanulada() {
    }

    public Facturaanulada(Integer idfacturaanulada) {
        this.idfacturaanulada = idfacturaanulada;
    }

    public Facturaanulada(Integer idfacturaanulada, Date fechaanulacion, String observacion) {
        this.idfacturaanulada = idfacturaanulada;
        this.fechaanulacion = fechaanulacion;
        this.observacion = observacion;
    }

    public Integer getIdfacturaanulada() {
        return idfacturaanulada;
    }

    public void setIdfacturaanulada(Integer idfacturaanulada) {
        this.idfacturaanulada = idfacturaanulada;
    }

    public Date getFechaanulacion() {
        return fechaanulacion;
    }

    public void setFechaanulacion(Date fechaanulacion) {
        this.fechaanulacion = fechaanulacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Facturaencabezado getFacturaencabezado() {
        return facturaencabezado;
    }

    public void setFacturaencabezado(Facturaencabezado facturaencabezado) {
        this.facturaencabezado = facturaencabezado;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfacturaanulada != null ? idfacturaanulada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturaanulada)) {
            return false;
        }
        Facturaanulada other = (Facturaanulada) object;
        if ((this.idfacturaanulada == null && other.idfacturaanulada != null) || (this.idfacturaanulada != null && !this.idfacturaanulada.equals(other.idfacturaanulada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Facturaanulada[ idfacturaanulada=" + idfacturaanulada + " ]";
    }
    
}

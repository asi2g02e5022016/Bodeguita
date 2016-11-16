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
@Table(name = "notapedido")
@NamedQueries({
    @NamedQuery(name = "Notapedido.findAll", query = "SELECT n FROM Notapedido n")})
public class Notapedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotapedidoPK notapedidoPK;
    @Basic(optional = false)
    @Column(name = "fechageneracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechageneracion;
    @Column(name = "fechaingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaingreso;
    @Basic(optional = false)
    @Column(name = "idusuarios")
    private String idusuarios;
    @Basic(optional = true)
    @Column(name = "observacion")
    private String observacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notapedido")
    private List<Notapedidodetalle> notapedidodetalleList;
    @JoinColumn(name = "idestado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado idestado;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sucursal sucursal;
    
    @JoinColumn(name = "idsucursalorigen", referencedColumnName = "idsucursal", insertable = true, updatable = true)
    @ManyToOne(optional = false)
    private Sucursal idSucursalOrigen;
    
    
    public Notapedido() {
    }

    public Notapedido(NotapedidoPK notapedidoPK) {
        this.notapedidoPK = notapedidoPK;
    }

    public Notapedido(NotapedidoPK notapedidoPK, Date fechageneracion, String idusuarios) {
        this.notapedidoPK = notapedidoPK;
        this.fechageneracion = fechageneracion;
        this.idusuarios = idusuarios;
    }

    public Notapedido(int idnotapedido, int idsucursal) {
        this.notapedidoPK = new NotapedidoPK(idnotapedido, idsucursal);
    }

    public NotapedidoPK getNotapedidoPK() {
        return notapedidoPK;
    }

    public void setNotapedidoPK(NotapedidoPK notapedidoPK) {
        this.notapedidoPK = notapedidoPK;
    }

    public Date getFechageneracion() {
        return fechageneracion;
    }

    public void setFechageneracion(Date fechageneracion) {
        this.fechageneracion = fechageneracion;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(String idusuarios) {
        this.idusuarios = idusuarios;
    }

    public List<Notapedidodetalle> getNotapedidodetalleList() {
        return notapedidodetalleList;
    }

    public void setNotapedidodetalleList(List<Notapedidodetalle> notapedidodetalleList) {
        this.notapedidodetalleList = notapedidodetalleList;
    }

    public Estado getIdestado() {
        return idestado;
    }

    public void setIdestado(Estado idestado) {
        this.idestado = idestado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notapedidoPK != null ? notapedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notapedido)) {
            return false;
        }
        Notapedido other = (Notapedido) object;
        if ((this.notapedidoPK == null && other.notapedidoPK != null) || (this.notapedidoPK != null && !this.notapedidoPK.equals(other.notapedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Notapedido[ notapedidoPK=" + notapedidoPK + " ]";
    }

    /**
     * @return the idSucursalOrigen
     */
    public Sucursal getIdSucursalOrigen() {
        return idSucursalOrigen;
    }

    /**
     * @param idSucursalOrigen the idSucursalOrigen to set
     */
    public void setIdSucursalOrigen(Sucursal idSucursalOrigen) {
        this.idSucursalOrigen = idSucursalOrigen;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}

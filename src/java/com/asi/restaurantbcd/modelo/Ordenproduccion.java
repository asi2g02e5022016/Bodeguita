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

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "ordenproduccion")
@NamedQueries({
    @NamedQuery(name = "Ordenproduccion.findAll", query = "SELECT o FROM Ordenproduccion o")})
public class Ordenproduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdenproduccionPK ordenproduccionPK;
    @Basic(optional = false)
    @Column(name = "fechapedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapedido;
    @JoinColumn(name = "idestado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado idestado;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sucursal sucursal;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenproduccion")
    private List<Ordenproducciondetalle> ordenproducciondetalleList;

    public Ordenproduccion() {
    }

    public Ordenproduccion(OrdenproduccionPK ordenproduccionPK) {
        this.ordenproduccionPK = ordenproduccionPK;
    }

    public Ordenproduccion(OrdenproduccionPK ordenproduccionPK, Date fechapedido) {
        this.ordenproduccionPK = ordenproduccionPK;
        this.fechapedido = fechapedido;
    }

    public Ordenproduccion(int idordenproduccion, int idsucursal) {
        this.ordenproduccionPK = new OrdenproduccionPK(idordenproduccion, idsucursal);
    }

    public OrdenproduccionPK getOrdenproduccionPK() {
        return ordenproduccionPK;
    }

    public void setOrdenproduccionPK(OrdenproduccionPK ordenproduccionPK) {
        this.ordenproduccionPK = ordenproduccionPK;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
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

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public List<Ordenproducciondetalle> getOrdenproducciondetalleList() {
        return ordenproducciondetalleList;
    }

    public void setOrdenproducciondetalleList(List<Ordenproducciondetalle> ordenproducciondetalleList) {
        this.ordenproducciondetalleList = ordenproducciondetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenproduccionPK != null ? ordenproduccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenproduccion)) {
            return false;
        }
        Ordenproduccion other = (Ordenproduccion) object;
        if ((this.ordenproduccionPK == null && other.ordenproduccionPK != null) || (this.ordenproduccionPK != null && !this.ordenproduccionPK.equals(other.ordenproduccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ordenproduccion[ ordenproduccionPK=" + ordenproduccionPK + " ]";
    }
    
}

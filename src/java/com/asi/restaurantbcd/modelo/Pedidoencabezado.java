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
@Table(name = "pedidoencabezado")
@NamedQueries({
    @NamedQuery(name = "Pedidoencabezado.findAll", query = "SELECT p FROM Pedidoencabezado p")})
public class Pedidoencabezado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoencabezadoPK pedidoencabezadoPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoencabezado")
    private List<Pedidodetalle> pedidodetalleList;

    public Pedidoencabezado() {
    }

    public Pedidoencabezado(PedidoencabezadoPK pedidoencabezadoPK) {
        this.pedidoencabezadoPK = pedidoencabezadoPK;
    }

    public Pedidoencabezado(PedidoencabezadoPK pedidoencabezadoPK, Date fechapedido) {
        this.pedidoencabezadoPK = pedidoencabezadoPK;
        this.fechapedido = fechapedido;
    }

    public Pedidoencabezado(int idpedido, int idsucursal) {
        this.pedidoencabezadoPK = new PedidoencabezadoPK(idpedido, idsucursal);
    }

    public PedidoencabezadoPK getPedidoencabezadoPK() {
        return pedidoencabezadoPK;
    }

    public void setPedidoencabezadoPK(PedidoencabezadoPK pedidoencabezadoPK) {
        this.pedidoencabezadoPK = pedidoencabezadoPK;
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

    public List<Pedidodetalle> getPedidodetalleList() {
        return pedidodetalleList;
    }

    public void setPedidodetalleList(List<Pedidodetalle> pedidodetalleList) {
        this.pedidodetalleList = pedidodetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoencabezadoPK != null ? pedidoencabezadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidoencabezado)) {
            return false;
        }
        Pedidoencabezado other = (Pedidoencabezado) object;
        if ((this.pedidoencabezadoPK == null && other.pedidoencabezadoPK != null) || (this.pedidoencabezadoPK != null && !this.pedidoencabezadoPK.equals(other.pedidoencabezadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Pedidoencabezado[ pedidoencabezadoPK=" + pedidoencabezadoPK + " ]";
    }
    
}

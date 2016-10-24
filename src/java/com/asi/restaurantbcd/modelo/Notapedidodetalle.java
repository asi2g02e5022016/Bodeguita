/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "notapedidodetalle")
@NamedQueries({
    @NamedQuery(name = "Notapedidodetalle.findAll", query = "SELECT n FROM Notapedidodetalle n")})
public class Notapedidodetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotapedidodetallePK notapedidodetallePK;
    @Basic(optional = false)
    @Column(name = "cantidadsolicitada")
    private int cantidadsolicitada;
    @Basic(optional = false)
    @Column(name = "cantidadconfirmada")
    private int cantidadconfirmada;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @JoinColumns({
        @JoinColumn(name = "idnotapedido", referencedColumnName = "idnotapedido", insertable = false, updatable = false),
        @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Notapedido notapedido;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;

    public Notapedidodetalle() {
    }

    public Notapedidodetalle(NotapedidodetallePK notapedidodetallePK) {
        this.notapedidodetallePK = notapedidodetallePK;
    }

    public Notapedidodetalle(NotapedidodetallePK notapedidodetallePK, int cantidadsolicitada, int cantidadconfirmada, float precio) {
        this.notapedidodetallePK = notapedidodetallePK;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
    }

    public Notapedidodetalle(int idnotapeddet, int idnotapedido, int idsucursal) {
        this.notapedidodetallePK = new NotapedidodetallePK(idnotapeddet, idnotapedido, idsucursal);
    }

    public NotapedidodetallePK getNotapedidodetallePK() {
        return notapedidodetallePK;
    }

    public void setNotapedidodetallePK(NotapedidodetallePK notapedidodetallePK) {
        this.notapedidodetallePK = notapedidodetallePK;
    }

    public int getCantidadsolicitada() {
        return cantidadsolicitada;
    }

    public void setCantidadsolicitada(int cantidadsolicitada) {
        this.cantidadsolicitada = cantidadsolicitada;
    }

    public int getCantidadconfirmada() {
        return cantidadconfirmada;
    }

    public void setCantidadconfirmada(int cantidadconfirmada) {
        this.cantidadconfirmada = cantidadconfirmada;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Notapedido getNotapedido() {
        return notapedido;
    }

    public void setNotapedido(Notapedido notapedido) {
        this.notapedido = notapedido;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notapedidodetallePK != null ? notapedidodetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notapedidodetalle)) {
            return false;
        }
        Notapedidodetalle other = (Notapedidodetalle) object;
        if ((this.notapedidodetallePK == null && other.notapedidodetallePK != null) || (this.notapedidodetallePK != null && !this.notapedidodetallePK.equals(other.notapedidodetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Notapedidodetalle[ notapedidodetallePK=" + notapedidodetallePK + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotapeddet")
    private Integer idnotapeddet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadsolicitada")
    private int cantidadsolicitada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadconfirmada")
    private int cantidadconfirmada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private float precio;
    @JoinColumn(name = "idnotapedido", referencedColumnName = "idnotapedido")
    @ManyToOne(optional = false)
    private Notapedido idnotapedido;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;

    public Notapedidodetalle() {
    }

    public Notapedidodetalle(Integer idnotapeddet) {
        this.idnotapeddet = idnotapeddet;
    }

    public Notapedidodetalle(Integer idnotapeddet, int cantidadsolicitada, int cantidadconfirmada, float precio) {
        this.idnotapeddet = idnotapeddet;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
    }

    public Integer getIdnotapeddet() {
        return idnotapeddet;
    }

    public void setIdnotapeddet(Integer idnotapeddet) {
        this.idnotapeddet = idnotapeddet;
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

    public Notapedido getIdnotapedido() {
        return idnotapedido;
    }

    public void setIdnotapedido(Notapedido idnotapedido) {
        this.idnotapedido = idnotapedido;
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
        hash += (idnotapeddet != null ? idnotapeddet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notapedidodetalle)) {
            return false;
        }
        Notapedidodetalle other = (Notapedidodetalle) object;
        if ((this.idnotapeddet == null && other.idnotapeddet != null) || (this.idnotapeddet != null && !this.idnotapeddet.equals(other.idnotapeddet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Notapedidodetalle[ idnotapeddet=" + idnotapeddet + " ]";
    }
    
}

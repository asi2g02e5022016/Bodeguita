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
@Table(name = "ordenpedidodetalle")
@NamedQueries({
    @NamedQuery(name = "Ordenpedidodetalle.findAll", query = "SELECT o FROM Ordenpedidodetalle o")})
public class Ordenpedidodetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idordenpedidodet")
    private Integer idordenpedidodet;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private float costo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva")
    private float iva;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumn(name = "idordenpedido", referencedColumnName = "idordenpedido")
    @ManyToOne(optional = false)
    private Ordenpedido idordenpedido;

    public Ordenpedidodetalle() {
    }

    public Ordenpedidodetalle(Integer idordenpedidodet) {
        this.idordenpedidodet = idordenpedidodet;
    }

    public Ordenpedidodetalle(Integer idordenpedidodet, int cantidadsolicitada, int cantidadconfirmada, float precio, float costo, float iva) {
        this.idordenpedidodet = idordenpedidodet;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
        this.costo = costo;
        this.iva = iva;
    }

    public Integer getIdordenpedidodet() {
        return idordenpedidodet;
    }

    public void setIdordenpedidodet(Integer idordenpedidodet) {
        this.idordenpedidodet = idordenpedidodet;
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

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Ordenpedido getIdordenpedido() {
        return idordenpedido;
    }

    public void setIdordenpedido(Ordenpedido idordenpedido) {
        this.idordenpedido = idordenpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idordenpedidodet != null ? idordenpedidodet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenpedidodetalle)) {
            return false;
        }
        Ordenpedidodetalle other = (Ordenpedidodetalle) object;
        if ((this.idordenpedidodet == null && other.idordenpedidodet != null) || (this.idordenpedidodet != null && !this.idordenpedidodet.equals(other.idordenpedidodet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ordenpedidodetalle[ idordenpedidodet=" + idordenpedidodet + " ]";
    }
    
}

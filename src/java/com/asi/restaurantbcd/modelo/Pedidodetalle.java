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
@Table(name = "pedidodetalle")
@NamedQueries({
    @NamedQuery(name = "Pedidodetalle.findAll", query = "SELECT p FROM Pedidodetalle p")})
public class Pedidodetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedidodetalle")
    private Integer idpedidodetalle;
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
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    @ManyToOne(optional = false)
    private Pedidoencabezado idpedido;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")
    @ManyToOne(optional = false)
    private Sucursal idsucursal;

    public Pedidodetalle() {
    }

    public Pedidodetalle(Integer idpedidodetalle) {
        this.idpedidodetalle = idpedidodetalle;
    }

    public Pedidodetalle(Integer idpedidodetalle, int cantidadsolicitada, int cantidadconfirmada, float precio, float costo, float iva) {
        this.idpedidodetalle = idpedidodetalle;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
        this.costo = costo;
        this.iva = iva;
    }

    public Integer getIdpedidodetalle() {
        return idpedidodetalle;
    }

    public void setIdpedidodetalle(Integer idpedidodetalle) {
        this.idpedidodetalle = idpedidodetalle;
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

    public Pedidoencabezado getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Pedidoencabezado idpedido) {
        this.idpedido = idpedido;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedidodetalle != null ? idpedidodetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidodetalle)) {
            return false;
        }
        Pedidodetalle other = (Pedidodetalle) object;
        if ((this.idpedidodetalle == null && other.idpedidodetalle != null) || (this.idpedidodetalle != null && !this.idpedidodetalle.equals(other.idpedidodetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Pedidodetalle[ idpedidodetalle=" + idpedidodetalle + " ]";
    }
    
}

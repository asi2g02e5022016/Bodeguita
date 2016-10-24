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
@Table(name = "pedidodetalle")
@NamedQueries({
    @NamedQuery(name = "Pedidodetalle.findAll", query = "SELECT p FROM Pedidodetalle p")})
public class Pedidodetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidodetallePK pedidodetallePK;
    @Basic(optional = false)
    @Column(name = "cantidadsolicitada")
    private int cantidadsolicitada;
    @Basic(optional = false)
    @Column(name = "cantidadconfirmada")
    private int cantidadconfirmada;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "costo")
    private float costo;
    @Basic(optional = false)
    @Column(name = "iva")
    private float iva;
    @JoinColumns({
        @JoinColumn(name = "idpedido", referencedColumnName = "idpedido", insertable = false, updatable = false),
        @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Pedidoencabezado pedidoencabezado;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sucursal sucursal;

    public Pedidodetalle() {
    }

    public Pedidodetalle(PedidodetallePK pedidodetallePK) {
        this.pedidodetallePK = pedidodetallePK;
    }

    public Pedidodetalle(PedidodetallePK pedidodetallePK, int cantidadsolicitada, int cantidadconfirmada, float precio, float costo, float iva) {
        this.pedidodetallePK = pedidodetallePK;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
        this.costo = costo;
        this.iva = iva;
    }

    public Pedidodetalle(int idpedidodetalle, int idpedido, int idsucursal) {
        this.pedidodetallePK = new PedidodetallePK(idpedidodetalle, idpedido, idsucursal);
    }

    public PedidodetallePK getPedidodetallePK() {
        return pedidodetallePK;
    }

    public void setPedidodetallePK(PedidodetallePK pedidodetallePK) {
        this.pedidodetallePK = pedidodetallePK;
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

    public Pedidoencabezado getPedidoencabezado() {
        return pedidoencabezado;
    }

    public void setPedidoencabezado(Pedidoencabezado pedidoencabezado) {
        this.pedidoencabezado = pedidoencabezado;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
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
        hash += (pedidodetallePK != null ? pedidodetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidodetalle)) {
            return false;
        }
        Pedidodetalle other = (Pedidodetalle) object;
        if ((this.pedidodetallePK == null && other.pedidodetallePK != null) || (this.pedidodetallePK != null && !this.pedidodetallePK.equals(other.pedidodetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Pedidodetalle[ pedidodetallePK=" + pedidodetallePK + " ]";
    }
    
}

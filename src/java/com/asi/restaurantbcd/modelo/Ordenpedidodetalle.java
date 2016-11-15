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
import javax.persistence.Transient;

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
    @EmbeddedId
    protected OrdenpedidodetallePK ordenpedidodetallePK;
    @Basic(optional = false)
    @Column(name = "cantidadsolicitada")
    private float cantidadsolicitada;
    @Basic(optional = false)
    @Column(name = "cantidadconfirmada")
    private float cantidadconfirmada;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "costo")
    private float costo;
    @Basic(optional = false)
    @Column(name = "iva")
    private float iva;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumns({
        @JoinColumn(name = "idordenpedido", referencedColumnName = "idordenpedido", insertable = false, updatable = false),
        @JoinColumn(name = "idSucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Ordenpedido ordenpedido;
    
    @Transient
    private Facturadetinterface factDetInterface;
    
    public Ordenpedidodetalle() {
    }

    public Ordenpedidodetalle(OrdenpedidodetallePK ordenpedidodetallePK) {
        this.ordenpedidodetallePK = ordenpedidodetallePK;
    }

    public Ordenpedidodetalle(OrdenpedidodetallePK ordenpedidodetallePK, int cantidadsolicitada, int cantidadconfirmada, float precio, float costo, float iva) {
        this.ordenpedidodetallePK = ordenpedidodetallePK;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
        this.costo = costo;
        this.iva = iva;
    }

    public Ordenpedidodetalle(int idordenpedidodet, int idordenpedido, int idSucursal) {
        this.ordenpedidodetallePK = new OrdenpedidodetallePK(idordenpedidodet, idordenpedido, idSucursal);
    }

    public OrdenpedidodetallePK getOrdenpedidodetallePK() {
        return ordenpedidodetallePK;
    }

    public void setOrdenpedidodetallePK(OrdenpedidodetallePK ordenpedidodetallePK) {
        this.ordenpedidodetallePK = ordenpedidodetallePK;
    }

    public float getCantidadsolicitada() {
        return cantidadsolicitada;
    }

    public void setCantidadsolicitada(float cantidadsolicitada) {
        this.cantidadsolicitada = cantidadsolicitada;
    }

    public float getCantidadconfirmada() {
        return cantidadconfirmada;
    }

    public void setCantidadconfirmada(float cantidadconfirmada) {
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

    public Ordenpedido getOrdenpedido() {
        return ordenpedido;
    }

    public void setOrdenpedido(Ordenpedido ordenpedido) {
        this.ordenpedido = ordenpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenpedidodetallePK != null ? ordenpedidodetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenpedidodetalle)) {
            return false;
        }
        Ordenpedidodetalle other = (Ordenpedidodetalle) object;
        if ((this.ordenpedidodetallePK == null && other.ordenpedidodetallePK != null) || (this.ordenpedidodetallePK != null && !this.ordenpedidodetallePK.equals(other.ordenpedidodetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ordenpedidodetalle[ ordenpedidodetallePK=" + ordenpedidodetallePK + " ]";
    }

    /**
     * @return the factDetInterface
     */
    public Facturadetinterface getFactDetInterface() {
        return factDetInterface;
    }

    /**
     * @param factDetInterface the factDetInterface to set
     */
    public void setFactDetInterface(Facturadetinterface factDetInterface) {
        this.factDetInterface = factDetInterface;
    }
    
}

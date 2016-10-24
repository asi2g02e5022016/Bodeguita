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
import javax.persistence.JoinColumns;
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
@Table(name = "compradetalle")
@NamedQueries({
    @NamedQuery(name = "Compradetalle.findAll", query = "SELECT c FROM Compradetalle c")})
public class Compradetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompradetalle")
    private Integer idcompradetalle;
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
    @Column(name = "iva")
    private float iva;
        @JoinColumns({
        @JoinColumn(name = "idcompra", referencedColumnName = "idcompra"),
        @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")})
    @ManyToOne
    private Compra compra;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;

    public Compradetalle() {
    }

    public Compradetalle(Integer idcompradetalle) {
        this.idcompradetalle = idcompradetalle;
    }

    public Compradetalle(Integer idcompradetalle, int cantidadsolicitada, 
            int cantidadconfirmada, float precio, float iva) {
        this.idcompradetalle = idcompradetalle;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
        this.iva = iva;
    }

    public Integer getIdcompradetalle() {
        return idcompradetalle;
    }

    public void setIdcompradetalle(Integer idcompradetalle) {
        this.idcompradetalle = idcompradetalle;
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

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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
        hash += (idcompradetalle != null ? idcompradetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compradetalle)) {
            return false;
        }
        Compradetalle other = (Compradetalle) object;
        if ((this.idcompradetalle == null && other.idcompradetalle != null) || (this.idcompradetalle != null && !this.idcompradetalle.equals(other.idcompradetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Compradetalle[ idcompradetalle=" + idcompradetalle + " ]";
    }
    
}

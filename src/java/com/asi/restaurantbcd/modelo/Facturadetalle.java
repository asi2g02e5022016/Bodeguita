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
@Table(name = "facturadetalle")
@NamedQueries({
    @NamedQuery(name = "Facturadetalle.findAll", query = "SELECT f FROM Facturadetalle f")})
public class Facturadetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfactdet")
    private Integer idfactdet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
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
    @JoinColumns({
        @JoinColumn(name = "idfactura", referencedColumnName = "idfactura"),
        @JoinColumn(name = "idserie", referencedColumnName = "idserie"),
        @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")})
    @ManyToOne(optional = false)
    private Facturaencabezado facturaencabezado;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;

    public Facturadetalle() {
    }

    public Facturadetalle(Integer idfactdet) {
        this.idfactdet = idfactdet;
    }

    public Facturadetalle(Integer idfactdet, int cantidad, float precio, float costo, float iva) {
        this.idfactdet = idfactdet;
        this.cantidad = cantidad;
        this.precio = precio;
        this.costo = costo;
        this.iva = iva;
    }

    public Integer getIdfactdet() {
        return idfactdet;
    }

    public void setIdfactdet(Integer idfactdet) {
        this.idfactdet = idfactdet;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public Facturaencabezado getFacturaencabezado() {
        return facturaencabezado;
    }

    public void setFacturaencabezado(Facturaencabezado facturaencabezado) {
        this.facturaencabezado = facturaencabezado;
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
        hash += (idfactdet != null ? idfactdet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturadetalle)) {
            return false;
        }
        Facturadetalle other = (Facturadetalle) object;
        if ((this.idfactdet == null && other.idfactdet != null) || (this.idfactdet != null && !this.idfactdet.equals(other.idfactdet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Facturadetalle[ idfactdet=" + idfactdet + " ]";
    }
    
}

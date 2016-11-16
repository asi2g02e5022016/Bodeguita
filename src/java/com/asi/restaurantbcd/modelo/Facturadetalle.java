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
    private Double cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private Double precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private Double costo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva")
    private Double iva;
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

    public Facturadetalle(Integer idfactdet, Double cantidad, Double precio, Double costo, Double iva) {
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

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
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

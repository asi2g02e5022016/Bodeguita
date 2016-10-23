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
@Table(name = "ordenproducciondetalle")
@NamedQueries({
    @NamedQuery(name = "Ordenproducciondetalle.findAll", query = "SELECT o FROM Ordenproducciondetalle o")})
public class Ordenproducciondetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdenproducciondetallePK ordenproducciondetallePK;
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
        @JoinColumn(name = "idordenproduccion", referencedColumnName = "idordenproduccion", insertable = false, updatable = false),
        @JoinColumn(name = "idSucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Ordenproduccion ordenproduccion;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;

    public Ordenproducciondetalle() {
    }

    public Ordenproducciondetalle(OrdenproducciondetallePK ordenproducciondetallePK) {
        this.ordenproducciondetallePK = ordenproducciondetallePK;
    }

    public Ordenproducciondetalle(OrdenproducciondetallePK ordenproducciondetallePK, int cantidadsolicitada, int cantidadconfirmada, float precio, float costo, float iva) {
        this.ordenproducciondetallePK = ordenproducciondetallePK;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
        this.costo = costo;
        this.iva = iva;
    }

    public Ordenproducciondetalle(int idordenproducciondetalle, int idordenproduccion, int idSucursal) {
        this.ordenproducciondetallePK = new OrdenproducciondetallePK(idordenproducciondetalle, idordenproduccion, idSucursal);
    }

    public OrdenproducciondetallePK getOrdenproducciondetallePK() {
        return ordenproducciondetallePK;
    }

    public void setOrdenproducciondetallePK(OrdenproducciondetallePK ordenproducciondetallePK) {
        this.ordenproducciondetallePK = ordenproducciondetallePK;
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

    public Ordenproduccion getOrdenproduccion() {
        return ordenproduccion;
    }

    public void setOrdenproduccion(Ordenproduccion ordenproduccion) {
        this.ordenproduccion = ordenproduccion;
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
        hash += (ordenproducciondetallePK != null ? ordenproducciondetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenproducciondetalle)) {
            return false;
        }
        Ordenproducciondetalle other = (Ordenproducciondetalle) object;
        if ((this.ordenproducciondetallePK == null && other.ordenproducciondetallePK != null) || (this.ordenproducciondetallePK != null && !this.ordenproducciondetallePK.equals(other.ordenproducciondetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ordenproducciondetalle[ ordenproducciondetallePK=" + ordenproducciondetallePK + " ]";
    }
    
}

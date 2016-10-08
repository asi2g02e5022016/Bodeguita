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
@Table(name = "ordenproducciondetalle")
@NamedQueries({
    @NamedQuery(name = "Ordenproducciondetalle.findAll", query = "SELECT o FROM Ordenproducciondetalle o")})
public class Ordenproducciondetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idordenproducciondetalle")
    private Integer idordenproducciondetalle;
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
    @JoinColumn(name = "idordenproduccion", referencedColumnName = "idordenproduccion")
    @ManyToOne(optional = false)
    private Ordenproduccion idordenproduccion;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;

    public Ordenproducciondetalle() {
    }

    public Ordenproducciondetalle(Integer idordenproducciondetalle) {
        this.idordenproducciondetalle = idordenproducciondetalle;
    }

    public Ordenproducciondetalle(Integer idordenproducciondetalle, int cantidadsolicitada, int cantidadconfirmada, float precio, float costo, float iva) {
        this.idordenproducciondetalle = idordenproducciondetalle;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
        this.costo = costo;
        this.iva = iva;
    }

    public Integer getIdordenproducciondetalle() {
        return idordenproducciondetalle;
    }

    public void setIdordenproducciondetalle(Integer idordenproducciondetalle) {
        this.idordenproducciondetalle = idordenproducciondetalle;
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

    public Ordenproduccion getIdordenproduccion() {
        return idordenproduccion;
    }

    public void setIdordenproduccion(Ordenproduccion idordenproduccion) {
        this.idordenproduccion = idordenproduccion;
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
        hash += (idordenproducciondetalle != null ? idordenproducciondetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenproducciondetalle)) {
            return false;
        }
        Ordenproducciondetalle other = (Ordenproducciondetalle) object;
        if ((this.idordenproducciondetalle == null && other.idordenproducciondetalle != null) || (this.idordenproducciondetalle != null && !this.idordenproducciondetalle.equals(other.idordenproducciondetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ordenproducciondetalle[ idordenproducciondetalle=" + idordenproducciondetalle + " ]";
    }
    
}

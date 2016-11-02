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
    @Column(name = "cantidadconfirmada")
    private Double cantidadconfirmada;
        @Basic(optional = false)
    @Column(name = "factorconvercion")
    private Double factorconvercion ;
    @Column(name = "costounitario")
    private Double costounitario;
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
    public OrdenproducciondetallePK getOrdenproducciondetallePK() {
        return ordenproducciondetallePK;
    }

    public void setOrdenproducciondetallePK(OrdenproducciondetallePK ordenproducciondetallePK) {
        this.ordenproducciondetallePK = ordenproducciondetallePK;
    }

    public Double getCantidadconfirmada() {
        return cantidadconfirmada;
    }

    public void setCantidadconfirmada(Double cantidadconfirmada) {
        this.cantidadconfirmada = cantidadconfirmada;
    }

    public Double getCostounitario() {
        return costounitario;
    }

    public void setCostounitario(Double costounitario) {
        this.costounitario = costounitario;
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

    public Double getFactorconvercion() {
        return factorconvercion;
    }

    public void setFactorconvercion(Double factorconvercion) {
        this.factorconvercion = factorconvercion;
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

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "existencia")
@NamedQueries({
    @NamedQuery(name = "Existencia.findAll", query = "SELECT e FROM Existencia e")})
public class Existencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExistenciaPK existenciaPK;
    @Basic(optional = false)
    @Column(name = "valor")
    private float valor;
    @Basic(optional = false)
    @Column(name = "costounitario")
    private float costounitario;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sucursal sucursal;

    public Existencia() {
    }

    public Existencia(ExistenciaPK existenciaPK) {
        this.existenciaPK = existenciaPK;
    }

    public Existencia(ExistenciaPK existenciaPK, float valor, float costounitario) {
        this.existenciaPK = existenciaPK;
        this.valor = valor;
        this.costounitario = costounitario;
    }

    public Existencia(int idexistencia, int idsucursal) {
        this.existenciaPK = new ExistenciaPK(idexistencia, idsucursal);
    }

    public ExistenciaPK getExistenciaPK() {
        return existenciaPK;
    }

    public void setExistenciaPK(ExistenciaPK existenciaPK) {
        this.existenciaPK = existenciaPK;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getCostounitario() {
        return costounitario;
    }

    public void setCostounitario(float costounitario) {
        this.costounitario = costounitario;
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
        hash += (existenciaPK != null ? existenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Existencia)) {
            return false;
        }
        Existencia other = (Existencia) object;
        if ((this.existenciaPK == null && other.existenciaPK != null) || (this.existenciaPK != null && !this.existenciaPK.equals(other.existenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Existencia[ existenciaPK=" + existenciaPK + " ]";
    }
    
}

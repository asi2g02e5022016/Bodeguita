/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author samaelopez
 */
@Embeddable
public class OrdenproducciondetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idordenproducciondetalle")
    private int idordenproducciondetalle;
    @Basic(optional = false)
    @Column(name = "idordenproduccion")
    private int idordenproduccion;
    @Basic(optional = false)
    @Column(name = "idSucursal")
    private int idSucursal;

    public OrdenproducciondetallePK() {
    }

    public OrdenproducciondetallePK(int idordenproducciondetalle, int idordenproduccion, int idSucursal) {
        this.idordenproducciondetalle = idordenproducciondetalle;
        this.idordenproduccion = idordenproduccion;
        this.idSucursal = idSucursal;
    }

    public int getIdordenproducciondetalle() {
        return idordenproducciondetalle;
    }

    public void setIdordenproducciondetalle(int idordenproducciondetalle) {
        this.idordenproducciondetalle = idordenproducciondetalle;
    }

    public int getIdordenproduccion() {
        return idordenproduccion;
    }

    public void setIdordenproduccion(int idordenproduccion) {
        this.idordenproduccion = idordenproduccion;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idordenproducciondetalle;
        hash += (int) idordenproduccion;
        hash += (int) idSucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenproducciondetallePK)) {
            return false;
        }
        OrdenproducciondetallePK other = (OrdenproducciondetallePK) object;
        if (this.idordenproducciondetalle != other.idordenproducciondetalle) {
            return false;
        }
        if (this.idordenproduccion != other.idordenproduccion) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.OrdenproducciondetallePK[ idordenproducciondetalle=" + idordenproducciondetalle + ", idordenproduccion=" + idordenproduccion + ", idSucursal=" + idSucursal + " ]";
    }
    
}

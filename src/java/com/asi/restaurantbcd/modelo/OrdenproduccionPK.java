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
public class OrdenproduccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idordenproduccion")
    private int idordenproduccion;
    @Basic(optional = false)
    @Column(name = "idsucursal")
    private int idsucursal;

    public OrdenproduccionPK() {
    }

    public OrdenproduccionPK(int idordenproduccion, int idsucursal) {
        this.idordenproduccion = idordenproduccion;
        this.idsucursal = idsucursal;
    }

    public int getIdordenproduccion() {
        return idordenproduccion;
    }

    public void setIdordenproduccion(int idordenproduccion) {
        this.idordenproduccion = idordenproduccion;
    }

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idordenproduccion;
        hash += (int) idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenproduccionPK)) {
            return false;
        }
        OrdenproduccionPK other = (OrdenproduccionPK) object;
        if (this.idordenproduccion != other.idordenproduccion) {
            return false;
        }
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.OrdenproduccionPK[ idordenproduccion=" + idordenproduccion + ", idsucursal=" + idsucursal + " ]";
    }
    
}

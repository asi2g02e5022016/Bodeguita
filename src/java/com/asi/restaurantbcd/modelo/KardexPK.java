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
public class KardexPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idkardex")
    private int idkardex;
    @Basic(optional = false)
    @Column(name = "idsucursal")
    private int idsucursal;

    public KardexPK() {
    }

    public KardexPK(int idkardex, int idsucursal) {
        this.idkardex = idkardex;
        this.idsucursal = idsucursal;
    }

    public int getIdkardex() {
        return idkardex;
    }

    public void setIdkardex(int idkardex) {
        this.idkardex = idkardex;
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
        hash += (int) idkardex;
        hash += (int) idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KardexPK)) {
            return false;
        }
        KardexPK other = (KardexPK) object;
        if (this.idkardex != other.idkardex) {
            return false;
        }
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.KardexPK[ idkardex=" + idkardex + ", idsucursal=" + idsucursal + " ]";
    }
    
}

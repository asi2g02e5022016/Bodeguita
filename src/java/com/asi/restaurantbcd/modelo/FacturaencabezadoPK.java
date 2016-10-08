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
import javax.validation.constraints.NotNull;

/**
 *
 * @author samaelopez
 */
@Embeddable
public class FacturaencabezadoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idfactura")
    private int idfactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idserie")
    private int idserie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsucursal")
    private int idsucursal;

    public FacturaencabezadoPK() {
    }

    public FacturaencabezadoPK(int idfactura, int idserie, int idsucursal) {
        this.idfactura = idfactura;
        this.idserie = idserie;
        this.idsucursal = idsucursal;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public int getIdserie() {
        return idserie;
    }

    public void setIdserie(int idserie) {
        this.idserie = idserie;
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
        hash += (int) idfactura;
        hash += (int) idserie;
        hash += (int) idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaencabezadoPK)) {
            return false;
        }
        FacturaencabezadoPK other = (FacturaencabezadoPK) object;
        if (this.idfactura != other.idfactura) {
            return false;
        }
        if (this.idserie != other.idserie) {
            return false;
        }
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.FacturaencabezadoPK[ idfactura=" + idfactura + ", idserie=" + idserie + ", idsucursal=" + idsucursal + " ]";
    }
    
}

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
public class CompradetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idcompradetalle")
    private int idcompradetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcompra")
    private int idcompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsucursal")
    private int idsucursal;

    public CompradetallePK() {
    }

    public CompradetallePK(int idcompradetalle, int idcompra, int idsucursal) {
        this.idcompradetalle = idcompradetalle;
        this.idcompra = idcompra;
        this.idsucursal = idsucursal;
    }

    public int getIdcompradetalle() {
        return idcompradetalle;
    }

    public void setIdcompradetalle(int idcompradetalle) {
        this.idcompradetalle = idcompradetalle;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
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
        hash += (int) idcompradetalle;
        hash += (int) idcompra;
        hash += (int) idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompradetallePK)) {
            return false;
        }
        CompradetallePK other = (CompradetallePK) object;
        if (this.idcompradetalle != other.idcompradetalle) {
            return false;
        }
        if (this.idcompra != other.idcompra) {
            return false;
        }
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.CompradetallePK[ idcompradetalle=" + idcompradetalle + ", idcompra=" + idcompra + ", idsucursal=" + idsucursal + " ]";
    }
    
}

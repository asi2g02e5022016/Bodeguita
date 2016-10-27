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
public class ExistenciaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idproducto")
    private int idproducto;
    @Basic(optional = false)
    @Column(name = "idsucursal")
    private int idsucursal;

    public ExistenciaPK() {
    }

    public ExistenciaPK(int idproducto, int idsucursal) {
        this.idproducto = idproducto;
        this.idsucursal = idsucursal;
    }

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idproducto;
        hash = 37 * hash + this.idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExistenciaPK other = (ExistenciaPK) obj;
        if (this.idproducto != other.idproducto) {
            return false;
        }
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExistenciaPK{" + "idproducto=" + idproducto + ", idsucursal=" + idsucursal + '}';
    }
    

    
    
}

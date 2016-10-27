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
 * @author PROGRAMADOR
 */
@Embeddable
public class VwexistenciasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idsucursal")
    private int idsucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproducto")
    private int idproducto;

    public VwexistenciasPK() {
    }
    
    public VwexistenciasPK(int idproducto, int idsucursal){
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
        int hash = 3;
        hash = 97 * hash + this.idsucursal;
        hash = 97 * hash + this.idproducto;
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
        final VwexistenciasPK other = (VwexistenciasPK) obj;
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        if (this.idproducto != other.idproducto) {
            return false;
        }
        return true;
    }   
    @Override
    public String toString() {
        return "VwexistenciasPK{" + "idsucursal=" + idsucursal + ", idproducto=" + idproducto + '}';
    }
    
}

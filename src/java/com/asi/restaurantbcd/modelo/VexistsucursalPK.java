/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author PROGRAMADOR
 */

@Embeddable
public class VexistsucursalPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsucursal")
    private int idsucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcompania")
    private Integer idcompania;
        @Column(name = "idproducto")
    
    private Integer idproducto;

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public Integer getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Integer idcompania) {
        this.idcompania = idcompania;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.idsucursal;
        hash = 53 * hash + Objects.hashCode(this.idcompania);
        hash = 53 * hash + Objects.hashCode(this.idproducto);
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
        final VexistsucursalPK other = (VexistsucursalPK) obj;
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        if (!Objects.equals(this.idcompania, other.idcompania)) {
            return false;
        }
        if (!Objects.equals(this.idproducto, other.idproducto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VexistsucursalPK{" + "idsucursal=" 
                + idsucursal + ", idcompania=" + idcompania + ", idproducto=" + idproducto + '}';
    }

        
    
}

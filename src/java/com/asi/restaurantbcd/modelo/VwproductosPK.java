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

/**
 *
 * @author samaelopez
 */
@Embeddable
public class VwproductosPK implements Serializable {
     @Basic(optional = false)
    @Column(name = "idproducto")
    private Integer idproducto;

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }
     
     public VwproductosPK() {
         
     }
     public VwproductosPK(Integer idproducto) {
         
     }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.idproducto);
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
        final VwproductosPK other = (VwproductosPK) obj;
        if (!Objects.equals(this.idproducto, other.idproducto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VwproductosPK{" + "idproducto=" + idproducto + '}';
    }
     
     
     
}

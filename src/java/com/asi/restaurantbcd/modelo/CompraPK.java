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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author samaelopez
 */
@Embeddable
public class CompraPK implements  Serializable {
     @Basic(optional = false)
    @Column(name = "idcompra")
    private Integer idcompra;
      @Basic(optional = false)
    @Column(name = "idsucursal")
    private Integer idsucursal;
    
    public CompraPK() {
        
    }

    public Integer getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idcompra);
        hash = 97 * hash + Objects.hashCode(this.idsucursal);
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
        final CompraPK other = (CompraPK) obj;
        if (!Objects.equals(this.idcompra, other.idcompra)) {
            return false;
        }
        if (!Objects.equals(this.idsucursal, other.idsucursal)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CompraPK{" + "idcompra=" + idcompra + ", idsucursal=" + idsucursal + '}';
    }
    
    
}

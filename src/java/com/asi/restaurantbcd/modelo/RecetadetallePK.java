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
public class RecetadetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idproducto")
    private int idproducto;
    @Basic(optional = false)
    @Column(name = "idreceta")
    private int idreceta;

    public RecetadetallePK() {
    }

    public RecetadetallePK(int idproducto, int idreceta) {
        this.idproducto = idproducto;
        this.idreceta = idreceta;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdreceta() {
        return idreceta;
    }

    public void setIdreceta(int idreceta) {
        this.idreceta = idreceta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idproducto;
        hash += (int) idreceta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetadetallePK)) {
            return false;
        }
        RecetadetallePK other = (RecetadetallePK) object;
        if (this.idproducto != other.idproducto) {
            return false;
        }
        if (this.idreceta != other.idreceta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.RecetadetallePK[ idproducto=" + idproducto + ", idreceta=" + idreceta + " ]";
    }
    
}


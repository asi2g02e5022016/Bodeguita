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
 * @author JAEM
 */
@Embeddable
public class AjustedetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idajustedetalle")
    private int idajustedetalle;
    @Basic(optional = false)
    @Column(name="idajuste")
    private int idajuste;
    @Basic(optional = false)
    @Column(name="idsucursal")
    private int idsucursal;

    public AjustedetallePK() {
    }

    public AjustedetallePK(int idajustedetalle, int idajuste, int idsucursal) {
        this.idajustedetalle = idajustedetalle;
        this.idajuste = idajuste;
        this.idsucursal = idsucursal;
    }

    public int getIdajustedetalle() {
        return idajustedetalle;
    }

    public void setIdajustedetalle(int idajustedetalle) {
        this.idajustedetalle = idajustedetalle;
    }

    public int getIdajuste() {
        return idajuste;
    }

    public void setIdajuste(int idajuste) {
        this.idajuste = idajuste;
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
        hash += (int) idajustedetalle;
        hash += (int) idajuste;
        hash += (int) idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AjustedetallePK)) {
            return false;
        }
        AjustedetallePK other = (AjustedetallePK) object;
        if (this.idajustedetalle != other.idajustedetalle) {
            return false;
        }
        if (this.idajuste != other.idajuste) {
            return false;
        }
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.AjustedetallePK[ idajustedetalle=" + idajustedetalle + ", idajuste=" + idajuste + ", idsucursal=" + idsucursal + " ]";
    }
    
}

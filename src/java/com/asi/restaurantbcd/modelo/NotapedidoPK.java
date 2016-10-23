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
public class NotapedidoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idnotapedido")
    private int idnotapedido;
    @Basic(optional = false)
    @Column(name = "idsucursal")
    private int idsucursal;

    public NotapedidoPK() {
    }

    public NotapedidoPK(int idnotapedido, int idsucursal) {
        this.idnotapedido = idnotapedido;
        this.idsucursal = idsucursal;
    }

    public int getIdnotapedido() {
        return idnotapedido;
    }

    public void setIdnotapedido(int idnotapedido) {
        this.idnotapedido = idnotapedido;
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
        hash += (int) idnotapedido;
        hash += (int) idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotapedidoPK)) {
            return false;
        }
        NotapedidoPK other = (NotapedidoPK) object;
        if (this.idnotapedido != other.idnotapedido) {
            return false;
        }
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.NotapedidoPK[ idnotapedido=" + idnotapedido + ", idsucursal=" + idsucursal + " ]";
    }
    
}

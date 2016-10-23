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
public class NotapedidodetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idnotapeddet")
    private int idnotapeddet;
    @Basic(optional = false)
    @Column(name = "idnotapedido")
    private int idnotapedido;
    @Basic(optional = false)
    @Column(name = "idsucursal")
    private int idsucursal;

    public NotapedidodetallePK() {
    }

    public NotapedidodetallePK(int idnotapeddet, int idnotapedido, int idsucursal) {
        this.idnotapeddet = idnotapeddet;
        this.idnotapedido = idnotapedido;
        this.idsucursal = idsucursal;
    }

    public int getIdnotapeddet() {
        return idnotapeddet;
    }

    public void setIdnotapeddet(int idnotapeddet) {
        this.idnotapeddet = idnotapeddet;
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
        hash += (int) idnotapeddet;
        hash += (int) idnotapedido;
        hash += (int) idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotapedidodetallePK)) {
            return false;
        }
        NotapedidodetallePK other = (NotapedidodetallePK) object;
        if (this.idnotapeddet != other.idnotapeddet) {
            return false;
        }
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
        return "com.asi.restaurantbcd.modelo.NotapedidodetallePK[ idnotapeddet=" + idnotapeddet + ", idnotapedido=" + idnotapedido + ", idsucursal=" + idsucursal + " ]";
    }
    
}

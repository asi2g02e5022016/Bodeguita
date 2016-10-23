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
public class OrdenpedidodetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idordenpedidodet")
    private int idordenpedidodet;
    @Basic(optional = false)
    @Column(name = "idordenpedido")
    private int idordenpedido;
    @Basic(optional = false)
    @Column(name = "idSucursal")
    private int idSucursal;

    public OrdenpedidodetallePK() {
    }

    public OrdenpedidodetallePK(int idordenpedidodet, int idordenpedido, int idSucursal) {
        this.idordenpedidodet = idordenpedidodet;
        this.idordenpedido = idordenpedido;
        this.idSucursal = idSucursal;
    }

    public int getIdordenpedidodet() {
        return idordenpedidodet;
    }

    public void setIdordenpedidodet(int idordenpedidodet) {
        this.idordenpedidodet = idordenpedidodet;
    }

    public int getIdordenpedido() {
        return idordenpedido;
    }

    public void setIdordenpedido(int idordenpedido) {
        this.idordenpedido = idordenpedido;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idordenpedidodet;
        hash += (int) idordenpedido;
        hash += (int) idSucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenpedidodetallePK)) {
            return false;
        }
        OrdenpedidodetallePK other = (OrdenpedidodetallePK) object;
        if (this.idordenpedidodet != other.idordenpedidodet) {
            return false;
        }
        if (this.idordenpedido != other.idordenpedido) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.OrdenpedidodetallePK[ idordenpedidodet=" + idordenpedidodet + ", idordenpedido=" + idordenpedido + ", idSucursal=" + idSucursal + " ]";
    }
    
}

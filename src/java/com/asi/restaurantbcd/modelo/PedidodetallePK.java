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
public class PedidodetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idpedidodetalle")
    private int idpedidodetalle;
    @Basic(optional = false)
    @Column(name = "idpedido")
    private int idpedido;
    @Basic(optional = false)
    @Column(name = "idsucursal")
    private int idsucursal;

    public PedidodetallePK() {
    }

    public PedidodetallePK(int idpedidodetalle, int idpedido, int idsucursal) {
        this.idpedidodetalle = idpedidodetalle;
        this.idpedido = idpedido;
        this.idsucursal = idsucursal;
    }

    public int getIdpedidodetalle() {
        return idpedidodetalle;
    }

    public void setIdpedidodetalle(int idpedidodetalle) {
        this.idpedidodetalle = idpedidodetalle;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
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
        hash += (int) idpedidodetalle;
        hash += (int) idpedido;
        hash += (int) idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidodetallePK)) {
            return false;
        }
        PedidodetallePK other = (PedidodetallePK) object;
        if (this.idpedidodetalle != other.idpedidodetalle) {
            return false;
        }
        if (this.idpedido != other.idpedido) {
            return false;
        }
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.PedidodetallePK[ idpedidodetalle=" + idpedidodetalle + ", idpedido=" + idpedido + ", idsucursal=" + idsucursal + " ]";
    }
    
}

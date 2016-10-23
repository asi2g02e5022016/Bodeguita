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
public class ExistenciaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idexistencia")
    private int idexistencia;
    @Basic(optional = false)
    @Column(name = "idsucursal")
    private int idsucursal;

    public ExistenciaPK() {
    }

    public ExistenciaPK(int idexistencia, int idsucursal) {
        this.idexistencia = idexistencia;
        this.idsucursal = idsucursal;
    }

    public int getIdexistencia() {
        return idexistencia;
    }

    public void setIdexistencia(int idexistencia) {
        this.idexistencia = idexistencia;
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
        hash += (int) idexistencia;
        hash += (int) idsucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExistenciaPK)) {
            return false;
        }
        ExistenciaPK other = (ExistenciaPK) object;
        if (this.idexistencia != other.idexistencia) {
            return false;
        }
        if (this.idsucursal != other.idsucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.ExistenciaPK[ idexistencia=" + idexistencia + ", idsucursal=" + idsucursal + " ]";
    }
    
}

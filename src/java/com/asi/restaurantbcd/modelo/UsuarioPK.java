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
import javax.validation.constraints.NotNull;

/**
 *
 * @author "Joaquin Sanchez SA101110"
 */
@Embeddable
public class UsuarioPK implements Serializable{
    @Basic(optional = false)
    @NotNull
    @Column(name = "idperfil")
    private int idperfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idempleado")
    private int idempleado;
    
    public UsuarioPK(){
    }
    public UsuarioPK(int idperfil, int idempleado){
        this.idperfil = idperfil;
        this.idempleado = idempleado;
    }

    public int getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idperfil;
        hash = 83 * hash + this.idempleado;
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
        final UsuarioPK other = (UsuarioPK) obj;
        if (this.idperfil != other.idperfil) {
            return false;
        }
        if (this.idempleado != other.idempleado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioPK{" + "idperfil=" + idperfil + ", idempleado=" + idempleado + '}';
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "condicionpag")
@NamedQueries({
    @NamedQuery(name = "Condicionpag.findAll", query = "SELECT c FROM Condicionpag c")})
public class Condicionpag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCondicion")
    private Integer idCondicion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "condicionPago")
    private String condicionPago;

    public Condicionpag() {
    }

    public Condicionpag(Integer idCondicion) {
        this.idCondicion = idCondicion;
    }

    public Condicionpag(Integer idCondicion, String condicionPago) {
        this.idCondicion = idCondicion;
        this.condicionPago = condicionPago;
    }

    public Integer getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(Integer idCondicion) {
        this.idCondicion = idCondicion;
    }

    public String getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(String condicionPago) {
        this.condicionPago = condicionPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCondicion != null ? idCondicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condicionpag)) {
            return false;
        }
        Condicionpag other = (Condicionpag) object;
        if ((this.idCondicion == null && other.idCondicion != null) || (this.idCondicion != null && !this.idCondicion.equals(other.idCondicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Condicionpag[ idCondicion=" + idCondicion + " ]";
    }
    
}

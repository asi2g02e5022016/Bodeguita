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
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "tipoproducto")
@NamedQueries({
    @NamedQuery(name = "Tipoproducto.findAll", query = "SELECT t FROM Tipoproducto t")})
public class Tipoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoProducto")
    private Integer idTipoProducto;
    @Size(max = 255)
    @Column(name = "tipoProducto")
    private String tipoProducto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoProducto")
    private Integer idTipoProducto1;
    @Size(max = 255)
    @Column(name = "tipoProducto")
    private String tipoProducto1;

    public Tipoproducto() {
    }

    public Tipoproducto(Integer idTipoProducto1) {
        this.idTipoProducto1 = idTipoProducto1;
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Integer getIdTipoProducto1() {
        return idTipoProducto1;
    }

    public void setIdTipoProducto1(Integer idTipoProducto1) {
        this.idTipoProducto1 = idTipoProducto1;
    }

    public String getTipoProducto1() {
        return tipoProducto1;
    }

    public void setTipoProducto1(String tipoProducto1) {
        this.tipoProducto1 = tipoProducto1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoProducto1 != null ? idTipoProducto1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoproducto)) {
            return false;
        }
        Tipoproducto other = (Tipoproducto) object;
        if ((this.idTipoProducto1 == null && other.idTipoProducto1 != null) || (this.idTipoProducto1 != null && !this.idTipoProducto1.equals(other.idTipoProducto1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Tipoproducto[ idTipoProducto1=" + idTipoProducto1 + " ]";
    }
    
}

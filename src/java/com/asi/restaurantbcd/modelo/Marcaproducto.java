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
@Table(name = "marcaproducto")
@NamedQueries({
    @NamedQuery(name = "Marcaproducto.findAll", query = "SELECT m FROM Marcaproducto m")})
public class Marcaproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMarcaProducto")
    private Integer idMarcaProducto;
    @Size(max = 255)
    @Column(name = "marcaProducto")
    private String marcaProducto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMarcaProducto")
    private Integer idMarcaProducto1;
    @Size(max = 255)
    @Column(name = "marcaProducto")
    private String marcaProducto1;

    public Marcaproducto() {
    }

    public Marcaproducto(Integer idMarcaProducto1) {
        this.idMarcaProducto1 = idMarcaProducto1;
    }

    public Integer getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(Integer idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public Integer getIdMarcaProducto1() {
        return idMarcaProducto1;
    }

    public void setIdMarcaProducto1(Integer idMarcaProducto1) {
        this.idMarcaProducto1 = idMarcaProducto1;
    }

    public String getMarcaProducto1() {
        return marcaProducto1;
    }

    public void setMarcaProducto1(String marcaProducto1) {
        this.marcaProducto1 = marcaProducto1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarcaProducto1 != null ? idMarcaProducto1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcaproducto)) {
            return false;
        }
        Marcaproducto other = (Marcaproducto) object;
        if ((this.idMarcaProducto1 == null && other.idMarcaProducto1 != null) || (this.idMarcaProducto1 != null && !this.idMarcaProducto1.equals(other.idMarcaProducto1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Marcaproducto[ idMarcaProducto1=" + idMarcaProducto1 + " ]";
    }
    
}

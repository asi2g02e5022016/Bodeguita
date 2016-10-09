/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @Column(name = "idmarcaproducto")
    private Integer idmarcaproducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "marcaproducto")
    private String marcaproducto;

    public Marcaproducto() {
    }

    public Marcaproducto(Integer idmarcaproducto) {
        this.idmarcaproducto = idmarcaproducto;
    }

    public Marcaproducto(Integer idmarcaproducto, String marcaproducto) {
        this.idmarcaproducto = idmarcaproducto;
        this.marcaproducto = marcaproducto;
    }

    public Integer getIdmarcaproducto() {
        return idmarcaproducto;
    }

    public void setIdmarcaproducto(Integer idmarcaproducto) {
        this.idmarcaproducto = idmarcaproducto;
    }

    public String getMarcaproducto() {
        return marcaproducto;
    }

    public void setMarcaproducto(String marcaproducto) {
        this.marcaproducto = marcaproducto;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmarcaproducto != null ? idmarcaproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcaproducto)) {
            return false;
        }
        Marcaproducto other = (Marcaproducto) object;
        if ((this.idmarcaproducto == null && other.idmarcaproducto != null) || (this.idmarcaproducto != null && !this.idmarcaproducto.equals(other.idmarcaproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Marcaproducto[ idmarcaproducto=" + idmarcaproducto + " ]";
    }
    
}

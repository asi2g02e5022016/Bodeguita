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
@Table(name = "tipoproducto")
@NamedQueries({
    @NamedQuery(name = "Tipoproducto.findAll", query = "SELECT t FROM Tipoproducto t")})
public class Tipoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoproducto")
    private Integer idtipoproducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipoproducto")
    private String tipoproducto;

    public Tipoproducto() {
    }

    public Tipoproducto(Integer idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }

    public Tipoproducto(Integer idtipoproducto, String tipoproducto) {
        this.idtipoproducto = idtipoproducto;
        this.tipoproducto = tipoproducto;
    }

    public Integer getIdtipoproducto() {
        return idtipoproducto;
    }

    public void setIdtipoproducto(Integer idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }

    public String getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(String tipoproducto) {
        this.tipoproducto = tipoproducto;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoproducto != null ? idtipoproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoproducto)) {
            return false;
        }
        Tipoproducto other = (Tipoproducto) object;
        if ((this.idtipoproducto == null && other.idtipoproducto != null) || (this.idtipoproducto != null && !this.idtipoproducto.equals(other.idtipoproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Tipoproducto[ idtipoproducto=" + idtipoproducto + " ]";
    }
    
}

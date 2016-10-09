/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "giro")
@NamedQueries({
    @NamedQuery(name = "Giro.findAll", query = "SELECT g FROM Giro g")})
public class Giro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgiro")
    private Integer idgiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "giro")
    private String giro;

    public Giro() {
    }

    public Giro(Integer idgiro) {
        this.idgiro = idgiro;
    }

    public Giro(Integer idgiro, String giro) {
        this.idgiro = idgiro;
        this.giro = giro;
    }

    public Integer getIdgiro() {
        return idgiro;
    }

    public void setIdgiro(Integer idgiro) {
        this.idgiro = idgiro;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgiro != null ? idgiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Giro)) {
            return false;
        }
        Giro other = (Giro) object;
        if ((this.idgiro == null && other.idgiro != null) || (this.idgiro != null && !this.idgiro.equals(other.idgiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Giro[ idgiro=" + idgiro + " ]";
    }
    
}

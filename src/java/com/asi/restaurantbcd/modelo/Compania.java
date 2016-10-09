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
@Table(name = "compania")
@NamedQueries({
    @NamedQuery(name = "Compania.findAll", query = "SELECT c FROM Compania c")})
public class Compania implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompania")
    private Integer idcompania;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "compania")
    private String compania;

    public Compania() {
    }

    public Compania(Integer idcompania) {
        this.idcompania = idcompania;
    }

    public Compania(Integer idcompania, String compania) {
        this.idcompania = idcompania;
        this.compania = compania;
    }

    public Integer getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Integer idcompania) {
        this.idcompania = idcompania;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompania != null ? idcompania.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compania)) {
            return false;
        }
        Compania other = (Compania) object;
        if ((this.idcompania == null && other.idcompania != null) || (this.idcompania != null && !this.idcompania.equals(other.idcompania))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Compania[ idcompania=" + idcompania + " ]";
    }
    
}

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JAEM
 */
@Entity
@Table(name = "compania")
public class Compania implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompania")
    private List<Sucursal> sucursalList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCompania")
    private Integer idCompania;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "compania")
    private String compania;

    public Compania() {
    }

    public Compania(Integer idCompania) {
        this.idCompania = idCompania;
    }

    public Compania(Integer idCompania, String compania) {
        this.idCompania = idCompania;
        this.compania = compania;
    }

    public Integer getIdCompania() {
        return idCompania;
    }

    public void setIdCompania(Integer idCompania) {
        this.idCompania = idCompania;
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
        hash += (idCompania != null ? idCompania.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compania)) {
            return false;
        }
        Compania other = (Compania) object;
        if ((this.idCompania == null && other.idCompania != null) || (this.idCompania != null && !this.idCompania.equals(other.idCompania))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Compania[ idCompania=" + idCompania + " ]";
    }

    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }
    
}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author siman
 */
@Entity
@Table(name = "piso")
public class Piso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idpiso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpiso;
    
    @Column(name = "nombre")
    private String nombre;
    
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", nullable = false)
    @ManyToOne(optional = false)
    private Sucursal idsucursal;
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpiso")
    private List<Mesa> mesaList;

    public Piso() {
    }

    public Piso(Integer idpiso) {
        this.idpiso = idpiso;
    }

    public Piso(Integer idpiso, String nombre) {
        this.idpiso = idpiso;
        this.nombre = nombre;
    }

    public Integer getIdpiso() {
        return idpiso;
    }

    public void setIdpiso(Integer idpiso) {
        this.idpiso = idpiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Mesa> getMesaList() {
        return mesaList;
    }

    public void setMesaList(List<Mesa> mesaList) {
        this.mesaList = mesaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpiso != null ? idpiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piso)) {
            return false;
        }
        Piso other = (Piso) object;
        if ((this.idpiso == null && other.idpiso != null) || (this.idpiso != null && !this.idpiso.equals(other.idpiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Piso[ idpiso=" + idpiso + " ]";
    }

    /**
     * @return the idsucursal
     */
    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    /**
     * @param idsucursal the idsucursal to set
     */
    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
    }
    
}

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
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "medida")
@NamedQueries({
    @NamedQuery(name = "Medida.findAll", query = "SELECT m FROM Medida m")})
public class Medida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmedida")
    private Integer idmedida;
    @Size(max = 25)
    @Column(name = "medida")
    private String medida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedida")
    private List<Producto> productoList;
    @OneToMany(mappedBy = "idmedida")
    private List<Conversionmedida> conversionmedidaList;

    public Medida() {
    }

    public Medida(Integer idmedida) {
        this.idmedida = idmedida;
    }

    public Integer getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(Integer idmedida) {
        this.idmedida = idmedida;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public List<Conversionmedida> getConversionmedidaList() {
        return conversionmedidaList;
    }

    public void setConversionmedidaList(List<Conversionmedida> conversionmedidaList) {
        this.conversionmedidaList = conversionmedidaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedida != null ? idmedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medida)) {
            return false;
        }
        Medida other = (Medida) object;
        if ((this.idmedida == null && other.idmedida != null) || (this.idmedida != null && !this.idmedida.equals(other.idmedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Medida[ idmedida=" + idmedida + " ]";
    }
    
}

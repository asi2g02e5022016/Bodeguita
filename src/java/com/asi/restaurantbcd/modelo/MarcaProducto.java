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
@Table(name = "marcaProducto")
@NamedQueries({
    @NamedQuery(name = "MarcaProducto.findAll", query = "SELECT m FROM MarcaProducto m")})
public class MarcaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMarcaProducto")
    private Integer idMarcaProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "marcaProducto")
    private String marcaProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMarcaProducto")
    private List<Producto> productoList;

    public MarcaProducto() {
    }

    public MarcaProducto(Integer idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    public MarcaProducto(Integer idMarcaProducto, String marcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
        this.marcaProducto = marcaProducto;
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

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarcaProducto != null ? idMarcaProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarcaProducto)) {
            return false;
        }
        MarcaProducto other = (MarcaProducto) object;
        if ((this.idMarcaProducto == null && other.idMarcaProducto != null) || (this.idMarcaProducto != null && !this.idMarcaProducto.equals(other.idMarcaProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.MarcaProducto[ idMarcaProducto=" + idMarcaProducto + " ]";
    }
    
}

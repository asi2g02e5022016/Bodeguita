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
 * @author samaelopez
 */
@Entity
@Table(name = "grupoProducto")
@NamedQueries({
    @NamedQuery(name = "GrupoProducto.findAll", query = "SELECT g FROM GrupoProducto g")})
public class GrupoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupoProducto")
    private Integer idGrupoProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "grupoProducto")
    private String grupoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivel")
    private int nivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoDependencia")
    private List<GrupoProducto> grupoProductoList;
    @JoinColumn(name = "grupoDependencia", referencedColumnName = "idGrupoProducto")
    @ManyToOne(optional = false)
    private GrupoProducto grupoDependencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupoProducto")
    private List<Producto> productoList;

    public GrupoProducto() {
    }

    public GrupoProducto(Integer idGrupoProducto) {
        this.idGrupoProducto = idGrupoProducto;
    }

    public GrupoProducto(Integer idGrupoProducto, String grupoProducto, int nivel) {
        this.idGrupoProducto = idGrupoProducto;
        this.grupoProducto = grupoProducto;
        this.nivel = nivel;
    }

    public Integer getIdGrupoProducto() {
        return idGrupoProducto;
    }

    public void setIdGrupoProducto(Integer idGrupoProducto) {
        this.idGrupoProducto = idGrupoProducto;
    }

    public String getGrupoProducto() {
        return grupoProducto;
    }

    public void setGrupoProducto(String grupoProducto) {
        this.grupoProducto = grupoProducto;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public List<GrupoProducto> getGrupoProductoList() {
        return grupoProductoList;
    }

    public void setGrupoProductoList(List<GrupoProducto> grupoProductoList) {
        this.grupoProductoList = grupoProductoList;
    }

    public GrupoProducto getGrupoDependencia() {
        return grupoDependencia;
    }

    public void setGrupoDependencia(GrupoProducto grupoDependencia) {
        this.grupoDependencia = grupoDependencia;
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
        hash += (idGrupoProducto != null ? idGrupoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoProducto)) {
            return false;
        }
        GrupoProducto other = (GrupoProducto) object;
        if ((this.idGrupoProducto == null && other.idGrupoProducto != null) || (this.idGrupoProducto != null && !this.idGrupoProducto.equals(other.idGrupoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.GrupoProducto[ idGrupoProducto=" + idGrupoProducto + " ]";
    }
    
}

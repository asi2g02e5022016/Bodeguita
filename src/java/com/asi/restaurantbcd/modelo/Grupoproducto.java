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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "grupoproducto")
@NamedQueries({
    @NamedQuery(name = "Grupoproducto.findAll", query = "SELECT g FROM Grupoproducto g")})
public class Grupoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupoProducto")
    private Integer idGrupoProducto;
    @Size(max = 255)
    @Column(name = "grupoProducto")
    private String grupoProducto;
    @Column(name = "nivel")
    private Integer nivel;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGrupoProducto")
    private Integer idGrupoProducto1;
    @Size(max = 255)
    @Column(name = "grupoProducto")
    private String grupoProducto1;
    @Column(name = "nivel")
    private Integer nivel1;
    @OneToMany(mappedBy = "grupoDependencia")
    private List<Grupoproducto> grupoproductoList;
    @JoinColumn(name = "grupoDependencia", referencedColumnName = "idGrupoProducto")
    @ManyToOne
    private Grupoproducto grupoDependencia;

    public Grupoproducto() {
    }

    public Grupoproducto(Integer idGrupoProducto1) {
        this.idGrupoProducto1 = idGrupoProducto1;
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getIdGrupoProducto1() {
        return idGrupoProducto1;
    }

    public void setIdGrupoProducto1(Integer idGrupoProducto1) {
        this.idGrupoProducto1 = idGrupoProducto1;
    }

    public String getGrupoProducto1() {
        return grupoProducto1;
    }

    public void setGrupoProducto1(String grupoProducto1) {
        this.grupoProducto1 = grupoProducto1;
    }

    public Integer getNivel1() {
        return nivel1;
    }

    public void setNivel1(Integer nivel1) {
        this.nivel1 = nivel1;
    }

    public List<Grupoproducto> getGrupoproductoList() {
        return grupoproductoList;
    }

    public void setGrupoproductoList(List<Grupoproducto> grupoproductoList) {
        this.grupoproductoList = grupoproductoList;
    }

    public Grupoproducto getGrupoDependencia() {
        return grupoDependencia;
    }

    public void setGrupoDependencia(Grupoproducto grupoDependencia) {
        this.grupoDependencia = grupoDependencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoProducto1 != null ? idGrupoProducto1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoproducto)) {
            return false;
        }
        Grupoproducto other = (Grupoproducto) object;
        if ((this.idGrupoProducto1 == null && other.idGrupoProducto1 != null) || (this.idGrupoProducto1 != null && !this.idGrupoProducto1.equals(other.idGrupoProducto1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Grupoproducto[ idGrupoProducto1=" + idGrupoProducto1 + " ]";
    }
    
}

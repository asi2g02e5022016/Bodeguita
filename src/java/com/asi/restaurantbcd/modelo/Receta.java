/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "receta")
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r")})
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idreceta")
    private Integer idreceta;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "idusuariocrea")
    private String idusuariocrea;
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receta")
    private List<Recetadetalle> recetadetalleList;

    public Receta() {
    }

    public Receta(Integer idreceta) {
        this.idreceta = idreceta;
    }

    public Integer getIdreceta() {
        return idreceta;
    }

    public void setIdreceta(Integer idreceta) {
        this.idreceta = idreceta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getIdusuariocrea() {
        return idusuariocrea;
    }

    public void setIdusuariocrea(String idusuariocrea) {
        this.idusuariocrea = idusuariocrea;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public List<Recetadetalle> getRecetadetalleList() {
        return recetadetalleList;
    }

    public void setRecetadetalleList(List<Recetadetalle> recetadetalleList) {
        this.recetadetalleList = recetadetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreceta != null ? idreceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.idreceta == null && other.idreceta != null) || (this.idreceta != null && !this.idreceta.equals(other.idreceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Receta[ idreceta=" + idreceta + " ]";
    }
    
}
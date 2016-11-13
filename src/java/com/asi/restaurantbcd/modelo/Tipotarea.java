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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JAEM
 */
@Entity
@Table(name = "tipotarea")
@NamedQueries({
    @NamedQuery(name = "Tipotarea.findAll", query = "SELECT t FROM Tipotarea t")})
public class Tipotarea implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "idtipotarea")
    private String idtipotarea;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipotarea")
    private List<Tarea> tareaList;

    private static final long serialVersionUID = 1L;


    public Tipotarea() {
    }

    public Tipotarea(String idtipotarea) {
        this.idtipotarea = idtipotarea;
    }

    public String getIdtipotarea() {
        return idtipotarea;
    }

    public void setIdtipotarea(String idtipotarea) {
        this.idtipotarea = idtipotarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipotarea != null ? idtipotarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipotarea)) {
            return false;
        }
        Tipotarea other = (Tipotarea) object;
        if ((this.idtipotarea == null && other.idtipotarea != null) || (this.idtipotarea != null && !this.idtipotarea.equals(other.idtipotarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Tipotarea[ idtipotarea=" + idtipotarea + " ]";
    }

    
    public List<Tarea> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tarea> tareaList) {
        this.tareaList = tareaList;
    }

    
}

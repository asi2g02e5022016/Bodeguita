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
import javax.persistence.Lob;
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
@Table(name = "tarea")
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t")})
public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idtarea;
    @Basic(optional = false)    
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String nombre;
    @Basic(optional = false)    
    @Size(min = 1, max = 5)
    @Column(nullable = false, length = 5)
    private String idtipotarea;
    @Size(max = 255)
    @Column(length = 255)
    private String ejecutable;
    @Basic(optional = false)    
    @Lob
    @Column(nullable = false)
    private byte[] activo;
    @OneToMany(mappedBy = "idtarea")
    private List<Programaciondetalle> programaciondetalleList;

    public Tarea() {
    }

    public Tarea(Integer idtarea) {
        this.idtarea = idtarea;
    }

    public Tarea(Integer idtarea, String nombre, String idtipotarea, byte[] activo) {
        this.idtarea = idtarea;
        this.nombre = nombre;
        this.idtipotarea = idtipotarea;
        this.activo = activo;
    }

    public Integer getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(Integer idtarea) {
        this.idtarea = idtarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdtipotarea() {
        return idtipotarea;
    }

    public void setIdtipotarea(String idtipotarea) {
        this.idtipotarea = idtipotarea;
    }

    public String getEjecutable() {
        return ejecutable;
    }

    public void setEjecutable(String ejecutable) {
        this.ejecutable = ejecutable;
    }

    public byte[] getActivo() {
        return activo;
    }

    public void setActivo(byte[] activo) {
        this.activo = activo;
    }

    public List<Programaciondetalle> getProgramaciondetalleList() {
        return programaciondetalleList;
    }

    public void setProgramaciondetalleList(List<Programaciondetalle> programaciondetalleList) {
        this.programaciondetalleList = programaciondetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarea != null ? idtarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarea)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.idtarea == null && other.idtarea != null) || (this.idtarea != null && !this.idtarea.equals(other.idtarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Tarea[ idtarea=" + idtarea + " ]";
    }
    
}

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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JAEM
 */
@Entity
@Table(name = "programaciontareas")
@NamedQueries({
    @NamedQuery(name = "Programaciontareas.findAll", query = "SELECT p FROM Programaciontareas p")})
public class Programaciontareas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprogramacion")
    private Integer idprogramacion;
    @Column(name = "inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Column(name = "fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;
    @Column(name = "frecuencia")
    private Double frecuencia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprogramacion")
    private List<Programaciondetalle> programaciondetalleList;

    public Programaciontareas() {
    }

    public Programaciontareas(Integer idprogramacion) {
        this.idprogramacion = idprogramacion;
    }

    public Integer getIdprogramacion() {
        return idprogramacion;
    }

    public void setIdprogramacion(Integer idprogramacion) {
        this.idprogramacion = idprogramacion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Double frecuencia) {
        this.frecuencia = frecuencia;
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
        hash += (idprogramacion != null ? idprogramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programaciontareas)) {
            return false;
        }
        Programaciontareas other = (Programaciontareas) object;
        if ((this.idprogramacion == null && other.idprogramacion != null) || (this.idprogramacion != null && !this.idprogramacion.equals(other.idprogramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Programaciontareas[ idprogramacion=" + idprogramacion + " ]";
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author siman
 */
@Entity
@Table(name = "programaciondetalle", catalog = "ifbc", schema = "")
@NamedQueries({
    @NamedQuery(name = "Programaciondetalle.findAll", query = "SELECT p FROM Programaciondetalle p")})
public class Programaciondetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Size(max = 255)
    @Column(name = "parametros")
    private String parametros;
    @JoinColumn(name = "idprogramacion", referencedColumnName = "idprogramacion")
    @ManyToOne(optional = false)
    private Programaciontareas idprogramacion;
    @JoinColumn(name = "idtarea", referencedColumnName = "idtarea")
    @ManyToOne
    private Tarea idtarea;
    @JoinColumn(name = "host", referencedColumnName = "idconfiguracion")
    @ManyToOne(optional = false)
    private Configuracion host;

    public Programaciondetalle() {
    }

    public Programaciondetalle(Integer id) {
        this.id = id;
    }

    public Programaciondetalle(Integer id, int orden) {
        this.id = id;
        this.orden = orden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public Programaciontareas getIdprogramacion() {
        return idprogramacion;
    }

    public void setIdprogramacion(Programaciontareas idprogramacion) {
        this.idprogramacion = idprogramacion;
    }

    public Tarea getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(Tarea idtarea) {
        this.idtarea = idtarea;
    }

    public Configuracion getHost() {
        return host;
    }

    public void setHost(Configuracion host) {
        this.host = host;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programaciondetalle)) {
            return false;
        }
        Programaciondetalle other = (Programaciondetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Programaciondetalle[ id=" + id + " ]";
    }
    
}

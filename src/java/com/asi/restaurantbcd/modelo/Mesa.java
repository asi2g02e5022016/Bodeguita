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
@Table(name = "mesa")
public class Mesa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idmesa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmesa;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "X")
    private int x;

    @Column(name = "Y")
    private int y;
    
    @JoinColumn(name = "idpiso", referencedColumnName = "idpiso")
    @ManyToOne(optional = false)
    private Piso idpiso;

    public Mesa() {
    }

    public Mesa(Integer idmesa) {
        this.idmesa = idmesa;
    }

    public Mesa(Integer idmesa, String nombre, int x, int y) {
        this.idmesa = idmesa;
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public Integer getIdmesa() {
        return idmesa;
    }

    public void setIdmesa(Integer idmesa) {
        this.idmesa = idmesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Piso getIdpiso() {
        return idpiso;
    }

    public void setIdpiso(Piso idpiso) {
        this.idpiso = idpiso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmesa != null ? idmesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.idmesa == null && other.idmesa != null) || (this.idmesa != null && !this.idmesa.equals(other.idmesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Mesa[ idmesa=" + idmesa + " ]";
    }
    
}

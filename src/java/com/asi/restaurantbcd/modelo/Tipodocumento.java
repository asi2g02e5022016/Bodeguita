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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "tipodocumento")
@NamedQueries({
    @NamedQuery(name = "Tipodocumento.findAll", query = "SELECT t FROM Tipodocumento t")})
public class Tipodocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoDocumento")
    private Integer idTipoDocumento;
    @Size(max = 255)
    @Column(name = "tipoDocumento")
    private String tipoDocumento;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoDocumento")
    private Integer idTipoDocumento1;
    @Size(max = 255)
    @Column(name = "tipoDocumento")
    private String tipoDocumento1;

    public Tipodocumento() {
    }

    public Tipodocumento(Integer idTipoDocumento1) {
        this.idTipoDocumento1 = idTipoDocumento1;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getIdTipoDocumento1() {
        return idTipoDocumento1;
    }

    public void setIdTipoDocumento1(Integer idTipoDocumento1) {
        this.idTipoDocumento1 = idTipoDocumento1;
    }

    public String getTipoDocumento1() {
        return tipoDocumento1;
    }

    public void setTipoDocumento1(String tipoDocumento1) {
        this.tipoDocumento1 = tipoDocumento1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento1 != null ? idTipoDocumento1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodocumento)) {
            return false;
        }
        Tipodocumento other = (Tipodocumento) object;
        if ((this.idTipoDocumento1 == null && other.idTipoDocumento1 != null) || (this.idTipoDocumento1 != null && !this.idTipoDocumento1.equals(other.idTipoDocumento1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Tipodocumento[ idTipoDocumento1=" + idTipoDocumento1 + " ]";
    }
    
}

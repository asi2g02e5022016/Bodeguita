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

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "conversionmedida")
@NamedQueries({
    @NamedQuery(name = "Conversionmedida.findAll", query = "SELECT c FROM Conversionmedida c")})
public class Conversionmedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconversionmedida")
    private Integer idconversionmedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidadmedini")
    private float unidadmedini;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidadmedfin")
    private float unidadmedfin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "factor")
    private float factor;
    @JoinColumn(name = "idmedida", referencedColumnName = "idmedida")
    @ManyToOne
    private Medida idmedida;

    public Conversionmedida() {
    }

    public Conversionmedida(Integer idconversionmedida) {
        this.idconversionmedida = idconversionmedida;
    }

    public Conversionmedida(Integer idconversionmedida, float unidadmedini, float unidadmedfin, float factor) {
        this.idconversionmedida = idconversionmedida;
        this.unidadmedini = unidadmedini;
        this.unidadmedfin = unidadmedfin;
        this.factor = factor;
    }

    public Integer getIdconversionmedida() {
        return idconversionmedida;
    }

    public void setIdconversionmedida(Integer idconversionmedida) {
        this.idconversionmedida = idconversionmedida;
    }

    public float getUnidadmedini() {
        return unidadmedini;
    }

    public void setUnidadmedini(float unidadmedini) {
        this.unidadmedini = unidadmedini;
    }

    public float getUnidadmedfin() {
        return unidadmedfin;
    }

    public void setUnidadmedfin(float unidadmedfin) {
        this.unidadmedfin = unidadmedfin;
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public Medida getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(Medida idmedida) {
        this.idmedida = idmedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconversionmedida != null ? idconversionmedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conversionmedida)) {
            return false;
        }
        Conversionmedida other = (Conversionmedida) object;
        if ((this.idconversionmedida == null && other.idconversionmedida != null) || (this.idconversionmedida != null && !this.idconversionmedida.equals(other.idconversionmedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Conversionmedida[ idconversionmedida=" + idconversionmedida + " ]";
    }
    
}

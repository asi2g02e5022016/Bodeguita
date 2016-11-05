/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Objects;
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
import javax.persistence.Transient;

/**
 *
 * @author quincho
 */
@Entity
@Table(name = "medida")
@NamedQueries({
    @NamedQuery(name = "Medida.findAll", query = "SELECT m FROM Medida m")})
public class Medida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmedida")
    private Integer idmedida;
    @Basic(optional = false)
    @Column(name = "medida")
    private String medida;
    @Basic(optional = false)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @Column(name = "conversion")
    private double conversion;
    
    @JoinColumn(name = "idMedidaBase", referencedColumnName = "idmedida")
    @ManyToOne(optional = true)
    private Medida idMedidaBase;
    
    @Transient
    private boolean medidaBase;
    
    @Transient
    private Medida medidaPadre;

    public Medida() {
    }

    /*public Medida(Integer idmedida) {
        this.idmedida = idmedida;
    }

    public Integer getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(Integer idmedida) {
        this.idmedida = idmedida;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedida != null ? idmedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medida)) {
            return false;
        }
        Medida other = (Medida) object;
        if ((this.idmedida == null && other.idmedida != null) || (this.idmedida != null && !this.idmedida.equals(other.idmedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Medida[ idmedida=" + idmedida + " ]";
    }*/

    public Integer getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(Integer idmedida) {
        this.idmedida = idmedida;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getConversion() {
        return conversion;
    }

    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idmedida);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medida other = (Medida) obj;
        if (!Objects.equals(this.idmedida, other.idmedida)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medida{" + "idmedida=" + idmedida + ", medida=" + medida + ", referencia=" + referencia + ", conversion=" + conversion + '}';
    }
    

    /**
     * @return the idMedidaBase
     */
    public Medida getIdMedidaBase() {
        return idMedidaBase;
    }

    /**
     * @param idMedidaBase the idMedidaBase to set
     */
    public void setIdMedidaBase(Medida idMedidaBase) {
        this.idMedidaBase = idMedidaBase;
    }

    /**
     * @return the medidaBase
     */
    public boolean isMedidaBase() {
        medidaBase = false;
        if(this.getIdMedidaBase() == null || this.getIdmedida().equals(this.getIdMedidaBase().getIdmedida()) ){
             medidaBase = true;
        }
        return medidaBase;
    }

    /**
     * @return the medidaPadre
     */
    public Medida getMedidaPadre() {
        medidaPadre = this.getIdMedidaBase();
        if (medidaPadre==null){
          medidaPadre = this;
        }
        return medidaPadre;
    }


    
    
    
}

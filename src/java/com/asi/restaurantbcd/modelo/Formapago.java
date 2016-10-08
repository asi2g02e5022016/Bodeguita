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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "formapago")
@NamedQueries({
    @NamedQuery(name = "Formapago.findAll", query = "SELECT f FROM Formapago f")})
public class Formapago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idformapago")
    private Integer idformapago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "formapago")
    private String formapago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idformapago")
    private List<Facturaencabezado> facturaencabezadoList;

    public Formapago() {
    }

    public Formapago(Integer idformapago) {
        this.idformapago = idformapago;
    }

    public Formapago(Integer idformapago, String formapago) {
        this.idformapago = idformapago;
        this.formapago = formapago;
    }

    public Integer getIdformapago() {
        return idformapago;
    }

    public void setIdformapago(Integer idformapago) {
        this.idformapago = idformapago;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public List<Facturaencabezado> getFacturaencabezadoList() {
        return facturaencabezadoList;
    }

    public void setFacturaencabezadoList(List<Facturaencabezado> facturaencabezadoList) {
        this.facturaencabezadoList = facturaencabezadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformapago != null ? idformapago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formapago)) {
            return false;
        }
        Formapago other = (Formapago) object;
        if ((this.idformapago == null && other.idformapago != null) || (this.idformapago != null && !this.idformapago.equals(other.idformapago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Formapago[ idformapago=" + idformapago + " ]";
    }
    
}

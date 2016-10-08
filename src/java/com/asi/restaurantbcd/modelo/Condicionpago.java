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
@Table(name = "condicionpago")
@NamedQueries({
    @NamedQuery(name = "Condicionpago.findAll", query = "SELECT c FROM Condicionpago c")})
public class Condicionpago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcondicionpago")
    private Integer idcondicionpago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "condicionpago")
    private String condicionpago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcondicionpago")
    private List<Facturaencabezado> facturaencabezadoList;

    public Condicionpago() {
    }

    public Condicionpago(Integer idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }

    public Condicionpago(Integer idcondicionpago, String condicionpago) {
        this.idcondicionpago = idcondicionpago;
        this.condicionpago = condicionpago;
    }

    public Integer getIdcondicionpago() {
        return idcondicionpago;
    }

    public void setIdcondicionpago(Integer idcondicionpago) {
        this.idcondicionpago = idcondicionpago;
    }

    public String getCondicionpago() {
        return condicionpago;
    }

    public void setCondicionpago(String condicionpago) {
        this.condicionpago = condicionpago;
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
        hash += (idcondicionpago != null ? idcondicionpago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condicionpago)) {
            return false;
        }
        Condicionpago other = (Condicionpago) object;
        if ((this.idcondicionpago == null && other.idcondicionpago != null) || (this.idcondicionpago != null && !this.idcondicionpago.equals(other.idcondicionpago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Condicionpago[ idcondicionpago=" + idcondicionpago + " ]";
    }
    
}

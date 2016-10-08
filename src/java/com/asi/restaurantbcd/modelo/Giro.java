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
@Table(name = "giro")
@NamedQueries({
    @NamedQuery(name = "Giro.findAll", query = "SELECT g FROM Giro g")})
public class Giro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGiro")
    private Integer idGiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "giro")
    private String giro;
    @OneToMany(mappedBy = "idGiro")
    private List<Cliente> clienteList;

    public Giro() {
    }

    public Giro(Integer idGiro) {
        this.idGiro = idGiro;
    }

    public Giro(Integer idGiro, String giro) {
        this.idGiro = idGiro;
        this.giro = giro;
    }

    public Integer getIdGiro() {
        return idGiro;
    }

    public void setIdGiro(Integer idGiro) {
        this.idGiro = idGiro;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGiro != null ? idGiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Giro)) {
            return false;
        }
        Giro other = (Giro) object;
        if ((this.idGiro == null && other.idGiro != null) || (this.idGiro != null && !this.idGiro.equals(other.idGiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Giro[ idGiro=" + idGiro + " ]";
    }
    
}

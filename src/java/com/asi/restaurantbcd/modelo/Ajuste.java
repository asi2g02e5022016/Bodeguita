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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JAEM
 */
@Entity
@Table(name = "ajuste")
@NamedQueries({
    @NamedQuery(name = "Ajuste.findAll", query = "SELECT a FROM Ajuste a")})
public class Ajuste implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AjustePK ajustePK;
    @Basic(optional = false)
   
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Basic(optional = false)
    
    
    @Column(nullable = false)
    private String idusuariocrea;
    @Basic(optional = false)
    
    @Column(nullable = false)
    private boolean autorizado;
    @Basic(optional = false)
    
    
    @Column(nullable = false)
    private String observacion;
//    @Basic(optional = false)
    
//    @Column(name = "idsucursal", nullable = false)
//    private int idsucursal1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ajuste")
    private List<Ajustedetalle> ajustedetalleList;

    public Ajuste() {
    }

    public Ajuste(AjustePK ajustePK) {
        this.ajustePK = ajustePK;
    }

    
    public Ajuste(int idajuste, int idsucursal) {
        this.ajustePK = new AjustePK(idajuste, idsucursal);
    }

    public AjustePK getAjustePK() {
        return ajustePK;
    }

    public void setAjustePK(AjustePK ajustePK) {
        this.ajustePK = ajustePK;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getIdusuariocrea() {
        return idusuariocrea;
    }

    public void setIdusuariocrea(String idusuariocrea) {
        this.idusuariocrea = idusuariocrea;
    }

    public boolean getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

//    public int getIdsucursal1() {
//        return idsucursal1;
//    }
//
//    public void setIdsucursal1(int idsucursal1) {
//        this.idsucursal1 = idsucursal1;
//    }

    public List<Ajustedetalle> getAjustedetalleList() {
        return ajustedetalleList;
    }

    public void setAjustedetalleList(List<Ajustedetalle> ajustedetalleList) {
        this.ajustedetalleList = ajustedetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ajustePK != null ? ajustePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ajuste)) {
            return false;
        }
        Ajuste other = (Ajuste) object;
        if ((this.ajustePK == null && other.ajustePK != null) || (this.ajustePK != null && !this.ajustePK.equals(other.ajustePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ajuste[ ajustePK=" + ajustePK + " ]";
    }
    
}

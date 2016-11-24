/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "numerofiscal")
@NamedQueries({
    @NamedQuery(name = "Numerofiscal.findAll", query = "SELECT n FROM Numerofiscal n")})
public class Numerofiscal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnumfiscal")
    private Integer idnumfiscal;
    @Column(name = "serie")
    private String serie;
    @Column(name = "fecharesolusion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharesolusion;
    @Column(name = "codinicial")
    private Integer codinicial;
    @Column(name = "codactual")
    private Integer codactual;
    @Column(name = "codfinal")
    private Integer codfinal;
    @JoinColumn(name = "idcaja", referencedColumnName = "idcaja")
    @ManyToOne
    private Caja idcaja;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")
    @ManyToOne
    private Sucursal idsucursal;
    @JoinColumn(name = "idtipodocumento", referencedColumnName = "idtipodocumento")
    @ManyToOne
    private Tipodocumento idtipodocumento;

    public Numerofiscal() {
    }

    public Numerofiscal(Integer idnumfiscal) {
        this.idnumfiscal = idnumfiscal;
    }

    public Integer getIdnumfiscal() {
        return idnumfiscal;
    }

    public void setIdnumfiscal(Integer idnumfiscal) {
        this.idnumfiscal = idnumfiscal;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Date getFecharesolusion() {
        return fecharesolusion;
    }

    public void setFecharesolusion(Date fecharesolusion) {
        this.fecharesolusion = fecharesolusion;
    }

    public Integer getCodinicial() {
        return codinicial;
    }

    public void setCodinicial(Integer codinicial) {
        this.codinicial = codinicial;
    }

    public Integer getCodactual() {
        return codactual;
    }

    public void setCodactual(Integer codactual) {
        this.codactual = codactual;
    }

    public Integer getCodfinal() {
        return codfinal;
    }

    public void setCodfinal(Integer codfinal) {
        this.codfinal = codfinal;
    }

    public Caja getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(Caja idcaja) {
        this.idcaja = idcaja;
    }

    public Tipodocumento getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(Tipodocumento idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }
    
    

    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnumfiscal != null ? idnumfiscal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Numerofiscal)) {
            return false;
        }
        Numerofiscal other = (Numerofiscal) object;
        if ((this.idnumfiscal == null && other.idnumfiscal != null) || (this.idnumfiscal != null && !this.idnumfiscal.equals(other.idnumfiscal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Numerofiscal[ idnumfiscal=" + idnumfiscal + " ]";
    }
    
}

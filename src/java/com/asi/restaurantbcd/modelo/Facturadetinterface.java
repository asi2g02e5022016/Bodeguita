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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author siman
 */
@Entity
@Table(name = "facturadetinterface", catalog = "ifbc", schema = "")
@NamedQueries({
    @NamedQuery(name = "Facturadetinterface.findAll", query = "SELECT f FROM Facturadetinterface f")})
public class Facturadetinterface implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencialinea")
    private Integer secuencialinea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "linea")
    private int linea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "documento")
    private int documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "serie")
    private String serie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "caja")
    private String caja;
    @Size(max = 255)
    @Column(name = "producto")
    private String producto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidades")
    private int unidades;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva")
    private double iva;

    public Facturadetinterface() {
    }

    public Facturadetinterface(Integer secuencialinea) {
        this.secuencialinea = secuencialinea;
    }

    public Facturadetinterface(Integer secuencialinea, int linea, int documento, String serie, String caja, int unidades, double iva) {
        this.secuencialinea = secuencialinea;
        this.linea = linea;
        this.documento = documento;
        this.serie = serie;
        this.caja = caja;
        this.unidades = unidades;
        this.iva = iva;
    }

    public Integer getSecuencialinea() {
        return secuencialinea;
    }

    public void setSecuencialinea(Integer secuencialinea) {
        this.secuencialinea = secuencialinea;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secuencialinea != null ? secuencialinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturadetinterface)) {
            return false;
        }
        Facturadetinterface other = (Facturadetinterface) object;
        if ((this.secuencialinea == null && other.secuencialinea != null) || (this.secuencialinea != null && !this.secuencialinea.equals(other.secuencialinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Facturadetinterface[ secuencialinea=" + secuencialinea + " ]";
    }
    
}

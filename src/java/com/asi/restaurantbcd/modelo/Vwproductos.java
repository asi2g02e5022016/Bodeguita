/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "vwproductos")
@NamedQueries({
    @NamedQuery(name = "Vwproductos.findAll", query = "SELECT v FROM Vwproductos v")})
public class Vwproductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VwproductosPK vwproductosPK; 
    
    @Basic(optional = false)
    @Column(name = "producto")
    private String producto;
    @Basic(optional = false)
    @Column(name = "idmarcaproducto")
    private int idmarcaproducto;
    @Column(name = "marcaproducto")
    private String marcaproducto;
    @Basic(optional = false)
    @Column(name = "idgrupoproducto")
    private int idgrupoproducto;
    @Column(name = "grupoproducto")
    private String grupoproducto;
    @Basic(optional = false)
    @Column(name = "idtipoproducto")
    private int idtipoproducto;
    @Column(name = "tipoproducto")
    private String tipoproducto;
    @Basic(optional = false)
    @Column(name = "idmedida")
    private int idmedida;
    @Column(name = "medida")
    private String medida;
    @Basic(optional = false)
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Basic(optional = false)
    @Column(name = "idusuario")
    private String idusuario;
    @Basic(optional = false)
    @Column(name = "activo")
    private int activo;
    @Basic(optional = false)
    @Column(name = "preciocompra")
    private double preciocompra;
    @Basic(optional = false)
    @Column(name = "precioventa")
    private double precioventa;
    @Basic(optional = false)
    @Column(name = "vendible")
    private boolean vendible;
    @Column(name = "excento")
    private Boolean excento;

    public Vwproductos() {
    }



    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getIdmarcaproducto() {
        return idmarcaproducto;
    }

    public void setIdmarcaproducto(int idmarcaproducto) {
        this.idmarcaproducto = idmarcaproducto;
    }

    public String getMarcaproducto() {
        return marcaproducto;
    }

    public void setMarcaproducto(String marcaproducto) {
        this.marcaproducto = marcaproducto;
    }

    public int getIdgrupoproducto() {
        return idgrupoproducto;
    }

    public void setIdgrupoproducto(int idgrupoproducto) {
        this.idgrupoproducto = idgrupoproducto;
    }

    public String getGrupoproducto() {
        return grupoproducto;
    }

    public void setGrupoproducto(String grupoproducto) {
        this.grupoproducto = grupoproducto;
    }

    public int getIdtipoproducto() {
        return idtipoproducto;
    }

    public void setIdtipoproducto(int idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }

    public String getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(String tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    public int getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(int idmedida) {
        this.idmedida = idmedida;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

 

    public double getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public boolean getVendible() {
        return vendible;
    }

    public void setVendible(boolean vendible) {
        this.vendible = vendible;
    }

    public Boolean getExcento() {
        return excento;
    }

    public void setExcento(Boolean excento) {
        this.excento = excento;
    }

    public VwproductosPK getVwproductosPK() {
        return vwproductosPK;
    }

    public void setVwproductosPK(VwproductosPK vwproductosPK) {
        this.vwproductosPK = vwproductosPK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.vwproductosPK);
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
        final Vwproductos other = (Vwproductos) obj;
        if (!Objects.equals(this.vwproductosPK, other.vwproductosPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vwproductos{" + "vwproductosPK=" + vwproductosPK + '}';
    }
    
    
    
    
}

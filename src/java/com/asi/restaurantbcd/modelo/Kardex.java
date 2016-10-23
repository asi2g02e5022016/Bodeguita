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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "kardex")
@NamedQueries({
    @NamedQuery(name = "Kardex.findAll", query = "SELECT k FROM Kardex k")})
public class Kardex implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KardexPK kardexPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "numdocumento")
    private String numdocumento;
    @Basic(optional = false)
    @Column(name = "tipodocumento")
    private String tipodocumento;
    @Basic(optional = false)
    @Column(name = "fechatransicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechatransicion;
    @Basic(optional = false)
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Basic(optional = false)
    @Column(name = "existenciaanterior")
    private int existenciaanterior;
    @Basic(optional = false)
    @Column(name = "existencianueva")
    private int existencianueva;
    @Column(name = "productoformula")
    private String productoformula;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sucursal sucursal;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Kardex() {
    }

    public Kardex(KardexPK kardexPK) {
        this.kardexPK = kardexPK;
    }

    public Kardex(KardexPK kardexPK, int cantidad, String numdocumento, String tipodocumento, Date fechatransicion, Date fechacreacion, int existenciaanterior, int existencianueva) {
        this.kardexPK = kardexPK;
        this.cantidad = cantidad;
        this.numdocumento = numdocumento;
        this.tipodocumento = tipodocumento;
        this.fechatransicion = fechatransicion;
        this.fechacreacion = fechacreacion;
        this.existenciaanterior = existenciaanterior;
        this.existencianueva = existencianueva;
    }

    public Kardex(int idkardex, int idsucursal) {
        this.kardexPK = new KardexPK(idkardex, idsucursal);
    }

    public KardexPK getKardexPK() {
        return kardexPK;
    }

    public void setKardexPK(KardexPK kardexPK) {
        this.kardexPK = kardexPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Date getFechatransicion() {
        return fechatransicion;
    }

    public void setFechatransicion(Date fechatransicion) {
        this.fechatransicion = fechatransicion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public int getExistenciaanterior() {
        return existenciaanterior;
    }

    public void setExistenciaanterior(int existenciaanterior) {
        this.existenciaanterior = existenciaanterior;
    }

    public int getExistencianueva() {
        return existencianueva;
    }

    public void setExistencianueva(int existencianueva) {
        this.existencianueva = existencianueva;
    }

    public String getProductoformula() {
        return productoformula;
    }

    public void setProductoformula(String productoformula) {
        this.productoformula = productoformula;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kardexPK != null ? kardexPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kardex)) {
            return false;
        }
        Kardex other = (Kardex) object;
        if ((this.kardexPK == null && other.kardexPK != null) || (this.kardexPK != null && !this.kardexPK.equals(other.kardexPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Kardex[ kardexPK=" + kardexPK + " ]";
    }
    
}

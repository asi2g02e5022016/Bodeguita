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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idkardex")
    private Integer idkardex;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numdocumento")
    private String numdocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipodocumento")
    private String tipodocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechatransicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechatransicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existenciaanterior")
    private int existenciaanterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existencianueva")
    private int existencianueva;
    @Size(max = 50)
    @Column(name = "productoformula")
    private String productoformula;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")
    @ManyToOne(optional = false)
    private Sucursal idsucursal;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Kardex() {
    }

    public Kardex(Integer idkardex) {
        this.idkardex = idkardex;
    }

    public Kardex(Integer idkardex, int cantidad, String numdocumento, String tipodocumento, Date fechatransicion, Date fechacreacion, int existenciaanterior, int existencianueva) {
        this.idkardex = idkardex;
        this.cantidad = cantidad;
        this.numdocumento = numdocumento;
        this.tipodocumento = tipodocumento;
        this.fechatransicion = fechatransicion;
        this.fechacreacion = fechacreacion;
        this.existenciaanterior = existenciaanterior;
        this.existencianueva = existencianueva;
    }

    public Integer getIdkardex() {
        return idkardex;
    }

    public void setIdkardex(Integer idkardex) {
        this.idkardex = idkardex;
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

    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
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
        hash += (idkardex != null ? idkardex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kardex)) {
            return false;
        }
        Kardex other = (Kardex) object;
        if ((this.idkardex == null && other.idkardex != null) || (this.idkardex != null && !this.idkardex.equals(other.idkardex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Kardex[ idkardex=" + idkardex + " ]";
    }
    
}

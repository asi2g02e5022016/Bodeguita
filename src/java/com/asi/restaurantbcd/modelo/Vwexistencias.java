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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PROGRAMADOR
 */
@Entity
@Table(name = "vwexistencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vwexistencias.findAll", query = "SELECT v FROM Vwexistencias v"),
    @NamedQuery(name = "Vwexistencias.findByIdexistencia", query = "SELECT v FROM Vwexistencias v WHERE v.idexistencia = :idexistencia"),
    @NamedQuery(name = "Vwexistencias.findByIdsucursal", query = "SELECT v FROM Vwexistencias v WHERE v.idsucursal = :idsucursal"),
    @NamedQuery(name = "Vwexistencias.findByIdproducto", query = "SELECT v FROM Vwexistencias v WHERE v.idproducto = :idproducto"),
    @NamedQuery(name = "Vwexistencias.findByValor", query = "SELECT v FROM Vwexistencias v WHERE v.valor = :valor"),
    @NamedQuery(name = "Vwexistencias.findByCostounitario", query = "SELECT v FROM Vwexistencias v WHERE v.costounitario = :costounitario"),
    @NamedQuery(name = "Vwexistencias.findBySucursal", query = "SELECT v FROM Vwexistencias v WHERE v.sucursal = :sucursal"),
    @NamedQuery(name = "Vwexistencias.findByIdcompania", query = "SELECT v FROM Vwexistencias v WHERE v.idcompania = :idcompania"),
    @NamedQuery(name = "Vwexistencias.findByCompania", query = "SELECT v FROM Vwexistencias v WHERE v.compania = :compania"),
    @NamedQuery(name = "Vwexistencias.findByProducto", query = "SELECT v FROM Vwexistencias v WHERE v.producto = :producto"),
    @NamedQuery(name = "Vwexistencias.findByIdtipoproducto", query = "SELECT v FROM Vwexistencias v WHERE v.idtipoproducto = :idtipoproducto"),
    @NamedQuery(name = "Vwexistencias.findByTipoproducto", query = "SELECT v FROM Vwexistencias v WHERE v.tipoproducto = :tipoproducto")})
public class Vwexistencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idexistencia")
    @Id
    private int idexistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsucursal")
    private int idsucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproducto")
    private int idproducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private float valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costounitario")
    private float costounitario;
    @Size(max = 50)
    @Column(name = "sucursal")
    private String sucursal;
    @Column(name = "idcompania")
    private Integer idcompania;
    @Size(max = 50)
    @Column(name = "compania")
    private String compania;
    @Size(max = 50)
    @Column(name = "producto")
    private String producto;
    @Column(name = "idtipoproducto")
    private Integer idtipoproducto;
    @Size(max = 100)
    @Column(name = "tipoproducto")
    private String tipoproducto;

    public Vwexistencias() {
    }

    public int getIdexistencia() {
        return idexistencia;
    }

    public void setIdexistencia(int idexistencia) {
        this.idexistencia = idexistencia;
    }

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getCostounitario() {
        return costounitario;
    }

    public void setCostounitario(float costounitario) {
        this.costounitario = costounitario;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public Integer getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Integer idcompania) {
        this.idcompania = idcompania;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getIdtipoproducto() {
        return idtipoproducto;
    }

    public void setIdtipoproducto(Integer idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }

    public String getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(String tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idexistencia;
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
        final Vwexistencias other = (Vwexistencias) obj;
        if (this.idexistencia != other.idexistencia) {
            return false;
        }
        return true;
    }
    
}

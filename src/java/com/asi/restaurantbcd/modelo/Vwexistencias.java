/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PROGRAMADOR
 */
@Entity
@Table(name = "vwexistencias")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vwexistencias.findAll", query = "SELECT v FROM Vwexistencias v")})
public class Vwexistencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VwexistenciasPK vwexistenciasPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsucursal")
    private int idsucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproducto")
    private int idproducto;
    @Column(name = "valor")
    private float valor;
    @Column(name = "costounitario")
    private float costounitario;
    @Column(name = "valreservado")
    private float valreservado;
    @Column(name = "transito")
    private float transito;
    
    @Size(max = 50)
    @Column(name = "sucursal")
    private String sucursal;
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
    
    public Vwexistencias(VwexistenciasPK vwexistenciasPK, int idproducto, int idsucursal){
        this.vwexistenciasPk = vwexistenciasPK;
        this.idproducto = idproducto;
        this.idsucursal = idsucursal;        
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

    public float getValreservado() {
        return valreservado;
    }

    public void setValreservado(float valreservado) {
        this.valreservado = valreservado;
    }

    public float getTransito() {
        return transito;
    }

    public void setTransito(float transito) {
        this.transito = transito;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
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

    public VwexistenciasPK getVwexistenciasPk() {
        return vwexistenciasPk;
    }

    public void setVwexistenciasPk(VwexistenciasPK vwexistenciasPk) {
        this.vwexistenciasPk = vwexistenciasPk;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.vwexistenciasPk);
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
        if (!Objects.equals(this.vwexistenciasPk, other.vwexistenciasPk)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vwexistencias{" + "vwexistenciasPk=" + vwexistenciasPk + '}';
    }

    
    
}

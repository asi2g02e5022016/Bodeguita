/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "compradetalle")
@NamedQueries({
    @NamedQuery(name = "Compradetalle.findAll", query = "SELECT c FROM Compradetalle c")})
public class Compradetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompradetallePK compradetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadsolicitada")
    private Double cantidadsolicitada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadconfirmada")
    private Double cantidadconfirmada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private Double precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva")
    private Double iva;
    @JoinColumns({
        @JoinColumn(name = "idcompra", referencedColumnName = "idcompra", insertable = false, updatable = false),
        @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Compra compra;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    
    @Transient
    private Double monto;
    @Transient
    private  Double total;
    @Transient
    private boolean editar;
    
    public Compradetalle() {
    }

    public Compradetalle(CompradetallePK compradetallePK) {
        this.compradetallePK = compradetallePK;
    }

    public Compradetalle(CompradetallePK compradetallePK, Double cantidadsolicitada,
            Double cantidadconfirmada, Double precio, Double iva) {
        this.compradetallePK = compradetallePK;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
        this.iva = iva;
    }

    public Compradetalle(int idcompradetalle, int idcompra, int idsucursal) {
        this.compradetallePK = new CompradetallePK(idcompradetalle, idcompra, idsucursal);
    }

    public CompradetallePK getCompradetallePK() {
        return compradetallePK;
    }

    public void setCompradetallePK(CompradetallePK compradetallePK) {
        this.compradetallePK = compradetallePK;
    }

    public Double getCantidadsolicitada() {
        return cantidadsolicitada;
    }

    public void setCantidadsolicitada(Double cantidadsolicitada) {
        this.cantidadsolicitada = cantidadsolicitada;
    }

    public Double getCantidadconfirmada() {
        return cantidadconfirmada;
    }

    public void setCantidadconfirmada(Double cantidadconfirmada) {
        this.cantidadconfirmada = cantidadconfirmada;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compradetallePK != null ? compradetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compradetalle)) {
            return false;
        }
        Compradetalle other = (Compradetalle) object;
        if ((this.compradetallePK == null && other.compradetallePK != null) || (this.compradetallePK != null && !this.compradetallePK.equals(other.compradetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Compradetalle[ compradetallePK=" + compradetallePK + " ]";
    }
    
}

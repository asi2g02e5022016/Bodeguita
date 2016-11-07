/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author JAEM
 */
@Entity
@Table(catalog = "ifbc", schema = "")
@NamedQueries({
    @NamedQuery(name = "Ajustedetalle.findAll", query = "SELECT a FROM Ajustedetalle a")})
public class Ajustedetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AjustedetallePK ajustedetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double costounitario;
//    @Basic(optional = false)    
//    @Column(nullable = false)
//    private int idproducto;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumns({
        @JoinColumn(name = "idajuste", referencedColumnName = "idajuste", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Ajuste ajuste;

    public Ajustedetalle() {
    }

    public Ajustedetalle(AjustedetallePK ajustedetallePK) {
        this.ajustedetallePK = ajustedetallePK;
    }

    public Ajustedetalle(AjustedetallePK ajustedetallePK, double cantidad, double costounitario, int idproducto) {
        this.ajustedetallePK = ajustedetallePK;
        this.cantidad = cantidad;
        this.costounitario = costounitario;
//        this.idproducto = idproducto;
    }

    public Ajustedetalle(int idajustedetalle, int idajuste, int idsucursal) {
        this.ajustedetallePK = new AjustedetallePK(idajustedetalle, idajuste, idsucursal);
    }

    public AjustedetallePK getAjustedetallePK() {
        return ajustedetallePK;
    }

    public void setAjustedetallePK(AjustedetallePK ajustedetallePK) {
        this.ajustedetallePK = ajustedetallePK;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostounitario() {
        return costounitario;
    }

    public void setCostounitario(double costounitario) {
        this.costounitario = costounitario;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

//    public int getIdproducto() {
//        return idproducto;
//    }
//
//    public void setIdproducto(int idproducto) {
//        this.idproducto = idproducto;
//    }
    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Ajuste getAjuste() {
        return ajuste;
    }

    public void setAjuste(Ajuste ajuste) {
        this.ajuste = ajuste;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ajustedetallePK != null ? ajustedetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ajustedetalle)) {
            return false;
        }
        Ajustedetalle other = (Ajustedetalle) object;
        if ((this.ajustedetallePK == null && other.ajustedetallePK != null) || (this.ajustedetallePK != null && !this.ajustedetallePK.equals(other.ajustedetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ajustedetalle[ ajustedetallePK=" + ajustedetallePK + " ]";
    }
    
}

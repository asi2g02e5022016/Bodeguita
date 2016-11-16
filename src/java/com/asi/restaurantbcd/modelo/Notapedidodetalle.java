/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import com.asi.restaurantebcd.negocio.util.EstadoEnum;
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
import javax.persistence.Transient;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "notapedidodetalle")
@NamedQueries({
    @NamedQuery(name = "Notapedidodetalle.findAll", query = "SELECT n FROM Notapedidodetalle n")})
public class Notapedidodetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotapedidodetallePK notapedidodetallePK;
    @Basic(optional = false)
    @Column(name = "cantidadsolicitada")
    private int cantidadsolicitada;
    @Basic(optional = false)
    @Column(name = "cantidadconfirmada")
    private int cantidadconfirmada;
    @Basic(optional = false)
    @Column(name = "cantidadrecibida")
    private int cantidadrecibida;
    @Basic(optional = false)
    @Column(name = "costo")
    private float costo;
    @JoinColumns({
        @JoinColumn(name = "idnotapedido", referencedColumnName = "idnotapedido", insertable = false, updatable = false),
        @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Notapedido notapedido;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;
    
    @Transient
    private float total;
    
    @Transient
    private float existencia = 0;
    
    @Transient
    private boolean lineaNueva=false;
    
    public Notapedidodetalle() {
    }

    public Notapedidodetalle(NotapedidodetallePK notapedidodetallePK) {
        this.notapedidodetallePK = notapedidodetallePK;
    }

    public Notapedidodetalle(NotapedidodetallePK notapedidodetallePK, int cantidadsolicitada, int cantidadconfirmada, float costo) {
        this.notapedidodetallePK = notapedidodetallePK;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.costo = costo;
    }

    public Notapedidodetalle(int idnotapeddet, int idnotapedido, int idsucursal) {
        this.notapedidodetallePK = new NotapedidodetallePK(idnotapeddet, idnotapedido, idsucursal);
    }

    public NotapedidodetallePK getNotapedidodetallePK() {
        return notapedidodetallePK;
    }

    public void setNotapedidodetallePK(NotapedidodetallePK notapedidodetallePK) {
        this.notapedidodetallePK = notapedidodetallePK;
    }

    public int getCantidadsolicitada() {
        return cantidadsolicitada;
    }

    public void setCantidadsolicitada(int cantidadsolicitada) {
        this.cantidadsolicitada = cantidadsolicitada;
    }

    public int getCantidadconfirmada() {
        return cantidadconfirmada;
    }

    public void setCantidadconfirmada(int cantidadconfirmada) {
        this.cantidadconfirmada = cantidadconfirmada;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float precio) {
        this.costo = precio;
    }

    public Notapedido getNotapedido() {
        return notapedido;
    }

    public void setNotapedido(Notapedido notapedido) {
        this.notapedido = notapedido;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notapedidodetallePK != null ? notapedidodetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notapedidodetalle)) {
            return false;
        }
        Notapedidodetalle other = (Notapedidodetalle) object;
        if ((this.notapedidodetallePK == null && other.notapedidodetallePK != null) || (this.notapedidodetallePK != null && !this.notapedidodetallePK.equals(other.notapedidodetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Notapedidodetalle[ notapedidodetallePK=" + notapedidodetallePK + " ]";
    }

    /**
     * @return the total
     */
    public float getTotal() {
        if(this.getNotapedido().getIdestado().getIdestado().equals(EstadoEnum.GENERADO.getInteger())){
        total = this.getCantidadsolicitada()*this.getCosto();
        }else{
         total = this.getCantidadsolicitada()*this.getCosto(); 
        }
        return total;
    }

    /**
     * @return the existencia
     */
    public float getExistencia() {
        return existencia;
    }

    /**
     * @param existencia the existencia to set
     */
    public void setExistencia(float existencia) {
        this.existencia = existencia;
    }

    /**
     * @return the cantidadrecibida
     */
    public int getCantidadrecibida() {
        return cantidadrecibida;
    }

    /**
     * @param cantidadrecibida the cantidadrecibida to set
     */
    public void setCantidadrecibida(int cantidadrecibida) {
        this.cantidadrecibida = cantidadrecibida;
    }

    /**
     * @return the lineaNueva
     */
    public boolean isLineaNueva() {
        return lineaNueva;
    }

    /**
     * @param lineaNueva the lineaNueva to set
     */
    public void setLineaNueva(boolean lineaNueva) {
        this.lineaNueva = lineaNueva;
    }

    
}

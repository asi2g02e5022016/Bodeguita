/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;

/**
 *
 * @author samaelopez
 */
public class OrdenpedidodetalleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected OrdenpedidodetallePK ordenpedidodetallePK;
    private Double cantidadsolicitada;
    private Double cantidadconfirmada;
    private Double precio;
       private OrdenpedidoDTO ordenpedidoDTO;
    private Double costo;
    private Double iva;
//    private Producto idproducto;
    private OrdenpedidoDTO ordenpedido;
    private Double monto;
    private Double total;
    public OrdenpedidodetalleDTO() {
    }

    public OrdenpedidodetalleDTO(OrdenpedidodetallePK ordenpedidodetallePK) {
        this.ordenpedidodetallePK = ordenpedidodetallePK;
    }

    public OrdenpedidodetalleDTO(OrdenpedidodetallePK ordenpedidodetallePK, 
            Double cantidadsolicitada, Double cantidadconfirmada,
            Double precio, Double costo, Double iva) {
        this.ordenpedidodetallePK = ordenpedidodetallePK;
        this.cantidadsolicitada = cantidadsolicitada;
        this.cantidadconfirmada = cantidadconfirmada;
        this.precio = precio;
        this.costo = costo;
        this.iva = iva;
    }

    public OrdenpedidodetalleDTO(int idordenpedidodet, int idordenpedido, int idSucursal) {
        this.ordenpedidodetallePK = new OrdenpedidodetallePK(idordenpedidodet, idordenpedido, idSucursal);
    }

    public OrdenpedidodetallePK getOrdenpedidodetallePK() {
        return ordenpedidodetallePK;
    }

    public void setOrdenpedidodetallePK(OrdenpedidodetallePK ordenpedidodetallePK) {
        this.ordenpedidodetallePK = ordenpedidodetallePK;
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

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }


//    public Producto getIdproducto() {
//        return idproducto;
//    }
//
//    public void setIdproducto(Producto idproducto) {
//        this.idproducto = idproducto;
//    }

    public OrdenpedidoDTO getOrdenpedido() {
        return ordenpedido;
    }

    public void setOrdenpedido(OrdenpedidoDTO ordenpedido) {
        this.ordenpedido = ordenpedido;
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

    public OrdenpedidoDTO getOrdenpedidoDTO() {
        return ordenpedidoDTO;
    }

    public void setOrdenpedidoDTO(OrdenpedidoDTO ordenpedidoDTO) {
        this.ordenpedidoDTO = ordenpedidoDTO;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenpedidodetallePK != null ? ordenpedidodetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenpedidodetalleDTO)) {
            return false;
        }
        OrdenpedidodetalleDTO other = (OrdenpedidodetalleDTO) object;
        if ((this.ordenpedidodetallePK == null && other.ordenpedidodetallePK != null) || (this.ordenpedidodetallePK != null && !this.ordenpedidodetallePK.equals(other.ordenpedidodetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.pedidoweb.modelo.Ordenpedidodetalle[ ordenpedidodetallePK=" + ordenpedidodetallePK + " ]";
    }
    
}

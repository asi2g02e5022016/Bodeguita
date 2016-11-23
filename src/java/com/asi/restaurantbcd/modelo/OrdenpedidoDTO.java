/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author samaelopez
 */

public class OrdenpedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    protected OrdenpedidoPK ordenpedidoPK;
    private Date fechapedido;
    private Integer mesa;
   private Integer idcliente;
    private String cliente;
   private Integer idestado;
    private Integer sucursal;
    private String idusuario;
    private List<OrdenpedidodetalleDTO> ordenpedidodetalleList;

    public OrdenpedidoDTO() {
    }

    public OrdenpedidoDTO(OrdenpedidoPK ordenpedidoPK) {
        this.ordenpedidoPK = ordenpedidoPK;
    }

    public OrdenpedidoDTO(OrdenpedidoPK ordenpedidoPK, Date fechapedido) {
        this.ordenpedidoPK = ordenpedidoPK;
        this.fechapedido = fechapedido;
    }

    public OrdenpedidoDTO(int idordenpedido, int idsucursal) {
        this.ordenpedidoPK = new OrdenpedidoPK(idordenpedido, idsucursal);
    }

    public OrdenpedidoPK getOrdenpedidoPK() {
        return ordenpedidoPK;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setOrdenpedidoPK(OrdenpedidoPK ordenpedidoPK) {
        this.ordenpedidoPK = ordenpedidoPK;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

//    public Cliente getIdcliente() {
//        return idcliente;
//    }
//
//    public void setIdcliente(Cliente idcliente) {
//        this.idcliente = idcliente;
//    }
//
//    public Estado getIdestado() {
//        return idestado;
//    }
//
//    public void setIdestado(Estado idestado) {
//        this.idestado = idestado;
//    }

//    public Sucursal getSucursal() {
//        return sucursal;
//    }
//
//    public void setSucursal(Sucursal sucursal) {
//        this.sucursal = sucursal;
//    }
//
//    public Usuario getIdusuario() {
//        return idusuario;
//    }
//
//    public void setIdusuario(Usuario idusuario) {
//        this.idusuario = idusuario;
//    }

    public List<OrdenpedidodetalleDTO> getOrdenpedidodetalleList() {
        return ordenpedidodetalleList;
    }

    public void setOrdenpedidodetalleList(List<OrdenpedidodetalleDTO> ordenpedidodetalleList) {
        this.ordenpedidodetalleList = ordenpedidodetalleList;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenpedidoPK != null ? ordenpedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenpedidoDTO)) {
            return false;
        }
        OrdenpedidoDTO other = (OrdenpedidoDTO) object;
        if ((this.ordenpedidoPK == null && other.ordenpedidoPK != null) || (this.ordenpedidoPK != null && !this.ordenpedidoPK.equals(other.ordenpedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.pedidoweb.modelo.Ordenpedido[ ordenpedidoPK=" + ordenpedidoPK + " ]";
    }
    
}

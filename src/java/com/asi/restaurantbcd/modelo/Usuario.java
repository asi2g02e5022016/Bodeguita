/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "idusuario")
    private String idusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @OneToMany(mappedBy = "idusuario")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Pedidoencabezado> pedidoencabezadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Producto> productoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Ordenproduccion> ordenproduccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Facturaencabezado> facturaencabezadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Kardex> kardexList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Facturaanulada> facturaanuladaList;
    @OneToMany(mappedBy = "idusuariod")
    private List<Notapedido> notapedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Ordenpedido> ordenpedidoList;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne(optional = false)
    private Empleado idempleado;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne(optional = false)
    private Perfil idperfil;

    public Usuario() {
    }

    public Usuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(String idusuario, String clave, boolean activo) {
        this.idusuario = idusuario;
        this.clave = clave;
        this.activo = activo;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    public List<Pedidoencabezado> getPedidoencabezadoList() {
        return pedidoencabezadoList;
    }

    public void setPedidoencabezadoList(List<Pedidoencabezado> pedidoencabezadoList) {
        this.pedidoencabezadoList = pedidoencabezadoList;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public List<Ordenproduccion> getOrdenproduccionList() {
        return ordenproduccionList;
    }

    public void setOrdenproduccionList(List<Ordenproduccion> ordenproduccionList) {
        this.ordenproduccionList = ordenproduccionList;
    }

    public List<Facturaencabezado> getFacturaencabezadoList() {
        return facturaencabezadoList;
    }

    public void setFacturaencabezadoList(List<Facturaencabezado> facturaencabezadoList) {
        this.facturaencabezadoList = facturaencabezadoList;
    }

    public List<Kardex> getKardexList() {
        return kardexList;
    }

    public void setKardexList(List<Kardex> kardexList) {
        this.kardexList = kardexList;
    }

    public List<Facturaanulada> getFacturaanuladaList() {
        return facturaanuladaList;
    }

    public void setFacturaanuladaList(List<Facturaanulada> facturaanuladaList) {
        this.facturaanuladaList = facturaanuladaList;
    }

    public List<Notapedido> getNotapedidoList() {
        return notapedidoList;
    }

    public void setNotapedidoList(List<Notapedido> notapedidoList) {
        this.notapedidoList = notapedidoList;
    }

    public List<Ordenpedido> getOrdenpedidoList() {
        return ordenpedidoList;
    }

    public void setOrdenpedidoList(List<Ordenpedido> ordenpedidoList) {
        this.ordenpedidoList = ordenpedidoList;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    public Perfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Perfil idperfil) {
        this.idperfil = idperfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}

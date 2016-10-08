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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "sucursal")
@NamedQueries({
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s")})
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsucursal")
    private Integer idsucursal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "sucursal")
    private String sucursal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Pedidoencabezado> pedidoencabezadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Pedidodetalle> pedidodetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Producto> productoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Existencia> existenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Ordenproduccion> ordenproduccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Empleado> empleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Kardex> kardexList;
    @OneToMany(mappedBy = "idsucursal")
    private List<Numerofiscal> numerofiscalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Notapedido> notapedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Ordenpedido> ordenpedidoList;
    @JoinColumn(name = "idcompania", referencedColumnName = "idcompania")
    @ManyToOne(optional = false)
    private Compania idcompania;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsucursal")
    private List<Caja> cajaList;

    public Sucursal() {
    }

    public Sucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public Sucursal(Integer idsucursal, String sucursal, String direccion, String telefono) {
        this.idsucursal = idsucursal;
        this.sucursal = sucursal;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public List<Pedidodetalle> getPedidodetalleList() {
        return pedidodetalleList;
    }

    public void setPedidodetalleList(List<Pedidodetalle> pedidodetalleList) {
        this.pedidodetalleList = pedidodetalleList;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public List<Existencia> getExistenciaList() {
        return existenciaList;
    }

    public void setExistenciaList(List<Existencia> existenciaList) {
        this.existenciaList = existenciaList;
    }

    public List<Ordenproduccion> getOrdenproduccionList() {
        return ordenproduccionList;
    }

    public void setOrdenproduccionList(List<Ordenproduccion> ordenproduccionList) {
        this.ordenproduccionList = ordenproduccionList;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public List<Kardex> getKardexList() {
        return kardexList;
    }

    public void setKardexList(List<Kardex> kardexList) {
        this.kardexList = kardexList;
    }

    public List<Numerofiscal> getNumerofiscalList() {
        return numerofiscalList;
    }

    public void setNumerofiscalList(List<Numerofiscal> numerofiscalList) {
        this.numerofiscalList = numerofiscalList;
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

    public Compania getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Compania idcompania) {
        this.idcompania = idcompania;
    }

    public List<Caja> getCajaList() {
        return cajaList;
    }

    public void setCajaList(List<Caja> cajaList) {
        this.cajaList = cajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsucursal != null ? idsucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.idsucursal == null && other.idsucursal != null) || (this.idsucursal != null && !this.idsucursal.equals(other.idsucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Sucursal[ idsucursal=" + idsucursal + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproducto")
    private Integer idproducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "producto")
    private String producto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Pedidodetalle> pedidodetalleList;
    @JoinColumn(name = "idgrupoproducto", referencedColumnName = "idgrupoproducto")
    @ManyToOne(optional = false)
    private Grupoproducto idgrupoproducto;
    @JoinColumn(name = "idmarcaproducto", referencedColumnName = "idmarcaproducto")
    @ManyToOne(optional = false)
    private Marcaproducto idmarcaproducto;
    @JoinColumn(name = "idmedida", referencedColumnName = "idmedida")
    @ManyToOne(optional = false)
    private Medida idmedida;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")
    @ManyToOne(optional = false)
    private Sucursal idsucursal;
    @JoinColumn(name = "idtipoproducto", referencedColumnName = "idtipoproducto")
    @ManyToOne(optional = false)
    private Tipoproducto idtipoproducto;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Existencia> existenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Kardex> kardexList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Ordenpedidodetalle> ordenpedidodetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Compradetalle> compradetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Notapedidodetalle> notapedidodetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Facturadetalle> facturadetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Ordenproducciondetalle> ordenproducciondetalleList;

    public Producto() {
    }

    public Producto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Producto(Integer idproducto, String producto, Date fechacreacion, boolean activo) {
        this.idproducto = idproducto;
        this.producto = producto;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Pedidodetalle> getPedidodetalleList() {
        return pedidodetalleList;
    }

    public void setPedidodetalleList(List<Pedidodetalle> pedidodetalleList) {
        this.pedidodetalleList = pedidodetalleList;
    }

    public Grupoproducto getIdgrupoproducto() {
        return idgrupoproducto;
    }

    public void setIdgrupoproducto(Grupoproducto idgrupoproducto) {
        this.idgrupoproducto = idgrupoproducto;
    }

    public Marcaproducto getIdmarcaproducto() {
        return idmarcaproducto;
    }

    public void setIdmarcaproducto(Marcaproducto idmarcaproducto) {
        this.idmarcaproducto = idmarcaproducto;
    }

    public Medida getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(Medida idmedida) {
        this.idmedida = idmedida;
    }

    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
    }

    public Tipoproducto getIdtipoproducto() {
        return idtipoproducto;
    }

    public void setIdtipoproducto(Tipoproducto idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public List<Existencia> getExistenciaList() {
        return existenciaList;
    }

    public void setExistenciaList(List<Existencia> existenciaList) {
        this.existenciaList = existenciaList;
    }

    public List<Kardex> getKardexList() {
        return kardexList;
    }

    public void setKardexList(List<Kardex> kardexList) {
        this.kardexList = kardexList;
    }

    public List<Ordenpedidodetalle> getOrdenpedidodetalleList() {
        return ordenpedidodetalleList;
    }

    public void setOrdenpedidodetalleList(List<Ordenpedidodetalle> ordenpedidodetalleList) {
        this.ordenpedidodetalleList = ordenpedidodetalleList;
    }

    public List<Compradetalle> getCompradetalleList() {
        return compradetalleList;
    }

    public void setCompradetalleList(List<Compradetalle> compradetalleList) {
        this.compradetalleList = compradetalleList;
    }

    public List<Notapedidodetalle> getNotapedidodetalleList() {
        return notapedidodetalleList;
    }

    public void setNotapedidodetalleList(List<Notapedidodetalle> notapedidodetalleList) {
        this.notapedidodetalleList = notapedidodetalleList;
    }

    public List<Facturadetalle> getFacturadetalleList() {
        return facturadetalleList;
    }

    public void setFacturadetalleList(List<Facturadetalle> facturadetalleList) {
        this.facturadetalleList = facturadetalleList;
    }

    public List<Ordenproducciondetalle> getOrdenproducciondetalleList() {
        return ordenproducciondetalleList;
    }

    public void setOrdenproducciondetalleList(List<Ordenproducciondetalle> ordenproducciondetalleList) {
        this.ordenproducciondetalleList = ordenproducciondetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Producto[ idproducto=" + idproducto + " ]";
    }
    
}

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
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "producto")
    private String producto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<CompraDetalle> compraDetalleList;
    @JoinColumn(name = "idGrupoProducto", referencedColumnName = "idGrupoProducto")
    @ManyToOne(optional = false)
    private GrupoProducto idGrupoProducto;
    @JoinColumn(name = "idMarcaProducto", referencedColumnName = "idMarcaProducto")
    @ManyToOne(optional = false)
    private MarcaProducto idMarcaProducto;
    @JoinColumn(name = "idMedida", referencedColumnName = "idMedida")
    @ManyToOne(optional = false)
    private Medida idMedida;
    @JoinColumn(name = "idSucursal", referencedColumnName = "idSucursal")
    @ManyToOne(optional = false)
    private Sucursal idSucursal;
    @JoinColumn(name = "idTipoProducto", referencedColumnName = "idTipoProducto")
    @ManyToOne(optional = false)
    private TipoProducto idTipoProducto;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<Existencia> existenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<Kardex> kardexList;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String producto, Date fechaCreacion, boolean activo) {
        this.idProducto = idProducto;
        this.producto = producto;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<CompraDetalle> getCompraDetalleList() {
        return compraDetalleList;
    }

    public void setCompraDetalleList(List<CompraDetalle> compraDetalleList) {
        this.compraDetalleList = compraDetalleList;
    }

    public GrupoProducto getIdGrupoProducto() {
        return idGrupoProducto;
    }

    public void setIdGrupoProducto(GrupoProducto idGrupoProducto) {
        this.idGrupoProducto = idGrupoProducto;
    }

    public MarcaProducto getIdMarcaProducto() {
        return idMarcaProducto;
    }

    public void setIdMarcaProducto(MarcaProducto idMarcaProducto) {
        this.idMarcaProducto = idMarcaProducto;
    }

    public Medida getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(Medida idMedida) {
        this.idMedida = idMedida;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    public TipoProducto getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(TipoProducto idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Producto[ idProducto=" + idProducto + " ]";
    }
    
}

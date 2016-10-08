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

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "ordenproduccion")
@NamedQueries({
    @NamedQuery(name = "Ordenproduccion.findAll", query = "SELECT o FROM Ordenproduccion o")})
public class Ordenproduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idordenproduccion")
    private Integer idordenproduccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechapedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapedido;
    @JoinColumn(name = "idestado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado idestado;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")
    @ManyToOne(optional = false)
    private Sucursal idsucursal;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idordenproduccion")
    private List<Ordenproducciondetalle> ordenproducciondetalleList;

    public Ordenproduccion() {
    }

    public Ordenproduccion(Integer idordenproduccion) {
        this.idordenproduccion = idordenproduccion;
    }

    public Ordenproduccion(Integer idordenproduccion, Date fechapedido) {
        this.idordenproduccion = idordenproduccion;
        this.fechapedido = fechapedido;
    }

    public Integer getIdordenproduccion() {
        return idordenproduccion;
    }

    public void setIdordenproduccion(Integer idordenproduccion) {
        this.idordenproduccion = idordenproduccion;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public Estado getIdestado() {
        return idestado;
    }

    public void setIdestado(Estado idestado) {
        this.idestado = idestado;
    }

    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
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
        hash += (idordenproduccion != null ? idordenproduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenproduccion)) {
            return false;
        }
        Ordenproduccion other = (Ordenproduccion) object;
        if ((this.idordenproduccion == null && other.idordenproduccion != null) || (this.idordenproduccion != null && !this.idordenproduccion.equals(other.idordenproduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ordenproduccion[ idordenproduccion=" + idordenproduccion + " ]";
    }
    
}

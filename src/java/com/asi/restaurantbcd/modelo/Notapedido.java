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
@Table(name = "notapedido")
@NamedQueries({
    @NamedQuery(name = "Notapedido.findAll", query = "SELECT n FROM Notapedido n")})
public class Notapedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotapedido")
    private Integer idnotapedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechageneracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechageneracion;
    @Column(name = "fechaingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaingreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "idusuarios")
    private String idusuarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnotapedido")
    private List<Notapedidodetalle> notapedidodetalleList;
    @JoinColumn(name = "idestado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado idestado;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")
    @ManyToOne(optional = false)
    private Sucursal idsucursal;
    @JoinColumn(name = "idusuariod", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuariod;

    public Notapedido() {
    }

    public Notapedido(Integer idnotapedido) {
        this.idnotapedido = idnotapedido;
    }

    public Notapedido(Integer idnotapedido, Date fechageneracion, String idusuarios) {
        this.idnotapedido = idnotapedido;
        this.fechageneracion = fechageneracion;
        this.idusuarios = idusuarios;
    }

    public Integer getIdnotapedido() {
        return idnotapedido;
    }

    public void setIdnotapedido(Integer idnotapedido) {
        this.idnotapedido = idnotapedido;
    }

    public Date getFechageneracion() {
        return fechageneracion;
    }

    public void setFechageneracion(Date fechageneracion) {
        this.fechageneracion = fechageneracion;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(String idusuarios) {
        this.idusuarios = idusuarios;
    }

    public List<Notapedidodetalle> getNotapedidodetalleList() {
        return notapedidodetalleList;
    }

    public void setNotapedidodetalleList(List<Notapedidodetalle> notapedidodetalleList) {
        this.notapedidodetalleList = notapedidodetalleList;
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

    public Usuario getIdusuariod() {
        return idusuariod;
    }

    public void setIdusuariod(Usuario idusuariod) {
        this.idusuariod = idusuariod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnotapedido != null ? idnotapedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notapedido)) {
            return false;
        }
        Notapedido other = (Notapedido) object;
        if ((this.idnotapedido == null && other.idnotapedido != null) || (this.idnotapedido != null && !this.idnotapedido.equals(other.idnotapedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Notapedido[ idnotapedido=" + idnotapedido + " ]";
    }
    
}

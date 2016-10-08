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
@Table(name = "ordenpedido")
@NamedQueries({
    @NamedQuery(name = "Ordenpedido.findAll", query = "SELECT o FROM Ordenpedido o")})
public class Ordenpedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idordenpedido")
    private Integer idordenpedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechapedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mesa")
    private int mesa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idordenpedido")
    private List<Ordenpedidodetalle> ordenpedidodetalleList;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "idestado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado idestado;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal")
    @ManyToOne(optional = false)
    private Sucursal idsucursal;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Ordenpedido() {
    }

    public Ordenpedido(Integer idordenpedido) {
        this.idordenpedido = idordenpedido;
    }

    public Ordenpedido(Integer idordenpedido, Date fechapedido, int mesa) {
        this.idordenpedido = idordenpedido;
        this.fechapedido = fechapedido;
        this.mesa = mesa;
    }

    public Integer getIdordenpedido() {
        return idordenpedido;
    }

    public void setIdordenpedido(Integer idordenpedido) {
        this.idordenpedido = idordenpedido;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public List<Ordenpedidodetalle> getOrdenpedidodetalleList() {
        return ordenpedidodetalleList;
    }

    public void setOrdenpedidodetalleList(List<Ordenpedidodetalle> ordenpedidodetalleList) {
        this.ordenpedidodetalleList = ordenpedidodetalleList;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idordenpedido != null ? idordenpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenpedido)) {
            return false;
        }
        Ordenpedido other = (Ordenpedido) object;
        if ((this.idordenpedido == null && other.idordenpedido != null) || (this.idordenpedido != null && !this.idordenpedido.equals(other.idordenpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ordenpedido[ idordenpedido=" + idordenpedido + " ]";
    }
    
}

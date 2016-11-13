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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "ordenpedido")
@NamedQueries({
    @NamedQuery(name = "Ordenpedido.findAll", query = "SELECT o FROM Ordenpedido o")})
public class Ordenpedido implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenpedido")
    private List<Facturaencabezado> facturaencabezadoList;
    @Column(name = "mesa")
    private Integer mesa;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdenpedidoPK ordenpedidoPK;
    @Basic(optional = false)
    @Column(name = "fechapedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenpedido")
    private List<Ordenpedidodetalle> ordenpedidodetalleList;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "idestado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado idestado;
    @JoinColumn(name = "idsucursal", referencedColumnName = "idsucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sucursal sucursal;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Ordenpedido() {
    }

    public Ordenpedido(OrdenpedidoPK ordenpedidoPK) {
        this.ordenpedidoPK = ordenpedidoPK;
    }

    public Ordenpedido(OrdenpedidoPK ordenpedidoPK, Date fechapedido, int mesa) {
        this.ordenpedidoPK = ordenpedidoPK;
        this.fechapedido = fechapedido;
        this.mesa = mesa;
    }

    public Ordenpedido(int idordenpedido, int idsucursal) {
        this.ordenpedidoPK = new OrdenpedidoPK(idordenpedido, idsucursal);
    }

    public OrdenpedidoPK getOrdenpedidoPK() {
        return ordenpedidoPK;
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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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
        hash += (ordenpedidoPK != null ? ordenpedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenpedido)) {
            return false;
        }
        Ordenpedido other = (Ordenpedido) object;
        if ((this.ordenpedidoPK == null && other.ordenpedidoPK != null) || (this.ordenpedidoPK != null && !this.ordenpedidoPK.equals(other.ordenpedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Ordenpedido[ ordenpedidoPK=" + ordenpedidoPK + " ]";
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public List<Facturaencabezado> getFacturaencabezadoList() {
        return facturaencabezadoList;
    }

    public void setFacturaencabezadoList(List<Facturaencabezado> facturaencabezadoList) {
        this.facturaencabezadoList = facturaencabezadoList;
    }
    
}

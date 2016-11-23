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
   
    @JoinColumn(name = "idreceta", referencedColumnName = "idreceta")
    @ManyToOne(optional= true)
    private Receta idreceta;
    @Column(name = "excento")
    private Boolean excento;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproducto")
    private Integer idproducto;
  
    @Basic(optional = false)
    @Column(name = "producto")
    private String producto;
    
    @Basic(optional = false)
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    
    @Basic(optional = false)
    @Column(name ="preciocompra")
     private double preciocompra;
    
    @Column(name ="precioventa")
    private double precioventa;
    
    @Column(name = "activo")
    private boolean activo;
    
   @Column(name ="vendible")
   private boolean vendible;

    @JoinColumn(name = "idgrupoproducto", referencedColumnName = "idgrupoproducto")
    @ManyToOne(optional = false)
    private Grupoproducto idgrupoproducto;
    @JoinColumn(name = "idmarcaproducto", referencedColumnName = "idmarcaproducto")
    @ManyToOne(optional = false)
    private Marcaproducto idmarcaproducto;
    @JoinColumn(name = "idmedida", referencedColumnName = "idmedida")
    @ManyToOne(optional = false)
    private Medida idmedida;
    @JoinColumn(name = "idtipoproducto", referencedColumnName = "idtipoproducto")
    @ManyToOne(optional = false)
    private Tipoproducto idtipoproducto;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario idusuario;

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

    public double getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public boolean isVendible() {
        return vendible;
    }

    public void setVendible(boolean vendible) {
        this.vendible = vendible;
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

    public Boolean getExcento() {
        return excento;
    }

    public void setExcento(Boolean excento) {
        this.excento = excento;
    }

    public Receta getIdreceta() {
        return idreceta;
    }

    public void setIdreceta(Receta idreceta) {
        this.idreceta = idreceta;
    }

}

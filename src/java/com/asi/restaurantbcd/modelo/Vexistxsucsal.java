/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PROGRAMADOR
 */
@Entity
@Table(name = "vexistxsucsal")
@NamedQueries({
    @NamedQuery(name = "Vexistxsucsal.findAll", query = "SELECT v FROM Vexistxsucsal v")})
public class Vexistxsucsal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsucursal")
    private int idsucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcompania")
    private int idcompania;
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
    @Column(name = "idexistencia")
    private Integer idexistencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @Column(name = "costounitario")
    private Float costounitario;
    @Column(name = "idproducto")
    @Id
    private Integer idproducto;
    @Column(name = "idmarcaproducto")
    private Integer idmarcaproducto;
    @Column(name = "idgrupoproducto")
    private Integer idgrupoproducto;
    @Column(name = "idtipoproducto")
    private Integer idtipoproducto;
    @Column(name = "idmedida")
    private Integer idmedida;
    @Size(max = 50)
    @Column(name = "producto")
    private String producto;
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Size(max = 50)
    @Column(name = "idusuario")
    private String idusuario;
    @Column(name = "activo")
    private Boolean activo;

    public Vexistxsucsal() {
    }

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public int getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(int idcompania) {
        this.idcompania = idcompania;
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

    public Integer getIdexistencia() {
        return idexistencia;
    }

    public void setIdexistencia(Integer idexistencia) {
        this.idexistencia = idexistencia;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Float getCostounitario() {
        return costounitario;
    }

    public void setCostounitario(Float costounitario) {
        this.costounitario = costounitario;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Integer getIdmarcaproducto() {
        return idmarcaproducto;
    }

    public void setIdmarcaproducto(Integer idmarcaproducto) {
        this.idmarcaproducto = idmarcaproducto;
    }

    public Integer getIdgrupoproducto() {
        return idgrupoproducto;
    }

    public void setIdgrupoproducto(Integer idgrupoproducto) {
        this.idgrupoproducto = idgrupoproducto;
    }

    public Integer getIdtipoproducto() {
        return idtipoproducto;
    }

    public void setIdtipoproducto(Integer idtipoproducto) {
        this.idtipoproducto = idtipoproducto;
    }

    public Integer getIdmedida() {
        return idmedida;
    }

    public void setIdmedida(Integer idmedida) {
        this.idmedida = idmedida;
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

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
}

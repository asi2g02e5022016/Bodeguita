/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "recetadetalle")
@NamedQueries({
    @NamedQuery(name = "Recetadetalle.findAll", query = "SELECT r FROM Recetadetalle r")})
public class Recetadetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecetadetallePK recetadetallePK;
    @Column(name = "salida")
    private Integer salida;
    @Column(name = "cantidad")
    private Long cantidad;
    @Column(name = "idmedidacargo")
    private Integer idmedidacargo;
    @Column(name = "factorconvercion")
    private Short factorconvercion;
    @JoinColumn(name = "idreceta", referencedColumnName = "idreceta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receta receta;

    public Recetadetalle() {
    }

    public Recetadetalle(RecetadetallePK recetadetallePK) {
        this.recetadetallePK = recetadetallePK;
    }

    public Recetadetalle(int idproducto, int idreceta) {
        this.recetadetallePK = new RecetadetallePK(idproducto, idreceta);
    }

    public RecetadetallePK getRecetadetallePK() {
        return recetadetallePK;
    }

    public void setRecetadetallePK(RecetadetallePK recetadetallePK) {
        this.recetadetallePK = recetadetallePK;
    }

    public Integer getSalida() {
        return salida;
    }

    public void setSalida(Integer salida) {
        this.salida = salida;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdmedidacargo() {
        return idmedidacargo;
    }

    public void setIdmedidacargo(Integer idmedidacargo) {
        this.idmedidacargo = idmedidacargo;
    }

    public Short getFactorconvercion() {
        return factorconvercion;
    }

    public void setFactorconvercion(Short factorconvercion) {
        this.factorconvercion = factorconvercion;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recetadetallePK != null ? recetadetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recetadetalle)) {
            return false;
        }
        Recetadetalle other = (Recetadetalle) object;
        if ((this.recetadetallePK == null && other.recetadetallePK != null) || (this.recetadetallePK != null && !this.recetadetallePK.equals(other.recetadetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Recetadetalle[ recetadetallePK=" + recetadetallePK + " ]";
    }
    
}

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "grupoproducto")
@NamedQueries({
    @NamedQuery(name = "Grupoproducto.findAll", query = "SELECT g FROM Grupoproducto g")})
public class Grupoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgrupoproducto")
    private Integer idgrupoproducto;
    @Basic(optional = false)
  
    @Column(name = "grupoproducto")
    private String grupoproducto;
    @Basic(optional = false)
  
    @Column(name = "nivel")
    private int nivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupoproducto")
    private List<Producto> productoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupodependencia")
    private List<Grupoproducto> grupoproductoList;
    @JoinColumn(name = "grupodependencia", referencedColumnName = "idgrupoproducto")
    @ManyToOne(optional = true)
    private Grupoproducto grupodependencia;
    
    @Transient
    private String breadcumb; 
    
    public Grupoproducto() {
    }

    public Grupoproducto(Integer idgrupoproducto) {
        this.idgrupoproducto = idgrupoproducto;
    }

    public Grupoproducto(Integer idgrupoproducto, String grupoproducto, int nivel) {
        this.idgrupoproducto = idgrupoproducto;
        this.grupoproducto = grupoproducto;
        this.nivel = nivel;
    }

    public Integer getIdgrupoproducto() {
        return idgrupoproducto;
    }

    public void setIdgrupoproducto(Integer idgrupoproducto) {
        this.idgrupoproducto = idgrupoproducto;
    }

    public String getGrupoproducto() {
        return grupoproducto;
    }

    public void setGrupoproducto(String grupoproducto) {
        this.grupoproducto = grupoproducto;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public List<Grupoproducto> getGrupoproductoList() {
        return grupoproductoList;
    }

    public void setGrupoproductoList(List<Grupoproducto> grupoproductoList) {
        this.grupoproductoList = grupoproductoList;
    }

    public Grupoproducto getGrupodependencia() {
        return grupodependencia;
    }

    public void setGrupodependencia(Grupoproducto grupodependencia) {
        this.grupodependencia = grupodependencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupoproducto != null ? idgrupoproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoproducto)) {
            return false;
        }
        Grupoproducto other = (Grupoproducto) object;
        if ((this.idgrupoproducto == null && other.idgrupoproducto != null) || (this.idgrupoproducto != null && !this.idgrupoproducto.equals(other.idgrupoproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Grupoproducto[ idgrupoproducto=" + idgrupoproducto + " ]";
    }
    
        
    public String getBreadcumb(){
        breadcumb = null;
        if(getGrupodependencia()==null){
        breadcumb = "Root -> "+this.getGrupoproducto();
        return breadcumb;
       }else {
            breadcumb = this.getGrupodependencia().getBreadcumb() + " -> " + this.getGrupoproducto();
           return breadcumb;
        }
    }
    
}

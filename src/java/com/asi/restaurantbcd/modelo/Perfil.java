/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author JAEM
 */
@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idPerfil")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfil;
    
    @NotEmpty
    @Column(name = "codigo", nullable = false, length = 8)
    @Length(max = 8)
    private String codigo;
    
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    
    
    @Column(name = "descripcion", nullable = true, length = 200)
    @Length(max = 200)
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfil", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;
    
//    	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinTable(name = "perfilOpcionMenu", 
//		joinColumns = @JoinColumn(name = "perfilId", referencedColumnName = "idPerfil"), 
//				inverseJoinColumns = @JoinColumn(name = "opcionMenuId", referencedColumnName = "id"))
//    private Set<OpcionMenu> opcionesDeMenu = new HashSet<OpcionMenu>();

    public Perfil() {
    }

    public Perfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Perfil(Integer idPerfil, String perfil) {
        this.idPerfil = idPerfil;
        this.nombre = perfil;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    /**
//     * @return the opcionesDeMenu
//     */
//    public Set<OpcionMenu> getOpcionesDeMenu() {
//        return opcionesDeMenu;
//    }
//
//    /**
//     * @param opcionesDeMenu the opcionesDeMenu to set
//     */
//    public void setOpcionesDeMenu(Set<OpcionMenu> opcionesDeMenu) {
//        this.opcionesDeMenu = opcionesDeMenu;
//    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPerfil fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}

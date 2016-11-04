/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author joaquin
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    //@EmbeddedId
    //protected UsuarioPK usuarioPk;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "idusuario")
    private String idusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @OneToOne(optional = false)
    private Empleado idempleado;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @OneToOne(optional = false)
    private Perfil idperfil;

    public Usuario() {
    }

    /*
    public Usuario(UsuarioPK usuarioPk) {
        this.usuarioPk = usuarioPk;             
    }
    
    public Usuario(String idusuario, int idperfil, int idempleado) {
        this.usuarioPk = new UsuarioPK(idusuario, idperfil, idempleado);      
    }

    public UsuarioPK getUsuarioPk() {
        return usuarioPk;
    }
    

    public void setUsuarioPk(UsuarioPK usuarioPk) {
        this.usuarioPk = usuarioPk;
    }
    */

    public Usuario(String idusuario, String clave, boolean activo, Empleado idempleado, Perfil idperfil) {
        this.idusuario = idusuario;
        this.clave = clave;
        this.activo = activo;
        this.idempleado = idempleado;
        this.idperfil = idperfil;
    }
    
    
    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    public Perfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Perfil idperfil) {
        this.idperfil = idperfil;
    }
    
    

    /*
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.usuarioPk);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.usuarioPk, other.usuarioPk)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuarioPk=" + usuarioPk + ", idusuario=" + idusuario + ", clave=" + clave + ", activo=" + activo + ", idempleado=" + idempleado + ", idperfil=" + idperfil + '}';
    }
    */  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idusuario);
        hash = 79 * hash + Objects.hashCode(this.clave);
        hash = 79 * hash + (this.activo ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.idempleado);
        hash = 79 * hash + Objects.hashCode(this.idperfil);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.idusuario, other.idusuario)) {
            return false;
        }
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        if (!Objects.equals(this.idempleado, other.idempleado)) {
            return false;
        }
        if (!Objects.equals(this.idperfil, other.idperfil)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idusuario=" + idusuario + ", clave=" + clave + ", activo=" + activo + ", idempleado=" + idempleado + ", idperfil=" + idperfil + '}';
    }

   
    
}

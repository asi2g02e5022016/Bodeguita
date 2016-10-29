/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.controller.seguridad;

import com.asi.restaurantbcd.modelo.Empleado;
import com.asi.restaurantbcd.modelo.Perfil;
import com.asi.restaurantbcd.modelo.Sucursal;
import com.asi.restaurantbcd.modelo.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author samael lopez
 */
@Named(value = "sessionUsr")
@SessionScoped
public class SessionUsr implements Serializable {
    public SessionUsr() {
    }
    private Usuario usuario;
    private Sucursal sucursal;
    private Perfil perfil;
    private Empleado EmpleSucursal;
    private String token;
    private BigInteger codPant;
    private Date fecha = new Date();
    private String url;



    public BigInteger getCodPant() {
        return codPant;
    }

    public void setCodPant(BigInteger codPant) {
        this.codPant = codPant;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Empleado getEmpleSucursal() {
        return EmpleSucursal;
    }

    public void setEmpleSucursal(Empleado EmpleSucursal) {
        this.EmpleSucursal = EmpleSucursal;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.usuario);
        hash = 37 * hash + Objects.hashCode(this.token);
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
        final SessionUsr other = (SessionUsr) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.token, other.token)) {
            return false;
        }
        return true;
    }
 
    
}

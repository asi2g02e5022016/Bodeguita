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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samaelopez
 */
@Entity
@Table(name = "opcionmenu")
@NamedQueries({
    @NamedQuery(name = "Opcionmenu.findAll", query = "SELECT o FROM Opcionmenu o")})
public class Opcionmenu implements Serializable {
private Opcionmenu menuPadre;
	private boolean asociado;
private Set<Opcionmenu> subMenus = new HashSet<Opcionmenu>();
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "estado")
    private String estado;
    @Size(max = 50)
    @Column(name = "etiqueta")
    private String etiqueta;
    @Column(name = "orden")
    private Integer orden;
    @Size(max = 100)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visible")
    private boolean visible;
    @JoinTable(name = "perfilopcionmenu", joinColumns = {
        @JoinColumn(name = "opcionmenuid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "perfilid", referencedColumnName = "idperfil")})
    @ManyToMany
    private List<Perfil> perfilList;
    @OneToMany(mappedBy = "menupadreid")
    private List<Opcionmenu> opcionmenuList;
    @JoinColumn(name = "menupadreid", referencedColumnName = "id")
    @ManyToOne
    private Opcionmenu menupadreid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menuPadreId", nullable = true)
	public Opcionmenu getMenuPadre() {
		return menuPadre;
	}

	public void setMenuPadre(Opcionmenu menuPadre) {
		this.menuPadre = menuPadre;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "menuPadre")
	@OrderBy("orden")
	public Set<Opcionmenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(Set<Opcionmenu> subMenus) {
		this.subMenus = subMenus;
	}
    public Opcionmenu() {
    }

    public Opcionmenu(Integer id) {
        this.id = id;
    }

    public Opcionmenu(Integer id, String estado, boolean visible) {
        this.id = id;
        this.estado = estado;
        this.visible = visible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    public List<Opcionmenu> getOpcionmenuList() {
        return opcionmenuList;
    }

    public void setOpcionmenuList(List<Opcionmenu> opcionmenuList) {
        this.opcionmenuList = opcionmenuList;
    }

    public Opcionmenu getMenupadreid() {
        return menupadreid;
    }

    public void setMenupadreid(Opcionmenu menupadreid) {
        this.menupadreid = menupadreid;
    }
	@Transient
	public boolean isAsociado() {
		return asociado;
	}

	public void setAsociado(boolean asociado) {
		this.asociado = asociado;
	}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcionmenu)) {
            return false;
        }
        Opcionmenu other = (Opcionmenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Opcionmenu[ id=" + id + " ]";
    }
    
}

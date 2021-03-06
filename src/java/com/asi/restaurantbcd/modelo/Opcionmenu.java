package com.asi.restaurantbcd.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "opcionmenu", uniqueConstraints = @UniqueConstraint(columnNames = { "etiqueta" }))
public class Opcionmenu implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String etiqueta;
	private String url;
	private Integer orden;
	private boolean visible;
	private Opcionmenu menuPadre;
	private Set<Opcionmenu> subMenus = new HashSet<Opcionmenu>();
	private boolean asociado;

	public Opcionmenu() {
		this.visible = true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "url", nullable = true, length = 100)
	@Length(max = 100)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "visible", nullable = false)
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

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

	@Transient
	public boolean isAsociado() {
		return asociado;
	}

	public void setAsociado(boolean asociado) {
		this.asociado = asociado;
	}

	@Column(name = "etiqueta", nullable = true, length = 50)
	@Length(max = 100)
	@NotEmpty
	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	

	@Column(name = "orden", nullable = true)
	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
    
    @Override
    public String toString() {
        return this.etiqueta;
    }
	
    @Transient
    public boolean isParentMenu(){
    	if(this.getSubMenus().size()>0){
    		return true;
    	}
    	if(this.getSubMenus().size()==0 && (this.getUrl()==null || this.getUrl().length()<5)){
    		return true;
    	}
    	return false;
    }
    
    @Transient
    public boolean isRootMenu(){
    	if(this.getMenuPadre()==null){
    		return true;
    	}
        	return false;
    }
    
    @Transient
    public String getBreadcumb(){
        if(getMenuPadre()==null){
        return "Root -> "+this.etiqueta;
       }else {
           return this.getMenuPadre().getBreadcumb() + " -> " + this.getEtiqueta();
        }
    }

	//
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "opcion_perfil_id", nullable = true)
	// @ForeignKey(name = "fk_opcion_perfil_id")
	// public OpcionPerfil getOpcionPerfil() {
	// return opcionPerfil;
	// }
	//
	// public void setOpcionPerfil(OpcionPerfil opcionPerfil) {
	// this.opcionPerfil = opcionPerfil;
	// }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPerfil fields are not set
        if (!(object instanceof Opcionmenu)) {
            return false;
        }
        Opcionmenu other = (Opcionmenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    

}

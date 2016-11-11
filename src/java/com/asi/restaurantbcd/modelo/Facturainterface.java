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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author siman
 */
@Entity
@Table(name = "facturainterface", catalog = "ifbc", schema = "")
@NamedQueries({
    @NamedQuery(name = "Facturainterface.findAll", query = "SELECT f FROM Facturainterface f")})
public class Facturainterface implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secuencia")
    private Integer secuencia;
    @Column(name = "documento")
    private Integer documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "caja")
    private String caja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "serie")
    private String serie;
    @Size(max = 255)
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "fecha_documento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDocumento;
    @Size(max = 255)
    @Column(name = "forma_pago")
    private String formaPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cajero")
    private String cajero;
    @Size(max = 255)
    @Column(name = "mesa")
    private String mesa;
    @Size(max = 255)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "anulado")
    private byte[] anulado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doc_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docTimestamp;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "cargada")
    private byte[] cargada;
    @Size(max = 255)
    @Column(name = "error")
    private String error;

    public Facturainterface() {
    }

    public Facturainterface(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public Facturainterface(Integer secuencia, String caja, String serie, String cajero, byte[] anulado, Date docTimestamp, byte[] cargada) {
        this.secuencia = secuencia;
        this.caja = caja;
        this.serie = serie;
        this.cajero = cajero;
        this.anulado = anulado;
        this.docTimestamp = docTimestamp;
        this.cargada = cargada;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public byte[] getAnulado() {
        return anulado;
    }

    public void setAnulado(byte[] anulado) {
        this.anulado = anulado;
    }

    public Date getDocTimestamp() {
        return docTimestamp;
    }

    public void setDocTimestamp(Date docTimestamp) {
        this.docTimestamp = docTimestamp;
    }

    public byte[] getCargada() {
        return cargada;
    }

    public void setCargada(byte[] cargada) {
        this.cargada = cargada;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secuencia != null ? secuencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturainterface)) {
            return false;
        }
        Facturainterface other = (Facturainterface) object;
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asi.restaurantbcd.modelo.Facturainterface[ secuencia=" + secuencia + " ]";
    }
    
}
